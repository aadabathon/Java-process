import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class StreamsDemo {

    public static void main(String[] args) throws Exception {
        
        Stream.of(3, 6, 4, 7, 6, 5)
                .filter( (number) -> number < 6 )
                .map( (number) -> number * 2 )
                .forEach( (number) -> System.out.println(number) );

        Files.lines( Paths.get("data.txt") )
                .filter( (line) -> !line.trim().equals("") )
                .map( (line) -> line.toUpperCase() )
                .forEach( (line) -> System.out.println(line) );

        String result =
        List.of("one", "two", "three").stream()
                .map( (el) -> el.toUpperCase() )
                .reduce("list: ", (acc, newVal) -> acc + " " + newVal);

        System.out.println(result);

    }

}
