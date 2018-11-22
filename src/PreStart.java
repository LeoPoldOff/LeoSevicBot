import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreStart implements INode{
    private Map<String,BotAction> commands;
    private List<BotAction> usedCommands;

    PreStart(){
        commands = new HashMap<>();
        usedCommands = new ArrayList<>();
    }

    void fillCommands(QuizNode quiz, SexNode preSexNode, RegionNode  preRegionNode, SmokingNode preSmokingNode,
                      AlcoholNode preAlcoholNode, SportNode preSportNode, HeightNode preHeightNode,
                      WeightNode preWeightNode, BirthdayNode preBDayNode, StartNode start){
        commands.put("my sex", new BotAction(quiz::changeSex, preSexNode));
        commands.put("my region", new BotAction(quiz::changeRegion, preRegionNode));
        commands.put("my smoking habit", new BotAction(quiz::changeSmokingRange, preSmokingNode));
        commands.put("my alcohol habit", new BotAction(quiz::changeAlcoholRange, preAlcoholNode));
        commands.put("my sport habit", new BotAction(quiz::changeSportRange, preSportNode));
        commands.put("my height", new BotAction(quiz::changeHeight, preHeightNode));
        commands.put("my weight", new BotAction(quiz::changeWeight, preWeightNode));
        commands.put("my birthday", new BotAction(quiz::changeBDay, preBDayNode));
        commands.put("ok", new BotAction(quiz::writeData, start));
    }

    public BotAction getAnswer(String s){
        BotAction command = commands.get(s);
        if (s.equals("help"))
            return new BotAction(this::viewHelp, this);
        else if (command == null || (s.equals("ok") && commands.size() - 1 != usedCommands.size()))
            return new BotAction(this::unknown, this);
        else if (usedCommands.contains(command))
            return new BotAction(this::alreadyUsed,this);
        usedCommands.add(command);
        return command;
    }

    public Response viewHelp() {
        return new Response(String.join("\n", getCommandList()), 0);
    }

    public List<String> getCommandList() {
        var commandList = new ArrayList<String>();
        if (usedCommands.size() == commands.size() - 1)
            commandList.add("ok");
        else {
            for (var key : commands.keySet()) {
                if (!key.equals("help") && !key.equals("ok") && !usedCommands.contains(commands.get(key)))
                    commandList.add(key);
            }
        }
        return commandList;
    }

    private Response alreadyUsed(){
        return new Response("This field already filled", 0);
    }

    public Response unknown(){
        return new Response ("Unknown", 0);
    }
}
