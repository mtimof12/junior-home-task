package com.betpawa.wallet.rest.request;

import lombok.Data;

@Data
public class LoginAccountRequest {
    private String email;
    private String password;
}
