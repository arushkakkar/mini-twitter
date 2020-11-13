package User;

import java.util.ArrayList;
import java.util.List;

import Admin.Admin;
import Data.Tweet;
import DataVisitor.TreeElementVisitor;
import Observer.Observer;
import Observer.Subject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class User extends Subject implements Observer{
    private String ID;
    private List<Tweet> tweets;
    private List<User> following;
    private List<Tweet> feed;

    private boolean windowOpen;
    private Stage userStage;
    private UserViewController controller;

    private User() {
    }

    public User(String id){
        ID = id;
        tweets = new ArrayList<Tweet>();
        following = new ArrayList<User>();
        feed = new ArrayList<Tweet>();
        windowOpen = false;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserView.fxml"));
        try {
            Parent root = loader.load();
            userStage = new Stage();
            userStage.setTitle(ID);
            userStage.setScene(new Scene(root, 390, 340));
            userStage.setOnCloseRequest(event -> {
                windowOpen = false;
            });
        }catch(Exception e){

        }
        controller = loader.getController();
        controller.setMaster(this);
    }

    public void tweet (String text) {
        Tweet t = new Tweet(ID, text);
        tweets.add(t);
        feed.add(t);
        controller.populateFeed();
        notifyObservers();
    }

    public boolean follow(String id){
        Admin a = Admin.getInstance();
        User target = a.findUser(id);
        if(target == null)
            return false;
        if(following.contains(target))
            return false;
        if(id.equals(ID))
            return false;

        following.add(target);
        controller.populateFollowing();
        target.attach(this);
        return true;
    }

    public void render(){
        if(windowOpen)
            return;
        userStage.show();
        windowOpen = true;
    }

    public void update(Subject subject) {
        User u = (User)subject;
        feed.add(u.getTweets().get(u.getTweets().size()-1));
        controller.populateFeed();
    }

    public String getID(){
        return ID;
    }

    public List<Tweet> getTweets(){
        return tweets;
    }

    public List<Tweet> getFeed(){
        return feed;
    }

    public List<User> getFollowing(){
        return following;
    }

    public int accept(TreeElementVisitor visitor){
        return visitor.count(this);
    }
}
