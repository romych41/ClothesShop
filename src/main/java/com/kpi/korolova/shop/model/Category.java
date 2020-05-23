package com.kpi.korolova.shop.model;

public enum Category {
    UNDEFINED               ("Прочее",                  ""),
    BREECHES                ("Бриджи",                  "OMD"),
    OVERALLS                ("Комбез",                  "OMD"),
    COSTUMES                ("Костюм",                  "OMD"),
    MENS_SWEATERS           ("Мужская кофта",           "OMD"),
    MENS_STRAIGHT_TROUSERS  ("Мужские прямые брюки",    "OMD"),
    FOOTWEAR                ("Обувь",                   "OMD"),
    STRAIGHT_PANTS          ("Прямые брюки",            "OMD"),
    SHIRTS                  ("Рубашка",                 "OMD"),
    SWEATSHIRTS             ("Свитшот",                 "OMD"),
    T_SHIRTS                ("Футболка",                "OMD"),
    ROBES                   ("Халат",                   "OMD"),
    PANTS                   ("Брюки",                   "S&W"),
    KITS                    ("Комплект",                "S&W"),
    BLOUSE                  ("Кофта",                   "S&W"),
    DRESS                   ("Платье",                  "S&W"),
    APRON                   ("Фартук",                  "S&W"),
    BROOCHES                ("Брошки",                  "Аксессуары"),
    DJIBITSA                ("Джибитсы",                "Аксессуары"),
    BOXING_WITH_DIARIES     ("Бокс с ежедневниками",    "Полиграфия"),
    PATIENT_CARTS           ("Карта Пациента",          "Полиграфия"),
    BEAUTY_PASSPORT         ("Паспорт Красоты",         "Полиграфия"),
    GLIDERS                 ("Планеры",                 "Полиграфия"),
    ;
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

    public static Category fromContains(String string) {
        for(Category category : Category.values()) {
            if(string.contains(category.description)) {
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
