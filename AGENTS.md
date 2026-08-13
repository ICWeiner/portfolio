# AGENTS.md

Guidance for AI coding agents working in this repository.

## Project

Personal portfolio & homelab showcase. The website is the visible surface; the
deployment, operation, and documentation of the whole platform are the project.

Read these before making architectural decisions:

- `docs/vision.md` — why the project exists and who it is for.
- `docs/architecture.md` — system design, components, CI/CD, security.
- `docs/master-plan.md` — phase-by-phase roadmap with checklists.
- `docs/adr/` — Architecture Decision Records. Follow the existing format
  (`Status / Context / Decision / Consequences`) when recording a new decision.

## Repo layout (monorepo)

Planned structure; directories are created as their phase lands.

```text
frontend/          Astro site (static build, markdown content collections)
backend/           Spring Boot API (Java 21)
infrastructure/    Docker Compose stack, Traefik config, VM provisioning
docs/              vision, architecture, master-plan, ADRs
.github/workflows/ GitHub Actions pipeline
```

## Key decisions

- **Monorepo** — single repo, one pipeline.
- **Frontend:** Astro, static output served by nginx (ADR-001).
- **Backend:** Spring Boot, no database in MVP, `/actuator/health` +
  `GET /api/v1/ping` (ADR-002, ADR-003).
- **Runtime:** Docker Compose on a single Proxmox VM (ADR-004).
- **CI/CD:** GitHub Actions; GitHub-hosted runners build/test, a
  **self-hosted runner on the VM** deploys via `docker compose` (ADR-005).
- **Registry:** GHCR; images tagged with the git SHA.
- VM exposes only ports `80`/`443` (Traefik). Everything else stays internal.

## Conventions

- Keep `docs/` in sync with code changes. Update architecture.md when the
  system changes; record consequential decisions as ADRs.
- Use Mermaid diagrams for system/flow documentation.
- Follow existing doc style: concise bullets, code blocks for config/diagrams.
- Never commit secrets. Credentials live in GitHub Actions secrets or
  environment variables.
- No comments in code unless asked.

## Commands

### Frontend (`frontend/`)

```bash
npm install        # install dependencies
npm run dev        # start dev server
npm run build      # static build -> dist/
npm run preview    # preview the production build
docker build -t portfolio-frontend .   # build the nginx image
```

Backend (`backend/`) — Spring Boot, Java 21, Maven wrapper:

```bash
./mvnw test          # run tests
./mvnw package       # build the jar (test-free: use -DskipTests)
./mvnw spring-boot:run   # run locally (default port 8080)
docker build -t portfolio-backend .   # build the JRE image
```

The `prod` profile enables structured JSON logging
(`logging.structured.format.console: logstash`).
