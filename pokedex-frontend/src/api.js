import axios from "axios";

const API_BASE = "http://localhost:7272/api/pokemon";

export const fetchPokemon = async (name) => {
  return axios.get(`${API_BASE}/${name}`);
};
