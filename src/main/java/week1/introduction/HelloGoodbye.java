package week1.introduction;

public class HelloGoodbye {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("You must type two names");
            return;
        }
        int i = 0;
        System.out.println("Hello " + args[i] + " and " + args[i+1] + ".");
        System.out.println("Goodbye " + args[i+1] + " and " + args[i]+ ".");
    }
}
