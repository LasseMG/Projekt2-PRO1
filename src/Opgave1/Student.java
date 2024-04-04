package Opgave1;

import java.util.ArrayList;
import java.util.Arrays;

public class Student {
    private String name;
    private boolean active;
    private int[] grades;
    private ArrayList<Team> studentArrayList;
    private static char[] studentAnswers;

    private double gradeAverage;

    private int correctAnswers;

    //Constructor for student
    public Student(String name, boolean active, int[] grades) {
        this.name = name;
        this.active = active;
        this.grades = grades;
        this.studentArrayList = new ArrayList<>(); //Husk at lav et tomt array i constructor, ellers er der ingenting at smide student objekter ind i (NullPointerException)
        this.studentAnswers = MultipleChoiceTest.generateStudentAnswers();
    }

    //Getters

    public char[] getStudentAnswers() {
        return studentAnswers;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public String getName() {
        return name;
    }

    public double getGradeAverage() {
        return gradeAverage;
    }

    public boolean isActive() {
        return active;
    }



    //Metoder

    //Den højeste karakter
    public int biggestGradeCalc() {
        int highestGrade = -10;
        for (int grade : grades) {
            if (grade > highestGrade) {
                highestGrade = grade;
            }
        }
        return highestGrade;
    }

    //Gennemsnit af grades
    public double averageGradeCalc(int[] grades) {

        for (int grade : grades) {
            this.gradeAverage += grade;
        }
        gradeAverage /= grades.length;
        return gradeAverage;
    }

    //Korrekte svar på test
    /*
    Tager student som parameter, henter dets svar på test, sammenholder med char i MCT klasse array
    + 1 til tæller hvis de er ens.
     */
    public int correctAnswersCount(char[] studentAnswers) {
        int correctAnswersNumber = 0;
        for (int i = 0; i < MultipleChoiceTest.getCorrectAnswers().length; i++) {
            if (studentAnswers[i] == MultipleChoiceTest.getCorrectAnswers()[i]) {
                correctAnswersNumber++;

            }
        }
        correctAnswers = correctAnswersNumber;
        return correctAnswersNumber;
    }

    //ToString
    @Override
    public String toString() {
        return "Student: name: " + name + ", " + "active: " + active + ", grades: " + Arrays.toString(grades)
                + " average grade: " + averageGradeCalc(grades) + " test answers: "
                + Arrays.toString(studentAnswers) + " correct answers: " + correctAnswersCount(studentAnswers) + "Team: ";
    }
}
