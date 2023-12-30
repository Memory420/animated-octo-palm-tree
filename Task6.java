import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.math.BigInteger;
import java.util.Stack;
import java.util.Map;
public class Task6 {
    public static void main(String[] args) {
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        System.out.println(hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth") + "\n");

        System.out.println(collect("intercontinentalisationalism", 6));
        System.out.println(collect("strengths", 3));
        System.out.println(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15) + "\n");

        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println(nicoCipher("andiloveherso", "tesha"));
        System.out.println(nicoCipher("mubashirhassan", "crazy"));
        System.out.println(nicoCipher("edabitisamazing", "matt"));
        System.out.println(nicoCipher("iloveher", "612345") + "\n");

        System.out.println(java.util.Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15}, 45)));
        System.out.println(java.util.Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        System.out.println(java.util.Arrays.toString(twoProduct(new int[]{1,  2, -1,  4,  5,  6,  10, 7}, 20)));
        System.out.println(java.util.Arrays.toString(twoProduct(new int[]{1, 2, 3, 4, 5,  6, 7, 8, 9, 10}, 10)));
        System.out.println(java.util.Arrays.toString(twoProduct(new int[]{100, 12, 4, 1, 2}, 15)) + "\n");

        System.out.println(java.util.Arrays.toString(isExact(6)));
        System.out.println(java.util.Arrays.toString(isExact(24)));
        System.out.println(java.util.Arrays.toString(isExact(125)));
        System.out.println(java.util.Arrays.toString(isExact(720)));
        System.out.println(java.util.Arrays.toString(isExact(1024)));
        System.out.println(java.util.Arrays.toString(isExact(40320)) + "\n");

        System.out.println(fractions("0.(6)"));
        System.out.println(fractions("1.(1)"));
        System.out.println(fractions("3.(142857)"));
        System.out.println(fractions("0.19(2367)"));
        System.out.println(fractions("0.1097(3)") + "\n");

        System.out.println(pilish_string("33314444"));
        System.out.println(pilish_string("X"));
        System.out.println(pilish_string("") + "\n");

        System.out.println(evaluateExpression("3 + 5 * (2 - 6)"));
        System.out.println(evaluateExpression("6 - 18 / (4 - 1)"));
        System.out.println(evaluateExpression("6 / 0") + "\n");

        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba") + "\n");

        System.out.println(findLCS("abcd", "bd"));
        System.out.println(findLCS("aggtab", "gxtxamb") + "\n");


    }

    public static String hiddenAnagram(String text, String anagram) {
        String cleanedText = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String cleanedAnagram = anagram.replaceAll("[^a-zA-Z]", "").toLowerCase();

        HashMap<Character, Integer> anagramMap = new HashMap<>();
        for (char c : cleanedAnagram.toCharArray()) {
            anagramMap.put(c, anagramMap.getOrDefault(c, 0) + 1);
        }

        int windowStart = 0;
        int lettersToMatch = cleanedAnagram.length();
        for (int windowEnd = 0; windowEnd < cleanedText.length(); windowEnd++) {
            char endChar = cleanedText.charAt(windowEnd);
            if (anagramMap.containsKey(endChar)) {
                anagramMap.put(endChar, anagramMap.get(endChar) - 1);
                if (anagramMap.get(endChar) >= 0) {
                    lettersToMatch--;
                }
            }

            if (lettersToMatch == 0) {
                return cleanedText.substring(windowStart, windowEnd + 1);
            }

            if (windowEnd >= cleanedAnagram.length() - 1) {
                char startChar = cleanedText.charAt(windowStart++);
                if (anagramMap.containsKey(startChar)) {
                    if (anagramMap.get(startChar) >= 0) {
                        lettersToMatch++;
                    }
                    anagramMap.put(startChar, anagramMap.get(startChar) + 1);
                }
            }
        }

        return "notfound";
    }
    public static List<String> collect(String s, int n) {
        if (s.length() < n) {
            return new ArrayList<>();
        }

        List<String> slices = collect(s.substring(n), n);
        slices.add(s.substring(0, n));
        if (slices.size() == 1) {
            Collections.sort(slices);
        }
        return slices;
    }
    public static String nicoCipher(String message, String key) {
        List<Pair> keyIndexPairs = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            keyIndexPairs.add(new Pair(key.charAt(i), i));
        }

        Collections.sort(keyIndexPairs);

        while (message.length() % key.length() != 0) {
            message += " ";
        }

        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < message.length(); i += key.length()) {
            char[] block = new char[key.length()];
            for (int j = 0; j < key.length(); j++) {
                int originalIndex = keyIndexPairs.get(j).index;
                block[j] = message.charAt(i + originalIndex);
            }
            for (char c : block) {
                encoded.append(c);
            }
        }

        return encoded.toString();
    }
    static class Pair implements Comparable<Pair> {
        char character;
        int index;
        Pair(char character, int index) {
            this.character = character;
            this.index = index;
        }

        @Override
        public int compareTo(Pair other) {
            return Character.compare(this.character, other.character);
        }
    }

    public static int[] twoProduct(int[] arr, int n) {
        Set<Integer> set = new HashSet<>();
        for (int value : arr) {
            if (n % value == 0 && set.contains(n / value)) {
                return new int[]{n / value, value};
            }
            set.add(value);
        }
        return new int[]{};
    }

    public static int[] isExact(int number) {
        return checkFactorial(number, 1, 1);
    }

    private static int[] checkFactorial(int number, int factorial, int n) {
        if (factorial == number) {
            return new int[]{number, n};
        } else if (factorial > number) {
            return new int[]{};
        }
        return checkFactorial(number, factorial * (n + 1), n + 1);
    }

    public static String fractions(String number) {
        int indexOfBracket = number.indexOf('(');
        if (indexOfBracket == -1) {
            return "Invalid input";
        }

        String nonRepeating = number.substring(0, indexOfBracket);
        String repeating = number.substring(indexOfBracket + 1, number.length() - 1);

        int nonRepeatingLength = nonRepeating.length() - nonRepeating.indexOf('.') - 1;
        int repeatingLength = repeating.length();

        BigInteger nonRepeatingPart = new BigInteger(nonRepeating.replace(".", ""));
        BigInteger repeatingPart = new BigInteger(repeating);

        BigInteger numerator = nonRepeatingPart.multiply(BigInteger.TEN.pow(repeatingLength))
                .add(repeatingPart)
                .subtract(nonRepeatingPart);
        BigInteger denominator = BigInteger.TEN.pow(nonRepeatingLength + repeatingLength)
                .subtract(BigInteger.TEN.pow(nonRepeatingLength));


        BigInteger gcd = numerator.gcd(denominator);
        numerator = numerator.divide(gcd);
        denominator = denominator.divide(gcd);

        return numerator + "/" + denominator;
    }

    public static String pilish_string(String txt) {

        int[] piDigits = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9};
        StringBuilder result = new StringBuilder();
        int charIndex = 0;

        for (int digit : piDigits) {
            if (charIndex >= txt.length()) {
                break;
            }

            if (result.length() > 0) {
                result.append(" ");
            }

            for (int i = 0; i < digit; i++) {
                if (charIndex + i < txt.length()) {
                    result.append(txt.charAt(charIndex + i));
                } else {
                    result.append(txt.charAt(txt.length() - 1));
                }
            }

            charIndex += digit;
        }

        return result.toString();
    }

    public static String evaluateExpression(String expression) {
        try {
            Stack<Double> numbers = new Stack<>();
            Stack<Character> operations = new Stack<>();

            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (c == ' ') {
                    continue;
                }

                if (c >= '0' && c <= '9') {
                    StringBuilder sb = new StringBuilder();
                    while (i < expression.length() && expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                        sb.append(expression.charAt(i++));
                    }
                    i--;
                    numbers.push(Double.parseDouble(sb.toString()));
                } else if (c == '(') {
                    operations.push(c);
                } else if (c == ')') {
                    while (operations.peek() != '(') {
                        numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
                    }
                    operations.pop();
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    while (!operations.empty() && hasPrecedence(c, operations.peek())) {
                        numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
                    }
                    operations.push(c);
                }
            }

            while (!operations.empty()) {
                numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
            }

            return numbers.pop().toString();
        } catch (Exception e) {
            return "Error: Invalid Expression";
        }
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    public static double applyOperation(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }

    public static String isValid(String s) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        Map<Integer, Integer> freqOfFreq = new HashMap<>();
        for (int freq : frequencyMap.values()) {
            freqOfFreq.put(freq, freqOfFreq.getOrDefault(freq, 0) + 1);
        }

        if (freqOfFreq.size() == 1) {
            return "YES";
        } else if (freqOfFreq.size() == 2) {
            if (freqOfFreq.containsKey(1) && freqOfFreq.get(1) == 1) {
                return "YES";
            }

            int f1 = 0, f2 = 0, countF1 = 0, countF2 = 0;
            for (Map.Entry<Integer, Integer> entry : freqOfFreq.entrySet()) {
                if (f1 == 0) {
                    f1 = entry.getKey();
                    countF1 = entry.getValue();
                } else {
                    f2 = entry.getKey();
                    countF2 = entry.getValue();
                }
            }

            if ((countF1 == 1 && (f1 - 1 == f2 || f1 == 1)) || (countF2 == 1 && (f2 - 1 == f1 || f2 == 1))) {
                return "YES";
            }
        }

        return "NO";
    }

    public static String findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int index = dp[m][n];
        char[] lcs = new char[index];
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs[index - 1] = s1.charAt(i - 1);
                i--;
                j--;
                index--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return new String(lcs);
    }
}