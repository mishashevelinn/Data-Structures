package Stack;

public class checkBalancedBrackets {
    private final Character[] open = {'{','[','(','<'};
    private final Character[] close = { '}', ']', ')', '>' };
    private StackList<Integer> stack;

    checkBalancedBrackets(int testedStringLenght) {
        stack = new StackList<Integer>(testedStringLenght);
    }
/*Iterating over a given string and checking:
*   if it is opening bracket, push its corresponding index in array of chars - defined as a field
*   if it is a closing bracket, pop the stack and compare the indexes of current closing bracket with poped. It should fin*/
    public boolean check(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < open.length; j++)
            {
                if(str.charAt(i) == open[j])
                {
                    stack.push(j);
                    break;
                }
                if (str.charAt(i) == close[j])
                {
                    if(stack.isEmpty()) return false;
                    if (stack.pop() == j)
                    {
                        break;
                    }
                    else
                        return false;
                }
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
        String tested = "< f(x), g(x) > = int_{-/pi}^{/pi}f(x)g(x)dx";
        String tested2 = "{d}";
        checkBalancedBrackets brChecker = new checkBalancedBrackets(tested.length());
        System.out.println(brChecker.check(tested2));
        System.out.println();

    }

}

