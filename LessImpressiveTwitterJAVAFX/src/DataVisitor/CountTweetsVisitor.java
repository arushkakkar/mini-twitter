package DataVisitor;

import Data.UserGroup;
import User.User;

public class CountTweetsVisitor implements CountVisitor {

    @Override
    public Integer visit(User u) {
        return u.getTweets().size();
    }

    public Integer visit(UserGroup u){
        return 0;
    }

}
