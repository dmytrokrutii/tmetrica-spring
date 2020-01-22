package com.project.timetracking.model.enums;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");
    private final String ROLE;

    private Role(String role) {
        this.ROLE = role;
    }

    public String getRole() {
        return ROLE;
    }
}

