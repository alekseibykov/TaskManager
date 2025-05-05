package util;

import model.Task;

import java.io.*;
import java.util.Collection;
import java.util.List;

public class FileUtils {

    public static void saveTasks(Collection<Task> tasks, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(List.copyOf(tasks));
        }
    }

    @SuppressWarnings("unchecked")
    public static Collection<Task> loadTasks(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Collection<Task>) ois.readObject();
        }
    }
}
