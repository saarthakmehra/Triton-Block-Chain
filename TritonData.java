/**
 * Name: Saarthak Mehra 
 *
 **/
import java.util.ArrayList;
import java.util.List;

public class TritonData {

    private List<String> transactions;
    private int proofId;

    /**
     * Triton Data Constructor
     * @param None
     */
    public TritonData(){
        this.transactions = new ArrayList<String>();  
    }

    /*Constructor if specific values are specified*/
    public TritonData(int proofId, List<String> transactions){
        this.transactions = (ArrayList<String>)transactions;
	    this.proofId = proofId;       
    }

    /*Get transactions*/
    public List<String> getTransactions() {
        return transactions;  
    }

    /*Get proofId*/
    public int getProofId() {
        return proofId; 
    }

    /*Print the data block*/
    public String toString(){
        String start = "DATA Start------------------------------" + 
	    "\nProof of work: " + getProofId();
	    String transax = "";
	    // checks if data is not of the 0 index Triton Block
	    if (!getTransactions().isEmpty()) {	 
	        for (int i = 0; i < getTransactions().size() - 1; i++) {
	    	    String str = getTransactions().get(i);
                
                transax += "\nTransaction " + (i+1) + "\nTransaction Content: " 
	                + getTransactions().get(i);
	        }
	    }
        return start + transax + "\nDATA End------------------------------";
    }}
