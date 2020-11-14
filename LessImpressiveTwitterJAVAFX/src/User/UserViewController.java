package User;

import Data.Tweet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

    private User master;

    @FXML private ListView<String> tweets;
    @FXML private ListView<String> following;

    @FXML private TextField userNameTextField;
    @FXML private TextField tweetTextField;

    @FXML private Button followUserButton;
    @FXML private Button tweetButton;

    @FXML
    private void followUserButtonClick(){
        String id = userNameTextField.getText();
        try {
            master.follow(id);
        }
        catch(RuntimeException e){
            Alert a = new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK);
            a.setTitle("error");
            a.show();
        }

    }

    @FXML
    private void tweetButtonClick(){
        String tweet = tweetTextField.getText();
        master.tweet(tweet);
    }

    protected void populateFeed(){
        ObservableList<String> items = FXCollections.observableArrayList();
        List<Tweet> feed = master.getFeed();
        for(int i = feed.size()-1; i >=0; i--) {
            items.add(feed.get(i).toString());
        }
        tweets.setItems(items);
    }

    protected void populateFollowing(){
        ObservableList<String> items = FXCollections.observableArrayList();
        for(User u: master.getFollowing()){
            items.add(u.getID());
        }
        following.setItems(items);
    }

    protected void setMaster(User u){
        master = u;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
    }
}