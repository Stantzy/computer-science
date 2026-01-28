package tests.algorithms.greedy.majority_vote;

import static algorithms.greedy.majority_vote.MajorityVote.majorityVote;

public class MajorityVoteTest {
    public static void main(String[] args) {
        runTestCase(new int[] { 1, 2, 3, 3, 4, 5 }, 3);
        runTestCase(new int[] { 2, 2, 3, 3, 3, 2, 2 }, 2);
        runTestCase(new int[] { 1, 1, 1, 1, 1 }, 1);
        runTestCase(new int[] { 1 }, 1);
    }

    private static void runTestCase(int[] numbers, int expected) {
        int result = majorityVote(numbers);

        assert expected == result;
    }
}
