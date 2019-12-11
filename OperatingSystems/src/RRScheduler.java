
import static java.util.Arrays.sort;

public class RRScheduler {

    public static void main(int input, int cycles, int[] pid, int[] burstTime, int[] waitingTime, int[] turnAroundTime) {
        int quantum = 2;
        int totalWaitTime = 0;
        int totalTurnAroundTime = 0;
        int[] priorityOrder = new int[pid.length];
        int[] sortedPriorityOrder = new int[pid.length];

        //Random priority processing for Round Robin!
        for(int i = 0; i < pid.length; i++) {

            priorityOrder[i] = (int)getRandomInt(1.0, 20.0) + pid[i];
            sortedPriorityOrder[i] = priorityOrder[i];
        }

        //Sort randomly generated values in array
        sort(sortedPriorityOrder);

        //Comparing sorted array vs pre-sorted array to output true process ID values.
        for(int i = 0; i < pid.length; i++) {
            for(int j = 0; j < pid.length; j++) {
                if(sortedPriorityOrder[i] == (priorityOrder[j])) {

                    sortedPriorityOrder[i] = pid[j];
                    burstTime[i] = burstTime[j];
                    waitingTime[i] = waitingTime[j];
                }
            }
        }

        findWaitTime(input, cycles, pid, burstTime, waitingTime, quantum);

        //find turnAroundTime
        for (int i = 0; i < input*4*cycles; i++) {
            turnAroundTime[i] = burstTime[i] + waitingTime[i];
        }

        System.out.println("Processes " + " BurstTime " +
                " WaitingTime " + " TurnAroundTime");

        for (int i = 0; i < input*4*cycles; i++) {
            totalWaitTime += waitingTime[i];
            totalTurnAroundTime += turnAroundTime[i];
            System.out.println("   " + sortedPriorityOrder[i] + "       " + burstTime[i] +"\t\t\t " +
                    waitingTime[i] +"\t\t\t\t " + turnAroundTime[i]);
        }

        System.out.println("Average Waiting time = " + (float) (totalWaitTime)/((float)input*4));
        System.out.println("Average Turnaround time = " + (float)(totalTurnAroundTime)/(float)(input*4));
    }

    private static void findWaitTime(int input, int cycles, int[] pid, int[] burstTime, int[] waitingTime, int quantum) {
        int[] kBurst = new int[input*4*cycles];

        for(int i = 0; i < input*4*cycles; i++) {
            kBurst[i] = burstTime[i];
        }

        int currentTime = 0;

        while(true) {
            boolean flag = true;

            for(int i = 0; i < input*4*cycles; i++) {
                if (kBurst[i] > 0)
                {
                    flag = false;

                    if(kBurst[i] > quantum) {
                        currentTime += quantum;
                        kBurst[i] -= quantum;
                    }
                    else {
                        currentTime = currentTime + kBurst[i];
                        waitingTime[i] = currentTime - burstTime[i];
                        kBurst[i] = 0;
                    }
                }
            }
            if(flag) {
                break;
            }
        }
    }
    private static double getRandomInt(double min, double max) {
        double randomInteger = (int) (Math.random() * ((max - min) + 1)) + min;
        return randomInteger;
    }

}

