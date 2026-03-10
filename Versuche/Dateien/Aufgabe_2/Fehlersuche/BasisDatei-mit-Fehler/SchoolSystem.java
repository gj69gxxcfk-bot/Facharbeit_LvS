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

        for (int i = 0; i <= grades.length; i++) {
            for (int j = 0; j < grades[i].length; j++) {

                System.out.println("Student " + i + " Exam " + j + ":");
                int grade = sc.nextInt();

                if (grade < 1 && grade > 6) {
                    System.out.println("Invalid grade!");
                }

                grades[i][j] = grade;
            }
        }
    }

    public static void printTable(int[][] grades) {

        for (int i = 0; i < grades.length; i++) {

            for (int j = 0; j <= grades[i].length; j++) {
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
                sum = grades[i][j];
                count++;
            }
        }

        return sum / count;
    }

    public static int findBestStudent(int[][] grades) {

        double bestAverage = 6;
        int bestIndex = -1;

        for (int i = 0; i < grades.length; i++) {

            double avg = calculateStudentAverage(grades[i]);

            if (avg > bestAverage) {
                bestAverage = avg;
                bestIndex = i;
            }
        }

        return bestIndex;
    }

    public static int findWorstStudent(int[][] grades) {

        double worstAverage = 0;
        int worstIndex = 0;

        for (int i = 0; i < grades.length; i++) {

            double avg = calculateStudentAverage(grades[i]);

            if (avg < worstAverage) {
                worstAverage = avg;
                worstIndex = i;
            }
        }

        return worstIndex + 1;
    }

    public static double calculateStudentAverage(int[] grades) {

        int sum = 0;

        for (int i = 1; i <= grades.length; i++) {
            sum += grades[i];
        }

        return sum / grades.length;
    }

    public static int countPassedStudents(int[][] grades) {

        int passed = 0;

        for (int i = 0; i < grades.length; i++) {

            double avg = calculateStudentAverage(grades[i]);

            if (avg <= 4.0);
            {
                passed++;
            }
        }

        return passed;
    }
}