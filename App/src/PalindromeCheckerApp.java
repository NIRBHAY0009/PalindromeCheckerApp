import java.util.Stack;

/**
 * PalindromeChecker Service
 * Encapsulates the logic for palindrome validation using a Stack-based approach.
 */
class PalindromeCheckerApp {

    /**
     * Public API to check for palindromes.
     * The caller doesn't need to know HOW it's checked (Encapsulation).
     */
    public boolean checkPalindrome(String text) {
        if (text == null) return false;

        String cleanText = preprocess(text);
        return isStackMatch(cleanText);
    }

    /**
     * Private helper to handle string normalization (Internal Logic).
     */
    private String preprocess(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    /**
     * Internal logic using a Stack Data Structure.
     * Stacks are LIFO (Last-In, First-Out), which naturally reverses data.
     */
    private boolean isStackMatch(String input) {
        Stack<Character> stack = new Stack<>();

        // Push all characters onto the stack
        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        // Pop and compare with the original string
        for (char c : input.toCharArray()) {
            if (c != stack.pop()) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create an instance of the service
        PalindromeChecker checker = new PalindromeChecker();

        // Usage
        String phrase = "No 'x' in Nixon";
        boolean result = checker.checkPalindrome(phrase);

        System.out.println("Is '" + phrase + "' a palindrome? " + result);
    }
}