import java.util.HashMap;

class SportNode extends Node {
    private PersonInfo personInfo;

    SportNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    Response sportChangeRespond(String s){
        personInfo.updateSportRange(s);
        return new Response("Your sport habit changed to \"" + s + "\"", 0);
    }
}
