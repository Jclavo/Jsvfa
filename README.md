# JSVFA

*Java Static Value Flow Analysis*

This projects is an **Static Value Flow** Analyzer for programs in Java. Its development is based in rules, which were extracting from [SVF: Interprocedural Static Value-Flow Analysis in LLVM book](https://dl.acm.org/doi/abs/10.1145/2892208.2892235) 

# To do

In this study the next implementations must be done: 

- [X] Rules
    - [X] Copy
    - [ ] Load
    - [ ] Store
    - [X] Call & Return
    - [ ] Array*
- [X] Graph
- [X] Value Flow
- [ ] Flowdroid benchmark 

# Dependencies
- SootUp

# Version
0.1

# Author
José Clavo Tafur

# Timeline
The proposal dates to finish the project can be found at [Project's timeline](https://github.com/users/Jclavo/projects/1)

# Dependencies

### sootup.java.bytecode

It needs **com.android.tools:r8:8.0.40** and at the time it is not available in the repository so it needs to be set up manually

- Download **r8-8.0.40.pom** from [https://maven.google.com/web/index.html#com.android.tools:r8:8.0.40](https://maven.google.com/web/index.html#com.android.tools:r8:8.0.40)
- Move file to **/.m2/repository/com/android/tools/r8/8.0.40/**
