import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WordStore {
    private final Map<Character, List<String>> store;
    private final Random random;

    // Constructor initializes the store
    public WordStore() {
        this.store = new HashMap<>();
        this.random = new Random();
    }

    // Constructor that loads words from a file
    public WordStore(String filename) throws IOException {
        this();
        loadFromFile(filename);
    }

    // Adds a word to the store under the given key (case insensitive)
    public void add(char key, String item) {
        char lowerKey = Character.toLowerCase(key);
        store.putIfAbsent(lowerKey, new ArrayList<>());
        store.get(lowerKey).add(item.toLowerCase());
    }

    // Returns a random word associated with a given key
    public String getRandomItem(char key) {
        char lowerKey = Character.toLowerCase(key);
        List<String> words = store.get(lowerKey);
        if (words == null || words.isEmpty()) {
            return null;
        }
        return words.get(random.nextInt(words.size()));
    }

    // Loads words from a file and maps them to letters
    private void loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    add(parts[1].charAt(0), parts[0]);
                }
            }
        }
    }
}
