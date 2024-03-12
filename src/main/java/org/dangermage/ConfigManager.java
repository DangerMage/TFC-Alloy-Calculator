package org.dangermage;


import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.file.FileConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    public void saveResource(File outputDirectory, String name, boolean replace)
            throws IOException {
        File out = new File(outputDirectory, name);
        if (!replace && out.exists())
            return;
        // Step 1:
        InputStream resource = this.getClass().getResourceAsStream(name);
        if (resource == null)
            throw new FileNotFoundException(name + " (resource not found)");
        // Step 2 and automatic step 4
        try(InputStream in = resource;
            OutputStream writer = new BufferedOutputStream(
                    new FileOutputStream(out))) {
            // Step 3
            byte[] buffer = new byte[1024 * 4];
            int length;
            while((length = in.read(buffer)) >= 0) {
                writer.write(buffer, 0, length);
            }
        }
    }

    public List<Alloy> readConfig(FileConfig config) {
        List<Alloy> definedAlloys = new ArrayList<>();

        if (!config.contains("alloys")) {
            return null;
        }

        ArrayList<Config> alloys = config.get("alloys");

        for (Config alloy : alloys) {

            List<Material> parsedMaterials = new ArrayList<>();

            String name = alloy.get("name");

            ArrayList<Config> materials = alloy.get("components");

            for (Config material : materials) {
                String materialName = material.get("material");
                int minPercentWhole = material.get("minPercentage");
                int maxPercentWhole = material.get("maxPercentage");

                double minPercent = (double) minPercentWhole / 100;

                double maxPercent = (double) maxPercentWhole / 100;


                Material parsedMaterial = new Material(materialName, minPercent, maxPercent);

                parsedMaterials.add(parsedMaterial);
            }

            Alloy parsedAlloy = new Alloy(name, parsedMaterials);

            definedAlloys.add(parsedAlloy);


        }

        return definedAlloys;
    }

}
