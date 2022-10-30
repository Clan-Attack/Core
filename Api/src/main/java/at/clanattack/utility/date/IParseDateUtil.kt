package at.clanattack.utility.date

interface IParseDateUtil {

    fun parseTime(time: String): Long
    fun parseTime(time: String, max: Long): Long

    fun parseTimeDiff(time: String): Long
    fun parseTimeDiff(time: String, max: Long): Long
    fun parseTimeDiff(time: String, future: Boolean): Long
    fun parseTimeDiff(time: String, max: Long, future: Boolean): Long

}