import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game {
	int money = 100;
	int counter;
	int bet;
	String[] dealer = new String[3];
	String[] player = new String[3];
	JLabel label_Image1 = new JLabel();
	JLabel label_Image2 = new JLabel();
	JLabel label_Image3 = new JLabel();
	JLabel label_Image4 = new JLabel();
	JLabel label_Image5 = new JLabel();
	JLabel label_Image6 = new JLabel();
	JButton btn_rpcard1 = new JButton("Replace Card 1");
	JButton btn_rpcard2 = new JButton("Replace Card 2");
	JButton btn_rpcard3 = new JButton("Replace Card 3");
	JButton btn_start = new JButton("Start");
	JButton btn_result = new JButton("Result");
	JLabel label_bet = new JLabel("Bet:$");
	JLabel label_info = new JLabel("Please place your bet!");
	JLabel label_money = new JLabel("Amount of money you have: $" + money);
	JTextField txt_inputbet = new JTextField(10);
	ImageIcon back = new ImageIcon("images/card_back.gif");
	ImageIcon Image1 = back;
	ImageIcon Image2 = back;
	ImageIcon Image3 = back;
	ImageIcon Image4 = back;
	ImageIcon Image5 = back;
	ImageIcon Image6 = back;
	String[] deckArray = { "card_11", "card_12", "card_13", "card_14", "card_15", "card_16",
			"card_17", "card_18", "card_19", "card_110", "card_111", "card_112", "card_113",
			"card_21", "card_22", "card_23", "card_24", "card_25", "card_26", "card_27",
			"card_28", "card_29", "card_210", "card_211", "card_212", "card_213", "card_31",
			"card_32", "card_33", "card_34", "card_35", "card_36", "card_37", "card_38",
			"card_39", "card_310", "card_311", "card_312", "card_313", "card_41", "card_42",
			"card_43", "card_44", "card_45", "card_46", "card_47", "card_48", "card_49",
			"card_410", "card_411", "card_412", "card_413" };
	ArrayList<String> deck;

	public void play() {
		label_Image1.setIcon(Image1);
		label_Image2.setIcon(Image2);
		label_Image3.setIcon(Image3);
		label_Image4.setIcon(Image4);
		label_Image5.setIcon(Image5);
		label_Image6.setIcon(Image6);

		JPanel MainPanel = new JPanel();
		JPanel DealerPanel = new JPanel();
		JPanel PlayerPanel = new JPanel();
		JPanel RpCardBtnPanel = new JPanel();
		JPanel ButtonPanel = new JPanel();
		JPanel InfoPanel = new JPanel();

		DealerPanel.add(label_Image1);
		DealerPanel.add(label_Image2);
		DealerPanel.add(label_Image3);
		PlayerPanel.add(label_Image4);
		PlayerPanel.add(label_Image5);
		PlayerPanel.add(label_Image6);

		RpCardBtnPanel.add(btn_rpcard1);
		RpCardBtnPanel.add(btn_rpcard2);
		RpCardBtnPanel.add(btn_rpcard3);

		ButtonPanel.add(label_bet);
		ButtonPanel.add(txt_inputbet);
		ButtonPanel.add(btn_start);
		ButtonPanel.add(btn_result);

		InfoPanel.add(label_info);
		InfoPanel.add(label_money);

		MainPanel.setLayout(new GridLayout(5, 1));
		MainPanel.add(DealerPanel);
		MainPanel.add(PlayerPanel);
		MainPanel.add(RpCardBtnPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.add(InfoPanel);

		DealerPanel.setBackground(Color.green);
		PlayerPanel.setBackground(Color.green);
		RpCardBtnPanel.setBackground(Color.green);

		JMenuBar menuBar = new JMenuBar();
		JMenu control = new JMenu("Control");
		JMenu help = new JMenu("Help");
		JMenuItem quit = new JMenuItem("Quit");
		JMenuItem rule = new JMenuItem("Rules");
		quit.addActionListener(new QuitBtn());
		rule.addActionListener(new HelpBtn());
		control.add(quit);
		help.add(rule);
		menuBar.add(control);
		menuBar.add(help);

		btn_result.setEnabled(false);
		btn_rpcard1.setEnabled(false);
		btn_rpcard2.setEnabled(false);
		btn_rpcard3.setEnabled(false);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(MainPanel);
		frame.setTitle("A Special Card Game");
		frame.setSize(400, 700);
		frame.setVisible(true);

		btn_start.addActionListener(new BtnStart());
		btn_result.addActionListener(new BtnResult());
		btn_rpcard1.addActionListener(new ReplaceOne());
		btn_rpcard2.addActionListener(new ReplaceTwo());
		btn_rpcard3.addActionListener(new ReplaceThree());

	}

	class HelpBtn implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JOptionPane.showMessageDialog(null, "J, Q, K are regarded as special cards.\n"
					+ "Rule 1: The one with more special cards wins.\n"
					+ "Rule 2: If both have the same number of special cards, add the face values of the other\n"
					+ "card(s) and take the remainder after dividing the sum by 10. The one with a bigger\n"
					+ "remainder wins. (Note: Ace = 1).\n"
					+ "Rule 3: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.\n");
		}
	}

	class QuitBtn implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}

	class BtnStart implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String text = txt_inputbet.getText();
			if (text.isEmpty()) {
				JOptionPane.showMessageDialog(null, "WARNING: Please place a bet!");
			} else if (text.indexOf(".") != -1 || Integer.parseInt(text) <= 0) {
				JOptionPane.showMessageDialog(null, "WARNING: The bet you place must be a positive integer!");
			} else if (Integer.parseInt(text) > money) {
				JOptionPane.showMessageDialog(null, "WARNING: The bet you place must be lower than your budget!");
			} else {
				deck = new ArrayList<String>(Arrays.asList(deckArray));
				counter = 2;
				bet = Integer.parseInt(txt_inputbet.getText());
				label_Image1.setIcon(back);
				label_Image2.setIcon(back);
				label_Image3.setIcon(back);
				btn_start.setEnabled(false);
				btn_result.setEnabled(true);
				btn_rpcard1.setEnabled(true);
				btn_rpcard2.setEnabled(true);
				btn_rpcard3.setEnabled(true);
				label_info.setText(("Your current bet is: $" + txt_inputbet.getText()));
				label_money.setText("Amount of money you have: $" + money);

				int randomIndex = (int) (Math.random() * deck.size());
				label_Image4.setIcon(new ImageIcon("images/" + deck.get(randomIndex) + ".gif"));
				player[0] = deck.get(randomIndex);
				deck.remove(randomIndex);

				randomIndex = (int) (Math.random() * deck.size());
				label_Image5.setIcon(new ImageIcon("images/" + deck.get(randomIndex) + ".gif"));
				player[1] = deck.get(randomIndex);
				deck.remove(randomIndex);

				randomIndex = (int) (Math.random() * deck.size());
				label_Image6.setIcon(new ImageIcon("images/" + deck.get(randomIndex) + ".gif"));
				player[2] = deck.get(randomIndex);
				deck.remove(randomIndex);

			}
		}
	}

	class BtnResult implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			btn_rpcard1.setEnabled(false);
			btn_rpcard2.setEnabled(false);
			btn_rpcard3.setEnabled(false);
			btn_start.setEnabled(true);
			
			int randomIndex = (int) (Math.random() * deck.size());
			label_Image1.setIcon(new ImageIcon("images/" + deck.get(randomIndex) + ".gif"));
			dealer[0] = deck.get(randomIndex);
			deck.remove(randomIndex);

			randomIndex = (int) (Math.random() * deck.size());
			label_Image2.setIcon(new ImageIcon("images/" + deck.get(randomIndex) + ".gif"));
			dealer[1] = deck.get(randomIndex);
			deck.remove(randomIndex);

			randomIndex = (int) (Math.random() * deck.size());
			label_Image3.setIcon(new ImageIcon("images/" + deck.get(randomIndex) + ".gif"));
			dealer[2] = deck.get(randomIndex);
			deck.remove(randomIndex);

			if (Winner.winner(dealer, player)){
				JOptionPane.showMessageDialog(null, "Congratulations! You win this round");
				money += bet;
			}else {
				JOptionPane.showMessageDialog(null, "Sorry! The dealer wins this round");
				money -= bet;
			}
			
			label_info.setText("Please place your bet!");
			label_money.setText("Amount of money you have: $" + money);
			txt_inputbet.setText("");;
			
			btn_result.setEnabled(false);
			
			label_Image1.setIcon(back);
			label_Image2.setIcon(back);
			label_Image3.setIcon(back);
			label_Image4.setIcon(back);
			label_Image5.setIcon(back);
			label_Image6.setIcon(back);
			
		}
	}

	class ReplaceOne implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int randomIndex = (int) (Math.random() * deck.size());
			label_Image4.setIcon(new ImageIcon("images/" + deck.get(randomIndex) + ".gif"));
			player[0] = deck.get(randomIndex);
			deck.remove(randomIndex);
			btn_rpcard1.setEnabled(false);
			counter--;
			if (counter == 0) {
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
			}

		}
	}

	class ReplaceTwo implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int randomIndex = (int) (Math.random() * deck.size());
			label_Image5.setIcon(new ImageIcon("images/" + deck.get(randomIndex) + ".gif"));
			player[1] = deck.get(randomIndex);
			deck.remove(randomIndex);
			btn_rpcard2.setEnabled(false);
			counter--;
			if (counter == 0) {
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
			}
		}
	}

	class ReplaceThree implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int randomIndex = (int) (Math.random() * deck.size());
			label_Image6.setIcon(new ImageIcon("images/" + deck.get(randomIndex) + ".gif"));
			player[2] = deck.get(randomIndex);
			deck.remove(randomIndex);
			btn_rpcard3.setEnabled(false);
			counter--;
			if (counter == 0) {
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
			}
		}
	}

}