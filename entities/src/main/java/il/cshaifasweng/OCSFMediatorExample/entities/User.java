package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable{

    public enum type{
        Student,Teacher,Principal;
    }

    @Id
    private int id;
    private String userName;

    private  String password;



    private type type;


    private boolean isConnected=false;

    public User() {
    }

    public User(int id ,String username ,String password, type type)
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

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
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


    public User.type getType() {
        return type;
    }

    public void setType(User.type type) {
        this.type = type;
    }



}
