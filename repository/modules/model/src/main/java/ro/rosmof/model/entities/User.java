package ro.rosmof.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 256)
    private String username;

    @Column(name = "password", nullable = false, unique = false, length = 256)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
