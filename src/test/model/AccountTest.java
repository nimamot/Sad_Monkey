package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account testAccount;
    private NFT testNft1;
    private NFT testNft2;

    @BeforeEach
    void runBefore() {
        testAccount = new Account("Dave", "dave.e@gmail.com");
        testNft1 = new NFT("monkey", 200, "Dave");
        testNft2 = new NFT("monkey 2", 5000, "Edward");
    }

    @Test
    void testConstructor() {
        assertEquals("Dave", testAccount.getName());
        assertEquals("dave.e@gmail.com", testAccount.getEmail());
        assertTrue(testAccount.getId() > 0);
        assertEquals(0, testAccount.getWatchList().size());


    }

    @Test
    void testAddToWatchlist() {
        testAccount.addToWatchlist(testNft1);
        assertEquals(1, testAccount.getWatchList().size());

        testAccount.addToWatchlist(testNft2);
        assertEquals(2, testAccount.getWatchList().size());

    }

}