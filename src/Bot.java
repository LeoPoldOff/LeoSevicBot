import java.util.List;

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
        var quiz = new QuizNode(personInfo);
        var sexNode = new SexNode(personInfo);
        var regionNode = new RegionNode(personInfo);
        var smokingNode = new SmokingNode(personInfo);
        var alcoholNode = new AlcoholNode(personInfo);
        var sportNode = new SportNode(personInfo);
        var heightNode = new HeightNode(personInfo);
        var weightNode = new WeightNode(personInfo);
        var bDayNode = new BirthdayNode(personInfo);

        start.fillCommands(quiz);
        quiz.fillCommands(start,sexNode,regionNode,smokingNode,alcoholNode,
                          sportNode,heightNode,weightNode,bDayNode);
        sexNode.fillCommands(quiz, QuizNode.sexOptions);
        regionNode.fillCommands(quiz, QuizNode.regionOptions);
        smokingNode.fillCommands(quiz, QuizNode.smokingOptions);
        alcoholNode.fillCommands(quiz, QuizNode.alcoholOptions);
        sportNode.fillCommands(quiz, QuizNode.sportOptions);
        heightNode.fillCommands(quiz);
        weightNode.fillCommands(quiz);
        bDayNode.fillCommands(quiz);

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

            preStartNode.fillCommands(quiz,preSexNode, preRegionNode, preSmokingNode, preAlcoholNode, preSportNode,
                    preHeightNode, preWeightNode, preBDayNode, start);
            preSexNode.fillCommands(preStartNode, QuizNode.sexOptions);
            preRegionNode.fillCommands(preStartNode, QuizNode.regionOptions);
            preSmokingNode.fillCommands(preStartNode, QuizNode.smokingOptions);
            preAlcoholNode.fillCommands(preStartNode, QuizNode.alcoholOptions);
            preSportNode.fillCommands(preStartNode, QuizNode.sportOptions);
            preHeightNode.fillCommands(preStartNode);
            preWeightNode.fillCommands(preStartNode);
            preBDayNode.fillCommands(preStartNode);

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

    String greet(){
        if (firstTime)
            return "Hello! This is Boring Life bot.\n" +
                   "Use \"help\" to see list off available commands.\n" +
                    "Firstly fill information about yourself.";
        else
            return "Hello again!";
    }
    
    List<String> currentCommandList(){ 
    	return currentNode.getCommandList(); 
    	}
}