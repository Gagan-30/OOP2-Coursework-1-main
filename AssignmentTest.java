import java.util.List;
import java.io.IOException;

public class AssignmentTest {
    public static void main(String[] args) {
        testWordStore();
        testVerbStore();
        testEncrypt();
        testDecrypt();
    }

    private static void testWordStore() {
        WordStore store = new WordStore();
        store.add('a', "Chair");
        store.add('a', "Table");

        String item = store.getRandomItem('a');
        assert item != null : "WordStore failed to retrieve an item.";
        assert item.equals("chair") || item.equals("table") : "WordStore returned an unexpected item.";

        System.out.println("WordStore tests passed.");
    }

    private static void testVerbStore() {
        VerbStore store = new VerbStore();
        store.add('h', "hop");
        store.add('j', "jump");
        store.add('b', "blame");
        store.add('s', "stop");
        store.add('r', "run");

        // Test "hop" → "hopping"
        String item1 = store.getRandomItem('h');
        System.out.println("Retrieved item (hop): " + item1); 
        assert item1 != null : "VerbStore failed to retrieve an item.";
        assert item1.equals("hopping") : "VerbStore returned an unexpected item: " + item1;

        // Test "jump" → "jumping"
        String item2 = store.getRandomItem('j');
        System.out.println("Retrieved item (jump): " + item2); 
        assert item2 != null : "VerbStore failed to retrieve an item.";
        assert item2.equals("jumping") : "VerbStore returned an unexpected item: " + item2;

        // Test "blame" → "blaming"
        String item3 = store.getRandomItem('b');
        System.out.println("Retrieved item (blame): " + item3); 
        assert item3 != null : "VerbStore failed to retrieve an item.";
        assert item3.equals("blaming") : "VerbStore returned an unexpected item: " + item3;

        // Test "stop" → "stopping"
        String item4 = store.getRandomItem('s');
        System.out.println("Retrieved item (stop): " + item4);
        assert item4 != null : "VerbStore failed to retrieve an item.";
        assert item4.equals("stopping") : "VerbStore returned an unexpected item: " + item4;

        // Test "run" → "running"
        String item = store.getRandomItem('s');
        System.out.println("Retrieved item (stop): " + item4);
        assert item4 != null : "VerbStore failed to retrieve an item.";
        assert item4.equals("stopping") : "VerbStore returned an unexpected item: " + item4;

        System.out.println("VerbStore tests passed.");
    }

    private static void testEncrypt() {
        try {
            Encrypt encryptor = new Encrypt();
            List<String> encrypted = encryptor.encrypt("password");
            assert encrypted.size() == 8 : "Encrypt returned an unexpected number of words.";

            System.out.println("Encrypt tests passed.");
        } catch (IOException e) {
            System.err.println("Encrypt test failed: " + e.getMessage());
        }
    }

    private static void testDecrypt() {
        try {
            Decrypt decryptor = new Decrypt();
            String decrypted = decryptor.decrypt(List.of("snicking", "unquestionably", "steepening", "successively", "cinching", "quickest", "cozy", "frightfulness"));
            assert decrypted.equals("password") : "Decrypt failed to decrypt correctly.";

            System.out.println("Decrypt tests passed.");
        } catch (IOException e) {
            System.err.println("Decrypt test failed: " + e.getMessage());
        }
    }
}
