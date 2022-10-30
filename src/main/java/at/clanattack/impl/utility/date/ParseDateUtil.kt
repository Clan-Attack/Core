package at.clanattack.impl.utility.date

import at.clanattack.utility.date.IParseDateUtil
import at.clanattack.xjkl.extention.supplyNullable
import java.lang.IllegalArgumentException
import java.util.*


class ParseDateUtil : IParseDateUtil {

    private val timePattern = Regex(
        "(?:([0-9]+)\\s*y[a-z]*[,\\s]*)?" +
                "(?:([0-9]+)\\s*mo[a-z]*[,\\s]*)?" +
                "(?:([0-9]+)\\s*w[a-z]*[,\\s]*)?" +
                "(?:([0-9]+)\\s*d[a-z]*[,\\s]*)?" +
                "(?:([0-9]+)\\s*h[a-z]*[,\\s]*)?" +
                "(?:([0-9]+)\\s*m[a-z]*[,\\s]*)?" +
                "(?:([0-9]+)\\s*(?:s[a-z]*)?)?",
        RegexOption.IGNORE_CASE
    )

    override fun parseTime(time: String) = this.parseTime(time, -1)

    override fun parseTime(time: String, max: Long): Long {
        timePattern.findAll(time).forEach { result ->
            if (result.groups.isEmpty()) return@forEach
            if (result.groups.all { it == null || it.value.isEmpty() }) return@forEach

            val calendar = GregorianCalendar()
            calendar.timeInMillis = 0

            result.groups[1]?.value?.toInt().supplyNullable { calendar.add(Calendar.YEAR, it) }
            result.groups[2]?.value?.toInt().supplyNullable { calendar.add(Calendar.MONTH, it) }
            result.groups[3]?.value?.toInt().supplyNullable { calendar.add(Calendar.WEEK_OF_YEAR, it) }
            result.groups[4]?.value?.toInt().supplyNullable { calendar.add(Calendar.DAY_OF_MONTH, it) }
            result.groups[5]?.value?.toInt().supplyNullable { calendar.add(Calendar.HOUR_OF_DAY, it) }
            result.groups[6]?.value?.toInt().supplyNullable { calendar.add(Calendar.MINUTE, it) }
            result.groups[7]?.value?.toInt().supplyNullable { calendar.add(Calendar.SECOND, it) }

            return if (max != -1L && calendar.after(max)) max else calendar.timeInMillis
        }

        throw IllegalArgumentException("Illegal date format")
    }

    override fun parseTimeDiff(time: String) = this.parseTimeDiff(time, -1, true)

    override fun parseTimeDiff(time: String, max: Long) = this.parseTimeDiff(time, max, true)

    override fun parseTimeDiff(time: String, future: Boolean) = this.parseTimeDiff(time, -1, future)

    override fun parseTimeDiff(time: String, max: Long, future: Boolean) =
        System.currentTimeMillis() + (this.parseTime(time, max) * if (future) 1 else -1)

}