import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class PersonInfo {
    public int sex;
    public Date birthDate;
    public int height;
    public int weight;
    public int region;
    public int smokingRange;
    public int alcoholrange;
    public int sportRange;

    public PersonInfo(){
        sex = 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        try {
            birthDate = dateFormat.parse("01.01.1900");
        }
        catch (ParseException e){}
        height = 160;
        weight = 50;
        region = 1;
        smokingRange = 1;
        alcoholrange = 1;
        smokingRange = 1;
    }

    public void fillInfo(){
        Scanner scan = new Scanner(System.in);
        var sexQuestion = "What`s your sex?\n" +
                "1.Man\n" +
                "2.Woman";
        sex = askQuestion(sexQuestion, 2, scan);

        System.out.println("What`s your date of birth?");
        var date = scan.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        try {
            birthDate = dateFormat.parse(date);
        }
        catch (ParseException e){}

        System.out.println("What`s your height measuring in centimeters?");
        height = Integer.parseInt(scan.nextLine());

        System.out.println("What`s your weight measuring in kilos?");
        weight = Integer.parseInt(scan.nextLine());

        var regionQuestion = "In what region do you live?\n" +
                "1.Russia\n" +
                "2.Europe\n" +
                "3.Asia\n" +
                "4.Africa\n" +
                "5.America\n" +
                "6.Australia";
        region = askQuestion(regionQuestion, 6, scan);

        var smokeQuestion = "What`s your attitude to smoking?(int 1 - 4)\n" +
                "1.One pack of cigarettes a day or more\n" +
                "2.One or two pack of cigarettes a week\n" +
                "3.One or two pack of cigarettes a month\n" +
                "4.I`m not smoking";
        smokingRange = askQuestion(smokeQuestion, 4, scan);

        var alcoholQuestion = "What`s your attitude to alcohol?(int 1 - 3)\n" +
                "1.I`m drinking everyday\n" +
                "2.Usually I drink a few times a week\n" +
                "3.Usually I drink a few times a month\n" +
                "4.I`m not drinking";
        alcoholrange = askQuestion(alcoholQuestion, 4, scan);
        var sportQuestion = "What`s your attitude to sport?(int 1 - 3)\n" +
                "1.I`m professional athlete\n" +
                "2.Sometimes I go to the gym\n" +
                "3.I don`t like sport";
        sportRange = askQuestion(sportQuestion, 3, scan);
    }

    private int askQuestion(String question, int range, Scanner scan){
        int answ;
        while (true) {
            System.out.println(question);
            try {
                answ = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("Incorrect answer");
                continue;
            }
            if (answ <= range && answ > 0){
                break;
            }
            else {
                System.out.println("Incorrect answer");
            }
        }

        return answ;
    }
}
