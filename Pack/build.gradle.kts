plugins {
    id("java")
}

group = "at.clanattack"
version = "0.6.1"

dependencies {
    api(project(":Api"))
    api(project(":Library"))
}
