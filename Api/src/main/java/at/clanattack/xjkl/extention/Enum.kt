package at.clanattack.xjkl.extention

inline fun <reified T : Enum<T>> unsafeEnumValueOf(value: String) = try {
        enumValueOf<T>(value)
    } catch (e: IllegalArgumentException) {
        null
    }
