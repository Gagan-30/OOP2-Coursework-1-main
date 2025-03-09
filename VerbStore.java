import java.io.IOException;

public class VerbStore extends WordStore {
    public VerbStore() {
        super();
    }

    // Loads words from a file
    public VerbStore(String filename) throws IOException {
        super(filename);
    }

    // Retrieves a random verb and converts it to present continuous form.
    @Override
    public String getRandomItem(char key) {
        String item = super.getRandomItem(key);
        if (item != null) {
            item = transformToPresentContinuous(item);
        }
        return item;
    }

    // Transforms a verb into its present continuous form (e.g. "run" → "running").
    private String transformToPresentContinuous(String verb) {
        verb = verb.toLowerCase();
        int length = verb.length();

        // Handle verbs ending with "e"
        if (verb.endsWith("e")) {
            verb = verb.substring(0, length - 1); // Remove the "e"
        }
        // Handle verbs with a single vowel followed by a consonant
        else if (length >= 2 && isSingleVowelConsonant(verb)) {
            verb = verb + verb.charAt(length - 1); // Double the final consonant
        }

        // Add "ing"
        verb += "ing";
        return verb;
    }

    // Checks if a verb ends in a single vowel followed by a consonant.
    private boolean isSingleVowelConsonant(String verb) {
        int length = verb.length();
        char lastChar = verb.charAt(length - 1);
        char secondLastChar = verb.charAt(length - 2);

        // Check if the last character is a consonant and the second last is a vowel
        return isConsonant(lastChar) && isVowel(secondLastChar);
    }

    private boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }

    private boolean isConsonant(char c) {
        return !isVowel(c);
    }
}
