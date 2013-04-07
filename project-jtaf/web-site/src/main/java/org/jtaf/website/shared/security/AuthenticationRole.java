package org.jtaf.website.shared.security;

import java.math.BigInteger;

public enum AuthenticationRole {

    USER(BigInteger.valueOf(1), "ROLE_User", "Internal User");

    private BigInteger id;
    private String role;
    private String shortRole;

    AuthenticationRole(BigInteger id, String role, String shortRole) {
        this.id = id;
        this.role = role;
        this.shortRole = shortRole;
    }

    /**
     * L'id du role
     * 
     * @return id
     */
    public BigInteger getId() {
        return this.id;
    }

    /**
     * Le role décrit dans spring Security
     * 
     * @return role
     */
    public String getRole() {
        return this.role;

    }

    /**
     * Défini le nom court du role
     * 
     * @param shortRole
     */
    public void setShortRole(String shortRole) {
        this.shortRole = shortRole;
    }

    /**
     * Le nom court du role
     * 
     * @return nom court
     */
    public String getShortRole() {
        return shortRole;
    }
}
