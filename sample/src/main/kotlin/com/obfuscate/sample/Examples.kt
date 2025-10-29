package com.obfuscate.sample

import com.obfuscate.annotation.StringObfuscate

// ✅ Works on OBJECT
@StringObfuscate
object ApiConfig {
    const val API_KEY = "example_api_key_123"
    const val BASE_URL = "https://api.example.com"
}

// ✅ Works on CLASS
@StringObfuscate
class UserCredentials {
    val username = "admin@example.com"
    val password = "example_password_123"
    val token = "example_token_abc123"
}

// ✅ Works on DATA CLASS
@StringObfuscate
data class DatabaseConfig(
    val host: String = "db.example.com",
    val username: String = "db_user",
    val password: String = "example_db_pass",
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
