public class Utils {

    public static void println(
        String message
    ) {
        print(message + "\n");
    }

    public static void print(
        String message
    ) {
        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i));
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
}
 