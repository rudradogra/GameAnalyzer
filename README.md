# GameAnalyzer

GameAnalyzer is a small, educational Java project for tracking simple 1v1 basketball games. It was built as a learning exercise to practice object‑oriented programming (OOP) and to apply SOLID design principles in a practical, easy-to-understand codebase. The project focuses on clean object modeling for Players, Games, and Stats, and on producing a readable summary of a match.

Why this project exists
- I'm learning Java and OOP fundamentals.
- I wanted a compact project to practice designing classes, encapsulation, and single-responsibility code.
- It’s a place to experiment with SOLID principles in a straightforward domain.

Features
- Represent players, games, and basic statistics for 1v1 basketball matches.
- Record scoring events and simple stats per player.
- Produce a summary or report of a game (console output).
- Small, easy-to-read codebase intended for learning and extension.

Getting started

Prerequisites
- Java Development Kit (JDK) 11+ (or whichever version the repo uses)
- An IDE such as IntelliJ IDEA, Eclipse, or VS Code with Java support
- (Optional) Maven or Gradle if you adapt the project to use a build tool

Build & run (basic, without a build tool)
1. Clone the repository:
   git clone https://github.com/rudradogra/GameAnalyzer.git
2. Open the project in your IDE or compile from the command line:
   - From the project root, if source files are directly under src/:
     javac -d out src/**/*.java
     java -cp out com.yourpackage.Main
   - If the project already uses a build tool (Maven/Gradle), open the project in your IDE and use the IDE’s run configuration or use:
     mvn compile exec:java
     or
     ./gradlew run

If you need specific run instructions for this repository (main class name, package structure), open the project in an IDE to find the entry point (class with public static void main(String[] args)).

Usage example (console)
A typical run may print a summary like:
Player A vs Player B
Final score: Player A 11 - Player B 9
Player A: 11 pts (6 2PT, 2 3PT, 3 assists)
Player B: 9 pts (4 2PT, 1 3PT, 2 steals)

(Exact output will depend on the implementation in the repository.)

Design & architecture

Object-oriented approach
- Models are separated into clear classes (e.g., Player, Game, Stats, Event).
- Each class has a focused responsibility (single responsibility principle).
- Code is written to make common operations (recording a score, printing a report) straightforward and testable.

How SOLID is applied (examples)
- Single Responsibility: A Game class manages the match flow, a Player holds player-specific data, and a Reporter class formats output.
- Open/Closed: Use interfaces or abstract classes where behavior might change (e.g., different report formats) so new functionality can be added without modifying existing code.
- Liskov Substitution: Subtypes (for example, different kinds of Reporters) are interchangeable where the base type is expected.
- Interface Segregation: Keep small, focused interfaces (e.g., Scorable, Reportable) rather than monolithic ones.
- Dependency Inversion: Higher-level modules depend on abstractions (interfaces), not concrete implementations — helpful for testing and swapping implementations.

Testing
- Add unit tests for core logic: scoring, game end conditions, and reporting.
- Use JUnit (recommended) for automated tests.
- Keep testable code by avoiding static state; pass dependencies via constructors for easier mocking.

Extending the project (ideas)
- Support multiplayer or full-team games.
- Persist games to JSON, CSV, or a lightweight database.
- Add a GUI or web front-end for interactive input and visualization.
- Add player statistics over multiple games (career totals/averages).
- Add command line arguments to seed games / run simulations.

Contributing
- This repository is a learning project — contributions and suggestions are welcome.
- Before submitting changes, consider:
  - Keeping changes small and focused
  - Adding or updating tests for new behavior
  - Keeping public APIs (class/method names) stable where possible
- To contribute:
  1. Fork the repo.
  2. Create a feature branch.
  3. Open a pull request with a clear description of your change.


Contact
- Author: rudradogra
- Repo: https://github.com/rudradogra/GameAnalyzer

Notes
- This is a beginner project — expect straightforward code and comments that explain design decisions.