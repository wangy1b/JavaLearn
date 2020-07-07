import java.io.*;
import java.net.*;

public class MultipleExceptionsExample {
    public int test() {
        String urlStr = null;
        while (true) {
            try {
                try {
                    System.out.print("Enter url: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    urlStr = reader.readLine();
                    if (urlStr.length() == 0) {
                        System.out.println("No url specified:");
                        continue;
                    }
                    System.out.println("Opening " + urlStr);
                    URL url = new URL(urlStr);
                    reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    System.out.println(reader.readLine());
                    reader.close();
//                    break;
                    // verify :
                    // When a return statement is executed in your code, the finally block is never executed.
                    return 1;

                } finally {
                    System.out.println("inner try finally test.");
                }
            } catch (MalformedURLException e) {
                System.out.println("Invalid URL " + urlStr + ": " + e.getMessage());
//                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Unable to execute " + urlStr + ": " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("outer try finally test.");
            }
        }
    }

    public static void main(String[] args) {
        MultipleExceptionsExample m = new MultipleExceptionsExample();
        m.test();
    }

}
