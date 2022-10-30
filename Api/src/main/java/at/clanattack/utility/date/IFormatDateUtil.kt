package at.clanattack.utility.date

interface IFormatDateUtil {

    fun formatTime(time: Long, format: String): String
    fun formatTime(time: Long): String
    fun formatTimeDiff(time: Long, format: String): String
    fun formatTimeDiff(time: Long): String
    fun formatTimeDiff(time: Long, accuracy: Int): String

    fun formatTimeDiff(start: Long, end: Long, format: String): String
    fun formatTimeDiff(start: Long, end: Long): String
    fun formatTimeDiff(start: Long, end: Long, accuracy: Int): String

}