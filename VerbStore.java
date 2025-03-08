import java.io.IOException;

public class VerbStore extends WordStore {

    // Default constructor
    public VerbStore() {
        super();
    }

    // Constructor that loads verbs from a file
    public VerbStore(String filename) throws IOException {
        super(filename);
    }

    // Converts a verb to present continuous tense
    private String toPresentContinuous(String verb) {
        if (verb.endsWith("e") && !verb.endsWith("ee")) {
            return verb.substring(0, verb.length() - 1) + "ing";
        }
        return verb + "ing";
    }

    // Overrides getRandomItem to return verbs in present continuous tense
    @Override
    public String getRandomItem(char key) {
        String verb = super.getRandomItem(key);
        return (verb != null) ? toPresentContinuous(verb) : null;
    }
}
