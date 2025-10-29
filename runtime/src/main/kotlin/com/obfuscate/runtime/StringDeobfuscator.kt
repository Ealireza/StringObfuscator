package com.obfuscate.runtime

import android.util.Base64

/**
 * Ultra-fast runtime deobfuscator for string values.
 * Optimized for minimal overhead and maximum performance.
 */
object StringDeobfuscator {

    /**
     * Deobfuscates a Base64-encoded, XOR-encrypted string.
     *
     * Process:
     * 1. Base64 decode
     * 2. Reverse bit shuffling
     * 3. XOR decryption
     *
     * @param encoded Base64-encoded obfuscated string
     * @param xorKey XOR key used during obfuscation
     * @return Original string value
     */
    @JvmStatic
    inline fun deobfuscate(encoded: String, xorKey: Byte): String {
        // Base64 decode
        val bytes = Base64.decode(encoded, Base64.DEFAULT)

        // Reverse bit shuffling & XOR in one pass for maximum performance
        val result = ByteArray(bytes.size)
        for (i in bytes.indices) {
            val b = bytes[i].toInt() and 0xFF
            // Reverse shuffle: (b >> 3) | (b << 5)
            val unshuffled = ((b shr 3) or (b shl 5)) and 0xFF
            // XOR decrypt
            result[i] = (unshuffled xor xorKey.toInt()).toByte()
        }

        return String(result, Charsets.UTF_8)
    }

    /**
     * Deobfuscates from raw byte array (even faster, no Base64 overhead).
     *
     * @param encoded Raw obfuscated bytes
     * @param xorKey XOR key used during obfuscation
     * @return Original string value
     */
    @JvmStatic
    inline fun deobfuscateFast(encoded: ByteArray, xorKey: Byte): String {
        // Single-pass deobfuscation for maximum speed
        val result = ByteArray(encoded.size)
        for (i in encoded.indices) {
            val b = encoded[i].toInt() and 0xFF
            val unshuffled = ((b shr 3) or (b shl 5)) and 0xFF
            result[i] = (unshuffled xor xorKey.toInt()).toByte()
        }
        return String(result, Charsets.UTF_8)
    }
}
