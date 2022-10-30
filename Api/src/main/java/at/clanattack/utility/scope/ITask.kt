package at.clanattack.utility.scope

interface ITask {

    val id: Int

    val sync: Boolean

    val cancelled: Boolean

    fun cancel()

}