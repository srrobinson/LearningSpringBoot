package com.github.srrobinson.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "greetings")
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Size(min = 10, max = 300, message = "{message.error.greeting.length}")
    private String text;
    //map this to the user table via the userid column (i.e. one user can have many greetings)
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public Greeting() {
    }

    public Greeting(int id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public Greeting(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }
}