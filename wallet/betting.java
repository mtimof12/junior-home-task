import java.util.stream.Stream;
public class Demo {
   public static void main(String[] args){
      Stream.Builder<String> builder = Stream.builder();
      builder.accept("Demo");
      builder.accept("Text");
      Stream<String> str = builder.build();
      str.forEach(System.out::println);
   }
}