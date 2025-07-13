package nas.test.library.iam.domain.model.aggregates;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import nas.test.library.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @NotBlank
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 50)
    @Column(name = "lastname")
    private String lastname;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true, name = "username")
    private String username;

    @NotBlank
    @Size(max = 120)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password")
    private String password;

    public User() {
        // Default constructor
    }

    public User(String name, String lastname, String username, String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
