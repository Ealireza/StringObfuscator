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
}
