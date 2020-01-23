package com.project.timetracking.model.enums;

public enum ActivityStatus {
    SUSPENDED("SUSPENDED"),
    CLOSED("CLOSED"),
    ACTIVE("ACTIVE");
    private final String STATUS;

    private ActivityStatus(String role) {
        this.STATUS = role;
    }

    public String getRole() {
        return STATUS;
    }
}