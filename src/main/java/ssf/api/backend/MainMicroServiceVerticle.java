package ssf.api.backend;

import io.vertx.core.AbstractVerticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ssf.api.backend.verticles.HttpVerticle;

@Component
public class MainMicroServiceVerticle extends AbstractVerticle {


    @Autowired
    private HttpVerticle httpVerticle;

    @Override
    public void start() throws Exception {
        vertx.deployVerticle(httpVerticle);
    }
}
