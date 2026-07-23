# PRODUCTIVITY & KNOWLEDGE PLATFORM

# 03_DECISION_LOG.md

---

# Document Information

| Field | Value |
|-------|-------|
| Project | Productivity & Knowledge Platform |
| Document | Architecture Decision Log |
| Version | 3.0 |
| Status | Living Document |
| Last Updated | v0.6.1 – Platform Foundation |
| Current Milestone | v0.7.0 – Document Processing Foundation|
---

# Purpose

This document records every important architectural decision made throughout the project.

It answers one simple question:

> **Why does the project look the way it does?**

Whenever a future architectural change is proposed, this document should be consulted before modifying existing designs.

---

# ADR-001

## Decision

Use Spring Boot as the backend framework.

---

### Context

The project aims to learn production backend engineering while building a portfolio-quality application.

---

### Alternatives Considered

- Jakarta EE
- Micronaut
- Quarkus
- Node.js (Express/NestJS)

---

### Decision

Spring Boot

---

### Reason

- Largest ecosystem
- Excellent documentation
- Industry standard
- Strong Spring Security support
- Excellent JPA integration
- Large job market

---

### Consequences

Positive

- Easier learning resources
- Production practices
- Huge ecosystem

Negative

- Higher abstraction
- Learning curve

---

# ADR-002

## Decision

Use Layered Architecture.

---

### Alternatives

- Clean Architecture
- Hexagonal Architecture
- Microservices

---

### Reason

Current project size does not justify more complex architectures.

Layered Architecture provides:

- Simplicity
- Maintainability
- Readability

---

### Future Impact

Architecture may evolve later.

No redesign is currently necessary.

---

# ADR-003

## Decision

Use PostgreSQL inside Docker.

---

### Reason

Provides:

- Environment consistency
- Production-like setup
- Easy portability
- Easy backup

---

### Alternatives

- Local PostgreSQL
- MySQL
- H2

---

### Why Rejected

H2 differs from production.

Docker gives consistent environments.

---

# ADR-004

## Decision

Use Email as the unique user identifier.

---

### Alternatives

Username

UUID

Phone Number

---

### Reason

Email is:

- Unique
- Familiar
- Already required

---

### Consequences

Authentication uses email.

JWT subject stores email.

UserDetails.getUsername() returns email.

---

# ADR-005

## Decision

Use Enum for roles.

---

### Current Roles

USER

ADMIN

---

### Alternative

Set<Role>

Many-to-Many Role table

---

### Reason

Current business requirements are simple.

Multiple simultaneous roles are unnecessary.

---

### Future

May evolve if requirements change.

---

# ADR-006

## Decision

Automatically assign USER role during registration.

---

### Reason

Users should never choose privileges.

Role assignment is server responsibility.

---

### Benefits

Simple

Secure

Prevents privilege escalation.

---

# ADR-007

## Decision

Use JWT Authentication.

---

### Alternatives

HTTP Session

Cookies

OAuth Only

---

### Reason

REST API

Stateless

Scalable

Frontend independent

---

### Benefits

No server session

Horizontal scalability

Simple API authentication

---

### Trade-offs

Cannot revoke tokens easily.

Requires expiration strategy.

Refresh Tokens required later.

---

# ADR-008

## Decision

Use BCrypt for password hashing.

---

### Alternatives

SHA-256

MD5

Plain text

---

### Reason

BCrypt automatically:

- Generates salt
- Handles secure hashing
- Industry standard

---

### Consequences

No manual salt implementation required.

---

# ADR-009

## Decision

Authentication handled by AuthenticationManager.

---

### Reason

Delegates authentication.

Follows Spring Security architecture.

Avoids custom authentication implementation.

---

### Consequences

Application relies on Spring Security strategy pattern.

---

# ADR-010

## Decision

Use DaoAuthenticationProvider.

---

### Reason

Username/password authentication.

Direct integration with UserDetailsService.

---

### Alternatives

Custom AuthenticationProvider

---

### Reason Rejected

Unnecessary complexity.

---

# ADR-011

## Decision

Create CustomUserDetails.

---

### Reason

Application User differs from Spring Security UserDetails.

Adapter pattern bridges both models.

---

### Benefits

Keeps domain model independent.

---

# ADR-012

## Decision

JWT validation performed using OncePerRequestFilter.

---

### Reason

JWT must be validated exactly once per request.

---

### Alternatives

Controller validation

Interceptor

---

### Reason Rejected

Authentication belongs in Security Filter Chain.

Not controllers.

---

# ADR-013

## Decision

Stateless authentication.

---

### Reason

JWT already stores authentication.

HTTP Sessions unnecessary.

---

### Consequences

SessionCreationPolicy.STATELESS

---

# ADR-014

## Decision

Use @ConfigurationProperties for JWT configuration.

---

### Alternatives

@Value

---

### Reason

Strong typing

Cleaner configuration

Better scalability

---

# ADR-015

## Decision

Use Records for DTOs.

---

### Reason

Immutable

Concise

Readable

---

### Consequences

Entities remain mutable.

DTOs remain immutable.

---

# ADR-016

## Decision

Never expose entities directly.

---

### Reason

API should not depend on persistence model.

---

### Benefits

Security

Encapsulation

Versioning

---

# ADR-017

## Decision

Controllers remain thin.

---

### Reason

Controllers should only coordinate HTTP communication.

Business logic belongs in services.

---

# ADR-018

## Decision

JwtService owns all JWT responsibilities.

---

### Responsibilities

- Generate token
- Validate token
- Extract claims
- Extract username
- Signing key
- Expiration

---

### Must Not

Authenticate users.

Access repositories.

Handle HTTP.

---

# ADR-019

## Decision

JwtFilter owns JWT validation.

---

### Responsibilities

Validate incoming token.

Populate SecurityContext.

Continue filter chain.

---

### Must Not

Generate tokens.

Authenticate passwords.

Contain business logic.

---

# ADR-020

## Decision

Use unified ApiErrorResponse.

---

### Reason

Consistent API responses.

---

### Current Fields

- timestamp
- status
- error
- message
- path

---

### Future

May include

- validationErrors
- traceId
- requestId

---

# ADR-021

## Decision

Guests can access selected document processing capabilities.

---

### Reason

Lower barrier to entry.

Users can evaluate platform before registering.

---

### Future

Authenticated users receive additional functionality:

- Processing History
- Saved Documents
- Analytics
- Personal Workspace
---

# ADR-022

## Decision

Keep JWT claims minimal.

---

### Current

Subject

↓

Email

---

### Reason

Avoid unnecessary token size.

Additional claims only when required.

---

# ADR-023

## Decision

Authentication module considered complete before Document Processing.

---

### Reason

Avoid spending excessive time perfecting authentication.

Document processing capabilities now take priority.

---

### Deferred Features

- Refresh Tokens
- OAuth2
- Forgot Password
- Email Verification

---

# ADR-024

## Decision

Follow "Simplicity First".

---

### Principle

Always implement the simplest solution that satisfies current requirements.

Only introduce abstraction when duplication or business complexity justifies it.

---

### Examples

Rejected:

- Factory Pattern
- Builder Pattern
- Generic Base Services

Reason

No current need.

---

# ADR-025

## Decision

Mentoring prioritizes understanding over implementation.

---

### Workflow

1. Explain problem.
2. Explain why solution exists.
3. Ask user to implement.
4. Review implementation.
5. Suggest improvements.
6. Discuss production practices.
7. Discuss interview relevance.

---

### Reason

Long-term engineering growth is more valuable than quickly completing features.

---

# ADR-026

## Decision

Use Apache PDFBox for PDF generation.

---

### Context

The first utility module requires converting uploaded images into PDF documents.

The library should be:

- Open source
- Mature
- Well documented
- Production proven
- Compatible with Spring Boot

---

### Alternatives Considered

- Apache PDFBox
- OpenPDF
- iText

---

### Decision

Apache PDFBox

---

### Reason

PDFBox provides:

- Apache 2.0 License
- Active maintenance
- Excellent image rendering support
- Large community
- Straightforward API
- No licensing concerns for open-source projects

---

### Consequences

Positive

- Simple API
- No commercial licensing restrictions
- Good documentation

Negative

- Entire document is generated in memory.
- Less feature-rich than commercial PDF libraries.

---

### Future Impact

If document size or processing time becomes significant, the implementation may evolve toward streaming or temporary-file generation.

---

# ADR-027

## Decision

Generate PDFs entirely in memory.

---

### Context

The current utility generates PDFs immediately and returns them to the client.

Generated files do not need to be stored.

---

### Alternatives Considered

- Temporary files
- Persistent file storage
- In-memory generation

---

### Decision

In-memory generation using ByteArrayResource.

---

### Reason

Current business requirements involve relatively small files.

Generating the document in memory:

- Simplifies implementation
- Avoids filesystem cleanup
- Reduces infrastructure requirements
- Provides fast responses

---

### Consequences

Positive

- Stateless processing
- No disk I/O
- Simple deployment
- Easy cleanup

Negative

Large PDFs increase memory usage.

---

### Future Impact

Future utility modules may introduce streaming responses or external object storage if larger workloads require it.

---

# ADR-028

## Decision

Implement processing-specific exception handling.

---

### Context

Document processing modules introduce business rules that differ from authentication and persistence.

Examples include:

- Invalid image formats
- Corrupted image files
- PDF generation failures

---

### Alternatives Considered

- Reuse generic exceptions only
- Create module-specific exceptions

---

### Decision

Create dedicated processing exceptions while continuing to use the application's centralized error response format.

---

### Reason

Business-specific exceptions improve:

- Readability
- Maintainability
- Error classification

while preserving a consistent API response structure.

---

### Consequences

Positive

- Better separation of concerns
- Easier debugging
- Reusable error response model

Negative

Slight increase in the number of exception classes.

---

### Future Impact

Each future document processing module should define its own business exceptions without duplicating response-building logic.

---

# ADR-029

## Decision

Implement document processing modules as self-contained feature packages.

---

### Context

The project has begun growing beyond authentication.

Document processing features should remain independent while continuing to follow the application's layered architecture.

---

### Alternatives Considered

- Place utility classes directly in shared service packages
- Organize utilities into dedicated feature packages

---

### Decision

Create a dedicated utility package for each feature.

Example:

```
utility

↓

imageToPdf

↓

controller

service

exception
```

---

### Reason

Feature-oriented organization:

- Improves maintainability
- Reduces coupling
- Simplifies future expansion
- Makes module ownership clearer

---

### Consequences

Positive

- Better modularity
- Easier navigation
- Scales naturally as additional document processing capabilities are added.

Negative

Slightly deeper package hierarchy.

Future document processing capabilities (PDF → Image, Merge PDF, Split PDF, OCR) should follow the same organizational approach.


---

# ADR-030: Adopt Apache PDFBox for PDF Rendering

### Status

Accepted

### Context

The platform required support for converting PDF documents into images while maintaining high rendering quality and supporting future document-processing capabilities.

### Decision

Apache PDFBox was selected as the rendering library.

### Rationale

- Mature open-source library
- Actively maintained
- Native PDF rendering support
- Excellent Spring Boot compatibility
- Supports future document-processing features

### Consequences

Positive

- Reliable rendering
- Extensible foundation
- Minimal external dependencies

Negative

- Rendering large PDFs increases memory usage
- Performance optimizations may be required in future versions

---

# ADR-031: Return Rendered Pages as ZIP Archive

### Status

Accepted

### Context

PDF to Image conversion may generate multiple image files.

Returning multiple HTTP responses is not practical.

### Decision

Package all rendered images into a single ZIP archive before returning the response.

### Rationale

- Single download
- Better user experience
- Standard browser support
- Simplifies API design

### Consequences

Positive

- Easy client handling
- Reduced network overhead
- Cleaner API

Negative

- Temporary memory overhead while creating the archive

---

# ADR-032: Validate PDFs Before Rendering

### Status

Accepted

### Context

Corrupted or invalid PDF files should be rejected before rendering begins.

### Decision

Validate uploaded PDFs using PDFBox before processing.

### Rationale

- Fail fast
- Better error messages
- Avoid unnecessary rendering work
- Improve API reliability

### Consequences

Positive

- Better user experience
- Improved stability
- Consistent validation flow

---

# ADR-033: Evolve from Utility-Centric Features to Document Processing

### Status

Accepted

### Context

The project originally organized file operations as independent utilities.

As additional capabilities were introduced, this structure no longer represented the long-term direction of the platform.

### Decision

Treat document processing as a core business domain.

Individual operations such as Image → PDF, PDF → Image, Merge PDF and Split PDF are considered capabilities within this domain.

### Rationale

- Better scalability
- Consistent terminology
- Easier future expansion
- Supports OCR, AI and document management

### Consequences

Positive

- Clear architectural direction
- Better documentation consistency
- Simplifies future feature organization

---
### Future Impact

---

# Future ADRs

Examples of future decisions:

ADR-030

PDF → Image Library Selection

ADR-031

File Storage Strategy

ADR-032

OCR Library Selection

ADR-033

AI Provider Strategy

ADR-034

Background Job Processing

ADR-035

Caching Strategy

ADR-036

Asynchronous Document Processing

ADR-037

Testing Strategy

---

# Decision Review Policy

Every significant architectural decision should:

- Be documented before implementation.
- Include reasoning.
- Include rejected alternatives.
- Include trade-offs.
- Include future impact.

This document should evolve alongside the project and serve as the historical record of architectural reasoning.

---

# End of Document

This document explains **why** the architecture exists in its current form. It should be consulted before introducing significant design changes to ensure future decisions remain consistent with the project's established philosophy.