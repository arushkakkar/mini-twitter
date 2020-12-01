package User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Admin.Admin;
import Data.TreeElement;
import Data.Tweet;
import DataVisitor.CountVisitor;
import DataVisitor.FindVisitor;
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
    private long creationTime;
    private long lastUpdatedTime;

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
        creationTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date(creationTime);
        lastUpdatedTime = System.currentTimeMillis();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserView.fxml"));
        try {
            Parent root = loader.load();
            userStage = new Stage();
            userStage.setTitle(ID + " (CreationTime: " + sdf.format(date) +")");
            userStage.setScene(new Scene(root, 390, 340));
            userStage.setOnCloseRequest(event -> {
                windowOpen = false;
            });
        }catch(Exception e){ }

        controller = loader.getController();
        controller.setMaster(this);
    }

    public void tweet (String text) {
        Tweet t = new Tweet(ID, text);
        tweets.add(t);
        feed.add(t);
        lastUpdatedTime = System.currentTimeMillis();
        Admin a = Admin.getInstance();
        a.findLastUpdatedUser();
        controller.populateFeed();
        notifyObservers();
    }

    public boolean follow(String id) throws RuntimeException{
        Admin a = Admin.getInstance();
        User target = a.findUser(id);
        if(target == null)
            throw new RuntimeException("Invalid UserID");
        if(id.equals(ID))
            throw new RuntimeException("You may not follow yourself");
        if(following.contains(target))
            throw new RuntimeException("You are already following this user");

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

    public long getLastUpdatedTime(){
        return lastUpdatedTime;
    }

    public int accept(CountVisitor visitor){
        return visitor.visit(this);
    }

    public TreeElement accept(FindVisitor visitor){
        return visitor.visit(this);
    }
}
