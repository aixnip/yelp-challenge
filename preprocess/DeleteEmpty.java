import java.io.*;
import java.util.*;
 
public class DeleteEmpty{
	public static void main(String[] args) throws IOException, FileNotFoundException{
		
		
		//read categories into a list
        Scanner scat = new Scanner(new File("selected.txt"));
		ArrayList<String> cat = new ArrayList<String>();
		
		while(scat.hasNext()){
			cat.add(scat.nextLine());
		}
 	   
	   	scat.close();
		
		//loop through the files in categories folder;
		for(String category : cat){
			int filep = 1;
			File f = new File("../extractdata/" + category + "/" + filep);
			while(f.exists()){
				if(f.length() == 0){
					System.out.println("deleted: " + "../extractdata/" + category + "/" + filep);
					f.delete();
				}
				filep++;
				f = new File("../extractdata/" + category + "/" + filep);
			}
		}
		
		
	}
	

	
	
	
}