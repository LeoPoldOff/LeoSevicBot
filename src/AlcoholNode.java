import java.util.HashMap;

class AlcoholNode extends Node {
    private PersonInfo personInfo;

    AlcoholNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    Response alcoholChangeRespond(String s){
        personInfo.updateAlcoholRange(s);
        return new Response("Your alcohol habit changed to \"" + s + "\"", 0);
    }
}
