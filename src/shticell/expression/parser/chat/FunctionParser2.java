package shticell.expression.parser.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Function {
    String name;
    List<Function> arguments;

    Function(String name) {
        this.name = name;
        this.arguments = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Function{name='" + name + "', arguments=" + arguments + '}';
    }
}

public class FunctionParser2 {

    private static final Pattern FUNCTION_PATTERN = Pattern.compile("\\{(\\w+)((?:,\\{[^}]+\\})*)\\}");

    public static Function parseFunction(String input) {
        return parseFunctionInternal(input);
    }

    private static Function parseFunctionInternal(String input) {
        Matcher matcher = FUNCTION_PATTERN.matcher(input);
        if (matcher.matches()) {
            String functionName = matcher.group(1);
            Function function = new Function(functionName);
            String argumentsString = matcher.group(2);
            if (argumentsString != null && !argumentsString.isEmpty()) {
                parseArguments(argumentsString, function);
            }
            return function;
        }
        return null;
    }

    private static void parseArguments(String argumentsString, Function function) {
        Matcher matcher = FUNCTION_PATTERN.matcher(argumentsString);
        while (matcher.find()) {
            Function argFunction = parseFunctionInternal(matcher.group());
            if (argFunction != null) {
                function.arguments.add(argFunction);
            }
        }

        // Handle direct arguments (not nested functions)
        String[] directArgs = argumentsString.split(",(?![^{}]*\\})");
        for (String arg : directArgs) {
            arg = arg.trim();
            if (!arg.isEmpty() && !arg.startsWith("{")) {
                function.arguments.add(new Function(arg));
            }
        }
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