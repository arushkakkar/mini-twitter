import Admin.Admin;

public class Driver {
    public static void main(String[] args){
        Admin a = Admin.getInstance();
        a.start(args);
    }
}