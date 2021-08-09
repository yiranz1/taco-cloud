package com.yiran.tacocloud.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiran.tacocloud.models.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

@Repository
public class JdbcTacoRepository implements TacoRepository {
    private final JdbcTemplate jdbc;
    private final ObjectMapper objectMapper;
    private final SimpleJdbcInsert tacoInserter;

    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.objectMapper = new ObjectMapper();
        this.tacoInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (String ingredient: taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }

        return taco;
    }
    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        Map<String, Object> values = objectMapper.convertValue(taco, Map.class);
        values.put("createdAt", taco.getCreatedAt());
        return tacoInserter.executeAndReturnKey(values).longValue();
    }

    private void saveIngredientToTaco(String ingredient, long tacoId) {
        jdbc.update(
                "INSERT INTO Taco_Ingredients (taco, ingredient) " +
                        "VALUES (? , ?)", tacoId, ingredient);
    }
}
