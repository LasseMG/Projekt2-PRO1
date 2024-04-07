package Opgave1;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class App3b {
    static Scanner input = new Scanner(System.in);

    /*
    Som start udskrives valgmuligheder. Herefter kører menuSelect, som opfanger hvad brugeren vil.
     */
    public static void main(String[] args) {
        boolean stop = false;
        while (!stop) {
            menu();
            stop = menuSelect(stop);
        }
    }

    public static void menu() {
        System.out.println("MENU");
        System.out.println("1: Create team");
        System.out.println("2: Create student");
        System.out.println("3: Show student info and results");
        System.out.println("4: Show team info and results");
        System.out.println("5: Show info and results for all teams");
        System.out.println("6: Exit program");
    }

    /*
    Fungerer som en menu, hvor hvert tal 1-6 kalder en metode.
     */
    public static boolean menuSelect(boolean stop) {
        int userInput = input.nextInt();

        if (userInput == 1) {
            createTeamPrompt(); //Eks. taster man 1 for Create team, kører createTeam metode
        } else if (userInput == 2) {
            Team team = null; //Laver et tomt objekt, som kan blive udfyldt
            createStudent(team); //Smid den student i det team, som dannes i en metode senere
        } else if (userInput == 3) {
            showStudentInfo(students);
        } else if (userInput == 4) {
            showTeamInfo(teams);
        } else if (userInput == 5) {
            showAllTeamsInfo(teams);
        } else if (userInput == 6) {
            stop = true;
        } else {
            System.out.println("Ugyldigt input.");
        }
        return stop;
    }

    /*
    En prompt som spørger, om du vil lave et team. Hvis ja, kør createTeam metode.
     */
    public static void createTeamPrompt() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vil du oprette et team? y/n");
        String userInput = input.nextLine();

        if (Objects.equals(userInput, "y")) {
            createTeam();
        } else if (Objects.equals(userInput, "n")) {
            //Kør
        } else {
            System.out.println("Ugyldigt input.");
        }
    }

    //ArrayList til at holde på teams
    private static ArrayList<Team> teams = new ArrayList<>();
    /*
    createTeam metode. Prompter navn, værelse, rum, samt hvor mange students. Er der et antal students,
    kører createStudents det antal gange, så der laves så mange studerende.
    Metoden tilføjer studerende til det team som er oprettet på linje 82.
     */
    public static Team createTeam() {
        Scanner input = new Scanner(System.in);

        System.out.println("Hvad skal dit team hedde?");
        String teamName = input.nextLine();

        System.out.println("Hvilket værelse skal dit team tilhøre?");
        String roomName = input.nextLine();

        System.out.println("Hvor mange studerende i teamet?");
        int numberOfStudents = input.nextInt();

        Team team = new Team(teamName, roomName);

        for (int i = 0; i < numberOfStudents; i++) {
            Student student = createStudent(team);
            team.addStudent(student);
        }
        teams.add(team);
        return team;
    }

    //ArrayList til at holde på students
    private static ArrayList<Student> students = new ArrayList<>();
    /*
    createStudent metode. Navn, aktiv, antal karakterer, og hvilke karakterer.
    Ud fra dette, smides informationen i en constructor, som danner den nye studerende.
     */
    public static Student createStudent(Team team) {
        System.out.println("Navn på studerende?");
        input.nextLine();
        String studentName = input.nextLine();
        System.out.println("Er den studerende aktiv? (true/false)");
        boolean studentActive = input.nextBoolean();
        System.out.println("Hvor mange karakterer har den studerende fået?");
        int numberOfGrades = input.nextInt();
        System.out.println("Hvilke karakterer har den studerende fået? Skriv et tal, og så ENTER.");
        int[] studentGrades = new int[numberOfGrades];
        for (int x = 0; x < numberOfGrades; x++) {
            studentGrades[x] = input.nextInt();
        }

        Student student = new Student(studentName, studentActive, studentGrades);
        students.add(student); //Tilføj studerende til ArrayList når oprettet

        /*
        Herefter spørges der om, hvilket team student skal i. Den viser mulige teams (navnet på det team, vi har lavet ovenfor),
        og indtaster man navnet på det team, vil student tilføjes hertil. Derefter kommer en bekræftelse.
        Indtaster man et navn på et team som ikke eksisterer, udskriver den en fejlmeddelelse.
        Sker kun hvis der er oprettet et team, altså !null.
         */
        if (team != null) {
            System.out.println("Til hvilket team skal den studerende tilføjes? (" + team.getName() + ")");
            String inputTeamName = input.next();
            if (inputTeamName.equals(team.getName())) {
                team.addStudent(student);
                System.out.println("Studenten er blevet tilføjet til " + team.getName() + " teamet.");
            } else {
                System.out.println("Fejl: Det indtastede holdnavn matcher ikke det angivne hold.");
            }
        }

//        //Print team, hvis der er et, ellers kun student, hvis der ikke er et team. Blot for at undgå,
//        //der udskrives null efter der er oprettet en student, men ikke et team.
//        if (team == null) {
//            System.out.println(student);
//        } else {
//            System.out.println(team);
//        }
        return student;
    }

    /*
    Første del af denne metode viser en liste over student objekter, som man kan vælge imellem.
    I anden del kan man indtaste et tal, som henviser til et index for et student objekt i
    student ArrayList. Derefter printes student objekt via. toString metoden.
     */
    public static void showStudentInfo(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("Ingen studerende at tilgå endnu.");
        } else {
            System.out.println("Hvilken studerendes information vil du se?");
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i).getName());
            }
        }

        //Anden del af metoden
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast nummeret på den studerende, du vil se information om.");
        int selectedStudentIndex = input.nextInt();

        if (selectedStudentIndex < 1 || selectedStudentIndex > students.size()) {
            System.out.println("Ugyldigt valg.");
        } else {
            Student selectedStudent = students.get(selectedStudentIndex - 1);
            System.out.println(selectedStudent);
        }
    }

    /*
    Samme metode/strategi som ved metoden til at vise student info.
     */
    public static void showTeamInfo(ArrayList<Team> teams) {
        if (teams.isEmpty()) {
            System.out.println("Ingen teams at tilgå endnu.");
        } else {
            System.out.println("Hvilket teams information vil du se?");
            for (int i = 0; i < teams.size(); i++) {
                System.out.println((i + 1) + ". " + teams.get(i).getName());
            }
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Indtast nummeret på det team, du vil se information om.");
        int selectedTeamIndex = input.nextInt();

        if (selectedTeamIndex < 1 || selectedTeamIndex > students.size()) {
            System.out.println("Ugyldigt valg.");
        } else {
            Team selectedTeam = teams.get(selectedTeamIndex - 1);
            System.out.println(selectedTeam);
        }
    }

    public static void showAllTeamsInfo(ArrayList<Team> teams) {
        for (Team team : teams) {
            System.out.println(team);
        }
    }
}