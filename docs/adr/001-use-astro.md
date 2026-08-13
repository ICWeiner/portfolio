# ADR-001: Use Astro for the Frontend

**Status:** Accepted

**Context:**
The portfolio needs a content-driven site (projects, blog, CV) with Markdown as
the content source. It must be fast, easy to maintain, and simple to deploy and
operate on the homelab. A heavy SPA framework is unnecessary for static pages.

**Decision:**
Use Astro with content collections for the frontend, building a static site
served by a small nginx container.

**Consequences:**

- Simple, fast static output — easy to serve and cache.
- Content stays in versioned Markdown files.
- No client-side framework complexity for MVP pages.
- Adds a Node/Astro build step to the pipeline.
