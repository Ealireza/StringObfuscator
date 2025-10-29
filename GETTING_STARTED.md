# Getting Started with String Obfuscator

Welcome to **String Obfuscator**! This guide will help you get up and running in 5 minutes.

---

## 📦 Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/Ealireza/StringObfuscator.git
cd StringObfuscator
```

### Step 2: Configure Android SDK

Set your Android SDK path in `local.properties`:

```properties
sdk.dir=/path/to/your/android/sdk
```

For Windows:
```properties
sdk.dir=C:/Users/YourName/AppData/Local/Android/sdk
```

For macOS:
```properties
sdk.dir=/Users/YourName/Library/Android/sdk
```

For Linux:
```properties
sdk.dir=/home/YourName/Android/Sdk
```

### Step 3: Build the Project

```bash
./gradlew build
```

On Windows:
```bash
gradlew.bat build
```

---

## 🚀 Quick Usage

### 1. Add to Your Project

In your app's `build.gradle.kts`:

```kotlin
plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"
}

dependencies {
    implementation(project(":annotation"))
    implementation(project(":runtime"))
    ksp(project(":processor"))
}
```

### 2. Annotate Your Class

Create a config file with your secrets:

```kotlin
package com.example.app

import com.obfuscate.annotation.StringObfuscate

@StringObfuscate
object ApiConfig {
    const val API_KEY = "your_api_key_here"
    const val API_SECRET = "your_secret_here"
    const val BASE_URL = "https://api.example.com"
}
```

### 3. Build Your App

```bash
./gradlew :app:build
```

KSP will automatically generate `ApiConfigObfuscated.kt` in:
```
app/build/generated/ksp/debug/kotlin/com/example/app/ApiConfigObfuscated.kt
```

### 4. Use Obfuscated Strings

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get obfuscated values (automatically deobfuscated)
        val apiKey = ApiConfigObfuscated.getApiKey()
        val apiSecret = ApiConfigObfuscated.getApiSecret()
        val baseUrl = ApiConfigObfuscated.getBaseUrl()

        // Use them in your app
        setupApiClient(apiKey, apiSecret, baseUrl)
    }
}
```

---

## 🎯 Examples

### Example 1: Database Credentials

```kotlin
@StringObfuscate
data class DatabaseConfig(
    val host: String = "db.myapp.com",
    val username: String = "admin",
    val password: String = "super_secret_password"
)
```

**Usage:**
```kotlin
val host = DatabaseConfigObfuscated.getHost()
val username = DatabaseConfigObfuscated.getUsername()
val password = DatabaseConfigObfuscated.getPassword()
```

### Example 2: OAuth Configuration

```kotlin
@StringObfuscate
object OAuthConfig {
    const val CLIENT_ID = "client_id_123456"
    const val CLIENT_SECRET = "client_secret_abcdef"
    const val REDIRECT_URI = "myapp://oauth/callback"
}
```

**Usage:**
```kotlin
val oauth = OAuthClient(
    clientId = OAuthConfigObfuscated.getClientId(),
    clientSecret = OAuthConfigObfuscated.getClientSecret(),
    redirectUri = OAuthConfigObfuscated.getRedirectUri()
)
```

### Example 3: Multiple SDK Keys

```kotlin
@StringObfuscate
object SdkKeys {
    const val GOOGLE_MAPS = "example_firebase_key_replace_me..."
    const val FIREBASE = "firebase_key_123..."
    const val FACEBOOK_APP_ID = "123456789012345"
}
```

**Usage:**
```kotlin
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize SDKs with obfuscated keys
        MapsSDK.initialize(SdkKeysObfuscated.getGoogleMaps())
        FirebaseApp.initializeApp(this)
        FacebookSdk.setApplicationId(SdkKeysObfuscated.getFacebookAppId())
    }
}
```

---

## 🛠️ Running the Sample App

Try the included sample app:

```bash
# Build the sample
./gradlew :sample:assembleDebug

# Install on connected device
./gradlew :sample:installDebug

# Or open in Android Studio and click Run
```

---

## 🔧 Troubleshooting

### Problem: "Cannot find generated class"

**Solution:** Build the project first to generate code:
```bash
./gradlew :app:kspDebugKotlin
```

### Problem: "KSP not found"

**Solution:** Make sure you have KSP plugin in root `build.gradle.kts`:
```kotlin
plugins {
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
}
```

### Problem: Generated files not visible in IDE

**Solution:**
1. Rebuild project: `Build > Rebuild Project`
2. Invalidate caches: `File > Invalidate Caches > Invalidate and Restart`
3. Check generated sources are marked: `build/generated/ksp/debug/kotlin/`

### Problem: ProGuard errors in release build

**Solution:** Add to `proguard-rules.pro`:
```proguard
-keep class **.*Obfuscated { *; }
-keepclassmembers class **.*Obfuscated { *; }
```

---

## 📚 Next Steps

- 📖 Read the [full README](README.md) for detailed documentation
- 💡 Check [usage examples](docs/USAGE_EXAMPLE.md) for more patterns
- 🔒 Review [security considerations](SECURITY.md)
- 🤝 Learn how to [contribute](CONTRIBUTING.md)

---

## 💬 Getting Help

- **Questions?** Open a [GitHub Discussion](https://github.com/Ealireza/StringObfuscator/discussions)
- **Bug?** File an [issue](https://github.com/Ealireza/StringObfuscator/issues)
- **Feature request?** Use our [feature template](https://github.com/Ealireza/StringObfuscator/issues/new?template=feature_request.md)

---

## 🎉 You're Ready!

That's it! You now have compile-time string obfuscation in your Android app.

**Remember:**
- ✅ Obfuscation happens at **build time** (zero runtime cost)
- ✅ Use `*Obfuscated.getXxx()` to access values
- ✅ Combine with ProGuard/R8 for best protection
- ✅ Don't rely solely on obfuscation for ultra-sensitive data

---

**Happy Coding!** 🔒
