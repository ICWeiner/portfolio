# ADR-002: Use Spring Boot for the API

**Status:** Accepted

**Context:**
The project exists to demonstrate operating production software end to end. A
real JVM backend exercises the deployment pipeline (build, test, containerize,
deploy, health-check, route) in ways a static site alone cannot. For the MVP the
API only needs a health endpoint and one demo endpoint.

**Decision:**
Use Spring Boot 4.0.x (Java 21) as a containerized API behind Traefik, with an
actuator health endpoint (including liveness/readiness probes) and
`GET /api/v1/ping`. The `prod` profile enables structured logstash-format JSON
logging to stdout, via Spring's built-in `logging.structured.format`.

**Consequences:**

- Demonstrates production Spring Boot operation rather than a static site alone.
- Adds a heavier container and Java build to the stack.
- Kept intentionally minimal: no DB and no content duplication.
- Pinned to Spring Boot 4.x — the modularized starters (e.g.
  `spring-boot-starter-webmvc`) and moved test packages differ from 3.x.
- Easily removed if it stops earning its keep.
