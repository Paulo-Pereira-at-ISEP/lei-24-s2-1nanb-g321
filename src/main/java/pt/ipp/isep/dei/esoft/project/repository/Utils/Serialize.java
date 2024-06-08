package pt.ipp.isep.dei.esoft.project.repository.Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Serialize {

    public final static String FOLDER_PATH = System.getProperty("user.dir") + File.separator + "Data";

    public static <T extends Serializable> T deserialize(String filePath) {
        T obj = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            obj = (T) in.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
        return obj;
    }

    public static <T extends Serializable> void serialize(List<T> obj, String filePath) {
        try {
            if (!Files.isDirectory(Paths.get(FOLDER_PATH))) {
                Files.createDirectories(Paths.get(FOLDER_PATH));
            }
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
                out.writeObject(obj);
            }
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }
    }
}
