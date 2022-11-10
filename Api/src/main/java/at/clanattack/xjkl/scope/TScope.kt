package at.clanattack.xjkl.scope

import at.clanattack.xjkl.extention.replaceLast
import at.clanattack.xjkl.json.JsonDocument
import com.google.gson.JsonPrimitive

fun <T> String.toT(clazz: Class<T>) : T{
    val json = JsonPrimitive(this)
    return JsonDocument.gson.fromJson(json, clazz)
}

fun Any.fromT(): String = this.fromT()!!

fun Any?.fromUnsafeT(): String? {
    if (this == null) return null

    return JsonDocument.gson.toJson(this)
        .replaceFirst("\"", "")
        .replaceLast("\"", "")
}

