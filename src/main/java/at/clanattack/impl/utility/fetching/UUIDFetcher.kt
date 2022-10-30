package at.clanattack.impl.utility.fetching

import com.google.common.cache.CacheBuilder
import com.google.gson.GsonBuilder
import com.mojang.util.UUIDTypeAdapter
import at.clanattack.utility.fetching.IUUIDFetcher
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit


class UUIDFetcher : IUUIDFetcher {

    private val gson = GsonBuilder().registerTypeAdapter(UUID::class.java, UUIDTypeAdapter()).create()
    private val uuidCache = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.DAYS).build<String, UUID>()
    private val nameCache = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.DAYS).build<UUID, String>()

    private val uuidUrl = "https://api.mojang.com/users/profiles/minecraft/%s?at=%d"
    private val nameUrl = "https://api.mojang.com/user/profiles/%s/names"

    override fun getUUID(name: String) = this.getUUIDAt(name, System.currentTimeMillis())

    private fun getUUIDAt(name: String, timestamp: Long): UUID? {
        val use = name.lowercase()
        val uuid = uuidCache.getIfPresent(use)
        if (uuid != null) return uuid

        val connection = URL(String.format(uuidUrl, name, timestamp / 1000)).openConnection() as HttpURLConnection
        connection.readTimeout = 5000

        if (connection.responseCode != HttpURLConnection.HTTP_OK) return null
        val data = gson.fromJson(BufferedReader(InputStreamReader(connection.inputStream)), UUIDData::class.java)
        uuidCache.put(use, data.id)
        nameCache.put(data.id, data.name)

        return data.id
    }

    override fun getName(uuid: UUID): String? {
        val name = nameCache.getIfPresent(uuid)
        if (name != null) return name

        val connection = URL(String.format(nameUrl, uuid)).openConnection() as HttpURLConnection
        connection.readTimeout = 5000

        if (connection.responseCode != HttpURLConnection.HTTP_OK) return null
        val data =
            gson.fromJson(BufferedReader(InputStreamReader(connection.inputStream)), Array<UUIDData>::class.java).last()

        uuidCache.put(data.name.lowercase(), data.id)
        nameCache.put(data.id, data.name)
        return data.name
    }

    override fun existsName(name: String) = this.getUUID(name) != null

    override fun existsUUID(uuid: UUID) = this.getName(uuid) != null

}