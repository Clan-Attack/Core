package at.clanattack.top.bootstrap

import at.clanattack.bootstrap.util.annotation.IAnnotationScanner
import at.clanattack.bootstrap.util.log.ILogger
import at.clanattack.top.TopCore
import io.github.classgraph.ClassInfo
import io.github.classgraph.MethodInfo
import kotlin.reflect.KClass

private val scanner: IAnnotationScanner
    get() = TopCore.core.annotationScanner

fun scanClasses(
    annotation: KClass<out Annotation>,
    filter: (ClassInfo) -> Boolean,
    vararg packages: String = arrayOf("*")
) = scanner.scanClasses(annotation, filter, *packages)

fun scanClasses(
    annotation: Class<out Annotation>,
    filter: (ClassInfo) -> Boolean,
    vararg packages: String = arrayOf("*")
) = scanner.scanClasses(annotation, filter, *packages)

fun scanClasses(
    annotation: KClass<out Annotation>,
    vararg packages: String = arrayOf("*")
) = scanner.scanClasses(annotation, *packages)

fun scanClasses(
    annotation: Class<out Annotation>,
    vararg packages: String = arrayOf("*")
) = scanner.scanClasses(annotation, *packages)

fun scanMethods(
    annotation: KClass<out Annotation>,
    filter: (MethodInfo) -> Boolean,
    vararg packages: String = arrayOf("*")
) = scanner.scanMethods(annotation, filter, *packages)

fun scanMethods(
    annotation: Class<out Annotation>,
    filter: (MethodInfo) -> Boolean,
    vararg packages: String = arrayOf("*")
) = scanner.scanMethods(annotation, filter, *packages)

fun scanMethods(
    annotation: KClass<out Annotation>,
    vararg packages: String = arrayOf("*")
) = scanner.scanMethods(annotation, *packages)

fun scanMethods(
    annotation: Class<out Annotation>,
    vararg packages: String = arrayOf("*")
) = scanner.scanMethods(annotation, *packages)

val logger: ILogger
    get() = TopCore.core.logger
