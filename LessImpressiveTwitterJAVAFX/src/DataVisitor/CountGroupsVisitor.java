package DataVisitor;

import Data.UserGroup;
import User.User;

public class CountGroupsVisitor implements CountVisitor {

    public Integer visit(UserGroup ug) {
        return 1;
    }

    public Integer visit(User u){
        return 0;
    }

}