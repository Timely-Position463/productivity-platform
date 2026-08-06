# PRODUCTIVITY & KNOWLEDGE PLATFORM

# 02_ARCHITECTURE.md

---

# Document Information

| Field | Value                                   |
|-------|-----------------------------------------|
| Project | Productivity & Knowledge Platform       |
| Document | Architecture                            |
| Version | 3.0                                     |
| Status | Living Document                         |
| Last Updated Milestone | v0.7.1 – Document Processing Foundation |
| Current Milestone | v0.8.x – Document Processing Expansion  |
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
├── entity
├── exception
├── repository
├── security
├── service
├── util

├── processing
│   ├── controller
│   ├── pdf
│   │   ├── imageToPdf
│   │   ├── pdfToImage
│   │   ├── merge
│   │   └── split
│   ├── image
│   └── common

└── ProductivityApplication
```

The project now follows a modular layered structure.

Core platform concerns (authentication, persistence, security) remain separated from business utility modules.

Each document-processing capability owns its own business logic while continuing to respect the global layered architecture.

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

Application business logic.

Responsibilities:

- Authentication
- User management
- Document processing job management
- Cross-module business coordination

Feature-specific business logic should be implemented inside the corresponding processing module whenever possible.

Example:

```
processing

↓

pdf

↓

imageToPdf
```

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

Reusable helper components.

Current shared utilities:

- EntityToDTOConverter

Feature-specific helpers belong inside their respective document-processing modules.

Example:

```
utility

↓

util

↓

ImagePlacement
```

This keeps unrelated utility implementations isolated from the application's shared components.

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

# 6. Document Processing Architecture

The Document Processing domain is designed as an independent business domain within the layered architecture.

Current implementation:

```
ProcessingController

↓

Processing Service

↓

Validation

↓

Processing Engine

↓

Generated Output

↓

HTTP Response
```

Current characteristics:

- Stateless processing
- Synchronous execution
- In-memory document processing
- Image → PDF generation
- PDF → Image rendering
- ZIP packaging for multi-file responses
- No persistent file storage
- Guest-accessible endpoints

This architecture intentionally favors simplicity.

Future utility modules should follow the same structure unless business requirements justify a different design.

---

# 7. Entity Model

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

Prepared for future document processing operations.

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

# 8. DTO Architecture

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

# 9. Security Architecture

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

# 10. JWT Architecture

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

# 11. JwtFilter

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

# 12. Authentication Flow

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

# 13 Authorization Flow

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

# 14. SecurityContext

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

# 15. Request Lifecycle

## Public Document Processing Request

```
Example: PDF → Image Request

Client

↓

Multipart Request

↓

Security Filter Chain

↓

PermitAll

↓

ProcessingController

↓

PdfToImageService

↓

PDF Validation

↓

PDF Rendering

↓

ZIP Packaging

↓

HTTP Response
```

---

## Protected Request

```
Client

↓

Security Filter Chain

↓

JwtFilter

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

Current utility endpoints are intentionally public.

Authenticated document processing workflows will extend this lifecycle by creating UtilityJob records after successful processing.

---

# 16. Error Handling Architecture

Application-wide exceptions are handled centrally.

```
Controller / Service

↓

Custom Exception

↓

Global Exception Handler

↓

ApiErrorResponse
```

Document processing modules define feature-specific exceptions while reusing the application's centralized error response architecture.
Current utility exceptions include:

- ImageValidationException
- PdfGenerationException
- ImageValidationException
- PdfGenerationException

This allows each module to define business-specific exceptions without duplicating error response logic.

---

# 17. Dependency Injection Rules

Project standard

Constructor Injection only.

Never use field injection.

Reasons

- Easier testing
- Immutable dependencies
- Better readability

---

# 18. Configuration Strategy

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

# 19. Database Strategy

Current

PostgreSQL

Docker

Spring Data JPA


Current document-processing capabilities (Image → PDF and PDF → Image) perform stateless, in-memory processing and therefore do not persist generated files.

Future authenticated processing workflows will integrate UtilityJob persistence for tracking processing history.

Future authenticated utility execution will integrate UtilityJob persistence for tracking utility history.

Future considerations

- Indexing
- Pagination
- Search
- Performance tuning

No premature optimization.

---

# 20. Logging Strategy

Current

SLF4J

Future

Meaningful logs

Avoid excessive logging.

Errors should include exception context.

---

# 21. Future Architectural Evolution

Current

Layered Modular Monolith

↓

Feature Modules

↓

Document Processing

↓

Document Editing

↓

OCR

↓

AI Services

↓

Knowledge Platform

↓

Selective Clean Architecture (only if justified)

The project will continue evolving incrementally without introducing unnecessary abstractions.

---

# 22. Architectural Constraints

Future contributors should preserve the following principles.

- Controllers remain thin.
- Business logic belongs in services.
- Document processing capabilities remain self-contained.
- Shared utilities remain framework-independent whenever practical.
- JWT remains centralized.
- Security remains independent from business modules.
- DTOs remain immutable.
- Entities are never exposed directly.
- Avoid premature abstraction.
- Prefer readability over cleverness.
- Introduce asynchronous processing only when synchronous execution becomes a limitation.
---

# 23. Definition of Good Architecture

Every new module should satisfy:

✓ Single Responsibility Principle

✓ Separation of Concerns

✓ Dependency Injection

✓ DTO-based APIs

✓ Consistent Error Handling

✓ Feature-oriented modularity

✓ Reusable Services

✓ Testability

✓ Extensibility

✓ Simplicity before abstraction

Every document processing capability should integrate naturally into the existing layered architecture without introducing parallel architectural patterns.

---

# End of Document

This document describes the complete technical architecture of the platform.

Any future feature (Utilities, OCR, AI, Search, Knowledge Management) should integrate into this architecture rather than introducing parallel designs.

For architectural decisions and rationale, refer to:

03_DECISION_LOG.md
