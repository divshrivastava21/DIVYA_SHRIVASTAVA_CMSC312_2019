import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Process extends SJFScheduler {


    public static void main(Scanner in, int input, Scanner scan, int[] arr, Scanner scan2, int[] arr2, Scanner scan3,
                            int[] arr3, Scanner scan4, int[] arr4) throws FileNotFoundException {

        int j = 0;
        int pid = 5000;
        //int totalMemoryMB = 4096; //KB = 4096000 --> 4096000000
        int memoryNeeded = 0;
        int memoryNeededKB = 0;
        ArrayList<ArrayList<Integer>> framesAssignedWithProcess = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> addFrames = new ArrayList<>();
        int totalFrames = 4096000/4000;
        int frameSize = 4000;
        int pageSizeKB = frameSize;
        int pagesNeeded = 0; //(int) Math.ceil(memoryNeeded/pageSizeKB);
        int pageTableMemory = (int) Math.floor(4096000/4000);
        int[] pageTable = new int[pageTableMemory]; //1024 entries
//      int totalMemoryKB = 4096000 - pageTableMemory;
        int framesNeeded = 0;
        Stack <Integer> frameSizeStack = new Stack<>();
        int[] processID = new int[input*4];
        int[] arrivalTimeInReady = new int[input*4];
        int[] burstTimeCPU = new int[input*4];
        int[]kBurstTimeCPU = new int[input*4];
        int[] completionTime = new int[input*4];
        int[] turnAroundTimeDuration = new int[input*4];
        int[] waitingTime = new int[input*4];
        int[] flag = new int[input*4];
        int[] kBurst = new int[input*4];
        int criticalSection = 0;
        MutexLock lock = new MutexLock();


        for (int i = 0; i <= totalFrames; i++) {
            frameSizeStack.push(i);
        }

        for (int i = 0; i < input; i++) {

            while (scan.hasNextLine()) {

                processID[j] = pid;
                String word;
                word = scan.next();

                if (word.equals("CALCULATE") || word.equals("I/O")) {

                    String number = scan.next();
                    int numInt = Integer.parseInt(number);
                    arr[i] = numInt + (int) (getRandomInt(numInt * (-0.4), numInt * (0.4)));

                    if(word.equals("CALCULATE")) {
                        criticalSection++;
                        lock.acquire();
                        lock.release();
                    }
                }
                if(word.equals("Memory:")) {

                    String memoryNum = scan.next();
                    System.out.println(memoryNum + "MB of memory has been processed");
                    memoryNeeded = Integer.parseInt(memoryNum); //90 MB
                    memoryNeededKB = memoryNeeded * 1000; //90000
                    framesNeeded = (int) Math.ceil(memoryNeededKB/frameSize); //23
                    pagesNeeded = (int) Math.ceil(memoryNeededKB/pageSizeKB);
                    totalFrames = totalFrames - framesNeeded;

                    if(!frameSizeStack.empty() && frameSizeStack.size() >= framesNeeded) { //AVAILABLE SPACE IN MEMORY

                        for(int k = 0; k < framesNeeded; k++) {
                            addFrames.add(frameSizeStack.pop()); //23 frames total
                        }
                        framesAssignedWithProcess.add(addFrames);
                        System.out.println("For process ID " + pid + ", " + addFrames.size() + " frames " +
                                "were added to memory.");
                    }
                    else{
                        System.out.println("There is no space in memory for Process ID " + pid);
                    }
                }
                scan.nextLine();
            }

            System.out.println("Process ID " + pid + " was in critical section " + criticalSection + " times.");
            pid += 1; //5001
            criticalSection = 0;
            System.out.println("Enter process " + j + " arrival time: ");
            arrivalTimeInReady[j] = in.nextInt();
            System.out.println("Enter process " + j + " burst time: ");
            burstTimeCPU[j] = in.nextInt();
            kBurst[j] = burstTimeCPU[j];
            j = j + 1; //1

            while (scan2.hasNextLine()) {

                processID[j] = pid; //5001
                String word;
                word = scan2.next();

                if (word.equals("CALCULATE") || word.equals("I/O")) {

                    String number = scan2.next();
                    int numInt = Integer.parseInt(number);
                    arr2[i] = numInt + (int) (getRandomInt(numInt * (-0.9), numInt * (0.9)));

                    if(word.equals("CALCULATE")) {
                        criticalSection++;
                        lock.acquire();
                    }
                }
                if(word.equals("Memory:")) {

                    String memoryNum = scan2.next();
                    System.out.println(memoryNum + "MB of memory has been processed");
                    memoryNeeded = Integer.parseInt(memoryNum); //90 MB
                    memoryNeededKB = memoryNeeded * 1000; //90000
                    framesNeeded = (int) Math.ceil(memoryNeededKB/frameSize); //23
                    totalFrames = totalFrames - framesNeeded;

                    if(!frameSizeStack.empty() && frameSizeStack.size() >= framesNeeded) { //AVAILABLE SPACE IN MEMORY

                        for(int k = 0; k < framesNeeded; k++) {

                            addFrames.add(frameSizeStack.pop()); //23 frames total
                        }
                        framesAssignedWithProcess.add(addFrames);
                        System.out.println("For process ID " + pid + ", " + addFrames.size() + " frames " +
                                "were added to memory.");
                    }
                    else{
                        System.out.println("There is no space in memory for Process ID " + pid);
                    }
                }
                lock.release();
            }

            System.out.println("Process ID " + pid + " was in critical section " + criticalSection + " times.");
            pid += 1; //5002
            criticalSection = 0;
            System.out.println("Enter process " + j + " arrival time: ");
            arrivalTimeInReady[j] = in.nextInt();
            System.out.println("Enter process " + j + " burst time: ");
            burstTimeCPU[j] = in.nextInt();
            kBurst[j] = burstTimeCPU[j];
            flag[i] = 0;
            j = j + 1; //2

            while (scan3.hasNextLine()) {

                processID[j] = pid;
                String word;
                word = scan3.next();

                if (word.equals("CALCULATE") || word.equals("I/O")) {

                    String number = scan3.next();
                    int numInt = Integer.parseInt(number);
                    arr3[i] = numInt + (int) (getRandomInt(numInt * (-0.8), numInt * (0.8)));

                    if(word.equals("CALCULATE")) {
                        criticalSection++;
                        lock.acquire();
                        lock.release();
                    }

                }

                if(word.equals("Memory:")) {

                    String memoryNum = scan3.next();
                    System.out.println(memoryNum + "MB of memory has been processed");
                    memoryNeeded = Integer.parseInt(memoryNum); //90 MB
                    memoryNeededKB = memoryNeeded * 1000; //90000
                    framesNeeded = (int) Math.ceil(memoryNeededKB/frameSize); //23
                    totalFrames = totalFrames - framesNeeded;
                    if(!frameSizeStack.empty() && frameSizeStack.size() >= framesNeeded) { //AVAILABLE SPACE IN MEMORY

                        for(int k = 0; k < framesNeeded; k++) {

                            addFrames.add(frameSizeStack.pop()); //23 frames total
                        }
                        framesAssignedWithProcess.add(addFrames);
                        System.out.println("For process ID " + pid + ", " + addFrames.size() + " frames " +
                                "were added to memory.");
                    }
                    else{
                        System.out.println("There is no space in memory for Process ID " + pid);
                    }
                }
            }

            System.out.println("Process ID " + pid + " was in critical section " + criticalSection + " times.");
            pid += 1;
            criticalSection = 0;
            System.out.println("Enter process " + j + " arrival time: ");
            arrivalTimeInReady[j] = in.nextInt();
            System.out.println("Enter process " + j + " burst time: ");
            burstTimeCPU[j] = in.nextInt();
            kBurst[j] = burstTimeCPU[j];
            flag[i] = 0;
            j = j + 1;


            while (scan4.hasNextLine()) {

                processID[j] = pid; //5003
                String word;
                word = scan4.next();

                if (word.equals("CALCULATE") || word.equals("I/O")) {

                    String number = scan4.next();
                    int numInt = Integer.parseInt(number);
                    arr4[i] = numInt + (int) (getRandomInt(numInt * (-0.6), numInt * (0.6)));

                    if(word.equals("CALCULATE")) {
                        criticalSection++;
                        lock.acquire();
                        lock.release();
                    }
                }

                if(word.equals("Memory:")) {

                    String memoryNum = scan4.next();
                    System.out.println(memoryNum + "MB of memory has been processed");
                    memoryNeeded = Integer.parseInt(memoryNum); //90 MB
                    memoryNeededKB = memoryNeeded * 1000; //90000
                    framesNeeded = (int) Math.ceil(memoryNeededKB/frameSize); //23
                    totalFrames = totalFrames - framesNeeded;

                    if(!frameSizeStack.empty() && frameSizeStack.size() >= framesNeeded) { //AVAILABLE SPACE IN MEMORY

                        for(int k = 0; k < framesNeeded; k++) {

                            addFrames.add(frameSizeStack.pop()); //23 frames total
                        }
                        framesAssignedWithProcess.add(addFrames);
                        System.out.println("For process ID " + pid + ", " + addFrames.size() + " frames " +
                                "were added to memory.");
                    }
                    else{
                        System.out.println("There is no space in memory for Process ID " + pid);
                    }
                }
            }

            System.out.println("Process ID " + pid + " was in critical section " + criticalSection + " times.");
            pid += 1; //5004
            criticalSection = 0;
            System.out.println("Enter process " + j + " arrival time: ");
            arrivalTimeInReady[j] = in.nextInt();
            System.out.println("enter process " + j + " burst time: ");
            burstTimeCPU[j] = in.nextInt();
            kBurst[j] = burstTimeCPU[j];
            flag[i] = 0;
            j = j + 1;
        }

        in.close();

        for(int i = 0; i < input*4; i++) {
            kBurstTimeCPU[i] = burstTimeCPU[i];
        }

        SJFScheduler.main(input, arrivalTimeInReady, kBurstTimeCPU, processID, completionTime, turnAroundTimeDuration,
                waitingTime, kBurst, flag);

        System.out.println();
        System.out.println();
        System.out.println("The following shows the processes being processed through Round Robin Scheduler: ");
        RRScheduler.main(input, processID, burstTimeCPU, waitingTime, turnAroundTimeDuration);

    }

    private static double getRandomInt(double min, double max) {
        double randomInteger = (int) (Math.random() * ((max - min) + 1)) + min;
        return randomInteger;
    }
}
