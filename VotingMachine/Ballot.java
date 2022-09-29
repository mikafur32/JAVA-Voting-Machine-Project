import java.util.ArrayList;


/**
 * A ballot containing the name of an election and a list of all the candidates.
 * The ballot also has the ability to generate the winner of the electoral race.
 */
public class Ballot
{
	private ArrayList<Candidate> encryptedCandidates;

	public ArrayList<Integer> getSSList() {
		return SSList;
	}

	public void setSSList(ArrayList<Integer> SSList) {
		this.SSList = SSList;
	}

	private ArrayList<Integer> SSList;
	/**
	 *	Name of the election (office).
	 */
	private String electionName;
	/**
	 *	ArrayList containing candidates in the election.
	 */
	private ArrayList<Candidate> candidateList;

	/**
	 * Constructor creating the ballot taking the name of the election and creating a new arrayList of candidates.
	 * @param electionName name of the election
	 */
	public Ballot(String electionName)
	{
		this.electionName = electionName;
		candidateList = new ArrayList<Candidate>();
		encryptedCandidates = new ArrayList<Candidate>();
	}
	/**
		Returns the name of the election (office).
	 	@return electionName
	 */
	public String getElectionName()
	{
    	return electionName;
	}

	/**
	 * Verifies no duplicate candidates.
	 * Adds a Candidate to the candidatesList.
	 * @param c the candidate to be added
	 */

	public void addCandidate(Candidate c)
	{
		//Turns the list into a string then checks to verify that the string value of c is in the string of candidates.
		if(! String.valueOf(candidateList).contains(c.toString()))
		{
			candidateList.add(c);
		}


	}

	/**
		Returns candidateList.
	 	@return candidateList
	 */
	public ArrayList<Candidate> getCandidates()
	{
		return candidateList;
	}

	/**
	 *	Determines the winner of the electoral race.
	 *	In the event of a tie, a new Candidate is made and returned
	 *	with name: "No Winner" and affiliation: null.
	 *
	 *	@return the winner of the electoral race
	 */
	public Candidate generateWinner()
	{
		Candidate leader = new Candidate();

		for (Candidate candidate : candidateList) {
			if (leader.getVoteCount() < candidate.getVoteCount()) {
				leader = candidate;
			} else if (leader.getVoteCount() == candidate.getVoteCount()) {
				leader = new Candidate("No Winner", null);
			}
		}
		return leader;
	}




}
