import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class BirthdayNode implements INode{
    private Map<String,BotAction> commands;
    private PersonInfo personInfo;

    BirthdayNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    void fillCommands(INode node){
        commands.put("", new BotAction(this::bDayChangeRespond, node));
    }

    private Response bDayChangeRespond(String s){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        try {
            dateFormat.parse(s);
        }
        catch (ParseException e){
            return new Response("Wrong date format", 0);
        }
        personInfo.updateBirthday(s);
        return new Response("Your birthday changed to \"" + s + "\"", 0);
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
        return new Response("Type your birthday (dd.mm.yyyy)", 0);
    }

    public List<String> getCommandList(){
        return new ArrayList<>();
    }

    public Response unknown(){
        return new Response ("Unknown", 0);
    }
}
