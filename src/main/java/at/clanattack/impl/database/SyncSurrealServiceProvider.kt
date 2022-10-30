package at.clanattack.impl.database

import com.surrealdb.driver.model.QueryResult
import com.surrealdb.driver.model.patch.Patch
import at.clanattack.bootstrap.ICore
import at.clanattack.bootstrap.provider.AbstractServiceProvider
import at.clanattack.bootstrap.provider.ServiceProvider
import at.clanattack.database.ISurrealServiceProvider
import at.clanattack.database.ISyncSurrealServiceProvider

@ServiceProvider(ISyncSurrealServiceProvider::class, [ISurrealServiceProvider::class])
class SyncSurrealServiceProvider(core: ICore) : AbstractServiceProvider(core), ISyncSurrealServiceProvider {

    private var internalAsyncProvider: ISurrealServiceProvider? = null
    private val asyncProvider
        get() = internalAsyncProvider ?: throw IllegalStateException("Surreal not initialized yet.")

    override fun load() {
        this.internalAsyncProvider = this.core.getServiceProvider(ISurrealServiceProvider::class)
    }

    override val connected: Boolean
        get() = this.asyncProvider.connected

    override fun let(key: String, value: String) = this.asyncProvider.let(key, value).getSync()

    override fun <T : Any> query(query: String, type: Class<T>, args: Map<String, String>): List<QueryResult<T>> =
        this.asyncProvider.query(query, type, args).getSync()

    override fun <T : Any> select(thing: String, type: Class<T>): List<T> =
        this.asyncProvider.select(thing, type).getSync()

    override fun <T : Any> create(thing: String, data: T) = this.asyncProvider.create(thing, data).getSync()

    override fun <T : Any> update(thing: String, data: T) = this.asyncProvider.update(thing, data).getSync()

    override fun <T : Any, P : Any> change(thing: String, data: T, type: Class<P>): List<P> =
        this.asyncProvider.change(thing, data, type).getSync()

    override fun patch(thing: String, patch: List<Patch>) = this.asyncProvider.patch(thing, patch).getSync()

    override fun delete(thing: String) = this.asyncProvider.delete(thing).getSync()
}