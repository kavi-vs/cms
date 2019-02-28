package ssf.api.backend.verticles;

import io.vertx.core.AbstractVerticle;
import org.springframework.stereotype.Component;

@Component
public class HttpVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        System.out.println("http...");
    }
}
