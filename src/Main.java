import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static boolean Operador(char c){
        return c == '+' || c=='-' || c=='*' || c=='/';
    }
    private static int Operadores(char operador) {
        return switch (operador) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }
    public static String InfijaASufija(String expresionInfija) {

        Stack<Character> stack = new Stack<>();
        StringBuilder expresionSufija = new StringBuilder();

        for (char token : expresionInfija.toCharArray()){
            if (token == '('){
                stack.push(token);
            } else if (token == ')') {
                while (!stack.isEmpty() && stack.peek() != '('){
                    expresionSufija.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                }
            } else if (Character.isLetterOrDigit(token)) {
                expresionSufija.append(token);
            } else if (Operador(token)) {
                while (!stack.isEmpty() && Operadores(token) <= Operadores(stack.peek())){
                    expresionSufija.append(stack.pop());
                }
                stack.push(token);
            }
        }
        while (!stack.isEmpty()){
            expresionSufija.append(stack.pop());
        }
        return expresionSufija.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese una expresion infija:");
        String expresionInfija = sc.nextLine();

        String expresionSufija = InfijaASufija(expresionInfija);
        System.out.println("Expresión infija: "+ expresionInfija);
        System.out.println("Expresion sufija: "+ expresionSufija);
    }
}
