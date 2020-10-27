import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DeleteSticky {
	private String visible;
	private String title;
	private String date;
	private String time;
	private String check;
	private String font;
	private String color;
	private String size;
	private String mainPage;
	
	public DeleteSticky(int delnum) {
		
		LoadData load = new LoadData(delnum);
		
		visible = "0";
		title = load.getTitle();
		date = load.getDate();
		time = load.getTime();
		check= load.getCheck();
		font = load.getfont();
		color = load.getcolor();
		size = load.getSize();
		mainPage = load.getMainPage();
		try {
			String fileName = "test"+delnum+".txt";
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write(visible + "\t");
			bw.write(check + "\t");
			bw.write(date + "\t");
			bw.write(time + "\t");
			bw.write(color + "\t");
			bw.write(font + "\t");
			bw.write(size + "\t");
			bw.write(title + "\n");
			bw.write(mainPage);
			bw.newLine();
			//bw.write("endOfFlower");
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
