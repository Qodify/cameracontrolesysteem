package kdg.be.processor.domain.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ADMIN")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(length=100, nullable = false)
    private String username;
    @Column( length=100, nullable = false)
    private String password;

    public void setId(long id) {
        this.id = id;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Column(name="roles")
    private String[] roles;

    public Admin() {
    }

    public Admin(String username, String password, String[] roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
