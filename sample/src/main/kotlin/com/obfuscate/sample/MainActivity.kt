package com.obfuscate.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Example: Using obfuscated strings
        // After build, use the generated *Obfuscated classes

        Log.d("StringObfuscate", "=== Original (Compile-Time) Values ===")
        Log.d("StringObfuscate", "API_KEY: ${ApiConfig.API_KEY}")
        Log.d("StringObfuscate", "API_SECRET: ${ApiConfig.API_SECRET}")
        Log.d("StringObfuscate", "BASE_URL: ${ApiConfig.BASE_URL}")

        Log.d("StringObfuscate", "\n=== Obfuscated (Runtime) Values ===")
        // After KSP processes, you'll use:
        // ApiConfigObfuscated.getApiKey()
        // ApiConfigObfuscated.getApiSecret()
        // ApiConfigObfuscated.getBaseUrl()
        // ApiConfigObfuscated.getEncryptionKey()

        // AppSecretsObfuscated.getDatabasePassword()
        // AppSecretsObfuscated.getJwtSecret()
        // AppSecretsObfuscated.getFirebaseKey()

        Log.d("StringObfuscate", "Note: Generated code will be in build/generated/ksp/")
    }
}
