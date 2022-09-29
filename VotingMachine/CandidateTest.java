import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CandidateTest
{
    Candidate person1 = new Candidate("person1", "party1");
    Candidate person2 = new Candidate("person2", "party2");

    @Test
    void getName()
    {
        assertEquals("person1", person1.getName());
    }

    @Test
    void getAffiliation()
    {
        assertEquals("party1", person1.getAffiliation());

    }

    @Test
    void getVoteCount()
    {
        assertEquals(0, person1.getVoteCount());
    }

    @Test
    void tallyVote()
    {
        person1.tallyVote();
        assertEquals(1, person1.getVoteCount());
    }

    @Test
    void testToString()
    {
        assertEquals("person1 - party1", person1.toString());
    }


    @Test
    void equals()
    {
        assertEquals(true, person1.equals(person1));
    }
}