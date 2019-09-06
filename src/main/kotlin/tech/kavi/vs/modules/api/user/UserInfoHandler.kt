package tech.kavi.vs.modules.api.user

import io.vertx.core.http.HttpMethod
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import tech.kavi.vs.common.HandlerRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import tech.kavi.vs.common.ControllerHandler

@HandlerRequest(path = "/admin/list", method = HttpMethod.GET)
class UserInfoHandler @Autowired constructor(@Qualifier("config") val config: JsonObject) : ControllerHandler() {
    /**
     * 自定义路由参数
     * */
    override fun route(route: Route) {
        super.route(route)
        route.order(1)
    }

    override fun handle(routingContext: RoutingContext) {
        routingContext.response().end("xxxxxxxxxxxxxxxxxxxxxxx")
    }
}