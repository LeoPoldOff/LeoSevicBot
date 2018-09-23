import java.util.Scanner;

public class Bot {
    public void init(){
        System.out.print("Hi! This is LeoSevikBot. Fill in the information about yourself and then let's get started!\n");
        Scanner scan = new Scanner(System.in);
        var personInfo = new PersonInfo();
        personInfo.fillInfo();
        loop1 : while(true) {
            var command = scan.nextLine();
            switch (command.toLowerCase()) {
                case "help":
                    System.out.println("\"when i die?\" to know when you'll die");
                    System.out.println("\"exit\" to quit");
                    break;
                case "when i die?":
                    var age = Tree.calcAge(personInfo.sex,
                            personInfo.birthDate,
                            personInfo.height,
                            personInfo.weight,
                            personInfo.region,
                            personInfo.smokingRange,
                            personInfo.alcoholrange,
                            personInfo.sportRange);
                    System.out.println(String.format("You'll die at age %f", age));
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