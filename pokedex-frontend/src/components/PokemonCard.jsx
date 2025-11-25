import "./PokemonCard.css";

export default function PokemonCard({ data }) {
  return (
    <div className="card">
      <h2>
        {data.name.toUpperCase()} <span className="id">#{data.id}</span>
      </h2>

      <img className="sprite" src={data.imageUrl} alt={data.name} />

      <div className="section">
        <h3>Types:</h3>
        <div className="tags">
          {data.types.map((t) => (
            <span className="tag" key={t}>{t}</span>
          ))}
        </div>
      </div>

      <div className="section">
        <h3>Stats:</h3>
        {data.stats.map((s) => (
          <p key={s}>{s}</p>
        ))}
      </div>

      <div className="section">
        <h3>Abilities:</h3>
        <ul>
          {data.abilities.map((a) => (
            <li key={a}>{a}</li>
          ))}
        </ul>
      </div>

      <div className="section">
        <h3>Moves (first 10):</h3>
        <div className="tags">
          {data.moves.slice(0, 10).map((m) => (
            <span className="tag move" key={m}>{m}</span>
          ))}
        </div>
      </div>
    </div>
  );
}
