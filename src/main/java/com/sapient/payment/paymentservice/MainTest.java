package com.sapient.payment.paymentservice;

import java.util.ArrayList;

public class MainTest {

    public static void main(String[] args) throws Exception {
        MainTest mainTest = new MainTest();
        ArrayList<Block> distributedBlockchain1 = (ArrayList<Block>) mainTest.createBlockList();
        System.out.println("distributedBlockchain1 " + distributedBlockchain1);
        ArrayList<Block> distributedBlockchain2 = (ArrayList<Block>) distributedBlockchain1.clone();
        ArrayList<Block> distributedBlockchain3 = (ArrayList<Block>) distributedBlockchain2.clone();
        System.out.println("distributedBlockchain2 " + distributedBlockchain2);
        System.out.println("distributedBlockchain3 fdsfsffdsfsfsf Sunil Kumar" + distributedBlockchain3);

    }

    public ArrayList<Block> createBlockList() throws Exception {
        ArrayList<Block> blockchain = new ArrayList<>();

        String genesisTransFeed = Base64Encryption.encryptStr("Lloyds sent 10 pounds to Sapient");
        String genesisStartFeed = Base64Encryption.encryptStr("Starting the transaction");
        System.out.println("genesisStartFeed " + genesisStartFeed + "\n genesisTransFeed " + genesisTransFeed);
        String[] genesisTransaction = {genesisTransFeed};

        Block blockGenesis = new Block(genesisTransFeed, genesisTransaction);
        // Block block2 = new Block(blockGenesis.getBlockHash(), genesisTransaction);

        System.out.println("First Block chain " + blockGenesis.getBlockHash());
        String secondTransFeed = Base64Encryption.encryptStr("Sunil sent 10 pounds to Manish");
        String secondTransFeed2 = Base64Encryption.encryptStr("Sunil sent 10 pounds to Narender");
        String secondTransFeed3 = Base64Encryption.encryptStr("Manish sent 10 pounds to Balram");
        String[] secondTrans = {secondTransFeed, secondTransFeed2, secondTransFeed3};
        Block block2 = new Block(blockGenesis.getBlockHash().toString(), secondTrans);
        System.out.println("Second Block chain " + block2.getBlockHash());
        String thirdTransFeed3 = Base64Encryption.encryptStr("Sunil sent 600 pounds to India");
        String[] thirdTrans = {thirdTransFeed3};
        Block block3 = new Block(block2.getBlockHash().toString(), thirdTrans);
        System.out.println("Third Block chain " + block3.getBlockHash());
        blockchain.add(blockGenesis);
        blockchain.add(block2);
        blockchain.add(block3);
        return blockchain;
    }

}
