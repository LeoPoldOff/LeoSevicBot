import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        var bot = new Bot();
        System.out.println(bot.greet());
        Scanner scan = new Scanner(System.in);
        while (true) {
            var command = scan.nextLine();
            var response = bot.respond(command.toLowerCase(), "user1");
            if (!response.userRespond.equals(""))
                System.out.println(response.userRespond);
            if (response.exitCode == 1) {
                break;
            }
        }
    }
}
