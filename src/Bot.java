import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Bot {
    private Map<String, User> users = new HashMap<>();
    private PreStartNode preStartNode;
    private StartNode start;

    Bot() {
        start = new StartNode();
        var quiz = new QuizNode();
        var sexNode = new SexNode();
        var regionNode = new RegionNode();
        var smokingNode = new SmokingNode();
        var alcoholNode = new AlcoholNode();
        var sportNode = new SportNode();
        var heightNode = new HeightNode();
        var weightNode = new WeightNode();
        var bDayNode = new BirthdayNode();
        start.fillCommands(quiz);
        quiz.fillCommands(start, sexNode, regionNode, smokingNode, alcoholNode,
                sportNode, heightNode, weightNode, bDayNode);
        sexNode.fillCommands(quiz, QuizNode.sexOptions);
        regionNode.fillCommands(quiz, QuizNode.regionOptions);
        smokingNode.fillCommands(quiz, QuizNode.smokingOptions);
        alcoholNode.fillCommands(quiz, QuizNode.alcoholOptions);
        sportNode.fillCommands(quiz, QuizNode.sportOptions);
        heightNode.fillCommands(quiz);
        weightNode.fillCommands(quiz);
        bDayNode.fillCommands(quiz);

        preStartNode = new PreStartNode();
        var preSexNode = new SexNode();
        var preRegionNode = new RegionNode();
        var preSmokingNode = new SmokingNode();
        var preAlcoholNode = new AlcoholNode();
        var preSportNode = new SportNode();
        var preHeightNode = new HeightNode();
        var preWeightNode = new WeightNode();
        var preBDayNode = new BirthdayNode();
        preStartNode.fillCommands(quiz, preSexNode, preRegionNode, preSmokingNode, preAlcoholNode, preSportNode,
                preHeightNode, preWeightNode, preBDayNode, start);
        preSexNode.fillCommands(preStartNode, QuizNode.sexOptions);
        preRegionNode.fillCommands(preStartNode, QuizNode.regionOptions);
        preSmokingNode.fillCommands(preStartNode, QuizNode.smokingOptions);
        preAlcoholNode.fillCommands(preStartNode, QuizNode.alcoholOptions);
        preSportNode.fillCommands(preStartNode, QuizNode.sportOptions);
        preHeightNode.fillCommands(preStartNode);
        preWeightNode.fillCommands(preStartNode);
        preBDayNode.fillCommands(preStartNode);
    }

    Response respond(String command, String chatId) {
        command = command.toLowerCase();
        var user = users.get(chatId);
        if (user == null) {
            var dw = new DataWorker();
            if (dw.hasInfo(chatId))
                user = new User(start, chatId, dw.readData(chatId));
            else
                user = new User(preStartNode, chatId);
            users.put(chatId, user);
        }
        var answer = user.getAnswerFromCurrentNode(command);
        user.moveToNextNode(answer.nextNode);

        if (answer.hasFunc)
            return answer.func.apply(command, user);
        else if (answer.hasSup)
            return answer.sup.get();
        else
            return new Response("Something went wrong", 0);
    }

    String greet() {
        return "Hello! This is Boring Life bot.\n" +
                "Use \"help\" to see list off available commands.\n" +
                "Firstly fill information about yourself.";
    }

    List<String> currentCommandList(String chatId) {
        return users.get(chatId).getCommandList();
    }
}