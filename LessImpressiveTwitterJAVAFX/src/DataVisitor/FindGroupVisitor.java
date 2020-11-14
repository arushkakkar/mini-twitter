package DataVisitor;

import Data.TreeElement;
import Data.UserGroup;
import User.User;

public class FindGroupVisitor implements FindVisitor {
    private String ID;

    private FindGroupVisitor(){
    }

    public FindGroupVisitor(String id){
        ID = id;
    }

    @Override
    public TreeElement visit(User u) {
        return null;
    }

    @Override
    public TreeElement visit(UserGroup u) {
        if(u.getName().equals(ID)){
            return u;
        }
        return null;
    }
}
