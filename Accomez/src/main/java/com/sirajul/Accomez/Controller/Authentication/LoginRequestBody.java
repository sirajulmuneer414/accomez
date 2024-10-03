package com.sirajul.Accomez.Controller.Authentication;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestBody {

    private final String email;
    private final String password;

    public LoginRequestBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
