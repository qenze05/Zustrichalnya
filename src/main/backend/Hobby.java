public class Hobby {

    String name;
    Hobby parent;
    public Hobby(String name){
        this.name = name;
        this.parent = null;
    }

    public Hobby(String name, Hobby parent){
        this.name = name;
        this.parent = parent;
    }

    public boolean hasParent(){
        if(parent == null) return false;
        return true;
    }

}
