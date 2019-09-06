package tech.kavi.vs.common

import io.vertx.core.Handler
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import tech.kavi.vs.Launcher

/**
 * 控制器抽象类
 */
abstract class Controller(val handlers: Router.() -> Unit = {}) {

    private val SLASH = "/"

    private val log = LoggerFactory.getLogger(this::class.java)
    /**
     * 创建模块子路由
     * @param router 路由
     * @param mountPoint 子路由地址
     * */
    fun create(router: Router, mountPoint:String) {
        router.apply { handlers() }
        this.loadController(router)
        router.mountSubRouter(mountPoint, router)
    }

    /**
     * 加载模块控制器
     * @param router 路由
     * */
    private fun loadController(router: Router) {
        try {
            // 获得当前类所在的包位置
            val handlerPackages = this.javaClass.getAnnotation(HandlerScan::class.java)
            if (handlerPackages != null) {
                // 当前处理器包位置
                val packagePath = this.javaClass.`package`.name
                // 组合当前控制下的所有handler地址
                val pathList = handlerPackages.value.map { "$packagePath.$it" }.toList()
                // 获取所有请求handler类
                Launcher.context.getBeansWithAnnotation(HandlerRequest::class.java).filter { (_, beanHandler) ->
                    val packageName = beanHandler.javaClass.`package`.name
                    when(pathList.isEmpty()) {
                        true -> packageName.startsWith(packagePath)
                        else -> !pathList.find { packageName.startsWith(it) }.isNullOrBlank()
                    }
                }.forEach { _, beanHandler ->
                    this.loadHandler(router, beanHandler, this.substrPath(beanHandler, packagePath, handlerPackages.uris))
                }
            }
        } catch (e: Exception) {
            log.error("Failed bing controller to router", e)
        }
    }

    /**
     * handler绑定至路由
     * */
    @Suppress("UNCHECKED_CAST")
    private fun loadHandler(router: Router, beanHandler: Any, path: String) {
        try {
            val handler = beanHandler as Handler<RoutingContext>
            val request = handler.javaClass.getAnnotation(HandlerRequest::class.java)
            checkNotNull(request)
            val url = this.pathToUrl(path, request.path)
            router.route(request.method, url).also {route ->
                // 增加自定义路由处理
                if (beanHandler is ControllerHandler) beanHandler.route(route)
                route.handler(handler)
            }
            log.info("Add RequestHandler:${beanHandler.javaClass.simpleName} to path:$url")
        } catch (e: Exception) {
            log.error("Failed add handler to router", e)
        }

    }

    /**
     * 校验并将路径转换至url地址
     * */
    private fun pathToUrl(modulePath:String, handlerUrl:String): String {
        var path = modulePath
        if (path.first().toString() != SLASH) path = SLASH + path
        if (path.last().toString() == SLASH) path = path.substringBeforeLast(SLASH)
        return path + when(handlerUrl.first().toString()) {
            SLASH -> handlerUrl
            else -> SLASH + handlerUrl
        }
    }

    /**
     * 截取类路径
     * @param beanHandler 注入的handler类
     * @param packagePath 当前类路径
     * @param uris 当前类所设定的路径重定义
     * */
    private fun substrPath(beanHandler: Any, packagePath: String, uris:Array<Uri>): String {
        val handlerPath = beanHandler.javaClass.`package`.name
        val packageUri = when(packagePath == handlerPath){
            true -> SLASH
            else -> handlerPath.substringAfter("$packagePath.").replace(".", SLASH)
        }
        uris.forEach {
            if (it.path == packageUri) return it.uri
        }
        return packageUri
    }
}