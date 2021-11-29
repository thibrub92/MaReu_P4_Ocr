package fr.example.mareu.model;

import java.io.Serializable;

public class Workmate implements Serializable {

    String mFirstName;
    String mName;
    String mId;
    String mEmail;

    public Workmate(String firstName, String name, String id, String email){
        this.mFirstName= firstName;
        this.mName= name;
        this.mId= id;
        this.mEmail= email;
    }

    public String getFirstName(){
        return mFirstName;
    }
    public void setFirstName(String firstName){mFirstName = firstName;}

    public String getName(){
        return mName;
    }
    public void setName(String name){mName = name;}

    public String getId(){
        return mId;
    }
    public void setId(String id){mId = id;}

    public String getEmail(){
        return mEmail;
    }
    public void setEmail(String email){mEmail = email;}

}
