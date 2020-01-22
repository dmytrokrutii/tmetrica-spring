package com.project.timetracking.security.exceptions;

public class TokenGlobalTimeExpiredException extends RuntimeException {
    public TokenGlobalTimeExpiredException() {
        super();
    }

    public TokenGlobalTimeExpiredException(String s) {
        super(s);
    }
}
