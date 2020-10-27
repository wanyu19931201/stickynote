import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class MainIndex extends JFrame {

	private int total;
	private JPanel contentPane;
	private Sticky[] stick;
	private int[] stickyID;
	private int count = 1;
	private JTextArea textArea;
	private Color color[] = { Color.WHITE, Color.RED, Color.YELLOW };
	private int font[] = { Font.PLAIN, Font.BOLD, Font.ITALIC };
	private String size[] = { "16", "18", "20", "22", "24", "26", "28" };

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	// private JTextArea detail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainIndex frame = new MainIndex();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public void readTotal() {
		String fileName = "total.txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			total = Integer.parseInt(line);
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public MainIndex() {
		readTotal();
		Timer timer = new Timer();
		timer.schedule(new Remind(), 1000, 60000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		stick = new Sticky[10];
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 400, 500);

		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		// scrollPane.setScrollPosition(0,0);
		// panel.scrollTop();
		GridBagConstraints c1 = new GridBagConstraints();
		panel.setLayout(new GridLayout(2, 0, 30, 10));
		panel.setPreferredSize(new Dimension(3500, 300));

		JLabel lblNewLabel = new JLabel();
		
		lblNewLabel.setBounds(459, 10, 203, 23);
		contentPane.add(lblNewLabel);

		Medal labelFrame = new Medal();
		JLabel medal = new JLabel(labelFrame.medalDisplay());
		medal.setBounds(459, 70, 203, 162);

		JTextField lblNewLabel2 = new JTextField("目前獎勵點數:" + total);
		lblNewLabel2.setEditable(false);

		lblNewLabel2.setBounds(470, 10, 203, 162);
		lblNewLabel2.setSize(170, 70);
		lblNewLabel2.setFont(new Font("monospaced", Font.BOLD, 20));
		medal.setBounds(459, 70, 203, 162);
		add(lblNewLabel2);

		getContentPane().add(medal);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(Color.white);
		textArea.setFont(new Font("�з���", Font.BOLD, 22));

		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		textArea.setLineWrap(true);

		textArea.setBounds(459, 275, 168, 162);
		contentPane.add(textArea);
		showtextArea();

		readcount();
		stickyID = new int[50];
		int x = 0;
		for (int i = 0; i <= 48; i += 2)
			stickyID[i] = x++;
		x = 25;
		for (int i = 1; i <= 49; i += 2)
			stickyID[i] = x++;

		stick = new Sticky[50];
		for (int i = 0; i <= 49; i++) {
			stick[i] = new Sticky();
			stick[i].add(stick[i].getMenuBar());
			stick[i].setLayout(new GridLayout(3, 1, 0, 0));

			panel.add(stick[i]);
			stick[i].add(stick[i].getTime());
			stick[i].add(stick[i].getInformation());
			stick[i].getTime().setText("");
			stick[i].getInformation().setText("");
			stick[i].setVisible(false);
			stick[i].item[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					stickyManager test = new stickyManager(stickyID[count], stick[stickyID[count]]);
					setVisible(false);
				}

			});

			stick[i].item[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					//setVisible(false);

				}

			});
			stick[i].item[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}

			});

			stick[i].item[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					readcount();
					stick[stickyID[count - 1]].setVisible(false);
				}

			});
			stick[i].item[4].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}

			});

		}
		stick[0].setVisible(true);
		stick[0].getTime().setText("2016/01/02");
		stick[0].getTime().setBackground(Color.YELLOW);
		stick[0].getInformation().setBackground(Color.YELLOW);
		stick[0].getInformation().setText("交報告");
		read_old_sticky();
	}

	public void showtextArea() {
		String fileName = "tempTextArea.txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			// count = Integer.parseInt(line);
			br.close();
			textArea.setText(line);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void read_old_sticky() {
		LoadData loadSticky;
		for (int i = 1; i < count; i++) {
			loadSticky = new LoadData(stickyID[i]);
			int co = Integer.valueOf(loadSticky.getcolor() + "");
			int si = Integer.valueOf(loadSticky.getSize() + "");
			int fo = Integer.valueOf(loadSticky.getfont() + "");
			stick[stickyID[i]].setID(stickyID[i]);
			stick[stickyID[i]].setTitle(loadSticky.getTitle());
			stick[stickyID[i]].setCol(color[co]);
			stick[stickyID[i]].setDetail(loadSticky.getMainPage());
			stick[stickyID[i]].setScore(loadSticky.getScore());

			stick[stickyID[i]].getTime().setText(loadSticky.getDate() + "\n" + loadSticky.getTime());
			stick[stickyID[i]].getTime().setBackground(color[co]);
			stick[stickyID[i]].getTime().setFont(new Font("Monospaced", font[fo], Integer.valueOf(size[si])));
			stick[stickyID[i]].getInformation().setBackground(color[co]);
			stick[stickyID[i]].getInformation().setText(loadSticky.getTitle());
			stick[stickyID[i]].getInformation().setFont(new Font("Monospaced", font[fo], Integer.valueOf(size[si])));

			stick[stickyID[i]].setVisible(true);
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
}
