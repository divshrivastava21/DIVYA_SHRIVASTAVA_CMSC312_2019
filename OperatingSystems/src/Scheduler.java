import java.io.FileNotFoundException;

public class Scheduler extends Dispatcher {

    public static void main(int input, int[] arrivalTime, int[]burstTime, int[] pid, int[] completionTime,
                            int[]turnAround, int[]waitTime) throws FileNotFoundException
    {
        int temp;
        float averageWaitingTime = 0;
        int count = 0;

        //ordering the processes by arrival time
        for(int i = 0 ; i < input; i++)
        {
            for(int  j=0;  j < input-(i+1) ; j++)
            {
                if( arrivalTime[j] > arrivalTime[j+1] )
                {
                    temp = arrivalTime[j];
                    arrivalTime[j] = arrivalTime[j+1];
                    arrivalTime[j+1] = temp;

                    temp = burstTime[j];
                    burstTime[j] = burstTime[j+1];
                    burstTime[j+1] = temp;
                    temp = pid[j];
                    pid[j] = pid[j+1];
                    pid[j+1] = temp;
                }
            }
        }

        // finding the completion times for each process
        for(int  i = 0 ; i < input*4; i++)
        {
            if( i == 0)
            {
                completionTime[i] = arrivalTime[i] + burstTime[i];
            }
            else
            {
                if( arrivalTime[i] > completionTime[i-1])
                {
                    completionTime[i] = arrivalTime[i] + burstTime[i];
                }
                else
                    completionTime[i] = completionTime[i-1] + burstTime[i];
            }
            turnAround[i] = completionTime[i] - arrivalTime[i];
            waitTime[i] = turnAround[i] - burstTime[i];
        }

        for(int i = 0; i < waitTime.length; i++) {
            if(i == 0 || waitTime[i] != 0) {
                averageWaitingTime += waitTime[i];
                count++;
            }
        }

        System.out.println("\nProcessID      State    arrivalTime     State     burstTime     State    completionTime   " +
                " State     " + "turnAroundTime    waitingTime");
        for(int  i = 0 ; i < input*4;  i++) {
            if (pid[i] != 0) {
                System.out.println(pid[i] + "\t        NEW       " + arrivalTime[i] + "\t    READY        " + burstTime[i] + "\t   " +
                        "     RUNNING       " + completionTime[i] + "\t  TERMINATED         " + turnAround[i] + " \t   " +
                        "      " + waitTime[i]);
            }
        }

        System.out.println("The average waiting time is " + (averageWaitingTime/(count)));
  }
}
