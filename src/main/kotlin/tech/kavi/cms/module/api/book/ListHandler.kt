package tech.kavi.cms.module.api.book

import io.vertx.core.http.HttpMethod
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import tech.kavi.cms.dao.BookDao
import tech.kavi.vs.mybatis.singleAsync
import tech.kavi.vs.web.ControllerHandler
import tech.kavi.vs.web.HandlerRequest

@HandlerRequest(path = "/list", method = HttpMethod.GET)
class ListHandler @Autowired constructor(
        @Qualifier("config") val config: JsonObject,
        val bookDao: BookDao
) : ControllerHandler() {

    override fun handle(routingContext: RoutingContext) {
        singleAsync(bookDao::listBook).subscribe({
            routingContext.response().end(it.toString())
        }, {
            routingContext.response().end(it.message)
        })
    }
}