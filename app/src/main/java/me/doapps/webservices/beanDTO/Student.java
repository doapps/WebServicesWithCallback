package me.doapps.webservices.beanDTO;

/**
 * Created by frank on 30/06/16.
 */
public class Student {

    private String id;
    private String name;
    private String score;

    public Student(String id, String name, String score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
