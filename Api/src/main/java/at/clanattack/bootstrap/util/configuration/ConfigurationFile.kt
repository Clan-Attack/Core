package at.clanattack.bootstrap.util.configuration

import java.io.File

@Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")
class ConfigurationFile(val file: File, providerClass: Class<out IFileConfigurationProvider>) {

    val configurationProvider: IFileConfigurationProvider

    init {
        configurationProvider = providerClass.constructors[0].newInstance(file) as IFileConfigurationProvider
    }

}