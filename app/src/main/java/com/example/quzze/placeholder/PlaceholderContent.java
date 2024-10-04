package com.example.quzze.placeholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<PlaceholderItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, PlaceholderItem> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            // Assuming the score and total values are randomly generated or fixed
            int score = (int) (Math.random() * 4);  // Random score between 0 and 100
            int total = 4;  // Total fixed as 100 for now
            String continent = getContinentForPosition(i);  // Get continent based on position

            addItem(new PlaceholderItem(String.valueOf(i), score, total, continent));
        }
    }

    private static void addItem(PlaceholderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static String getContinentForPosition(int position) {
        // Example logic to assign a continent based on position
        switch (position % 5) {
            case 1: return "North America";
            case 2: return "Europe";
            case 3: return "Asia";
            case 4: return "Africa";
            default: return "Australia";
        }
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem {
        public final String id;
        public final int score;
        public final int total;
        public final String continent;

        public PlaceholderItem(String id, int score, int total, String continent) {
            this.id = id;
            this.score = score;
            this.total = total;
            this.continent = continent;
        }

        public String getId() {
            return id;
        }

        public int getScore() {
            return score;
        }

        public int getTotal() {
            return total;
        }

        public String getContinent() {
            return continent;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Score: " + score + "/" + total + ", Continent: " + continent;
        }
    }
}
