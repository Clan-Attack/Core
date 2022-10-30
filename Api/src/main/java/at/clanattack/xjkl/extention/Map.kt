package at.clanattack.xjkl.extention

fun <K, V, T : MutableMap<K, V>> T.putAndGet(key: K, default: V): V {
    this.putIfAbsent(key, default)
    return this[key]!!
}