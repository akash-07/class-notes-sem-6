import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = "./src/MyOutput.java";
		
		try {
			System.out.println("I am inside");
			FileWriter fw = new FileWriter(filename);
			
			BufferedWriter bfwr = new BufferedWriter(fw);
			
			bfwr.write("class Write \t{\n");
            bfwr.write(" //the text to the file.\n}");
            
            bfwr.close();
		}
		
		catch(IOException ex) {
			 System.out.println(
		                "Error writing to file '"
		                + filename + "'");
		}
	}

}
