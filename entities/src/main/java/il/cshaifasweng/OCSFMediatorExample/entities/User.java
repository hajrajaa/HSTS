package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable{

    public enum UserType{
        Student,Teacher,Princiaple;
    }

    private int id;

    @Id
    private String userName;

    private  String password;

    private UserType type;

    private boolean isConnected=false;

    public User() {}

    public User(int id ,String username ,String password, UserType type)
    {
        super();
        this.id=id;
        this.userName=username;
        this.password=password;
        this.type=type;
    }
    public  User(String userName ,String password)
    {
        super();
        this.userName=userName;
        this.password=password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }


    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
