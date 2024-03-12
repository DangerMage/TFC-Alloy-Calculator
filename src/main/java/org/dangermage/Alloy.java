package org.dangermage;

import java.util.List;

// Ease of use record for storing the alloy names and material lists

public record Alloy(String name, List<Material> materials) {
}
