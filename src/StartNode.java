import java.util.HashMap;

class StartNode extends Node {
    private PersonInfo personInfo;

    StartNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }
    void fillCommands(QuizNode quiz){
        commands.put("my info", new BotAction(this::myInfo, this));
        commands.put("change info", new BotAction(this::changeInfo, quiz));
        commands.put("when i die?", new BotAction(this::whenIDie, this));
        commands.put("exit", new BotAction(this::exit, this));
    }

    private Response myInfo(){
        return new Response(personInfo.showInfo(), 0);
    }

    private Response exit(){
        return new Response("Bye!", 1);
    }

    private Response whenIDie(){
        var age = Tree.calcAge(personInfo);
        var userRespond = String.format("You'll die at age %d", age);
        return new Response(userRespond, 0);
    }

    private Response changeInfo(){
        return new Response("What parameter you want to change?",0);
    }
}
