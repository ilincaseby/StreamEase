import commandcontrol.DecideDependingInput;
import io.InputAll;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;

public class Main {
    /**
     * Main method
     * **/
    public static void main(final String[] args) throws IOException {
        File pathToTest = new File(args[0]);
        ObjectMapper objectMapper = new ObjectMapper();
        InputAll input = objectMapper.readValue(pathToTest, InputAll.class);
        ArrayNode output = objectMapper.createArrayNode();
        DecideDependingInput.takeAction(input, output);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}