import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class OperatingSystems extends Process {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);

        File file = new File("TextProcessorV1.txt");
        File file2 = new File("TextProcessorV2.txt");
        File file3 = new File("TextProcessorV3.txt");
        File file4 = new File("TextProcessorV4.txt");

        Scanner scan = new Scanner(file);
        int[] arr = new int[10];
        Scanner scan2 = new Scanner(file2);
        int[] arr2 = new int[10];
        Scanner scan3 = new Scanner(file3);
        int[] arr3 = new int[10];
        Scanner scan4 = new Scanner(file4);
        int[] arr4 = new int[10];

        try {
            System.out.println("How many processes would you like from each program file? Please enter a value greater than 0.");
            int input = in.nextInt();
            if (input > 0) {
                Process.main(in, input, scan, arr, scan2, arr2, scan3, arr3, scan4, arr4);
            } else {
                System.out.println("Number of processes must be greater than 0. Please try again.");
            }
        }
        catch(InputMismatchException e) {
            System.out.println("Please input a numeric value.");
        }
    }
}