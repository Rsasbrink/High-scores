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
public class PriorityQueueHighScores implements HighScoreList {

    private ArrayList<Player> players = new ArrayList<>();

    @Override
    public void add(Player player) {
        players.add(player);
        sortScores();
    }

    private void sortScores() {
//        for (int i = 0; i < players.size() - 1; i++) {
//            int pos = i;
//            // find position of smallest num between (i + 1)th element and last element
//            for (int j = i + 1; j <= players.size(); j++) {
//                if (players.get(j).getHighScore() < players.get(pos).getHighScore())
//                    pos = j;
//
//                // Swap min (smallest num) to current position on playersay
//                Player min = players.get(pos);
//                players.set(pos, players.get(i));
//                players.set(i, min);
//            }
//        }
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        return players.subList(0, Math.min(numberOfHighScores, players.size()));
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
