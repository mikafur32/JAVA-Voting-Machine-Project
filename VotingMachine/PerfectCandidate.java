
// YOU ARE NOT REQUIRED TO FULLY UNDERSTAND THIS CODE; READ IT BUT YOU MAY NOT
// MODIFY IT.

import java.io.IOException;
import javax.swing.JOptionPane;


public class PerfectCandidate{
	public static void main(String[] args)	{
		Ballot ballot = null;

		do {
			FileSelector inputSelector = new FileSelector(
				null, "Select Input File", "Path to input file", true);

			inputSelector.setVisible(true);

			String inputFilename = inputSelector.getSelectedFile();

			if (inputFilename == null){
				return;
			}

			try	{
				ballot = BallotReader.readBallot(inputFilename);
			}
			catch (IOException e)	{
				JOptionPane.showMessageDialog(null,
					"Could not open  and read input file",
					"Input Error",
					JOptionPane.ERROR_MESSAGE);
			}
		}	while (ballot == null);


		PasswordDialog pass = null;


		int result = 0; //checks the answer to question: "Are there more voters today?"
		while(result != JOptionPane.NO_OPTION)
		{
			//Password Dialog
			pass = new PasswordDialog(null,ballot); //Enter Social Security Number
			pass.setVisible(true);

			//Ballot Dialog
			BallotDialog bf = new BallotDialog(null, ballot);
			bf.setVisible(true);
			result = JOptionPane.showConfirmDialog(null,
					"Are there more voters today?",
					"Voters",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		}

		result = 0; //resets result for further use below to answer another question


		result = JOptionPane.showConfirmDialog(null,
				"Would you like to output the encrypted file?",
				"Encrypted file",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // Asks if user wants to see the encrypted files

		if(result != JOptionPane.NO_OPTION)
		{
			FileSelector outputSelector = new FileSelector(
					null, "Select Encrypted Output File", "Path to encrypted output file", false);

			outputSelector.setVisible(true);

			String outputFilename = outputSelector.getSelectedFile();

			if (outputFilename == null)
			{
				return;
			}

			try
			{
				ResultWriter.writeResults(outputFilename, ballot, PasswordDialog.getSSList());
			}	catch (IOException e)
			{
				JOptionPane.showMessageDialog(null,
						"Could not write results to output file",
						"Output Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		JOptionPane.showMessageDialog(null,
				"Output to regular file",
				"Output",
				JOptionPane.INFORMATION_MESSAGE);

		FileSelector outputSelector2 = new FileSelector(
				null, "Select  Output File", "Path to output file", false);

		outputSelector2.setVisible(true);

		String outputFilename2 = outputSelector2.getSelectedFile();

		if (outputFilename2 == null)
		{
			return;
		}

		try	{
			ResultWriter.writeResults(outputFilename2, ballot);
		}	catch (IOException e)	{
			JOptionPane.showMessageDialog(null,
					"Could not write results to output file",
					"Output Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
