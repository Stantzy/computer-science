package algorithms.greedy.majority_vote;

/* Boyer-Moore majority vote algorithm */
public class MajorityVote {
    public static int majorityVote(int[] numbers) {
        if(numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException(
                "Numbers array must not be null/empty"
            );
        }

        int counter = 0;
        int majority = 0;

        for(int num : numbers) {
            if(counter == 0)
                majority = num;

            if(num == majority)
                counter++;
            else
                counter--;
        }

        return majority;
    }
}
