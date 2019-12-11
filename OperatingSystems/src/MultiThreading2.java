import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreading2 extends Process implements Runnable  {

//    private PipedOutputStream pipedOutPut = new PipedOutputStream();


    public MultiThreading2 (int input, File file, int memoryNeeded, int memoryNeededKB, int framesNeeded, int pagesNeeded,
                            int totalFrames, MutexLock lock, ArrayList<ArrayList<Integer>> framesAssignedWithProcess,
                            int[] arr, Stack<Integer> frameSizeStack, int[] processID, int pid,
                            int i, int j, int frameSize, int pageSizeKB, ArrayList<Integer> addFrames,
                            int[] arrivalTimeInReady, int[] burstTimeCPU, int[]kBurstTimeCPU, int[] kBurst) throws FileNotFoundException {

        Scanner scan = new Scanner(file);
        int criticalSection = 0;
        int counter = 0;

        while (scan.hasNextLine()) {

            processID[j] = pid;
            String word;
            word = scan.next();

            if (word.equals("CALCULATE") || word.equals("I/O")) {

                String number = scan.next();
                int numInt = Integer.parseInt(number);
                arr[i] = numInt + (int) (getRandomInt(numInt * (-0.9), numInt * (0.9)));

                if(word.equals("CALCULATE")) {
                    criticalSection++;
                    lock.acquire();
                }
            }

            lock.release();

            if(word.equals("Memory:")) {

                String memoryNum = scan.next();
                System.out.println(memoryNum + "MB of memory has been processed for process ID " + pid);
                memoryNeeded = Integer.parseInt(memoryNum); //90 MB
                super.setMemoryNeeded(Integer.parseInt(memoryNum));
                memoryNeededKB = memoryNeeded * 1000; //90000
                framesNeeded = (int) Math.ceil(memoryNeededKB/frameSize); //23
                pagesNeeded = (int) Math.ceil(memoryNeededKB/pageSizeKB);
                totalFrames = totalFrames - framesNeeded;

                if(!frameSizeStack.empty() && frameSizeStack.size() >= framesNeeded) { //AVAILABLE SPACE IN MEMORY

                    for(int k = 0; k < framesNeeded; k++) {
                        addFrames.add(frameSizeStack.pop()); //23 frames total
                    }
                    framesAssignedWithProcess.add(addFrames);
                    System.out.println(addFrames.size() + " frames were added to memory.");
                }
                else{
                    System.out.println("There is no space in memory for Process ID " + pid);
                }
            }
        }

        counter++;
        System.out.println("Process ID " + pid + " was in critical section " + criticalSection + " times.");
        super.setCriticalSection(criticalSection);
        processID[j] = pid;
        super.setPid(pid);
        arrivalTimeInReady[j] = 2; /*(int)getRandomInt(1, input*4);*/
        burstTimeCPU[j] = 3; /*(int)getRandomInt(1, 20);*/
        kBurst[j] = burstTimeCPU[j];
        super.setJValue(j);
        AddressTranslator.main();
        System.out.println();
    }

    @Override
    public void run() {
    }

    private static double getRandomInt(double min, double max) {
        double randomInteger = (int) (Math.random() * ((max - min) + 1)) + min;
        return randomInteger;
    }

    public class pipedOutputThread implements Runnable {

        public pipedOutputThread() {
            if(getCount() == getInput()) {
                try {
                    pipedOutPut.write(("Inter-process Communication Successful between two parallel processes.").getBytes());
                    pipedOutPut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        @Override
        public void run() {


        }
    }
}

