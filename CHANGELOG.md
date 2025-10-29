# Changelog

All notable changes to String Obfuscator will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Planned
- Support for custom obfuscation algorithms
- Gradle plugin for easier integration
- Multi-module project support improvements
- Performance benchmarking suite

## [1.0.0] - 2024-XX-XX

### Added
- Initial release of String Obfuscator
- `@StringObfuscate` annotation for marking classes
- KSP processor for compile-time string obfuscation
- Multi-layer obfuscation (XOR + Bit Shuffling + Base64)
- Runtime deobfuscation utilities
- Support for objects, classes, data classes, and companion objects
- Inline optimized deobfuscation (~100ns performance)
- Sample Android app demonstrating usage
- Comprehensive documentation and examples
- ProGuard/R8 compatibility
- GitHub Actions CI/CD workflows
- MIT License

### Security
- XOR encryption with random keys
- Bit shuffling for additional entropy
- Base64 encoding for storage
- Zero plaintext strings in compiled APK

### Documentation
- Complete README with usage examples
- CONTRIBUTING guide
- SECURITY policy
- Usage examples document
- GitHub issue templates

### Infrastructure
- Multi-module Gradle project structure
- Automated build and test workflows
- Release automation via GitHub Actions
- Issue templates for bugs and features

---

## Release Notes

### Version 1.0.0 - Initial Release

This is the first stable release of String Obfuscator, a compile-time string obfuscation library for Android/Kotlin using KSP.

**Key Features:**
- ⚡ Ultra-fast compile-time obfuscation
- 🔒 Multi-layer security (XOR + Shuffle + Base64)
- 🎯 Simple annotation-based API
- 📦 Works on all Kotlin class types
- 🛡️ ProGuard/R8 compatible

**What's Included:**
- Annotation module
- KSP processor
- Runtime utilities
- Sample app
- Full documentation

**Requirements:**
- Kotlin 1.9.22+
- KSP 1.9.22-1.0.17+
- Android SDK 21+

---

## Migration Guides

### From Hardcoded Strings to String Obfuscator

**Before:**
```kotlin
object ApiConfig {
    const val API_KEY = "my_secret_key"
}

// Usage
val key = ApiConfig.API_KEY
```

**After:**
```kotlin
@StringObfuscate
object ApiConfig {
    const val API_KEY = "my_secret_key"
}

// Usage
val key = ApiConfigObfuscated.getApiKey()
```

**Steps:**
1. Add `@StringObfuscate` annotation to your class
2. Add KSP processor to your `build.gradle.kts`
3. Build project to generate obfuscated classes
4. Update usage to use generated `*Obfuscated` classes

---

## Future Roadmap

### v1.1.0 (Planned)
- [ ] Custom obfuscation algorithm support
- [ ] Configuration options via Gradle
- [ ] Improved error messages
- [ ] Performance profiling tools

### v1.2.0 (Planned)
- [ ] Gradle plugin for easier setup
- [ ] IDE plugin for code completion
- [ ] Additional obfuscation methods
- [ ] Benchmarking suite

### v2.0.0 (Future)
- [ ] Support for non-string types
- [ ] Advanced encryption options
- [ ] Multi-module optimization
- [ ] Native library support

---

## Links

- [GitHub Repository](https://github.com/Ealireza/StringObfuscator)
- [Issue Tracker](https://github.com/Ealireza/StringObfuscator/issues)
- [Releases](https://github.com/Ealireza/StringObfuscator/releases)

---

**Questions?** Open an issue or check our [documentation](README.md)!
