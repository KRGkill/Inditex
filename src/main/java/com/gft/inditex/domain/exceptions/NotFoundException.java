package com.gft.inditex.domain.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String detail) {
        super(detail);
    }

}
