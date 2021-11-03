package br.pedro.rproject.models.enums;

public enum ItemType {
    RANGED_WEAPON ("ARMA RANGED"),
    MELEE_WEAPON ("ARMA MELEE"),
    COMMON ("COMUM");

    private final String value;

    ItemType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ItemType fromString(String text) {
        for (ItemType att : ItemType.values()) {
            if (att.value.equalsIgnoreCase(text)) {
                return att;
            }
        }
        return null;
    }
}
