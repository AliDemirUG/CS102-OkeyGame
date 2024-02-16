public class Tile {
    
    int value;

    /*
     * creates a tile using the given value. False jokers are not included in this game.
     */
    public Tile(int value) {
        this.value = value;
    }

    /**
     * @return true if they have the same value; if not, false
     */
    public boolean matchingTiles(Tile t) {
        if(this.compareTo(t) == 0){
            return true;
        }
        return false;
    }

    /**
     * @return integer that corresponds to comparision of the two
     * return 1 if given tile has smaller in value
     * return 0 if they have the same value
     * return -1 if the given tile has higher value
     */
    public int compareTo(Tile t) {
        int valueChecker;
        if(t.getValue() < this.value){
            valueChecker = 1;
        }else if(t.getValue() > this.value){
            valueChecker = -1;
        }else{
            valueChecker = 0;
        }
       return valueChecker;
    }

    /**
     * @return boolean that corresponds whether if the given tile can form a chain or not
     * takes difference between the two and assigns the boolean accordingly
     */
    public boolean canFormChainWith(Tile t) {
        boolean canFormChain;
        if(Math.abs(this.value - t.getValue()) == 1){
            canFormChain = true;
        }
        else{
            canFormChain = false;
        }
        return canFormChain;
    }

    // toString method to overrige the string representation, and it gives proper deck representation of the tiles
    public String toString() {
        return "" + value;
    }

    // getter (accessor) method
    public int getValue() {
        return value;
    }

    // setter (mutator) method
    public void setValue(int value){
        this.value = value;
    }
}
