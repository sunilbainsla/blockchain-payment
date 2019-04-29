package com.sapient.payment.paymentservice;

public class Block {

    public Object getBlockHash() {
        return blockHash;
    }

    private String previousHash;
    private String[] transactions;
    private Object blockHash;

    public Block(String previousHash, String[] transactions) {
        this.previousHash = previousHash;
        this.transactions = transactions;
        String marHash = "";
        if (transactions.length == 1) {
            marHash = Base64Encryption.sha256(transactions[0]);
        } else {
            marHash = generateMarkleHash(transactions).toString();
        }

        this.blockHash = Base64Encryption.sha256(marHash);

    }

    private StringBuilder generateMarkleHash(String[] transactions) {
        StringBuilder trans = new StringBuilder();
        for (int i = 0; i < transactions.length - 1; i++) {

            if (i % 2 == 1) {
                trans.append(generateHash(transactions[i], transactions[i - 1]));
            }
            trans.append(Base64Encryption.sha256(transactions[i])).append(Base64Encryption.sha256(transactions[i + 1]));

        }
        return trans;
    }

    private StringBuilder generateHash(String first, String second) {
        StringBuilder builder = new StringBuilder();
        builder.append(Base64Encryption.sha256(first)).append(Base64Encryption.sha256(second));
        return builder;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String[] getTransactions() {
        return transactions;
    }

    public void setTransactions(String[] transactions) {
        this.transactions = transactions;
    }

}
