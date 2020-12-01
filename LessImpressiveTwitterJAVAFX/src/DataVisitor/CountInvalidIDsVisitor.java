package DataVisitor;

import Data.UserGroup;
import User.User;

public class CountInvalidIDsVisitor implements CountVisitor {

    @Override
    public Integer visit(User u) {
        String id = u.getID();
        for(int i = 0; i < id.length(); i++)
            if(id.charAt(i) == ' ')
                return 1;
        return 0;
    }

    @Override
    public Integer visit(UserGroup u) {
        String id = u.getName();
        for(int i = 0; i < id.length(); i++)
            if(id.charAt(i) == ' ')
                return 1;
        return 0;
    }
}
