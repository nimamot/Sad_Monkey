package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    Account ac;

    @BeforeEach
    public void setUp() {
        ac = new Account("nima", "nima@gmail.com");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(ac);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            ac = reader.read();
            assertEquals("nima", ac.getName());
            assertEquals("nima@gmail.com", ac.getEmail());
            assertEquals(0, ac.getWallet().getBalance());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ac.addToWatchlist(new NFT("Mobkey", 2000, "Dave Emran"));
            ac.addToWatchlist(new NFT("Monkey 1", 10000, "Nimamot"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(ac);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            ac = reader.read();
            assertEquals("nima", ac.getName());
            List<NFT> nfts = ac.getWatchList();
            assertEquals(2, nfts.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
