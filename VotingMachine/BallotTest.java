import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BallotTest
{
    Ballot ballot = new Ballot("election");
    @Test
    void getElectionName()
    {
        assertEquals("election", ballot.getElectionName());
    }

    @Test
    void getCandidates()
    {
        ballot.addCandidate(new Candidate("person1","party"));
        assertEquals("[person1 - party]", String.valueOf(ballot.getCandidates()));
    }

    @Test
    void addCandidate()
    {
        ballot.addCandidate(new Candidate("person1","party"));
        assertEquals("[person1 - party]",String.valueOf(ballot.getCandidates()));
        ballot.addCandidate(new Candidate("person1","party"));
        assertEquals("[person1 - party]",String.valueOf(ballot.getCandidates()));
    }


    @Test
    void generateWinner()
    {
        Candidate person1 = new Candidate("person1","party");
        person1.tallyVote();
        ballot.addCandidate(person1);
        assertEquals("person1 - party", String.valueOf(ballot.generateWinner()));

        Candidate person2 = new Candidate("person2","party");
        person2.tallyVote();
        ballot.addCandidate(person2);
        assertEquals("No Winner - null", String.valueOf(ballot.generateWinner()));

    }
}