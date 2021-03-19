package herman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LineCounter {

	File fi;
	int count = 0;// needs to be outside of method

	public List<String> javaFile;

	public LineCounter(File fi) {
		this.fi = fi;
	}

	public int search() throws IOException{
		
		javaFile = Files.readAllLines(Path.of(String.valueOf(this.fi)));//reads file
		
		for (int z = 0; z<javaFile.size(); z++) {//each line of java file
			if (javaFile.get(z).trim().length()>0) {//removes blank spaces
				
				for(int y = 0; y< javaFile.get(z).length(); y++){//looks for comments and new lines, ignores them
					
					if(javaFile.get(z).charAt(y) == '/' && javaFile.get(z).charAt(y+1) == '*') {
						break; 
					}
					else if (javaFile.get(z).charAt(y) == '/' && javaFile.get(z).charAt(y + 1) == '/') {
						break;
	                } 
		             else if (javaFile.get(z).charAt(y) == '*' && javaFile.get(z).charAt(y + 1) != '/') {
		
		                boolean check = quickS(javaFile.get(z + 1));
		                if (!check) {
		                    count++;//adds new line if *multiplier not comment
		                }
		                break;
		
		            } else if (javaFile.get(z).charAt(y) == '*' && (javaFile.get(z).charAt(y + 1) == '/')) {
		                break;
		            } else if (y == javaFile.get(z).length() - 1) {
		                count++;
		            }
				}
			}
		}
		
		

		return count;
	}


	private boolean quickS(String s) {
	
		for (int i = 0; i < s.length(); i++) {
		
		    if (s.charAt(i) == '*') {
		        return true;
		    }
		}
		return false;
	}
}
