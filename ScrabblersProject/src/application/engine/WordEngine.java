package application.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class WordEngine {
	private char[] focusLetter = new char[7];
	private short focusSize = 0;
	private ArrayList list = new ArrayList();
	
	private StringBuilder output = new StringBuilder();
    private final String inputstring;
    private static ArrayList<String> cmb = new ArrayList<String>();
    private static ArrayList<String> pmt = new ArrayList<String>();
    private static int wordLen;
    private static char c;
	
	public WordEngine() {
		
		System.out.println("Dimelo papi");
		addList(GameEngine.getHand());
		String str = new String(focusLetter);
		System.out.println("The current hand is: " + str);
		System.out.println("The string length is: " + str.length());

		System.out.println("The string at 6 is: " + str.charAt(6));
		//createExclusiveList();
		
		inputstring = str;
		
	}
	
	public void main () {
		//WordEngine w = new WordEngine("donkey");
		//w.combine();
		c = 'a';
		System.out.println("inside main");
		for(int i = 0; i < cmb.size(); i++) {  
			printPermutation(cmb.get(i), "");
        }
        System.out.println("POOKIE");
        readFile();
	}
	

	
	public void addList(LinkedList<String> focus) {
		for (int i = 0; i < focus.size(); i++) {
			focusLetter[i] = focus.get(i).charAt(0);
			focusSize++;
		}
	}
	
	public void createInclusiveList() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/ScrabbleDict.txt"));
			String line = reader.readLine();
			line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				
				
				/*
				boolean matchesCriteria = true;
				for (int i = 0; i < focusSize; i++) {
					if (line.indexOf(focusLetter[i]) == -1) {
						matchesCriteria = false;
					}
				}
				if (matchesCriteria) {
					list.add(line);
					System.out.println(line);
				}
				*/
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			System.out.println("file not found");
		}
	}
	
	public void createExclusiveList() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/ScrabbleDict.txt"));
			String line = reader.readLine();
			line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				boolean matchesCriteria = true;
				for (int i = 0; i < focusSize; i++) {
					if (line.indexOf(focusLetter[i]) == -1) {
						matchesCriteria = false;
					}
					if (line.length() > focusSize) {
						matchesCriteria = false;
					}
				}
				if (matchesCriteria) {
					list.add(line);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			System.out.println("file not found");
		}
	}
	
	
	
	public ArrayList getCurrentList() {
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i));
		}
		return list;
	}
	
	static void printPermutation(String str, String ans) 
    { 
  
		System.out.println("ran prmttn func");
        if (str.length() == 0) { 
        	//if(ans.charAt(0) == c) {
	            pmt.add(ans); 
	            //System.out.println(ans);
        	//}
            return; 
        } 
  
        for (int i = 0; i < str.length(); i++) { 
            char ch = str.charAt(i); 
            String ros = str.substring(0, i) + str.substring(i + 1); 

            printPermutation(ros, ans + ch); 
        } 
    } 
       
    
    public void combine() { combine( 0 ); }
    private void combine(int start ){
    	System.out.println("ran combine func");
    	System.out.println(inputstring.length());
        for( int i = start; i < inputstring.length(); ++i ){
            output.append( inputstring.charAt(i) );
            
            String str = output.toString();
            System.out.println( str );
            //if(str.length() == wordLen)
            	cmb.add(str);
            if ( i < inputstring.length() )
            	combine( i + 1);
            
            output.setLength( output.length() - 1 );
        }
    }
    
    public static void readFile () {
    	System.out.println("reading file rn, excuse me");
    	try {
	    	File file = new File("res/ScrabbleDict.txt"); 
	    	BufferedReader br = new BufferedReader(new FileReader(file)); 
	    	
	    	String s;
	    	
	    	while ((s = br.readLine()) != null) {
	    		for(int i = 0; i < pmt.size(); i++) {
	    			if(pmt.get(i).equals(s)) {
	    				System.out.println("The word " + s + " is valid");
	    			}
	            }
	    		//System.out.println(s);
	    	}
	    	
    	}catch(Exception e)
		{
			System.out.println(e);
		}
    }
	
}
