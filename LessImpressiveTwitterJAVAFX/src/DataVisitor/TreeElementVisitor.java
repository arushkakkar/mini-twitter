package DataVisitor;

import Data.UserGroup;
import User.User;

public interface TreeElementVisitor {
    int count(UserGroup u);
    int count(User u);
}
