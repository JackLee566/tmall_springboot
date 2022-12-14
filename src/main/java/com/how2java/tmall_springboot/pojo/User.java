package com.how2java.tmall_springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;
    private String password;
    private String salt;

    @Transient
    private String anonymousName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAnonymousName() {
        if(name!=null){
            return anonymousName;
        }
        if(null==name){
         return null;
        }
        else if(name.length()==1){
         anonymousName="*";
         return anonymousName;
        }
        else if(name.length()==2){
         anonymousName=name.substring(0,1)+"*";
         return anonymousName;
        }
        else{
         char[] cs = name.toCharArray();
         for(int i = 1; i<name.length()-1;i++){
             cs[i]='*';
         }
         return new String(cs);
        }

    }

    public void setAnonymousName(String anonymousName) {
        this.anonymousName = anonymousName;
    }
}
