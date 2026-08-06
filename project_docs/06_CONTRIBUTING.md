# PRODUCTIVITY & KNOWLEDGE PLATFORM

# 06_DEVELOPMENT_GUIDE.md

---

# Document Information

| Field | Value                                   |
|-------|-----------------------------------------|
| Project | Productivity & Knowledge Platform       |
| Document | Development Guide                       |
| Version | 3.0                                     |
| Status | Living Document                         |
| Last Updated Milestone | v0.7.1 – Platform Foundation            |
| Current Milestone | v0.8.0 – Document Processing Foundation |
---

# 1. Purpose

This document defines **how the project should be developed**.

Unlike PROJECT_SPECIFICATION.md, which explains **what** is being built, this document explains **how every feature should be designed, implemented, reviewed, and evolved**.

Every future contributor—including future ChatGPT sessions—should follow these standards.

---

# 2. Engineering Philosophy

This project is intentionally built like a real software engineering project rather than a tutorial.

Every implementation should optimize for:

- Understanding
- Maintainability
- Readability
- Correctness
- Scalability
- Long-term evolution

rather than simply making the code work.

---

# 3. Core Development Principles

## Principle 1

### Understand Before Implementing

Before writing code, always answer:

- What business problem exists?
- Why is this feature needed?
- What are the constraints?
- What alternatives exist?
- Why is the chosen solution appropriate?

Code should always be the final step—not the first.

---

## Principle 2

### Simplicity First

Always begin with the simplest implementation that satisfies the current requirements.

Avoid introducing:

- Generic abstractions
- Design patterns
- Frameworks
- Additional layers

unless they solve a real problem.

---

## Principle 3

### Responsibility-Driven Design

Every class should own exactly one responsibility.

Instead of asking:

> "Where can I write this code?"

Ask:

> "Which class should own this responsibility?"

This question should guide future architectural decisions.

---

## Principle 4

### Features Drive Learning

The project teaches concepts through feature implementation.

Never study technologies in isolation.

Instead:

Business Requirement

↓

Architecture

↓

Implementation

↓

Learning

Example

Document processing features should introduce new backend concepts naturally through real business requirements rather than isolated tutorials.

Example:

Image → PDF introduced MultipartFile, validation, and PDF generation.

PDF → Image will introduce PDF parsing, rendering, ZIP archive creation, and multi-file responses.

---

# 4. Coding Standards

## Constructor Injection Only

Always use constructor injection.

Preferred:

```java
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

}
```

Never use:

```java
@Autowired
private UserRepository repository;
```

Reason:

- Immutable dependencies
- Easier testing
- Better readability

---

## Records for DTOs

All Request and Response DTOs should use Java Records.

Example

```java
public record LoginRequest(
    String email,
    String password
) {
}
```

Reason

- Immutable
- Concise
- Better intent

---

## Entities Are Persistence Models

Entities represent database structure.

They must not be exposed directly through APIs.

Always convert:

Entity

↓

DTO

---

## Controllers Stay Thin

Controllers should only:

- Receive requests
- Validate requests
- Call services
- Return responses

Controllers must never contain business logic.

---

## Services Own Business Logic

Services should:

- Coordinate repositories
- Apply business rules
- Validate business constraints
- Delegate specialized work

Feature-specific business logic should remain inside the corresponding feature module whenever possible.

Example

```
utility

↓

imageToPdf

↓

ImageToPdfService
```

Services should never know HTTP details or directly construct HTTP responses.

---

## Repositories Only Persist Data

Repositories should:

- Query the database
- Save entities
- Delete entities

Nothing more.

---

## Configuration Is Centralized

Configuration belongs only in the config package.

Use:

@ConfigurationProperties

instead of scattered @Value annotations whenever appropriate.

---

# 5. Package Organization Rules

The project follows a layered modular structure.

Shared packages:

- config
- controller
- service
- repository
- entity
- dto
- security
- exception
- util

Business features should be implemented as independent feature modules whenever appropriate.

Current implementation uses the utility package as the document processing feature area.

Future document processing capabilities should continue extending this structure until a dedicated processing module becomes justified.
Example

```
utility

↓

imageToPdf

↓

controller

service

exception
```

New feature modules should integrate into the existing architecture instead of creating parallel structures.

---

# 6. Naming Conventions

## Controllers

Feature + Controller

Examples

UserController

AuthController

UtilityController

---

## Services

Feature + Service

Examples

AuthService

JwtService

ImageToPdfService

UtilityJobService

---

## Repositories

Entity + Repository

Examples

UserRepository

UtilityJobRepository

---

## DTOs

Request

```
CreateUserRequest
```

Response

```
LoginResponse
```

---

## Exceptions

Use meaningful names.

Examples

UserNotFoundException

InvalidCredentialsException

JobNotFoundException

---

# 7. Error Handling Standards

All exceptions should be handled centrally.

Controllers should never manually construct error responses.

Current approach

```
Business Logic

↓

Custom Exception

↓

GlobalExceptionHandler

↓

ApiErrorResponse
```

Each feature module may define business-specific exceptions while continuing to use the centralized error response format.

This keeps error handling consistent without sacrificing modularity.

Future validation responses should also follow the same structure.

---

# 8. Security Development Rules

Authentication should remain centralized.

Controllers should never manually inspect JWTs.

JwtFilter owns:

- Token validation
- Authentication creation
- SecurityContext population

JwtService owns:

- JWT generation
- JWT validation
- Claim extraction

Never mix these responsibilities.

---

# 9. Feature Development Workflow

Every new feature should follow this workflow.

### Step 1

Understand the business problem.

---

### Step 2

Define functional requirements.

---

### Step 3

Identify architectural responsibilities.

---

### Step 4

Design the API contract.

---

### Step 5

Design DTOs and validation.

---

### Step 6

Implement business logic incrementally.

---

### Step 7

Review architecture and responsibilities.

---

### Step 8

Refactor only when justified.

---

### Step 9

Update tests.

---

### Step 10

Step 10

Update documentation, CHANGELOG, and release notes (when applicable).

A milestone is complete only when implementation and documentation remain synchronized.

---

# 10. Code Review Standards

Every completed implementation should be reviewed.

Review format:

## Overall Score

Example

9.5 / 10

---

## Strengths

What was done well.

---

## Architectural Review

Responsibilities

Layering

SRP

Design

---

## Code Review

Naming

Readability

Spring usage

Potential improvements

---

## Trade-offs

Alternative implementations.

Why the current solution is appropriate.

---

## Production Considerations

Would this design scale?

Would this design be maintainable?

Would this design be acceptable in production?

---

## Interview Perspective

Explain which interview topics are reinforced.

---

## Next Exercise

Continue learning incrementally.

---

# 11. Documentation Policy

# 11. Documentation Policy

Every completed milestone should review and update, where applicable:

- 01_PROJECT_SPECIFICATION.md
- 02_ARCHITECTURE.md
- 03_DECISION_LOG.md
- 04_ROADMAP.md
- 05_ENGINEERING_PLAYBOOK.md
- 06_CONTRIBUTING.md
- 07_HANDOFF.md
- CHANGELOG.md
- README.md (when user-facing functionality changes)

Documentation should evolve together with the implementation.

Outdated documentation is considered technical debt.

---

# 12. Architecture Evolution Policy

The architecture should evolve only when justified.

Current

Layered Modular Monolith

↓

Feature Modules

↓

Document Processing Expansion

↓

OCR

↓

AI Integration

↓

Knowledge Platform

↓

Selective Clean Architecture (only if justified)

Architecture should evolve only when business complexity requires it.

Avoid redesigning the project for theoretical reasons.

---

# 13. Mentoring Workflow

Future ChatGPT sessions should continue following this workflow.

## Before Coding

Explain:

- Real-world problem
- Business motivation
- Alternatives
- Trade-offs

---

## During Coding

Ask the developer to attempt implementation first.

Provide hints before complete solutions.

Review code critically.

Encourage reasoning.

---

## After Coding

Discuss:

- Refactoring
- Production improvements
- Best practices
- Interview relevance

The objective is to build engineering judgment rather than simply finish features.

---

# 14. Communication Style

Future mentoring should be:

- Professional
- Encouraging
- Honest
- Technically rigorous

Avoid excessive praise.

Instead:

Explain what is correct.

Explain why.

Suggest improvements.

Challenge assumptions respectfully.

---

# 15. Definition of High-Quality Code

Every implementation should satisfy:

✓ Correctness

✓ Readability

✓ Single Responsibility

✓ Clear naming

✓ Layered architecture

✓ Appropriate abstractions

✓ Meaningful exception handling

✓ Consistent API design

✓ Maintainability

✓ Feature modularity and reusable document processing design

✓ Production-ready documentation

If an implementation works but violates these principles, it should be improved before being considered complete.

---

# 16. Future Engineering Topics

The following topics should be introduced only when the project naturally requires them.

- Automated Testing
- Transactions
- Document Processing Pipelines
- Resource Management
- Async Processing
- Background Jobs
- File Storage Strategies
- Object Storage
- Performance Profiling
- Search
- Messaging
- Distributed Systems

Avoid introducing complexity before it provides measurable value.

---

# 17. Release Checklist

Before publishing a new project milestone:

- Verify code quality.
- Remove debug logging.
- Review API consistency.
- Update all project documentation.
- Update CHANGELOG.
- Update README.
- Tag the release.
- Perform final manual testing.
- Verify documentation reflects the implemented code.
- Confirm release notes are prepared.
- Tag the release after the final commit is pushed.

A release should represent a stable and well-documented milestone rather than simply the latest code.

---

# 18. Definition of Success

This project is successful when:

- The platform evolves through production-inspired features.
- The architecture remains maintainable.
- Every document processing capability introduces meaningful backend engineering concepts.
- Documentation stays synchronized with implementation.
- The developer learns to reason independently about architecture, trade-offs, and production-quality software.
- The architecture remains maintainable.
- Every feature teaches a new engineering concept.
- Documentation stays synchronized with implementation.
- The developer learns to reason about software architecture independently.

The final goal is not simply to complete a backend application.

The final goal is to develop the mindset and skills of a professional backend engineer.

---

# End of Document

This guide defines the engineering standards, coding conventions, mentoring workflow, and quality expectations for the entire project.

Every future implementation should follow this document to maintain consistency across the codebase and the learning process.