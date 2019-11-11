import java.io.FileNotFoundException;

public class RRScheduler {

    public static void main(int input, int[] pid, int[] burstTime, int[] waitingTime, int[] turnAroundTime) {
        int quantum = 2;
        int totalWaitTime = 0;
        int totalTurnAroundTime = 0;

        findWaitTime(input, pid, burstTime, waitingTime, quantum);

        //find turnAroundTime
        for (int i = 0; i < input*4; i++) {
            turnAroundTime[i] = burstTime[i] + waitingTime[i];
        }

        System.out.println("Processes " + " BurstTime " +
                " WaitingTime " + " TurnAroundTime");

        for (int i = 0; i < input*4; i++) {
            totalWaitTime += waitingTime[i];
            totalTurnAroundTime += turnAroundTime[i];
            System.out.println("   " + pid[i] + "      " + burstTime[i] +"\t\t\t " +
                    waitingTime[i] +"\t\t\t\t " + turnAroundTime[i]);
        }

        System.out.println("Average Waiting time = " + (float) (totalWaitTime)/((float)input*4));
        System.out.println("Average Turnaround time = " + (float)(totalTurnAroundTime)/(float)(input*4));
    }

    private static void findWaitTime(int input, int[] pid, int[] burstTime, int[] waitingTime, int quantum) {
        int[] kBurst = new int[input*4];

        for(int i = 0; i < input*4; i++) {
            kBurst[i] = burstTime[i];
        }

        int currentTime = 0;

        while(true) {
            boolean flag = true;

            for(int i = 0; i < input*4; i++) {
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
}

