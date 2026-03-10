import java.util.Scanner;

public class SchoolSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Number of students:");
        int students = sc.nextInt();

        System.out.println("Number of exams:");
        int exams = sc.nextInt();

        int[][] grades = new int[students][exams];

        inputGrades(grades, sc);

        printTable(grades);

        double classAverage = calculateClassAverage(grades);
        System.out.println("Class average: " + classAverage);

        int best = findBestStudent(grades);
        System.out.println("Best student: " + best);

        int worst = findWorstStudent(grades);
        System.out.println("Worst student: " + worst);

        System.out.println("Students passed: " + countPassedStudents(grades));

        sc.close();
    }

    public static void inputGrades(int[][] grades, Scanner sc) {

        // Schleifen müssen < verwenden, sonst geht `i == grades.length` über das
        // Ende des Arrays hinaus und löst eine Ausnahme aus.
        for (int i = 0; i < grades.length; i++) {           // ***
            for (int j = 0; j < grades[i].length; j++) {     // ***
            
                int grade ;

                // Die Bedingung „< 1 UND > 6“ ist niemals wahr; eine Note
                // kann nicht gleichzeitig kleiner als 1 und größer als 6 sein.
                // Verwende OR, um beide Grenzen zu prüfen.
                // solange die Note außerhalb des Bereichs liegt, erneut einlesen
            do {
                System.out.print("Student " + i + " Exam " + j + ": ");
                grade = sc.nextInt();
                if (grade < 1 || grade > 6) {
                    System.out.println("Invalid grade, please enter a number between 1 and 6.");
                }
            } while (grade < 1 || grade > 6);      // *** Wiederholung bei Fehler
                grades[i][j] = grade;
            }
        }
    }

    public static void printTable(int[][] grades) {

        for (int i = 0; i < grades.length; i++) {
            for (int j = 0; j < grades[i].length; j++) {      // ***
                System.out.print(grades[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static double calculateClassAverage(int[][] grades) {

        int sum = 0;
        int count = 0;

        for (int i = 0; i < grades.length; i++) {
            for (int j = 0; j < grades[i].length; j++) {
                sum += grades[i][j];                         // ***
                count++;
            }
        }

        // Ganzzahldivision würde den Bruch abschneiden; cast auf double
        return (double) sum / count;                        // ***
    }

    public static int findBestStudent(int[][] grades) {

        // will man die kleinstmögliche Durchschnittsnote („best“) finden,
        // initialisiert man mit einem sehr großen Wert;
        // ansonsten ist 6 nicht sinnvoll, weil Noten im Bereich 1–6 liegen.
        double bestAverage = Double.MAX_VALUE;               // ***
        int bestIndex = -1;

        for (int i = 0; i < grades.length; i++) {
            double avg = calculateStudentAverage(grades[i]);
            if (avg < bestAverage) {                        // *** umgedreht
                bestAverage = avg;
                bestIndex = i;
            }
        }
        return bestIndex;                                   // 0‑basierter Index
    }

    public static int findWorstStudent(int[][] grades) {

        double worstAverage = Double.MIN_VALUE;              // ***
        int worstIndex = -1;

        for (int i = 0; i < grades.length; i++) {
            double avg = calculateStudentAverage(grades[i]);
            if (avg > worstAverage) {                       // ***
                worstAverage = avg;
                worstIndex = i;
            }
        }
        return worstIndex;
    }

    public static double calculateStudentAverage(int[] grades) {

        int sum = 0;
        // auch hier: 0..length‑1, sonst OOB
        for (int i = 0; i < grades.length; i++) {           // ***
            sum += grades[i];
        }
        return (double) sum / grades.length;                // ***
    }

    public static int countPassedStudents(int[][] grades) {

        int passed = 0;

        for (int i = 0; i < grades.length; i++) {
            double avg = calculateStudentAverage(grades[i]);

            // das Semikolon nach der if‑Klammer schließt die Bedingung ab,
            // der folgende Block läuft immer – deshalb wurden alle Schüler
            // als „bestanden“ gezählt.
            if (avg <= 4.0)                                  // ***
            {
                passed++;
            }
        }
        return passed;
    }
}