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

The compose stack, Traefik config, and deployment pipeline are already
implemented in the repo. What remains is hands-on setup, in order:

## Next Steps (ordered)

- [x] Push the repository to GitHub
- [ ] Purchase the domain (e.g. `diogonunes.dev`)
- [ ] Create the VM on Proxmox (Debian 12) — the "Portfolio Host"
- [ ] Install Docker Engine on the VM
- [ ] Register the self-hosted GitHub Actions runner
- [ ] Set up `/opt/portfolio/infrastructure` with a real `.env` (DOMAIN, ACME_EMAIL)
- [ ] Point DNS: apex / `www` → VM, `api` → VM
- [ ] Push to `main` and confirm the first automated deploy
- [ ] Verify external HTTPS, `/api/v1/ping`, `/actuator/health`
- [ ] Review firewall rules (only `80`/`443` exposed)
- [ ] Replace the fixed `sleep 10` in the deploy job (`.github/workflows/deploy.yml`)
      with a proper readiness wait — poll `/actuator/health` with retries/backoff
      until `UP`, or use a compose `healthcheck` +
      `depends_on.condition: service_healthy`

VM/runner details: `infrastructure/README.md`.

### Proposed Structure

```text
diogonunes.dev

www.diogonunes.dev
api.diogonunes.dev
grafana.diogonunes.dev
status.diogonunes.dev
```

---

## Documentation

- [x] Create infrastructure diagram   (architecture.md)
- [x] Document deployment flow         (architecture.md §6, infrastructure/README.md)
- [ ] Create network diagram

---

# Phase 2 - Source Control & CI/CD

**Decisions (see ADRs):**

- Monorepo (ADR-004 context / architecture.md)
- GitHub Actions with a self-hosted runner on the VM (ADR-005)
- GHCR as the container registry (ADR-007)
- Branch model: protected `main` + `develop` (ADR-008)
- Release versioning with CI auto-bump (ADR-009)

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

## Branching & Versioning

- [x] Branch model decided: `main` (release) + `develop` (integration) (ADR-008)
- [ ] Branch protection configured on `main` (web UI: require PR + required
      checks, block force-push)
- [x] `ci.yml` runs frontend build + backend tests on PRs to `main`/`develop`
      and on pushes to `develop`
- [x] Release versioning: images tagged `<version>`/`<sha>`/`latest`;
      `bump-develop` advances `develop` to the next `-SNAPSHOT` (ADR-009)

---

## Frontend Pipeline

- [x] Automatic build on push
- [x] Deployment automation
- [x] Build validation

---

## Backend Pipeline

- [x] Automatic build
- [x] Unit tests
- [x] Container image build
- [x] Deployment automation

---

## Secrets Management

- [x] No secrets committed
- [x] Deployment credentials protected
- [x] Document secret handling

Deployment model: `ci.yml` validates pull requests (frontend build + backend
tests); `deploy.yml` runs on push to `main` — GitHub-hosted runners build,
test, and push images, then a self-hosted runner on the VM pulls them and runs
`docker compose up -d`. Only `80`/`443` are exposed on the VM. Implemented in
`.github/workflows/` (ADR-005, ADR-007).

---

# Phase 3 - Frontend (Astro)

## Core Website

- [x] Landing page
- [x] Responsive layout
- [x] Navigation
- [x] Footer
- [x] Contact links

---

## About Page

- [x] Professional summary
- [x] Career timeline
- [x] Technical skills
- [x] Interests and focus areas

---

## CV Page

- [x] Online CV
- [x] Downloadable PDF  (placeholder until the real CV is added)

---

## Projects Section

- [x] Project listing page
- [x] Individual project pages
- [ ] Screenshots
- [ ] Architecture diagrams
- [x] Lessons learned

---

# Phase 4 - Spring Boot API

## Foundation

- [x] Create Spring Boot application
- [x] Containerize application
- [x] Health endpoint

---

## API Design

- [x] API versioning strategy

Example:

```text
/api/v1
```

- [x] DTO layer
- [ ] Request validation  (n/a for MVP — no request bodies yet)
- [ ] OpenAPI documentation

---

## Security

- [ ] Security model defined  (MVP is fully public; revisit if an admin area lands)
- [x] Public endpoints identified
- [ ] Internal endpoints protected  (n/a for MVP — no internal endpoints)

---

## Production Readiness

- [x] Structured logging  (logstash JSON in `prod` profile)
- [ ] Error handling strategy  (default for now; custom handlers later)
- [x] Configuration management  (profiles + env vars)

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

- [x] Markdown posts  (Astro content collections)
- [ ] Categories
- [x] Tags
- [ ] RSS feed

---

## Initial Articles

- [ ] Building My Homelab
- [ ] Migrating to OPNsense
- [ ] Self-Hosting Lessons Learned
- [ ] Running Local LLMs
- [x] Building This Portfolio Platform

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