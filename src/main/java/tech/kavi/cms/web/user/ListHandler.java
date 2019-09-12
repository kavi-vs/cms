package tech.kavi.cms.web.user;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import tech.kavi.cms.mapper.UserMapper;
import tech.kavi.vs.mybatis.AsyncHelperKt;
import tech.kavi.vs.web.ControllerHandler;
import tech.kavi.vs.web.HandlerRequest;

@HandlerRequest(path = "/list", method = HttpMethod.GET)
public class ListHandler extends ControllerHandler {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void handle(RoutingContext routingContext){
        AsyncHelperKt.singleAsync(userMapper::listUser).subscribe(it -> {
            routingContext.response().end(it.toString());
        }, e-> {
            routingContext.response().end(e.getMessage());
        });
    }
}
