package br.pedro.rproject.models.embedded;

import br.pedro.rproject.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile {
    private String name;
    private int age;
    private String alignment;
    private Status status;
    private String imgUrl;

    public Profile() {
    }

    public Profile(String name) {
        this.name = name;
        this.status = Status.ALIVE;
    }

    public Profile(Profile profile) {
        this.name = profile.getName();
        this.age = profile.getAge();
        this.alignment = profile.getAlignment();
        this.status = profile.getStatus();
        this.imgUrl = profile.getImgUrl();
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
