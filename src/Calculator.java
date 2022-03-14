import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        String result;
        boolean singExit = false;
        boolean signRepeatMenu = false;
        boolean signRepeatOper;
        int choiceMenu;
        int choiceOperation;
        String numText;
        String operation;
        Scanner srcMenu = new Scanner(System.in);
        Scanner srcOperation = new Scanner(System.in);
        Scanner srcExpression = new Scanner(System.in);
        String Expression;
        int a = 0;
        int b = 0;
        // строим меню пока не будет выбран пункт "3. Выход""
        while (!singExit){
            mainMenu();
            numText = srcMenu.nextLine() ;
            // распознование числа
            if (!isNumber(numText)) {
                System.out.println("Не удалось распознать операцию! Повторите выбор:");
                continue;
            }
            choiceMenu = Integer.parseInt(numText);
            signRepeatOper = true;
            // ввод выражения и его вычисление
            if (choiceMenu == 1) {
                boolean signEnterExp = true;
                while (signEnterExp) {
                    String expression = srcExpression.nextLine();
                    String[] expressions = new String[3];
                    expressions = expression.split(" "); // массив из элементов выражения
                    if ((expressions.length < 2)) {
                        System.out.println("Выражение некорректно, повторите ввод:");
                        continue;
                    }
                    operation = expressions[1];
                    // распознование числа
                    if (!isNumber(expressions[0])) {
                        System.out.println("Первый элемент выражения должен быть числом, повторите ввод:");
                        continue;
                    }
                    a = Integer.parseInt(expressions[0]);
                    if (expressions.length > 2) {
                        if (!isNumber(expressions[2])) {
                            System.out.println("Третий элемент выражения должен быть числом, повторите ввод:");
                            continue;
                        }
                        b = Integer.parseInt(expressions[2]);
                    }
                    System.out.println(solutionExample(operation, a, b));
                    signRepeatMenu = true;
                    signEnterExp = false;
                }
            }
            // другая операция с числами из предыдущего выражения
            else if (choiceMenu == 2) {
                while (signRepeatOper) {
                    if (!signRepeatMenu) {
                        System.out.println("Недоступно!");
                        break;
                    }
                    operationMenu();
                    numText = srcOperation.nextLine();
                    // распознование числа
                    if (!isNumber(numText)) {
                        System.out.println("Не удалось распознать операцию! Повторите выбор:");
                        continue;
                    }
                    choiceOperation = Integer.parseInt(numText);
                    switch (choiceOperation) {
                        case 1:
                            operation = "+";
                            break;
                        case 2:
                            operation = "-";
                            break;
                        case 3:
                            operation = "*";
                            break;
                        case 4:
                            operation = "/";
                            break;
                        case 5:
                            operation = "!";
                            break;
                        case 6:
                            operation = "^";
                            break;
                        case 7:
                            operation = "?";
                            break;
                        case 0:
                            signRepeatOper = false;
                            continue;
                        default:
                            System.out.println("Не удалось распознать операцию! Повторите выбор");
                            continue;
                    }
                    System.out.println(solutionExample(operation, a, b));
                }
            }
            // Выход из программы
            else if ( choiceMenu == 3 ) {
                System.out.println("До свидания!");
                singExit = true;
            }
            //
            else {
                System.out.println("Введите номер пункта меню из предложенных вариантов!");
                continue;
            }

            }

        }

    // метод построения меню первого уровня
    public static void mainMenu() {
        System.out.println("1. Ввести пример" + "\n" +
                           "2. Продолжить работать с предыдущим ответом" + "\n" +
                           "3. Выход");
    }

    // метод построения меню Операций над числами
    public static void operationMenu() {
        System.out.println("1. Сложение");
        System.out.println("2. Вычитание");
        System.out.println("3. Умножение");
        System.out.println("4. Деление");
        System.out.println("5. Факториал");
        System.out.println("6. Возведение в степень");
        System.out.println("7. Сравнение");
        System.out.println("0. Назад");

    }
    // функция разбора и вычисления выражения
    public static String solutionExample(String operation, int a, int b) {
        String answer = "";
            answer =  "\"" + operation + "\" - " + a + " " + operation + " " + b + " = " ;
        switch (operation) {
            case "+" :
                answer += addition( a, b );
                break;
            case "-" :
                answer += subtraction( a, b );
                break;
            case "*" :
                answer += multiplication( a, b );
                break;
            case "/" :
                if ( b == 0 ) { answer = "На ноль делить нельзя!"; break;}
                answer += division( a, b );
                break;
            case "^" :
                answer += extent( a, b );
                break;
            case "?" :
                answer += comparison( a, b );
                break;
            case "!" :
                if ( a < 0 ) { answer = "Факториал отрицательного числа не существует"; break; }
                answer = "\"!\" - " + a + "!" + diffFactorial(a) + " = "  + factorial(a);
                break;
            default: answer = "Не удалось распознать операцию!";

        }

        return answer;
    }
    // функция сложения 2-х чисел
    public static int addition(int a, int b) {
        return a+b;
    }

    // функция вычитания одного числа из другого
    public static int subtraction(int a, int b) {
        return a-b;
    }
    // функция умножения 2-х чисел
    public static int multiplication(int a, int b) {
        return a*b;
    }

    // функция деления одного целого числа на другое нацело
    public static int division(int a, int b) {
        return a/b;
    }

    // функция возведения числа в степень
    public static int extent(int a, int b) {
        return (int) Math.pow(a, b);
    }

    // функция сравнения двух чисел
    public static String comparison(int a, int b) {
        return a > b ? ( a + " > " + b ) : ( a < b ? ( a + " < " + b ) : ( a + " = " + b ) );
    }

    // фукция вычисления факториала числа
    public static int factorial(int a) {
        return ( a == 1 || a == 0)  ? 1 : a * factorial(a - 1);
    }

    // фукция формулы вычисления факториала числа
    public static String diffFactorial(int a) {
        if (a <= 1) return "";
        String formula = " = " + Integer.toString(a) ;
        while ( a > 1 ) {
            formula += " * " +  (a - 1);
            a--;
        }
        return formula;
    }
    // метод распознавания числа из строки
    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
