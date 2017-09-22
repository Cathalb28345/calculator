
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Cathal Brady
 * C00202493
 * @version 2.0
*/
public class Calculator extends JFrame {
	// ================== constants
	private static final Font BIGGER_FONT = new Font("monspaced", Font.PLAIN, 20);
	private static final long serialVersionUID = 1L;

	/* Component referenced during execution */
	/**
	 *  JTextField
	 *            sets the displayField variable for the input
	 */
	private JTextField displayField;
	/**
	 * CalcLogic
	 *            logic = new CalcLogic() creates the array for the logic to be
	 *            called contains all the functionality for the calculator logic
	 *            and the memory functions
	 */
	private CalcLogic logic = new CalcLogic();

	/*
	 * sets the looks and feel of the system and mimics the look of the
	 * operating systems layout for there calculator
	 */
	/*
	 * Calculator window Creates the window for the calculator
	 * window.setDefaultCloseOperation sets that on closing of the calculator
	 * that the window should close
	 * 
	 */
	// method main main driver
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception unused) {
			; /*
				 * Ignore exception because we can't do anything. Will use
				 * default.
				 */
		}

		Calculator window = new Calculator();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	// ==============================================================
	// constructor
	/*
	 * method Calculator builds the calculator
	 */

	public Calculator() {

		// ... Set attributes of the display field
		displayField = new JTextField("", 12);
		displayField.setHorizontalAlignment(JTextField.RIGHT);
		displayField.setFont(BIGGER_FONT);
		displayField.setEditable(false);
		// ... Create and set attributes of clear button
		JButton clearButton = new JButton("Clear");
		clearButton.setFont(BIGGER_FONT);
		clearButton.addActionListener(new ClearListener());

		// ... Use one listener for all numeric keys.
		ActionListener numListener = new NumListener();

		// ... Layout numeric keys in a grid. Generate the buttons in a loop
		// from the chars in a string.
		String buttonOrder = "1234567890. ";
		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new GridLayout(5, 5, 2, 2));/// play with
		// loop to print the buttons
		for (int i = 0; i < buttonOrder.length(); i++) {
			String keyTop = buttonOrder.substring(i, i + 1);
			JButton b = new JButton(keyTop);
			if (keyTop.equals(" ")) {
				// ... Put a dummy button in this position.
				b.setEnabled(false);
			} else {
				// ... Put a digit button in the interface.
				b.addActionListener(numListener);
				b.setFont(BIGGER_FONT);
			}
			buttonPanel.add(b);
		}

		// ... One ActionListener to use for all operator buttons.v */
		ActionListener opListener = new OpListener();

		/*
		 * Create panel with grid layout to hold operator buttons. /* Use array
		 * of button names to create buttons in a loop.
		 */
		JPanel opPanel = new JPanel();

		opPanel.setLayout(new GridLayout(5, 1, 2, 2));

		String[] opOrder = { "+", "-", "*", "/", "x²", "%", "√", "←", "MC", "MR", "MS", "M+", "M-", "=" };

		for (int i = 0; i < opOrder.length; i++) {
			JButton b = new JButton(opOrder[i]);
			b.addActionListener(opListener);
			b.setFont(BIGGER_FONT);
			opPanel.add(b);
		}

		/* Put Clear button in flow layout to keep from expanding. */
		JPanel clearPanel = new JPanel();
		clearPanel.setLayout(new FlowLayout());
		clearPanel.add(clearButton);

		JPanel EqualsPanel = new JPanel();
		clearPanel.setLayout(new FlowLayout());
		clearPanel.add(EqualsPanel);
		/* Layout the top-level content panel. */
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout(6, 6));
		content.add(displayField, BorderLayout.NORTH);
		content.add(buttonPanel, BorderLayout.CENTER);
		content.add(opPanel, BorderLayout.EAST);
		content.add(clearPanel, BorderLayout.SOUTH);

		content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		/* Finish building the window (JFrame) */
		this.setContentPane(content);
		this.pack();
		this.setTitle("C00202493 Calculator");
		this.setResizable(false);
		this.setLocationRelativeTo(null);

	}

	/* end constructor */
	/**
	 * Clears the screen and sets it to "" startNumber to null to be changed by
	 * the displayField displayField sets the starts number to blank to accept
	 * the new digit
	 *
	 */
	private void actionClear() {

		displayField.setText("");

	}

	/////////////////////// inner listener class
	/////////////////////// OpListener/////////////////////////////////
	/*
	 * OpListener Listener for all op buttons.
	 * 
	 **/
	/*
	 * Clears the screen and sets it to "" Sign pulls the relevant operator from
	 * the logic displayText gets the displayText that is already in the screen
	 * and then adds the sign after the action command
	 */
	/*
	 * displayText = displayField.getText(); Get value from display field,
	 * convert, do prev op If this is the first op, previousOp will be =.
	 */
	class OpListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// Next thing must be a the operator
			try {

				String displayText = displayField.getText();
				String Sign = e.getActionCommand();
				if (Sign == "+") {
					displayField.setText(displayText + "+");
				}

				else if (Sign == "-") {
					displayField.setText(displayText + "-");
				}

				else if (Sign == "*") {
					displayField.setText(displayText + "*");
				}

				else if (Sign == "/") {
					displayField.setText(displayText + "/");
				} else if (Sign == "=") {
					try {
						displayField.setText(logic.Evaluate(displayText));
					} catch (DivideByZeroException e1) {
						displayField.setText("you cannot divide by zero");
					}
				} else if (Sign == "MC") {
					logic.ResetStoredVal();
				}

				else if (Sign == "MS") {

					logic.Store(displayText);
				} else if (Sign == "M+") {
					displayField.setText(Double.toString(logic.GetStoredVaL()));

				} else if (Sign == "M-") {

					logic.Store(displayText);
				}

				else if (Sign == "%") {
					displayField.setText(displayText + "%");
				} else if (Sign == "x²") {

					String result = logic.Square(displayText);
					displayField.setText(result);
				}

				else if (Sign == "←") {
					displayField.setText(displayField.getText().substring(0, displayField.getText().length() - 1));
				}

				else if (Sign == "√") {
					String result = logic.SquareRoot(displayText);
					displayField.setText(result);
				} else if (Sign == "MR") {
					displayField.setText(Double.toString(logic.GetStoredVaL()));
				}

			} /* TRY ENDS HERE AS PERAMITERS ARE DONE */
			catch (NumberFormatException ex) {
				actionClear();
				displayField.setText("Error");
			}

		}// end method
	} // end class

	// inner listener class ClearListener */
	// Action listener for numeric keys */
	/*
	 * NumListener for the calculator digit is what the sign is when it is added
	 * to the displyField when it has been entered in through getActionCommand
	 * displayText gets the displayText that is already in the screen and then
	 * adds the sign after the action command
	 */

	/*
	 * This is the first digit, clear field and set
	 */
	private class NumListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String digit = e.getActionCommand();
			/* Get text from button */
			String displayText = displayField.getText();

			displayField.setText(displayText + digit);

		}
	}

	/* inner listener class ClearListener */
	/*
	 * ClearListener inner listener class ClearListener takes the action that
	 * was made and clears it from the screen
	 */

	/*
	 * actionClear This is the variable created for the method call to clear the
	 * screen.
	 */
	class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			actionClear();
		}
	}
}