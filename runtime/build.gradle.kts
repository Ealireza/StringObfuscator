plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

group = "com.github.Ealireza"
version = "1.0.0"

android {
    namespace = "com.obfuscate.runtime"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(kotlin("stdlib"))
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.Ealireza"
                artifactId = "stringobfuscator-runtime"
                version = "1.0.0"
            }
        }
    }
}
