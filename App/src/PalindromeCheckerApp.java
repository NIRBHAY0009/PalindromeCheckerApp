public class PalindromeCheckerApp {

    public static boolean isPalindrome(String input) {
        if (input == null) return false;

        // 1. Normalize String: Remove non-alphanumeric characters and convert to lowercase
        // "\\s+" would just remove spaces, but "[^a-zA-Z0-9]" handles spaces, tabs, and punctuation.
        String cleanString = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // 2. Apply logic (Two-pointer approach)
        int left = 0;
        int right = cleanString.length() - 1;

        while (left < right) {
            if (cleanString.charAt(left) != cleanString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        String test1 = "A man a plan a canal Panama";
        String test2 = "Race Car";
        String test3 = "Hello World";

        System.out.println("Test 1 ('" + test1 + "'): " + isPalindrome(test1)); // true
        System.out.println("Test 2 ('" + test2 + "'): " + isPalindrome(test2)); // true
        System.out.println("Test 3 ('" + test3 + "'): " + isPalindrome(test3)); // false
    }
}