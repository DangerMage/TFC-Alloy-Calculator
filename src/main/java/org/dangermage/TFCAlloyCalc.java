package org.dangermage;


import com.electronwill.nightconfig.core.file.FileConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TFCAlloyCalc {

    private static final ConfigManager configManager = new ConfigManager();

    public static void main( String[] args ) {

        //Check if config exists, if not create it then load it
        File configFile = new File("config/alloys.json");

        if (!configFile.exists()) {
            try {
                Files.createDirectories(Path.of("config"));
                configManager.saveResource(new File("config"), "alloys.json", true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        // load config into a record list
        FileConfig config = FileConfig.of(configFile);
        config.load();

        List<Alloy> alloys = configManager.readConfig(config);



    }
}
