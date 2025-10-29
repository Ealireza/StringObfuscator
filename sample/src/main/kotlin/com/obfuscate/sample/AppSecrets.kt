package com.obfuscate.sample

import com.obfuscate.annotation.StringObfuscate

/**
 * Example: Application secrets that need obfuscation.
 */
@StringObfuscate
object AppSecrets {
    const val DATABASE_PASSWORD = "SuperSecretDBPass123!"
    const val JWT_SECRET = "jwt_signing_key_very_secret_2024"
    const val FIREBASE_KEY = "AIzaSyD1234567890abcdefghijklmnop"
}
