import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class WordStore {
    private Map<Character, List<String>> store;
   
    public WordStore() {
        this.store = new HashMap<>();    
    }

    public void add(char key, String item) {
        key = Character.toLowerCase(key);
        item = item.toLowerCase();

        store.putIfAbsent(key, new ArrayList<>());
        store.get(key).add(item);
    }

    public String getRandomItem(char key) {
        key = Character.toLowerCase(key);

        if (store.containsKey(key)) {
            List<String> items = store.get(key);
            Random random = new Random();
            // Return a random word from the list.
            return items.get(random.nextInt(items.size()));
        } else {
            return null; // Return null if no words are associated with the key.
        }
    }
}
