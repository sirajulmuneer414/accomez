package com.sirajul.Accomez.Controller.Authentication;

import com.sirajul.Accomez.Entities.Employee;
import com.sirajul.Accomez.Entities.User;
import lombok.Getter;

@Getter
public class LoginResponseBody {
    private final String jwtToken;
    private final User user;
    private final Employee  employee;

    public LoginResponseBody(String jwtToken, User user, Employee employee){

        this.jwtToken = jwtToken;
        this.user = user;
        this.employee = employee;

    }
}
