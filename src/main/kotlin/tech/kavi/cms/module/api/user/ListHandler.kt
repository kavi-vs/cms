package tech.kavi.cms.module.api.user

import io.vertx.core.http.HttpMethod
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import tech.kavi.cms.dao.UserDao
import tech.kavi.vs.mybatis.singleAsync
import tech.kavi.vs.web.ControllerHandler
import tech.kavi.vs.web.HandlerRequest

@HandlerRequest(path = "/list", method = HttpMethod.GET)
class ListHandler @Autowired constructor(
        @Qualifier("config") val config: JsonObject,
        val userDao: UserDao
) : ControllerHandler() {
    /**
     * 自定义路由参数
     * */
    override fun route(route: Route) {
        super.route(route)
        route.order(1)
    }

    override fun handle(routingContext: RoutingContext) {
        singleAsync(userDao::listUser).subscribe({
            routingContext.response().end(it.toString())
        }, {
            routingContext.response().end(it.message)
        })
    }
}