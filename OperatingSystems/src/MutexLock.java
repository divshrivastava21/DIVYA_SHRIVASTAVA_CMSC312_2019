public class MutexLock {

    private static boolean lockAvailable = true;

    public static void acquire() {
        if (lockAvailable) {
            lockAvailable = false; //lock is no longer available
//            System.out.println("Lock has been successfully acquired.");
        }
//        else{
//            System.out.println("Lock is unavailable; please release first");
//        }
    }

    public static void release() {
        if (!lockAvailable) {
            lockAvailable = true;
//            System.out.println("Lock has been successfully released.");
        }
//        else{
//            System.out.println("Lock is already available; please acquire lock");
//        }
    }
}