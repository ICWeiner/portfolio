# ADR-006: Traefik Edge Proxy with Let's Encrypt

**Status:** Accepted

**Context:**
The platform needs a single entry point that terminates TLS, serves the
frontend and the API on different subdomains, and keeps everything else
off the network. Certificates must be obtained and renewed automatically.

**Decision:**
Use Traefik v3 as the edge reverse proxy, listening only on ports `80` and
`443`, with Let's Encrypt certificates via the HTTP-01 challenge. Traefik
terminates TLS, redirects all HTTP to HTTPS, and routes by subdomain using
Docker provider labels. Security headers (HSTS + preload, frame deny,
nosniff, referrer/permissions policy) are applied as a shared middleware.
The backend is health-checked by Traefik before being routed.

**Consequences:**

- Single exposed surface: only `80`/`443`; all other container ports stay
  internal to the Docker network.
- Certificates are issued and auto-renewed without manual intervention.
- Routing and middleware live as labels next to the services they serve.
- HTTP-01 challenge requires the domain's DNS to point at the VM before
  certificates can be issued.
- Traefik needs read-only access to the Docker socket.
