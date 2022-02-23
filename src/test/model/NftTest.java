package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NftTest {
    NFT nft1;
    NFT nft2;
    NFT nftCollection;

    @BeforeEach
    void runBefore() {

        nft1 = new NFT("Monkey1", 400, "David");
        nft2 = new NFT("Monkey2", 500, "Nima");
        nftCollection = new NFT();
    }

    @Test
     void testConstructor() {
        assertEquals("Monkey1", nft1.getTitle());
        assertEquals(400, nft1.getPrice());
        assertEquals("David", nft1.getOwner());

        assertEquals("Monkey2", nft2.getTitle());
        assertEquals(500, nft2.getPrice());
        assertEquals("Nima", nft2.getOwner());

        nftCollection.addNFT(nft1);
        assertEquals(1, nftCollection.size());

        nftCollection.addNFT(nft2);
        assertEquals(2, nftCollection.size());

    }

    @Test
    void testGetNftByTitle() {
        nftCollection.addNFT(nft1);
        nftCollection.addNFT(nft2);
        assertEquals(nft1, nftCollection.getNftByTitle("Monkey1"));
    }

    @Test
    void setOwner() {
        nft1.setOwner("Shariq");
        assertEquals("Shariq", nft1.getOwner());
    }

    @Test
    void testGetAllNFTs() {
        nftCollection.addNFT(nft1);
        nftCollection.addNFT(nft2);

        assertTrue(nftCollection.getAllNFTs().contains(nft1));
        assertTrue(nftCollection.getAllNFTs().contains(nft2));
    }


}
