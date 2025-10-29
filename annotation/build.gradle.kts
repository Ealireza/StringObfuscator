plugins {
    kotlin("jvm")
    id("maven-publish")
}

group = "com.github.Ealireza"
version = "1.0.0"

dependencies {
    implementation(kotlin("stdlib"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = "com.github.Ealireza"
            artifactId = "stringobfuscator-annotation"
            version = "1.0.0"
        }
    }
}
