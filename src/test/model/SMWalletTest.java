package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SMWalletTest {

    SMWallet wallet1;
    NFT nft1;

    @BeforeEach
    void runBefore() {
        wallet1 = new SMWallet(123456789);
        nft1 = new NFT("MOnkey1", 100, "Dave");
    }

    @Test
    void testConstructor() {
        assertEquals("123456789", wallet1.getAccountNumber());
        assertEquals(0, wallet1.getBalance());
        assertEquals(0, wallet1.getOwnedNFT().size());
    }

    @Test
    void testDeposit() {
        wallet1.deposit("wwwe2efw", 2000);
        assertEquals(2000, wallet1.getBalance());

        wallet1.deposit("wwwe2efw", 3000);
        assertEquals(5000, wallet1.getBalance());


    }

    @Test
    void testMakePurchase() {
        wallet1.deposit("wwwe2efw", 2000);
        wallet1.makePurchase(nft1, "Dave");
       assertTrue(wallet1.getOwnedNFT().contains(nft1));
       assertEquals("Dave", nft1.getOwner());

    }
}
