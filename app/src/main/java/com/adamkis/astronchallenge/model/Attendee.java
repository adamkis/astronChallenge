package com.adamkis.astronchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 8/25/15.
 */
public class Attendee implements Comparable<Attendee> {



    @Expose
    @SerializedName("name")
    private Name name;


    @Expose
    @SerializedName("gender")
    private String gender;


    @Expose
    @SerializedName("age")
    private int age;

    @Expose
    @SerializedName("id")
    private int id;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "name=" + name +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(Attendee other) {
        return getName().compareTo(other.getName());
    }
}
