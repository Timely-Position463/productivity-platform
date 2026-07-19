# Changelog

All notable changes to this project will be documented in this file.

The format is inspired by **Keep a Changelog**, and the project follows **Semantic Versioning** where appropriate.

---

## [v0.6.1] - CI/CD Foundation

### Added
- GitHub Actions CI workflow
- Automated Maven test execution on every push and pull request
- PostgreSQL service container for CI
- Maven dependency caching
- PostgreSQL health checks before test execution

### Improved
- Secure configuration using GitHub Repository Secrets
- Automated backend validation for every code change

---

## [v0.6.0] - Utility Module v1 (Image → PDF)

### Added

#### Utility Module
- Implemented Image → PDF conversion utility.
- Support for uploading multiple images in a single request.
- Support for PNG and JPEG image formats.
- Configurable maximum image count validation.
- Configurable maximum file size validation.
- Automatic image scaling while preserving aspect ratio.
- Multi-page PDF generation using Apache PDFBox.
- Direct PDF download without server-side file storage.

#### Architecture
- Introduced feature-oriented utility module structure.
- Added ImageToPdfService for business logic.
- Added utility-specific exception hierarchy.
- Extended centralized exception handling for utility modules.

#### API
- Public Image → PDF endpoint.
- Standardized PDF response headers.
- Improved validation for multipart requests.

#### Documentation
- Updated project specification.
- Updated architecture documentation.
- Added new Architecture Decision Records (ADR-026 to ADR-029).
- Updated roadmap.
- Updated learning log.
- Updated development guide.
- Updated handoff document.
- Improved README.
- Updated CHANGELOG.

### Changed

- Project focus shifted from authentication infrastructure to feature development.
- Utility module established as the first production-inspired business feature.
- Documentation synchronized with the completed Image → PDF milestone.

### Fixed

- Improved multipart request validation.
- Improved error handling for unsupported image formats.
- Improved resource management using try-with-resources.
- Removed outdated milestone references throughout the documentation.

---

## [v0.5.0] - Authentication Foundation Complete

### Added

#### Project Foundation
- Spring Boot project setup.
- PostgreSQL integration.
- Docker development environment.
- Layered modular architecture.
- DTO pattern.
- Request validation.
- Global exception handling.

#### Security
- Spring Security configuration.
- JWT authentication.
- BCrypt password hashing.
- Stateless authentication.
- Role-based authorization.
- AuthenticationManager integration.
- Custom UserDetails implementation.
- JWT request filter.
- Centralized authentication entry point.

### Documentation
- Initial engineering documentation.
- Project specification.
- Architecture documentation.
- Architecture Decision Log.
- Roadmap.
- Learning log.
- Development guide.
- Handoff documentation.

---

## Upcoming

### v0.7.0

- PDF → Image
- ZIP response generation
- PDF page rendering
- Memory optimization for multi-page processing

### Future

- Merge PDF
- Split PDF
- Compress Image
- Resize Image
- Rotate Image
- Watermark PDF
- OCR Module
- AI Integration
- Knowledge Platform