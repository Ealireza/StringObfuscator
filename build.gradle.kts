plugins {
    kotlin("jvm") version "1.9.22" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    id("com.android.application") version "8.2.2" apply false
    id("com.android.library") version "8.2.2" apply false
    id("maven-publish")
}

group = "com.github.Ealireza"
version = "1.0.0"

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
