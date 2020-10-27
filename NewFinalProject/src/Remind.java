import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Remind extends TimerTask {
	private Sticky []sticky;
	private String text;
	private Date time;
	private int[] stickyID;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int min;
	private int count;
	private LoadData load;
	
	public void run() {
		// TODO Auto-generated method stub
		
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
		
		stickyID = new int[50];
		int x = 0;
		for (int i = 0; i <= 48; i += 2)
			stickyID[i] = x++;
		x = 25;
		for (int i = 1; i <= 49; i += 2)
			stickyID[i] = x++;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dt=new Date();
		
		Calendar c=Calendar.getInstance();
		
		int[] remindtime={c.get(Calendar.YEAR),
		                 c.get(Calendar.MONTH),
		                 c.get(Calendar.DAY_OF_MONTH),
		                 c.get(Calendar.HOUR_OF_DAY),
		                 c.get(Calendar.MINUTE),
		                 c.get(Calendar.SECOND)
		                 };
	
		//透過SimpleDateFormat的format方法將Date轉為字串
	
		String dts=sdf.format(dt);

		for(int i=1;i<count;i++){
			load = new LoadData(stickyID[i]);
			text = load.getTitle();
			if(Integer.parseInt(load.getCheck()) == 1){
				transDate(load.getDate());
				transTime(load.getTime());
			}
			else
				noCheck();
			
			if(hour == 0){
				if((remindtime[0] == year)&&(remindtime[1]+1 == month)&&(remindtime[2] == day-1)&&(remindtime[3] == 23)&&(remindtime[4] == min)){
					out(60 ,stickyID[i]);
				}
			}
			else{
				if((remindtime[0] == year)&&(remindtime[1]+1 == month)&&(remindtime[2] == day)&&(remindtime[3] == hour-1)&&(remindtime[4] == min)){
					out(60 ,stickyID[i]);
				}
			}
			
			if(min<30){
				if(hour == 0){
					if((remindtime[0] == year)&&(remindtime[1]+1 == month)&&(remindtime[2] == day-1)&&(remindtime[3] == 23)&&(remindtime[4] == 30+min)){
						out(30 ,stickyID[i]);
					}
				}
				else{
					if((remindtime[0] == year)&&(remindtime[1]+1 == month)&&(remindtime[2] == day-1)&&(remindtime[3] == hour-1)&&(remindtime[4] == 30+min)){
						out(30 ,stickyID[i]);
					}
				}
			}
			else{
				if((remindtime[0] == year)&&(remindtime[1]+1 == month)&&(remindtime[2] == day)&&(remindtime[3] == hour)&&(remindtime[4] == min-30)){
					out(30 ,stickyID[i]);
				}
			}
			
			if(min<10){
				if(hour == 0){
					if((remindtime[0] == year)&&(remindtime[1]+1 == month)&&(remindtime[2] == day-1)&&(remindtime[3] == 23)&&(remindtime[4] == 50+min)){
						out(10 ,stickyID[i]);
					}
				}
				else{
					if((remindtime[0] == year)&&(remindtime[1]+1 == month)&&(remindtime[2] == day-1)&&(remindtime[3] == hour-1)&&(remindtime[4] == 50+min)){
						out(10 ,stickyID[i]);
					}
				}
			}
			else{
				if((remindtime[0] == year)&&(remindtime[1]+1 == month)&&(remindtime[2] == day)&&(remindtime[3] == hour)&&(remindtime[4] == min-10)){
					out(10 ,stickyID[i]);
				}
			}
			
			if((remindtime[0] == year)&&(remindtime[1]+1 == month)&&(remindtime[2] == day)&&(remindtime[3] == hour)&&(remindtime[4] == min))
				out(0 ,stickyID[i]);
		}
	}
	
	public void transDate(String d){
		String[] token = d.split("/");
		
		year = Integer.parseInt(token[0]);
		month = Integer.parseInt(token[1]);
		day = Integer.parseInt(token[2]);
	}
	public void transTime(String t){
		String[] token = t.split(":");
		
		hour = Integer.parseInt(token[0]);
		min = Integer.parseInt(token[1]);
	}
	public void noCheck(){
		year = 0;
		month = 0;
		day = 0;
		hour = 0;
		min = 0;
	}
	public void out(int time ,int number){
		JPanel panel = new JPanel();
		
		String filename = "Carry.mp3";
		MP3Test mp3 = new MP3Test(filename);
		
		if(time == 60)
			JOptionPane.showMessageDialog(panel, text + "的時間快到了!還有一個小時!");
		else if(time == 30)
			JOptionPane.showMessageDialog(panel, text + "的時間快到了!還有半小時!");
		else if(time == 10)
			JOptionPane.showMessageDialog(panel, text + "的時間快到了!還有10分鐘!");
		else{
			mp3.play();
			write(number);
			JOptionPane.showMessageDialog(panel, text + "的時間到了!!!");
			mp3.close();
		}
	}

	public void write(int number) {



		try {
			
			load = new LoadData(number);
			
			String visible = load.getVisible();
			int warn = 0;
			String dateLines = load.getDate();
			String timeLines = load.getTime();
			String colored = load.getcolor();
			String fonted = load.getfont();
			String sized = load.getSize();
			String titleLines = load.getTitle();
			String lines = load.getMainPage();
			
			System.out.println("now:" + number);
			
			String fileName = "test" + number + ".txt";
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write(visible + "\n");
			bw.write(warn + "\n");
			bw.write(dateLines + "\n");
			bw.write(timeLines + "\n");
			bw.write(colored + "\n");
			bw.write(fonted + "\n");
			bw.write(sized + "\n");
			bw.write(titleLines + "\n");
			// bw.write(score + "\n");
			bw.write(lines);
			bw.newLine();
			// bw.write("endOfFlower");
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
