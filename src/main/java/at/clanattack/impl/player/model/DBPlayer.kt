package at.clanattack.impl.player.model

data class DBPlayer(val onlineTime: Long, val playerData: Map<String, String?>)

data class PlayerDataUpdate(val playerData: Map<String, String?>) {

    companion object {

        fun create(key: String, value: String?) = PlayerDataUpdate(mapOf(key to value))

    }

}
