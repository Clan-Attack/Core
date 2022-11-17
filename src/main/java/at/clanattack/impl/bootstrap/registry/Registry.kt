package at.clanattack.impl.bootstrap.registry

import at.clanattack.xjkl.scope.asExpr

open class Registry<T : Any>(vararg val possible: Pair<List<Class<out Any>>, List<() -> Any>>) {

    private val registry = mutableMapOf<Class<out T>, T>()

    protected fun getInstance(`class`: Class<out T>) = registry[`class`]

    protected fun registerInstance(instance: T) = this.registerInstance(instance::class.java, instance)

    protected fun <U : T> registerInstance(`class`: Class<out U>, instance: U) = asExpr { registry[`class`] = instance }

    protected fun createInstance(`class`: Class<out T>) {
        for ((types, instances) in this.possible) {
            try {
                val constructor = `class`.getDeclaredConstructor(*types.toTypedArray())
                val instance = constructor.newInstance(instances.map { it() })
                this.registerInstance(`class`, instance)
                return
            } catch (e: Exception) {
            }
        }

        throw IllegalStateException("Couldn't register $`class` because the class doesn't have the correct constructor.")
    }

}