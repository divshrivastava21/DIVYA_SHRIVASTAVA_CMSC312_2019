import java.io.FileNotFoundException;

public class SJFScheduler extends Dispatcher{

    public static void main(int input, int cycles, int[] arrivalTime, int[]burstTime, int[] pid, int[] completionTime,
                            int[]turnAround, int[]waitTime, int[] kBurst, int[] flag) throws FileNotFoundException
    {

        float averageWaitingTime = 0;
        float averageTurnaroundTime = 0;
        int total = 0;
        int currentTime = 0;

        while(true) {
            int min = 99;
            int c = (input*4*cycles);
            if(total == (input*4*cycles))
                break;

            for(int i = 0; i < input*4*cycles; i++) {
                if ((arrivalTime[i]<= currentTime) && (flag[i]==0) && (burstTime[i]<min))
                {
                    min=burstTime[i];
                    c=i;
                }
            }

            if (c == (input*4*cycles))
                currentTime++;
            else
            {
                burstTime[c]--;
                currentTime++;
                if (burstTime[c]==0)
                {
                    completionTime[c]= currentTime;
                    flag[c]=1;
                    total++;
                }
            }
        }

        for(int i = 0; i < input*4*cycles; i++) {

            turnAround[i] = completionTime[i] - arrivalTime[i];
            waitTime[i] = turnAround[i] - kBurst[i];

            averageWaitingTime += waitTime[i];
            averageTurnaroundTime += turnAround[i];
        }

        System.out.println();
        System.out.println("The following shows the process being processed through Shortest Job First Scheduler: ");
        System.out.println("\nProcessID      State    arrivalTime     State     burstTime     State    completionTime   " +
                " State     " + "turnAroundTime    waitingTime");
        for(int  i = 0 ; i < input*4*cycles;  i++) {
            if (pid[i] != 0) {
                System.out.println(pid[i] + "\t        NEW          " + arrivalTime[i] + "\t        READY        " +
                        kBurst[i] + "\t   " + "     RUNNING         " + completionTime[i] + "\t      TERMINATED         "
                        + turnAround[i] + " \t   " + "\t\t\t" + waitTime[i]);
            }
        }

        System.out.println("The average waiting time is " + (averageWaitingTime/(input*4*cycles)));
        System.out.println("The average turnaround time is " + (averageTurnaroundTime/(input*4*cycles)));
  }
}
