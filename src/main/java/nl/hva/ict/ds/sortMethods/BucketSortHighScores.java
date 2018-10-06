/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.ict.ds.sortMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import nl.hva.ict.ds.HighScoreList;
import nl.hva.ict.ds.Player;

/**
 *
 * @author rsasb
 */
public class BucketSortHighScores implements HighScoreList {

    private static final int DEFAULT_BUCKET_SIZE = 5000;
    private static final int MAX_SCORE = 100000;

    public ArrayList<Player> players = new ArrayList<>();

    @Override
    public void add(Player player) {
        players.add(player);

    }

    public void sort(int bucketSize) {
        if (players.isEmpty()) {
            return;
        }

        long minValue = players.get(0).getHighScore();
        long maxValue = players.get(0).getHighScore();
        for (int i = 1; i < players.size(); i++) {
            if (players.get(i).getHighScore() < minValue) {
                minValue = players.get(i).getHighScore();
            } else if (players.get(i).getHighScore() > maxValue) {
                maxValue = players.get(i).getHighScore();
            }
        }

        // Initialise buckets
        int bucketCount = (int) ((maxValue - minValue) / bucketSize + 1);
        List<List<Player>> buckets = new ArrayList<>(bucketCount);

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribute input array values into buckets
        for (int i = 0; i < players.size(); i++) {
            buckets.get((int) (((int) players.get(i).getHighScore() - minValue) / bucketSize)).add(players.get(i));
        }
        players.clear();
        int currentIndex = 0;
        int bucketAmount = buckets.size() - 1;
        for (int i = bucketAmount; i > 0; i--) {
            Player[] bucketArray = new Player[buckets.get(i).size()];
            bucketArray = buckets.get(i).toArray(bucketArray);
            selectionSort(bucketArray);
            List<Object> list = Arrays.asList(bucketArray);
            Collections.reverse(list);
            bucketArray = (Player[]) list.toArray();
            for (Player player : bucketArray) {
                players.add(player);
            }
        }
    }

    void selectionSort(Player[] bucket) {
        int length = bucket.length;

        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < length - 1; i++) {
            // Find the minimum element in unsorted array 
            int min_idx = i;
            for (int j = i + 1; j < length; j++) {
                if (bucket[j].getHighScore() < bucket[min_idx].getHighScore()) {
                    min_idx = j;
                }
            }

            // Swap the found minimum element with the first 
            // element 
            Player temp = bucket[min_idx];
            bucket[min_idx] = bucket[i];
            bucket[i] = temp;
        }
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
