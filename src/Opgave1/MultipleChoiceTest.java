package Opgave1;

import java.util.Random;

public class MultipleChoiceTest {

    public static char[] generateStudentAnswers() {
        int numberOfQuestions = 10;

        char[] studentAnswers = new char[numberOfQuestions];

        for (int i = 0; i < studentAnswers.length; i++) {
            Random r = new Random();
            char c = (char) (r.nextInt(4) + 'A');
            studentAnswers[i] = c;
        }
        return studentAnswers;
    }

    /*
    De korrekte svar som get metode, så det kan hentes i andre klasser.
     */
    public static char[] getCorrectAnswers() {
        char[] characters = {'A', 'B', 'C', 'D', 'B', 'A', 'C', 'B', 'B', 'A'};
        return characters;
    }

}