package com.pokedex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokedex.cache.PokemonCache;
import com.pokedex.client.PokeApiClient;
import com.pokedex.model.PokemonResponse;

@Service
public class PokemonService {

    @Autowired
    private PokemonCache cache;

    @Autowired
    private PokeApiClient client;

    public Object getPokemon(String name) {

        String key = "pokemon_" + name.toLowerCase();

        // check cache
        Object cached = cache.get(key);
        if (cached != null) {
            return cached;
        }

        // call external API
        Map raw = (Map) client.fetchPokemon(name);

        // map to our model
        PokemonResponse response = new PokemonResponse();
        response.setId((Integer) raw.get("id"));
        response.setName((String) raw.get("name"));
        response.setHeight((Integer) raw.get("height"));
        response.setWeight((Integer) raw.get("weight"));

        // image URL
        Map sprites = (Map) raw.get("sprites");
        response.setImageUrl((String) sprites.get("front_default"));

        // types
        List<Map> typesList = (List<Map>) raw.get("types");
        List<String> types = new ArrayList<>();
        for (Map t : typesList) {
            Map typeObj = (Map) t.get("type");
            types.add((String) typeObj.get("name"));
        }
        response.setTypes(types);

        // stats
        List<Map> statsList = (List<Map>) raw.get("stats");
        List<String> stats = new ArrayList<>();
        for (Map s : statsList) {
            Map statObj = (Map) s.get("stat");
            stats.add(statObj.get("name") + ": " + s.get("base_stat"));
        }
        response.setStats(stats);

        // abilities
        List<Map> abList = (List<Map>) raw.get("abilities");
        List<String> abilities = new ArrayList<>();
        for (Map a : abList) {
            Map abilityObj = (Map) a.get("ability");
            abilities.add((String) abilityObj.get("name"));
        }
        response.setAbilities(abilities);

        // moves
        List<Map> movesList = (List<Map>) raw.get("moves");
        List<String> moves = new ArrayList<>();
        for (Map m : movesList) {
            Map moveObj = (Map) m.get("move");
            moves.add((String) moveObj.get("name"));
        }
        response.setMoves(moves);

        // store to cache
        cache.set(key, response);

        return response;
    }
}
