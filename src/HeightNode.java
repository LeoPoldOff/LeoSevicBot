import java.util.HashMap;
import java.util.Map;

public class HeightNode implements INode {
    Map<String,BotAction> commands;
    private PersonInfo personInfo;

    HeightNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }
    Response heightChangeRespond(String s){
        personInfo.updateHeightRange(s);
        return new Response("Your height changed to " + s + " sm", 0);
    }

    public BotAction getAnswer(String s){
        BotAction command = commands.get("");
        if (s.equals("help")) {
            return new BotAction(this::viewHelp, this);
        }
        else if (command == null)
            return new BotAction(this::unknown, this);
        return command;
    }

    public Response viewHelp() {
        return new Response("Type your height in sm", 0);
    }

    public Response unknown(){
        return new Response ("Unknown", 0);
    }
}
