import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Task5 {
    private static final Map<String, String> timeZones = new HashMap<>();
    static {
        timeZones.put("Los Angeles", "-08:00");
        timeZones.put("New York", "-05:00");
        timeZones.put("Caracas", "-04:30");
        timeZones.put("Buenos Aires", "-03:00");
        timeZones.put("London", "Z");
        timeZones.put("Rome", "+01:00");
        timeZones.put("Moscow", "+03:00");
        timeZones.put("Tehran", "+03:30");
        timeZones.put("New Delhi", "+05:30");
        timeZones.put("Beijing", "+08:00");
        timeZones.put("Canberra", "+10:00");
    }

    public static void main(String[] args) {
        System.out.println(sameLetterPattern("ABAB", "CDCD")); // 51
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD") + "\n");

        System.out.println(spiderVsFly("H4", "B4")); // 52
        System.out.println(spiderVsFly("A4", "H2"));
        System.out.println(spiderVsFly("A2", "H4") + "\n");

        System.out.println(digitsCount(4666)); // 53
        System.out.println(digitsCount(544));
        System.out.println(digitsCount(121317));
        System.out.println(digitsCount(0));
        System.out.println(digitsCount(12345));
        System.out.println(digitsCount(1289396387328L) + "\n");

        System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster")); // 54
        System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant"));
        System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed") + "\n");

        System.out.println(sumsUp(new int[]{1, 2, 3, 4, 5})); // 55
        System.out.println(sumsUp(new int[]{1, 2, 3, 7, 9}));
        System.out.println(sumsUp(new int[]{10, 9, 7, 2, 8}));
        System.out.println(sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7}) + "\n");

        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"})); // 56
        System.out.println(takeDownAverage(new String[]{"10%"}));
        System.out.println(takeDownAverage(new String[]{"53%", "79%"}) + "\n");

        System.out.println(caesarCipher("encode", "hello world", 3)); // 57
        System.out.println(caesarCipher("decode", "EPQSWX PEWX XEWO!", 4) + "\n");

        System.out.println(setSetup(5, 3)); // 58
        System.out.println(setSetup(7, 3) + "\n");

        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra")); // 59
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing") + "\n");

        System.out.println(isNew(3)); // 60
        System.out.println(isNew(30));
        System.out.println(isNew(321));
        System.out.println(isNew(130));

    }

    public static boolean sameLetterPattern(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        Map<Character, Character> patternMap = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(i);

            if (patternMap.containsKey(ch1)) {
                if (patternMap.get(ch1) != ch2) {
                    return false;
                }
            }
            else {
                if (patternMap.containsValue(ch2)) {
                    return false;
                }
                patternMap.put(ch1, ch2);
            }
        }
        return true;
    }

    private static final String RADIALS = "ABCDEFGH";
    public static String spiderVsFly(String spider, String fly) {
        int spiderRadial = RADIALS.indexOf(spider.charAt(0));
        int spiderRing = Character.getNumericValue(spider.charAt(1));
        int flyRadial = RADIALS.indexOf(fly.charAt(0));
        int flyRing = Character.getNumericValue(fly.charAt(1));

        StringBuilder path = new StringBuilder();
        path.append(spider);

        int angleDistance = Math.min(Math.abs(spiderRadial - flyRadial), RADIALS.length() - Math.abs(spiderRadial - flyRadial));
        if (angleDistance >= 3 || spiderRing == 0 || flyRing == 0) {
            for (int i = spiderRing - 1; i > 0; i--) {
                path.append("-").append(RADIALS.charAt(spiderRadial)).append(i);
            }
            path.append("-A0");
            for (int i = 1; i <= flyRing; i++) {
                path.append("-").append(RADIALS.charAt(flyRadial)).append(i);
            }
        }   else {
            if (spiderRadial == flyRadial) {
                for (int i = spiderRing - 1; i >= flyRing; i--) {
                    path.append("-").append(RADIALS.charAt(spiderRadial)).append(i);
                }
            }   else {
                int ringChange = spiderRing > flyRing ? -1 : 1;
                for (int ring = spiderRing; ring != flyRing; ring += ringChange) {
                    path.append("-").append(RADIALS.charAt(spiderRadial)).append(ring + ringChange);
                }
                int radialChange = spiderRadial < flyRadial ? 1 : -1;
                if (Math.abs(spiderRadial - flyRadial) > RADIALS.length() / 2) {
                    radialChange = -radialChange;
                }
                for (int radial = spiderRadial; radial != flyRadial; radial = (radial + radialChange + RADIALS.length()) % RADIALS.length()) {
                    path.append("-").append(RADIALS.charAt((radial + radialChange + RADIALS.length()) % RADIALS.length())).append(flyRing);
                }
            }
        }
        return path.toString();
    }

    public static int digitsCount(long n) {

        if (n >= 0 && n <= 9) {
            return 1;
        }

        return 1 + digitsCount(n / 10);
    }

    public static int totalPoints(String[] guesses, String word) {
        int totalPoints = 0;
        for (String guess : guesses) {
            if (isValidWord(guess, word)) {
                totalPoints += getWordPoints(guess, word);
            }
        }
        return totalPoints;
    }

    private static boolean isValidWord(String guess, String word) {
        Map<Character, Integer> letterCounts = new HashMap<>();
        for (char c : word.toCharArray()) {
            letterCounts.put(c, letterCounts.getOrDefault(c, 0) + 1);
        }

        for (char c : guess.toCharArray()) {
            if (!letterCounts.containsKey(c) || letterCounts.get(c) == 0) {
                return false;
            }
            letterCounts.put(c, letterCounts.get(c) - 1);
        }
        return true;
    }

    private static int getWordPoints(String guess, String word) {
        if (guess.length() == 3) {
            return 1;
        } else if (guess.length() == 4) {
            return 2;
        } else if (guess.length() == 5) {
            return 3;
        } else if (guess.length() == 6) {
            if (guess.equals(word)) {
                return 54;
            }

            Map<Character, Integer> guessCounts = new HashMap<>();
            Map<Character, Integer> wordCounts = new HashMap<>();
            for (char c : guess.toCharArray()) {
                guessCounts.put(c, guessCounts.getOrDefault(c, 0) + 1);
            }
            for (char c : word.toCharArray()) {
                wordCounts.put(c, wordCounts.getOrDefault(c, 0) + 1);
            }
            if (guessCounts.equals(wordCounts)) {
                return 54;
            }
        }
        return 0;
    }


    public static List<List<Integer>> sumsUp(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> pairs = new ArrayList<>();
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == 8) {
                pairs.add(Arrays.asList(arr[left], arr[right]));
                left++;
                right--;
            }
            else if (sum < 8) {
                left++;
            }
            else {
                right--;
            }
        }

        return pairs;
    }

    public static String takeDownAverage(String[] scores) {
        double currentAverage = Arrays.stream(scores)
                .mapToInt(score -> Integer.parseInt(score.replace("%", "")))
                .average()
                .orElse(0);

        double newAverage = currentAverage - 5;

        int n = scores.length;
        double requiredScore = newAverage * (n + 1) - currentAverage * n;

        requiredScore = Math.max(0, requiredScore);
        return String.format("%d%%", Math.round(requiredScore));
    }

    public static String caesarCipher(String mode, String message, int shift) {
        StringBuilder result = new StringBuilder();
        if (mode.equals("decode")) {
            shift = 26 - (shift % 26);
        }

        for (char character : message.toCharArray()) {
            if (character >= 'A' && character <= 'Z') {
                char shiftedChar = (char) ((character - 'A' + shift) % 26 + 'A');
                result.append(shiftedChar);
            }
            else if (character >= 'a' && character <= 'z') {
                char shiftedChar = (char) (((character - 'a') + shift) % 26 + 'A');
                result.append(shiftedChar);
            }
            else {
                result.append(character);
            }
        }

        return result.toString();
    }

    public static int setSetup(int n, int k) {
        return factorial(n) / factorial(n - k);
    }

    private static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static String timeDifference(String cityA, String timestamp, String cityB) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse(timestamp, inputFormatter);

        ZonedDateTime zonedDateTimeA = ZonedDateTime.of(localDateTime, ZoneOffset.of(timeZones.get(cityA)));
        ZonedDateTime zonedDateTimeB = zonedDateTimeA.withZoneSameInstant(ZoneOffset.of(timeZones.get(cityB)));

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
        return zonedDateTimeB.format(outputFormatter);
    }


    public static boolean isNew(int number) {
        if (number < 10) {
            return true;
        }

        String numberStr = Integer.toString(number);
        char[] numberChars = numberStr.toCharArray();
        Arrays.sort(numberChars);

        for (int i = 1; i < number; i++) {
            String smallerNumberStr = Integer.toString(i);
            char[] smallerNumberChars = smallerNumberStr.toCharArray();
            Arrays.sort(smallerNumberChars);

            if (Arrays.equals(numberChars, smallerNumberChars)) {
                return false;
            }
        }

        return true;
    }
}

