import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

class Twitter {
    private static int timeStamp = 0;
    private HashMap<Integer, User> userMap = new HashMap<>();

    private static class User {
        private HashSet<Integer> followees;
        private Tweet head;
        private int id;

        public User(int id) {
            this.id = id;
            this.head = null;
            followees = new HashSet<>();
            follow(id);
        }

        public void follow(int id) {
            followees.add(id);
        }

        public void unfollow(int id) {
            if (this.id != id) {
                followees.remove(id);
            }
        }

        public void post(int tweetId) {
            Tweet newTweet = new Tweet(id, timeStamp);
            timeStamp++;
            newTweet.next = head;
            head = newTweet;
        }
    }

    private static class Tweet {
        private int id;
        private int time;
        private Tweet next;

        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
            this.next = null;
        }
    }

    /** Initialize your data structure here. */
    public Twitter() {
        userMap = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        userMap.putIfAbsent(userId, new User(userId));
        User user = userMap.get(userId);
        user.post(tweetId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in
     * the news feed must be posted by users who the user followed or by the user
     * herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        if (!userMap.containsKey(userId)) {
            return result;
        }
        User user = userMap.get(userId);
        PriorityQueue<Tweet> priorityQueue = new PriorityQueue<>(user.followees.size(), (a, b) -> (b.time - a.time));
        for (Integer followeeId : user.followees) {
            Tweet tweet = userMap.get(followeeId).head;
            if (tweet == null)
                continue;
            priorityQueue.add(tweet);
        }
        while (!priorityQueue.isEmpty()) {
            Tweet tweet = priorityQueue.poll();
            result.add(tweet.id);
            if (tweet.next != null) {
                priorityQueue.add(tweet.next);
            }
            if (result.size() == 10) {
                break;
            }
        }
        return result;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a
     * no-op.
     */
    public void follow(int followerId, int followeeId) {
        userMap.putIfAbsent(followerId, new User(followerId));
        userMap.putIfAbsent(followeeId, new User(followeeId));
        User user = userMap.get(followerId);
        user.follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a
     * no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            User user = userMap.get(followerId);
            user.unfollow(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such: Twitter obj =
 * new Twitter(); obj.postTweet(userId,tweetId); List<Integer> param_2 =
 * obj.getNewsFeed(userId); obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */