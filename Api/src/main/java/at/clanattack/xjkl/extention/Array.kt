package at.clanattack.xjkl.extention

fun Array<out String?>.contains(needle: String, ignoreCase: Boolean = true): Boolean =
    this.any { it.equals(needle, ignoreCase) }

@Suppress("BooleanMethodIsAlwaysInverted")
fun String.inArray(haystack: Array<out String?>, ignoreCase: Boolean = true) = haystack.contains(this, ignoreCase)