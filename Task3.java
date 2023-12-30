import java.util.*;

public class Task3 {
    public static void main(String[] args) {
        String input = "apple";
        String result = replaceVowels(input);
        System.out.println(result);
        String input2 = "Even if you did this task not by yourself, you have to understand every single line of code";
        String result2 = replaceVowels(input2);
        System.out.println(result2 + "\n");

        System.out.println(stringTransform("hello"));
        System.out.println(stringTransform("bookkeeper") + "\n");

        System.out.println(doesBlockFit(1, 3, 5, 4, 5));
        System.out.println(doesBlockFit(1, 8, 1, 1, 1));
        System.out.println(doesBlockFit(1, 2, 2, 1, 1) + "\n");

        System.out.println(numCheck(243));
        System.out.println(numCheck(52) + "\n");

        System.out.println(countRoots(new int[]{1, -3, 2}));
        System.out.println(countRoots(new int[]{2, 5, 2}));
        System.out.println(countRoots(new int[]{1, -6, 9}) + "\n");

        String[][] testData1 = {
                {"Apple", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Banana", "Shop2", "Shop3", "Shop4"},
                {"Orange", "Shop1", "Shop3", "Shop4"},
                {"Pear", "Shop2", "Shop4"}
        };

        String[][] testData2 = {
                {"Fridge", "Shop2", "Shop3"},
                {"Microwave", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Laptop", "Shop3", "Shop4"},
                {"Phone", "Shop1", "Shop2", "Shop3", "Shop4"}
        };

        System.out.println(salesData(testData1));
        System.out.println(salesData(testData2) + "\n");

        System.out.println(validSplit("apple Eagle egg goat"));
        System.out.println(validSplit("cat dog goose fish") + "\n");

        System.out.println(waveForm(new int[]{3, 1, 4, 2, 7, 5}));
        System.out.println(waveForm(new int[]{1, 2, 3, 4, 5}));
        System.out.println(waveForm(new int[]{1, 2, -6, 10, 3}) + "\n");

        System.out.println(commonVowel("Hello world"));
        System.out.println(commonVowel("Actions speak louder than words.") + "\n");

        int[][] array1 = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {5, 5, 5, 5, 5},
                {7, 4, 3, 14, 2},
                {1, 0, 11, 10, 1}
        };

        int[][] array2 = {
                {6, 4, 19, 0, 0},
                {81, 25, 3, 1, 17},
                {48, 12, 60, 32, 14},
                {91, 47, 16, 65, 217},
                {5, 73, 0, 4, 21}
        };
        System.out.println(Arrays.deepToString(array1));


        System.out.println(Arrays.deepToString(array2));


    }

    public static String replaceVowels(String input) { // 31
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if ("AEIOUaeiou".contains(String.valueOf(currentChar))) {
                result.append('*');
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    public static String stringTransform(String s) { // 32
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                result.append("Double").append(Character.toUpperCase(s.charAt(i)));
                i += 2;
            } else {
                result.append(s.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static boolean doesBlockFit(int a, int b, int c, int w, int h) {            // 33
        return (fits(a, b, w, h) || fits(a, c, w, h) || fits(b, c, w, h));             //
    }                                                                                  //

    //
    private static boolean fits(int side1, int side2, int w, int h) {                  //
        return (side1 <= w && side2 <= h) || (side1 <= h && side2 <= w);               //
    }                                                                                  //

    public static boolean numCheck(int inputNumber) { // 34
        int sumOfSquares = 0;
        int number = inputNumber;

        while (number > 0) {
            int digit = number % 10;
            sumOfSquares += digit * digit;
            number /= 10;
        }
        return (sumOfSquares % 2  == inputNumber % 2 );
    }

    public static int countRoots(int[] coefficients) { //35
        int a = coefficients[0];
        int b = coefficients[1];
        int c = coefficients[2];

        int discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return 0;
        }

        double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);

        int count = 0;
        if (root1 == (int) root1) {
            count++;
        }
        if (root2 == (int) root2 && root1 != root2) {
            count++;
        }

        return count;
    }

    public static List<String> salesData(String[][] data) { // 36
        List<String> result = new ArrayList<>();
        Set<String> uniqueShops = new HashSet<>();

        for (String[] productData : data) {
            for (int i = 1; i < productData.length; i++) {
                uniqueShops.add(productData[i]);
            }
        }

        for (String[] productData : data) {
            Set<String> productShops = new HashSet<>();
            for (int i = 1; i < productData.length; i++) {
                productShops.add(productData[i]);
            }

            if (productShops.equals(uniqueShops)) {
                result.add(productData[0]);
            }
        }

        return result;
    }

    public static boolean validSplit(String sentence) { //37
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].toLowerCase().charAt(words[i].length() - 1) != words[i + 1].toLowerCase().charAt(0)) {
                return false;
            }
        }
        return true;
    }

    public static boolean waveForm(int[] arr) { // 38
        if (arr.length < 2) {
            return false;
        }

        boolean shouldIncrease = arr[0] < arr[1];

        for (int i = 1; i < arr.length; i++) {
            if (shouldIncrease) {
                if (arr[i - 1] >= arr[i]) {
                    return false;
                }
            } else {
                if (arr[i - 1] <= arr[i]) {
                    return false;
                }
            }
            shouldIncrease = !shouldIncrease;
        }
        return true;
    }

    public static char commonVowel(String sentence) { // 39
        Map<Character, Integer> vowelCount = new HashMap<>();
        String vowels = "aeiouAEIOU";

        for (char v : vowels.toCharArray()) {
            vowelCount.put(v, 0);
        }

        for (char c : sentence.toCharArray()) {
            if (vowelCount.containsKey(c)) {
                vowelCount.put(c, vowelCount.get(c) + 1);
            }
        }

        char maxVowel = 0;
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : vowelCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxVowel = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return maxVowel;
    }

    public static int[][] dataScience(int[][] inputArrays) {
        int size = inputArrays.length;

        for (int currentIndex = 0; currentIndex < size; currentIndex++) {
            int columnSum = 0;

            for (int arrayIndex = 0; arrayIndex < size; arrayIndex++) {
                if (currentIndex != arrayIndex) {
                    columnSum += inputArrays[arrayIndex][currentIndex];
                }
            }

            inputArrays[currentIndex][currentIndex] = columnSum / (size - 1);
        }

        return inputArrays;
    }
}