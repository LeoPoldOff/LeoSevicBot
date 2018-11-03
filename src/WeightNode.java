import java.util.HashMap;

public class WeightNode extends Node {
    private PersonInfo personInfo;

    WeightNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    Response weightChangeRespond(String s){
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
}
