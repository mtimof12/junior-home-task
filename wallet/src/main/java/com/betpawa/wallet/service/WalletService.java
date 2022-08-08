package com.betpawa.wallet.service;

import com.betpawa.wallet.dto.Balance;
import com.betpawa.wallet.dto.MoneyTransferData;
import com.betpawa.wallet.dto.MoneyTransferResult;

public interface WalletService {
    MoneyTransferResult creditAccount(MoneyTransferData transferData);

    MoneyTransferResult debitAccount(MoneyTransferData transferData);

    Balance balance(Long accountId);

}
