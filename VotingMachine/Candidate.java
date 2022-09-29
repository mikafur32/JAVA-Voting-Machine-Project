import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * A candidate containing the name, party affiliation, and number of votes of the candidate.
 * Will increase the number of votes for every vote cast for this candidate.
 */
public class Candidate
{
	/**
		The name of the candidate.
	 */
	private String name;
	/**
		The party affiliation of candidate.
	 */
	private String affiliation;
	/**
		Number of votes for candidate.
	 */
	private int numVotes;

	/**
	 * The constructor assigning the number of votes to 0 and taking in the name and affiliation of the candidate.
	 * @param name The name of the candidate.
	 * @param affiliation The party affiliation of the candidate.
	 */
	public Candidate(String name, String affiliation)
	{
		this.name = name;
		this.affiliation = affiliation;
		numVotes = 0;
	}

	/**
	 * The default Constructor assigning the name and affiliation to empty strings and number of votes to 0.
	 */
	public Candidate()
	{
		name = "";
		affiliation = "";
		numVotes = 0;
	}

	/**
	 * Returns the name of the candidate.
	 * @return name
	 */
	public String getName()
	{
    	return name;
	}

	/**
	 * Returns the party affiliation of the candidate
	 * @return affiliation
	 */
	public String getAffiliation()
	{
    	return affiliation;

	}

	/**
	 * Returns the current vote count.
	 * @return numVotes
	 */
	public int getVoteCount()
	{
    	return numVotes;
	}

	/**
	 * Increments the number of votes by 1.
	 */
	public void tallyVote()
	{

		this.numVotes++;
	}

	/**
	 * Creates and returns a string representation of the candidate.
	 * @return a string representation of the candidate.
	 */
	@Override
	public String toString()
	{return String.format("%s - %s", this.name, this.affiliation);}

	/**
	 * Adds the ability to append a string ot the end of the toString.
	 * @param append The string to be appended at the end of the toString.
	 * @return toString with the appended "append" parameter.
	 */
	public String toString(String append)
	{
		return this.toString() + append;
	}

	public String encrypt(String key) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		Encoder encoder = new Encoder(key);
		return encoder.encrypt(toString());
	}

	/**
	 * Compares the affiliation and name of each candidates, if the same, return true.
	 * @param c candidate to be compared.
	 * @return the equality of the two Candidate's affiliation and name.
	 */
	public boolean equals(Candidate c)
	{
		return affiliation.equals(c.getAffiliation()) && name.equals(c.getName());
	}
}
