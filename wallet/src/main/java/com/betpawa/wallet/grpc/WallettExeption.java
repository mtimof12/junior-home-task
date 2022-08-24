package com.betpawa.wallet.grpc;

public class WallettExeption extends Exception {
    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public WallettExeption(String message, int errorCode){
        super(message);
        this.errorCode = errorCode;
    }
    
    public WallettExeption(){
        super();
    }

    public WallettExeption(String message){
        super(message);
    }

    public WallettExeption(Exception e){
        super(e);
    }
}
