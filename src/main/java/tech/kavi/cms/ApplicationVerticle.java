package tech.kavi.cms;


import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import tech.kavi.cms.web.WebController;
import tech.kavi.vs.mybatis.MybatisDataSourceBean;
import tech.kavi.vs.web.HandlerRequestAnnotationBeanName;
import tech.kavi.vs.web.LauncherVerticle;

@Import({BeanConfig.class, MybatisDataSourceBean.class})
@ComponentScan(nameGenerator= HandlerRequestAnnotationBeanName.class)
public class ApplicationVerticle extends LauncherVerticle {

    @Autowired
    private Router router;

    @Autowired
    private WebController webController;

    @Override
    public void start() throws Exception {
        super.start();
        webController.create(router, "/web");
        vertx.createHttpServer().requestHandler(router).listen(8080);
    }

    public static void main(String[] args ) {
        launcher(ApplicationVerticle.class);
    }
}
