package flashcard;

public class Flashcard {

    public String question;
    public String answer;

    Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String writeToFile() {
        return question + "|" + answer + "\n";
    }
}
