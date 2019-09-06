package tech.kavi.vs.common

import org.springframework.stereotype.Controller

/**
 * @param value 当前控制器下的模块包
 * @param isPrefixPath 是否使用包名作为路径前缀
 * */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Controller
annotation class HandlerScan(vararg val value: String, val isPrefixPath: Boolean = true, val uris:Array<Uri> = [])