package com.pokedex.controller;

import com.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon")
@CrossOrigin(origins = "*")
public class PokemonController {

    @Autowired
    private PokemonService service;

    @GetMapping("/{name}")
    public Object getPokemon(@PathVariable String name) {
        return service.getPokemon(name);
    }
}

