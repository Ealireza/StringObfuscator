# Security Policy

## Supported Versions

| Version | Supported          |
| ------- | ------------------ |
| 1.x.x   | :white_check_mark: |

## Security Considerations

### What String Obfuscator Protects

✅ **Basic reverse engineering** - Makes it harder to extract strings from APK
✅ **Static analysis** - Tools like `strings`, `jadx`, `apktool` won't easily find values
✅ **Casual inspection** - Decompiled code shows obfuscated bytes, not plain strings

### What It Doesn't Protect

❌ **Advanced debugging** - Runtime debugging with breakpoints can still expose values
❌ **Determined attackers** - Given enough time and tools, obfuscation can be broken
❌ **Memory dumps** - Runtime memory inspection can reveal deobfuscated strings
❌ **Root access** - Rooted devices with debugging tools can intercept values

## Best Practices

### DO ✅

1. **Combine with ProGuard/R8** - Use code minification for additional protection
2. **Use certificate pinning** - For sensitive API communications
3. **Implement root detection** - Detect compromised devices
4. **Add runtime integrity checks** - Verify app hasn't been tampered with
5. **Use remote config** - Fetch sensitive values from server when possible
6. **Rotate secrets** - Regularly update API keys and tokens
7. **Monitor usage** - Track API usage for suspicious activity
8. **Use HTTPS only** - Never send sensitive data over unencrypted connections

### DON'T ❌

1. **Don't hardcode ultra-sensitive secrets** - Use secure key storage (Android Keystore)
2. **Don't rely solely on obfuscation** - It's one layer, not complete security
3. **Don't skip server validation** - Always validate on the backend
4. **Don't commit real secrets to git** - Use environment variables or secure vaults
5. **Don't assume it's unbreakable** - Obfuscation ≠ Encryption

## Threat Model

### Low Threat (Protected ✅)
- Script kiddies using basic tools
- Automated string extraction tools
- Casual reverse engineers
- Static analysis without runtime debugging

### High Threat (Not Protected ❌)
- Advanced attackers with debugging tools
- Nation-state level adversaries
- Attackers with physical device access
- Sophisticated runtime memory analysis

## Recommended Architecture

For maximum security:

```
┌─────────────────────────────────────┐
│          Mobile App                 │
│                                     │
│  ┌──────────────────────────────┐  │
│  │   String Obfuscator          │  │
│  │   (Basic Protection)         │  │
│  └──────────────────────────────┘  │
│  ┌──────────────────────────────┐  │
│  │   ProGuard/R8                │  │
│  │   (Code Minification)        │  │
│  └──────────────────────────────┘  │
│  ┌──────────────────────────────┐  │
│  │   Certificate Pinning        │  │
│  │   (Network Security)         │  │
│  └──────────────────────────────┘  │
│  ┌──────────────────────────────┐  │
│  │   Root Detection             │  │
│  │   (Runtime Protection)       │  │
│  └──────────────────────────────┘  │
└─────────────────────────────────────┘
              ↕ HTTPS
┌─────────────────────────────────────┐
│       Backend Server                │
│   (Ultimate Authority)              │
│                                     │
│   - API Key Validation              │
│   - Rate Limiting                   │
│   - Anomaly Detection               │
│   - Secret Rotation                 │
└─────────────────────────────────────┘
```

## Reporting Security Issues

**Please do not report security vulnerabilities through public GitHub issues.**

Instead, please report them via:

1. **Email:** security@yourproject.com (replace with your email)
2. **Private disclosure** on GitHub Security Advisories

Include:

- Description of the vulnerability
- Steps to reproduce
- Potential impact
- Suggested fix (if any)

We will respond within **48 hours** and work with you to resolve the issue.

## Responsible Disclosure

We appreciate security researchers who:

- Give us reasonable time to fix issues before public disclosure
- Do not exploit vulnerabilities beyond proof-of-concept
- Make a good faith effort to avoid privacy violations
- Don't perform attacks that could harm our users

## Security Updates

Security updates will be released as:

- **Patch versions** (x.x.X) for security fixes
- **GitHub Security Advisories** for known vulnerabilities
- **Release notes** with `[SECURITY]` tag

Subscribe to releases to stay informed.

## Compliance

This library is designed for:

- **OWASP Mobile Top 10** - Addresses M7 (Client Code Quality)
- **PCI DSS** - Helps with requirement 6.5.3 (Insecure Cryptographic Storage)
- **GDPR** - Part of technical measures for data protection

**Note:** Obfuscation alone does not guarantee compliance. Consult security professionals for your specific requirements.

## Audit History

| Date | Auditor | Findings | Status |
|------|---------|----------|--------|
| TBD  | TBD     | TBD      | TBD    |

*We welcome independent security audits of this project.*

## References

- [OWASP Mobile Security](https://owasp.org/www-project-mobile-security/)
- [Android Security Best Practices](https://developer.android.com/topic/security/best-practices)
- [KSP Documentation](https://kotlinlang.org/docs/ksp-overview.html)

---

**Remember:** Security is a journey, not a destination. Obfuscation is one tool in your security toolbox! 🔒
