package DataVisitor;

import Data.TreeElement;
import Data.UserGroup;
import User.User;

public interface FindVisitor extends TreeElementVisitor{
    TreeElement visit(User u);
    TreeElement visit(UserGroup u);
}
