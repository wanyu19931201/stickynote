import java.awt.FlowLayout; // specifies how components are arranged
import javax.swing.JFrame; // provides basic window features
import javax.swing.JLabel; // displays text and images
import javax.swing.SwingConstants; // common constants used with Swing
import javax.swing.Icon; // interface used to manipulate images
import javax.swing.ImageIcon; // loads images
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Medal extends JFrame {
	private JLabel rewardPoints; // 顯示獎勵點數
	private JLabel medal_1; // 獎牌
	private JLabel medal_2;
	private JLabel medal_3;
	private JLabel medal_4;
	private JLabel medal_5;
	public ImageIcon iconMedal_1 = new ImageIcon(getClass().getResource("medal_1.png"));
	public ImageIcon iconMedal_2 = new ImageIcon(getClass().getResource("medal_2.png"));
	public ImageIcon iconMedal_3 = new ImageIcon(getClass().getResource("medal_3.png"));
	public ImageIcon iconMedal_4 = new ImageIcon(getClass().getResource("medal_4.png"));
	public ImageIcon iconMedal_5 = new ImageIcon(getClass().getResource("medal_5.png"));
	private int reward ; // 獎勵點數
	private Font boldFont; // 修改字體大小

	public Medal() {
		// 標題
		super("勳章");
		setLayout(new FlowLayout());
		readTotal();
		// 顯示獎勵點數
		rewardPoints = new JLabel("獎勵點數: " + reward); // 顯示目前獎勵點數
		System.out.println(reward);
		rewardPoints.setFont(new Font("Serif", Font.BOLD, 32)); // 增加字體大小
		add(rewardPoints);
		medal_1 = new JLabel("沒有任何徽章");
		// medal_1
		if (reward < 10) // 未解鎖
		{
			ImageIcon iconMedal_1 = new ImageIcon(getClass().getResource("locked_medal_1.png")); // 顯示未解鎖圖示
			medal_1 = new JLabel("達到10點解鎖！", iconMedal_1, SwingConstants.LEFT); // 顯示未解鎖訊息
		} else // 解鎖
		{
			// ImageIcon iconMedal_1 = new ImageIcon( getClass().getResource(
			// "medal_1.png" ) ); // 解鎖圖示
			medal_1 = new JLabel("已解鎖", iconMedal_1, SwingConstants.LEFT); // 解鎖訊息
		}
		medal_1.setFont(new Font("Serif", Font.BOLD, 18)); // 設置字體大小
		add(medal_1); // add此獎牌

		// medal_2
		if (reward < 35) {
			Icon iconMedal_2 = new ImageIcon(getClass().getResource("locked_medal_2.png"));
			medal_2 = new JLabel("達到35點解鎖！", iconMedal_2, SwingConstants.LEFT);
		} else {
			// Icon iconMedal_2 = new ImageIcon( getClass().getResource(
			// "medal_2.png" ) );
			medal_2 = new JLabel("已解鎖", iconMedal_2, SwingConstants.LEFT);
		}
		medal_2.setFont(new Font("Serif", Font.BOLD, 18));
		add(medal_2);

		// medal_3
		if (reward < 70) {
			Icon iconMedal_3 = new ImageIcon(getClass().getResource("locked_medal_3.png"));
			medal_3 = new JLabel("達到70點解鎖！", iconMedal_3, SwingConstants.LEFT);
		} else {
			Icon iconMedal_3 = new ImageIcon(getClass().getResource("medal_3.png"));
			medal_3 = new JLabel("已解鎖", iconMedal_3, SwingConstants.LEFT);
		}
		medal_3.setFont(new Font("Serif", Font.BOLD, 18));
		add(medal_3);

		// medal_4
		if (reward < 100) {
			Icon iconMedal_4 = new ImageIcon(getClass().getResource("locked_medal_4.png"));
			medal_4 = new JLabel("達到100點解鎖！", iconMedal_4, SwingConstants.LEFT);
		} else {
			Icon iconMedal_4 = new ImageIcon(getClass().getResource("medal_4.png"));
			medal_4 = new JLabel("已解鎖", iconMedal_4, SwingConstants.LEFT);
		}
		medal_4.setFont(new Font("Serif", Font.BOLD, 18));
		add(medal_4);

		// medal_5
		if (reward < 150) {
			Icon iconMedal_5 = new ImageIcon(getClass().getResource("locked_medal_5.png"));
			medal_5 = new JLabel("達到150點解鎖！", iconMedal_5, SwingConstants.LEFT);
		} else {
			Icon iconMedal_5 = new ImageIcon(getClass().getResource("medal_5.png"));
			medal_5 = new JLabel("已解鎖", iconMedal_5, SwingConstants.LEFT);
		}
		medal_5.setFont(new Font("Serif", Font.BOLD, 18));
		add(medal_5);
	} // end constructor

	public void readTotal() {
		String fileName = "total.txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			reward = Integer.parseInt(line);
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ImageIcon medalDisplay() {
		if (reward > 10 && reward <= 35) {
			return iconMedal_1;
		}
		if (reward > 35 && reward <= 70) {
			return iconMedal_2;
		}
		if (reward > 70 && reward <= 100) {
			return iconMedal_3;
		}
		if (reward > 100 && reward <= 150) {
			return iconMedal_4;
		}
		if (reward > 150) {
			return iconMedal_5;
		}
		return null;
	}

	public int getReward() {
		return reward;
	}
} // end class