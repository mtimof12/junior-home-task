package com.betpawa.wallet.rest.request;

import lombok.Data;

@Data
public class NewAccountRequest {
    private String email;
    private String password;
}
