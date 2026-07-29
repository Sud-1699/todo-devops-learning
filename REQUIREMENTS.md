# Java Full Stack DevOps Learning Project

## Objective

Build a complete DevOps learning project from scratch as a Java Full Stack Developer.

The primary goal is to understand the complete Software Development Life Cycle (SDLC), Infrastructure as Code (IaC), containerization, Kubernetes orchestration, and CI/CD deployment using a production-like architecture while running everything locally.

The project will begin with a simple Spring Boot monolithic application and gradually evolve into a fully automated deployment pipeline.

---

# Learning Goals

- Develop a Spring Boot monolithic application.
- Develop a simple Angular frontend.
- Integrate PostgreSQL database.
- Containerize applications using Docker.
- Deploy applications to Kubernetes using Helm.
- Learn Infrastructure as Code using Terraform and Terragrunt.
- Simulate AWS services locally using LocalStack.
- Automate build and deployment using GitHub Actions.
- Gain production-level DevOps experience.

---

# Deployment Flow

```text
Developer

    │

    ▼

GitHub Repository

    │

    ▼

GitHub Actions (CI)

    │

    ▼

Build Spring Boot

Build Angular

Run Tests

Build Docker Images

    │

    ▼

Docker Images

    │

    ▼

Terraform / Terragrunt

    │

    ▼

LocalStack Infrastructure

    │

    ├── ECR

    ├── S3

    ├── IAM

    ├── CloudWatch

    └── (Optional EKS Simulation)

    │

    ▼

Kubernetes Cluster

(Kind / Minikube)

    │

    ▼

Helm Deployment

    │

    ▼

Spring Boot API

Angular UI

PostgreSQL
```

---

# Technology Stack

## Backend

- Java 21
- Spring Boot 3.x
- Spring Security
- JWT Authentication
- Spring Data JPA
- PostgreSQL
- Flyway
- Spring Boot Actuator
- OpenAPI / Swagger

## Frontend

- Angular
- Angular Material
- RxJS

## Database

- PostgreSQL

## Containerization

- Docker
- Docker Compose

## Kubernetes

- Kubernetes
- Helm

## Infrastructure

- Terraform
- Terragrunt
- LocalStack

## CI/CD

- GitHub Actions

## Monitoring (Future Scope)

- Prometheus
- Grafana

---

# Application

A simple Todo / Note Taking application.

## Functional Requirements

### Authentication

- User Registration
- User Login
- JWT Authentication

### Notes

- Create Note
- Update Note
- Delete Note
- Archive Note
- Search Notes
- Pagination
- Sorting

### User

- View Profile
- Update Profile
- Change Password

---

# Database Design

## User

| Column | Type |
|----------|------|
| id | UUID |
| name | String |
| email | String |
| password | String |

---

## Role

| Column | Type |
|----------|------|
| id | UUID |
| role_name | String |

---

## Note

| Column | Type |
|----------|------|
| id | UUID |
| title | String |
| description | Text |
| status | String |
| created_date | Timestamp |
| updated_date | Timestamp |
| user_id | UUID |

---

# Project Structure

```text
todo-devops-learning/

├── backend/
│
├── frontend/
│
├── docker/
│   └── docker-compose.yml
│
├── helm/
│   └── todo-app/
│       ├── Chart.yaml
│       ├── values.yaml
│       └── templates/
│
├── terraform/
│   ├── modules/
│   │   ├── ecr/
│   │   ├── eks/
│   │   ├── iam/
│   │   ├── s3/
│   │   └── postgres/
│   │
│   └── environments/
│       ├── dev/
│       ├── qa/
│       └── prod/
│
├── .github/
│   └── workflows/
│       ├── ci.yml
│       └── cd.yml
│
└── README.md
```

---

# Development Phases

## Phase 1

### Local Development

- Create Spring Boot application
- Create Angular frontend
- Connect PostgreSQL
- Test APIs
- Test UI

---

## Phase 2

### Docker

Create Dockerfiles for

- Backend
- Frontend

Create Docker Compose

Services

- Backend
- Frontend
- PostgreSQL
- pgAdmin

Verify everything runs locally using Docker Compose.

---

## Phase 3

### Kubernetes

Deploy application to Kubernetes using

- Kind or Minikube
- Helm Charts

Resources

- Deployment
- Service
- ConfigMap
- Secret
- Persistent Volume
- Ingress

---

## Phase 4

### Infrastructure as Code

Provision infrastructure using

- Terraform
- Terragrunt

Provision

- ECR
- IAM
- S3
- CloudWatch

using LocalStack.

---

## Phase 5

### CI Pipeline

GitHub Actions

Pipeline

```
Checkout Source

↓

Setup Java

↓

Setup Node

↓

Run Backend Tests

↓

Run Frontend Tests

↓

Build Spring Boot

↓

Build Angular

↓

Build Docker Images

↓

Push Images
```

---

## Phase 6

### CD Pipeline

```
Terraform Apply

↓

Helm Upgrade

↓

Deploy to Kubernetes

↓

Health Check

↓

Smoke Test
```

---

# Docker Components

- Backend Container
- Frontend Container
- PostgreSQL
- pgAdmin

Future

- Prometheus
- Grafana

---

# Helm Structure

```
helm/

    Chart.yaml

    values.yaml

    templates/

        deployment.yaml

        service.yaml

        ingress.yaml

        configmap.yaml

        secret.yaml

        pvc.yaml
```

Example values.yaml

```yaml
image:
  repository: todo-api
  tag: latest

service:
  port: 8080

database:
  host: postgres
  port: 5432
```

---

# Terraform Modules

```
modules/

    ecr/

    eks/

    iam/

    s3/

    postgres/
```

Terragrunt environments

```
environments/

    dev/

    qa/

    prod/
```

---

# CI/CD Workflow

## Continuous Integration

- Checkout repository
- Install Java
- Install Node.js
- Build backend
- Build frontend
- Run unit tests
- Build Docker images
- Publish Docker images

---

## Continuous Deployment

- Provision infrastructure
- Deploy Helm release
- Verify deployment
- Run smoke tests

---

# Learning Outcomes

## Spring Boot

- REST APIs
- Security
- JWT
- Validation
- Exception Handling
- Flyway
- Actuator

## Angular

- Routing
- Reactive Forms
- Authentication
- HTTP Interceptors
- Angular Material

## Docker

- Dockerfile
- Docker Compose
- Volumes
- Networks
- Multi-stage Build

## Kubernetes

- Deployment
- Service
- ConfigMap
- Secret
- Persistent Volume
- Ingress

## Helm

- Charts
- Values
- Templates
- Release Management
- Rollbacks

## Terraform

- Resources
- Variables
- Outputs
- Modules
- State Management

## Terragrunt

- Environment Management
- Reusable Infrastructure
- Dependencies

## GitHub Actions

- CI Pipelines
- CD Pipelines
- Secrets
- Docker Automation

---

# Project Milestones

| Milestone | Goal |
|------------|------|
| 1 | Develop Spring Boot CRUD API |
| 2 | Integrate PostgreSQL with Flyway |
| 3 | Build Angular Frontend |
| 4 | Dockerize Backend and Frontend |
| 5 | Docker Compose Local Setup |
| 6 | Deploy to Kubernetes using Helm |
| 7 | Provision Infrastructure with Terraform |
| 8 | Configure LocalStack Services |
| 9 | Deploy using Helm |
| 10 | Automate using GitHub Actions |

---

# Important Notes

- Start by validating the application locally before introducing infrastructure automation.
- Use Docker Compose to ensure all services communicate correctly.
- Deploy to a local Kubernetes cluster (Kind or Minikube) before integrating LocalStack.
- Use LocalStack primarily for AWS service emulation (ECR, S3, IAM, CloudWatch).
- Once local deployment is stable, implement Terraform/Terragrunt for infrastructure provisioning.
- Finally, automate the complete build and deployment process with GitHub Actions.

---

# End Goal

Build a production-inspired, end-to-end DevOps project that demonstrates:

- Full Stack Application Development
- Docker Containerization
- Kubernetes Deployment
- Helm Chart Management
- Infrastructure as Code
- Local AWS Simulation
- CI/CD Automation
- Production-grade Project Structure

This project should serve as both a comprehensive learning exercise and a portfolio-ready showcase of modern Java Full Stack and DevOps practices.