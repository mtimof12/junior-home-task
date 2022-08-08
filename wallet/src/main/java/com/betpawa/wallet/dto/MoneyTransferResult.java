package com.betpawa.wallet.dto;


public record MoneyTransferResult(Long accountId, TransferStatusType status, String message) {
}
