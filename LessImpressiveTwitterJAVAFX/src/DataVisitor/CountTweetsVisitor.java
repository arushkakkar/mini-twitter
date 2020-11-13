package DataVisitor;

import Data.UserGroup;
import User.User;

public class CountTweetsVisitor implements TreeElementVisitor {

    @Override
    public int count(User u) {
        return u.getTweets().size();
    }

    public int count(UserGroup u){
        return 0;
    }

}
