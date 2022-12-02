plugins {
    id("java")
}

group = "at.clanattack"
version = "0.7.1"

dependencies {
    // Bootstrap
    api("io.github.classgraph:classgraph:4.8.151")
    api("com.google.code.gson:gson:2.10")

    // Database
    api(files("lib/surreal.jar"))
    api("org.java-websocket:Java-WebSocket:1.5.3")

    // Discord
    api("net.dv8tion:JDA:5.0.0-beta.1")
    api("com.github.minndevelopment:jda-ktx:0.10.0-beta.1")
    api("com.github.minndevelopment:jda-reactor:1.6.0")
}

bukkit {
    main = "at.clanattack.library.Library"
    name = "Clanattack-Library"
    version = "0.7.1"
    apiVersion = "1.19"
    author = "CheeseTastisch"
}
