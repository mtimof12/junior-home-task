package com.betpawa.wallet.grpc;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

import com.betpawa.wallet.api.proto.AccountDetailsRequest;
import com.betpawa.wallet.api.proto.BalanceResponse;
import com.betpawa.wallet.api.proto.MoneyTransferRequest;
import com.betpawa.wallet.api.proto.MoneyTransferResponse;
import com.betpawa.wallet.api.proto.TransferStatus;
import com.betpawa.wallet.api.proto.WalletGrpcServiceGrpc;
import com.betpawa.wallet.dto.Balance;
import com.betpawa.wallet.dto.MoneyTransferData;
import com.betpawa.wallet.dto.MoneyTransferResult;
import com.betpawa.wallet.dto.TransferStatusType;
import com.betpawa.wallet.service.WalletService;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class WalletGrpcService extends WalletGrpcServiceGrpc.WalletGrpcServiceImplBase {
    private final WalletService service;
    private final Map<TransferStatusType, TransferStatus> convertMap = Map.of(
            TransferStatusType.FAIL, TransferStatus.FAILURE,
            TransferStatusType.OK, TransferStatus.SUCCESS,
            TransferStatusType.NOT_ENOUGH_MONEY, TransferStatus.NOT_ENOUGH_MONEY
    );

    @Override
    public void reserveMoney(final MoneyTransferRequest request, final StreamObserver<MoneyTransferResponse> responseObserver) {
        responseObserver.onNext(convertTransferStatus(service.creditAccount(builderTransferData(request))));
        responseObserver.onCompleted();
    }

    private static MoneyTransferData builderTransferData(final MoneyTransferRequest request) {
        return new MoneyTransferData(request.getAccountId(), BigDecimal.valueOf(request.getAmount()), request.getReference());
    }

    @Override
    public void addMoney(final MoneyTransferRequest request, final StreamObserver<MoneyTransferResponse> responseObserver) {
        responseObserver.onNext(convertTransferStatus(service.debitAccount(builderTransferData(request))));
        responseObserver.onCompleted();
    }

    private MoneyTransferResponse convertTransferStatus(final MoneyTransferResult result) {
        return MoneyTransferResponse.newBuilder()
                .setAccountId(result.accountId())
                .setStatus(convertStatusType(result.status()))
                .setMessage(result.message())
                .build();
    }

    private TransferStatus convertStatusType(final TransferStatusType status) {
        return convertMap.get(status);
    }

    @Override
    public void balance(final AccountDetailsRequest request, final StreamObserver<BalanceResponse> responseObserver) {
        responseObserver.onNext(converBalance(service.balance(request.getAccountId())));
        responseObserver.onCompleted();
    }

    private BalanceResponse converBalance(final Balance balance) {
        return BalanceResponse.newBuilder()
                .setAccountId(balance.accountId())
                .setAmount(balance.amount().longValue())
                .build();
    }

    /**
     * @param args
     */
    public static void main(String[] args)
{
    // Setup some initial variables
    int bank = 10;
    double flip = Math.random();

    // Instantiate a new Scanner object
    Scanner input = new Scanner(System.in);

    // Let the user bet until they run out of money in the bank
    do
    {
        // Ask the user how much money to wager
        System.out.println("How much do you want to bet?");
        int bet = input.nextInt();

        // The bet was invalid
        while(bet < 0 || bet > bank)
        {
            System.out.println("Invalid bet (" + bet + "). Minimum bet = 0. Maximum bet = " + bank);
            bet = input.nextInt();
        }

        // Ask the user for which side
        System.out.println("Heads or tails?");
        String guess = input.next();

        // The coin is either heads or tails, so it can always start as tails. If the value goes under .5, change it to heads. If not, it remains the same, "tails".
        String coin = "tails";
        if (flip < 0.5) 
        {
            coin = "heads";
        }

        // Tell the user the result, regardless of win or lose
        System.out.println("The flip was " + coin);

        // Update the user's bank and inform them of the new value
        if(guess.equalsIgnoreCase(coin)) // this allows for any form of HeaDs or TaILs
        {
            bank += bet;
            System.out.println("You win " + bet);
            System.out.println("Your bank contains " + bank);
        }
        else
        {
            bank -= bet;
            System.out.println("You lose " + bet);
            System.out.println("Your bank contains " + bank);
        }
    }
    while(bank > 0);

    // Goodbye message
    System.out.println("Thanks for playing!");

    // Don't forget to close your Scanner object!
    input.close();  
    }
}


