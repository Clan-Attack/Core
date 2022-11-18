package at.clanattack.impl.bootstrap.util.annotation

import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfo
import io.github.classgraph.MethodInfo
import at.clanattack.bootstrap.util.annotation.IAnnotationScanner
import at.clanattack.xjkl.scope.asExpr

class AnnotationScanner : IAnnotationScanner {

    val loaders = mutableListOf<ClassLoader>()

    fun addLoader(loader: ClassLoader) = asExpr { this.loaders.add(loader) }

    override fun scanClasses(
        annotation: Class<out Annotation>,
        filter: (ClassInfo) -> Boolean,
        vararg packages: String
    ) = loaders.map { cl ->
        ClassGraph()
            .overrideClassLoaders(cl)
            .enableClassInfo()
            .enableAnnotationInfo()
            .acceptPackages(*packages)
            .scan()
            .allClasses
            .filter { it.hasAnnotation(annotation) }
            .filter(filter)
            .map { it.loadClass() }
    }.flatten()

    override fun scanMethods(
        annotation: Class<out Annotation>,
        filter: (MethodInfo) -> Boolean,
        vararg packages: String
    ) = loaders.map { cl ->
        ClassGraph()
            .overrideClassLoaders(cl)
            .enableMethodInfo()
            .enableAnnotationInfo()
            .acceptPackages(*packages)
            .scan()
            .allClasses
            .asSequence()
            .map { it.methodInfo }
            .flatten()
            .filter { it.hasAnnotation(annotation) }
            .filter(filter)
            .map { it.loadClassAndGetMethod() }
            .toList()
    }.flatten()


}