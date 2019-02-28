package ssf.api.backend;


import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({BeanConfiguration.class})
@ComponentScan("ssf.api.backend")
@Component
public class Launch implements ApplicationListener {
    public static void main( String[] args ) { new AnnotationConfigApplicationContext(Launch.class); }

    @Autowired
    private Vertx vertx;

    @Autowired
    private MainMicroServiceVerticle verticle;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        vertx.deployVerticle(verticle);
    }

}
