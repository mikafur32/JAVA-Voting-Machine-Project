import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Container class for the readBallot() method.
 */
public class BallotReader{

	/**
	 * Reads input from a file in a specific format to return a valid Ballot object with all candidates initialized.
	 * @param fileName name of the file
	 * @return a Ballot
	 * @throws IOException In the event of an invalid file name, an IOException is thrown.
	 */
	public static Ballot readBallot(String fileName) throws IOException
	{

		List<String> lines = Files.readAllLines(Paths.get(fileName));
		Ballot ballot = new Ballot(lines.get(0));
		Integer numCandidates = Integer.parseInt(lines.get(1));
		int i = 0;
		while(numCandidates > i)
		{
			String[] candidateStr = lines.get(i + 2).split(";");


			ballot.addCandidate(new Candidate(candidateStr[0],candidateStr[1]));
			i++;
		}

		//Ballot ballot = new Ballot("James Beard Award");
		//ballot.addCandidate(new Candidate("Nina Compton", "Compere Lapin Party"));
		//ballot.addCandidate(new Candidate("Alon Shaya", "Saba Party"));
		//ballot.addCandidate(new Candidate("Emeril Lagasse", "Emeril's Party"));
		//return ballot;
		return ballot;
	}



}
