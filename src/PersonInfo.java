import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

class PersonInfo {
    int sex, height, weight, region,
            smokingRange, alcoholRange, sportRange;
    private Date birthDate;
    private String sDate;

    private static final String dirPath = new File("").getAbsolutePath() + "/data";
    static final String dataFilePath = dirPath + "/user_data.json";
    private static Map<Integer,String> sexOptions = new HashMap<>();
    {
        sexOptions.put(1, "Man");
        sexOptions.put(2, "Woman");
    }
    private static Map<Integer,String> regionOptions = new HashMap<>();
    {
        regionOptions.put(1, "Russia");
        regionOptions.put(2, "Europe");
        regionOptions.put(3, "Asia");
        regionOptions.put(4, "Africa");
        regionOptions.put(5, "America");
        regionOptions.put(6, "Australia");
    }
    private static Map<Integer,String> smokingOptions = new HashMap<>();
    {
        smokingOptions.put(1, "One pack of cigarettes a day or more");
        smokingOptions.put(2, "One or two pack of cigarettes a week");
        smokingOptions.put(3, "One or two pack of cigarettes a month");
        smokingOptions.put(4, "I`m not smoking");
    }

    private static Map<Integer,String> alcoholOptions = new HashMap<>();
    {
        alcoholOptions.put(1, "I`m drinking everyday");
        alcoholOptions.put(2, "Usually I drink a few times a week");
        alcoholOptions.put(3, "Usually I drink a few times a month");
        alcoholOptions.put(4, "I`m not drinking");
    }

    private static Map<Integer,String> sportOptions = new HashMap<>();
    {
        sportOptions.put(1, "I`m professional athlete");
        sportOptions.put(2, "Sometimes I go to the gym");
        sportOptions.put(3, "I don`t like sport");
    }

    private PersonInfo(){
        sex = 1;
        sDate = "01.10.1999";
        birthDate = parseDate("01.01.1900");
        height = 160;
        weight = 50;
        region = 1;
        smokingRange = 1;
        alcoholRange = 1;
        sportRange = 1;
    }

    @SuppressWarnings("unchecked")
    PersonInfo(boolean hasData){
        if (hasData) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject object = (JSONObject) parser.parse(new FileReader(dataFilePath));

                sex = ((Number)object.get("sex")).intValue();
                sDate = object.get("birthDate").toString();
                birthDate = parseDate(sDate);
                height = ((Number) object.get("height")).intValue();
                weight = ((Number) object.get("weight")).intValue();
                region = ((Number) object.get("region")).intValue();
                smokingRange = ((Number) object.get("smokingRange")).intValue();
                alcoholRange = ((Number) object.get("alcoholRange")).intValue();
                sportRange = ((Number) object.get("sportRange")).intValue();
            }
            catch (IOException | org.json.simple.parser.ParseException e){System.out.println(e.getMessage());}
        }
        else {
            var pInfo = getInfo();
            sex = pInfo.sex;
            sDate = pInfo.sDate;
            birthDate = pInfo.birthDate;
            height = pInfo.height;
            weight = pInfo.weight;
            region = pInfo.region;
            smokingRange = pInfo.smokingRange;
            alcoholRange = pInfo.alcoholRange;
            sportRange = pInfo.sportRange;

            var dataDir = new File(dirPath);
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            JSONObject object = new JSONObject();
            object.put("sex", sex);
            object.put("birthDate", sDate);
            object.put("height", height);
            object.put("weight", weight);
            object.put("region", region);
            object.put("smokingRange", smokingRange);
            object.put("alcoholRange", alcoholRange);
            object.put("sportRange", sportRange);

            try (FileWriter writer = new FileWriter(dataFilePath)){
                writer.write(object.toJSONString());
                writer.flush();
                //writer.close();
            } catch (IOException e) {System.out.println(e.getMessage());}

        }
    }

    private Date parseDate(String sDate){
        var date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        //SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH);
        try {
            date = dateFormat.parse(sDate);
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
        }
        return date;
    }

    private PersonInfo getInfo(){
        PersonInfo pInfo = new PersonInfo();
        System.out.print("Fill in the information about yourself.\n");
        Scanner scan = new Scanner(System.in);

        var sexQuestion = makeQuestion("What`s your sex?", sexOptions);
        pInfo.sex = askQuestion(sexQuestion, sexOptions.size(), scan);

        System.out.println("What`s your date of birth?");
        var date = scan.nextLine();
        pInfo.sDate = date;
        pInfo.birthDate = parseDate(date);

        System.out.println("What`s your height measuring in centimeters?");
        pInfo.height = Integer.parseInt(scan.nextLine());

        System.out.println("What`s your weight measuring in kilos?");
        pInfo.weight = Integer.parseInt(scan.nextLine());

        var regionQuestion = makeQuestion("In what region do you live?", regionOptions);
        pInfo.region = askQuestion(regionQuestion, regionOptions.size(), scan);

        var smokeQuestion = makeQuestion("What`s your attitude to smoking?", smokingOptions);
        pInfo.smokingRange = askQuestion(smokeQuestion, smokingOptions.size(), scan);

        var alcoholQuestion = makeQuestion("What`s your attitude to alcohol?", alcoholOptions);
        pInfo.alcoholRange = askQuestion(alcoholQuestion, alcoholOptions.size(), scan);
        var sportQuestion = makeQuestion("What`s your attitude to sport?", sportOptions);
        pInfo.sportRange = askQuestion(sportQuestion, sportOptions.size(), scan);

        return pInfo;
    }

    private static String makeQuestion(String question, Map<Integer, String> options){
        var resultQuestion = new StringBuilder(question + "\n");
        for (var k : options.keySet()){
            resultQuestion.append(k).append(". ").append(options.get(k)).append("\n");
        }

        return resultQuestion.deleteCharAt(resultQuestion.length() - 1).toString();
    }

    private static int askQuestion(String question, int range, Scanner scan){
        int answer;
        while (true) {
            System.out.println(question);
            try {
                answer = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("Incorrect answer");
                continue;
            }
            if (answer <= range && answer > 0)
                break;
            else
                System.out.println("Incorrect answer");
        }
        return answer;
    }

    String showInfo() {
        var info = new StringBuilder();
        info.append("Sex: ").append(sexOptions.get(sex));
        info.append("\nDate of birth: ").append(birthDate.toString());
        info.append("\nRegion: ").append(regionOptions.get(region));
        info.append("\nWeight: ").append(weight).append(" kg");
        info.append("\nHeight: ").append(height).append(" sm");
        info.append("\nAttitude to smoking: ").append(smokingOptions.get(smokingRange));
        info.append("\nAttitude to alcohol: ").append(alcoholOptions.get(alcoholRange));
        info.append("\nAttitude to sport: ").append(sportOptions.get(sportRange));
        return info.toString();
    }
}
