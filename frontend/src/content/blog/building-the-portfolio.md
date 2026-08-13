---
title: "Building This Portfolio Platform"
date: 2026-08-13
tags: ["homelab", "self-hosting", "ci-cd", "documentation"]
excerpt: "Why a portfolio that is really a whole platform, and what the MVP is deliberately leaving out."
---

## The plan started as a website

Most portfolios are a static site with a contact button. That shows what you
built, not how you build. The goal here is the opposite: demonstrate the complete
lifecycle of designing, deploying, documenting, and operating software.

So the website is only the presentation layer. The project is the entire system —
and every piece of it is versioned, reproducible, and documented.

## The MVP is intentionally small

- A domain, DNS, HTTPS, Traefik, and a deployment pipeline.
- An Astro site with Home, About, Projects, and CV.
- A Spring Boot API with a health endpoint and one real endpoint.
- One project page and one blog post.

No Grafana, no Prometheus, no homelab dashboard, no AI experiments page. Those
are future phases, not MVP distractions.

## Decisions recorded, not memorized

Every architectural choice is written down as an ADR in `docs/adr/`. When I
revisit this in two years, I should be able to explain not just what the system
is, but why each piece was chosen and what the alternatives were.
