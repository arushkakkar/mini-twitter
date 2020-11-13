package DataVisitor;

import Data.UserGroup;
import User.User;

public class CountUsersVisitor implements TreeElementVisitor {
    public int count(UserGroup ug){
        return 0;
    }

    public int count(User u){
        return 1;
    }
}