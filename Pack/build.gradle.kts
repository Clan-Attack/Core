plugins {
    id("java")
}

group = "at.clanattack"
version = "0.7.1"

dependencies {
    api(project(":Api"))
    api(project(":Library"))
}
