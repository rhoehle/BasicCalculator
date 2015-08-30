package com.robertleehoehle;

import java.awt.*;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.JMenuBar;

public class Calculator extends JFrame {

	// Fields - Variables
	private static final long serialVersionUID = 2743412299161140668L;

	private JMenuBar menuBar;
	private JMenu file;
	private JMenu edit;
	private JMenu help;
	private JMenuItem close;
	private JMenuItem copy;
	private JMenuItem view;
	private JMenuItem about;

	// JText fields for the calculator's display
	private JTextArea display;

	private JButton clear;
	private JButton equals;
	private JButton zero;
	private JButton decimal;
	private JButton pnbutton;
	private JButton one;
	private JButton two;
	private JButton three;
	private JButton four;
	private JButton five;
	private JButton six;
	private JButton seven;
	private JButton eight;
	private JButton nine;

	// Operations Fields
	private JButton division;
	private JButton multiplication;
	private JButton subtraction;
	private JButton addition;

	// Temporary number for operators
	private double firstTemp = 0.0;

	// Create an array
	private boolean[] operation = new boolean[4];

	public static void main(String[] args) {
		// set the theme to the computer's theme with a try/catch block
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			System.out.println("Cannot load the current system theme.");
		}
		// Create the instance
		new Calculator();

	}

	public Calculator() {
		super("Basic Calculator");
		sendMenuBar();
		sendDisplay();
		sendButtons();
		sendUI(this);
	}

	private void sendMenuBar() {
		menuBar = new JMenuBar(); // Create the menu bar
		file = new JMenu(" File "); // File, Edit, Help headings
		edit = new JMenu(" Edit ");
		help = new JMenu(" Help ");
		close = new JMenuItem("Close"); // Close on the File heading
		copy = new JMenuItem("Copy");
		view = new JMenuItem("View Help");
		about = new JMenuItem("About Basic Calculator");
		setJMenuBar(menuBar); // Set the JMenuBar to menuBar
		menuBar.add(file); // place the file in the menu bar
		menuBar.add(edit);
		menuBar.add(help);

		// make the close button work with an action listener
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		;

		// Action Listeners
		copy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String tempDisplay = display.getText();
				StringSelection display = new StringSelection(tempDisplay);
				Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
				system.setContents(display, display);
			}
		});

		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Basic Calculator is a work in progress used for development purposes.", "Basic Calculator", 1);

			}
		});

		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Basic Calculator was created for educational purposes.",
						"Basic Calculator", 1);

			}
		});

		file.add(close); // place close in the file menu bar item
		edit.add(copy); // copy into the edit menu bar item
		help.add(view); // view into the help menu bar item
		help.add(about); // about into the help menu bar item
	}

	private void sendDisplay() {
		// create and add the display to the application
		display = new JTextArea();
		display.setBounds(10, 10, 425, 60);
		display.setEditable(false);
		// Set the orientation to read like regular calculators
		display.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		display.setText("0");
		display.setFont(new Font("Tahoma", Font.PLAIN, 30)); // Change font to
																// fit
																// calculator
		add(display);
	}

	private void sendButtons() {

		division = new JButton("/");
		division.setBounds(275, 80, 160, 65);
		division.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFirstTemp(Double.parseDouble(display.getText()));
				display.setText("0");
				operation[0] = true;
			}
		});
		add(division);

		multiplication = new JButton("*");
		multiplication.setBounds(275, 155, 160, 65);
		multiplication.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFirstTemp(Double.parseDouble(display.getText()));
				display.setText("0");
				operation[1] = true;
			}

		});
		add(multiplication);

		subtraction = new JButton("-");
		subtraction.setBounds(275, 230, 160, 65);
		subtraction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFirstTemp(Double.parseDouble(display.getText()));
				display.setText("0");
				operation[2] = true;
			}
		});
		add(subtraction);

		addition = new JButton("+");
		addition.setBounds(275, 305, 160, 65);
		addition.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFirstTemp(Double.parseDouble(display.getText()));
				display.setText("0");
				operation[3] = true;
			}
		});
		add(addition);

		clear = new JButton("Clear");
		clear.setBounds(178, 377, 255, 65);
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				display.setText("0");
				setFirstTemp(0.0);
				for (int i = 0; i <= 3; i++) {
					operation[i] = false;
				}
			}

		});
		add(clear);

		equals = new JButton("=");
		equals.setBounds(10, 377, 160, 65);
		equals.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (operation[0])
					display.setText(Double.toString(getFirstTemp() / Double.parseDouble(display.getText())));
				else if (operation[1])
					display.setText(Double.toString(getFirstTemp() * Double.parseDouble(display.getText())));
				else if (operation[2])
					display.setText(Double.toString(getFirstTemp() - Double.parseDouble(display.getText())));
				else if (operation[3])
					display.setText(Double.toString(getFirstTemp() + Double.parseDouble(display.getText())));
				if (display.getText().endsWith(".0"))
					display.setText(display.getText().replace(".0", ""));
				setFirstTemp(0.0);
				for (int i = 0; i <= 3; i++)
					operation[i] = false;
			}

		});
		add(equals);

		zero = new JButton("0");
		zero.setBounds(10, 305, 75, 65);
		zero.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (display.getText().equalsIgnoreCase("0") || display.getText().length() > 24)
					return;
				display.append("0");
			}

		});
		add(zero);

		decimal = new JButton(".");
		decimal.setBounds(94, 305, 75, 65);
		decimal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (display.getText().contains("."))
					return;
				display.append(".");
			}

		});
		add(decimal);

		pnbutton = new JButton("+/-");
		pnbutton.setBounds(178, 305, 75, 65);
		pnbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (display.getText().equalsIgnoreCase("0"))
					return;
				display.setText(Double.toString(Double.parseDouble(display.getText()) * -1));
				if (display.getText().endsWith(".0"))
					display.setText(display.getText().replace(".0", ""));
			}

		});
		add(pnbutton);

		one = new JButton("1");
		one.setBounds(10, 230, 75, 65);
		one.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (display.getText().length() > 24)
					return;
				if (display.getText().equalsIgnoreCase("0")) {
					display.setText("1");
					return;
				}
				display.append("1");
			}

		});
		add(one); // adds to the application

		two = new JButton("2");
		two.setBounds(94, 230, 75, 65);
		two.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (display.getText().length() > 24)
					return;
				if (display.getText().equalsIgnoreCase("0")) {
					display.setText("2");
					return;
				}
				display.append("2");
			}

		});
		add(two);

		three = new JButton("3");
		three.setBounds(178, 230, 75, 65);
		three.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (display.getText().length() > 24)
					return;
				if (display.getText().equalsIgnoreCase("0")) {
					display.setText("3");
					return;
				}
				display.append("3");
			}

		});
		add(three);

		four = new JButton("4");
		four.setBounds(10, 155, 75, 65);
		four.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (display.getText().length() > 24)
					return;
				if (display.getText().equalsIgnoreCase("0")) {
					display.setText("4");
					return;
				}
				display.append("4");
			}

		});
		add(four);

		five = new JButton("5");
		five.setBounds(94, 155, 75, 65);
		five.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (display.getText().length() > 24)
					return;
				if (display.getText().equalsIgnoreCase("0")) {
					display.setText("5");
					return;
				}
				display.append("5");
			}
		});
		add(five);

		six = new JButton("6");
		six.setBounds(178, 155, 75, 65);
		six.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (display.getText().length() > 24)
					return;
				if (display.getText().equalsIgnoreCase("0")) {
					display.setText("6");
					return;
				}
				display.append("6");
			}

		});
		add(six);

		seven = new JButton("7");
		seven.setBounds(10, 80, 75, 65);
		seven.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (display.getText().length() > 24)
					return;
				if (display.getText().equalsIgnoreCase("0")) {
					display.setText("7");
					return;
				}
				display.append("7");

			}
		});
		add(seven);

		eight = new JButton("8");
		eight.setBounds(94, 80, 75, 65);
		eight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (display.getText().length() > 24)
					return;
				if (display.getText().equalsIgnoreCase("0")) {
					display.setText("8");
					return;
				}
				display.append("8");

			}
		});
		add(eight);

		nine = new JButton("9");
		nine.setBounds(178, 80, 75, 65);
		nine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (display.getText().length() > 24)
					return;
				if (display.getText().equalsIgnoreCase("0")) {
					display.setText("9");
					return;
				}
				display.append("9");
			}
		});
		add(nine);

	}

	private void sendUI(Calculator app) {
		// set the basics
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(450, 525); // set the size
		app.setResizable(false);
		app.setLayout(null);
		app.setLocationRelativeTo(null); // Set in center of screen
		app.setVisible(true);

	}

	public double getFirstTemp() {
		return firstTemp;
	}

	public void setFirstTemp(double firstTemp) {
		this.firstTemp = firstTemp;
	}

}
