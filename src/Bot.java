import java.util.Map;

class Bot {
    private INode currentNode;
    private boolean firstTime;

    Bot(){
        var dw = new DataWorker();
        firstTime = !dw.hasInfo();
        PersonInfo personInfo;
        if (!firstTime)
            personInfo = dw.readData();
        else
            personInfo = new PersonInfo();

        var start = new StartNode(personInfo);
        var quiz = new QuizAsker(personInfo);
        var sexNode = new SexNode(personInfo);
        var regionNode = new RegionNode(personInfo);
        var smokingNode = new SmokingNode(personInfo);
        var alcoholNode = new AlcoholNode(personInfo);
        var sportNode = new SportNode(personInfo);
        var heightNode = new HeightNode(personInfo);
        var weightNode = new WeightNode(personInfo);
        var bDayNode = new BirthdayNode(personInfo);

        start.commands.put("my info", new BotAction(start::myInfo, start));
        start.commands.put("change info", new BotAction(start::changeInfo, quiz));
        start.commands.put("when i die?", new BotAction(start::whenIDie, start));
        start.commands.put("exit", new BotAction(start::exit, start));
        quiz.commands.put("change sex", new BotAction(quiz::changeSex, sexNode));
        quiz.commands.put("change region", new BotAction(quiz::changeRegion, regionNode));
        quiz.commands.put("change smoke habit", new BotAction(quiz::changeSmokingRange, smokingNode));
        quiz.commands.put("change alcohol habit", new BotAction(quiz::changeAlcoholRange, alcoholNode));
        quiz.commands.put("change sport habit", new BotAction(quiz::changeSportRange, sportNode));
        quiz.commands.put("change height", new BotAction(quiz::changeHeight, heightNode));
        heightNode.commands.put("", new BotAction(heightNode::heightChangeRespond, quiz));
        quiz.commands.put("change weight", new BotAction(quiz::changeWeight, weightNode));
        weightNode.commands.put("", new BotAction(weightNode::weightChangeRespond, quiz));
        quiz.commands.put("change birthday", new BotAction(quiz::changeBDay, bDayNode));
        bDayNode.commands.put("", new BotAction(bDayNode::bDayChangeRespond, quiz));
        quiz.commands.put("ok", new BotAction(quiz::writeData, start));
        fillCommandsDict(sexNode.commands, QuizAsker.sexOptions, new BotAction(sexNode::sexChangeRespond, quiz));
        fillCommandsDict(regionNode.commands, QuizAsker.regionOptions, new BotAction(regionNode::regionChangeRespond, quiz));
        fillCommandsDict(smokingNode.commands, QuizAsker.smokingOptions, new BotAction(smokingNode::smokeChangeRespond, quiz));
        fillCommandsDict(alcoholNode.commands, QuizAsker.alcoholOptions, new BotAction(alcoholNode::alcoholChangeRespond, quiz));

        currentNode = start;
    }

    Response respond(String command){
        command = command.toLowerCase();
        var answer = currentNode.getAnswer(command);
        currentNode = answer.nextNode;

        if (answer.hasFunc)
            return answer.func.apply(command);
        else if (answer.hasSup)
            return answer.sup.get();
        else
            return new Response("Something went wrong", 0);
    }

    private void fillCommandsDict(Map<String, BotAction> commands, Map<Integer,String> options, BotAction action){
        for (var a : options.keySet()){
            commands.put(a.toString(), action);
        }
    }

    String greet(){
        if (firstTime)
            return "Hello! This is Boring Life bot.\n" +
                   "Use \"help\" to see list off available commands.";
        else
            return "Hello again!";
    }
}