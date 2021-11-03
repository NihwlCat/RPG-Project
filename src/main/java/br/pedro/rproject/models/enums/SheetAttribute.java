package br.pedro.rproject.models.enums;

public enum SheetAttribute {
    CONSTITUTION ("CONSTITUIÇÃO"),
    STRENGTH ("FORÇA"),
    DEXTERITY ("DESTREZA"),
    SIZE ("TAMANHO"),
    INTELLIGENCE ("INTELIGÊNCIA"),
    APPEARANCE ("APARÊNCIA"),
    POWER ("PODER"),
    EDUCATION ("EDUCAÇÃO"),
    DODGE ("ESQUIVAR"),
    PERSUADE ("PERSUADIR"),
    LISTEN ("ESCUTAR"),
    SEARCH ("PROCURAR"),
    PSYCHOLOGY ("PSICOLOGIA"),
    STEALTH ("FURTIVIDADE"),
    THROW ("ARREMESSAR"),
    AUTOMOBILES ("PILOTAGEM"),
    SOS ("SOS"),
    OTHERS ("OUTRAS"),
    PRIMARY ("PRIMÁRIA"),
    STRUGGLE ("BRIGA"),
    SECONDARY ("SECUNDÁRIA");

    private final String value;

    SheetAttribute(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SheetAttribute fromString(String text) {
        for (SheetAttribute att : SheetAttribute.values()) {
            if (att.value.equalsIgnoreCase(text)) {
                return att;
            }
        }
        return null;
    }
}
