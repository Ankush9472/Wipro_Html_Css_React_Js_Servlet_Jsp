package com.example.checkuser.model;

public class User
{
    private Long id;
    private int age;
    private Long aadharNo;
    private String panNo;

    public User() {}

    public User(Long id, int age, Long aadharNo, String panNo) {
        this.id = id;
        this.age = age;
        this.aadharNo = aadharNo;
        this.panNo = panNo;
    }

    public Long getId()
    { return id; }

    public void setId(Long id)
    { this.id = id; }

    public int getAge()
    { return age; }

    public void setAge(int age)
    { this.age = age; }

    public Long getAadharNo()
    { return aadharNo; }

    public void setAadharNo(Long aadharNo)
    { this.aadharNo = aadharNo; }

    public String getPanNo()
    { return panNo; }

    public void setPanNo(String panNo)
    { this.panNo = panNo; }

    @Override
    public String toString() {
        return "User{id=" + id + ", age=" + age +
                ", aadharNo='" + aadharNo + "', panNo='" + panNo + "'}";
    }
}