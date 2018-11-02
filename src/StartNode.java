import java.util.HashMap;
import java.util.Map;

class StartNode implements INode {
    private PersonInfo personInfo;
    Map<String,BotAction> commands;

    StartNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    Response myInfo(){
        return new Response(personInfo.showInfo(), 0);
    }

    Response exit(){
        return new Response("Bye!", 1);
    }

    Response whenIDie(){
        var age = Tree.calcAge(personInfo);
        var userRespond = String.format("You'll die at age %d", age);
        return new Response(userRespond, 0);
    }

    Response changeInfo(){
        return new Response("Type \"change\" + param you want to change",0);
    }

    public BotAction getAnswer(String s){
        BotAction command = commands.get(s);
        if (s.equals("help")) {
            return new BotAction(this::viewHelp, this);
        }
        else if (command == null)
            return new BotAction(this::unknown, this);
        return command;
    }

    public Response viewHelp() {
        var help = new StringBuilder();
        for (var key : commands.keySet()) {
            if (!key.equals("help"))
                help.append(key).append("\n");
        }
        return new Response(help.substring(0, help.length() - 1), 0);
    }

    public Response unknown(){
        return new Response ("Unknown", 0);
    }
}
