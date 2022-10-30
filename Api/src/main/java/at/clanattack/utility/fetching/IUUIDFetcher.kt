package at.clanattack.utility.fetching

import java.util.UUID

interface IUUIDFetcher {

    fun getUUID(name: String): UUID?

    fun getName(uuid: UUID): String?

    fun existsName(name: String): Boolean

    fun existsUUID(uuid: UUID): Boolean

}