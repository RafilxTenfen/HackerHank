package GridSearch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    static String gridSearch(String[] G, String[] P) {
        Pattern pattern = Pattern.compile(P[0]);

        for(int ig = 0; ig < G.length; ig++) {
            Matcher matcher = pattern.matcher(G[ig]);

            int startFind = 0;
            while(matcher.find(startFind)) {
                startFind = matcher.start() + 1;

                findingPattern:
                if(ig + P.length - 1 >= G.length) {
                    break;
                } else {
                    for(int ip = 1; ip < P.length; ip++) {
                        if(!P[ip].equals(G[ig + ip].substring(matcher.start(), matcher.end()))) {
                            break findingPattern;
                        }
                    }
                    return "YES";
                }
            }
        }
        return "NO";
    }

}