package tech.kavi.cms

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import org.springframework.context.annotation.Bean
import tech.kavi.vs.core.VertxBeans

/**
 * 依赖参数全局初始化
 * */
class BeanConfig : VertxBeans() {
    /**
     * 注入router
     */
    //todo route end aop注入捕捉每次end时增加方法
    @Bean
    fun router(vertx: Vertx) = Router.router(vertx)
}