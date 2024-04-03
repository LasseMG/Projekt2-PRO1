package Opgave1;

import java.lang.reflect.Array;
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

    public ArrayList<Student> getStudents() {
        return studentArrayList;
    }

    public ArrayList<Student> getActiveStudentsList() {
        return activeStudentsList;
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
        return "Team: " + name + " high score students: " + Arrays.toString(highScoreStudents(3))
                + " correct answers from each student: " + studentsCorrectAnswers(studentArrayList);
    }
}