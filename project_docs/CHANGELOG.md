# Changelog

All notable changes to this project will be documented in this file.

The format is inspired by **Keep a Changelog**, and the project follows **Semantic Versioning** where appropriate.

---

# v0.7.1 - Developer Experience

## Added

- Docker & Docker Compose support
- Environment-based configuration (.env)
- .env.example template
- Swagger/OpenAPI documentation
- Postman Collection & Environment
- Developer onboarding improvements
- README redesign

## Improved

- Local development workflow
- Project documentation
- Release documentation
- Platform onboarding experience

## [v0.7.0] - PDF to Image Utility

### Added

#### Utility Module
- Implemented PDF → Image conversion utility.
- Support for converting every page of a PDF into images.
- ZIP archive generation for converted images.
- Configurable image output format.
- PDF validation before processing.
- Temporary workspace management for generated images.
- Automatic cleanup of temporary resources after processing.

#### Architecture
- Introduced reusable PDF validation utility.
- Added PDF rendering service using Apache PDFBox.
- Added ZIP generation utility for multi-file responses.
- Extended utility module with PDF processing capabilities.

#### API
- Public PDF → Image endpoint.
- Standardized ZIP download response.
- Improved validation for uploaded PDF files.

#### Documentation
- Updated project specification.
- Updated architecture documentation.
- Updated roadmap.
- Updated development guide.
- Updated handoff documentation.
- Updated README.
- Updated CHANGELOG.

### Changed

- Expanded document-processing capabilities from image generation to PDF rendering.
- Improved utility module extensibility for future document conversion features.

### Fixed

- Proper detection of corrupted or unreadable PDF files.
- Improved resource cleanup during PDF rendering.
- Prevented processing of invalid PDF uploads.

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

## Planned

### v0.8.0
- Merge PDF
- Split PDF

### Later
- Compress Image
- Resize Image
- Rotate Image
- Watermark PDF
- OCR Module
- AI Integration
- Knowledge Platform

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



## Notes

This engineering release focuses on improving developer experience rather than introducing new platform capabilities.