# Pokemon Search Engine — Pokedex

## Overview
Spring Boot backend (pokedex-backend) + React + Vite frontend (pokedex-frontend).
Backend fetches data from PokeAPI and caches responses (in-memory TTL + max entries).
Frontend calls backend via `VITE_API_BASE`.

## Run locally

### Backend
cd pokedex-backend
./mvnw spring-boot:run
# or build and run jar
mvn -DskipTests package
java -jar target/pokedex-backend-0.0.1-SNAPSHOT.jar

By default backend listens on port from env variable `PORT` or fallback `7272`.

### Frontend
cd pokedex-frontend
npm install
export VITE_API_BASE="http://localhost:7272/api/pokemon"
npm run dev

## API
GET /api/pokemon/{name}
Response: JSON with pokemon details (sprite, types, stats, abilities, base experience, etc.)
Errors: 404 if not found, 500 on upstream error.

## Caching
- Type: in-memory cache
- TTL: 1 hour (example)
- Max entries: 500 (LRU eviction)
- Behavior: cache on successful vendor response; cache keys = lowercase pokemon name

## Repo
https://github.com/Krantikumar4211/pokedex-fullstack

