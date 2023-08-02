package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "princiaples")
public class Princiaple extends User implements Serializable {



    public Princiaple(int id, String username, String password) {
        super(id, username, password, UserType.Princiaple);
    }

    public Princiaple() {
    }
}
