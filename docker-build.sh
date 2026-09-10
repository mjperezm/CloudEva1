#!/bin/bash
set -e
echo "========================================="
echo "Pedidos360 - Docker Build & Run Script"
echo "========================================="
ENV=${1:-dev}
if [ "$ENV" != "dev" ] && [ "$ENV" != "prod" ]; then
    echo "Error: Environment must be 'dev' or 'prod'"
    exit 1
fi
COMPOSE_FILE="docker-compose.yml"
[ "$ENV" = "prod" ] && COMPOSE_FILE="docker-compose.prod.yml"
echo "Building for: $ENV"
docker-compose -f $COMPOSE_FILE build --no-cache
echo "Build complete!"
