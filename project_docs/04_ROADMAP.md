# PRODUCTIVITY & KNOWLEDGE PLATFORM

# 04_ROADMAP.md

---

# Document Information

| Field | Value |
|-------|-------|
| Project | Productivity & Knowledge Platform |
| Document | Development Roadmap |
| Version | 3.0 |
| Status | Living Document |
| Last Updated Milestone | Utility Module v1 (Image → PDF) |
| Current Milestone | Utility Module v2 (PDF → Image) |
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
| Utility Module v1 (Image → PDF) | ✅ Completed | High |
| Utility Module v2 (PDF → Image) | 🚧 Current | High |
| Utility Module v3 | Planned | Medium |
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

## Milestone 3

### Utility Module v1 – Image → PDF

Status

Completed

Achievements

- Multipart file upload support
- PNG and JPEG validation
- Maximum file count validation
- Maximum file size validation
- Multi-page PDF generation
- Automatic image scaling while preserving aspect ratio
- Downloadable PDF response
- Feature-specific exception handling
- Stateless utility processing

Concepts Learned

- Multipart requests
- MultipartFile
- File validation
- BufferedImage
- Apache PDFBox
- Resource management
- In-memory file generation
- ByteArrayResource
- Feature-oriented modular architecture


---

## Objective

Expand the utility platform by introducing PDF → Image conversion.

This milestone introduces the first utility capable of producing multiple output files from a single input document.

---

## Business Requirement

Guest users should be able to:

- Upload a PDF document.
- Select an output image format.
- Download all generated pages.

Authenticated users will additionally receive:

- Utility history
- Job tracking
- Future download management

---

## Learning Objectives

This milestone introduces:

- PDF parsing
- Rendering PDF pages
- ZIP generation
- Multiple file responses
- Memory optimization
- Temporary resource management
- Utility pipeline evolution

---

## Expected Deliverables

### PDF Upload

Support:

- PDF documents

---

### Image Generation

Generate one image for each PDF page.

Initially:

PNG output.

Future:

JPEG and WEBP.

---

### Response

Return generated images as a ZIP archive.

---

### Validation

- PDF validation
- Maximum file size
- Corrupted document detection
- Empty upload detection

---

### Error Handling

Continue using standardized API responses through centralized exception handling.

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

# 6. Utility Module v3

Features

- Merge PDF
- Split PDF
- Compress Image
- Resize Image
- Rotate Image
- Watermark PDF

Concepts

- PDF manipulation
- Image processing
- Processing pipelines
- Memory optimization
- Reusable utility services

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

Basic manual testing completed.

Future

- Unit Tests
- Integration Tests
- Security Tests
- File Processing Tests
- Performance Tests
- Exception Handling Tests

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

Completed

```
Authentication

↓

Image → PDF
```

Current

```
PDF → Image
```

Upcoming

```
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
```

Each milestone builds upon the concepts introduced by the previous milestone.

Avoid skipping milestones unless business requirements change significantly.
---

# 17. Future Refactoring Opportunities

Current architecture intentionally favors simplicity.

Possible future improvements:

- Authenticated utility history
- UtilityJob integration
- Storage abstraction
- Async utility execution
- Background job processing
- Object storage integration
- Search indexing
- Performance profiling

These improvements should only be introduced when they solve an actual business or scalability problem.

---

# 18. Current Platform Progress

The project has now completed its infrastructure foundation and delivered its first production-inspired business feature.

Completed capabilities include:

- Authentication and Authorization
- Stateless JWT Security
- User Management
- Utility Module v1 – Image → PDF

The project has now entered its feature expansion phase, where additional utility modules will continue building upon the established architecture.

---

# 19. Milestone Completion Policy

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

# 20. Living Document Policy

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