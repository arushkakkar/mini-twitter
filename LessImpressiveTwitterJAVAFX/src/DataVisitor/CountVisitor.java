package DataVisitor;

import Data.UserGroup;
import User.User;

public interface CountVisitor extends TreeElementVisitor {
    Integer visit(User u);
    Integer visit(UserGroup u);
}