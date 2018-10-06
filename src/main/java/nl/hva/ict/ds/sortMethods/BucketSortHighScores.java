/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.ict.ds.sortMethods;

import java.util.ArrayList;
import java.util.List;
import nl.hva.ict.ds.HighScoreList;
import nl.hva.ict.ds.Player;

/**
 *
 * @author rsasb
 */
public class BucketSortHighScores implements HighScoreList {

    private static final int DEFAULT_BUCKET_SIZE = 500;
    private static final int MAX_SCORE = 100000;

    private ArrayList<Player> players = new ArrayList<>();

    @Override
    public void add(Player player) {
        players.add(player);
        sort(DEFAULT_BUCKET_SIZE);
    }

    public void sort(int bucketSize) {
        if (players.isEmpty()) {
            return;
        }

        int bucketCount = MAX_SCORE / bucketSize;
        List<List<Player>> buckets = new ArrayList<List<Player>>(bucketCount);

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Player>());
        }
        for (int i = 0; i < players.size(); i++) {
            buckets.get((int) players.get(i).getHighScore() / bucketSize).add(players.get(i));
        }

        int currentIndex = 0;
        for (int i = 0; i < buckets.size(); i++) {
            Player[] bucketArray = new Player[buckets.get(i).size()];
            bucketArray = buckets.get(i).toArray(bucketArray);

            for (int j = 0; j < bucketArray.length; j++) {
                players.clear();
                players.add(bucketArray[j]);
            }
        }
//        // Sort buckets and place back into input array
//        int currentIndex = 0;
//        for (int i = 0; i < buckets.size(); i++) {
//            Integer[] bucketArray = new Integer[buckets.get(i).size()];
//            bucketArray = buckets.get(i).toArray(bucketArray);
//            InsertionSort.sort(bucketArray);
//            for (int j = 0; j < bucketArray.length; j++) {
//                array[currentIndex++] = bucketArray[j];
//            }
//        }
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        sort(DEFAULT_BUCKET_SIZE);
        return players.subList(0, Math.min(numberOfHighScores, players.size()));
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) {
        List<Player> matchedPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.getFirstName().equals(firstName)) {
                matchedPlayers.add(player);
            }
        }
        return matchedPlayers;
    }

    


}
