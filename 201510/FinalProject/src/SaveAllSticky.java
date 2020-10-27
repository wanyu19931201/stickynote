import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SaveAllSticky {
	private int count;

	

	public void readcount() {
		String fileName = "count.txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			count = Integer.parseInt(line);
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
