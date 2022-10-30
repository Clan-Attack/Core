package at.clanattack.impl.utility.date

import at.clanattack.bootstrap.ICore
import at.clanattack.message.IMessageServiceProvider
import at.clanattack.utility.date.IFormatDateUtil
import at.clanattack.xjkl.extention.supply
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class FormatDateUtil(private val core: ICore) : IFormatDateUtil {

    private val shortNames = arrayOf("y", "mo", "d", "h", "m", "s")
    private val types = intArrayOf(
        Calendar.YEAR,
        Calendar.MONTH,
        Calendar.DAY_OF_MONTH,
        Calendar.HOUR_OF_DAY,
        Calendar.MINUTE,
        Calendar.SECOND
    )
    private val names = arrayOf(
        "core.utility.time.year", "core.utility.time.years",
        "core.utility.time.month", "core.utility.time.months",
        "core.utility.time.day", "core.utility.time.days",
        "core.utility.time.hour", "core.utility.time.hours",
        "core.utility.time.minute", "core.utility.time.minutes",
        "core.utility.time.second", "core.utility.time.seconds",
    )


    override fun formatTime(time: Long, format: String) = SimpleDateFormat(format).format(time)!!

    override fun formatTime(time: Long) = this.formatTime(
        time,
        this.core.getServiceProvider(IMessageServiceProvider::class)
            .getStringMessage("core.utility.date.format.default")
    )

    override fun formatTimeDiff(time: Long, format: String) =
        this.formatTimeDiff(System.currentTimeMillis(), time, format)

    override fun formatTimeDiff(time: Long) = this.formatTimeDiff(System.currentTimeMillis(), time, 2)

    override fun formatTimeDiff(time: Long, accuracy: Int) =
        this.formatTimeDiff(System.currentTimeMillis(), time, accuracy)

    override fun formatTimeDiff(start: Long, end: Long, format: String): String {
        var formatted = format

        this.getDateDiff(start, end, false).supply {
            if (it.isEmpty()) return@supply
            it.forEachIndexed { i, time -> formatted = formatted.replace("%${shortNames[i]}", time.toString()) }
        }

        this.getDateDiff(start, end, true).supply {
            if (it.isEmpty()) return@supply
            it.forEachIndexed { i, time -> formatted = formatted.replace("%f${shortNames[i]}", time.toString()) }
        }

        return formatted
    }

    override fun formatTimeDiff(start: Long, end: Long) = this.formatTimeDiff(start, end, 2)

    override fun formatTimeDiff(start: Long, end: Long, accuracy: Int): String {
        val differences = this.getDateDiff(start, end, false)
        if (differences.isEmpty()) return this.core.getServiceProvider(IMessageServiceProvider::class)
            .getStringMessage("core.utility.time.now")

        val builder = StringBuilder()

        var currentAccuracy = 0
        differences.forEachIndexed { i, it ->
            if (currentAccuracy > accuracy) return@forEachIndexed

            if (it > 0) {
                currentAccuracy++
                builder.append(" ").append(it).append(" ").append(this.getName(i, it))
            }
        }

        if (builder.isEmpty()) return this.core.getServiceProvider(IMessageServiceProvider::class)
            .getStringMessage("core.utility.time.now")
        return builder.toString().trim()
    }

    private fun getName(i: Int, it: Long): String {
        return if (it == 1L) this.core.getServiceProvider(IMessageServiceProvider::class)
            .getStringMessage(names[i * 2 - 1]) else this.core.getServiceProvider(IMessageServiceProvider::class)
            .getStringMessage(names[i * 2])
    }

    private fun getDateDiff(start: Long, end: Long, full: Boolean): Array<Long> {
        val startCalendar = this.getCalendarWithMillis(start)
        val endCalendar = this.getCalendarWithMillis(end)

        if (startCalendar == endCalendar) return emptyArray()
        val future = endCalendar.after(startCalendar)

        // Temporary 50ms time buffer added to avoid display truncation due to code execution delays
        endCalendar.add(Calendar.MILLISECOND, if (future) 50 else -50)

        val value = types.map { this.dateDiff(it, startCalendar, endCalendar, future, !full) }.toTypedArray()

        // Preserve correctness in the original date object by removing the extra buffer time
        endCalendar.add(Calendar.MILLISECOND, if (future) -50 else 50)

        return value
    }

    private fun dateDiff(type: Int, start: Calendar, end: Calendar, modifyCalendar: Boolean, future: Boolean): Long {
        var diff = 0L
        var savedDate: Long = start.timeInMillis

        while ((future && !start.after(end)) || (!future && !start.before(end))) {
            if (modifyCalendar) savedDate = start.timeInMillis

            start.add(type, if (future) 1 else -1)
            diff++
        }

        diff--
        start.timeInMillis = savedDate

        return diff
    }

    private fun getCalendarWithMillis(millis: Long): Calendar {
        val calendar = GregorianCalendar()
        calendar.timeInMillis = millis
        return calendar
    }

}