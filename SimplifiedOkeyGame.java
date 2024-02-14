import java.util.ArrayList;

public class SimplifiedOkeyGame {

    Player[] players;
    Tile[] tiles;
    int tileCount;

    Tile lastDiscardedTile;

    int currentPlayerIndex = 0;

    public SimplifiedOkeyGame() {
        players = new Player[4];
    }

    public void createTiles() {
        tiles = new Tile[104];
        int currentTile = 0;

        // four copies of each value, no jokers
        for (int i = 1; i <= 26; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[currentTile++] = new Tile(i);
            }
        }

        tileCount = 104;
    }

    /*
     * TO/DO: distributes the starting tiles to the players
     * player at index 0 gets 15 tiles and starts first
     * other players get 14 tiles, this method assumes the tiles are already shuffled
     */
    public void distributeTilesToPlayers() {
        for(int i = 0; i < 15; i++){
            players[0].addTile(tiles[i]);
        }
        for(int i = 15; i < 29; i++){
            players[1].addTile(tiles[i]);
        }
        for(int i = 29; i < 43; i++){
            players[0].addTile(tiles[i]);
        }
        for(int i = 0; i < 15; i++){
            players[0].addTile(tiles[i]);
        }
    }

    /*
     * TO/DO: get the last discarded tile for the current player
     * (this simulates picking up the tile discarded by the previous player)
     * it should return the toString method of the tile so that we can print what we picked
     */
    public String getLastDiscardedTile() {
        players[getCurrentPlayerIndex()].addTile(lastDiscardedTile);
        return lastDiscardedTile.toString();
    }

    /*
     * TO/DO: get the top tile from tiles array for the current player
     * that tile is no longer in the tiles array (this simulates picking up the top tile)
     * and it will be given to the current player
     * returns the toString method of the tile so that we can print what we picked
     */
    public String getTopTile() {
        //loop until selected tile isn't null
        int index = 103;
        while (tiles[index] == null) {
            index--;
        }

        //get tile string, add tile, erase tile from index
        String returnVal = tiles[index].toString();
        players[getCurrentPlayerIndex()].addTile(tiles[index]);
        tiles[index] = null;

        return returnVal;
    }

    /**
     * shuffles the tiles
     */
    public void shuffleTiles() {
        int k, value;
        for(int i = 0; i < tiles.length; i++){
            k = (int) (Math.random() * (tileCount + 1));
            value = tiles[i].getValue();
            tiles[i].setValue(tiles[k].getValue());
            tiles[k].setValue(value);
        }
    }

    /*
     * TO/DO: check if game still continues, should return true if current player
     * finished the game. use checkWinning method of the player class to determine
     */
    public boolean didGameFinish() {
        return players[getCurrentPlayerIndex()].checkWinning();
    }

    /* TO/DO: finds the player who has the highest number for the longest chain
     * if multiple players have the same length may return multiple players
     */
    public Player[] getPlayerWithHighestLongestChain() {
        int currentMax = 0;
        ArrayList<Player> winners = new ArrayList<Player>();

        //Find max value by looping through all players
        for (Player player : players) {
            if (player.findLongestChain() > currentMax){
                currentMax = player.findLongestChain();
                winners.clear();
                winners.add(player);
            } else if (player.findLongestChain() == currentMax){
                winners.add(player);
            }
        }

        return (Player[]) winners.toArray();
    }
    
    /*
     * checks if there are more tiles on the stack to continue the game
     */
    public boolean hasMoreTileInStack() {
        return tileCount != 0;
    }

    /*
     * TODO: pick a tile for the current computer player using one of the following:
     * - picking from the tiles array using getTopTile()
     * - picking from the lastDiscardedTile using getLastDiscardedTile()
     * you should check if getting the discarded tile is useful for the computer
     * by checking if it increases the longest chain length, if not get the top tile
     */
    public void pickTileForComputer() {

    }

    /*
     * TODO: Current computer player will discard the least useful tile.
     * you may choose based on how useful each tile is
     */
    public void discardTileForComputer() {

    }

    /*
     * TODO: discards the current player's tile at given index
     * this should set lastDiscardedTile variable and remove that tile from
     * that player's tiles
     */
    public void discardTile(int tileIndex) {

    }

    public void displayDiscardInformation() {
        if(lastDiscardedTile != null) {
            System.out.println("Last Discarded: " + lastDiscardedTile.toString());
        }
    }

    public void displayCurrentPlayersTiles() {
        players[currentPlayerIndex].displayTiles();
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

      public String getCurrentPlayerName() {
        return players[currentPlayerIndex].getName();
    }

    public void passTurnToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 4;
    }

    public void setPlayerName(int index, String name) {
        if(index >= 0 && index <= 3) {
            players[index] = new Player(name);
        }
    }

    public Player[] getPlayers() {
        return players;
    }

}
