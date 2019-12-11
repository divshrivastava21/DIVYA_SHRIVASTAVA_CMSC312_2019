import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

import static java.lang.Thread.sleep;

public class Process extends SJFScheduler {

    //global variables
    private static int pid = 5000;
    private static int[] memoryNeededGUI = new int[10000];
    private static int[] criticalSection = new int[10000];
    private static int r = 0;
    private static int s = 0;
    private static int j = 0;
    private static int countingInputForPipes = 1;
    private static int inputNum = 0;
    private final static PipedOutputStream pipedOut = new PipedOutputStream();
    final static PipedInputStream pipedIn = new PipedInputStream();
    final static PipedInputStream pipedInPut = new PipedInputStream();
    static PipedOutputStream pipedOutPut = new PipedOutputStream();
    private static int pageFaultsCounter = 0;
    private static int translationLookAsideBufferCounter = 0;
    private static String referenceNumber = "";

    void setCriticalSection(int critical) {
        criticalSection[s] = critical;
        s++;
    }
    void setMemoryNeeded(int memory) {
        memoryNeededGUI[r] = memory;
        r++;
    }
    void setPid(int processID) {
        pid = processID + 1;
    }
    void setJValue(int jValue) { j = jValue + 1; }
    int getCount() { return countingInputForPipes; }
    private static void setInput(int processNumber) { inputNum = processNumber; }
    static int getInput() { return inputNum; }
    static void setPageFaultsCounter(int counter) {
        pageFaultsCounter = pageFaultsCounter + counter;
    }
    private static int getPageFaultsCounter() {
        return pageFaultsCounter;
    }
    static void setTLBCounter(int counter) {
        translationLookAsideBufferCounter = translationLookAsideBufferCounter +  counter;
    }
    private static int getTLBCounter() {
        return translationLookAsideBufferCounter;
    }
    static void setReferenceNumber(int pageNum){
        referenceNumber = referenceNumber + " " + pageNum;
    }
    private static String getReferenceNum() {
        return referenceNumber;
    }

    static void main(Scanner in, int input, int cycles, int[] arr, int[] arr2, int[] arr3, int[] arr4, File file, File file2,
                     File file3, File file4) throws IOException {

        //int totalMemoryMB = 4096; //KB = 4096000 --> 4096000000
        int memoryNeeded = 0;
        int memoryNeededKB = 0;
        ArrayList<ArrayList<Integer>> framesAssignedWithProcess = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> addFrames = new ArrayList<>();
        int frameSize = 4096;
        int totalFrames = 4096000 / frameSize;
        int pageSize = frameSize;
        int pagesNeeded = 0; //(int) Math.ceil(memoryNeeded/pageSizeKB);
        int framesNeeded = 0;
        Stack<Integer> frameSizeStack = new Stack<>();
        int[] processID = new int[input * 4 * cycles];
        int[] arrivalTimeInReady = new int[input * 4 * cycles];
        int[] burstTimeCPU = new int[input * 4 * cycles];
        int[] kBurstTimeCPU = new int[input * 4 * cycles];
        int[] completionTime = new int[input * 4 * cycles];
        int[] turnAroundTimeDuration = new int[input * 4 * cycles];
        int[] waitingTime = new int[input * 4 * cycles];
        int[] flag = new int[input * 4 * cycles];
        int[] kBurst = new int[input * 4 * cycles];
        setInput(input);
        MutexLock lock = new MutexLock();
        MultiThreading process1;
        MultiThreading2 process2;
        MultiThreading3 process3;
        MultiThreading4 process4;


        for (int i = 0; i <= totalFrames; i++) {
            frameSizeStack.push(i);
        }
        //invoking Multithreading
        for (int i = 0; i < input * cycles; i++) {

            process1 = new MultiThreading(input, file, memoryNeeded, memoryNeededKB, framesNeeded, pagesNeeded,
                    totalFrames, lock, framesAssignedWithProcess, arr, frameSizeStack, processID, pid, i, j,
                    frameSize, pageSize, addFrames, arrivalTimeInReady, burstTimeCPU, kBurstTimeCPU, kBurst);

            process2 = new MultiThreading2(input, file2, memoryNeeded, memoryNeededKB, framesNeeded, pagesNeeded,
                    totalFrames, lock, framesAssignedWithProcess, arr2, frameSizeStack, processID, pid, i, j,
                    frameSize, pageSize, addFrames, arrivalTimeInReady, burstTimeCPU, kBurstTimeCPU, kBurst);

            process3 = new MultiThreading3(input, file3, memoryNeeded, memoryNeededKB, framesNeeded, pagesNeeded,
                    totalFrames, lock, framesAssignedWithProcess, arr3, frameSizeStack, processID, pid, i, j,
                    frameSize, pageSize, addFrames, arrivalTimeInReady, burstTimeCPU, kBurstTimeCPU, kBurst);

            process4 = new MultiThreading4(input, file4, memoryNeeded, memoryNeededKB, framesNeeded, pagesNeeded,
                    totalFrames, lock, framesAssignedWithProcess, arr4, frameSizeStack, processID, pid, i, j,
                    frameSize, pageSize, addFrames, arrivalTimeInReady, burstTimeCPU, kBurstTimeCPU, kBurst);

            Thread multiProcess = new Thread(process1);
            Thread multiProcess1 = new Thread(process2);
            Thread multiProcess2 = new Thread(process3);
            Thread multiProcess3 = new Thread(process4);

            if (countingInputForPipes == input) {
                try {
                    pipedOut.connect(pipedIn);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ExecutorService service = Executors.newFixedThreadPool(2);
                Process pipedWriteReadDemo = new Process();
                service.execute(pipedWriteReadDemo.new pipedOutputThread());
                service.execute(process1.new pipeThreading());
            }

            if (countingInputForPipes == input) {
                try {
                    pipedOutPut.connect(pipedInPut);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ExecutorService service2 = Executors.newFixedThreadPool(2);
                service2.execute(process2.new pipedOutputThread());
                service2.execute(process3.new pipeThreading());
            }

            multiProcess.run();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } //each process starts 10 milliseconds later

            multiProcess1.run();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } //each process starts 10 milliseconds later

            multiProcess2.run();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } //each process starts 10 milliseconds later

            multiProcess3.run();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } //each process starts 10 milliseconds later

            countingInputForPipes++;
        }

        in.close();

        for (int i = 0; i < input * 4 * cycles; i++) {
            kBurstTimeCPU[i] = burstTimeCPU[i];
        }

        System.out.println();
        System.out.println(String.format("Total page faults: %s", getPageFaultsCounter()));
        System.out.println(String.format("Total tlb misses: %s", getTLBCounter()));

        //Calling Shortest Job First Scheduler
        System.out.println("The following shows the processes being processed through Shortest Job First Scheduler: ");
        SJFScheduler.main(input, cycles, arrivalTimeInReady, kBurstTimeCPU, processID, completionTime, turnAroundTimeDuration,
                waitingTime, kBurst, flag);

        System.out.println();
        System.out.println();

        //Calling Round Robin Scheduler
        System.out.println("The following shows the processes being processed through Round Robin Scheduler: ");
        RRScheduler.main(input, cycles, processID, burstTimeCPU, waitingTime, turnAroundTimeDuration);

        JFrame frame = new JFrame("OperatingSystemGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTableGUI q = new JTableGUI(processID, arrivalTimeInReady, completionTime, memoryNeededGUI, criticalSection);
        q.setOpaque(true); //content panes must be opaque
        frame.setContentPane(q);
        frame.pack();
        frame.setVisible(true); //j.createAndShowGui();

        SecondChancePageReplacement.main(getReferenceNum());

        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class pipedOutputThread implements Runnable {

        @Override
        public void run() {

                try{
                    if(countingInputForPipes == inputNum) {
                        pipedOut.write(inputNum);
                        pipedOut.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}


