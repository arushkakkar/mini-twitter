package DataVisitor;

import Data.Tweet;
import Data.UserGroup;
import User.User;

import java.util.Arrays;
import java.util.List;

public class CountPositiveVisitor implements CountVisitor {

    public Integer visit(User u) {
        int positiveCount = 0;
        for(Tweet t: u.getTweets()) {
            String[] tweet = t.getTweet().split(" ");
            List<String> words = Arrays.asList(tweet);
            if (words.contains("good") || words.contains("great") || words.contains("wonderful")) {
                positiveCount++;
                continue;
            }
        }
        return positiveCount;
    }

    public Integer visit(UserGroup u){
        return 0;
    }
}
