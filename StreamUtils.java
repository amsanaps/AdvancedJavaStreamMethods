
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;

public class StreamUtils {
    public static <T> Stream<T> skipUntil(Stream<T> stream, Predicate<T> predicate) {
        Iterator<T> iterator = stream.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (predicate.test(element)) {
                break;
            }
        }
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
    }

    public static <T> Stream<T> takeWhileInclusive(Stream<T> stream, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        Iterator<T> iterator = stream.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            result.add(element);
            if (!predicate.test(element)) {
                break;
            }
        }
        return result.stream();
    }

    public static <T> Stream<List<T>> chunk(Stream<T> stream, int size) {
        List<T> chunk = new ArrayList<>();
        Iterator<T> iterator = stream.iterator();
        
        while (iterator.hasNext()) {
            chunk.add(iterator.next());
            if (chunk.size() == size) {
                yield(chunk);
                chunk = new ArrayList<>();
            }
        }
        
        if (!chunk.isEmpty()) {
            yield(chunk);
        }
    }

    private static <T> void yield(List<T> chunk) {
        System.out.println(chunk);
    }

    public static <T> Stream<T> indexFilter(Stream<T> stream, BiPredicate<Integer, T> predicate) {
        List<T> result = new ArrayList<>();
        Iterator<T> iterator = stream.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (predicate.test(index++, element)) {
                result.add(element);
            }
        }
        return result.stream();
    }

    public static <T> Stream<List<T>> pairWithNext(Stream<T> stream) {
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = stream.iterator();
        
        T prev = null;
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (prev != null) {
                yield(prev, current);
            }
            prev = current;
        }
        return list.stream();
    }

    private static <T> void yield(T first, T second) {
        System.out.println("Pair: (" + first + ", " + second + ")");
    }

    public static <T, K> Map<K, List<T>> groupBy(Stream<T> stream, Function<T, K> classifier) {
        return stream.collect(Collectors.groupingBy(classifier));
    }

    public static <T> Stream<T> cycle(Stream<T> stream, int times) {
        List<T> list = stream.collect(Collectors.toList());
        List<T> result = new ArrayList<>();
        
        for (int i = 0; i < times; i++) {
            result.addAll(list);
        }
        return result.stream();
    }

    public static <T, U> Stream<Map.Entry<T, U>> zipWith(Stream<T> stream1, Stream<U> stream2) {
        Iterator<T> iterator1 = stream1.iterator();
        Iterator<U> iterator2 = stream2.iterator();
        
        List<Map.Entry<T, U>> result = new ArrayList<>();
        
        while (iterator1.hasNext() && iterator2.hasNext()) {
            result.add(new AbstractMap.SimpleEntry<>(iterator1.next(), iterator2.next()));
        }
        return result.stream();
    }

    public static <T> boolean isSorted(Stream<T> stream, Comparator<T> comparator) {
        List<T> list = stream.collect(Collectors.toList());
        for (int i = 1; i < list.size(); i++) {
            if (comparator.compare(list.get(i-1), list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }
}
