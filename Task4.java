import java.util.*;


public class Task4 {
    public static void main(String[] args) {
        System.out.println(nonRepeatable("abracadabra")); // 41
        System.out.println(nonRepeatable("paparazzi") + "\n");

        System.out.println(generateBrackets(1)); // 42
        System.out.println(generateBrackets(2));
        System.out.println(generateBrackets(3) + "\n");

        List<String> filteredList = generateAndFilterBinaryNumbers(5); // 43
        System.out.println(filteredList  + "\n");

        String input11 = "abababa";
        System.out.println(alphabeticRow(input11));
        String input1 = "abcdjuwx"; // 44
        System.out.println(alphabeticRow(input1));
        String input2 = "klmabzyxw";
        System.out.println(alphabeticRow(input2) + "\n");
        System.out.println(alphabeticRow("abababa"));
        System.out.println(alphabeticRow("abcdjuwx"));
        System.out.println(alphabeticRow("klmabzyxw") + "\n");

        System.out.println(compressAndSort("aaabbcdd")); // 45
        System.out.println(compressAndSort("vvvvaajaaaaa") + "\n");

        System.out.println(convertToNum("eight")); // 46
        System.out.println(convertToNum("five hundred sixty seven"));
        System.out.println(convertToNum("thirty one") + "\n");

        System.out.println(uniqueSubstring("123412324")); // 47
        System.out.println(uniqueSubstring("111111"));
        System.out.println(uniqueSubstring("77897898") + "\n");

        int[][] matrix1 = { // 48
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int[][] matrix2 = {
                {2, 7, 3},
                {1, 4, 8},
                {4, 5, 9}
        };
        System.out.println(shortestWay(matrix1));
        System.out.println(shortestWay(matrix2) + "\n");

        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng")); // 49
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat") + "\n");

        System.out.println(switchNums(519, 723)); // 50
        System.out.println(switchNums(491, 3912));
        System.out.println(switchNums(6274, 71259) + "\n");
    }
    public static String nonRepeatable(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        char firstChar = str.charAt(0);
        String remainingString = str.substring(1);
        remainingString = remainingString.replace(Character.toString(firstChar), "");

        return firstChar + nonRepeatable(remainingString);
    }

    public static List<String> generateBrackets(int n) {
        List<String> result = new ArrayList<>();
        generate("", 0, 0, n, result);
        return result;
    }

    private static void generate(String current, int open, int close, int max, List<String> result) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        if (open < max) {
            generate(current + "(", open + 1, close, max, result);
        }
        if (close < open) {
            generate(current + ")", open, close + 1, max, result);
        }
    }
    public static List<String> binarySystem(int n) {
        Set<String> uniqueCombinations = new HashSet<>();
        generateCombinations("", n, uniqueCombinations);
        return new ArrayList<>(uniqueCombinations);
    }

    private static void generateCombinations(String prefix, int n, Set<String> result) {
        if (n == 0) {
            result.add(prefix);
            return;
        }

        if (prefix.isEmpty()) {
            generateCombinations("0", n - 1, result);
            generateCombinations("1", n - 1, result);
        } else {
            char lastChar = prefix.charAt(prefix.length() - 1);
            if (lastChar == '0') {
                generateCombinations(prefix + "1", n - 1, result);
            } else if (lastChar == '1') {
                generateCombinations(prefix + "0", n - 1, result);
            }

            // Добавляем случай, когда следующий символ также может быть '1'
            generateCombinations(prefix + "1", n - 1, result);
        }
    }
    public static List<String> generateAndFilterBinaryNumbers(int n) {
        List<String> filteredList = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            String binaryString = Integer.toBinaryString(i);
            while (binaryString.length() < n) {
                binaryString = "0" + binaryString;
            }

            if (!binaryString.contains("00")) {
                filteredList.add(binaryString);
            }
        }

        return filteredList;
    }
    public static String alphabeticRow(String str) {
        String longestRow = "";
        int n = str.length();

        for (int i = 0; i < n; i++) {
            for (int route : new int[]{1, -1}) {
                int j = i;
                while (j < n - 1 && str.charAt(j + 1) == str.charAt(j) + route) {
                    j++;
                }
                if (j - i + 1 > longestRow.length()) {
                    longestRow = str.substring(i, j + 1);
                }
            }
        }

        return longestRow;
    }

    public static String compressAndSort(String input) {
        StringBuilder result = new StringBuilder();
        ArrayList<String> chunks = new ArrayList<>();

        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                count++;
            } else {
                chunks.add(input.charAt(i - 1) + Integer.toString(count));
                count = 1;
            }
        }
        chunks.add(input.charAt(input.length() - 1) + Integer.toString(count));

        Collections.sort(chunks, new Comparator<String>() {
            public int compare(String a, String b) {
                return Integer.compare(Integer.parseInt(a.substring(1)), Integer.parseInt(b.substring(1)));
            }
        });

        for (String chunk : chunks) {
            result.append(chunk);
        }

        return result.toString();
    }
    public static int convertToNum(String wordStr) {
        HashMap<String, Integer> wordToNum = new HashMap<>();
        wordToNum.put("zero", 0);
        wordToNum.put("one", 1);
        wordToNum.put("two", 2);
        wordToNum.put("three", 3);
        wordToNum.put("four", 4);
        wordToNum.put("five", 5);
        wordToNum.put("six", 6);
        wordToNum.put("seven", 7);
        wordToNum.put("eight", 8);
        wordToNum.put("nine", 9);
        wordToNum.put("ten", 10);
        wordToNum.put("eleven", 11);
        wordToNum.put("twelve", 12);
        wordToNum.put("thirteen", 13);
        wordToNum.put("fourteen", 14);
        wordToNum.put("fifteen", 15);
        wordToNum.put("sixteen", 16);
        wordToNum.put("seventeen", 17);
        wordToNum.put("eighteen", 18);
        wordToNum.put("nineteen", 19);
        wordToNum.put("twenty", 20);
        wordToNum.put("thirty", 30);
        wordToNum.put("forty", 40);
        wordToNum.put("fifty", 50);
        wordToNum.put("sixty", 60);
        wordToNum.put("seventy", 70);
        wordToNum.put("eighty", 80);
        wordToNum.put("ninety", 90);
        wordToNum.put("hundred", 100);
        wordToNum.put("thousand", 1000);

        String[] words = wordStr.split(" ");
        int total = 0;
        int currentTotal = 0;

        for (String word : words) {
            if (wordToNum.containsKey(word)) {
                int num = wordToNum.get(word);
                if (num == 100) {
                    currentTotal *= num;
                } else if (num == 1000) {
                    total += currentTotal;
                    total *= num;
                    currentTotal = 0;
                } else {
                    currentTotal += num;
                }
            }
        }
        total += currentTotal;

        return total;
    }
    public static String uniqueSubstring(String input) {
        String longestUnique = "";
        String currentUnique = "";

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (currentUnique.indexOf(c) == -1) {
                currentUnique += c;
                if (currentUnique.length() > longestUnique.length()) {
                    longestUnique = currentUnique;
                }
            } else {
                currentUnique = "" + c;
            }
        }   

        return longestUnique;
    }
    public static int shortestWay(int[][] matrix) {
        int n = matrix.length;
        int[][] yx = new int[n][n];
        yx[0][0] = matrix[0][0];

        for (int j = 1; j < n; j++) {
            yx[0][j] = yx[0][j - 1] + matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            yx[i][0] = yx[i - 1][0] + matrix[i][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                yx[i][j] = Math.min(yx[i - 1][j], yx[i][j - 1]) + matrix[i][j];
            }
        }

        return yx[n - 1][n - 1];
    }
    public static String numericOrder(String input) {
        String[] words = input.split(" ");
        String[] orderedWords = new String[words.length];

        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (Character.isDigit(c)) {
                    int index = Character.getNumericValue(c) - 1;
                    orderedWords[index] = word.replaceAll("[0-9]", "");
                    break;
                }
            }
        }

        return String.join(" ", orderedWords);
    }
    public static int switchNums(int num1, int num2) {
        int[] count1 = new int[10];
        char[] num2Chars = Integer.toString(num2).toCharArray();

        while (num1 > 0) {
            count1[num1 % 10]++;
            num1 /= 10;
        }

        for (int i = 0; i < num2Chars.length; i++) {
            for (int j = 9; j > num2Chars[i] - '0'; j--) {
                if (count1[j] > 0) {
                    count1[j]--;
                    num2Chars[i] = (char) (j + '0');
                    break;
                }
            }
        }

        return Integer.parseInt(new String(num2Chars));
    }
}