/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.ict.ds.sortMethods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import nl.hva.ict.ds.HighScoreList;
import nl.hva.ict.ds.Player;

/**
 *
 * @author rsasb
 */
public class PriorityQueueHighScores implements HighScoreList {

    Comparator<Player> HighScoreComparator = new Comparator<Player>() {
        @Override
        public int compare(Player player1, Player player2) {
            return (int) (player2.getHighScore() - player1.getHighScore());
        }
    };
    
    PriorityQueue<Player> HighScoresPriorityQueue = new PriorityQueue<>(HighScoreComparator);

    private final ArrayList<Player> players = new ArrayList<>();

    @Override
    public void add(Player player) {
        HighScoresPriorityQueue.add(player);
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        copyQueueToArrayList();

        return players.subList(0, Math.min(numberOfHighScores, players.size()));

    }

    public void copyQueueToArrayList() {
        // Clear the list, create a copy, since polling removes the object from te list.
        players.clear();
        PriorityQueue<Player> PQCopy = new PriorityQueue<>(HighScoresPriorityQueue);

        while (!PQCopy.isEmpty()) {
            players.add(PQCopy.poll());
        }
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) {
        copyQueueToArrayList();
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
