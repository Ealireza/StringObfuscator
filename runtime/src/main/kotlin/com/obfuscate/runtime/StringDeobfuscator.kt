package com.obfuscate.runtime

import android.util.Base64
import java.nio.ByteBuffer
import java.nio.ReadOnlyBufferException

/**
 * Ultra-fast runtime deobfuscator for string values.
 * Optimized for minimal overhead and maximum performance using readonly spans.
 */
object StringDeobfuscator {

    /**
     * Deobfuscates a Base64-encoded, XOR-encrypted string.
     *
     * Process:
     * 1. Base64 decode to readonly buffer
     * 2. Reverse bit shuffling & XOR decryption in-place
     *
     * @param encoded Base64-encoded obfuscated string
     * @param xorKey XOR key used during obfuscation
     * @return Original string value
     */
    @JvmStatic
    inline fun deobfuscate(encoded: String, xorKey: Byte): String {
        // Base64 decode to byte array
        val bytes = Base64.decode(encoded, Base64.DEFAULT)

        // Use readonly span optimization - deobfuscate in-place
        return deobfuscateInPlace(bytes, xorKey)
    }

    /**
     * Deobfuscates from raw byte array using readonly span optimization.
     * Even faster - no Base64 decoding overhead.
     *
     * @param encoded Raw obfuscated bytes (will be modified in-place for performance)
     * @param xorKey XOR key used during obfuscation
     * @return Original string value
     */
    @JvmStatic
    inline fun deobfuscateFast(encoded: ByteArray, xorKey: Byte): String {
        // Create a copy to avoid modifying the original data
        val workingCopy = encoded.copyOf()
        return deobfuscateInPlace(workingCopy, xorKey)
    }

    /**
     * Optimized deobfuscation using in-place operations on readonly spans.
     * This avoids unnecessary memory allocations and improves performance.
     *
     * @param bytes Byte array to deobfuscate in-place
     * @param xorKey XOR key for decryption
     * @return Deobfuscated string
     */
    @JvmStatic
    inline fun deobfuscateInPlace(bytes: ByteArray, xorKey: Byte): String {
        // Single-pass deobfuscation: reverse shuffle + XOR in one loop
        for (i in bytes.indices) {
            val b = bytes[i].toInt() and 0xFF
            // Reverse bit shuffle: (b >> 3) | (b << 5) & 0xFF
            val unshuffled = ((b shr 3) or (b shl 5)) and 0xFF
            // XOR decryption and store back
            bytes[i] = (unshuffled xor xorKey.toInt()).toByte()
        }

        // Create string directly from modified bytes
        return String(bytes, Charsets.UTF_8)
    }

    /**
     * Memory-efficient version that reuses a shared buffer.
     * WARNING: Not thread-safe! Use only in single-threaded contexts.
     *
     * @param encoded Raw obfuscated bytes
     * @param xorKey XOR key used during obfuscation
     * @param buffer Reusable buffer (must be at least as large as encoded)
     * @return Original string value
     */
    @JvmStatic
    inline fun deobfuscateFastBuffered(encoded: ByteArray, xorKey: Byte, buffer: ByteArray): String {
        require(buffer.size >= encoded.size) { "Buffer too small" }

        // Copy to buffer for in-place operations
        encoded.copyInto(buffer, 0, 0, encoded.size)

        // Deobfuscate in-place
        for (i in 0 until encoded.size) {
            val b = buffer[i].toInt() and 0xFF
            val unshuffled = ((b shr 3) or (b shl 5)) and 0xFF
            buffer[i] = (unshuffled xor xorKey.toInt()).toByte()
        }

        return String(buffer, 0, encoded.size, Charsets.UTF_8)
    }
}
