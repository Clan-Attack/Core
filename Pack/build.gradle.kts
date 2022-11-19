plugins {
    id("java")
}

group = "at.clanattack"
version = "0.6"

dependencies {
    api(project(":Api"))
    api(project(":Library"))
}
