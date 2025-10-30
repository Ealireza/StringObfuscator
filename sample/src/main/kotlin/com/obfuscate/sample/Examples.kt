package com.obfuscate.sample

import com.obfuscate.annotation.StringObfuscate

// ✅ Works on OBJECT - See ApiConfig.kt for example

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

// �o. Works on array/list/set of strings
@StringObfuscate
class NetworkConfig {
    val proxyHosts = arrayOf(
        "socksProxyHost",
        "http.proxyHost",
        "https.proxyHost"
    )

    val proxyPorts: List<String> = listOf("1080", "3128", "8888")

    val trustedDomains: Set<String> = setOf("localhost", "127.0.0.1", "*.internal")
}
