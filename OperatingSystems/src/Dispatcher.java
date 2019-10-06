import java.io.FileNotFoundException;

public class Dispatcher implements Runnable {
    public static Thread thread1;
    public static Dispatcher object;

    public static void main(String[] args) throws FileNotFoundException
    {
        object = new Dispatcher();
        thread1 = new Thread(object);

        // thread1 created and is currently in the NEW state.
        System.out.println("State of thread1 after creating it - " + thread1.getState());
        thread1.start();

        // thread1 moved to Runnable state
        System.out.println("State of thread1 after calling .start() method on it - " +
                thread1.getState());
    }

    @Override
    public void run() {
        thread myThread = new thread();
        Thread thread2 = new Thread(myThread);

        // thread1 is currently in the NEW state.
        System.out.println(thread2.getState());
        thread2.start();

        // thread2 moved to Runnable state
        System.out.println(thread2.getState());

        // moving thread1 to timed waiting state
        try
        {
            //moving thread1 to timed waiting state
            Thread.sleep(300);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(thread2.getState());


        try
        {
            // waiting for thread2 to terminate
            thread2.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(thread2.getState());
    }

    class thread implements Runnable
    {
        public void run()
        {
            // moving thread2 to timed waiting state
            try
            {
                Thread.sleep(1500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            System.out.println(Dispatcher.thread1.getState());
            try
            {
                Thread.sleep(300);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

