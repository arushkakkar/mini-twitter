package DataVisitor;

import Data.UserGroup;
import User.User;

public class CountUsersVisitor implements CountVisitor {
    public Integer visit(UserGroup ug){
        return 0;
    }

    public Integer visit(User u){
        return 1;
    }
}