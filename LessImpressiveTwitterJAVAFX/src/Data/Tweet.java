package Data;

public class Tweet {
    private String user;
    private String tweet;

    private Tweet(){
    }

    public Tweet(String u, String t){
        user = u;
        tweet = t;
    }

    public String getUser() {
        return user;
    }

    public String getTweet() {
        return tweet;
    }

    public String toString(){
        return user + ": " + tweet;
    }

}
