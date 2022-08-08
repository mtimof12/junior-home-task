package com.betpawa.wallet.dto;

import java.math.BigDecimal;

public record Balance(Long accountId, BigDecimal amount) {
}
