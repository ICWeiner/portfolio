---
title: "Portfolio Platform"
summary: "This website and the full platform behind it — built, deployed, documented, and operated from a single monorepo on a self-hosted homelab."
date: 2026-08-13
stack: ["Astro", "Spring Boot", "Docker", "Traefik", "GitHub Actions"]
status: "in-progress"
links:
  live: "https://diogonunes.dev"
  repo: "https://github.com/diogonunes/portfolio"
---

## The idea

The website is not the project. The project is the entire platform: source code,
deployment pipeline, infrastructure, and documentation — all versioned in one
monorepo and running on a self-hosted homelab behind Traefik.

## Architecture

The system is documented in `docs/architecture.md` and follows a simple, honest
shape:

- **Frontend:** an Astro static site with Markdown content collections.
- **Backend:** a Spring Boot API exposing a health endpoint and a ping endpoint.
- **Runtime:** Docker Compose on a single VM behind Traefik with Let's Encrypt TLS.
- **Pipeline:** GitHub Actions builds and tests on hosted runners, then a
  self-hosted runner on the VM deploys via `docker compose`.

## Decisions

Every consequential decision is recorded as an Architecture Decision Record in
`docs/adr/` — why Astro, why Spring Boot, why no database in the MVP, why Docker
Compose over Kubernetes, and why a self-hosted runner for deploys.

## Lessons learned

Being written live as the platform grows. The blog post
[Building This Portfolio Platform](/blog/building-the-portfolio) covers why the
project exists and what the MVP is scoped to.
