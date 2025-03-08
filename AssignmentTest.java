import java.io.IOException;
import java.util.List;

public class AssignmentTest {
    public static void main(String[] args) {
        // Enable assertions
        boolean assertionsEnabled = false;
        assert assertionsEnabled = true;
        if (!assertionsEnabled) {
            System.out.println("Assertions are not enabled. Run with -enableassertions flag.");
            return;
        }

        System.out.println("Running tests for all questions...");

        // Test Question 1: WordStore
        testQuestion1();

        // Test Question 2: Populating the WordStore and VerbStore
        testQuestion2();

        // Test Question 3: Encryption
        testQuestion3();

        // Test Question 4: Decryption
        testQuestion4();

        System.out.println("All tests passed!");
    }

    // Test Question 1: WordStore
    private static void testQuestion1() {
        System.out.println("Testing Question 1: WordStore...");

        WordStore store = new WordStore();

        // Test adding words
        store.add('a', "Chair");
        store.add('a', "Table");
        store.add('b', "Book");
        store.add('b', "Bag");

        // Test getRandomItem method
        String randomWordA = store.getRandomItem('a');
        assert randomWordA != null : "Random word for 'a' should not be null";
        assert randomWordA.equals("chair") || randomWordA.equals("table") : "Random word for 'a' should be 'chair' or 'table'";

        String randomWordB = store.getRandomItem('b');
        assert randomWordB != null : "Random word for 'b' should not be null";
        assert randomWordB.equals("book") || randomWordB.equals("bag") : "Random word for 'b' should be 'book' or 'bag'";

        String randomWordC = store.getRandomItem('c');
        assert randomWordC == null : "Random word for 'c' should be null";

        // Test case insensitivity
        String randomWordA2 = store.getRandomItem('A');
        assert randomWordA2 != null : "Random word for 'A' should not be null";
        assert randomWordA2.equals("chair") || randomWordA2.equals("table") : "Random word for 'A' should be 'chair' or 'table'";

        System.out.println("Question 1 tests passed!");
    }

    // Test Question 2: Populating the WordStore and VerbStore
    private static void testQuestion2() {
        System.out.println("Testing Question 2: Populating the WordStore and VerbStore...");

        try {
            // Test loading words from a file
            WordStore fileStore = new WordStore("adjectives.txt");
            assert fileStore.getRandomItem('a') != null : "Failed to load words from file";

            // Test VerbStore
            VerbStore verbStore = new VerbStore("verbs.txt");
            String verb = verbStore.getRandomItem('r');
            assert verb != null : "VerbStore should return a verb";
            assert verb.endsWith("ing") : "VerbStore should return verbs in present continuous tense";

            System.out.println("Question 2 tests passed!");
        } catch (IOException e) {
            System.err.println("Error loading word stores: " + e.getMessage());
        }
    }

    // Test Question 3: Encryption
    private static void testQuestion3() {
        System.out.println("Testing Question 3: Encryption...");

        try {
            Encrypt encryptor = new Encrypt();

            // Test encryption
            List<String> encryptedSentence = encryptor.encrypt("password");
            assert encryptedSentence.size() == 8 : "Encrypted sentence should have 8 words";
            System.out.println("Encrypted sentence: " + encryptedSentence);

            // Test edge cases
            List<String> emptySentence = encryptor.encrypt("");
            assert emptySentence.isEmpty() : "Encrypted sentence should be empty for empty input";

            List<String> singleCharSentence = encryptor.encrypt("a");
            assert singleCharSentence.size() == 1 : "Encrypted sentence should have 1 word for single character input";

            System.out.println("Question 3 tests passed!");
        } catch (IOException e) {
            System.err.println("Error loading word stores: " + e.getMessage());
        }
    }

    // Test Question 4: Decryption
    private static void testQuestion4() {
        System.out.println("Testing Question 4: Decryption...");

        try {
            Decrypt decryptor = new Decrypt();

            // Test decryption
            List<String> encryptedSentence = List.of("quickly", "flying", "loudly", "squawking", "quietly", "resting", "cheerful", "bird");
            String decryptedWord = decryptor.decrypt(encryptedSentence);
            assert decryptedWord.equals("password") : "Decrypted word should be 'password'";
            System.out.println("Decrypted word: " + decryptedWord);

            // Test edge cases
            List<String> emptySentence = List.of();
            String emptyWord = decryptor.decrypt(emptySentence);
            assert emptyWord.isEmpty() : "Decrypted word should be empty for empty input";

            List<String> singleWordSentence = List.of("bird");
            String singleCharWord = decryptor.decrypt(singleWordSentence);
            assert singleCharWord.length() == 1 : "Decrypted word should have 1 character for single word input";

            System.out.println("Question 4 tests passed!");
        } catch (IOException e) {
            System.err.println("Error loading word stores: " + e.getMessage());
        }
    }
}
