import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class writeData {
	private int colored ;
	private int score;
	private int fonted ;
	private int warn ;
	private int visible ;
	private String lines;
	private String time;
	private String date;
	private String dateLines;
	private String titleLines;
	private String timeLines;
	private String sized ;
	
	public writeData(int fileNumber , int vi , int wa , String da ,String ti ,int co ,int fo ,int si ,String tl,int sc){
		try {
			String fileName = "test"+fileNumber+".txt";
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write(visible + "\n");
			bw.write(warn + "\n");
			bw.write(dateLines + "\n");
			bw.write(timeLines + "\n");
			bw.write(colored + "\n");
			bw.write(fonted + "\n");
			bw.write(sized + "\n");
			bw.write(titleLines + "\n");
			//bw.write(score + "\n");
			bw.write(lines);
			bw.newLine();
			//bw.write("endOfFlower");
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
