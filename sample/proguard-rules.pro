# String Obfuscator ProGuard Rules

# Keep generated obfuscated classes
-keep class **.*Obfuscated { *; }
-keepclassmembers class **.*Obfuscated { *; }

# Keep deobfuscator utility
-keep class com.obfuscate.runtime.StringDeobfuscator { *; }

# Keep annotation (not strictly necessary as it's SOURCE retention)
-keep @interface com.obfuscate.annotation.StringObfuscate

# Keep classes annotated with @StringObfuscate
-keep @com.obfuscate.annotation.StringObfuscate class * { *; }

# Standard Android optimizations
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# Keep line numbers for debugging
-keepattributes SourceFile,LineNumberTable

# Remove logging in release builds (optional)
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
