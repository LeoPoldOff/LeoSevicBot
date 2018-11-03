import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

class BirthdayNode extends Node{
    private PersonInfo personInfo;

    BirthdayNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    Response bDayChangeRespond(String s){
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
}
