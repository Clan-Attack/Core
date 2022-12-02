package at.clanattack.impl.bootstrap.call

import at.clanattack.bootstrap.ICore
import at.clanattack.bootstrap.call.Call
import at.clanattack.bootstrap.call.SystemState
import at.clanattack.impl.bootstrap.registry.Registry
import java.lang.reflect.Method

class CallHandler(private val core: ICore) : Registry() {

    private val calls = mutableMapOf<SystemState, MutableList<Pair<Method, Any>>>()

    fun registerMethods() {
        this.core.annotationScanner.scanMethods(Call::class)
            .forEach {
                if (it.parameterCount != 0 && it.parameterCount != 1 && it.parameterTypes[0] != SystemState::class.java) {
                    this.core.logger.error("Can not register call methods ${it.name} in " +
                            "${it.declaringClass.simpleName} because it has the wrong methods signature.")
                    return@forEach
                }

                createInstanceOrKeep(it.declaringClass)
                val states = it.getAnnotation(Call::class.java).state

                states.forEach { state ->
                    this.calls.putIfAbsent(state, mutableListOf())
                    this.calls[state]?.add(it to getInstance(it.declaringClass)!!)
                }
            }
    }

    fun call(systemState: SystemState) {
        calls[systemState]?.forEach { (method, instance) ->
            if (method.parameterCount == 1) method.invoke(instance, systemState)
            else method.invoke(instance)
        }
    }


}