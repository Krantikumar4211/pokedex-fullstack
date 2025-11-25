package com.pokedex.cache;

import com.github.benmanes.caffeine.cache.*;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class PokemonCache {

	    private Cache<String, Object> cache = Caffeine.newBuilder()
	            .maximumSize(300)
	            .expireAfterWrite(10, TimeUnit.MINUTES)
	            .build();

	    public Object get(String key) {
	        return cache.getIfPresent(key);
	    }

	    public void set(String key, Object value) {
	        cache.put(key, value);
	    }
}


