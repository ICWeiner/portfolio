# Personal Portfolio & Homelab Showcase

## Vision

Build a self-hosted portfolio platform that demonstrates:

- Software engineering skills
- Spring Boot backend development
- Linux administration
- Self-hosting experience
- Networking knowledge
- CI/CD practices
- Infrastructure operation
- AI-assisted development workflows

The website is not the project.

The platform, deployment, operation, maintenance, and documentation are the project.

---

# Phase 0 - Define the Goal

## Success Criteria

- [ ] Own a personal domain
- [ ] Portfolio publicly accessible via HTTPS
- [ ] Self-hosted on homelab
- [ ] Automated deployments
- [ ] Professional About page
- [ ] Downloadable CV
- [ ] Project showcase section
- [ ] Blog section
- [ ] Spring Boot API in production
- [ ] Monitoring and observability
- [ ] Architecture documentation
- [ ] Linked from LinkedIn and CV

---

# Phase 1 - Domain & Infrastructure Foundation

## Domain

- [ ] Purchase personal domain
- [ ] Configure DNS records
- [ ] Configure subdomain strategy

### Proposed Structure

```text
diogonunes.dev

www.diogonunes.dev
api.diogonunes.dev
grafana.diogonunes.dev
status.diogonunes.dev
```

---

## Network & Hosting

- [ ] Create dedicated portfolio service
- [ ] Deploy on Proxmox
- [ ] Configure Traefik reverse proxy
- [ ] Configure Let's Encrypt certificates
- [ ] Configure HTTPS redirects
- [ ] Verify external accessibility
- [ ] Review firewall rules
- [ ] Document exposure decisions

---

## Documentation

- [ ] Create infrastructure diagram
- [ ] Create network diagram
- [ ] Document deployment flow

---

# Phase 2 - Source Control & CI/CD

**Decisions (see ADRs):**

- Monorepo (ADR-004 context / architecture.md)
- GitHub Actions with a self-hosted runner on the VM (ADR-005)
- GHCR as the container registry

## Repository Strategy

Choose one:

### Monorepo ✅

```text
portfolio/
├── frontend/
├── backend/
├── infrastructure/
└── docs/
```

### Multi Repository

```text
portfolio-frontend
portfolio-api
portfolio-infrastructure
```

---

## Frontend Pipeline

- [ ] Automatic build on push
- [ ] Deployment automation
- [ ] Build validation

---

## Backend Pipeline

- [ ] Automatic build
- [ ] Unit tests
- [ ] Container image build
- [ ] Deployment automation

---

## Secrets Management

- [ ] No secrets committed
- [ ] Deployment credentials protected
- [ ] Document secret handling

Deployment model: GitHub-hosted runners build and test; a self-hosted runner on
the VM pulls the images and runs `docker compose up -d`. Only `80`/`443` are
exposed on the VM.

---

# Phase 3 - Frontend (Astro)

## Core Website

- [ ] Landing page
- [ ] Responsive layout
- [ ] Navigation
- [ ] Footer
- [ ] Contact links

---

## About Page

- [ ] Professional summary
- [ ] Career timeline
- [ ] Technical skills
- [ ] Interests and focus areas

---

## CV Page

- [ ] Online CV
- [ ] Downloadable PDF

---

## Projects Section

- [ ] Project listing page
- [ ] Individual project pages
- [ ] Screenshots
- [ ] Architecture diagrams
- [ ] Lessons learned

---

# Phase 4 - Spring Boot API

## Foundation

- [ ] Create Spring Boot application
- [ ] Containerize application
- [ ] Health endpoint

---

## API Design

- [ ] API versioning strategy

Example:

```text
/api/v1
```

- [ ] DTO layer
- [ ] Request validation
- [ ] OpenAPI documentation

---

## Security

- [ ] Security model defined
- [ ] Public endpoints identified
- [ ] Internal endpoints protected

---

## Production Readiness

- [ ] Structured logging
- [ ] Error handling strategy
- [ ] Configuration management

---

# Phase 5 - Homelab Integration

## Homelab Page

- [ ] Hardware overview
- [ ] Service inventory
- [ ] Network architecture
- [ ] Infrastructure documentation

---

## Service Status API

Example:

```text
/api/v1/homelab/services
```

- [ ] Service inventory endpoint
- [ ] Service status endpoint

---

## Optional Live Data

- [ ] Uptime information
- [ ] Resource utilization
- [ ] Availability statistics

Only expose information safe for public viewing.

---

# Phase 6 - Blog

## Blogging System

- [ ] Markdown posts
- [ ] Categories
- [ ] Tags
- [ ] RSS feed

---

## Initial Articles

- [ ] Building My Homelab
- [ ] Migrating to OPNsense
- [ ] Self-Hosting Lessons Learned
- [ ] Running Local LLMs
- [ ] Building This Portfolio Platform

---

# Phase 7 - Observability

## Metrics

- [ ] Application metrics
- [ ] Container metrics
- [ ] Host metrics

---

## Monitoring

- [ ] Grafana dashboards
- [ ] Health monitoring
- [ ] Alerting strategy

---

## Logging

- [ ] Centralized logging
- [ ] Log retention policy

---

# Phase 8 - Reliability

## Backups

- [ ] Portfolio backup strategy
- [ ] API backup strategy
- [ ] Documentation backup strategy

---

## Recovery

- [ ] Recovery procedure documented
- [ ] Restore test completed

---

# Phase 9 - Professional Presentation

## LinkedIn

- [ ] Add portfolio URL
- [ ] Add featured projects

---

## CV

- [ ] Add portfolio domain
- [ ] Add project links

---

## Project Documentation Standard

Every project page should answer:

- [ ] What problem existed?
- [ ] Why was this solution chosen?
- [ ] What alternatives were considered?
- [ ] What challenges appeared?
- [ ] What was learned?

---

# Stretch Goals

## AI Section

- [ ] AI tooling page
- [ ] Opencode workflow
- [ ] Local LLM experiments
- [ ] Model comparison notes

---

## Authentication

- [ ] Admin area
- [ ] Protected endpoints

---

## Infrastructure as Code

- [ ] Terraform usage
- [ ] Automated provisioning

---

## Public API

- [ ] Public portfolio API
- [ ] API documentation page

---

# Final Result

A visitor should be able to discover:

1. Who I am.
2. My professional experience.
3. My backend engineering skills.
4. My homelab and infrastructure experience.
5. My deployment and operational knowledge.
6. My AI-assisted development workflow.
7. My ability to document, maintain, and operate production systems.

The objective is not to build a website.

The objective is to demonstrate the complete lifecycle of designing, building, deploying, documenting, monitoring, and maintaining software and infrastructure.