package tech.kavi.vs.common

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationBeanNameGenerator
import org.springframework.util.StringUtils

/**
 * 重构beanName注入方式
 * */
class AnnotationBeanNameGenerator : AnnotationBeanNameGenerator() {

    override fun generateBeanName(definition: BeanDefinition, registry: BeanDefinitionRegistry): String? {
        if (definition is AnnotatedBeanDefinition) {
            val beanName = determineBeanNameFromAnnotation(definition)
            if (StringUtils.hasText(beanName)) {
                // Explicit bean name found.
                return beanName
            }
            // 指定HandlerRequest使用全类路径作为bean名
            if (definition.metadata.hasAnnotation(HandlerRequest::class.java.name) && definition.beanClassName != null) {
                return definition.beanClassName
            }
        }
        return buildDefaultBeanName(definition, registry)
    }
}