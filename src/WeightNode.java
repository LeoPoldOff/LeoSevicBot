import java.util.HashMap;
import java.util.Map;

public class WeightNode implements INode {
    private Map<String,BotAction> commands;
    private PersonInfo personInfo;

    WeightNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    void fillCommands(INode node){
        commands.put("", new BotAction(this::weightChangeRespond, node));
    }

    private Response weightChangeRespond(String s){
        personInfo.updateWeightRange(s);
        return new Response("Your weight changed to " + s + " kg", 0);
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
        return new Response("Type your weight in kg", 0);
    }

    public Response unknown(){
        return new Response ("Unknown", 0);
    }
}
