package at.clanattack.impl.bootstrap.registry

import at.clanattack.xjkl.scope.asExpr

open class Registry<T : Any>(val possible: MutableList<Pair<List<Class<out Any>>, List<() -> Any>>>) {

    constructor(vararg possible: Pair<List<Class<out Any>>, List<() -> Any>>) : this(possible.toMutableList())

    constructor(`class`: Class<out Any>, get: () -> Any) : this(listOf(`class`) to listOf(get))

    init {
        possible.add(emptyList<Class<out Any>>() to emptyList())
    }

    private val registry = mutableMapOf<Class<out T>, T>()

    protected fun getInstance(`class`: Class<out T>) = registry[`class`]

    protected fun hasInstance(`class`: Class<out T>) = `class` in registry

    protected fun registerInstance(instance: T) = this.registerInstance(instance::class.java, instance)

    protected fun registerInstanceOrKeep(instance: T) = this.registerInstanceOrKeep(instance::class.java, instance)

    protected fun <U : T> registerInstance(`class`: Class<out U>, instance: U) = asExpr { registry[`class`] = instance }

    protected fun <U : T> registerInstanceOrKeep(`class`: Class<out U>, instance: U) {
        if (this.hasInstance(`class`)) return
        registry[`class`] = instance
    }

    protected fun createInstance(`class`: Class<out T>) {
        for ((types, instances) in this.possible) {
            try {
                val constructor = `class`.getDeclaredConstructor(*types.toTypedArray())
                val instance = constructor.newInstance(*instances.map { it() }.toTypedArray())
                this.registerInstance(`class`, instance)
                return
            } catch (_: Exception) { }
        }

        throw IllegalStateException("Couldn't register $`class` because the class doesn't have the correct constructor.")
    }

    protected fun createInstanceOrKeep(`class`: Class<out T>) {
        if (this.hasInstance(`class`)) return
        this.createInstance(`class`)
    }

}