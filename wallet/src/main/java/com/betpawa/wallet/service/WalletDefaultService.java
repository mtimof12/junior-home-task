package com.betpawa.wallet.service;

import org.springframework.stereotype.Service;

import com.betpawa.wallet.dto.Balance;
import com.betpawa.wallet.dto.MoneyTransferData;
import com.betpawa.wallet.dto.MoneyTransferResult;

@Service
public class WalletDefaultService implements WalletService {
    @Override
    public MoneyTransferResult creditAccount(MoneyTransferData transferData) {
        throw new IllegalStateException("Implement");
    }

    @Override
    public MoneyTransferResult debitAccount(MoneyTransferData transferData) {
        throw new IllegalStateException("Implement");
    }

    @Override
    public Balance balance(Long accountId) {
        throw new IllegalStateException("Implement");
    }
}
