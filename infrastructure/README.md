# Infrastructure

Docker Compose stack and provisioning docs for the portfolio platform, deployed
to a single "Portfolio Host" VM on Proxmox.

## Layout

```text
infrastructure/
├── docker-compose.yml        Traefik + frontend + backend stack
├── traefik/
│   ├── traefik.yml           static config (entrypoints, ACME resolver)
│   ├── dynamic.yml           dynamic config (security headers middleware)
│   └── certificates/         Let's Encrypt state (acme.json, gitignored)
├── .env.example              template for the real .env (gitignored)
└── README.md
```

## Services

| Service   | Image                                     | Routes                                  |
| --------- | ----------------------------------------- | --------------------------------------- |
| Traefik   | `traefik:v3.7`                            | `:80` → redirect, `:443` → TLS          |
| Frontend  | `ghcr.io/diogonunes/portfolio-frontend`   | `${DOMAIN}`, `www.${DOMAIN}`            |
| Backend   | `ghcr.io/diogonunes/portfolio-backend`    | `api.${DOMAIN}` (health-checked)        |

Only ports `80` and `443` are published to the host; containers talk to each
other on the internal `edge` network. `exposedByDefault: false` in Traefik
means only containers with explicit labels get routes.

## Environment

Copy `.env.example` to `.env` and fill in real values. `.env` is gitignored.

```bash
DOMAIN=diogonunes.dev
ACME_EMAIL=diogo@diogonunes.dev
IMAGE_TAG=latest   # deploy workflow overrides this with the git SHA
```

## Provisioning the VM

Prerequisites: a VM on Proxmox (e.g. Debian 12) with outbound internet and the
repos already pushed to GHCR.

### 1. Install Docker

```bash
curl -fsSL https://get.docker.com | sh
sudo usermod -aG docker "$USER"   # then log out/in
```

### 2. Install the GitHub Actions runner

```bash
mkdir -p ~/actions-runner && cd ~/actions-runner
# From GitHub → repo → Settings → Actions → Runners → New self-hosted runner
# (download + configure the runner package, then run as a service)
./svc.sh install && ./svc.sh start
```

The runner connects outbound to GitHub, so no inbound port is required. Only
`80`/`443` stay open on the firewall.

### 3. Prepare the stack directory

```bash
sudo mkdir -p /opt/portfolio
sudo chown "$USER":"$USER" /opt/portfolio
git clone <repo-url> /opt/portfolio   # or copy infrastructure/ contents
cd /opt/portfolio/infrastructure
cp .env.example .env
nano .env   # set DOMAIN and ACME_EMAIL
```

The deploy workflow keeps this directory in sync (config lives in the repo,
`.env` lives on the VM).

## Deploying

Manually:

```bash
cd /opt/portfolio/infrastructure
docker compose pull
docker compose up -d
```

The GitHub Actions workflow does the same automatically on push to `main`,
tagged with the git SHA.

## Verifying

```bash
curl -I https://diogonunes.dev            # 200, HSTS header present
curl https://diogonunes.dev               # frontend page
curl https://api.diogonunes.dev/api/v1/ping
curl https://api.diogonunes.dev/actuator/health
docker compose ps
```

## Operations

- **Logs:** `docker compose logs -f traefik frontend backend`
- **Update:** push to `main`; or `docker compose pull && docker compose up -d`
- **Certificates:** Let's Encrypt auto-renews; `acme.json` is stateful — back it
  up and never delete it.
- **Backup:** `acme.json` is the only persistent state; everything else rebuilds
  from images.
- **Rollback:** redeploy the previous `IMAGE_TAG` (git SHA).

## Security notes

- Only `80`/`443` are exposed; all other ports stay internal.
- Docker socket is mounted read-only to Traefik only.
- Traefik enforces HTTPS redirects and sends HSTS/security headers.
- `acme.json` holds private keys — keep it on the VM and out of the repo.
