import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.text.DefaultCaret;

import java.text.DateFormat;
import java.text.ParseException;

import java.io.*;

public class stickyManager extends JFrame {
	// private Sticky stick;
	private int colored;
	private int fonted;
	private int sized;
	
	private int score;
	private int warn = 0;
	
	private int font[] = { Font.PLAIN, Font.BOLD, Font.ITALIC };
	private Color color[] = { Color.WHITE, Color.RED, Color.YELLOW };
	private String size[] = { "16", "18", "20", "22", "24", "26", "28" };
	final JFileChooser fc = new JFileChooser();
	private int visible = 1;
	private int count;

	private JPanel timePanel; 
	private JPanel thePanel;
	private String lines;
	private String time;
	private String date;
	private String dateLines;
	private String titleLines;
	private String timeLines;
	JTextArea textArea;
	private JTextField titleField;
	private JTextField timeField;
	private JTextField dateField;
	private JLabel titleLabel;
	//
	private JLabel colorLabel; 
	private JLabel fontLabel;
	private JComboBox colorBox;
	private JComboBox fontBox;
	private JLabel sizeLabel;
	private JComboBox sizeBox;
	
	private JButton saveSticky;
	private JButton exmi;

	JCheckBox checkBox;
	Container outlook = null;

	public stickyManager(int itemNumber, Sticky stick) {
		super("新增便利貼");
		creatView();

		score = stick.getScore();

		
		JMenuBar menuBar;
		

		menuBar = new JMenuBar();
	
		setJMenuBar(menuBar);

		
		readcount();
		

		saveSticky.addActionListener(new ActionListener() {
		

			public void actionPerformed(ActionEvent ae) {
				write(itemNumber);
				stick.settitle(titleLines);
				stick.getTime().setText(dateLines);
				count++;
				writecount();
				
				MainIndex x = new MainIndex();
				x.setVisible(true);
				stickyManager.this.dispose();
			}
		});

		
		exmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exit) {
				MainIndex x = new MainIndex(); // ���������ɧ�D�������^��
				x.setVisible(true);
				stickyManager.this.dispose();
			}
		});


		this.addWindowListener(new WindowAdapter() {
			public void actionPerformed(ActionEvent exit) {
				stickyManager.this.dispose();
			}
		});

		colorBox.addItemListener(new ItemListener() // anonymous inner class
		{
			// handle JComboBox event
			public void itemStateChanged(ItemEvent event) {
				// determine whether checkbox selected
				if (event.getStateChange() == ItemEvent.SELECTED) {
					try {
						textArea.setBackground(color[colorBox.getSelectedIndex()]);
						colored = colorBox.getSelectedIndex();
					} catch (IndexOutOfBoundsException iobe) {
						System.err.println(iobe);
					} // end try-catch
				} // end event
			} // end method itemStateChanged
		} // end anonymous inner class
		); // end call to addItemListener

		fontBox.addItemListener(new ItemListener() // anonymous inner class
		{
			// handle JComboBox event
			public void itemStateChanged(ItemEvent event) {
				// determine whether checkbox selected
				if (event.getStateChange() == ItemEvent.SELECTED) {
					textArea.setFont(new Font("Lucida Grande", font[fontBox.getSelectedIndex()],
							Integer.valueOf(size[sizeBox.getSelectedIndex()])));
					fonted = fontBox.getSelectedIndex();
				} // end event
			} // end method itemStateChanged
		} // end anonymous inner class
		); // end call to addItemListener

		sizeBox.addItemListener(new ItemListener() // anonymous inner class
		{
			// handle JComboBox event
			public void itemStateChanged(ItemEvent event) {
				// determine whether checkbox selected
				if (event.getStateChange() == ItemEvent.SELECTED) {
					textArea.setFont(new Font("Lucida Grande", font[fontBox.getSelectedIndex()],
							Integer.valueOf(size[sizeBox.getSelectedIndex()])));
					sized = colorBox.getSelectedIndex();
				} // end event
			} // end method itemStateChanged
		} // end anonymous inner class
		); // end call to addItemListener

		this.setSize(600, 500);
		this.setVisible(true);
	}

	public void writecount() {
		String fileName = "count.txt";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write(count + "");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
	private void creatView() {
		outlook = this.getContentPane();
		outlook.setLayout(new BorderLayout());

		titleField = new JTextField();
		titleLabel = new JLabel("主題");

		thePanel = new JPanel();
		thePanel.setLayout(new GridLayout(2, 1));
		JPanel NorthPanel = new JPanel();
		NorthPanel.setLayout(new GridLayout(1, 6));
		JPanel SonthPanel = new JPanel();
		SonthPanel.setLayout(new GridLayout(1, 6));
		outlook.add(thePanel, BorderLayout.NORTH);
		thePanel.add(NorthPanel);
		thePanel.add(SonthPanel);
		JLabel label1 = new JLabel();
		JLabel label2 = new JLabel();
		JLabel label3 = new JLabel();
		JLabel label4 = new JLabel();
		
		NorthPanel.add(titleLabel);
		NorthPanel.add(titleField);
		NorthPanel.add(label1);
		NorthPanel.add(label2);
		NorthPanel.add(label3);
		NorthPanel.add(label4);

		colorLabel = new JLabel("The Color");
		SonthPanel.add(colorLabel);

		String colorName[] = { "White", "Red", "Yellow" };

		colorBox = new JComboBox(colorName);
		SonthPanel.add(colorBox);

		String fontName[] = { "PLAIN", "BOLD", "ITALIC" };

		fontLabel = new JLabel("Font");
		SonthPanel.add(fontLabel);

		fontBox = new JComboBox(fontName);
		SonthPanel.add(fontBox);

		sizeLabel = new JLabel("Size");
		SonthPanel.add(sizeLabel);

		sizeBox = new JComboBox(size);
		SonthPanel.add(sizeBox);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		textArea = new JTextArea();

		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		textArea.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(textArea);
		outlook.add(scroll, BorderLayout.CENTER);

		timePanel = new JPanel();
		timePanel.setLayout(new GridLayout(2, 4));
		outlook.add(timePanel, BorderLayout.SOUTH);
		int year = new Date().getYear() + 1900;
		int month = new Date().getMonth() + 1;
		int day = new Date().getDate();
		JLabel dateLabel = new JLabel("日期");
		dateField = new JTextField(year + "/" + month + "/" + day);
		JLabel timeLabel = new JLabel("時間");
		timeField = new JTextField("8:00");   
		JLabel warnLabel = new JLabel();
		checkBox = new JCheckBox("提醒");
		
		saveSticky = new JButton("儲存");
		exmi = new JButton("放棄"); // ���}
		
		timePanel.add(dateLabel);
		timePanel.add(dateField);
		timePanel.add(warnLabel);
		timePanel.add(checkBox);
		timePanel.add(timeLabel);
		timePanel.add(timeField);
		timePanel.add(saveSticky);
		timePanel.add(exmi);
	}

	public String getString() {
		return lines;
	}

	public String getTime() {
		if (checkBox.isSelected())
			return time;
		else
			return null;
	}

	public String getDate() {
		if (checkBox.isSelected()) {
			StringTokenizer st = new StringTokenizer(date, "/");
			if (st.countTokens() == 3) {
				return date;
			} else {
				System.out.println("格式錯誤 請重新輸入");
				return "fail";
			}
		} else {
			return null;
		}
	}

	public void stringToDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		Date dt = null;
		try {
			dt = sdf.parse(date);
			//System.out.println(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




	public void write(int number) {



		try {
			String fileName = "test" + number + ".txt";
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write(visible + "\n");
			if (checkBox.isSelected())
				warn = 1;
			bw.write(warn + "\n");
			System.out.println(warn);
			
			
			dateLines = dateField.getText();
			bw.write(dateLines + "\n");
			timeLines = timeField.getText();
			bw.write(timeLines + "\n");
			bw.write(colored + "\n");
			bw.write(fonted + "\n");
			bw.write(sized + "\n");
			titleLines = titleField.getText();
			bw.write(titleLines + "\n");
			// bw.write(score + "\n");
			String lines = textArea.getText();
			bw.write(lines);
			bw.newLine();
			// bw.write("endOfFlower");
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void read(int number) {
		String fileName = "test" + number + ".txt";
		try {
			String[] tempArray = new String[8];
			String line;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			line = br.readLine();
			tempArray = line.split("\t");
			for (int i = 0; i < tempArray.length; i++) {
				textArea.append(tempArray[i] + "\n");
			}
			line = br.readLine();
			textArea.setText(line);
			while ((line = br.readLine()) != null) {
				textArea.append("\n" + line);
			}
			titleField.setText(tempArray[7]);
			br.close();
		} catch (IOException ioe) {
		}

	}
}