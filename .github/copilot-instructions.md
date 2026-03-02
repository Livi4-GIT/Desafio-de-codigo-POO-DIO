# Repository Overview

This is a minimal **Java/Maven** project with a handful of classes under `src/main/java` and a single, trivial JUnit test. It is not a framework application; there is no Spring, Jakarta EE or similar. The goal is to demonstrate simple domain objects and the standard Maven layout.

## Big‑picture architecture

- `br.com.dio` is the root package used for production code.
  - `App.java` is the only entry point and simply prints "Hello World!".
  - `br/com/dio/dominio` contains the domain model with interconnected objects:
    - **`Conteudo`**: abstract base class for course content; subclasses (`Curso`, `Mentoria`) define learning materials. Has abstract method `calcularXp()`.
    - **`Curso`** & **`Mentoria`**: concrete content types with title, description, and duration. Both inherit from `Conteudo`.
    - **`Dev`**: represents a developer enrolled in bootcamps. Manages sets of content via `conteudosInscritos` and `conteudosConcluidos`; includes logic for progress (`progredir()`) and XP calculation (`calcularTotalXp()`).
    - **`Bootcamp`**: wraps a collection of `Conteudo` and enrolled `Dev` instances. Has fixed enrollment period (45 days from start).
- Tests live under `src/test/java/com/livia/app` and use **JUnit 5**. Only one test exists (`AppTest`) and it simply asserts `true`.

There are no services, repositories or external integrations in this repository. The layout is the standard Maven directory structure (`src/main/java`, `src/test/java`).

> ⚠️ Be aware that the existing domain classes contain minor mistakes:
> - `Curso` is spelled `Cruso` in the file name. 
> - `Mentoria` has a getter for `cargaHoraria` but no corresponding field. 
> Fix such issues when editing or adding new classes to keep the package consistent.

## Build & developer workflows

All build logic is driven by Maven (`pom.xml`). The project targets Java 11 (see `<maven.compiler.source>`/`target`).

Common commands:

```sh
# compile and run the tests
mvn clean test

# create a jar in target/ (tests are executed as part of this)
mvn clean package

# compile only
mvn compile

# run the main class from the CLI
java -cp target/classes br.com.dio.App
```

- There are no custom plugins configured; rely on the defaults defined in `pluginManagement`.
- Use the Maven `junit-bom` and `junit-jupiter` dependencies for any new tests.

In an IDE you can invoke the `main()` method in `App` or run individual test classes as usual.

## Project‑specific conventions

1. **Package naming**: production code is under `br.com.dio.*`; tests currently use `com.livia.app` but new tests can be placed in the same package as the class under test (i.e. mirror the `br.com.dio` hierarchy) for clarity.
2. **Domain objects**: simple POJOs with `private` fields and standard getters/setters. Fields use basic types (`String`, `int`, `LocalDate`).
3. **Inheritance & abstraction**: `Conteudo` is the base for all course content; concrete subclasses (`Curso`, `Mentoria`) implement `calcularXp()`.
4. **Collections**: Use `LinkedHashSet` to preserve insertion order (e.g., for tracked learning sequences); use `HashSet` when order is irrelevant.
5. **Class/file names**: must match exactly and be spelled correctly. ⚠️ Fix `Cruso` → `Curso` when refactoring.
6. **No business logic outside domain**: Keep logic lightweight and localized in domain objects (e.g., `.progredir()`, `.calcularTotalXp()` live in `Dev`).
7. **External dependencies**: currently only JUnit; add new dependencies sparingly and declare them in `pom.xml`.

## Integration points & external services

This repo has none. There is no database, network, or third‑party API used. Any new integration should be added explicitly to `pom.xml` and manually wired in the code.

## Examples of key files

- **`src/main/java/br/com/dio/dominio/Conteudo.java`** is an abstract base for all learning content:
  ```java
  public abstract class Conteudo {
      protected static final double XP_PADRAO = 10d;
      private String titulo, descricao;
      public abstract double calcularXp();
      // getters/setters
  }
  ```

- **`src/main/java/br/com/dio/dominio/Curso.java`** (currently misspelled `Cruso`) and **`Mentoria.java`** extend `Conteudo`. Both follow the bean pattern with `private` fields and public getters/setters.
  - ⚠️ **Note**: `Mentoria.java` has a getter for `cargaHoraria` but no corresponding field; consider removing the getter or adding the field.

- **`src/main/java/br/com/dio/dominio/Dev.java`** manages a developer's enrollment and progress:
  ```java
  public class Dev {
      private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
      private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();
      public void inscreverBootcamp(Bootcamp bootcamp) { /* enroll in bootcamp */ }
      public void progredir() { /* move first completed content to conclusão */ }
      public double calcularTotalXp() { /* sum XP from completed content */ }
  }
  ```
  Uses `LinkedHashSet` to preserve insertion order.

- **`src/main/java/br/com/dio/dominio/Bootcamp.java`** aggregates content and enrolled devs:
  ```java
  public class Bootcamp {
      private final LocalDate dataInicial = LocalDate.now();
      private final LocalDate dataFinal = dataInicial.plusDays(45);
      private Set<Dev> devsInscritos = new HashSet<>();
      private Set<Conteudo> conteudos = new LinkedHashSet<>();
  }
  ```
  Note: `dataInicial` and `dataFinal` are `final` and initialized at field level. Use `HashSet` for devs (order irrelevant) and `LinkedHashSet` for content (order matters).

- **`pom.xml`** uses JUnit BOM for consistent dependency versions and locks plugin versions under `<pluginManagement>`.

## How to be immediately productive

- When asked to add a new feature, first confirm whether it belongs in `br.com.dio.dominio` or elsewhere; there are no existing packages for services/controllers.
- Fix obvious compile errors in existing code before expanding (the IDE or `mvn compile` will flag them). The project should always compile cleanly.
- Write tests under `src/test/java`, preferably mirroring the package structure of the code under test. Use JUnit 5 annotations (`@Test` etc.).
- Use Maven commands listed above for building/testing; no special environment variables or profiles are required.

## FAQs for AI assistance

- **"Where do I put new Java classes?"** In `src/main/java/br/com/dio` or a subpackage.
- **"How do I run the code?"** Use `java -cp target/classes br.com.dio.App` after `mvn compile`/`mvn package`.
- **"What Java version?"** Java 11 as specified in the `pom.xml`.
- **"Any domain conventions?"** Simple beans with getters/setters; follow naming consistency and correct spelling.

---

If there are any patterns you think I've missed or if instructions seem unclear, let me know so I can refine this document!