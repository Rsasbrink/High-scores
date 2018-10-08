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
import java.util.Queue;
import nl.hva.ict.ds.HighScoreList;
import nl.hva.ict.ds.Player;

/**
 *
 * @author rsasb
 */
public class PriorityQueueHighScores implements HighScoreList {

    Queue<Player> players;

    @Override
    public void add(Player player) {
        players.add(player);
    }

    private void sortScores() {

    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        return (List<Player>) players;
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) {
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
