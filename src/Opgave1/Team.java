package Opgave1;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Team {
    private String name;
    private String room;
    private ArrayList<Student> studentArrayList; //Opgave 1.3 : ArrayList<Student>
    private ArrayList<Student> activeStudentsList;
    private ArrayList<Student> highScoreStudentsList;
    private ArrayList<Student> correctAnswersList;

    //Constructor
    public Team(String name, String room) {
        this.name = name;
        this.room = room;
        this.studentArrayList = new ArrayList<>();
        this.activeStudentsList = new ArrayList<>();
        this.highScoreStudentsList = new ArrayList<>();
        this.correctAnswersList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Student> getStudents() {
        return studentArrayList;
    }

    public ArrayList<Student> getActiveStudentsList() {
        return activeStudentsList;
    }

    //Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setStudentArrayList(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }

    public void setActiveStudentsList(ArrayList<Student> activeStudentsList) {
        this.activeStudentsList = activeStudentsList;
    }

    public void setHighScoreStudentsList(ArrayList<Student> highScoreStudentsList) {
        this.highScoreStudentsList = highScoreStudentsList;
    }

    public void setCorrectAnswersList(ArrayList<Student> correctAnswersList) {
        this.correctAnswersList = correctAnswersList;
    }

//Metoder

    //Add student metode
    /*
    Tilføjer kun et student objekt, hvis de er studieaktive, boolean active = true
     */
    public void addStudent(Student student) {
        this.studentArrayList.add(student);
    }

    //Returner alle aktive students
    public void activeStudents() {
        activeStudentsList = new ArrayList<>();
        for (Student student : studentArrayList) {
            if (student.isActive()) {
                activeStudentsList.add(student);
            }
        }
    }

    //Fjern students
    /*
    Skriver et navn ind, hvis et navn passer til navn på et objekt, fjern dette
     */
    public void removeStudent(String name) {
        int index = 0;

        while (index < studentArrayList.size()) {

            String studentName = studentArrayList.get(index).getName();

            if (studentName.equals(name)) {
                studentArrayList.remove(index);
            } else {
                index++;
            }
        }
    }

    //Grade average for alle students
    public double allStudentsAverageGrade() {
        double totalGradeAverage = 0;
        for (Student student : studentArrayList) {
            totalGradeAverage += student.getGradeAverage();
        }
        return totalGradeAverage;
    }

    //Students med et gennemsnit over minAverage, i et array og ikke arrayList
    public Student[] highScoreStudents(double minAverage) {
        highScoreStudentsList = new ArrayList<>();

        for (Student student : activeStudentsList) {
            if (student.getGradeAverage() >= minAverage) {
                highScoreStudentsList.add(student);
            }
        }

        Student[] highScoreStudentsArray = new Student[highScoreStudentsList.size()];
        highScoreStudentsArray = highScoreStudentsList.toArray(highScoreStudentsArray);

        return highScoreStudentsArray;
    }

    //Array med de studerendes korrekte svar
    public ArrayList<Integer> studentsCorrectAnswers(ArrayList<Student> studentArrayList) {
        ArrayList<Integer> correctAnswersList = new ArrayList<>();

        for (int i = 0; i < studentArrayList.size(); i++) {
            Student student = studentArrayList.get(i);
            int correctAnswers = student.getCorrectAnswers();
            correctAnswersList.add(correctAnswers);
        }
        return correctAnswersList;
    }

    //Lav arrays, og gem som feltvariabel
    public ArrayList<String> studentToArray() {
        ArrayList<String> studentRows = new ArrayList<>(); //Lav nyt array til strings

        for (Student student : studentArrayList) {
            String studentRow = String.format("%-20s %-15.1f %-10d", student.getName(), student.getGradeAverage(), student.getCorrectAnswers()); //Lav en string til array + formater
            studentRows.add(studentRow); //Tilføj til array
        }
        return studentRows;
    }

    //Print arrays metode
    public void printStudentRows() {
        ArrayList<String> studentRows = studentToArray();
        System.out.printf("%-15s %-15s %-15s\n", "Name", "Grade average", "Correct answers");
        System.out.println("---------------------------------------------------------------");

        for (String studentRow : studentRows) {
            System.out.println(studentRow);
        }
    }

    public int[] answerChecker() {

        char[] correctAnswers = MultipleChoiceTest.getCorrectAnswers();
        int[] correctAnswerCounter = new int[correctAnswers.length];

        for (int i = 0; i < correctAnswers.length; i++) {
            int counter = 0;
            for (Student student : studentArrayList) {
                if (student.getStudentAnswers()[i] == correctAnswers[i]) {
                    counter++;
                }
            }
            correctAnswerCounter[i] = counter;
        }
        return correctAnswerCounter;
    }

    public void printAnswerChecker() {
        int[] correctAnswerdQuestArray = answerChecker();


        System.out.println("---------------------------------");
        System.out.println("Spg. nr: \tKorrekte svar: ");

        for (int i = 0; i < correctAnswerdQuestArray.length; i++) {

            if (i < correctAnswerdQuestArray.length) {
                System.out.printf("%d\t\t\t%d\n", i + 1, correctAnswerdQuestArray[i]);
            }
        }
    }

    //Print metoder
    public void toPrint() {
        for (Student student : studentArrayList) {
            System.out.println(student);
        }
    }

    public void toPrintActive() {
        activeStudents();
        for (Student student : activeStudentsList) {
            System.out.println(student);
        }
    }
    @Override
    public String toString() {
        toPrint();
        return "Team: " + name + " high score students: " + Arrays.toString(highScoreStudents(3))
                + " correct answers from each student: " + studentsCorrectAnswers(studentArrayList);
    }
}