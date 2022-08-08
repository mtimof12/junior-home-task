package com.betpawa.wallet.rest.response;

import java.math.BigDecimal;

public record BalanceResponse(Long accountId, BigDecimal amount) {
}
