import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filename = "./src/UDPServer.java";
		
		String line = null;
		
		try {
			FileReader filereader = new FileReader(filename);
			
			BufferedReader bufferedReader = 
					new BufferedReader(filereader);
			
			while((line = bufferedReader.readLine()) != null)
				System.out.println(line);
			
			bufferedReader.close();
		}
		catch(FileNotFoundException ex)	{
			System.out.println("Unable to open file: " + filename);
		}
		catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + filename + "'");                  
        }
	}

}
