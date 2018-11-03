import java.util.HashMap;

class RegionNode extends Node {
    private PersonInfo personInfo;

    RegionNode(PersonInfo pInfo) {
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    Response regionChangeRespond(String s) {
        personInfo.updateRegion(s);
        return new Response("Your region changed to " + s, 0);
    }
}
