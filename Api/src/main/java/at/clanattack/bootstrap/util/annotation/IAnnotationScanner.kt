package at.clanattack.bootstrap.util.annotation

import io.github.classgraph.ClassInfo
import io.github.classgraph.MethodInfo
import java.lang.reflect.Method
import kotlin.reflect.KClass

interface IAnnotationScanner {

    fun scanClasses(
        annotation: KClass<out Annotation>,
        filter: (ClassInfo) -> Boolean,
        vararg packages: String = arrayOf("*")
    ) = scanClasses(annotation.java, filter, *packages)
        .map { it.kotlin }

    fun scanClasses(
        annotation: Class<out Annotation>,
        filter: (ClassInfo) -> Boolean,
        vararg packages: String = arrayOf("*")
    ): List<Class<*>>

    fun scanClasses(
        annotation: KClass<out Annotation>,
        vararg packages: String = arrayOf("*")
    ) = scanClasses(annotation.java, *packages)
        .map { it.kotlin }

    fun scanClasses(
        annotation: Class<out Annotation>,
        vararg packages: String = arrayOf("*")
    ) = scanClasses(annotation, { true }, *packages)

    fun scanMethods(
        annotation: KClass<out Annotation>,
        filter: (MethodInfo) -> Boolean,
        vararg packages: String = arrayOf("*")
    ) = scanMethods(annotation.java, filter, *packages)

    fun scanMethods(
        annotation: Class<out Annotation>,
        filter: (MethodInfo) -> Boolean,
        vararg packages: String = arrayOf("*")
    ): List<Method>

    fun scanMethods(
        annotation: KClass<out Annotation>,
        vararg packages: String = arrayOf("*")
    ) = scanMethods(annotation.java, *packages)

    fun scanMethods(
        annotation: Class<out Annotation>,
        vararg packages: String = arrayOf("*")
    ) = scanMethods(annotation, { true }, *packages)

}