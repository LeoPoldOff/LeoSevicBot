import java.util.HashMap;

class StartNode extends Node {
    private PersonInfo personInfo;

    StartNode(PersonInfo pInfo){
        personInfo = pInfo;
        commands = new HashMap<>();
    }

    Response myInfo(){
        return new Response(personInfo.showInfo(), 0);
    }

    Response exit(){
        return new Response("Bye!", 1);
    }

    Response whenIDie(){
        var age = Tree.calcAge(personInfo);
        var userRespond = String.format("You'll die at age %d", age);
        return new Response(userRespond, 0);
    }

    Response changeInfo(){
        return new Response("What parameter you want to change?",0);
    }
}
