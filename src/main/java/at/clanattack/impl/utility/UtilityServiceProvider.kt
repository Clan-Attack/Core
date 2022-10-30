package at.clanattack.impl.utility

import at.clanattack.bootstrap.ICore
import at.clanattack.bootstrap.provider.AbstractServiceProvider
import at.clanattack.bootstrap.provider.ServiceProvider
import at.clanattack.utility.IUtilityServiceProvider
import at.clanattack.impl.utility.command.CommandHandler
import at.clanattack.impl.utility.date.FormatDateUtil
import at.clanattack.impl.utility.date.ParseDateUtil
import at.clanattack.impl.utility.fetching.UUIDFetcher
import at.clanattack.impl.utility.inventory.InventoryHandler
import at.clanattack.impl.utility.item.ItemHandler
import at.clanattack.impl.utility.listener.ListenerHandler
import at.clanattack.impl.utility.scope.ScopeHandler

@ServiceProvider(IUtilityServiceProvider::class)
class UtilityServiceProvider(core: ICore) : AbstractServiceProvider(core), IUtilityServiceProvider {

    override val listenerHandler = ListenerHandler(this.core)
    override val commandHandler = CommandHandler(this.core)
    override val scopeHandler = ScopeHandler(this.core)
    override val itemHandler = ItemHandler(this.core)
    override val inventoryHandler = InventoryHandler(this.core)
    override val uuidFetcher = UUIDFetcher()
    override val parseDateUtil = ParseDateUtil()
    override val formatDateUtil = FormatDateUtil(this.core)

    override fun load() {
        this.listenerHandler.registerListenerInstance(this.commandHandler)
    }

    override fun enable() {
        this.listenerHandler.registerBlock()

        this.listenerHandler.loadListeners()
        this.listenerHandler.registerEvents()
        this.commandHandler.registerCommands()
    }

}