# Usage Examples

## Basic Usage

### 1. API Keys Configuration

```kotlin
import com.obfuscate.annotation.StringObfuscate

@StringObfuscate
object ApiKeys {
    const val STRIPE_KEY = "pk_live_51234567890abcdef"
    const val FIREBASE_KEY = "AIzaSyD1234567890abcdefghij"
    const val AWS_SECRET = "aws_secret_key_abc123xyz"
}
```

**After build, use:**

```kotlin
val stripeKey = ApiKeysObfuscated.getStripeKey()
val firebaseKey = ApiKeysObfuscated.getFirebaseKey()
val awsSecret = ApiKeysObfuscated.getAwsSecret()
```

---

## Advanced Examples

### 2. Database Configuration

```kotlin
@StringObfuscate
data class DatabaseConfig(
    val host: String = "prod-db.example.com",
    val port: String = "5432",
    val username: String = "admin_user",
    val password: String = "SuperSecretPassword123!",
    val database: String = "production"
)
```

**Usage:**

```kotlin
val host = DatabaseConfigObfuscated.getHost()
val password = DatabaseConfigObfuscated.getPassword()

val connection = DriverManager.getConnection(
    "jdbc:postgresql://$host:${DatabaseConfigObfuscated.getPort()}/${DatabaseConfigObfuscated.getDatabase()}",
    DatabaseConfigObfuscated.getUsername(),
    password
)
```

---

### 3. OAuth Credentials

```kotlin
@StringObfuscate
class OAuthConfig {
    val clientId = "oauth_client_id_123456"
    val clientSecret = "oauth_secret_abcdefghijk"
    val redirectUri = "https://app.example.com/oauth/callback"
    val scope = "read write delete"
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

---

### 4. Multiple Environments

```kotlin
sealed class Environment {
    @StringObfuscate
    object Production {
        const val API_URL = "https://api.production.com"
        const val API_KEY = "prod_key_123"
    }

    @StringObfuscate
    object Staging {
        const val API_URL = "https://api.staging.com"
        const val API_KEY = "staging_key_456"
    }

    @StringObfuscate
    object Development {
        const val API_URL = "https://api.dev.com"
        const val API_KEY = "dev_key_789"
    }
}
```

**Usage:**

```kotlin
val config = when (BuildConfig.BUILD_TYPE) {
    "release" -> Environment.ProductionObfuscated
    "staging" -> Environment.StagingObfuscated
    else -> Environment.DevelopmentObfuscated
}

val apiUrl = config.getApiUrl()
val apiKey = config.getApiKey()
```

---

### 5. Encryption Keys

```kotlin
@StringObfuscate
object SecurityConfig {
    const val AES_KEY = "1234567890abcdef1234567890abcdef"
    const val IV_PARAMETER = "abcdef1234567890"
    const val SALT = "random_salt_for_hashing"
    const val JWT_SECRET = "jwt_signing_key_super_secret"
}
```

**Usage:**

```kotlin
val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
val secretKey = SecretKeySpec(
    SecurityConfigObfuscated.getAesKey().toByteArray(),
    "AES"
)
val iv = IvParameterSpec(
    SecurityConfigObfuscated.getIvParameter().toByteArray()
)
cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)
```

---

### 6. Third-Party SDK Keys

```kotlin
@StringObfuscate
object SdkKeys {
    const val GOOGLE_MAPS_KEY = "AIzaSyD_maps_key_123456"
    const val FACEBOOK_APP_ID = "123456789012345"
    const val TWITTER_API_KEY = "twitter_api_key_abc"
    const val ANALYTICS_KEY = "analytics_key_xyz"
}
```

**Usage in AndroidManifest.xml:**

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize SDKs with obfuscated keys
        FacebookSdk.setApplicationId(SdkKeysObfuscated.getFacebookAppId())
        FirebaseApp.initializeApp(this)
        Analytics.initialize(SdkKeysObfuscated.getAnalyticsKey())
    }
}
```

---

### 7. Companion Object Pattern

```kotlin
class PaymentService {
    @StringObfuscate
    companion object {
        const val MERCHANT_ID = "merchant_123456"
        const val MERCHANT_KEY = "merchant_secret_key_abc"
        const val GATEWAY_URL = "https://payment.gateway.com"
    }

    fun processPayment(amount: Double) {
        val merchantId = PaymentService.Companion.Obfuscated.getMerchantId()
        val merchantKey = PaymentService.Companion.Obfuscated.getMerchantKey()
        // Process payment...
    }
}
```

---

## Best Practices

### ✅ DO

- Use for API keys, tokens, passwords
- Combine with ProGuard/R8
- Use const val for compile-time constants
- Keep secrets in separate configuration files

### ❌ DON'T

- Don't rely solely on obfuscation for critical secrets
- Don't use for user data (use proper encryption)
- Don't commit actual production keys to git
- Don't skip server-side validation

---

## Performance Tips

### Caching Deobfuscated Values

```kotlin
object ApiClient {
    private val apiKey: String by lazy {
        ApiKeysObfuscated.getApiKey()
    }

    fun makeRequest() {
        // Use cached apiKey (deobfuscated once)
        httpClient.setHeader("Authorization", "Bearer $apiKey")
    }
}
```

### Minimize Repeated Calls

```kotlin
// ❌ BAD: Deobfuscates every time
fun authenticate() {
    val key1 = ApiKeysObfuscated.getApiKey()
    val key2 = ApiKeysObfuscated.getApiKey()
    val key3 = ApiKeysObfuscated.getApiKey()
}

// ✅ GOOD: Deobfuscate once
fun authenticate() {
    val apiKey = ApiKeysObfuscated.getApiKey()
    // Use apiKey multiple times
}
```

---

## Gradle Configuration

### Release-Only Obfuscation

```kotlin
android {
    buildTypes {
        release {
            // Enable obfuscation only in release builds
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            // Use plain strings in debug for easier debugging
        }
    }
}
```

### Build Variants

```kotlin
android {
    flavorDimensions += "environment"
    productFlavors {
        create("production") {
            dimension = "environment"
            buildConfigField("Boolean", "USE_OBFUSCATION", "true")
        }
        create("staging") {
            dimension = "environment"
            buildConfigField("Boolean", "USE_OBFUSCATION", "false")
        }
    }
}
```

---

## Testing

```kotlin
@Test
fun testObfuscatedStrings() {
    // Get obfuscated value
    val apiKey = ApiKeysObfuscated.getApiKey()

    // Verify it's not empty
    assertNotNull(apiKey)
    assertTrue(apiKey.isNotEmpty())

    // Test functionality with obfuscated value
    val result = apiClient.authenticate(apiKey)
    assertTrue(result.isSuccess)
}
```

---

## Troubleshooting

### Generated Files Not Found?

1. Clean and rebuild:
   ```bash
   ./gradlew clean build
   ```

2. Check KSP generated sources:
   ```
   app/build/generated/ksp/debug/kotlin/
   ```

3. Verify annotation is applied correctly

### ProGuard Issues?

Add to `proguard-rules.pro`:

```proguard
-keep class **.*Obfuscated { *; }
-keepclassmembers class **.*Obfuscated { *; }
```

---

**More examples?** Check the [sample app](../sample/) for complete working code!
