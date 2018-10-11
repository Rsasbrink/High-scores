package nl.hva.ict.ds.sortMethods;

import java.util.ArrayList;
import java.util.List;
import nl.hva.ict.ds.HighScoreList;
import nl.hva.ict.ds.Player;

public class InsertionSortHighScores implements HighScoreList {

    private ArrayList<Player> players = new ArrayList<Player>();

    @Override
    public void add(Player player) {
        players.add(player);
        sortScores();
    }

    private void sortScores() {
        for (int i = 1; i < players.size(); i++) {
            Player playerToSort = players.get(i);
            int j = i;
            while (j > 0 && players.get(j - 1).getHighScore() < playerToSort.getHighScore()) {
               players.set(j, players.get(j-1));
               j--;
            }
            players.set(j, playerToSort);
        }
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        return players.subList(0, Math.min(numberOfHighScores, players.size()));
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) {
        List<Player> matchedPlayers = new ArrayList<>();
        players.forEach((player) -> {
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
        });
        return matchedPlayers;
    }
}
