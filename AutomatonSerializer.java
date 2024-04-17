import java.io.*;

public class AutomatonSerializer {
    public static void serialize(NFA automaton, String filename) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(automaton);
        }
    }

    public static NFA deserialize(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            return (NFA) inputStream.readObject();
        }
    }
}
