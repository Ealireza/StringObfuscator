plugins {
    kotlin("jvm")
    id("maven-publish")
}

group = "com.github.Ealireza"
version = "1.0.0"

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":annotation"))
    implementation("com.google.devtools.ksp:symbol-processing-api:2.2.21-2.0.4")
    implementation("com.squareup:kotlinpoet:1.18.1")
    implementation("com.squareup:kotlinpoet-ksp:1.18.1")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = "com.github.Ealireza"
            artifactId = "stringobfuscator-processor"
            version = "1.0.0"
        }
    }
}
