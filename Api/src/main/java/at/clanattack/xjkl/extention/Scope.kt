package at.clanattack.xjkl.extention

fun <T : Any, R : Any> T?.supplyNullable(supplier: (T) -> R?): R? =
    at.clanattack.xjkl.scope.supplyNullable(this, supplier)

fun <T : Any, R : Any> T.supply(supplier: (T) -> R): R =
    at.clanattack.xjkl.scope.supply(this, supplier)