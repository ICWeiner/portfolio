# ADR-004: Docker Compose over Kubernetes

**Status:** Accepted

**Context:**
The platform runs on a single Proxmox VM for a solo operator. The MVP stack is
small (Traefik, frontend, backend). Kubernetes would add operational overhead far
beyond the current need.

**Decision:**
Use Docker Compose on one VM for the MVP, with Traefik as the entry point.

**Consequences:**

- Simple, predictable deployment via `docker compose pull/up`.
- Fast iteration and low operational burden.
- Fewer concepts to maintain and document.
- Revisit if the platform outgrows a single host or needs self-healing/scale.
