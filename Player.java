public class Player {
    String playerName;
    Tile[] playerTiles;
    int numberOfTiles;

    public Player(String name) {
        setName(name);
        playerTiles = new Tile[15]; // there are at most 15 tiles a player owns at any time
        numberOfTiles = 0; // currently this player owns 0 tiles, will pick tiles at the beggining of the game
    }
    
    
    public boolean checkWinning() 
    {
        if(findLongestChain() == 14)
        {
            //if player have 14 continious tiles, it will lead win
            return true;
        }
        else
        {
            return false;
        }
    }
    
    // simple bubble sort for tile value sorting
    public void TileSort() 
    {
        int n = playerTiles.length;
        for (int i = 0; i < n - 1; i++) 
        {
            for (int j = 0; j < n - i - 1; j++) 
            {
                if (playerTiles[j].getValue() > playerTiles[j + 1].getValue()) 
                {
                    // Swap playerTiles[j] and playerTiles[j+1]
                    Tile temp = playerTiles[j];
                    playerTiles[j] = playerTiles[j + 1];
                    playerTiles[j + 1] = temp;
                }
            }
        }
    }
    
    public int findLongestChain() 
    {
        TileSort();
        int count = 1;
        for (int i = 1; i < playerTiles.length; i++) 
        {
            if (playerTiles[i].getValue() == ((playerTiles[i - 1].getValue()) + 1)) 
            {
                count++;
            } 
            else 
            {
                // count reset
                count = 1; 
            }
        }
        return count;
    }
    
    public Tile getAndRemoveTile(int index) {
        playerTiles[index] = null;
        TileSort();
        return null;
    }

    public void addTile(Tile t) 
    {
        playerTiles[15] = t;
        TileSort();
    }

    /*
     * finds the index for a given tile in this player's hand
     */
    public int findPositionOfTile(Tile t) {
        int tilePosition = -1;
        for (int i = 0; i < numberOfTiles; i++) {
            if(playerTiles[i].matchingTiles(t)) {
                tilePosition = i;
            }
        }
        return tilePosition;
    }

    /*
     * displays the tiles of this player
     */
    public void displayTiles() {
        System.out.println(playerName + "'s Tiles:");
        for (int i = 0; i < numberOfTiles; i++) {
            System.out.print(playerTiles[i].toString() + " ");
        }
        System.out.println();
    }

    public Tile[] getTiles() {
        return playerTiles;
    }

    public void setName(String name) {
        playerName = name;
    }

    public String getName() {
        return playerName;
    }
}
