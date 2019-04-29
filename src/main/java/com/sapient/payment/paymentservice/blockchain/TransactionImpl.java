package com.sapient.payment.paymentservice.blockchain;

import com.sapient.payment.paymentservice.SHA256;

public class TransactionImpl implements Transaction {

    private String hash;
    private String value;

    public String hash() {
        return hash;
    }

    public TransactionImpl(String value) {
        this.hash = SHA256.generateHash(value);
        this.setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {

        // new value need to recalc hash
        this.hash = SHA256.generateHash(value);
        this.value = value;
    }

    public String toString() {
        return this.hash + " : " + this.getValue();
    }

}
