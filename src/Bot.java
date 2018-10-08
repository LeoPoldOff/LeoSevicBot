import java.util.Scanner;
import java.io.*;

public class Bot {
    public void init(){
        var dataFile = new File(PersonInfo.dataFilePath);
        var hasData = dataFile.exists();
        System.out.println("Hello" + (hasData ? " again!" : "!"));
        var personInfo = new PersonInfo(hasData);
        Scanner scan = new Scanner(System.in);
        loop1 : while(true) {
            var command = scan.nextLine();
            switch (command.toLowerCase()) {
                case "help":
                    System.out.println("\"when i die?\" to know when you'll die");
                    System.out.println("\"exit\" to quit");
                    break;
                case "my info":
                    System.out.println(personInfo.showInfo());
                    break;
                case "when i die?":
                    var age = Tree.calcAge(personInfo);
                    System.out.println(String.format("You'll die at age %d", age));
                    break;
                case "exit":
                    break loop1;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }
}