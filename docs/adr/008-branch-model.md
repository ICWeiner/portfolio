# ADR-008: Branch Model (Protected `main` + `develop`)

**Status:** Accepted

**Context:**
The repo needs a production branch that always represents a deployable release
and an integration branch where work accumulates before release. Direct pushes
to the production branch must be forbidden so every release is reviewed. CI
must validate changes before they can land.

**Decision:**
Use a two-branch model:

- `main` — production. Branch protection requires a pull request and passing
  checks; no direct pushes, no force pushes. Merges are releases and trigger
  `deploy.yml`.
- `develop` — integration. Direct pushes are allowed; this is where feature
  work and version bumps land.
- Short-lived feature branches merge into `develop`; releases ship via a PR
  `develop` → `main`.
- `ci.yml` runs frontend build and backend tests on PRs to `main`/`develop`
  and on pushes to `develop`, providing the required status checks.

**Consequences:**

- Every release is reviewed and passes the test suite before reaching `main`.
- `develop` stays one release ahead and carries the next `-SNAPSHOT` version.
- Only `main` deploys to production; `develop` is a CI-validated staging
  ground.
- The version bump job can push directly to `develop` (unprotected), which
  keeps the release pipeline automatic.
- Feature branches should be short-lived to avoid divergence.
