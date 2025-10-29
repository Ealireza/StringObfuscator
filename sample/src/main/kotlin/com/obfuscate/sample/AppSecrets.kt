package com.obfuscate.sample

import com.obfuscate.annotation.StringObfuscate

/**
 * Example: Application secrets that need obfuscation.
 */
@StringObfuscate
object AppSecrets {
    const val DATABASE_PASSWORD = "example_db_password_replace_me"
    const val JWT_SECRET = "example_jwt_secret_replace_me"
    const val FIREBASE_KEY = "example_firebase_key_replace_me"
}
