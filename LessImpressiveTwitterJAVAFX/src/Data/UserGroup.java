package Data;

import java.util.ArrayList;
import java.util.List;

import DataVisitor.CountVisitor;
import DataVisitor.FindVisitor;

public class UserGroup implements TreeElement{
    private String name;
    private List<TreeElement> elements;

    public UserGroup(){
        name = "root";
        elements = new ArrayList<TreeElement>();
    }

    public UserGroup(String name){
        this.name = name;
        elements = new ArrayList<TreeElement>();
    }

    public void addElement(TreeElement e) {
        elements.add(e);
    }

    public int accept(CountVisitor d){
        int count = 0;
        for(TreeElement t : elements){
            count += t.accept(d);
        }
        count += d.visit(this);
        return count;
    }

    public TreeElement accept(FindVisitor d){
        TreeElement u = d.visit(this);
        if(u != null)
            return this;
        for(TreeElement t: elements){
            u = t.accept(d);
            if(u != null)
                return u;
        }
        return null;
    }

    public String getName(){
        return name;
    }

}
