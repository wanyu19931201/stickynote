import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadData {
	private String visible;
	private String title;
	private String date;
	private String time;
	private String check;
	private String font;
	private String color;
	private String size;
	private String score;
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}

	private String mainPage;
	private String fileName;

	public LoadData(int number) {
		try {
			String[] tempArray = new String[9];
			String line;
			fileName = "test"+number+".txt";
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			for(int i =0 ; i < tempArray.length-1;i++){
				tempArray[i] = br.readLine();
			}
			line = br.readLine();
			mainPage = line;
			
			while ((line = br.readLine()) != null) {
				mainPage = mainPage+"\n"+line;
			}
			visible = tempArray[0];
			check = tempArray[1];
			date = tempArray[2];
			time = tempArray[3];
			color =tempArray[4];
			font =tempArray[5];
			size =tempArray[6];
			title =tempArray[7];
			score = color;
			System.out.println("color " + getScore());
			br.close();
		} catch (IOException ioe) {
		}
	}
	public String getMainPage(){
		return mainPage;
	}
	
	public String getSize(){
		return size;
	}
	
	public String getcolor(){
		return color;
	}
	
	public String getfont(){
		return font;
	}
	
	public String getTime(){
		return time;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getCheck(){
		return check;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getVisible(){
		return visible;
	}
}
