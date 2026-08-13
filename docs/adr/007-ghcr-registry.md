# ADR-007: GHCR as the Container Registry with Git-SHA Tags

**Status:** Accepted

**Context:**
Build artifacts need to travel from CI to the deployment host. The image
registry must be private, co-located with the source repository, and support
traceability of any running image back to a commit.

**Decision:**
Use GitHub Container Registry (GHCR) as the image registry. Images are pushed
from GitHub Actions using the built-in `GITHUB_TOKEN` (no separate
credentials) and tagged with the git SHA, plus `latest` for convenience. The
deploy workflow passes the SHA as `IMAGE_TAG` to `docker compose`.

**Consequences:**

- No external registry account or credential to manage; auth reuses GitHub's
  token with `packages: write` scope.
- Every deployed image is traceable to a commit; rollback = redeploy a
  previous SHA.
- Private by default; visible to collaborators only.
- Images are coupled to GitHub as the CI provider.
