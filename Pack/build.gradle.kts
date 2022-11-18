plugins {
    id("java")
}

group = "at.clanattack"
version = "0.5.1"

dependencies {
    api(project(":Api"))
    api(project(":Library"))
}

tasks {
    jar {
        archiveBaseName.set("Core")
    }
}
