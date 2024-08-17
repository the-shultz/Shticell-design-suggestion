package shticell.expression.parser.chat;

import java.util.Stack;

public class FunctionParser1 {

    public static Function parseFunction(String input) {
        Stack<Function> stack = new Stack<>();
        StringBuilder buffer = new StringBuilder();
        Function root = null;

        for (char c : input.toCharArray()) {
            if (c == '{') {
                if (buffer.length() > 0) {
                    // Found a function name, create a new Function object
                    String functionName = buffer.toString().trim();
                    Function function = new Function(functionName);
                    if (!stack.isEmpty()) {
                        stack.peek().arguments.add(function);
                    } else {
                        root = function;
                    }
                    stack.push(function);
                    buffer.setLength(0); // Clear the buffer after use
                }
            } else if (c == '}') {
                if (buffer.length() > 0) {
                    // Handle the last argument or function name within this scope
                    String functionName = buffer.toString().trim();
                    Function function = new Function(functionName);
                    if (!stack.isEmpty()) {
                        stack.peek().arguments.add(function);
                    }
                    buffer.setLength(0); // Clear the buffer after use
                }
                if (!stack.isEmpty()) {
                    stack.pop(); // Only pop if the stack is not empty
                }
            } else if (c == ',') {
                if (buffer.length() > 0) {
                    // Handle argument or function name before the comma
                    String functionName = buffer.toString().trim();
                    Function function = new Function(functionName);
                    if (!stack.isEmpty()) {
                        stack.peek().arguments.add(function);
                    }
                    buffer.setLength(0); // Clear the buffer after use
                }
            } else {
                buffer.append(c);
            }
        }

        return root;
    }

    public static void printFunction(Function function, String indent) {
        if (function != null) {
            System.out.println(indent + "Function: " + function.name);
            if (!function.arguments.isEmpty()) {
                System.out.println(indent + "Arguments:");
                for (Function arg : function.arguments) {
                    printFunction(arg, indent + "  ");
                }
            }
        }
    }

    public static void main(String[] args) {
        String input = "{Function1,{Function2,arg1,arg2},{Function3,{Function4,arg3}}}";
        Function rootFunction = parseFunction(input);

        if (rootFunction != null) {
            printFunction(rootFunction, "");
        } else {
            System.out.println("Failed to parse input.");
        }
    }
}
