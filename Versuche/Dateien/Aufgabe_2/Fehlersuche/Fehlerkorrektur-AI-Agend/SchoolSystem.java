import java.util.Scanner;

public class SchoolSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Anzahl der Schüler:");
        int students = sc.nextInt();

        System.out.println("Anzahl der Prüfungen:");
        int exams = sc.nextInt();

        int[][] grades = new int[students][exams];

        inputGrades(grades, sc);

        printTable(grades);

        double classAverage = calculateClassAverage(grades);
        System.out.println("Durchschnitt der Klasse: " + classAverage);

        int best = findBestStudent(grades);
        System.out.println("Bester Schüler: " + best);

        int worst = findWorstStudent(grades);
        System.out.println("Schlechtester Schüler: " + worst);

        System.out.println("Anzahl bestandener Schüler: " + countPassedStudents(grades));

        sc.close();
    }

    public static void inputGrades(int[][] grades, Scanner sc) {

        for (int i = 0; i < grades.length; i++) {
            for (int j = 0; j < grades[i].length; j++) {

                int grade;
                do {
                    System.out.println("Schüler " + i + " Prüfung " + j + ":");
                    grade = sc.nextInt();
                    if (grade < 1 || grade > 6) {
                        System.out.println("Ungültige Note! Bitte zwischen 1 und 6 eingeben.");
                    }
                } while (grade < 1 || grade > 6);

                grades[i][j] = grade;
            }
        }
    }

    public static void printTable(int[][] grades) {

        for (int i = 0; i < grades.length; i++) {

            for (int j = 0; j < grades[i].length; j++) {
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
                sum += grades[i][j];
                count++;
            }
        }

        return count == 0 ? 0.0 : (double) sum / count;
    }

    public static int findBestStudent(int[][] grades) {

        // im deutschen System ist 1 die beste Note, also suchen wir den niedrigsten Durchschnitt
        double bestAverage = Double.MAX_VALUE;
        int bestIndex = -1;

        for (int i = 0; i < grades.length; i++) {

            double avg = calculateStudentAverage(grades[i]);

            if (avg < bestAverage) {
                bestAverage = avg;
                bestIndex = i;
            }
        }

        return bestIndex;
    }

    public static int findWorstStudent(int[][] grades) {

        // höchster Durchschnitt bedeutet schlechteste Leistung im deutschen System
        double worstAverage = -1;
        int worstIndex = -1;

        for (int i = 0; i < grades.length; i++) {

            double avg = calculateStudentAverage(grades[i]);

            if (avg > worstAverage) {
                worstAverage = avg;
                worstIndex = i;
            }
        }

        return worstIndex;
    }

    public static double calculateStudentAverage(int[] grades) {

        int sum = 0;

        for (int i = 0; i < grades.length; i++) {
            sum += grades[i];
        }

        return grades.length == 0 ? 0.0 : (double) sum / grades.length;
    }

    public static int countPassedStudents(int[][] grades) {

        int passed = 0;

        for (int i = 0; i < grades.length; i++) {

            double avg = calculateStudentAverage(grades[i]);

            if (avg <= 4.0) {
                passed++;
            }
        }

        return passed;
    }
}