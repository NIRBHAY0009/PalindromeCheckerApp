import java.util.*;

// --- UC12: Strategy Pattern Setup ---

interface PalindromeCheckerApp {
    boolean isPalindrome(String text);
}

class StackStrategy implements PalindromeStrategy {
    @Override
    public boolean isPalindrome(String text) {
        if (text == null) return false;
        String clean = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Stack<Character> stack = new Stack<>();
        for (char c : clean.toCharArray()) stack.push(c);
        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) reversed.append(stack.pop());
        return clean.equals(reversed.toString());
    }
}

class DequeStrategy implements PalindromeStrategy {
    @Override
    public boolean isPalindrome(String text) {
        if (text == null) return false;
        String clean = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : clean.toCharArray()) deque.addLast(c);
        while (deque.size() > 1) {
            if (!deque.removeFirst().equals(deque.removeLast())) return false;
        }
        return true;
    }
}

// --- UC13: Main Application & Performance Logic ---

public class UseCase13PalindromeCheckerApp {

    public static void main(String[] args) {
        System.out.println("=== UC13: Palindrome Performance Comparison ===");

        // Generate a very large palindrome for stress testing (100,000 characters)
        String base = "racecar";
        StringBuilder largeBuilder = new StringBuilder();
        for (int i = 0; i < 20000; i++) {
            largeBuilder.append(base);
        }
        String largeInput = largeBuilder.toString();

        System.out.println("Testing with string length: " + largeInput.length() + " characters.\n");

        // Strategies to compare
        PalindromeStrategy stackStr = new StackStrategy();
        PalindromeStrategy dequeStr = new DequeStrategy();

        // Perform measurements
        long stackTime = measurePerformance(stackStr, largeInput);
        long dequeTime = measurePerformance(dequeStr, largeInput);

        // Display Results
        displayResults("Stack Strategy", stackTime);
        displayResults("Deque Strategy", dequeTime);

        // Conclusion
        String winner = (stackTime < dequeTime) ? "Stack Strategy" : "Deque Strategy";
        System.out.println("Winner for this run: " + winner);
    }

    /**
     * Captures execution time using System.nanoTime()
     */
    private static long measurePerformance(PalindromeStrategy strategy, String input) {
        // Warm up (to let JVM JIT optimize)
        strategy.isPalindrome(input);

        long startTime = System.nanoTime();
        strategy.isPalindrome(input);
        long endTime = System.nanoTime();

        return (endTime - startTime);
    }

    private static void displayResults(String name, long nanoTime) {
        double milliseconds = nanoTime / 1_000_000.0;
        System.out.printf("%-20s | Time: %10d ns (%.4f ms)%n", name, nanoTime, milliseconds);
    }
}