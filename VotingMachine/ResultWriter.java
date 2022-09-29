import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;

/**
 * Container class for the writeResults() method.
 */
public class ResultWriter
{
	/**
	 * Writes the results of the electoral race according to the required output,
	 * using the private introline, bodyLines, and outroLine methods to distribute the output.
	 *
	 * @param fileName the name of the input file to be read.
	 * @param ballot the ballot on which the candidates are.
	 * @throws IOException In the event of an invalid file name, an IOException is thrown.
	 */
	public static void writeResults(String fileName, Ballot ballot)
	throws IOException
	{
		String printer = "";

		printer += introLine(ballot);
		printer += bodyLines(ballot.getCandidates());
		printer += outroLine(ballot);

		File output = new File(fileName);
		FileWriter writer = new FileWriter(output);
		writer.write(printer);
		writer.flush();
		writer.close();
	}


	public static void writeResults(String fileName, Ballot ballot, ArrayList<Integer> keyList) throws IOException {
		String printer = "";
		for (int i = 0; i < keyList.size(); i++)
		{
			Candidate c = ballot.getCandidates().get(i);
			try {
				printer += c.encrypt(String.valueOf(keyList.get(i))) + "\n";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}

		}


		File output = new File(fileName);
		FileWriter writer = new FileWriter(output);
		writer.write(printer);
		writer.flush();
		writer.close();
	}

	/**
	 Returns a string representing the first line of the output describing the header of the output.
	 @param ballot of type Ballot from which the information for the first line is obtained.
	 @return a printer string representing the first line of the output.
	 */
	private static String introLine(Ballot ballot)
	{
		String printer = String.format("RESULTS - %s \n", ballot.getElectionName());
		int printLen = printer.length();
		for (int i = 0; i < printLen; i++)
		{
			printer += "-";
		}
		printer += "\n";
		return printer;
	}
	/**
	 Returns a string representing the last line of the output describing the winner of the electoral race.
	 @param ballot of type Ballot from which the information for the last line is obtained.
	 @return a printer string representing the last line of the output.
	 */
	private static String outroLine(Ballot ballot)
	{
		Candidate winner = ballot.generateWinner();
		String printer;
		if(!winner.getName().equals("No Winner"))
		{
			printer = String.format("WINNER: %s - %s", winner.getName(), winner.getAffiliation());
		}
		else
		{
			printer = "NO WINNER";
		}
		return printer;
	}

	/**
	 * Returns a string of the name, affiliation, and the number of votes each candidate
	 * had at the termination of the electoral race according to the required output format.
	 * @param candidateList list of candidates on the ballot.
	 * @return a printer string representing the body lines of the output.
	 */
	private static String bodyLines(ArrayList<Candidate> candidateList)
	{
		Candidate longestName = new Candidate();
		// Compares next candidate's name the current longest name,
		// if longer, longestNameIndex is assigned to the new index.

		for (Candidate i: candidateList)
		{
			if(i.getName().length() > longestName.getName().length())
			{
				longestName = i;
			}
		}
		/*
		Once the index of the longest name is found, we must add 12 spaces at the end of the tag,
		then append the numVotes to the end of the tag, accounting for the 10^n of votes
		and correct the other candidate's tags to be in line with the longestName
		*/
		int numCharsInLongestName = longestName.toString().length();
		String printer = "";
		for (Candidate k: candidateList)
		{
			printer += (k.toString(" ".repeat(numCharsInLongestName-k.toString().length()) + " ".repeat(12) + k.getVoteCount()+"\n"));
		}
		return printer;
	}
}
