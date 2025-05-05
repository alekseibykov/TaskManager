package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import model.Task;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    public static void saveToJson(Collection<Task> tasks, String filename) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), tasks);
    }

    public static List<Task> loadFromJson(String filename) throws IOException {
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, Task.class);
        return mapper.readValue(new File(filename), listType);
    }
}
