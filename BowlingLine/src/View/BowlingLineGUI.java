// Arlo Davidson and Ryan Compton

package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;

import Model.BowlingLine;

@SuppressWarnings("serial")
public class BowlingLineGUI extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame aWindow = new BowlingLineGUI();
		aWindow.setVisible(true);
	}

	JButton rollB = new JButton("Roll");
	JButton newgameB = new JButton("New Game");
	DefaultListModel model = new DefaultListModel();
	JList pins = new JList(model);
	JTextArea display = new JTextArea();
	BowlingLine game = new BowlingLine();

	public BowlingLineGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Bowling Line");
		setSize(900, 300);
		setLocation(100, 50);

		setLayout(null);

		display.setSize(725, 75);
		display.setLocation(100, 30);
		display.setBackground(Color.CYAN);
		display.setText(game.toString());
		display.setFont(new Font("Courier", Font.BOLD, 16));
		add(display);

		rollB.setSize(100, 25);
		rollB.setLocation(100, 120);
		add(rollB);

		newgameB.setSize(100, 25);
		newgameB.setLocation(235, 120);
		add(newgameB);

		for (int i = 0; i < 11; i++) {
			model.addElement(i);
		}
		pins.updateUI();
		pins.setSize(30, 200);
		pins.setLocation(50, 30);
		add(pins);

		Rolllistener rl = new Rolllistener();
		rollB.addActionListener(rl);

		Newgamelistener ng = new Newgamelistener();
		newgameB.addActionListener(ng);

	}

	private class Rolllistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (game.pinsLeftToDown() != 0) {
				game.pinsDowned((Integer) pins.getSelectedValue());
				model.clear();
				for (int i = 0; i <= game.pinsLeftToDown(); i++) {
					model.addElement(i);
				}
				pins.updateUI();
				display.setText(game.toString());
				pins.setSelectedIndex(model.size()-1);
			} else {
				model.clear();
				pins.updateUI();
			}
		}

	}

	private class Newgamelistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			game = new BowlingLine();
			model.clear();
			for (int i = 0; i <= game.pinsLeftToDown(); i++) {
				model.addElement(i);
			}
			pins.updateUI();
			display.setText(game.toString());
		}

	}
}
