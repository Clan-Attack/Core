plugins {
    id("java")
}

group = "at.clanattack"
version = "0.5"

dependencies {
    api(project(":Api"))
    api(project(":Library"))
}
