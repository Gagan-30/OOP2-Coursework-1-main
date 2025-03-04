# Encryption Assignment

## Overview
This assignment focuses on implementing a rudimentary encryption tool for secret inter-agent communication. The encryption system maps words to letters, allowing messages to be encoded using predefined word mappings. The assignment is designed to test proficiency in Java concepts such as collections, generics, input/output streams, inheritance, and object-oriented program design.

## Features
- **WordStore Class:** Stores mappings of characters to lists of words and allows retrieval of random words for encryption.
- **VerbStore Class:** Extends `WordStore`, modifying verbs into the present continuous tense.
- **Encrypt Class:** Converts a given word into an encrypted sequence of words based on predefined mappings.
- **Decrypt Class:** Reverses the encryption process to retrieve the original word.
- **Testing:** A dedicated `AssignmentTest` class ensures functionality of all implemented classes.

## Implementation Details
1. **WordStore Class**
    - Supports case-insensitive storage and retrieval of words.
    - Loads word mappings from a file.
    - Returns random words associated with given characters.

2. **VerbStore Class**
    - Converts verbs into their present continuous tense form before returning them.

3. **Encrypt Class**
    - Encrypts words using stored mappings.
    - Outputs encrypted words in an alternating pattern of verbs and adverbs, ending with an adjective and noun.

4. **Decrypt Class**
    - Reverses the encryption process to retrieve the original input.

5. **AssignmentTest Class**
    - Implements assertions and test cases to verify correctness.

## Questions and Marks
### **Question 1: WordStore [10 Marks]**
- Implement a class `WordStore` to store mappings of characters to words.
- Methods: `add(char key, String item)`, `getRandomItem(char key)`.
- Ensure case-insensitive storage and retrieval.

### **Question 2: Populating the WordStore [30 Marks]**
- Implement a file-based constructor for `WordStore`.
- Load word mappings from files.
- Implement `VerbStore` to handle verbs in the present continuous tense.

### **Question 3: Creating Encrypted Sentences [25 Marks]**
- Implement `Encrypt` to generate encrypted word sequences.
- Ensure correct sentence structure (Verb? (Adverb Verb)* Adjective Noun).
- Handle command-line arguments.

### **Question 4: Decryption [20 Marks]**
- Implement `Decrypt` to reverse encryption and recover the original word.
- Ensure correct handling of input.

### **Testing and Code Quality [15 Marks]**
- Implement `AssignmentTest` to validate all functionalities.
- Ensure good code organization, readability, and object-oriented design.

## Running the Code
### Compilation
```sh
javac *.java
```

### Running Encryption
```sh
java Encrypt <word>
```

### Running Decryption
```sh
java Decrypt <encrypted words>
```

### Running Tests
```sh
java -enableassertions AssignmentTest
```

## Submission Requirements
- Ensure all `.java` files (WordStore.java, VerbStore.java, Encrypt.java, Decrypt.java, AssignmentTest.java) are submitted.
- Code should compile and run without errors.
- Follow best practices for OOP, coding style, and readability.

## Notes
- The use of external libraries is not allowed.
- The provided `assignment1TesterStudent.jar` can be used for testing.
- The word lists (adjectives.txt, adverbs.txt, nouns.txt, verbs.txt) must be in the same directory as the Java files.
- Late submissions may incur penalties as per university guidelines.