# PRODUCTIVITY & KNOWLEDGE PLATFORM

# 04_ROADMAP.md

---

# Document Information

| Field | Value |
|-------|-------|
| Project | Productivity & Knowledge Platform |
| Document | Development Roadmap |
| Version | 2.0 |
| Status | Living Document |
| Last Updated Milestone | Authentication Module (JWT v1) |
| Current Milestone | Utility Module – Image → PDF |

---

# 1. Purpose

This document defines the development roadmap for the project.

Unlike a traditional TODO list, this roadmap is intended to guide architectural evolution.

Every milestone should:

- Solve a real business problem.
- Introduce new backend concepts naturally.
- Build upon previous milestones.
- Preserve architectural consistency.
- Improve backend engineering knowledge.

---

# 2. Guiding Principles

The roadmap follows several rules.

## Build Features, Not Concepts

We never implement technology for its own sake.

Instead,

Business Requirement

↓

Architecture

↓

Implementation

↓

Learning

Example

We do **not** learn MultipartFile first.

Instead,

Image → PDF requires MultipartFile.

Therefore MultipartFile is introduced naturally.

---

## Preserve Simplicity

The roadmap intentionally delays advanced topics until they solve actual problems.

Avoid introducing:

- Microservices
- Kafka
- CQRS
- Event Sourcing
- Distributed Cache

until justified.

---

## Each Milestone Should Teach Something New

Every milestone introduces new engineering concepts.

Authentication

↓

Spring Security

Utility Module

↓

File Processing

OCR

↓

Image Processing

AI

↓

External API Integration

Knowledge Platform

↓

Search & Information Retrieval

---

# 3. Milestone Overview

| Milestone | Status | Priority |
|-----------|--------|----------|
| Environment Setup | ✅ Completed | High |
| Project Foundation | ✅ Completed | High |
| Authentication & Security | ✅ Completed | High |
| Utility Module | 🚧 Current | High |
| OCR Module | Planned | Medium |
| AI Module | Planned | Medium |
| Knowledge Module | Planned | Medium |
| Admin Dashboard | Planned | Low |
| Performance & Optimization | Planned | Low |
| Production Hardening | Planned | Low |

---

# 4. Completed Milestones

## Milestone 0

### Environment Setup

Status

Completed

Achievements

- Java
- Maven
- Spring Boot
- Docker
- PostgreSQL

Concepts Learned

- Docker
- PostgreSQL
- Spring Boot setup
- Maven

---

## Milestone 1

### Foundation

Status

Completed

Achievements

- Layered Architecture
- DTOs
- Entities
- Repositories
- Services
- Controllers
- Validation
- Global Exception Handling

Concepts Learned

- REST APIs
- DTO Pattern
- Validation
- Dependency Injection
- Layered Architecture

---

## Milestone 2

### Authentication & Security

Status

Completed

Achievements

- Registration
- Login
- BCrypt
- JWT
- Security Filter Chain
- JwtFilter
- AuthenticationManager
- DaoAuthenticationProvider
- UserDetails
- SecurityContext
- Role-based Authorization
- Stateless Authentication

Concepts Learned

- Spring Security
- Authentication
- Authorization
- JWT
- Security Architecture
- Filter Chain
- OncePerRequestFilter

---

# 5. Current Milestone

# Utility Module v1

Status

Current

Priority

Highest

---

## Objective

Deliver the first business feature of the application.

Image → PDF

This milestone transitions the project from infrastructure development to user-facing functionality.

---

## Business Requirement

Guest users should be able to:

- Upload one or more images.
- Receive a generated PDF.

Authenticated users should additionally receive:

- Job history.
- Ownership.
- Future download history.

---

## Learning Objectives

This milestone introduces:

- Multipart Requests
- MultipartFile
- File Upload Lifecycle
- File Validation
- Temporary File Handling
- PDF Generation
- Streaming Responses
- Resource Management
- File Cleanup
- UtilityJob Persistence

---

## Expected Deliverables

### API Design

Design endpoints.

Discuss REST conventions.

---

### File Upload

Support:

PNG

JPEG

WEBP (optional)

Future:

HEIC

BMP

---

### PDF Generation

Convert uploaded images into a PDF.

Initially:

Synchronous generation.

Future:

Async processing.

---

### Streaming

Return PDF as:

application/pdf

instead of storing files unnecessarily.

---

### Persistence

Authenticated users:

Create UtilityJob.

Guest users:

No persistence.

---

### Validation

Supported image types.

Maximum file count.

Maximum file size.

Empty uploads.

Invalid images.

---

### Error Handling

Consistent API responses.

Use ApiErrorResponse.

---

## Success Criteria

✓ Multiple images accepted

✓ PDF generated

✓ Download works

✓ Guest access supported

✓ Authenticated ownership supported

✓ Job persisted

✓ Exception handling complete

✓ API documented

---

# 6. Next Milestone

# Utility Module v2

Features

- PDF → Image
- Merge PDF
- Split PDF

Concepts

- Multiple outputs
- ZIP streaming
- PDF parsing
- Memory optimization

---

# 7. Utility Module v3

Features

- Compress Image
- Resize Image
- Rotate Image
- Watermark PDF

Concepts

- Image manipulation
- Processing pipelines
- Performance

---

# 8. OCR Module

Status

Planned

Business Goal

Extract text from uploaded images.

Potential Libraries

- Tesseract
- Tess4J

Expected Concepts

- OCR
- Text extraction
- Search indexing

---

# 9. AI Module

Status

Planned

Potential Providers

- OpenAI
- Google Gemini
- Ollama

Future Features

Document Summary

↓

Quiz Generation

↓

Flashcards

↓

Knowledge Extraction

↓

Semantic Search

---

# 10. Knowledge Module

Status

Planned

Features

- Notes
- Tags
- Collections
- Search
- Saved Documents

Potential Future Architecture

Knowledge Graph

Semantic Search

---

# 11. Admin Module

Status

Future

Features

User Management

↓

Job Monitoring

↓

Statistics

↓

System Health

↓

Logs

---

# 12. Performance Milestone

Future

Topics

Pagination

↓

Caching

↓

Database Optimization

↓

Indexes

↓

Streaming Optimization

↓

Memory Profiling

---

# 13. Testing Milestone

Current

Minimal testing.

Future

Unit Tests

↓

Integration Tests

↓

Security Tests

↓

File Processing Tests

---

# 14. Production Hardening

Future

Logging

↓

Monitoring

↓

Rate Limiting

↓

Metrics

↓

Health Checks

↓

Containerization Improvements

---

# 15. Authentication Backlog

The following are intentionally postponed.

Refresh Tokens

OAuth2

Email Verification

Forgot Password

Reset Password

AccessDeniedHandler

Validation Response Unification

Reason

Authentication foundation is complete.

Business value now has higher priority.

---

# 16. Recommended Execution Order

Future development should follow this order.

Authentication

↓

Image → PDF

↓

PDF → Image

↓

Merge PDF

↓

Split PDF

↓

Image Compression

↓

OCR

↓

AI Summary

↓

Knowledge Platform

↓

Search

↓

Performance

↓

Production Hardening

Avoid skipping milestones.

Each milestone introduces concepts required by the next.

---

# 17. Future Refactoring Opportunities

Current architecture intentionally favors simplicity.

Possible future improvements:

- Domain-oriented packages.
- Storage abstraction.
- Async job execution.
- Background processing.
- Search indexing.
- File storage service.

These should only be introduced when justified.

---

# 18. Milestone Completion Policy

A milestone is complete only when:

✓ Feature implemented.

✓ API reviewed.

✓ Architecture reviewed.

✓ Code reviewed.

✓ Documentation updated.

✓ ADR created (if architectural decisions were made).

✓ Learning Log updated.

✓ Handoff updated.

---

# 19. Living Document Policy

After every milestone:

Update

- PROJECT_SPECIFICATION.md
- ARCHITECTURE.md
- DECISION_LOG.md
- ROADMAP.md
- LEARNING_LOG.md
- DEVELOPMENT_GUIDE.md
- HANDOFF.md

No new handoff should be created unless the architecture changes significantly.

---

# End of Document

This roadmap is the authoritative planning document for the project.

Every future development session should begin by identifying the current milestone and ensuring new work aligns with the established execution order and architectural philosophy.