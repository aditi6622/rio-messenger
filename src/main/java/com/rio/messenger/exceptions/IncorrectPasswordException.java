package com.rio.messenger.exceptions;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User Already exists")
public class IncorrectPasswordException extends RuntimeException {

    @Builder
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
