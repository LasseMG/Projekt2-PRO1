package Opgave1;
import java.util.Objects;
import java.util.Scanner;


public class App3b {
    static Scanner input = new Scanner(System.in);

    /*
    Som start udskrives valgmuligheder. Herefter kører menuSelect, som opfanger hvad brugeren vil.
     */
    public static void main(String[] args) {
        System.out.println("MENU");
        System.out.println("1: Create team");
        System.out.println("2: Create student");
        System.out.println("3: Show student info and results");
        System.out.println("4: Show team info and results");
        System.out.println("5: Show info and results for all teams");
        System.out.println("6: Exit program");
        menuSelect();
    }

    /*
    Fungerer som en menu, hvor hvert tal 1-6 kalder en metode eller stopper programmet.
     */
    public static void menuSelect() {
        int userInput = input.nextInt();

        if (userInput == 1) {
            createTeamPrompt(); //Eks. taster man 1 for Create team, kører createTeam metode
        } else if (userInput == 2) {
            Team team = null; //Laver et tomt objekt, som kan blive udfyldt
            createStudent(team); //Smid den student i det team, som dannes i en metode senere
        } else if (userInput == 3) {
            //Metode 3 (3.B)
        } else if (userInput == 4) {
            //Metode 4 (3.B)
        } else if (userInput == 5) {
            //Metode 5 (3.B)
        } else if (userInput == 6) {
            //Metode 6 (3.B)
        } else {
            System.out.println("Invalid input.");
        }
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
            System.out.println("Invalid input.");
        }
    }

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
        return team;
    }

    /*
    createStudent metode. Navn, aktiv, antal karakterer, og hvilke karakterer.
    Ud fra dette, smides informationen i en constructor, som danner den nye studerende.
     */
    public static Student createStudent(Team team) {
        System.out.println("Navn på studerende?");
        input.nextLine();
        String studentName = input.nextLine();
        System.out.println("Er den studerende aktiv?");
        boolean studentActive = input.nextBoolean();
        System.out.println("Hvor mange karakterer har den studerende fået?");
        int numberOfGrades = input.nextInt();
        System.out.println("Hvilke karakterer har den studerende fået? Skriv et tal, og så ENTER.");
        int[] studentGrades = new int[numberOfGrades];
        for (int x = 0; x < numberOfGrades; x++) {
            studentGrades[x] = input.nextInt();
        }

        Student student = new Student(studentName, studentActive, studentGrades);

        /*
        Herefter spørges der om, hvilket team student skal i. Den viser mulige teams (navnet på det team, vi har lavet ovenfor),
        og indtaster man navnet på det team, vil student tilføjes hertil. Derefter kommer en bekræftelse.
        Indtaster man et navn på et team som ikke eksisterer, udskriver den en fejlmeddelelse.
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
        System.out.println(team);
        return student;
    }
}