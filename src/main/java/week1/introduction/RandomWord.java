package week1.introduction;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {
        String champ = "";
        int n = 0;
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if (StdRandom.bernoulli((double) 1/++n)) {
                champ = str;
            }
        }
        StdOut.print(champ);
    }

}
