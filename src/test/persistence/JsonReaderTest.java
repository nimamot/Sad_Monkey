package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Account ac = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccount.json");
        try {
            Account ac = reader.read();
            assertEquals("nima", ac.getName());
            assertEquals(0, ac.getWallet().getBalance());
            assertEquals(0, ac.getWallet().getOwnedNFT().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAccount.json");
        try {
            Account ac = reader.read();
            assertEquals("Alex Lee", ac.getName());
            assertEquals("Alex.lee@gmail.com", ac.getEmail());
            assertEquals(1500, ac.getWallet().getBalance());
            assertEquals(2, ac.getWallet().getOwnedNFT().size());

            ac.addToWatchlist(new NFT("monkey 10", 100, "Simone"));

            assertEquals("Simone", ac.getWatchList().get(1).getOwner());
            assertEquals(2, JsonReader.getNfts().size());
            List<NFT> nfts = JsonReader.getNfts();
            assertFalse(nfts.isEmpty());
            assertEquals("monkey 1", nfts.get(0).getTitle());
            assertEquals("monkey 3", nfts.get(1).getTitle());
            assertEquals("Alex Lee", nfts.get(1).getOwner());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
