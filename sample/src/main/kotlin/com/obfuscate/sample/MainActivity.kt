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
        val apiKey = ApiConfigObfuscated.getApiKey()
        val apiSecret = ApiConfigObfuscated.getApiSecret()
        val baseUrl = ApiConfigObfuscated.getBaseUrl()

        Log.d("StringObfuscate", "Obfuscated API Key: $apiKey")
        Log.d("StringObfuscate", "Obfuscated API Secret: $apiSecret")
        Log.d("StringObfuscate", "Obfuscated Base URL: $baseUrl")

        // Method call examples (currently not auto-obfuscated)
        Log.d("StringObfuscate", "\n=== Method Call Examples ===")
        val proxyHost = ApiConfig.getSystemProxyHost()
        val userHome = ApiConfig.getUserHome()
        Log.d("StringObfuscate", "Proxy Host: $proxyHost")
        Log.d("StringObfuscate", "User Home: $userHome")

        // Safe version using obfuscated parameters
        val safeProxyHost = ApiConfig.getObfuscatedProxyHost()
        Log.d("StringObfuscate", "Safe Proxy Host: $safeProxyHost")

        Log.d("StringObfuscate", "Note: Generated code will be in build/generated/ksp/")
    }
}
