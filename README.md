# IT 355 Java Coding Standard

## Repository for code snippets demonstrating Java standard practices

### Batch Scripts

#### 1. `compile-and-clean`

This batch script is designed to automate the compilation of Java source files located in the `src` directory. It compiles the files into the `out` directory and removes any existing compiled `.class` files before compilation. To use:

```bash
compile_and_clean
```

#### 2. `run-example`

This batch script compiles the Java files located in the `src/example` directory, executes the `Driver` class with provided arguments, and deletes the compiled `.class` files afterwards. To use:

```bash
run-example
```