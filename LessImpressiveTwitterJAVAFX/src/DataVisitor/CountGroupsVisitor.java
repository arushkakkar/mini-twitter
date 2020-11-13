package DataVisitor;

import Data.UserGroup;
import User.User;

public class CountGroupsVisitor implements TreeElementVisitor {

    public int count(UserGroup ug) {
        return 1;
    }

    public int count(User u){
        return 0;
    }

}