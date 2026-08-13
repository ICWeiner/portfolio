# ADR-003: No Database in the MVP

**Status:** Accepted

**Context:**
Portfolio content is static Markdown, and the MVP API only returns application
info. There is no persistent, queryable data yet.

**Decision:**
Store content as Markdown files in the frontend and keep the backend
stateless — no database in the MVP.

**Consequences:**

- Simpler deployment.
- Fewer moving parts.
- Faster MVP delivery.
- No backup/state-management burden yet; revisit when a feature actually needs
  persistence (e.g. contact form, homelab status history).
