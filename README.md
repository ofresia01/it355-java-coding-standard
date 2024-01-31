# IT 355 Java Coding Standard

## Repository for code snippets demonstrating Java standard practices

This repository contains code snippets illustrating Java standard practices, with a focus on adhering to coding standards and best practices in Java development.

### Batch Scripts

#### 1. `compile-and-clean`

This batch script is designed to automate the compilation of Java source files located in the `src` directory. It compiles the files into the `out` directory and removes any existing compiled `.class` files before compilation. To use:

```bash
compile_and_clean
```

#### 2. `run-classes`

This batch script executes all compiled Java class files in the `out` directory. It assumes that the class files are in the `out` directory. To use:

```bash
run-classes
```

Note: Ensure that your Java runtime (`java`) is available in the system's PATH for these scripts to work properly.