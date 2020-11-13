package Data;

import java.util.ArrayList;
import java.util.List;

import DataVisitor.TreeElementVisitor;
import User.User;

public class UserGroup implements TreeElement{
    private String name;
    private List<TreeElement> elements;

    private final boolean leaf = false;

    public UserGroup(){
        name = "root";
        elements = new ArrayList<TreeElement>();
    }

    public UserGroup(String name){
        this.name = name;
        elements = new ArrayList<TreeElement>();
    }

    public boolean addElement(TreeElement s){
        return elements.add(s);
    }



    public UserGroup addGroup(String name, UserGroup location) {
        if(findGroup(name) != null)
            return null;
        if(findGroup(location.getName()) == null)
            return null;
        TreeElement temp = new UserGroup(name);
        location.addElement(temp);
        return (UserGroup)temp;
    }

    public User addUser(String name, UserGroup location){
        if(findID(name) != null)
            return null;
        if(findGroup(location.getName()) == null)
            return null;

        TreeElement temp = new User(name);
        location.addElement(temp);
        return (User)temp;
    }

    public User findID(String id){
        for(TreeElement u : elements){
            if(u instanceof User && ((User)u).getID().equals(id))
                return (User)u;
            if(u instanceof UserGroup){
                User a = ((UserGroup)u).findID(id);
                if(a == null)
                    continue;
                return a;
            }
        }
        return null;
    }

    public UserGroup findGroup(String group){
        if(name.equals(group) && this instanceof UserGroup){
            return this;
        }
        for(TreeElement u : elements) {
            if (u instanceof UserGroup) {
                UserGroup a = ((UserGroup) u).findGroup(group);
                if (a == null)
                    continue;
                return a;
            }
        }
        return null;
    }

    public int accept(TreeElementVisitor d){
        int count = 0;
        for(TreeElement t : elements){
            count += t.accept(d);
        }
        count += d.count(this);
        return count;
    }

    public String getName(){
        return name;
    }

    public List<TreeElement> getElements(){
        return elements;
    }
}
