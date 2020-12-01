package DataVisitor;

import Data.TreeElement;
import Data.UserGroup;
import User.User;

public class FindLastUpdatedUserVisitor implements FindVisitor {

    private User user;
    private long time;

    public FindLastUpdatedUserVisitor(){
        user = null;
        time = -1;
    }


    @Override
    public TreeElement visit(User u) {
        if(u.getLastUpdatedTime() > time){
            user = u;
            time = u.getLastUpdatedTime();
        }
        return null;
    }

    @Override
    public TreeElement visit(UserGroup u) {
        return null;
    }

    public User getUser(){
        return user;
    }
}