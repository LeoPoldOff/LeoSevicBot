import java.util.HashMap;
import java.util.Map;

class QuizNode extends Node {
    //private UserInfo userInfo;
    private String sexQuestion = "What`s your sex?";
    static Map<Integer, String> sexOptions = new HashMap<>();

    {
        sexOptions.put(0, "Undefined");
        sexOptions.put(1, "Male");
        sexOptions.put(2, "Female");
    }

    private String regionQuestion = "In what region do you live?";
    static Map<Integer, String> regionOptions = new HashMap<>();

    {
        regionOptions.put(0, "Undefined");
        regionOptions.put(1, "Russia");
        regionOptions.put(2, "Europe");
        regionOptions.put(3, "Asia");
        regionOptions.put(4, "Africa");
        regionOptions.put(5, "America");
        regionOptions.put(6, "Australia");
    }

    private String smokeQuestion = "What`s your attitude to smoking?";
    static Map<Integer, String> smokingOptions = new HashMap<>();

    {
        smokingOptions.put(0, "Undefined");
        smokingOptions.put(1, "One pack of cigarettes a day or more");
        smokingOptions.put(2, "One or two pack of cigarettes a week");
        smokingOptions.put(3, "One or two pack of cigarettes a month");
        smokingOptions.put(4, "I`m not smoking");
    }

    private String alcoholQuestion = "What`s your attitude to alcohol?";
    static Map<Integer, String> alcoholOptions = new HashMap<>();

    {
        alcoholOptions.put(0, "Undefined");
        alcoholOptions.put(1, "I`m drinking everyday");
        alcoholOptions.put(2, "Usually I drink a few times a week");
        alcoholOptions.put(3, "Usually I drink a few times a month");
        alcoholOptions.put(4, "I`m not drinking");
    }

    private String sportQuestion = "What`s your attitude to sport?";
    static Map<Integer, String> sportOptions = new HashMap<>();

    {
        sportOptions.put(0, "Undefined");
        sportOptions.put(1, "I`m professional athlete");
        sportOptions.put(2, "Sometimes I go to the gym");
        sportOptions.put(3, "I don`t like sport");
    }

    private String heightQuestion = "What`s your height measuring in centimeters?";
    private String weightQuestion = "What`s your weight measuring in kilos?";
    private String birthdayQuestion = "What`s your date of birth?(dd.mm.yyyy)";

    QuizNode() {
        commands = new HashMap<>();
    }

    void fillCommands(StartNode start, SexNode sexNode, RegionNode regionNode, SmokingNode smokingNode,
                      AlcoholNode alcoholNode, SportNode sportNode, HeightNode heightNode, WeightNode weightNode,
                      BirthdayNode bDayNode) {
        commands.put("sex", new BotAction(this::changeSex, sexNode));
        commands.put("region", new BotAction(this::changeRegion, regionNode));
        commands.put("smoking habit", new BotAction(this::changeSmokingRange, smokingNode));
        commands.put("alcohol habit", new BotAction(this::changeAlcoholRange, alcoholNode));
        commands.put("sport habit", new BotAction(this::changeSportRange, sportNode));
        commands.put("height", new BotAction(this::changeHeight, heightNode));
        commands.put("weight", new BotAction(this::changeWeight, weightNode));
        commands.put("birthday", new BotAction(this::changeBDay, bDayNode));
        commands.put("ok", new BotAction(this::writeData, start));
    }

    Response changeSex() {
        return new Response(makeQuestion(sexQuestion, sexOptions), 0);
    }

    Response changeHeight() {
        return new Response(heightQuestion, 0);
    }

    Response changeWeight() {
        return new Response(weightQuestion, 0);
    }

    Response changeBDay() {
        return new Response(birthdayQuestion, 0);
    }

    Response changeRegion() {
        return new Response(makeQuestion(regionQuestion, regionOptions), 0);
    }

    Response changeSmokingRange() {
        return new Response(makeQuestion(smokeQuestion, smokingOptions), 0);
    }

    Response changeAlcoholRange() {
        return new Response(makeQuestion(alcoholQuestion, alcoholOptions), 0);
    }

    Response changeSportRange() {
        return new Response(makeQuestion(sportQuestion, sportOptions), 0);
    }

    Response writeData(String s, User user) {
        var dw = new DataWorker();
        dw.writeData(user.getInfo(), user.id);
        return new Response("Changes saved", 0);
    }

    private String makeQuestion(String question, Map<Integer, String> options) {
        var resultQuestion = new StringBuilder(question + "\n");
        for (var k : options.keySet())
            if (k != 0)
                resultQuestion.append(k).append(". ").append(options.get(k)).append("\n");
        return resultQuestion.deleteCharAt(resultQuestion.length() - 1).toString();
    }
}
