package br.pedro.rproject.models.embedded;

import br.pedro.rproject.models.enums.Status;

public class Profile {
    private String name;
    private int age;
    private String alignment;
    private Status status;

    public Profile() {
    }

    public Profile(String name) {
        this.name = name;
        this.status = Status.ALIVE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", alignment='" + alignment + '\'' +
                ", status=" + status +
                '}';
    }
}
