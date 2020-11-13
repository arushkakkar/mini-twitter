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

    //ADD/ FIND USER/ GROUP STUFF
    public User findUser(String id) {
        return ((UserGroup)root).findID(id);
    }

    public UserGroup findGroup(String name){
        return ((UserGroup)root).findGroup(name);
    }

    protected User addUser(String id, UserGroup target){
        if(target == null)
            return null;
        User newUser = ((UserGroup)root).addUser(id, target);
        if(newUser != null){
            return newUser;
        }
        return null;
    }

    protected UserGroup addGroup(String name, UserGroup location){
        UserGroup newGroup = ((UserGroup)root).addGroup(name, location);
        if(newGroup != null){
            return newGroup;
        }
        return null;
    }

    protected void launchUserView(User target){
        target.render();
    }

    //VISITOR STUFF
    protected int countUsers(){
        TreeElementVisitor visitor = new CountUsersVisitor();
        return root.accept(visitor);
    }

    protected int countTweets(){
        TreeElementVisitor visitor = new CountTweetsVisitor();
        return root.accept(visitor);
    }

    protected int countGroups(){
        TreeElementVisitor visitor = new CountGroupsVisitor();
        return root.accept(visitor)-1;
    }

    private double countPositive(){
        TreeElementVisitor visitor = new CountPositiveVisitor();
        return root.accept(visitor);
    }

    protected double getPositivityRatio(){
        return (100*countPositive())/countTweets();
    }

    //GETTER/ SETTER STUFF
    protected UserGroup getRoot(){
        return (UserGroup)root;
    }

}
