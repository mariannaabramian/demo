package com.example.demo;

import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String title;

    @ManyToOne(optional = false)
    private Account account;

    public Task(String title, Account account) {
        this.title = title;
        this.account = account;
    }

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
