# Productivity & Knowledge Platform

> A production-inspired backend platform built with Spring Boot to learn modern backend engineering through real-world feature development.

---

## Overview

The Productivity & Knowledge Platform is a long-term backend project designed to evolve from a collection of file utilities into an intelligent knowledge management platform.

Rather than focusing only on implementing features, this project emphasizes software architecture, clean code, security, scalability, and production-oriented engineering practices.

Current development follows an incremental milestone-based approach where each feature introduces new backend concepts naturally.

---

## Current Status

**Current Version:** `v0.5.0`

**Milestone:** Authentication Foundation Complete

### Completed

* Spring Boot 3.5
* PostgreSQL
* Docker Development Environment
* Layered Architecture
* DTO Pattern
* Global Exception Handling
* Spring Security
* JWT Authentication
* Stateless Security
* Role-Based Authorization
* BCrypt Password Encoding

### In Progress

* File Utilities Module

---

## Planned Features

### File Utilities

* Image → PDF
* PDF → Image
* Merge PDF
* Split PDF
* Compress Images
* Resize Images
* Rotate Images
* Image Format Conversion

### OCR

* Text Extraction
* Searchable Documents

### AI Features

* Document Summaries
* Flashcards
* Quiz Generation
* Knowledge Extraction

---

## Technology Stack

### Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Maven

### Database

* PostgreSQL
* Docker

### Authentication

* JWT
* BCrypt

---

## Architecture

The project follows a Layered Modular Monolith architecture.

```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
PostgreSQL
```

Security is handled through Spring Security using JWT-based stateless authentication.

---

## Documentation

Detailed engineering documentation is available in the `docs/` directory.

* Project Specification
* Architecture
* Decision Log
* Roadmap
* Learning Log
* Development Guide
* Project Handoff

---

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/Timely-Position463/productivity-platform.git
```

### Start PostgreSQL

```bash
docker compose up -d
```

### Run the Application

```bash
mvn spring-boot:run
```

---

## Roadmap

Current milestone:

**File Utilities Module**

Upcoming milestones:

* OCR Module
* AI Module
* Knowledge Platform

---

## Learning Philosophy

This project is intentionally developed as a long-term engineering journey.

Every feature is designed to introduce new backend concepts through practical implementation while following production-inspired architecture and clean coding principles.

---

## License

MIT License.
