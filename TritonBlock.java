/* Name: Saarthak Mehra
 * 
 */

public class TritonBlock {
    
    /*Class variables, all the attributes of the block*/
    private int index;
    private long timestamp;
    private TritonData data;
    private String prev_hash;
    private String self_hash;
    
    private boolean isHashed; //keeps track of when the block was first hashed
    private final int FLD_LIM = 2; //number of characters to fold after in 
                                   //hashfunction
    private final int PRIME = 17; //prime number used in hash function
    
    /* Constructor, builds a block with passed in variables, 
     * then creates a hash for curr block
     *
     * */
    public TritonBlock(int index, long timestamp, TritonData data, 
        String prev_hash){
        
        this.index = index;
	    this.timestamp = timestamp;
	    this.data = data;
	    this.prev_hash = prev_hash;
    }


    /**
     * Finds the hash of the current block using a unique 
     * folding technique
     * @return String representation of the integer-valued hash
     */ 
    private String hashBlock(){
	//concatenates each property of block into a string
        String fin = "" + index + this.timestamp + prev_hash + data.getProofId();
	    for (String trans : data.getTransactions()) {
	        fin += trans; 
	    } 
	
    	//hash the integer representation of that concatenation
	    int sum = 0;
	    for (int i = 0; i < fin.length(); i++) {
	        sum += (int)fin.charAt(i);
	        if (i % 2 == 1) {
		    sum *= PRIME;
	        }
	    }
	    self_hash = "" + sum; //set hash equal to the self_hash var

	    isHashed = true; //block has been hashed for the first time, or is 
			             //already hashed
	    return self_hash;
    }

    /*Get index*/
    public int getIndex(){
        return index; 
    }

    /*Get timestamp*/
    public long getTimestamp(){
       return this.timestamp; 
    }

    /*Get data block*/
    public TritonData getData(){
        return data;
    }

    /*Get previous hash*/
    public String getPrev_hash(){
        return prev_hash;
    }

    /*Get current hash*/
    public String getSelf_hash(){
        //if block has not been hashed yet
	    if (!isHashed) {
	        self_hash = hashBlock();
	        return self_hash;
	    }
	    //block has been hashed
	    else { 
	        return self_hash;
	    }
    }

    /*Print the block*/
    public String toString() {
	    return "\nTritonBlock " + index + "\nIndex: " + index +
	        "\nTimestamp: " + getTimestamp() + "\nPrev Hash: " + 
	        getPrev_hash() + "\nHash: " + getSelf_hash() + "\n" + 
            data.toString();     
    }
}
