package bank.dto;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, CUSTOMER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
