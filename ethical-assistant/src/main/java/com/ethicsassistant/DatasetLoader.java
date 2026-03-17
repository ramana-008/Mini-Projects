package com.ethicsassistant;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;

public class DatasetLoader {
    public static List<EthicsCase> loadDataset() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = DatasetLoader.class.getResourceAsStream("/ethics_dataset.json")) {
            return mapper.readValue(is, new TypeReference<List<EthicsCase>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load dataset", e);
        }
    }
}
