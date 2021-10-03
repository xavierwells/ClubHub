package com.exzhacks;

import java.util.ArrayList;

public class User
{
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private String password;

    private ArrayList<String> interests;

    public User()
    {
        this.interests = new ArrayList<String>();
    }

    public void setPassword( String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getMajor()
    {
        return major;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public ArrayList<String> getInterests()
    {
        return interests;
    }

    public void addInterest(String n)
    {
        interests.add(n);
    }

    public void setFirstName(String s)
    {
        firstName = s;
    }

    public void setLastName(String s)
    {
        lastName = s;
    }

    public void setUserName(String s)
    {
        userName = s;
    }

    public void setEmail(String s)
    {
        email = s;
    }

    public void setMajor(String s)
    {
        major = s;
    }
}