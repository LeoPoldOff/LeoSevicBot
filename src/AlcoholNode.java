import java.util.HashMap;
import java.util.Map;

public class AlcoholNode implements INode {
    private PersonInfo personInfo;
    Map<String,BotAction> commands;

    AlcoholNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    Response alcoholChangeRespond(String s){
        personInfo.updateAlcoholRange(s);
        return new Response("Your alcohol habit changed to \"" + s + "\"", 0);
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
