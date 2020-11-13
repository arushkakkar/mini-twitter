import Admin.Admin;
import Data.UserGroup;
import User.User;

public class Driver {
    public static void main(String[] args){
        Admin a = Admin.getInstance();
        a.start(args);
    }
}