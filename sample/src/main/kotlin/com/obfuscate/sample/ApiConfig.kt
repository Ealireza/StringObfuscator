package com.obfuscate.sample

import com.obfuscate.annotation.StringObfuscate

/**
 * Example: API configuration with sensitive strings that should be obfuscated.
 */
@StringObfuscate
object ApiConfig {
    const val API_KEY = "sk_live_1234567890abcdefghijklmnop"
    const val API_SECRET = "secret_key_abc123_super_sensitive"
    const val BASE_URL = "https://api.example.com/v2"
    const val ENCRYPTION_KEY = "MyVerySecretEncryptionKey2024!"
}
