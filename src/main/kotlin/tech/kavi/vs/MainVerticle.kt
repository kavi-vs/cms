package tech.kavi.vs

import io.vertx.core.AbstractVerticle
import tech.kavi.vs.modules.HttpVerticle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MainVerticle : AbstractVerticle() {

    @Autowired
    private lateinit var httpVerticle: HttpVerticle

    /**
     * 初始化处理模块
     */
    @Throws(Exception::class)
    override fun start() {
       vertx.deployVerticle(httpVerticle)
    }

}
