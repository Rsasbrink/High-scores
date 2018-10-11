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
    public int bucketAmount = MAX_SCORE / DEFAULT_BUCKET_SIZE;

    List<List<Player>> buckets;
    public ArrayList<Player> players = new ArrayList<>();

    public BucketSortHighScores() {
        buckets = new ArrayList<>(bucketAmount);
        for (int i = 0; i < bucketAmount; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    @Override
    public void add(Player player) {
        //Add player to it's bucket
        buckets.get((int) (((int) player.getHighScore()) / DEFAULT_BUCKET_SIZE)).add(player);
    }

    public void sort(int bucketSize) {
        // for every bucket, sort and put the players in the arrayList
        for (int i = bucketAmount - 1; i >= 0; i--) {
            Player[] bucketArray = new Player[buckets.get(i).size()];
            // Convert bucket to array, so it can be sorted
            bucketArray = buckets.get(i).toArray(bucketArray);
            selectionSort(bucketArray);

            // Convert to list to reverse it, since we're sorting reversed. 
            List<Object> list = Arrays.asList(bucketArray);
            Collections.reverse(list);
            bucketArray = (Player[]) list.toArray();

            // add players in the bucket to the arrayList. 
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
        // Quick add al highscores to arrayList without sorting 
        for (int i = bucketAmount - 1; i >= 0; i--) {
            Player[] bucketArray = new Player[buckets.get(i).size()];
            bucketArray = buckets.get(i).toArray(bucketArray);
            for (Player player : bucketArray) {
                players.add(player);
            }
        }
         
        List<Player> matchedPlayers = new ArrayList<>();
        for (Player player : players) {

            if ("".equals(lastName.trim())) {
                if (player.getFirstName().equals(firstName)) {
                    matchedPlayers.add(player);
                }
            } else if ("".equals(firstName.trim())) {
                if (player.getLastName().equals(lastName)) {
                    matchedPlayers.add(player);
                }
            } else {
                if (player.getFirstName().equals(firstName) && player.getLastName().equals(lastName)) {
                    matchedPlayers.add(player);
                }
            }
        }
        return matchedPlayers;
    }

}
