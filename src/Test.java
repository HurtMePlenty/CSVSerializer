import java.util.ArrayList;
import java.util.List;

public class Test {
    @CsvField
    String name;
    @CsvField
    String age;

    public static void main(String[] args) {
        Test test1 = new Test();
        test1.name = "qwer1";
        test1.age = "22";

        Test test2 = new Test();
        test2.name = "qwer2";
        test2.age = "33";

        List<Test> testList = new ArrayList<Test>();
        testList.add(test1);
        testList.add(test2);

        System.out.println("GO:");
        try {
            System.out.println(CSVSerializer.serialize(testList, Test.class, ",", ""));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
