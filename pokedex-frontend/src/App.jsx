import { useState } from "react";
import { fetchPokemon } from "./api";
import PokemonCard from "./components/PokemonCard";
import "./index.css";

function App() {
  const [query, setQuery] = useState("");
  const [pokemon, setPokemon] = useState(null);
  const [error, setError] = useState("");

  const searchPokemon = async () => {
    if (!query.trim()) return;

    try {
      setError("");
      const res = await fetchPokemon(query);
      setPokemon(res.data);
    } catch (err) {
      setPokemon(null);
      setError("Pokémon not found");
    }
  };

  return (
    <div className="container">
      <h1 className="title">Pokédex</h1>

      <div className="search-box">
        <input
          type="text"
          placeholder="Search Pokémon (e.g., pikachu)"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />
        <button onClick={searchPokemon}>Search</button>
      </div>

      {error && <p className="error">{error}</p>}

      {pokemon && <PokemonCard data={pokemon} />}
    </div>
  );
}

export default App;
