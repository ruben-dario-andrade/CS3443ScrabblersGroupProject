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
		int p = 'A';
		System.out.println(p);
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
		System.out.println("list size is: " + list.size());
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i));
		}
		return list;
	}
	
	static void printPermutation(String str, String ans) 
    { 
  
		//System.out.println("ran prmttn func");
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
	    				int count = points(s);
	    				System.out.println("This word has " + count + " points");
	    			}
	            }
	    		//System.out.println(s);
	    	}
	    	
    	}catch(Exception e)
		{
			System.out.println(e);
		}
    }
    //10 q,z
    //8 j,x
    //5 k
    //4 f,h,v,w,y
    //3 b,c,m,p
    //2 d, g
    // 1 a,e,i,l,n,o,r,s,t,u
    public static int points( String s ) {
    	
    	int points = 0;
    	
    	for(int i = 0; i < s.length(); i++) {
    		
    		if(s.charAt(i) == 'Q' || s.charAt(i) == 'Z' ) 
    			points += 10;
    		else if(s.charAt(i) == 'J' || s.charAt(i) == 'X' ) 
    			points += 8;
    		else if(s.charAt(i) == 'K') 
    			points += 5;
    		else if(s.charAt(i) == 'F' || s.charAt(i) == 'H' || s.charAt(i) == 'V' || s.charAt(i) == 'W' || s.charAt(i) == 'Y') 
    			points += 4;
    		else if(s.charAt(i) == 'B' || s.charAt(i) == 'C' || s.charAt(i) == 'M' || s.charAt(i) == 'P') 
    			points += 3;
    		else if(s.charAt(i) == 'D' || s.charAt(i) == 'G' ) 
    			points += 2;
    		else
    			points += 1;
    		    		
    	}
    	
    	return points;
    	
    }
	
}
