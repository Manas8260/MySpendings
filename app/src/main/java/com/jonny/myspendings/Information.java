package com.jonny.myspendings;

public class Information {
    private String reason,amount;
    public Information(){
    }
    public Information(String reason,String amount){
        this.reason = reason;
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
