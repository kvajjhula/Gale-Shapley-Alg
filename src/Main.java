import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to randomize input? (yes/no)");
        String choice = sc.next();
        Man[] allMen;
        Woman[] allWomen;

        if (choice.equalsIgnoreCase("yes")) {
            System.out.println("Enter the input size:");
            int inputSize = sc.nextInt();
            Random rand = new Random();
            allMen = new Man[inputSize];
            allWomen = new Woman[inputSize];
            for (int i = 0; i < allMen.length; i++) {
                int[] perm = Permutation(inputSize, rand);
                allMen[i] = new Man(i, perm);
            }
            for (int i = 0; i < allWomen.length; i++) {
                int[] perm = Permutation(inputSize, rand);
                allWomen[i] = new Woman(i, perm);
            }
        } else {
            allMen = new Man[4];
            allWomen = new Woman[4];
            allMen = new Man[4];
            allWomen = new Woman[4];
            allMen[0] = new Man(0, new int[]{2, 1, 3, 0});
            allMen[1] = new Man(1, new int[]{0, 1, 3, 2});
            allMen[2] = new Man(2, new int[]{0, 1, 2, 3});
            allMen[3] = new Man(3, new int[]{0, 1, 2, 3});
            allWomen[0] = new Woman(0, new int[]{0, 2, 1, 3});
            allWomen[1] = new Woman(1, new int[]{2, 0, 3, 1});
            allWomen[2] = new Woman(2, new int[]{3, 2, 1, 0});
            allWomen[3] = new Woman(3, new int[]{2, 3, 1, 0});
        }
        printPreferences(allMen, allWomen);
        galeShapley(allMen, allWomen);
        printFinalPairings(allMen);
    }

    public static void printPreferences(Man[] allMen, Woman[] allWomen) {
        System.out.println("Men's preferences:");
        for (Man man : allMen) {
            System.out.print("Man " + man.id + ": ");
            for (int i = 0; i < man.preferences.length; i++) {
                System.out.print(man.preferences[i]);
                if (i < man.preferences.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        System.out.println("Women's preferences:");
        for (Woman woman : allWomen) {
            System.out.print("Woman " + woman.id + ": ");
            for (int i = 0; i < woman.preferences.length; i++) {
                System.out.print(woman.preferences[i]);
                if (i < woman.preferences.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }


    public static int[] Permutation(int n, Random rand) {
        int[] arr = IdentityPermutation(n);
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
    // Implementing the IdentityPermutation method to create an array of size n
// to generate permutations
    public static int[] IdentityPermutation(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }
    public static void printFinalPairings(Man[] allMen) {
        System.out.println("Final pairings are:");
        for (Man man : allMen) {
            System.out.println("Man " + man.id + " is paired with Woman " +
                    man.currentPartner.id);
        }
    }
    public static void galeShapley(Man[] allMen, Woman[] allWomen) {
// List to keep track of all free men at any given time
        List<Man> freeMen = new ArrayList<>(Arrays.asList(allMen));
// while there is a free m
        while (!freeMen.isEmpty()) {
// find a pairing for next free man in list
            Man m = freeMen.get(0);
// while single man has not exhausted his preference list
            while (!m.isEngaged() && m.currentPreferenceIndex < m.preferences.length) {
// retrieve the next woman he hasn't proposed to
                Woman w = allWomen[m.preferences[m.currentPreferenceIndex]];
// if w is single then they get engaged
                if (!w.isEngaged()) {
                    m.engageTo(w);
                    System.out.println("Man " + m.id + " is engaged to Woman " + w.id);
                } else { // otherwise, pick current suitor or stay with partner
                    if (w.prefersNewSuitor(m)) {
                        Man previousPartner = (Man) w.currentPartner;
                        w.breakEngagement();
                        previousPartner.currentPreferenceIndex++;
                        freeMen.add(previousPartner); // Add the rejected man back to the list
                        m.engageTo(w);
                        System.out.println("Man " + m.id + " is engaged to Woman " + w.id);
                    } else {
                        m.currentPreferenceIndex++;
                    }
                }
            }
            freeMen.remove(m); // remove man from freeMen list once he is engaged
        }
    }

    public static void populateCurrentPreferenceIndex(Woman[] allWomen) {
        for (Woman w : allWomen) {
            int currPartner = w.currentPartner.id;
            for (int i = 0; i < w.preferences.length; i++) {
                if (w.preferences[i] == currPartner) {
                    w.currentPreferenceIndex = i;
                }
            }
        }
    }
    public static void calculateGoodnessValue(Man[] allMen, Woman[] allWomen) {
        double mRank = 0;
        double wRank = 0;
        for (int i = 0; i < allMen.length; i++) {
            mRank += allMen[i].currentPreferenceIndex;
            wRank += allWomen[i].currentPreferenceIndex;
        }
        System.out.println("MGoodness: " + mRank/allMen.length);
        System.out.println("WGoodness: " + wRank/allWomen.length);
    }
}