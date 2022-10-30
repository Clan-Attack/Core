package at.clanattack.bootstrap.provider

import at.clanattack.bootstrap.ICore

abstract class AbstractServiceProvider(val core: ICore) {

    var state: ServiceProviderState = ServiceProviderState.UNLOADED

    open fun load() {}

    open fun enable() {}

    open fun disable() {}

}