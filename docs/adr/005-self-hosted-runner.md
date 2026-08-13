# ADR-005: Self-Hosted GitHub Actions Runner for Deploys

**Status:** Accepted

**Context:**
The platform is deployed to a single VM on the homelab behind a firewall that
only exposes ports `80`/`443`. The deployment pipeline must reach that VM from
GitHub Actions without weakening the firewall. Options considered:

- **SSH deploy** — requires opening port `22` inbound, restricted to GitHub's
  IP ranges; adds firewall rules and a wider attack surface.
- **VPN (e.g. Tailscale)** — VM joins an overlay network; secure, but another
  service to install and operate.
- **Self-hosted runner** — a GitHub Actions runner installed on the VM connects
  outbound to GitHub, so no inbound ports are needed at all.

**Decision:**
Use a self-hosted GitHub Actions runner on the Portfolio Host VM. Build and
test jobs run on GitHub-hosted runners; the deploy job runs on the self-hosted
runner and executes `docker compose pull` / `docker compose up -d` locally.

**Consequences:**

- No inbound ports beyond `80`/`443`; the firewall stays strict.
- The deploy job runs directly against local Docker and the compose stack.
- The runner holds access to the repository; must be restricted to a personal
  project (not shared), registered per-repo, and its secrets protected.
- Registration token and GHCR credentials are stored in GitHub Actions secrets,
  never in the repository.
