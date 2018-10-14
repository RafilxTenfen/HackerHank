package ArrayManipulation;

public class Solution {

    static long arrayManipulation(int n, int[][] queries) {
        long maxVal = Long.MIN_VALUE;
        long sum = 0;
        int [] values = new int[n + 1];

        for(int i = 0; i < queries.length; i++) {
            values[queries[i][0] - 1] += queries[i][2];
            values[queries[i][1]] -= queries[i][2];
        }

        for(int val: values) {
            sum += val;
            maxVal = Math.max(sum, maxVal);
        }

        return maxVal;
    }
}
