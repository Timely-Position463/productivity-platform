# PRODUCTIVITY & KNOWLEDGE PLATFORM

# 01_PROJECT_SPECIFICATION.md

---

## Document Information

| Field | Value |
|-------|-------|
| Project | Productivity & Knowledge Platform |
| Document | Project Specification |
| Version | 2.0 |
| Status | Living Document |
| Last Updated Milestone | Authentication Module (JWT v1) |
| Current Milestone | Utility Module – Image → PDF |
| Architecture | Layered Modular Monolith |
| Primary Language | Java 21 |
| Framework | Spring Boot 3.5.x |

---

# 1. Project Vision

## Mission

Build a production-inspired backend platform while learning modern backend engineering through real-world project development.

The objective is **not** to create another CRUD application.

The objective is to understand:

- Why architectural decisions exist.
- Why frameworks behave the way they do.
- How production systems evolve.
- How to think like a backend engineer.

The project simultaneously serves as:

- Portfolio project
- Backend engineering practice
- Spring Boot learning platform
- System design practice
- Interview preparation project

---

# 2. Product Vision

The application begins as a Utility Platform.

Eventually it evolves into a complete Productivity & Knowledge Platform.

Long-term vision:

```
                Productivity Platform

                         │

      ┌──────────────────┼──────────────────┐

   Utilities          Knowledge          AI

      │                  │                │

 Image→PDF          Notes            OCR

 PDF→Image          Search           Summary

 Merge PDF          Tags             Quiz

 Split PDF          Collections      Flashcards

 Compress           History          Semantic Search

 Resize             Saved Files      Knowledge Graph
```

Every module should naturally build upon previous modules.

---

# 3. Product Philosophy

The project follows several guiding principles.

## 3.1 Learn Through Building

Every framework concept should appear naturally while implementing real features.

Example:

Instead of studying MultipartFile separately,

Image → PDF requires MultipartFile.

Therefore MultipartFile is introduced.

---

## 3.2 Simplicity First

Start with the simplest implementation.

Introduce abstraction only when business growth requires it.

Never introduce complexity "just because production systems do it."

---

## 3.3 Production-Oriented

The project should resemble production software.

Examples:

- DTOs
- Layered Architecture
- JWT Authentication
- Exception Handling
- Logging
- Configuration Properties

rather than tutorial shortcuts.

---

## 3.4 Long-Term Evolution

The project is intentionally designed to evolve.

Phase 1

Authentication

↓

Phase 2

Utilities

↓

Phase 3

OCR

↓

Phase 4

AI

↓

Phase 5

Knowledge Platform

Architecture should evolve naturally with these phases.

---

# 4. Business Context

Many free online productivity tools exist.

Examples:

- Image to PDF
- PDF Merge
- Compress Image

However, they usually provide:

- No history
- No personalization
- No AI assistance

This platform aims to combine:

Traditional file utilities

+

User accounts

+

Knowledge management

+

AI

into one cohesive application.

---

# 5. Core Business Goals

The platform should allow users to:

### Guest Users

- Use basic utilities.
- No account required.
- No history stored.

### Registered Users

- Access utilities.
- Save history.
- Manage generated files.
- Use future AI features.

### Administrators

- Manage platform.
- Manage users.
- Monitor jobs.

---

# 6. Functional Requirements

## Authentication Module

Completed

### Registration

Users can register.

Requirements:

- Username
- Email
- Password

Email must be unique.

Role automatically assigned as USER.

---

### Login

Users authenticate using:

Email

Password

Returns:

- Access Token
- Token Type
- Expiration

---

### Authorization

Public Endpoints

Accessible without login.

Protected Endpoints

Require authentication.

Admin Endpoints

Require ADMIN role.

---

### Security

Implemented

- JWT
- BCrypt
- Stateless Authentication
- Role-based Authorization

Future

- Refresh Tokens
- OAuth2
- Forgot Password
- Email Verification

---

# 7. Utility Module Requirements

(Current Milestone)

## Image → PDF

Guest User

```
Upload Images

↓

Generate PDF

↓

Download PDF
```

Authenticated User

```
Upload Images

↓

Generate PDF

↓

Store Utility Job

↓

View History
```

---

Future utilities:

- PDF → Image
- Merge PDF
- Split PDF
- Compress Image
- Resize Image
- Rotate Image
- Watermark PDF
- Extract Pages

---

# 8. Knowledge Module

Planned

Features:

- Saved Notes
- Search
- Collections
- Tags
- Categories

---

# 9. AI Module

Planned

Features

OCR

↓

Summary

↓

Quiz Generation

↓

Flashcards

↓

Knowledge Extraction

Future integrations:

- OpenAI
- Gemini
- Ollama (local)

Provider should remain replaceable.

---

# 10. User Module

Current

Basic registration and authentication.

Future

- Profile
- Avatar
- Preferences
- Storage statistics
- Dashboard
- Activity history

---

# 11. Job Management

Every utility execution should become a UtilityJob.

Responsibilities:

- Track status
- Store ownership
- Maintain history
- Enable retries
- Support future async execution

Possible statuses:

- PENDING
- PROCESSING
- COMPLETED
- FAILED

Current implementation stores the foundation for this model.

---

# 12. Non-Functional Requirements

## Maintainability

One responsibility per class.

Readable code preferred over clever code.

---

## Extensibility

Authentication providers should be replaceable.

Utility modules should be independent.

Future AI integrations should require minimal architectural changes.

---

## Performance

Avoid unnecessary database calls.

Stream files when appropriate.

Avoid excessive memory usage during file processing.

---

## Security

Passwords:

BCrypt

Authentication:

JWT

Sessions:

Disabled

Authorization:

Role-based

---

## Scalability

Current architecture:

Layered Modular Monolith.

Future evolution:

Modular Domain Structure if complexity justifies it.

No premature microservices.

---

## Reliability

Consistent error responses.

Centralized exception handling.

Meaningful logging.

---

# 13. Feature Priorities

## Completed

✔ Spring Boot Setup

✔ PostgreSQL

✔ Docker

✔ Layered Architecture

✔ DTOs

✔ Validation

✔ Security

✔ JWT

✔ Authentication

✔ Authorization

✔ Global Exception Handling

---

## Current

Image → PDF

---

## Next

PDF → Image

---

## Medium-Term

OCR

---

## Long-Term

Knowledge Platform

AI

Search

---

# 14. Success Criteria

The project is considered successful if it demonstrates:

- Strong backend architecture
- Clean REST API design
- Proper authentication
- Real-world file processing
- AI integration
- Modular growth
- Production-inspired engineering practices

Equally important:

The developer (Ajay) should finish the project understanding:

- Why architectural decisions were made.
- How Spring Boot components interact.
- How production backend systems evolve.
- How to reason about software design rather than memorize framework APIs.

---

# 15. Out of Scope (Current)

The following are intentionally postponed.

- Microservices
- Kubernetes
- Distributed caching
- Event-driven architecture
- CQRS
- Event Sourcing
- Multi-tenancy
- Distributed tracing

Reason:

The current project size does not justify these complexities.

They may be introduced later if the project naturally grows to require them.

---

# 16. Definition of Done

A feature is considered complete only if it satisfies all of the following:

✓ Business requirement implemented

✓ API designed appropriately

✓ Layered architecture respected

✓ DTOs used correctly

✓ Validation implemented

✓ Exception handling implemented

✓ Security considered

✓ Code reviewed

✓ Trade-offs discussed

✓ Architecture rationale documented

✓ User understands *why* the implementation exists

Learning is considered equally important as implementation.

---

# End of Document

This document defines **what the project is**, **why it exists**, **what features it contains**, and **where it is heading**.

Implementation details, architecture, package structure, request lifecycles, security internals, and technical decisions are intentionally documented separately in:

**02_ARCHITECTURE.md**