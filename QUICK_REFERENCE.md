# String Obfuscator - Quick Reference

## 📦 Installation

```kotlin
// Root build.gradle.kts
plugins {
    id("com.google.devtools.ksp") version "2.2.21-2.0.4" apply false
}

// App build.gradle.kts
plugins {
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":annotation"))
    implementation(project(":runtime"))
    ksp(project(":processor"))
}
```

---

## 🎯 Usage Patterns

### Object
```kotlin
@StringObfuscate
object Config {
    const val KEY = "value"
}
// Usage: ConfigObfuscated.getKey()
```

### Class
```kotlin
@StringObfuscate
class Credentials {
    val username = "admin"
    val password = "pass123"
}
// Usage: CredentialsObfuscated.getUsername()
```

### Data Class
```kotlin
@StringObfuscate
data class DbConfig(
    val host: String = "localhost",
    val port: String = "5432"
)
// Usage: DbConfigObfuscated.getHost()
```

### Companion Object
```kotlin
class Service {
    @StringObfuscate
    companion object {
        const val API_KEY = "key123"
    }
}
// Usage: Service.CompanionObfuscated.getApiKey()
```

---

## 🔧 Build Commands

```bash
# Build project
./gradlew build

# Generate KSP code only
./gradlew kspDebugKotlin

# Build release APK
./gradlew assembleRelease

# Clean build
./gradlew clean build

# Run tests
./gradlew test
```

---

## 📂 Generated Files Location

```
app/build/generated/ksp/
├── debug/kotlin/           # Debug variant
│   └── YourClassObfuscated.kt
└── release/kotlin/         # Release variant
    └── YourClassObfuscated.kt
```

---

## 🛡️ ProGuard Rules

Add to `proguard-rules.pro`:

```proguard
-keep class **.*Obfuscated { *; }
-keepclassmembers class **.*Obfuscated { *; }
-keep class com.obfuscate.runtime.StringDeobfuscator { *; }
```

---

## ⚡ Performance

| Operation | Time | Impact |
|-----------|------|--------|
| Obfuscation | 0ms runtime | Build-time only |
| Deobfuscation | ~100ns | Per string access |
| Memory | Minimal | Base64 bytes |

---

## 🔒 Security Layers

1. **XOR Encryption** - Random key per string
2. **Bit Shuffling** - Additional entropy
3. **Base64 Encoding** - Storage format

---

## 💡 Best Practices

### ✅ DO
- Use for API keys, tokens, passwords
- Combine with ProGuard/R8
- Cache deobfuscated values
- Use remote config for ultra-sensitive data

### ❌ DON'T
- Rely solely on obfuscation
- Use for user data (use proper encryption)
- Commit production secrets to git
- Skip server-side validation

---

## 🐛 Common Issues

### Generated class not found?
```bash
./gradlew kspDebugKotlin
# Then: Build > Rebuild Project in IDE
```

### ProGuard errors?
Add keep rules (see above)

### KSP not processing?
Check annotation import:
```kotlin
import com.obfuscate.annotation.StringObfuscate
```

---

## 📖 Documentation

- [README.md](README.md) - Full documentation
- [GETTING_STARTED.md](GETTING_STARTED.md) - Setup guide
- [USAGE_EXAMPLE.md](docs/USAGE_EXAMPLE.md) - Code examples
- [SECURITY.md](SECURITY.md) - Security considerations
- [CONTRIBUTING.md](CONTRIBUTING.md) - Contribution guide

---

## 🔗 Quick Links

- **GitHub:** https://github.com/Ealireza/StringObfuscator
- **Issues:** https://github.com/Ealireza/StringObfuscator/issues
- **Releases:** https://github.com/Ealireza/StringObfuscator/releases

---

## 📊 Comparison

| Method | Build Time | Runtime | Security | Ease |
|--------|-----------|---------|----------|------|
| Plain Strings | ✅ Fast | ✅ Fast | ❌ None | ✅ Easy |
| Manual Obfuscation | ⚠️ Slow | ⚠️ Medium | ⚠️ Low | ❌ Hard |
| String Obfuscator | ✅ Fast | ✅ Fast | ✅ Medium | ✅ Easy |
| Native Crypto | ⚠️ Medium | ⚠️ Slow | ✅ High | ⚠️ Medium |

---

## 🎓 Example Workflow

```kotlin
// 1. Define
@StringObfuscate
object ApiConfig {
    const val KEY = "secret123"
}

// 2. Build
// $ ./gradlew build

// 3. Use
val key = ApiConfigObfuscated.getKey()
apiClient.authenticate(key)

// 4. Profit! 🎉
```

---

**Print this page for quick reference!** 📄
