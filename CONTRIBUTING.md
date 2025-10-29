# Contributing to String Obfuscator

First off, thank you for considering contributing to String Obfuscator! 🎉

## 🚀 How to Contribute

### Reporting Bugs

If you find a bug, please open an issue with:

- **Clear title** describing the problem
- **Steps to reproduce** the issue
- **Expected behavior** vs actual behavior
- **Environment details** (Kotlin version, KSP version, Android version)
- **Code samples** if applicable

### Suggesting Features

Have an idea? Open an issue with:

- **Feature description** - What do you want to add?
- **Use case** - Why is this needed?
- **Proposed implementation** - How would it work?

### Pull Requests

1. **Fork** the repository
2. **Create a branch** from `main`:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make your changes** following our coding guidelines
4. **Test thoroughly** - Ensure builds pass
5. **Commit** with clear messages:
   ```bash
   git commit -m "Add: Feature description"
   ```
6. **Push** to your fork:
   ```bash
   git push origin feature/your-feature-name
   ```
7. **Open a Pull Request** with:
   - Clear description of changes
   - Reference to related issues
   - Screenshots/examples if applicable

## 📝 Development Guidelines

### Code Style

- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Add KDoc comments for public APIs
- Keep functions small and focused

### Testing

- Add tests for new features
- Ensure existing tests pass
- Test on multiple Android versions

### Commit Messages

Use clear, descriptive commit messages:

- `Add: New feature description`
- `Fix: Bug description`
- `Update: Changed functionality`
- `Refactor: Code improvement`
- `Docs: Documentation updates`

## 🏗️ Project Structure

```
StringObfuscator/
├── annotation/          # @StringObfuscate annotation
├── processor/           # KSP processor
├── runtime/             # Runtime utilities
└── sample/              # Example app
```

### Key Files

- `processor/.../StringObfuscateProcessor.kt` - Main KSP processor
- `runtime/.../StringDeobfuscator.kt` - Runtime deobfuscation
- `annotation/.../StringObfuscate.kt` - Annotation definition

## 🧪 Building & Testing

```bash
# Build all modules
./gradlew build

# Run tests
./gradlew test

# Build sample app
./gradlew :sample:assembleDebug
```

## 📋 Checklist

Before submitting a PR, ensure:

- [ ] Code follows project conventions
- [ ] All tests pass
- [ ] New features have tests
- [ ] Documentation is updated
- [ ] Commit messages are clear
- [ ] No unnecessary dependencies added
- [ ] ProGuard rules updated if needed

## 🤝 Code of Conduct

- Be respectful and inclusive
- Provide constructive feedback
- Focus on the code, not the person
- Help create a welcoming environment

## 💡 Ideas for Contribution

Looking for something to work on? Consider:

- **Performance improvements** - Optimize deobfuscation speed
- **Custom algorithms** - Add more obfuscation methods
- **Documentation** - Improve guides and examples
- **Testing** - Add more test coverage
- **CI/CD** - Improve automation
- **Examples** - Add more use cases

## 📞 Questions?

Feel free to:

- Open an issue for discussion
- Tag maintainers in your PR
- Check existing issues and PRs

## 🙏 Thank You!

Your contributions make String Obfuscator better for everyone. We appreciate your time and effort!

---

**Happy Coding!** 🔒
