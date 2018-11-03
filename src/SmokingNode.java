import java.util.HashMap;

class SmokingNode extends Node {
    private PersonInfo personInfo;

    SmokingNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    Response smokeChangeRespond(String s){
        personInfo.updateSmokingRange(s);
        return new Response("Your smoking habit changed to \"" + s + "\"", 0);
    }
}
