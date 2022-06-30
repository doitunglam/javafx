package java_btl;

public class Method{
    private String name;
    public Method(String name){
        this.name=name;
    }
    public void setName(String name){
    this.name=name;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
