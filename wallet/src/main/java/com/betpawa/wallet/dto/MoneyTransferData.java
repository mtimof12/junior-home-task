package com.betpawa.wallet.dto;

import java.math.BigDecimal;

public record MoneyTransferData(Long accountId, BigDecimal amount, String reference) {
}
