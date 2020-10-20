package flashcard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardTest {

    @Test
    void writeToFile() {
        Flashcard flashcard = new Flashcard("2+2", "4");
        String expected = "2+2|4\n";
        String actual = flashcard.writeToFile();
        assertEquals(expected, actual);
    }
}