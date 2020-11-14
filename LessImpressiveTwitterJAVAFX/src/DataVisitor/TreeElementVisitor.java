package DataVisitor;

import Data.UserGroup;
import User.User;

public interface TreeElementVisitor {
    Object visit(UserGroup u);
    Object visit(User u);
}
