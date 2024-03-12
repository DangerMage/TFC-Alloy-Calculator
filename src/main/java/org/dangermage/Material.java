package org.dangermage;

// Ease of use record to store materials and their minimum and maximum use percentages for an alloy

public record Material(String name, double minPercent, double maxPercent) {
}
