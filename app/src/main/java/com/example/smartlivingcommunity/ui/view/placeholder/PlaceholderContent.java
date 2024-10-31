package com.example.smartlivingcommunity.ui.view.placeholder;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderContent {

    public static final List<PlaceholderItem> ITEMS = new ArrayList<>();

    static {
        // Add some sample items.
        for (int i = 1; i <= 10; i++) {
            ITEMS.add(new PlaceholderItem(String.valueOf(i), "Content " + i));
        }
    }

    public static class PlaceholderItem {
        public final String id;
        public final String content;

        public PlaceholderItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
