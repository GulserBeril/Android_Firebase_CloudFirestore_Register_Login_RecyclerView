package com.gulser.android_firebase_cloudfirestore_register_login_recyclerview.Model;

public class UserModel {
    String student_number;
    String name;
    String surname;

    public UserModel() {
    }

    public UserModel(String student_number, String name, String surname) {
        this.student_number = student_number;
        this.name = name;
        this.surname = surname;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
