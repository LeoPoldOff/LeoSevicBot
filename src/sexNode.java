import java.util.HashMap;

class SexNode extends Node {
    private PersonInfo personInfo;

    SexNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    Response sexChangeRespond(String s){
        personInfo.updateSex(s);
        return new Response("Your sex changed to " + s, 0);
    }
}
