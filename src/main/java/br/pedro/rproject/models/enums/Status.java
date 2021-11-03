package br.pedro.rproject.models.enums;

public enum Status {
    ALIVE ("VIVO"),
    DEAD ("MORTO"),
    INJURED ("FERIDO"),
    UNCONSCIOUS ("INCONSCIENTE"),
    TRAUMATIZED ("TRAUMATIZADO");

    private final String value;

    Status(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Status fromString(String text) {
        for (Status status : Status.values()) {
            if (status.value.equalsIgnoreCase(text)) {
                return status;
            }
        }
        return null;
    }
}
