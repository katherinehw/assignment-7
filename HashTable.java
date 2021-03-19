package herman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;


public class HashTable {//creates hashtable class with fi attribute
	
	File fi;
	int counter = 0;
	
	
	public HashTable(File fi) {
		this.fi = fi;
	}

	public Hashtable<String, Integer> hash() {//creates hashtable
		
	       Hashtable<String, Integer> temp = new Hashtable<String, Integer>();//creates temporary hashtable

	        try{
	            BufferedReader reader = new BufferedReader(new FileReader(fi));
	            Scanner in = new Scanner(fi);

	            while (in.hasNext()){

	                String line = in.nextLine();
	                temp.put(line.trim(), counter);
	                counter++;

	            }


	        }catch (IOException ioException) {
	            ioException.printStackTrace();
	        }

	        return temp;

	    }

	}
