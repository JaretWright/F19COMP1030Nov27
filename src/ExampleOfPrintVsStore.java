import java.util.Scanner;

public class ExampleOfPrintVsStore {
    public static void main(String[] args)
    {
        System.out.printf("Enter your name: ");
        Scanner keyboard = new Scanner(System.in);
        String name = keyboard.nextLine();

        System.out.println(name);
        System.out.printf("Your name starts with %c%n", name.charAt(0));
    }

}
