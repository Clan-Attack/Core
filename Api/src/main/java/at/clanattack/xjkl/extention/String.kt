package at.clanattack.xjkl.extention

fun String.replaceLast(oldValue: String, newValue: String) =
    this.reversed().replaceFirst(oldValue.reversed(), newValue.reversed()).reversed()
