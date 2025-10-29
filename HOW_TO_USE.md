# How to Use String Obfuscator

## Quick 3-Step Setup

### 1️⃣ Add Repository

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

### 2️⃣ Add Dependencies

**Root `build.gradle.kts`:**
```kotlin
plugins {
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
}
```

**App `build.gradle.kts`:**
```kotlin
plugins {
    id("com.google.devtools.ksp")
}

dependencies {
    implementation("com.github.Ealireza.StringObfuscator:annotation:1.0.0")
    implementation("com.github.Ealireza.StringObfuscator:runtime:1.0.0")
    ksp("com.github.Ealireza.StringObfuscator:processor:1.0.0")
}
```

### 3️⃣ Use It

```kotlin
import com.obfuscate.annotation.StringObfuscate

// Mark your class
@StringObfuscate
object ApiConfig {
    const val API_KEY = "your_secret_key"
}

// Build project
// ./gradlew build

// Use obfuscated strings
val apiKey = ApiConfigObfuscated.getApiKey()
```

---

## Full Examples

### API Keys
```kotlin
@StringObfuscate
object ApiKeys {
    const val STRIPE = "pk_live_123..."
    const val FIREBASE = "AIzaSyD..."
}

// Usage
val stripe = ApiKeysObfuscated.getStripe()
```

### Database Config
```kotlin
@StringObfuscate
data class DbConfig(
    val host: String = "db.example.com",
    val password: String = "secret123"
)

// Usage
val host = DbConfigObfuscated.getHost()
```

### Companion Object
```kotlin
class PaymentService {
    @StringObfuscate
    companion object {
        const val MERCHANT_ID = "merchant_123"
    }
}

// Usage
val id = PaymentService.CompanionObfuscated.getMerchantId()
```

---

## That's It! 🎉

Your strings are now obfuscated at compile-time with:
- ✅ XOR encryption
- ✅ Bit shuffling
- ✅ Base64 encoding
- ✅ Zero runtime overhead

**See [README.md](README.md) for full documentation.**
