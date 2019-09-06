package tech.kavi.vs.common

import io.vertx.core.Handler
import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext

/**
 * 控制器handler
 * 可自定义route参数
 * */
abstract class ControllerHandler: Handler<RoutingContext> {
    open fun route(route: Route) {}
}