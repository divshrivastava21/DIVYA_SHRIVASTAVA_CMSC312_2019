import java.io.File;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;
///import java.

enum State {

    NEW, READY, WAITING, RUNNING, TERMINATED;
}

public class Process extends Scheduler {


    public static void main(Scanner in, int input, Scanner scan, int[] arr, Scanner scan2, int[] arr2, Scanner scan3,
                            int[] arr3, Scanner scan4, int[] arr4) throws FileNotFoundException {
        //Scanner in = new Scanner(System.in);

        int j = 0;
        int pid = 5000;
        int[] processID = new int[input*4];
        int[] arrivalTimeInReady = new int[input*4];
        int[] burstTimeCPU = new int[input*4];
        int[] completionTime = new int[input*4];
        int[] turnAroundTimeDuration = new int[input*4];
        int[] waitingTime = new int[input*4];
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        Date date = new Date();

        for (int i = 0; i < input; i++) {
            while (scan.hasNextLine()) {
                //String line = scan.nextLine();
                //System.out.println(line);
                String word;

                    System.out.println("" + scan.hasNext());
                    word = scan.next();
                    //System.out.println(word);
                    if (word.equals("CALCULATE") || word.equals("I/O")) {
                        String number = scan.next();
                        int numInt = Integer.parseInt(number);
                        arr[i] = numInt + (int) (getRandomInt(numInt * (-0.4), numInt * (0.4)));
                    }

                    scan.nextLine();
            }
            processID[j] = pid; //5000
            pid += 1; //5001

            arrivalTimeInReady[i] = Integer.parseInt(formatter.format(date));
            System.out.println("Enter process " + j + " burst time: ");
            burstTimeCPU[i] = in.nextInt();
            j = j + 1; //1

            while (scan2.hasNextLine()) {
                String word;
//                if (scan.hasNext()) {
//                    word = scan2.next();
//                }
                System.out.println("" + scan2.hasNext());
                word = scan2.next();
                if (word.equals("CALCULATE") || word.equals("I/O")) {
                    String number = scan2.next();
                    int numInt = Integer.parseInt(number);
                    arr2[i] = numInt + (int) (getRandomInt(numInt * (-0.9), numInt * (0.9)));
                }
            }
            processID[j] = pid; //5001
              pid += 1; //5002

            arrivalTimeInReady[j] = Integer.parseInt(formatter.format(date));
            System.out.println("Enter process " + j + " burst time: ");
            burstTimeCPU[j] = in.nextInt();
            j = j + 1; //2

            while (scan3.hasNextLine()) {
                String word;
//                if (scan.hasNext()) {
//                    word = scan3.next();
//                }
                System.out.println("" + scan3.hasNext());
                word = scan3.next();
                //String word = scan3.next();
                if (word.equals("CALCULATE") || word.equals("I/O")) {
                    String number = scan3.next();
                    int numInt = Integer.parseInt(number);
                    arr3[i] = numInt + (int) (getRandomInt(numInt * (-0.8), numInt * (0.8)));
                }
            }
            processID[j] = pid;
              pid += 1;

            arrivalTimeInReady[j] = Integer.parseInt(formatter.format(date));
            System.out.println("Enter process " + j + " burst time: ");
            burstTimeCPU[j] = in.nextInt();
              j = j + 1;
            while (scan4.hasNextLine()) {
                String word;
//                if (scan.hasNext()) {
//                    word = scan4.next();
//                }
                System.out.println("" + scan4.hasNext());
                word = scan4.next();
                //String word = scan4.next();
                if (word.equals("CALCULATE") || word.equals("I/O")) {
                    String number = scan4.next();
                    int numInt = Integer.parseInt(number);
                    arr4[i] = numInt + (int) (getRandomInt(numInt * (-0.6), numInt * (0.6)));
                }
            }
            processID[j] = pid; //5003
            pid += 1; //5004
            arrivalTimeInReady[j] = Integer.parseInt(formatter.format(date));
            System.out.println("enter process " + j + " burst time: ");
            burstTimeCPU[j] = in.nextInt();
              j = j + 1;
        }
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != 0) {
                System.out.println(arr[i]);
            }
        }
        in.close();

        for(int i = 0; i < processID.length; i++) {
            System.out.println("ProcessID is " + processID[i]);


        }

        //new ProcessControlBlock();
        Scheduler.main(input, arrivalTimeInReady, burstTimeCPU, processID, completionTime, turnAroundTimeDuration,
                waitingTime);

    }

    public static double getRandomInt(double min, double max) {
        double x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

    static class ProcessControlBlock
    {
        public String PCB (int[] arr){

        int pID;
        int runtime;
        State processState = State.NEW;
        int processPointer;
            return "";
    }
    }
}
