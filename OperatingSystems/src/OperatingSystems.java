import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class OperatingSystems extends Process {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner in = new Scanner(System.in);

        File file = new File("TextProcessorV1.txt");
        File file2 = new File("TextProcessorV2.txt");
        File file3 = new File("TextProcessorV3.txt");
        File file4 = new File("TextProcessorV4.txt");

        //Reading in all four files
//        Scanner scan = new Scanner(file);
        int[] arr = new int[100];
//        Scanner scan2 = new Scanner(file2);
        int[] arr2 = new int[100];
//        Scanner scan3 = new Scanner(file3);
        int[] arr3 = new int[100];
//        Scanner scan4 = new Scanner(file4);
        int[] arr4 = new int[100];

            try {
                System.out.println("How many processes would you like from each program file? Please enter a value greater than 0.");
                int input = in.nextInt(); //may randomly assign number myself without user input.
                System.out.println("How many cycles would you like to run");
                int cycles = in.nextInt();
                if (input > 0 && cycles >= 25 && cycles <= 50) { //
                    Process.main(in, input, cycles, arr, arr2, arr3, arr4, file, file2, file3, file4);
                } else {
                    System.out.println("Number of processes must be greater than 0. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input a numeric value.");
            }
    }
}