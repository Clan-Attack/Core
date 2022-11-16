import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.7.21"
    id("io.papermc.paperweight.userdev") version "1.3.8"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "at.clanattack"
version = "0.4"

bukkit {
    main = "at.clanattack.impl.bootstrap.boot.Bootstrap"
    name = "Clanattack-Core"
    version = "0.4"
    apiVersion = "1.19"
    author = "CheeseTastisch"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "java-library")
    apply(plugin = "io.papermc.paperweight.userdev")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        mavenCentral()
        maven("https://jitpack.io/")
        maven("https://maven.clan-attack.at/releases")
    }

    dependencies {
        paperDevBundle("1.19.2-R0.1-SNAPSHOT")
        compileOnly("org.jetbrains:annotations:23.0.0")
    }

    tasks {
        assemble {
            dependsOn(reobfJar)
        }

        compileJava {
            options.encoding = Charsets.UTF_8.name()
            options.release.set(17)
        }

        javadoc {
            options.encoding = Charsets.UTF_8.name()
        }

        processResources {
            filteringCharset = Charsets.UTF_8.name()
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

dependencies {
    // Api
    api(project(":Api"))

    // Database
    implementation("org.java-websocket:Java-WebSocket:1.5.3")
}
