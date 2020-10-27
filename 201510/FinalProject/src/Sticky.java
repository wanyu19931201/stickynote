import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JLabel;

public class Sticky extends JPanel {
	
	
	private Color color[] = { Color.WHITE, Color.RED, Color.YELLOW };
	JMenuItem[] item;
	String[] tempArray;
	private JPanel contentPane;
	private JTextArea information;
	private JTextArea time;
	private JPanel panel;
	private JMenuBar menuBar;
	// private Time t;
	private String date;
	private String t;
	private int score;
	private int ID;
	private String title;
	private int count;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

	private Color col;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	private String detail;
	private int[] stickyID;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public JMenuItem[] getItem() {
		return item;
	}

	public void setItem(JMenuItem[] item) {
		this.item = item;
	}

	public void settitle(String title) {
		this.information.setText(title);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTextArea getInformation() {
		return information;
	}

	public void setInformation(JTextArea information) {
		this.information = information;
	}

	public JTextArea getTime() {
		return time;
	}

	public void setTime(JTextArea time) {
		this.time = time;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public int getScore() {
		return score;
	}

	public void setScore(String score) {
		int x = 0;
		x=Integer.parseInt(score);
		if(x == 0)//白
			this.score = 1;
		else if(x == 1)//洪
			this.score = 10;
		if(x == 2)//黃
			this.score = 5;
	}

	public Sticky() {
		stickyID = new int[50];
		int x = 0;
		for (int i = 0; i <= 48; i += 2)
			stickyID[i] = x++;
		x = 25;
		for (int i = 1; i <= 49; i += 2)
			stickyID[i] = x++;
		panel = new JPanel();
		panel.setBackground(Color.BLUE);
		time = new JTextArea("");
		item = new JMenuItem[7];

		item[0] = new JMenuItem("新增便利貼");
		item[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}

		});
		item[1] = new JMenuItem("修改便利貼");
		item[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							
							Modify modify = new Modify(ID);
							modify.setVisible(true);
							
							//saveSticky(getID());
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

		});
		item[2] = new JMenuItem("顯示內容");
		item[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//System.out.println(getDetail());
					String fileName = "tempTextArea.txt";
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
					bw.write(getDetail() + "\n");

					bw.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}

		});
		item[3] = new JMenuItem("刪除便利貼");
		item[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readLast();
				saveSticky(getID());

			}

		});
		item[4] = new JMenuItem("完成事件");
		item[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(getScore());
			
			}
		});
		item[5] = new JMenuItem("顯示/關閉");
		item[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(getTime().getText());
			}
		});
		item[6] = new JMenuItem("勳章列表");
		item[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Medal labelFrame = new Medal(); // create LabelFrame
				labelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				labelFrame.setSize(350, 930);
				labelFrame.setResizable(false);
				labelFrame.setVisible(true);
			}
		});
		information = new JTextArea();
		information.setEditable(false);
		time.setFont(new Font("Monospaced", Font.PLAIN, 15));
		information.setFont(new Font("Monospaced", Font.PLAIN, 20));
		information.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 146, 300);
		// setContentPane(contentPane);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 101, 21);
		// panel.add(menuBar);
		panel.add(information);
		information.setBounds(30, 10, 146, 200);
		JMenu menu = new JMenu("功能");
		menuBar.add(menu);
		for (int i = 0; i <= 6; i++)
			menu.add(item[i]);
		// panel.setVisible(true);

	}

	public void saveSticky(int id) {
		try {
			String fileName = "test" + id + ".txt";
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write(tempArray[0] + "\n");
			bw.write(tempArray[1] + "\n");
			// dateLines = dateField.getText();
			bw.write(tempArray[2] + "\n");
			// timeLines = timeField.getText();
			bw.write(tempArray[3] + "\n");
			bw.write(tempArray[4] + "\n");
			bw.write(tempArray[5] + "\n");
			bw.write(tempArray[6] + "\n");
			// titleLines = titleField.getText();
			bw.write(tempArray[7] + "\n");
			// bw.write(score + "\n");
			// String lines = textArea.getText();
			bw.write(detail);
			bw.newLine();
			// bw.write("endOfFlower");
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void readSticky( int id ) { // 讀他自己本身
		String fileName = "count.txt";
		BufferedReader br;
		count = 0;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			count = Integer.parseInt(line);
			
			fileName = "test" + id + ".txt";
			br = new BufferedReader(new FileReader(fileName));

			tempArray = new String[9];
			for (int i = 0; i < tempArray.length - 1; i++) {
				tempArray[i] = br.readLine();
				// System.out.println(tempArray[i]);
			}
			
			line = br.readLine();
			detail = line;
			// System.out.println("ggg" + mainPage);
			while ((line = br.readLine()) != null) { // 一條一條去讀
				detail = detail + "\n" + line;
			}

			/*
			 * visible = tempArray[0]; check = tempArray[1];
			 */
			date = tempArray[2];
			t = tempArray[3];
			col = color[Integer.parseInt(tempArray[4])];
			/*
			 * font = tempArray[5]; size = tempArray[6];
			 */
			title = tempArray[7];
			// score = tempArray[8];

			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		time.setText(date + " " + t);
		information.setText(title);
		time.setBackground(getCol());
		information.setBackground(getCol());
		//System.out.println(count);
		/*
		 * setVisible(false); MainIndex x = new MainIndex(); x.setVisible(true);
		 */
	}
	
	public void readLast() {
		String fileName = "count.txt";
		BufferedReader br;
		count = 0;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			count = Integer.parseInt(line);

			fileName = "test" + stickyID[count - 1] + ".txt";
			br = new BufferedReader(new FileReader(fileName));

			tempArray = new String[9];
			for (int i = 0; i < tempArray.length - 1; i++) {
				tempArray[i] = br.readLine();
				// System.out.println(tempArray[i]);
			}
			line = br.readLine();
			detail = line;
			// System.out.println("ggg" + mainPage);
			while ((line = br.readLine()) != null) {
				detail = detail + "\n" + line;
			}

			/*
			 * visible = tempArray[0]; check = tempArray[1];
			 */
			date = tempArray[2];
			t = tempArray[3];
			col = color[Integer.parseInt(tempArray[4])];
			/*
			 * font = tempArray[5]; size = tempArray[6];
			 */
			title = tempArray[7];
			// score = tempArray[8];

			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		time.setText(date + " " + t);
		information.setText(title);
		time.setBackground(getCol());
		information.setBackground(getCol());
		count--;
		//System.out.println(count);
		writecount();
		/*
		 * setVisible(false); MainIndex x = new MainIndex(); x.setVisible(true);
		 */
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

	private class Modify extends JFrame {

		private JPanel modifyPane; // 基底Pane
		
		private JLabel colorLabel; // 顏色
		private JLabel fontLabel; // 字型
		private JLabel sizeLabel; // 大小
		private JLabel titleLabel; // 主題
		private JLabel dateLabel; // 顯示日期
		private JLabel timeLabel; // 表示時間
		
		private JTextField titleField; // 裝主題的textField
		private JTextField dateField; // 裝日期的textField
		private JTextField timeField; // 裝時間的textField
		private JTextArea textArea; // 寫內容的用的textArea
		
		private JComboBox colorBox; // 顏色選單
		private JComboBox fontBox; // 字型選單
		private JComboBox sizeBox; // 大小選單
		
		private int colorIndex;
		private int fontIndex;
		private int sizeIndex;
		
		private JButton button; // 確定按鈕
		private JButton exmi;
		private Color colorSelected; // 修正後的顏色
		private Font fontSelected; // 修正後的字型
		
		private int visible = 1;
		private int warn = 0;
		private String dateLines; // 表示日期
		private String titleLines; // 表示主題
		private String timeLines; // 表示時間
		private String detail;
		private String tempArray[];
		JCheckBox checkBox; // 提示按鈕

		// 表示顏色
		//Color color[] = { Color.WHITE, Color.CYAN, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };
		//String colorName[] = { "White", "Cyan", "Red", "Green", "Blue", "Yellow" }; //這是之前的版本
		//Color color[] = { Color.WHITE, Color.RED, Color.YELLOW };
		String colorName[] = { "White", "Red", "Yellow" }; // 後來的版本
		
		// 表示字型
		int font[] = { Font.PLAIN, Font.BOLD, Font.ITALIC };
		String fontName[] = { "PLAIN", "BOLD", "ITALIC" };
		
		// 表示大小
		String size[] = { "8", "10", "12", "14", "16", "18", "20" };
		
		public Modify(int itemNumber) {
			super("修改便利貼");
			
			// 設定墊在下面的Pane
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 600, 400);
			modifyPane = new JPanel();
			modifyPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(modifyPane);
			modifyPane.setLayout(new BorderLayout(0, 0));
			
			// 設定上面的Panel
			JPanel northPanel = new JPanel();
			modifyPane.add(northPanel, BorderLayout.NORTH);
			northPanel.setLayout(new GridLayout(2, 1, 0, 0));
			JPanel north1 = new JPanel();
			north1.setLayout(new GridLayout(1, 6));
			JPanel north2 = new JPanel();
			north2.setLayout(new GridLayout(1, 6));
			northPanel.add(north1);
			northPanel.add(north2);
			titleLabel = new JLabel("主題：");
			titleField = new JTextField();
			//titleField.setColumns(10);
			JLabel northBlank1 = new JLabel();
			JLabel northBlank2 = new JLabel();
			JLabel northBlank3 = new JLabel();
			JLabel northBlank4 = new JLabel(); // northBlank 1~4 只是排版用
			colorLabel = new JLabel("背景顏色");
			colorBox = new JComboBox(colorName);
			fontLabel = new JLabel("字體型態");
			fontBox = new JComboBox(fontName);
			sizeLabel = new JLabel("字體大小");
			sizeBox = new JComboBox(size);
			north1.add(titleLabel);
			north1.add(titleField);
			north1.add(northBlank1);
			north1.add(northBlank2);
			north1.add(northBlank3);
			north1.add(northBlank4);
			north2.add(colorLabel);
			north2.add(colorBox);
			north2.add(fontLabel);
			north2.add(fontBox);
			north2.add(sizeLabel);
			north2.add(sizeBox);
			
			// 設定中間的Panel
			textArea = new JTextArea("hello");
			textArea.setLineWrap(true);
			DefaultCaret caret = (DefaultCaret) textArea.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			JScrollPane scroll = new JScrollPane(textArea);
			modifyPane.add(scroll, BorderLayout.CENTER);
			
			// 設定下面的Panel
			JPanel sonthPanel = new JPanel();
			modifyPane.add(sonthPanel, BorderLayout.SOUTH);
			sonthPanel.setLayout(new GridLayout(2, 4, 0, 0)); // <-修改
			//int year = new Date().getYear() + 1900;
			//int month = new Date().getMonth() + 1;
			//int day = new Date().getDate();
			dateLabel = new JLabel("日期");
			dateField = new JTextField();
			JLabel warn = new JLabel("使用提醒");   //修改
			checkBox = new JCheckBox();
			timeLabel = new JLabel("時間");
			timeField = new JTextField();
			JLabel sonthBlank = new JLabel();
			button = new JButton("確定");
			exmi = new JButton("取消");
			sonthPanel.add(dateLabel);
			sonthPanel.add(dateField);
			sonthPanel.add(warn);        // <-新增
			sonthPanel.add(checkBox);
			sonthPanel.add(timeLabel);
			sonthPanel.add(timeField);
			sonthPanel.add(button);
			sonthPanel.add(exmi);        // <-新增
			
			readSticky(itemNumber); // 讀擋

			colorBox.addItemListener(new ItemListener() { // 選背景的選單事件
				public void itemStateChanged(ItemEvent event) {
					if (event.getStateChange() == ItemEvent.SELECTED) {
						try {
							textArea.setBackground(color[colorBox.getSelectedIndex()]);
							colorIndex = colorBox.getSelectedIndex();
						} catch (IndexOutOfBoundsException iobe) {
							System.err.println(iobe);
						}
					}
				}
			}
			);

			fontBox.addItemListener(new ItemListener(){ // 選字體型態的選單事件
				public void itemStateChanged(ItemEvent event) {
					if (event.getStateChange() == ItemEvent.SELECTED) {
						try {
							textArea.setFont(new Font("Lucida Grande", font[fontBox.getSelectedIndex()],
									Integer.valueOf(size[sizeBox.getSelectedIndex()])));
							fontIndex = fontBox.getSelectedIndex();
						} catch (IndexOutOfBoundsException iobe) {
							System.err.println(iobe);
						}
					}
				}
			}
			);

			sizeBox.addItemListener(new ItemListener(){ // 選字體大小的選單事件
				public void itemStateChanged(ItemEvent event) {
					if (event.getStateChange() == ItemEvent.SELECTED) {
						try {
							textArea.setFont(new Font("Lucida Grande", font[fontBox.getSelectedIndex()],
									Integer.valueOf(size[sizeBox.getSelectedIndex()])));
							sizeIndex = sizeBox.getSelectedIndex();
						} catch (IndexOutOfBoundsException iobe) {
							System.err.println(iobe);
						}
					}
				}
			}
			);
			
			button.addActionListener( new ActionListener(){ // 按鈕事件
				// 按下按鈕可把修正後的主題，內容，時間等
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					write(itemNumber);
					MainIndex x = new MainIndex();
					x.setVisible(true);
					Modify.this.dispose();
					
					// 之前修改的
					// titleLines = titleField.getText();
					// colorSelected = color[colorBox.getSelectedIndex()];
					// fontSelected = new Font("Lucida Grande", font[fontBox.getSelectedIndex()],
					//		Integer.valueOf(size[sizeBox.getSelectedIndex()]));
					
				} // 完成確定事件並關閉視窗
			});
			
			exmi.addActionListener( new ActionListener(){ // 按鈕事件
				// 按下按鈕可把修正後的主題，內容，時間等
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					MainIndex x = new MainIndex();
					x.setVisible(true);
					Modify.this.dispose();
					
				} // 完成確定事件並關閉視窗
			});
			
		} // end Modify
		
		// 寫擋
		public void write(int number) {

			try {
				String fileName = "test" + number + ".txt";
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
				bw.write(visible + "\n");
				if (checkBox.isSelected())
					warn = 1;
				bw.write(warn + "\n");
				dateLines = dateField.getText();
				bw.write(dateLines + "\n");
				timeLines = timeField.getText();
				bw.write(timeLines + "\n");
				bw.write(colorIndex + "\n");
				bw.write(fontIndex + "\n");
				bw.write(sizeIndex + "\n");
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

		public void readSticky(int itemNumber) {
			
			try {
				String fileName = "count.txt";
				BufferedReader br;
				br = new BufferedReader(new FileReader(fileName));
				String line = br.readLine();

				fileName = "test" + itemNumber + ".txt";
				br = new BufferedReader(new FileReader(fileName));

				tempArray = new String[9];
				for (int i = 0; i < tempArray.length - 1; i++) {
					tempArray[i] = br.readLine();
					// System.out.println(tempArray[i]);
				}
				line = br.readLine();
				detail = line;
				// System.out.println("ggg" + mainPage);
				while ((line = br.readLine()) != null) {
					detail = detail + "\n" + line;
				}
				textArea.setText(detail);
				
				/*
				 * visible = tempArray[0]; check = tempArray[1];
				 */
				if (Integer.parseInt(tempArray[1]) == 1) checkBox.setSelected(true);
				
				dateField.setText(tempArray[2]);
				timeField.setText(tempArray[3]);
				
				// 預覽原先在檔案中便利貼
				colorIndex = Integer.parseInt(tempArray[4]);
				fontIndex = Integer.parseInt(tempArray[5]);
				sizeIndex = Integer.parseInt(tempArray[6]);
				colorBox.setSelectedIndex(colorIndex);
				fontBox.setSelectedIndex(fontIndex);
				sizeBox.setSelectedIndex(sizeIndex);
				
				textArea.setBackground(color[colorBox.getSelectedIndex()]);
				textArea.setFont(new Font("Lucida Grande", font[fontBox.getSelectedIndex()],
						Integer.valueOf(size[sizeBox.getSelectedIndex()])));
				
				titleField.setText(tempArray[7]);
				// score = tempArray[8];

				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.err.println("FileNotFoundException");
				System.err.println("找不到檔案" + "test" + itemNumber + ".txt" );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.err.println("IOException");
				System.err.println("找不到檔案" + "test" + itemNumber + ".txt" );
			}
			//time.setText(date + " " + t);
			//information.setText(title);
			//time.setBackground(getCol());
			//information.setBackground(getCol());
			
			//System.out.println(count);
			/*
			 * setVisible(false); MainIndex x = new MainIndex(); x.setVisible(true);
			 */
		}
	}
}
