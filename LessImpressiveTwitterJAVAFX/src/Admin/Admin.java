package Admin;

import Data.TreeElement;
import Data.UserGroup;
import DataVisitor.*;
import User.User;

public class Admin {
    private TreeElement root;

    private static Admin instance;

    //SINGLETON STUFF
    private Admin(){
        root = new UserGroup();
    }

    public static Admin getInstance(){
        if(instance == null)
            instance = new Admin();
        return instance;
    }

    public void start(String[] args){
        AdminUI.startUI(args);
    }

    //ADD GROUP STUFF
    protected void addUser(String id, String name) throws RuntimeException{
        if(findUser(id) != null)
            throw new RuntimeException("This UserID may be taken");
        UserGroup target = findGroup(name);
        if(target == null)
            throw new RuntimeException("Please select a group");

        TreeElement temp = new User(id);
        target.addElement(temp);
    }

    protected void addGroup(String name, String targetName) throws RuntimeException{
        if(findGroup(name) != null)
            throw new RuntimeException("This Group may already exist");
        UserGroup target = findGroup(targetName);
        if(target == null)
            throw new RuntimeException("Please select a group");

        TreeElement temp = new UserGroup(name);
        target.addElement(temp);
    }

    //LAUNCH USER VIEW
    protected void launchUserView(String id){
        User target = findUser(id);
        if(target == null)
            throw new RuntimeException("Please select a user from TreeView");
        target.render();
    }

    //VISITOR STUFF
    protected int countUsers(){
        CountVisitor visitor = new CountUsersVisitor();
        return root.accept(visitor);
    }

    protected int countTweets(){
        CountVisitor visitor = new CountTweetsVisitor();
        return root.accept(visitor);
    }

    protected int countGroups(){
        CountVisitor visitor = new CountGroupsVisitor();
        return root.accept(visitor)-1;
    }

    private double countPositive(){
        CountVisitor visitor = new CountPositiveVisitor();
        return root.accept(visitor);
    }
    public User findUser(String id) {
        FindVisitor f = new FindUserVisitor(id);
        return (User)root.accept(f);
    }

    public UserGroup findGroup(String name){
        FindVisitor f = new FindGroupVisitor(name);
        return (UserGroup) root.accept(f);
    }

    protected double getPositivityRatio(){
        return (100*countPositive())/countTweets();
    }

    //GETTER/ SETTER STUFF
    protected UserGroup getRoot(){
        return (UserGroup)root;
    }

}
