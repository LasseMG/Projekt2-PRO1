package Opgave123;

public class App {
    public static void main(String[] args) {
        //Lav teams
        Team team1 = new Team("24Y", "A28");
        Team team2 = new Team("24X", "B28");

        //Lav student objekter
        Student student1 = new Student("Anders", false, new int[]{2, 2, -3, 0});
        Student student2 = new Student("Oliver", true, new int[]{7, 7, 7, 7});
        Student student3 = new Student("Frederik", true, new int[]{10, 12, 10, 10});
        Student student4 = new Student("Lasse", false, new int[]{4, 4, 4, 7});
        Student student5 = new Student("Emilie", true, new int[]{4, 10, -3, 2});
        Student student6 = new Student("Mathilde", true, new int[]{4, 10, 12, 12});

        //Tilføj alle students til respektive teams
        team1.addStudent(student1);
        team1.addStudent(student2);
        team1.addStudent(student3);
        team2.addStudent(student4);
        team2.addStudent(student5);
        team2.addStudent(student6);

        //Print team 1, inkl. ikke-aktive
        System.out.println("Team 1: ");
        team1.toPrint();
        System.out.println();

        //Print team 2, inkl. ikke-aktive
        System.out.println("Team 2:");
        team2.toPrint();
        System.out.println();

        //Fjern en student, print team 2 igen
        team1.removeStudent("Anders");
        System.out.println("Team 1:");
        team1.toPrint();
        System.out.println();

        //Lav array med kun de aktive students
        System.out.println("Kun aktive students: ");
        System.out.println("Team 1: ");
        team1.toPrintActive();
        System.out.println();
        System.out.println("Team 2: ");
        team2.toPrintActive();
        System.out.println();

        System.out.println("Teams: ");
        System.out.println(team1);
        System.out.println(team2);
        System.out.println();

        //2.7: Kolonner med info
        System.out.println("Student information table: ");
        team1.printStudentRows();
        team2.printStudentRows();
        System.out.println();

        //2.8 Svarfordeling
        System.out.println("Svarfordeling: ");
        team1.printAnswerChecker();

    }
}
