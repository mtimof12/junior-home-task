package com.betpawa.wallet.rest.request;

import lombok.Data;

@Data
public class DepositRequest {
    private Long amount;
    private String reference;
}
