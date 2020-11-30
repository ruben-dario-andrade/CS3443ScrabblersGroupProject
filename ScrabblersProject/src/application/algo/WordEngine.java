
package application.algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import application.engine.GameEngine;

public class WordEngine {
	private char[] focusLetter = new char[15];
	private short focusSize = 0;
	private ArrayList<String> list = new ArrayList<String>();
	BufferedReader reader;
	private StringBuilder output = new StringBuilder();
    private String inputstring;
    private static ArrayList<String> cmb = new ArrayList<String>();


	public void addList(LinkedList<String> focus) {
		for (int i = 0; i < 15; i++) {
			focusLetter[i] = ' ';
		}
		for (int i = 0; i < focus.size(); i++) {
			focusLetter[i] = focus.get(i).charAt(0);
		}
		focusSize = (short) focus.size();		
	}
	
	public void clearLists() {
		list.clear();
    	cmb.clear();
	}
	
	public WordEngine() {
		
		addList(GameEngine.getHand());
		String str = new String(focusLetter);
		String s = str.substring(0,focusSize);
		
		inputstring = s;

	}
	
	public void addString (String s) {
		list.clear();
		inputstring = inputstring + s;
	}
	
	public void onlyString (String s) {
		list.clear();
		inputstring = s;
	}

	public void createExclusiveList() {
		try {
			list.clear();
			BufferedReader reader = new BufferedReader(new FileReader("res/ScrabbleDict.txt"));
			String line = reader.readLine();
			line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				int ctr = 0;
				
				for(int i = 0; i < cmb.size(); i++) { 
					boolean matchesCriteria = true;
					int limit = cmb.get(i).length();
					
					for (int j = 0; j < cmb.get(i).length(); j++) {
						if (line.indexOf(cmb.get(i).charAt(j)) == -1) 
							matchesCriteria = false;		
					}
					for (int j = 0; j < cmb.get(i).length(); j++) {
						for (int y = 0; y < cmb.get(i).length(); y++) {
							if(y != j) {
								if (cmb.get(i).charAt(j) == cmb.get(i).charAt(y))
									limit--;
							}
								
						}
					}
					if (line.length() > limit) 
						matchesCriteria = false;
					
					if (matchesCriteria) {
						System.out.println("The word " + line + " is valid");
	    				int count = points(line);
	    				System.out.println("This word has " + count + " points");
	    				if(ctr == 0) {
	    					list.add(line);
	    					ctr++;
	    				}else {
	    					if(list.get(list.size() - 1) != line)
	    						list.add(line);
	    				}
					}
				}
				
				
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			System.out.println("file not found");
		}
	}

    
    public void combine() { combine( 0 ); }
    private void combine(int start ){
        for( int i = start; i < inputstring.length(); ++i ){
            output.append( inputstring.charAt(i) );   
            String str = output.toString();
            System.out.println("Combination: " + str );
            cmb.add(str);
            if ( i < inputstring.length() )
            	combine( i + 1);
            
            output.setLength( output.length() - 1 );
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
	
	
	public LinkedList<String> getCurrentList() {
		LinkedList<String> reccomend = new LinkedList<String>();
		for (int i = 0; i < list.size(); i++) {
			reccomend.add(list.get(i));	
		}
		return reccomend;
	}
	
}