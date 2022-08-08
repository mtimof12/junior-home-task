package com.betpawa.wallet.rest.request;

import lombok.Data;

@Data
public class WithdrawRequest {
    private Long amount;
    private String reference;
}
