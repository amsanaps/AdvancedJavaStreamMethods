# Advanced Custom Stream Methods in Java
In Java 8, the Stream API allows functional-style operations on collections of data. However, sometimes the built-in methods don’t cover every use case. This project introduces custom advanced stream methods to fill those gaps and make stream processing more flexible.

*Available Methods
Here’s a brief overview of the custom stream methods available in this repository:

skipUntil(Predicate): Skip elements until a given condition is true.

takeWhileInclusive(Predicate): Take elements until the first failure, including the element that fails.

chunk(int size): Split the stream into chunks of a specified size.

indexFilter(BiPredicate): Filter elements based on both their index and value.

pairWithNext(): Create sliding pairs of consecutive elements.

groupBy(Function): Group elements based on a given function.

cycle(int): Repeat the stream elements n times.

zipWith(): Merge two streams element-wise.

isSorted(): Check if the stream is sorted.
