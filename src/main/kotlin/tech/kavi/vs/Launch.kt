package tech.kavi.vs

import io.vertx.core.Vertx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Component
import tech.kavi.vs.common.AnnotationBeanNameGenerator

@Import(BeanConfig::class)
@ComponentScan(nameGenerator = AnnotationBeanNameGenerator::class)
@Component
class Launcher : ApplicationListener<ApplicationEvent> {

    @Autowired
    private lateinit var vertx: Vertx

    @Autowired
    private lateinit var verticle: MainVerticle

    /**
     * spring启动初始化verticle
     * */
    override fun onApplicationEvent(applicationEvent: ApplicationEvent) {
        vertx.deployVerticle(verticle)
    }

    /**
     * spring初始加载
     * */
    companion object {
        val context:AnnotationConfigApplicationContext by lazy { AnnotationConfigApplicationContext() }
        @JvmStatic
        fun main(args: Array<String>) {

            context.register(Launcher::class.java)
            context.refresh()
        }
    }
}