package com.pokedex.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokeApiClient {

    @Value("${pokeapi.baseurl}")
    private String baseUrl;

    private RestTemplate rest = new RestTemplate();

    public Object fetchPokemon(String name) {
        String url = baseUrl + name.toLowerCase();
        return rest.getForObject(url, Object.class);
    }
}

