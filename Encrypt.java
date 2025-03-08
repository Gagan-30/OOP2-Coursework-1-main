import java.util.ArrayList;
import java.util.List;

public class Encrypt {
    private final WordStore adjectiveStore;
    private final WordStore adverbStore;
    private final WordStore nounStore;
    private final VerbStore verbStore;

    // Constructor initializes the word stores
    public Encrypt() throws IOException {
        this.adjectiveStore = new WordStore("adjectives.txt");
        this.adverbStore = new WordStore("adverbs.txt");
        this.nounStore = new WordStore("nouns.txt");
        this.verbStore = new VerbStore("verbs.txt");
    }

    // Encrypts the input string into a list of words
    public List<String> encrypt(String input) {
        List<String> encryptedSentence = new ArrayList<>();
        int length = input.length();

        // Work backwards to enforce the sentence structure
        for (int i = length - 1; i >= 0; i--) {
            char currentChar = input.charAt(i);

            if (i == length - 1) {
                // Last character must be encoded as a noun
                String noun = nounStore.getRandomItem(currentChar);
                if (noun != null) {
                    encryptedSentence.add(0, noun);
                }
            } else if (i == length - 2 && length >= 2) {
                // Second last character must be encoded as an adjective (if possible)
                String adjective = adjectiveStore.getRandomItem(currentChar);
                if (adjective != null) {
                    encryptedSentence.add(0, adjective);
                }
            } else {
                // Remaining characters alternate between adverbs and verbs
                if ((length - i) % 2 == 1) {
                    // Odd positions: adverbs
                    String adverb = adverbStore.getRandomItem(currentChar);
                    if (adverb != null) {
                        encryptedSentence.add(0, adverb);
                    }
                } else {
                    // Even positions: verbs
                    String verb = verbStore.getRandomItem(currentChar);
                    if (verb != null) {
                        encryptedSentence.add(0, verb);
                    }
                }
            }
        }

        return encryptedSentence;
    }

    // Main method for command-line execution
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Encrypt <word>");
            return;
        }

        String input = args[0].toLowerCase();

        try {
            Encrypt encryptor = new Encrypt();
            List<String> encryptedSentence = encryptor.encrypt(input);

            // Print the encrypted sentence, one word per line
            for (String word : encryptedSentence) {
                System.out.println(word);
            }
        } catch (IOException e) {
            System.err.println("Error loading word stores: " + e.getMessage());
        }
    }
}