package at.clanattack.impl.bootstrap.registry

import at.clanattack.bootstrap.ICore
import at.clanattack.top.TopCore
import at.clanattack.xjkl.extention.supplyNullable
import at.clanattack.xjkl.scope.asExpr
import java.lang.reflect.InvocationTargetException

object BackingRegistry {

    private val registry = mutableMapOf<Class<out Any>, Any>()
    private val possibleConstructors = mutableMapOf<List<Class<out Any>>, List<() -> Any>>(
        emptyList<Class<out Any>>() to emptyList(),
        listOf<Class<out Any>>(ICore::class.java) to listOf { TopCore.core }
    )

    fun addConstructor(parameters: List<Class<out Any>>, getters: List<() -> Any>) =
        asExpr { this.possibleConstructors[parameters] = getters }

    fun addConstructors(list: List<Pair<List<Class<out Any>>, List<() -> Any>>>) =
        asExpr { this.possibleConstructors.putAll(list) }

    fun addConstructors(array: Array<out Pair<List<Class<out Any>>, List<() -> Any>>>) =
        asExpr { this.possibleConstructors.putAll(array) }

    fun getInstance(`class`: Class<out Any>) = registry[`class`]

    inline fun <reified T : Any> getInstance(`class`: Class<out Any>) = getInstance(`class`).supplyNullable { it as T }

    fun hasInstance(`class`: Class<out Any>) = `class` in registry

    fun registerInstance(instance: Any) = this.registerInstance(instance::class.java, instance)

    fun registerInstance(`class`: Class<out Any>, instance: Any) = asExpr { registry[`class`] = instance }

    fun registerInstanceOrKeep(instance: Any) = this.registerInstanceOrKeep(instance::class.java, instance)

    fun registerInstanceOrKeep(`class`: Class<out Any>, instance: Any) =
        asExpr { registry.putIfAbsent(`class`, instance) }

    fun createInstance(`class`: Class<out Any>) {
        for ((types, instances) in this.possibleConstructors) {
            try {
                val constructor = `class`.getDeclaredConstructor(*types.toTypedArray())
                val instance = constructor.newInstance(*instances.map { it() }.toTypedArray())
                this.registerInstance(`class`, instance)
                return
            } catch (e: InvocationTargetException) {
                throw e.cause ?: e
            } catch (_: Exception) {
            }
        }

        throw IllegalStateException("Couldn't register $`class` because the class doesn't have the correct constructor.")
    }

    fun createInstanceOrKeep(`class`: Class<out Any>) {
        if (this.hasInstance(`class`)) return
        this.createInstance(`class`)
    }

}

open class Registry {

    constructor(vararg possible: Pair<List<Class<out Any>>, List<() -> Any>>) {
        BackingRegistry.addConstructors(possible)
    }

    constructor(`class`: Class<out Any>, get: () -> Any) : this(listOf(`class`) to listOf(get))

    protected inline fun <reified T : Any> getInstance(`class`: Class<out T>) = BackingRegistry.getInstance<T>(`class`)

    protected fun hasInstance(`class`: Class<out Any>) = BackingRegistry.hasInstance(`class`)

    protected fun registerInstance(instance: Any) = BackingRegistry.registerInstance(instance)

    protected fun registerInstanceOrKeep(instance: Any) = BackingRegistry.registerInstanceOrKeep(instance)

    protected fun registerInstance(`class`: Class<out Any>, instance: Any) =
        BackingRegistry.registerInstance(`class`, instance)

    protected fun registerInstanceOrKeep(`class`: Class<out Any>, instance: Any) =
        BackingRegistry.registerInstanceOrKeep(`class`, instance)

    protected fun createInstance(`class`: Class<out Any>) = BackingRegistry.createInstance(`class`)

    protected fun createInstanceOrKeep(`class`: Class<out Any>) = BackingRegistry.createInstanceOrKeep(`class`)

}