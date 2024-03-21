package utils;

public class BrowserUtils {

    public static void wait(int second){
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException("Problem occurred in Thread.sleep() method!");
        }
    }
    public static void wait(double second){
        try {
            Thread.sleep((long)(second * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException("Problem occurred in Thread.sleep() method!");
        }
    }
}
