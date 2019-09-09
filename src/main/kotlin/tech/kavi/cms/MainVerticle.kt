package tech.kavi.cms

import tech.kavi.cms.modules.HttpVerticle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import tech.kavi.vs.web.HandlerRequestAnnotationBeanName
import tech.kavi.vs.web.LauncherVerticle

@Import(BeanConfig::class)
@ComponentScan(nameGenerator = HandlerRequestAnnotationBeanName::class)
class MainVerticle : LauncherVerticle() {

    @Autowired
    private lateinit var httpVerticle: HttpVerticle

    @Throws(Exception::class)
    override fun start() {
        super.start()
        vertx.deployVerticle(httpVerticle)
    }

    companion object {
        @JvmStatic
        fun main(args:Array<String>) {
            launcher(MainVerticle::class.java)
        }
    }
}
