import java.util.Stack;

class StackImpl {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek()); // what prints?
        System.out.println(stack.pop()); // what prints?
        System.out.println(stack.peek()); // what prints?
    }
}