package tech.kavi.vs.common

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Uri(val path: String, val uri:String)
