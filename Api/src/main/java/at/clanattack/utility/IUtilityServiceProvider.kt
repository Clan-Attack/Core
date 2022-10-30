package at.clanattack.utility

import at.clanattack.bootstrap.provider.IServiceProvider
import at.clanattack.utility.command.ICommandHandler
import at.clanattack.utility.date.IFormatDateUtil
import at.clanattack.utility.date.IParseDateUtil
import at.clanattack.utility.fetching.IUUIDFetcher
import at.clanattack.utility.inventory.IInventoryHandler
import at.clanattack.utility.item.IItemHandler
import at.clanattack.utility.listener.IListenerHandler
import at.clanattack.utility.scope.IScopeHandler

interface IUtilityServiceProvider: IServiceProvider {

    val listenerHandler: IListenerHandler
    val commandHandler: ICommandHandler
    val scopeHandler: IScopeHandler
    val itemHandler: IItemHandler
    val inventoryHandler: IInventoryHandler
    val uuidFetcher: IUUIDFetcher
    val parseDateUtil: IParseDateUtil
    val formatDateUtil: IFormatDateUtil

}