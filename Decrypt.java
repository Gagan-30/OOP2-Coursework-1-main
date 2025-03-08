import java.io.IOException;
import java.util.List;

public class Decrypt {
    private final WordStore adjectiveStore;
    private final WordStore adverbStore;
    private final WordStore nounStore;
    private final VerbStore verbStore;

    // Constructor initializes the word stores
    public Decrypt() throws IOException {
        this.adjectiveStore = new WordStore("adjectives.txt");
        this.adverbStore = new WordStore("adverbs.txt");
        this.nounStore = new WordStore("nouns.txt");
        this.verbStore = new VerbStore("verbs.txt");
    }

    // Decrypts the encrypted sentence back into the original word
    public String decrypt(List<String> encryptedSentence) {
        StringBuilder decryptedWord = new StringBuilder();

        // Iterate through the encrypted sentence and map each word to its corresponding character
        for (String word : encryptedSentence) {
            char key = findCharacterForWord(word);
            if (key != '\0') {
                decryptedWord.append(key);
            }
        }

        return decryptedWord.toString();
    }

    // Helper method to find the character corresponding to a word
    private char findCharacterForWord(String word) {
        // Check if the word is in the adjective store
        char key = findKeyInStore(word, adjectiveStore);
        if (key != '\0') {
            return key;
        }

        // Check if the word is in the adverb store
        key = findKeyInStore(word, adverbStore);
        if (key != '\0') {
            return key;
        }

        // Check if the word is in the noun store
        key = findKeyInStore(word, nounStore);
        if (key != '\0') {
            return key;
        }

        // Check if the word is in the verb store (convert to base form first)
        String baseVerb = word.toLowerCase();
        if (baseVerb.endsWith("ing")) {
            baseVerb = baseVerb.substring(0, baseVerb.length() - 3);
            if (!baseVerb.endsWith("e")) {
                baseVerb += "e"; // Add back the 'e' if it was removed during encryption
            }
        }
        key = findKeyInStore(baseVerb, verbStore);
        if (key != '\0') {
            return key;
        }

        // If no match is found, return null character
        return '\0';
    }

    // Helper method to search for a word in a store and return its key
    private char findKeyInStore(String word, WordStore store) {
        for (char key = 'a'; key <= 'z'; key++) {
            List<String> words = store.getWordsForKey(key);
            if (words != null && words.contains(word.toLowerCase())) {
                return key;
            }
        }
        return '\0';
    }

    // Main method for command-line execution
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Decrypt <word1> <word2> ...");
            return;
        }

        // Convert command-line arguments to a list of words
        List<String> encryptedSentence = List.of(args);

        try {
            Decrypt decryptor = new Decrypt();
            String decryptedWord = decryptor.decrypt(encryptedSentence);
            System.out.println(decryptedWord);
        } catch (IOException e) {
            System.err.println("Error loading word stores: " + e.getMessage());
        }
    }
}
