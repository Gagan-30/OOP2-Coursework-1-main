import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class AssignmentTest {

    public static void main(String[] args) {
        WordStore store = new WordStore();
        
        // Test adding words to the store.
        store.add('a', "Chair");
        store.add('d', "Table");
        store.add('b', "Ball");
        store.add('c', "Cat");

        // Test getRandomItem method.
        System.out.println("Random word for 'a': " + store.getRandomItem('a'));
        System.out.println("Random word for 'b': " + store.getRandomItem('b'));
        System.out.println("Random word for 'c': " + store.getRandomItem('c'));
        System.out.println("Random word for 'd': " + store.getRandomItem('d')); // Non-existing key.
    } 
}
