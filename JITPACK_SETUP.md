# JitPack Setup Guide

This document explains how **String Obfuscator** is configured for JitPack publishing.

## 📦 What is JitPack?

[JitPack](https://jitpack.io) is a package repository for Git that builds Maven/Gradle artifacts directly from your GitHub repository. No need to manually publish artifacts!

## ✅ Configuration

### 1. `jitpack.yml`

Specifies the JDK version for building:

```yaml
jdk:
  - openjdk17
```

### 2. Maven Publishing (`build.gradle.kts`)

Each module is configured with `maven-publish` plugin:

**Root `build.gradle.kts`:**
```kotlin
plugins {
    id("maven-publish")
}

group = "com.github.Ealireza"
version = "1.0.0"
```

**Module `build.gradle.kts` (annotation, processor):**
```kotlin
plugins {
    kotlin("jvm")
    id("maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = "com.github.Ealireza"
            artifactId = "stringobfuscator-<module>"
            version = "1.0.0"
        }
    }
}
```

**Runtime module (Android Library):**
```kotlin
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
```

## 🚀 Publishing to JitPack

### Automatic Publishing

JitPack automatically builds your library when:

1. You create a **GitHub Release** or **Git Tag**
2. Someone requests the dependency for the first time

### Create a Release

```bash
# Tag your release
git tag -a v1.0.0 -m "Release v1.0.0"
git push origin v1.0.0

# Or create a GitHub Release
# Go to: https://github.com/Ealireza/StringObfuscator/releases/new
```

### Check Build Status

Visit: https://jitpack.io/#Ealireza/StringObfuscator

You'll see:
- ✅ Build status
- 📦 Available versions
- 📝 Build logs

## 📥 Using the Library

After publishing to JitPack, users add it like this:

### Step 1: Add JitPack Repository

**`settings.gradle.kts`:**
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### Step 2: Add Dependencies

**`build.gradle.kts`:**
```kotlin
dependencies {
    implementation("com.github.Ealireza.StringObfuscator:annotation:1.0.0")
    implementation("com.github.Ealireza.StringObfuscator:runtime:1.0.0")
    ksp("com.github.Ealireza.StringObfuscator:processor:1.0.0")
}
```

## 🔄 Version Management

JitPack supports multiple version formats:

```kotlin
// Specific release version
implementation("com.github.Ealireza.StringObfuscator:annotation:1.0.0")

// Git commit hash (for testing)
implementation("com.github.Ealireza.StringObfuscator:annotation:abc1234")

// Branch snapshot (not recommended for production)
implementation("com.github.Ealireza.StringObfuscator:annotation:main-SNAPSHOT")

// Latest release
implementation("com.github.Ealireza.StringObfuscator:annotation:latest.release")
```

## 🛠️ Testing Locally Before Publishing

Before pushing to GitHub, test your build:

```bash
# Build all modules
./gradlew build

# Generate Maven artifacts locally
./gradlew publishToMavenLocal

# Check artifacts in ~/.m2/repository/com/github/Ealireza/
```

## 📊 JitPack Build Process

When someone requests your library:

1. **JitPack clones** your GitHub repository
2. **Runs** `./gradlew build publishToMavenLocal`
3. **Caches** the built artifacts
4. **Serves** them to users

## 🐛 Troubleshooting

### Build Failed on JitPack?

Check the build log at: https://jitpack.io/#Ealireza/StringObfuscator

Common issues:

1. **Missing `jitpack.yml`** - Add JDK configuration
2. **Wrong Gradle version** - Ensure Gradle wrapper is committed
3. **Missing dependencies** - Check all dependencies are available
4. **Build errors** - Fix errors locally first

### Force Rebuild

Visit: https://jitpack.io/#Ealireza/StringObfuscator

Click **"Look up"** or **"Delete and Rebuild"**

## 📝 Checklist for Publishing

Before creating a release:

- [ ] Update version in all `build.gradle.kts` files
- [ ] Update `CHANGELOG.md`
- [ ] Test build locally: `./gradlew build`
- [ ] Commit all changes
- [ ] Create Git tag: `git tag -a v1.0.0 -m "Release v1.0.0"`
- [ ] Push tag: `git push origin v1.0.0`
- [ ] Create GitHub Release
- [ ] Verify build on JitPack
- [ ] Test dependency in a sample project
- [ ] Update README with new version number

## 🎉 Benefits of JitPack

✅ **No account needed** - Just use GitHub releases
✅ **Automatic builds** - Push tag, get artifact
✅ **Multi-module support** - All modules published together
✅ **Free for open source** - No cost
✅ **Simple setup** - Minimal configuration
✅ **Version control** - Tied to Git tags/releases

## 🔗 Links

- **JitPack Documentation**: https://jitpack.io/docs/
- **Build Status**: https://jitpack.io/#Ealireza/StringObfuscator
- **GitHub Releases**: https://github.com/Ealireza/StringObfuscator/releases

---

**Ready to publish?** Just create a GitHub release and JitPack does the rest! 🚀
