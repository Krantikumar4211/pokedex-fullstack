// new - /src/api.js
import axios from "axios";

// Vite environment variable; fallback to local dev backend
const API_BASE = import.meta.env.VITE_API_BASE || "http://localhost:7272/api/pokemon";

export const fetchPokemon = (name) => axios.get(`${API_BASE}/${name}`);
