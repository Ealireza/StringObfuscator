plugins {
    kotlin("jvm")
    id("maven-publish")
}

group = "com.github.Ealireza"
version = "1.0.0"

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":annotation"))
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.22-1.0.17")
    implementation("com.squareup:kotlinpoet:1.16.0")
    implementation("com.squareup:kotlinpoet-ksp:1.16.0")
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
