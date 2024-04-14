package TESTSAMPLE;

public class DaiStackStimulation {
    public static void main(String[] args) {
        DaiStackStimulation stack = new DaiStackStimulation();
        String[] result = stack.DaiPopAll(new String[] {"Adu","Vai","LOLLLL"});
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

        String[] duc1 = {"d","u","c"};
        String[] dai = {"d","a","i"};
        System.out.println(stack.DaiminOperations(dai, duc1));
    }
    public String[] DaiPopAll(String[] stack) {
        if (stack.length == 0) {
            return stack;
        }
        int n = stack.length;
        String[] alterArray = new String[n];
        for (int i = 0; i < n ; i++) {
            alterArray[i] = stack[n - i - 1];
        }return alterArray;

    }

    public int DaiminOperations(String[] targetStack, String[] currentStack) {
        int start = 0;
        while (start < currentStack.length && start < targetStack.length) {
            if (currentStack[start] != targetStack[start]) {
                break;
            }
            start++;
        }
        return (targetStack.length - start) + (currentStack.length - start);
    }
}
