package com.kryspinmusiol.orm.testfixtures.model;

import com.kryspinmusiol.orm.annotations.Column;
import com.kryspinmusiol.orm.annotations.Entity;
import com.kryspinmusiol.orm.annotations.PrimaryKey;

@Entity
public class Person {

    @PrimaryKey
    private int id;

    @Column
    private String name;

    @Column
    private int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
