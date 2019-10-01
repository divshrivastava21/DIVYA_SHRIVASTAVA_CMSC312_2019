import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class OperatingSystems {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter file name: ");
        String fileName = in.nextLine();
        File file = new File (fileName);
        Scanner scan = new Scanner(file);
        System.out.println("How many processes would you like for each")

    }
}