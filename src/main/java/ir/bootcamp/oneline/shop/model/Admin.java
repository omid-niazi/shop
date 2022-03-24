package ir.bootcamp.oneline.shop.model;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin extends User {

    public Admin() {
    }

    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin(Long id, String username, String password) {
        super(id, username, password);
    }
}
