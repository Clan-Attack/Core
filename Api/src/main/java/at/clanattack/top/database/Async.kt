package at.clanattack.top.database

import at.clanattack.database.ISurrealServiceProvider
import at.clanattack.database.change
import at.clanattack.database.query
import at.clanattack.database.select
import at.clanattack.top.bootstrap.getServiceProvider
import com.surrealdb.driver.model.patch.Patch
import kotlin.reflect.KClass

private val provider: ISurrealServiceProvider
    get() = getServiceProvider()

fun letDatabase(key: String, value: String) = provider.let(key, value)

fun <T : Any> queryDatabase(
    query: String,
    type: Class<T>,
    args: Map<String, String> = emptyMap()
) = provider.query(query, type, args)

fun <T : Any> queryDatabase(query: String, type: KClass<T>, args: Map<String, String> = emptyMap()) =
    provider.query(query, type, args)

fun <T : Any> selectDatabase(thing: String, type: Class<T>) = provider.select(thing, type)

fun <T : Any> selectDatabase(thing: String, type: KClass<T>) = provider.select(thing, type)

fun <T : Any> createDatabase(thing: String, data: T) = provider.create(thing, data)

fun <T : Any> updateDatabase(thing: String, data: T) = provider.update(thing, data)

fun <T : Any, P : Any> changeDatabase(thing: String, data: T, type: Class<P>) = provider.change(thing, data, type)

fun <T : Any, P : Any> changeDatabase(thing: String, data: T, type: KClass<P>) = provider.change(thing, data, type)

fun patchDatabase(thing: String, patch: List<Patch>) = provider.patch(thing, patch)

fun deleteDatabase(thing: String) = provider.delete(thing)

inline fun <reified T : Any> ISurrealServiceProvider.queryDatabase(
    query: String,
    args: Map<String, String> = emptyMap()
) = getServiceProvider<ISurrealServiceProvider>().query<T>(query, args)

inline fun <reified T : Any> ISurrealServiceProvider.selectDatabase(thing: String) =
    getServiceProvider<ISurrealServiceProvider>().select<T>(thing)

inline fun <T : Any, reified P : Any> ISurrealServiceProvider.changeDatabase(thing: String, data: T) =
    getServiceProvider<ISurrealServiceProvider>().change<T, P>(thing, data)