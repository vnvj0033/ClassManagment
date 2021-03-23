public class ForLoop {

    public static int countChars(String input, char ch) {
        int count;
        int i;
        for (i = 0, count = 0; i < input.length(); i++)
            if (input.charAt(i) == ch)
                count++;
        return count;
    }

    public static boolean isPalindrome(String string) {
        for (int forward = 0, backward = string.length() - 1; forward < string.length(); forward++, backward--)
            if (string.charAt(forward) != string.charAt(backward))
                return false;
        return true;
    }

}
