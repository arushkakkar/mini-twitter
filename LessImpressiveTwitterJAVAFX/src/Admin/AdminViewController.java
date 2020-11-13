package Admin;

import Data.UserGroup;
import User.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    private Admin a;

    private Image image;

    @FXML
    private TreeView treeView;

    @FXML
    private TextField UIDTextField;
    @FXML
    private TextField GIDTextField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button addUserButton;
    @FXML
    private Button addGroupButton;
    @FXML
    private Button showUserCountButton;
    @FXML
    private Button showGroupCountButton;
    @FXML
    private Button showTweetCountButton;
    @FXML
    private Button showPositivePercentButton;

    @FXML
    private void addUserButtonClick(){
        a = Admin.getInstance();
        String id = UIDTextField.getText();
        TreeItem selected = (TreeItem)treeView.getSelectionModel().getSelectedItem();
        try {
            String group = selected.getValue().toString();
            User u = a.addUser(id, a.findGroup(group));
            if (u != null) {
                TreeItem<String> temp = new TreeItem<String>(id);
                selected.getChildren().add(temp);
            }
            else{
                Alert a = new Alert(Alert.AlertType.NONE, "User ID may already exist\nPlease select a Group", ButtonType.OK);
                a.setTitle("Error");
                a.show();
            }
        }
        catch(Exception e){
            Alert a = new Alert(Alert.AlertType.NONE, "Please select a Group from TreeView", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    private void addGroupButtonClick(){
        a = Admin.getInstance();
        String name = GIDTextField.getText();
        TreeItem selected = (TreeItem)treeView.getSelectionModel().getSelectedItem();
        try {
            String group = selected.getValue().toString();
            UserGroup u = a.addGroup(name, a.findGroup(group));
            if (u != null) {
                TreeItem<String> temp = new TreeItem<String>(name, new ImageView(image));
                selected.getChildren().add(temp);
            }
            else{
                Alert a = new Alert(Alert.AlertType.NONE, "Group ID may already exist\nPlease select a Group", ButtonType.OK);
                a.setTitle("Error");
                a.show();
            }
        }catch(Exception e){
            Alert a = new Alert(Alert.AlertType.NONE, "Please select a Group from TreeView", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    private void launchUserViewButtonClick(){
        a = Admin.getInstance();
        TreeItem selected = (TreeItem)treeView.getSelectionModel().getSelectedItem();
        String id = selected.getValue().toString();
        User target = a.findUser(id);
        if(target == null){
            Alert a = new Alert(Alert.AlertType.NONE, "Please select a User from TreeView", ButtonType.OK);
            a.show();
            return;
        }
        a.launchUserView(target);
    }

    @FXML
    private void showUserCountButtonClick(){
        a = Admin.getInstance();
        messageLabel.setText("User Count: " + a.countUsers());
    }

    @FXML
    private void showGroupCountButtonClick(){
        a = Admin.getInstance();
        messageLabel.setText("Group Count: " + a.countGroups());
    }

    @FXML
    private void showTweetCountButtonClick(){
        a = Admin.getInstance();
        messageLabel.setText("Tweet Count: " + a.countTweets());
    }

    @FXML
    private void showPositivePercentButtonClick(){
        a = Admin.getInstance();
        messageLabel.setText("Positivity: " + a.getPositivityRatio() + "%");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image = new Image("\\Admin\\folder.png");
        TreeItem<String> root = new TreeItem<String>("root", new ImageView(image));
        treeView.setRoot(root);
        treeView.setShowRoot(true);
    }
}
