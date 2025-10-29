package com.obfuscate.sample

import com.obfuscate.annotation.StringObfuscate

// ✅ Works on OBJECT
@StringObfuscate
object ApiConfig {
    const val API_KEY = "sk_live_1234567890"
    const val BASE_URL = "https://api.example.com"
}

// ✅ Works on CLASS
@StringObfuscate
class UserCredentials {
    val username = "admin@example.com"
    val password = "SuperSecret123!"
    val token = "jwt_token_abc123xyz"
}

// ✅ Works on DATA CLASS
@StringObfuscate
data class DatabaseConfig(
    val host: String = "db.example.com",
    val username: String = "db_user",
    val password: String = "db_password_123",
    val database: String = "production_db"
)

// ✅ Works on COMPANION OBJECT inside class
class MyService {
    @StringObfuscate
    companion object {
        const val API_KEY = "service_key_123"
        const val SECRET = "my_secret"
    }
}

// ✅ Works on nested classes
@StringObfuscate
class AppConfig {
    val appName = "MyApp"
    val version = "1.0.0"

    @StringObfuscate
    class DatabaseSettings {
        val connectionString = "Server=myServerAddress;Database=myDataBase"
        val apiKey = "nested_api_key_456"
    }
}
