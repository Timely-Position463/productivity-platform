# PRODUCTIVITY & KNOWLEDGE PLATFORM

# 02_ARCHITECTURE.md

---

# Document Information

| Field | Value |
|-------|-------|
| Project | Productivity & Knowledge Platform |
| Document | Architecture |
| Version | 2.0 |
| Status | Living Document |
| Last Updated Milestone | Authentication Module (JWT v1) |
| Current Milestone | Utility Module – Image → PDF |

---

# 1. Architecture Overview

The project follows a **Layered Modular Monolith** architecture.

This architecture was intentionally chosen because it provides the best balance between:

- Simplicity
- Maintainability
- Learning
- Scalability

without introducing unnecessary complexity.

Current architecture:

```

REST Client

↓

Controllers

↓

Services

↓

Repositories

↓

PostgreSQL

```

Spring Security surrounds the application.

```

REST Request

↓

Security Filter Chain

↓

JWT Filter

↓

Controller

↓

Service

↓

Repository

↓

Database

```

---

# 2. Why Layered Architecture?

Alternative architectures considered:

- Clean Architecture
- Hexagonal Architecture
- Microservices

Rejected because:

The project is currently small.

The user is learning backend engineering.

Introducing advanced architectures now would increase complexity without solving any real problem.

Decision:

Remain Layered until business growth naturally requires evolution.

---

# 3. Package Structure

```

com.ajay.productivity

├── config

├── controller

├── dto

│ ├── request

│ └── response

├── entity

├── exception

├── repository

├── security

├── service

├── util

└── ProductivityApplication

```

Package responsibilities are strict.

Classes should not violate these boundaries.

---

# 4. Package Responsibilities

## config

Purpose

Application configuration only.

Contains:

- SecurityConfig
- JwtProperties

Must never contain business logic.

---

## controller

Purpose

HTTP communication.

Responsibilities:

- Receive request
- Validate request
- Call service
- Return response

Controllers remain intentionally thin.

---

## service

Purpose

Business logic.

Responsibilities:

- Authentication
- Utility processing
- User operations
- Job management

Services must never contain HTTP concerns.

---

## repository

Purpose

Persistence.

Responsibilities:

- Database queries
- CRUD operations

Repositories should never contain business rules.

---

## entity

Purpose

Persistence model.

Contains JPA entities.

Entities should not be returned directly by APIs.

---

## dto

Purpose

API communication.

Uses Java Records.

Separated into:

Request DTOs

Response DTOs

---

## security

Purpose

Everything related to authentication.

Current classes:

- JwtService
- JwtFilter
- CustomUserDetails
- CustomUserDetailsService
- JwtAuthenticationEntryPoint

---

## exception

Purpose

Application-wide error handling.

Contains:

- Custom Exceptions
- ApiErrorResponse
- GlobalExceptionHandler

---

## util

Purpose

Shared helper classes.

Current:

EntityToDTOConverter

Future:

Utility-specific helpers if justified.

---

# 5. Layer Responsibilities

## Controller Layer

Responsibilities

✓ Receive HTTP requests

✓ Validate DTOs

✓ Call services

✓ Return responses

Must never:

- Access repositories
- Implement business rules

---

## Service Layer

Responsibilities

✓ Business logic

✓ Validation beyond DTO constraints

✓ Coordination

✓ Transactions (future)

Must never:

- Parse HTTP requests
- Know REST details

---

## Repository Layer

Responsibilities

✓ Database interaction

Nothing else.

---

# 6. Entity Model

## User

Fields

- id
- username
- email
- password
- role
- createdAt
- updatedAt

Relationship

```

User

↓

One-To-Many

↓

UtilityJob

```

---

## UtilityJob

Current

- id
- filename
- status
- timestamps
- user

Prepared for future utility modules.

---

## Role

Enum

```

USER

ADMIN

```

Decision:

Enum instead of Set<Role>

Reason:

Current requirements do not justify multiple simultaneous roles.

---

# 7. DTO Architecture

Project rule:

Entities never leave the service layer.

Flow

```

Entity

↓

Converter

↓

Response DTO

```

Reasons

- Encapsulation
- API stability
- Security

---

# 8. Security Architecture

Authentication architecture:

```

AuthenticationManager

↓

AuthenticationProvider

↓

DaoAuthenticationProvider

↓

UserDetailsService

↓

CustomUserDetailsService

↓

UserRepository

```

Responsibilities are intentionally separated.

---

# 9. JWT Architecture

JwtService owns:

- Token generation
- Token validation
- Claims
- Username extraction
- Signing key
- Expiration

JwtService never:

- Authenticates users
- Accesses repositories
- Handles HTTP requests

---

# 10. JwtFilter

Purpose

Authenticate every request exactly once.

Flow

```

Request

↓

Authorization Header

↓

Bearer?

↓

Extract Token

↓

Extract Username

↓

Load UserDetails

↓

Validate Token

↓

Create Authentication

↓

SecurityContext

↓

Continue Filter Chain

```

Responsibilities

Only JWT validation.

Nothing else.

---

# 11. Authentication Flow

```

Login Request

↓

AuthenticationManager

↓

AuthenticationProvider

↓

UserDetailsService

↓

Authentication

↓

JwtService

↓

JWT

↓

LoginResponse

```

---

# 12. Authorization Flow

Protected Request

↓

JwtFilter

↓

SecurityContext

↓

Role Check

↓

Controller

Decision

Authentication occurs before authorization.

---

# 13. SecurityContext

Purpose

Stores the authenticated user for the lifetime of the request.

Current Authentication object contains:

Principal

↓

CustomUserDetails

Credentials

↓

null

Authorities

↓

ROLE_USER / ROLE_ADMIN

---

# 14. Request Lifecycle

## Public Endpoint

```

Client

↓

Security Filter Chain

↓

PermitAll

↓

Controller

↓

Service

↓

Repository

↓

Response

```

---

## Protected Endpoint

```

Client

↓

Security Filter Chain

↓

JwtFilter

↓

JwtService

↓

SecurityContext

↓

Authorization

↓

Controller

↓

Service

↓

Repository

↓

Response

```

---

# 15. Error Handling Architecture

Global exceptions

↓

GlobalExceptionHandler

↓

ApiErrorResponse

Current standardized fields

- timestamp
- status
- error
- message
- path

Future

Validation details may be unified into this structure.

---

# 16. Dependency Injection Rules

Project standard

Constructor Injection only.

Never use field injection.

Reasons

- Easier testing
- Immutable dependencies
- Better readability

---

# 17. Configuration Strategy

Configuration belongs only in:

config package

Current configuration

- Security
- JWT
- Application Properties

Configuration values should use:

@ConfigurationProperties

instead of scattered @Value annotations whenever practical.

---

# 18. Database Strategy

Current

PostgreSQL

Docker

Spring Data JPA

Future considerations

- Indexing
- Pagination
- Search
- Performance tuning

No premature optimization.

---

# 19. Logging Strategy

Current

SLF4J

Future

Meaningful logs

Avoid excessive logging.

Errors should include exception context.

---

# 20. Future Architectural Evolution

Current

Layered Architecture

↓

Future

Modular Layered

↓

Domain-oriented packages

↓

Selective Clean Architecture

Only when justified.

---

# 21. Architectural Constraints

Future contributors (or future ChatGPT sessions) should preserve:

- Controllers remain thin
- Services own business logic
- JWT remains centralized
- Security stays independent
- DTOs remain immutable
- Entities never exposed
- Avoid premature abstraction
- Favor readability over cleverness

---

# 22. Definition of Good Architecture

Every new module should satisfy:

✓ Single Responsibility Principle

✓ Separation of Concerns

✓ Dependency Injection

✓ DTO-based APIs

✓ Consistent Error Handling

✓ Reusable Services

✓ Testability

✓ Extensibility

without introducing unnecessary complexity.

---

# End of Document

This document describes the complete technical architecture of the platform.

Any future feature (Utilities, OCR, AI, Search, Knowledge Management) should integrate into this architecture rather than introducing parallel designs.

For architectural decisions and rationale, refer to:

03_DECISION_LOG.md
