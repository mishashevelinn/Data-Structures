package Stack;

public class checkBalancedBrackets {
    private final String left_brackets;
    private final String right_brackets;
    private StackList<Character> stack;

    checkBalancedBrackets(int testedStringLenght) {
        left_brackets = "{[(<";
        right_brackets = "}])>";
        stack = new StackList<Character>(testedStringLenght);
    }

    public boolean check(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (in(str.charAt(i), left_brackets))
                stack.push(str.charAt(i));
            if (in(str.charAt(i), right_brackets)) {
                if (stack.isEmpty() || str.charAt(i) != stack.pop())
                    return false;
            }
        }

        return true;
    }

    boolean in(char needle, String haystack) {
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String tested = "< f(x), g(x) > = int_{-/pi)^{/pi}f(x)g(x)dx";
        String tested2 = "{]";
        checkBalancedBrackets brChecker = new checkBalancedBrackets(tested.length());
        System.out.println(brChecker.check(tested2));

    }

}

