package com.adamkis.astronchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.adamkis.astronchallenge.common.Const;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* Created by adam on 8/25/15.
*/
public class Attendee implements Comparable<Attendee>, Parcelable {


    public enum AgeGroup { STUDENT, WORKER, RETIRED }
    public static final String[] AGE_GROUP_NAMES = new String[] { "Student", "Worker", "Retired" };

    public enum GenderType { MALE, FEMALE, UNDEFINED }
    public static final String MALE_KEY = "male";
    public static final String FEMALE_KEY = "female";

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


    public String getGenderString() {
        return gender;
    }

    public GenderType getGender() {
        if( getGenderString().equals(MALE_KEY) ){
            return GenderType.MALE;
        } else if( getGenderString().equals(FEMALE_KEY) ){
            return GenderType.FEMALE;
        }
        else{
            return GenderType.UNDEFINED;
        }
    }

    public void setGenderString(String gender) {
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


    public AgeGroup getAgeGroup(){
        if( getAge() <= Const.AGE_LIMIT_WORKER ){
            return AgeGroup.STUDENT;
        }else if( getAge() <= Const.AGE_LIMIT_RETIRED ){
            return AgeGroup.WORKER;
        }else{
            return AgeGroup.RETIRED;
        }
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

    protected Attendee(Parcel in) {
        name = (Name) in.readValue(Name.class.getClassLoader());
        gender = in.readString();
        age = in.readInt();
        id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeString(gender);
        dest.writeInt(age);
        dest.writeInt(id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Attendee> CREATOR = new Parcelable.Creator<Attendee>() {
        @Override
        public Attendee createFromParcel(Parcel in) {
            return new Attendee(in);
        }

        @Override
        public Attendee[] newArray(int size) {
            return new Attendee[size];
        }
    };
}