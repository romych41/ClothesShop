package com.kpi.korolova.shop.model;

public enum Category {
    UNDEFINED("Прочее", "");
    private final String description;
    private final String parent;

    public static Category fromDescription(String description) {
        for(Category category : Category.values()) {
            if(category.getDescription().equals(description)) {
                return category;
            }
        }
        return UNDEFINED;
    }

    Category(String description, String parent) {
        this.description = description;
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public String getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "Category{" +
                "description='" + description + '\'' +
                ", parent='" + parent + '\'' +
                '}';
    }
}
