package com.obfuscate.sample

import com.obfuscate.annotation.StringObfuscate

/**
 * Example: API configuration with sensitive strings that should be obfuscated.
 */
@StringObfuscate
object ApiConfig {
    const val API_KEY = "example_api_key_replace_with_yours"
    const val API_SECRET = "example_secret_replace_with_yours"
    const val BASE_URL = "https://api.example.com/v2"
    const val ENCRYPTION_KEY = "example_encryption_key_32chars!"

    // Method call examples - currently not automatically obfuscated
    // But you can wrap them manually:

    fun getSystemProxyHost(): String? = System.getProperty("http.proxyHost")
    fun getUserHome(): String? = System.getProperty("user.home")
    fun getJavaVersion(): String = System.getProperty("java.version") ?: "unknown"

    // Or use obfuscated versions (requires SystemProperties class):
    // fun getObfuscatedProxyHost(): String? {
    //     return System.getProperty(SystemPropertiesObfuscated.getProxyHostKey())
    // }
}

// Separate obfuscated class for method call parameters
// Note: These strings are filtered out by default (system properties)
// Uncomment if you want to test with custom filtering
/*
@StringObfuscate
object SystemProperties {
    const val PROXY_HOST_KEY = "http.proxyHost"
    const val USER_HOME_KEY = "user.home"
    const val JAVA_VERSION_KEY = "java.version"
}
*/
