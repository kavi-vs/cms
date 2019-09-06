package tech.kavi.vs.modules

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import tech.kavi.vs.modules.api.ApiController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import tech.kavi.vs.modules.api.user.UserInfoHandler

/**
 * web处理器
 * */
@Component
class HttpVerticle : AbstractVerticle() {

    /**
     * 注入全局路由
     * */
    @Autowired
    private lateinit var router: Router


    /**
     * 注入模块Api控制器
     * */
    @Autowired
    private lateinit var apiController: ApiController

    @Throws(Exception::class)
    override fun start() {

        apiController.create(router, "/api")

        vertx.createHttpServer().requestHandler(router).listen(80){
            if (it.succeeded()) {
                println("成功启动监听端口")
            } else {
                println("failed")
            }
        }
    }
}