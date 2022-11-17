package at.clanattack.bootstrap.call

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Call(vararg val state: SystemState)
