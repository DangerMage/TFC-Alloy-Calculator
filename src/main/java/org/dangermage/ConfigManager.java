package org.dangermage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigManager {
    private static void ensureConfigExists(String filePath) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            try {
                Files.copy(TFCAlloyCalc.class.getResourceAsStream("/defaultConfig.json"), path);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create default configuration file.", e);
            }
        }
    }
}
