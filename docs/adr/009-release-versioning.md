# ADR-009: Release Versioning with CI Auto-Bump

**Status:** Accepted

**Context:**
Published artifacts (container images) must carry a clean release version
(e.g. `0.0.1`) while development continues on a snapshot
(e.g. `0.0.1-SNAPSHOT`). Manual version bumping is error-prone and easy to
forget. Each component needs its own version, with a single source of truth.

**Decision:**
- Version source of truth: `backend/pom.xml` `<revision>` and
  `frontend/package.json` `version`, always kept as `X.Y.Z-SNAPSHOT` on
  `develop`.
- On push to `main`, `deploy.yml` derives the release version by stripping
  `-SNAPSHOT` (e.g. `0.0.1-SNAPSHOT` → `0.0.1`) and tags images
  `<version>` / `<sha>` / `latest`.
- The backend image is built with `REVISION=<version>` (a Docker build arg),
  so `/api/v1/ping` reports the release version. The backend jar is always
  `app.jar` via a Maven `<finalName>`.
- After a successful build, the `bump-develop` job advances `develop` to the
  next snapshot by bumping the patch (`0.0.1-SNAPSHOT` → `0.0.2-SNAPSHOT`) and
  pushes the change directly to `develop` (ADR-008).

**Consequences:**

- Every image tag is traceable to a version and a commit; rollback = redeploy a
  previous `<version>` tag.
- No manual bumping: `develop` is automatically one release ahead.
- `develop` and `main` version files diverge by design; the next release merge
  (`develop` → `main`) may conflict on the version files and resolves to
  `develop`'s snapshot.
- Bump on `develop` requires `contents: write` scope on the workflow's job.
- Requires a PR-based release flow so `main` never carries `-SNAPSHOT` into a
  release build.
