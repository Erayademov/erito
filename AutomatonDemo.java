import java.io.*;
import java.util.*;

public class AutomatonDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NFA automaton = new NFA();

        while (true) {
            System.out.println("1. Добави състояние");
            System.out.println("2. Добави преход");
            System.out.println("3. Задай начално състояние");
            System.out.println("4. Добави финално състояние");
            System.out.println("5. Сериализирай автомат");
            System.out.println("6. Десериализирай автомат");
            System.out.println("7. Провери дума");
            System.out.println("8. Изход");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Въведете номер на състоянието:");
                    int state = scanner.nextInt();
                    automaton.addState(state);
                    break;
                case 2:
                    System.out.println("Въведете от коe състояние, символ и до коe състояние:");
                    int from = scanner.nextInt();
                    char symbol = scanner.next().charAt(0);
                    int to = scanner.nextInt();
                    automaton.addTransition(from, symbol, to);
                    break;
                case 3:
                    System.out.println("Въведете начално състояние:");
                    int start = scanner.nextInt();
                    automaton.setStartState(start);
                    break;
                case 4:
                    System.out.println("Въведете финално състояние:");
                    int finalState = scanner.nextInt();
                    automaton.addFinalState(finalState);
                    break;
                case 5:
                    System.out.println("Въведете име на файл за сериализация:");
                    String serializeFilename = scanner.next();
                    try {
                        AutomatonSerializer.serialize(automaton, serializeFilename);
                        System.out.println("Автоматът е сериализиран успешно.");
                    } catch (IOException e) {
                        System.err.println("Грешка при сериализация: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Въведете име на файл за десериализация:");
                    String deserializeFilename = scanner.next();
                    try {
                        automaton = AutomatonSerializer.deserialize(deserializeFilename);
                        System.out.println("Автоматът е десериализиран успешно.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println("Грешка при десериализация: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Въведете дума за проверка:");
                    String word = scanner.next();
                    if (automaton.accepts(word)) {
                        System.out.println("Думата е приета от автомата.");
                    } else {
                        System.out.println("Думата не е приета от автомата.");
                    }
                    break;
                case 8:
                    System.out.println("Излизане от програмата...");
                    System.exit(0);
                default:
                    System.out.println("Невалиден избор. Опитайте отново.");
            }
        }
    }
}
