import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Decrypt {
    private final Map<String, Character> wordToCharMap;

    public Decrypt() throws IOException {
        wordToCharMap = new HashMap<>();
        loadWordList("adjectives.txt");
        loadWordList("adverbs.txt");
        loadWordList("verbs.txt");
        loadWordList("nouns.txt");
    }

    // Loads words from a file and maps them to characters.
    private void loadWordList(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String word = parts[0].toLowerCase();
                    char mappedChar = parts[1].charAt(0);
                    
                    // Only allow the first occurrence of a word (from earlier files)
                    if (!wordToCharMap.containsKey(word)) {
                        wordToCharMap.put(word, mappedChar);
                    }
                }
            }
        }
    }

    // Decrypt the input list of words into a single string
    public String decrypt(List<String> words) {
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            String originalWord = word.toLowerCase();
            
            // Handle verb modifications
            if (originalWord.endsWith("ing")) {
                // Try removing "ing" first
                String trimmedWord = originalWord.substring(0, originalWord.length() - 3);
                if (wordToCharMap.containsKey(trimmedWord)) {
                    originalWord = trimmedWord;
                } else {
                    // If removing "ing" doesn't work, try adding an "e" back
                    String withE = trimmedWord + "e";
                    if (wordToCharMap.containsKey(withE)) {
                        originalWord = withE;
                    }
                }
            }

            // Look up the word in the map
            Character c = wordToCharMap.get(originalWord);
            if (c != null) {
                result.append(c);
            } else {
                // If the word is not found, append a placeholder (e.g., '?')
                result.append('?');
            }
        }
        return result.toString();
    }

    // Main method for command-line execution
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Decrypt <word1> <word2> ...");
            return;
        }

        try {
            Decrypt decryptor = new Decrypt();
            String decrypted = decryptor.decrypt(List.of(args));
            System.out.println(decrypted);
        } catch (IOException e) {
            System.err.println("Error loading word lists: " + e.getMessage());
        }
    }
}
