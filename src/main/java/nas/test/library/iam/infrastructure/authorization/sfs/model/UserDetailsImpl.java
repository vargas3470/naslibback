package nas.test.library.iam.infrastructure.authorization.sfs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import nas.test.library.iam.domain.model.aggregates.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    private final String username;
    @Getter
    private final String email;
    @Getter
    private final String name;
    @Getter
    private final String lastname;

    @JsonIgnore
    private final String password;

    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public UserDetailsImpl(String name, String lastname, String username, String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getName(),
                user.getLastname(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Siempre devuelve una lista vacía
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
