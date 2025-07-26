
import java.util.*;
import java.util.stream.*;

public class StreamExample {
    public static void main(String[] args) {
        // Example for skipUntil
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("skipUntil example:");
        StreamUtils.skipUntil(list.stream(), n -> n > 3)
            .forEach(System.out::println); // Output: 4, 5

        // Example for takeWhileInclusive
        System.out.println("takeWhileInclusive example:");
        StreamUtils.takeWhileInclusive(list.stream(), n -> n < 4)
            .forEach(System.out::println); // Output: 1, 2, 3, 4

        // Example for chunk
        System.out.println("chunk example:");
        StreamUtils.chunk(list.stream(), 3)
            .forEach(System.out::println); // Output: [1, 2, 3], [4, 5]

        // Example for indexFilter
        System.out.println("indexFilter example:");
        List<String> strList = Arrays.asList("a", "b", "c", "d", "e");
        StreamUtils.indexFilter(strList.stream(), (index, value) -> index % 2 == 0 && value.equals("a"))
            .forEach(System.out::println); // Output: a

        // Example for pairWithNext
        System.out.println("pairWithNext example:");
        StreamUtils.pairWithNext(list.stream())
            .forEach(System.out::println); // Output: (1, 2), (2, 3), (3, 4), (4, 5)

        // Example for groupBy
        System.out.println("groupBy example:");
        Map<Character, List<String>> grouped = StreamUtils.groupBy(strList.stream(), s -> s.charAt(0));
        grouped.forEach((k, v) -> System.out.println(k + ": " + v)); 
        // Output: a: [a], b: [b], c: [c], d: [d], e: [e]

        // Example for cycle
        System.out.println("cycle example:");
        StreamUtils.cycle(list.stream(), 2)
            .forEach(System.out::println); // Output: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5

        // Example for zipWith
        System.out.println("zipWith example:");
        List<String> list1 = Arrays.asList("a", "b", "c");
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        StreamUtils.zipWith(list1.stream(), list2.stream())
            .forEach(pair -> System.out.println(pair.getKey() + ": " + pair.getValue())); 
        // Output: a: 1, b: 2, c: 3

        // Example for isSorted
        System.out.println("isSorted example:");
        boolean sorted = StreamUtils.isSorted(list.stream(), Comparator.naturalOrder());
        System.out.println(sorted); // Output: true
    }
}
