package DataVisitor;

import Data.TreeElement;
import Data.UserGroup;
import User.User;

public class FindUserVisitor implements FindVisitor {

    private String ID;

    private FindUserVisitor(){
    }

    public FindUserVisitor(String id){
        ID = id;
    }


    @Override
    public TreeElement visit(User u) {
        if(u.getID().equals(ID)){
            return u;
        }
        return null;
    }

    @Override
    public TreeElement visit(UserGroup u) {
        return null;
    }
}
