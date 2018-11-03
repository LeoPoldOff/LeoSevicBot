import java.util.Map;

class Bot {
    private INode currentNode;
    private boolean firstTime;

    Bot(){
        var dw = new DataWorker();
        firstTime = !dw.hasInfo();
        PersonInfo personInfo;
        if (firstTime)
            personInfo = new PersonInfo();
        else
            personInfo = dw.readData();

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
        quiz.commands.put("sex", new BotAction(quiz::changeSex, sexNode));
        fillCommandsDict(sexNode.commands, QuizAsker.sexOptions,
                         new BotAction(sexNode::sexChangeRespond, quiz));
        quiz.commands.put("region", new BotAction(quiz::changeRegion, regionNode));
        fillCommandsDict(regionNode.commands, QuizAsker.regionOptions,
                         new BotAction(regionNode::regionChangeRespond, quiz));
        quiz.commands.put("smoking habit", new BotAction(quiz::changeSmokingRange, smokingNode));
        fillCommandsDict(smokingNode.commands, QuizAsker.smokingOptions,
                         new BotAction(smokingNode::smokeChangeRespond, quiz));
        quiz.commands.put("alcohol habit", new BotAction(quiz::changeAlcoholRange, alcoholNode));
        fillCommandsDict(alcoholNode.commands, QuizAsker.alcoholOptions,
                         new BotAction(alcoholNode::alcoholChangeRespond, quiz));
        quiz.commands.put("sport habit", new BotAction(quiz::changeSportRange, sportNode));
        fillCommandsDict(sportNode.commands, QuizAsker.sportOptions,
                         new BotAction(sportNode::sportChangeRespond, quiz));
        quiz.commands.put("height", new BotAction(quiz::changeHeight, heightNode));
        heightNode.commands.put("", new BotAction(heightNode::heightChangeRespond, quiz));
        quiz.commands.put("weight", new BotAction(quiz::changeWeight, weightNode));
        weightNode.commands.put("", new BotAction(weightNode::weightChangeRespond, quiz));
        quiz.commands.put("birthday", new BotAction(quiz::changeBDay, bDayNode));
        bDayNode.commands.put("", new BotAction(bDayNode::bDayChangeRespond, quiz));
        quiz.commands.put("ok", new BotAction(quiz::writeData, start));

        if (firstTime){
            var preStartNode = new PreStart();
            var preSexNode = new SexNode(personInfo);
            var preRegionNode = new RegionNode(personInfo);
            var preSmokingNode = new SmokingNode(personInfo);
            var preAlcoholNode = new AlcoholNode(personInfo);
            var preSportNode = new SportNode(personInfo);
            var preHeightNode = new HeightNode(personInfo);
            var preWeightNode = new WeightNode(personInfo);
            var preBDayNode = new BirthdayNode(personInfo);

            preStartNode.commands.put("my sex", new BotAction(quiz::changeSex, preSexNode));
            fillCommandsDict(preSexNode.commands, QuizAsker.sexOptions,
                    new BotAction(sexNode::sexChangeRespond, preStartNode));
            preStartNode.commands.put("my region", new BotAction(quiz::changeRegion, preRegionNode));
            fillCommandsDict(preRegionNode.commands, QuizAsker.regionOptions,
                    new BotAction(regionNode::regionChangeRespond, preStartNode));
            preStartNode.commands.put("my smoking habit", new BotAction(quiz::changeSmokingRange, preSmokingNode));
            fillCommandsDict(preSmokingNode.commands, QuizAsker.smokingOptions,
                    new BotAction(smokingNode::smokeChangeRespond, preStartNode));
            preStartNode.commands.put("my alcohol habit", new BotAction(quiz::changeAlcoholRange, preAlcoholNode));
            fillCommandsDict(preAlcoholNode.commands, QuizAsker.alcoholOptions,
                    new BotAction(alcoholNode::alcoholChangeRespond, preStartNode));
            preStartNode.commands.put("my sport habit", new BotAction(quiz::changeSportRange, preSportNode));
            fillCommandsDict(preSportNode.commands, QuizAsker.sportOptions,
                    new BotAction(sportNode::sportChangeRespond, preStartNode));
            preStartNode.commands.put("my height", new BotAction(quiz::changeHeight, preHeightNode));
            preHeightNode.commands.put("", new BotAction(heightNode::heightChangeRespond, preStartNode));
            preStartNode.commands.put("my weight", new BotAction(quiz::changeWeight, preWeightNode));
            preWeightNode.commands.put("", new BotAction(weightNode::weightChangeRespond, preStartNode));
            preStartNode.commands.put("my birthday", new BotAction(quiz::changeBDay, preBDayNode));
            preBDayNode.commands.put("", new BotAction(bDayNode::bDayChangeRespond, preStartNode));
            preStartNode.commands.put("ok", new BotAction(quiz::writeData, start));

            currentNode = preStartNode;
        }
        else
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
                   "Use \"help\" to see list off available commands.\n" +
                    "Firstly fill information about yourself.";
        else
            return "Hello again!";
    }
}