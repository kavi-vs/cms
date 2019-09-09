package tech.kavi.cms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import tech.kavi.cms.modules.HttpVerticle;
import tech.kavi.vs.web.AnnotationBeanNameGenerator;
import tech.kavi.vs.web.LauncherVerticle;

@Import(BeanConfig.class)
@ComponentScan(nameGenerator= AnnotationBeanNameGenerator.class)
public class ApplicationVerticle extends LauncherVerticle {

    @Autowired
    private HttpVerticle httpVerticle;

    @Override
    public void start() throws Exception {
        super.start();
        vertx.deployVerticle(httpVerticle);
    }

    public static void main(String[] args ) {
        launcher(ApplicationVerticle.class);
    }
}
