/**
 * Name: Saarthak Mehra 
 *
 **/

import java.util.*;
import java.util.LinkedList;
public class TritonBlockChain {

    private static final String MINE_REWARD = "1";
    /*Blockchain clas variable*/
    private List<TritonBlock> blockchain;

    /*Constructor, takes in genesis block data to start the blockchain*/
    public TritonBlockChain(int index, long timestamp, TritonData data, String 
        prev_hash) {
        
        blockchain = new LinkedList<TritonBlock>();
	    blockchain.add(new TritonBlock(index, timestamp, data, prev_hash));
    }

    /*Makes the next block after the proof of work from mining is finished*/
    public TritonBlock makeNewBlock(TritonBlock lastBlock, TritonData newData) {
        TritonBlock currBlock = new TritonBlock(lastBlock.getIndex() + 1,
	    System.currentTimeMillis(), newData, lastBlock.getSelf_hash());
	    return currBlock;
    }

    /*Mines the transaction and creates the block to add to the blockchain*/
    public boolean beginMine(List<String> curTransactions) {
	    int proofId = 0;
	    if (curTransactions.isEmpty()) {
	        return false;
	    } 
	    else { 
	        proofId = proofOfWork();
	        curTransactions.add("Triton coined earned: " + MINE_REWARD);
	        blockchain.add(makeNewBlock(blockchain.get(blockchain.size() - 1), 
                new TritonData(proofOfWork(), curTransactions)));
   	        return true;
	    } 
    }

    /*Simple proof of work algorithm to prove cpu usage was used to mine block*/
    public int proofOfWork() {
        int lastProofId = 
            blockchain.get(blockchain.size() - 1).getData().getProofId();
	    boolean isDivisible = false;
	    int currProofId = 13;
	    
        while (true) {
	        if (currProofId % 13 == 0 && currProofId % (lastProofId + 1) == 0) {
		        break;
	        }
	        ++currProofId;
	    } 
    	return currProofId; 
    }

    /*Prints current blockchain*/
    public String toString() {
        String output = "";
	    for (TritonBlock block : blockchain) {
	        output += "\n\n" + block.toString();
	    }
	    return "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n" +
	        "T R I T O N  B L O C K C H A I N" +
	        "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + output;
    }

    /*Validates each block in the chain looking for any hash pointer 
     * descrepancies, which can point to a tampering problem
     *
     **/
    public boolean validateChain() {
	    for (int i = 1; i < blockchain.size() - 1; i++) {
	        if (blockchain.get(i).getPrev_hash() != 
                blockchain.get(i-1).getSelf_hash()) {
		        
                return false;
	        }
	    }
	    return true;
    }

    /*Get blockchain*/
    public List<TritonBlock> getBlockchain() {
        return blockchain; 
    }

}
