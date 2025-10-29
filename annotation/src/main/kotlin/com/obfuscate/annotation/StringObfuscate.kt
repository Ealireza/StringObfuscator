package com.obfuscate.annotation

/**
 * Marks a class for string obfuscation.
 * All string property values in the annotated class will be obfuscated at compile time.
 *
 * The obfuscation process:
 * 1. XOR encryption with a random key
 * 2. Additional byte manipulation for entropy
 * 3. Base64 encoding
 *
 * Deobfuscation happens automatically at runtime via generated code.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class StringObfuscate
