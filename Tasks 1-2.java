import java.util.Arrays;
import java.util.Random;
// import mtuci.main.muataz
public class Unity {
    public static void main(String[] args) {
        System.out.println(convert(5));
        System.out.println(convert(3));
        System.out.println(convert(8) + "\n");
        System.out.println(fitCalc(15, 1));
        System.out.println(fitCalc(24,2));
        System.out.println(fitCalc(41,3) + "\n");
        System.out.println(containers(3,4,2));
        System.out.println(containers(5,0,2));
        System.out.println(containers(4,1,4) + "\n");
        System.out.println(triangleType(5, 5, 5));
        System.out.println(triangleType(5, 4, 5));
        System.out.println(triangleType(3, 4, 5));
        System.out.println(triangleType(5, 1, 1));
        System.out.println(ternaryEvaluation(8,4));
        System.out.println(ternaryEvaluation(1,11));
        System.out.println(ternaryEvaluation(5, 9) + "\n");
        System.out.println(howManyItems(22,1.4,2));
        System.out.println(howManyItems(45, 1.8,1.9));
        System.out.println(howManyItems(100, 2, 2) + "\n");
        System.out.println(factorial(3));
        System.out.println(factorial(5));
        System.out.println(factorial(7) + "\n");
        System.out.println(gcd(48, 18));
        System.out.println(gcd(52, 8));
        System.out.println(gcd(259, 28) + "\n");
        System.out.println(ticketSaler(70, 1500));
        System.out.println(ticketSaler(24, 950));
        System.out.println(ticketSaler(53, 1250) + "\n");
        System.out.println(tables(5, 2));
        System.out.println(tables(31, 20));
        System.out.println(tables(123, 58) + "\n");
        System.out.println("--------------------");
        System.out.println(duplicateChars("Donald")); // 11
        System.out.println(duplicateChars("orange") + "\n");
        System.out.println(getInitials("Ryan Gosling")); // 12
        System.out.println(getInitials("Barack Obama") + "\n");
        int[] array1 = {44, 32, 86, 19}; // 13
        int[] array2 = {22, 50, 16, 63, 31, 55};
        System.out.println(differenceEvenOdd(array1));
        System.out.println(differenceEvenOdd(array2) + "\n");
        int[] array3 = {1, 2, 3, 4, 5}; // 14
        int[] array4 = {1, 2, 3, 4, 6};
        System.out.println(equalToAvg(array3));
        System.out.println(equalToAvg(array4) + "\n");
        int[] array5 = {1, 2, 3}; // 15
        int[] array6 = {3, 3, -2, 408, 3, 31};
        System.out.println(Arrays.toString(indexMult(array5)));
        System.out.println(Arrays.toString(indexMult(array6)) + "\n");
        System.out.println(reverse("Hello World")); // 16
        System.out.println(reverse("The quick brown fox.") + "\n");
        System.out.println(Tribonacci(7)); // 17
        System.out.println(Tribonacci(11) + "\n");
        System.out.println(pseudoHash(5)); // 18
        System.out.println(pseudoHash(10));
        System.out.println(pseudoHash(0) + "\n");
        System.out.println(botHelper("Hello, I’m under the water, he helps me")); // 19
        System.out.println(botHelper("Two pepperoni pizzas please") +  "\n");
        System.out.println(isAnagram("listen", "silent")); // 20
        System.out.println(isAnagram("eleven plus two", "twelve plus one"));
        System.out.println(isAnagram("hello", "world"));
    }
    public static float convert(int x){ // Задача 1
        return x * 3.785f;
    }
    public static int fitCalc(int minutes, int intensity) {
        int calories = 0;
        switch (intensity) {
            case 1:
                calories = minutes * 1;
                break;
            case 2:
                calories = minutes * 2;
                break;
            case 3:
                calories = minutes * 3;
                break;
            default:
                System.out.println("Недопустимое значение");
                return -1;
        }
        return calories;
    }
    public static int containers(int x, int y, int z){ // Задача 3
        return x * 20 + y * 50 + z * 100;
    }
    public static String triangleType(int x, int y, int z){
        if (x + y <= z || x + z <= y || y + z <= x) {
            return "not a triangle";
        }
        if (x == y && y == z) {
            return "isosceles";
        } else if (x == y || y == z || x == z) {
            return "equilateral";
        }
        return "different-sided";
    }
    public static int ternaryEvaluation(int x, int y){ // Задача 5
        return (x > y) ? x : y;
    }
    public static int howManyItems(int x, double y, double z){
        int totalArea = x * 2;
        double itemArea = y * z;
        return (int) (totalArea / itemArea);
    }
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public static int ticketSaler(int tickets, int price) {
        double commissionRate = 0.28;
        double totalRevenue = tickets * price - (tickets * price * commissionRate);
        return (int) Math.round(totalRevenue);
    }
    public static int tables(int students, int tables) {
        int totalSeats = tables * 2;
        int missingSeats = students - totalSeats;

        if (missingSeats > 0) {
            return (missingSeats + 1) / 2;
        } else {
            return 0;
        }
    }
    public static boolean duplicateChars(String str) {
        int length = str.length();

        String lowerCaseStr = str.toLowerCase();

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (lowerCaseStr.charAt(i) == lowerCaseStr.charAt(j)) {
                    return true;
                }
            }
        }

        return false;
    }
    public static String getInitials(String fullName) {
        String[] words = fullName.split(" ");
        StringBuilder initials = new StringBuilder();

        for (String word : words) {
            initials.append(word.charAt(0));
        }

        return initials.toString();
    }
    public static int differenceEvenOdd(int[] arr) {
        int sum_even = 0;
        int sum_odd = 0;

        for (int num : arr) {
            if (num % 2 == 0) {
                sum_even += num;
            } else {
                sum_odd += num;
            }
        }

        return Math.abs(sum_even - sum_odd);
    }
    public static boolean equalToAvg(int[] arr) {
        int sum_elements = 0;

        for (int num : arr) {
            sum_elements += num;
        }
        double avg = (double) sum_elements / arr.length;
        for (int num : arr) {
            if (num == avg) {
                return true;
            }
        }

        return false;
    }
    public static int[] indexMult(int[] arr) {
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            result[i] = i * arr[i];
        }

        return result;
    }
    public static String reverse(String str) {
        StringBuilder reversed = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }

        return reversed.toString();
    }
    public static int Tribonacci(int n) {
        int a = 0, b = 0, c = 1;
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 0;
        } else if (n == 3) {
            return 1;
        }
        for (int i = 4; i <= n; i++) {
            int temp = a + b + c;
            a = b;
            b = c;
            c = temp;
        }

        return c;
    }
    public static String pseudoHash(int length) {
        String chars = "abcdef0123456789";
        StringBuilder result = new StringBuilder();

        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(chars.length());
            result.append(chars.charAt(index));
        }

        return result.toString();
    }
    public static String botHelper(String message) {
        message = message.toLowerCase();

        String[] words = message.split(" ");

        for (String word : words) {
            if ("help".equals(word)) {
                return "Calling for a staff member";
            }
        }

        return "Keep waiting";
    }
    public static boolean isAnagram(String str1, String str2) {
        str1 = str1.replaceAll(" ", "").toLowerCase();
        str2 = str2.replaceAll(" ", "").toLowerCase();

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2);
    }
}
