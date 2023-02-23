package NEW.Collection.Stream;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static NEW.Collection.Print.print;
import static NEW.Collection.Print.println;

/**
 * Summary: ✔️ Streams transmit elements from a data source (any data structure)
 * through a pipeline of operations and return a value without modifying the source ✔️
 * <p>
 * The Stream API - is an object for universal data processing. We specify which operations we want to perform,
 * without worrying about the implementation details. For example, to take elements from a list of employees,
 * select those under 40, sort by last name, and place them in a new list.
 * <p>
 * That is, Stream uses existing collections to obtain new elements. It is by no means a new data structure.
 * Operators are then applied to the data. For example, to take only certain elements (filter),
 * transform each element (map), calculate the sum of elements, or combine everything into one object (reduce).
 * <p>
 * Operators can be divided into two groups:
 * Intermediate operators process incoming elements and return a stream. There may be many intermediate operators in a chain of element processing.
 * Terminal operators process elements and terminate the stream, so there can be only one terminal operator in a chain.
 * <p>
 * Key methods:
 * filter() -> Filters the stream, accepting only those elements that meet a given condition.
 * map() -> Applies a function to each element in the stream and returns the modified stream.
 * limit() -> Limits the size of the stream.
 * sort() -> Counts the number of elements. ("a","b","c","d") -> 4
 * distinct() -> Removes duplicate elements and returns a stream of unique elements.
 * forEach() -> Performs the specified action for each element in the stream. (Terminal)
 * count() -> Returns the number of elements in the stream after filtering.
 * counting() -> Counts the number of elements.
 * generate() -> creates (x -> 0) -> 000000...
 * concat() -> Combines two streams, with the elements of stream A followed by those of stream B.
 * iterate() -> fori for collections
 * skip() -> Skips the stream in the range [2-5) -> 2,3,4.
 * peek() -> Allows an element to be passed somewhere without breaking the chain of operators.
 * takeWhile() -> Returns elements as long as they meet a condition.
 * range() -> Creates a stream from a numerical range [start..end).
 * findFirst() -> Guarantees the first element of the stream is returned.
 * sum() -> Returns the sum of elements in a primitive stream.
 * statistics() -> Collects statistics, such as the number of elements, sum, average, min, and max.
 * join() -> Collects elements into a single string. (You can specify a separator, prefix, and suffix)
 * sum_avg_var -> A collector that transforms objects and calculates the sum.
 * partitionBy -> Divides the stream by some criteria, with all elements that meet the condition falling into one part.
 */


public class Stream_API {
    public static void main(String[] args) {
        sum();
    }

    public static void base() {
        IntStream.of( 120, 410, 85, 32, 314, 12 )
                .filter( x -> x < 300 )
                .map( x -> x + 11 )
                .limit( 3 )
                .forEach( System.out::print );
        // Here are already three intermediate operators:
        // - filter — selects elements whose value is less than 300,
        // - map — adds 11 to each number,
        // - limit — limits the number of elements to 3.
    }

    public static void createStream() {
        // Stream from List: list.stream()
        // Stream from Map: map.entrySet().stream()
        // Stream from array: Arrays.stream(array)
        // Stream from specified elements: Stream.of("a", "b", "c")
    }

    public static void generate() {
        Stream.generate( () -> "" )
                .limit( 100 )
                .forEach( System.out::print );
    }

    public static void iterate() {
        Stream.iterate( 0, x -> x + 6 ) // start with 0 and go x ->
                .limit( 10 )
                .forEach( System.out::println );
    }

    public static void concat() {
        Stream.concat(
                        Stream.of( 1, 2, 3 ),
                        Stream.of( 4, 5, 6 ) )
                .forEach( System.out::println );

        // Concatenates two streams so that the elements of
        // Stream A come first, followed by the elements of Stream B.
    }

    public static void builder() {
        // Creates a mutable object for adding elements to a stream
        // without using any container for this purpose.

        Stream.Builder<Integer> streamBuider = Stream.<Integer>builder()
                .add( 0 );
        for (int i = 2; i <= 8; i += 2)
            streamBuider.accept( i );
        streamBuider
                .add( 10 )
                .build()
                .forEach( x -> print( x + " " ) );
    }

    public static void range() {
        // Creates a stream from a numeric range [start..end)
        IntStream.range( 0, 10 )
                .forEach( x -> print( x + " " ) );
    }

    public static void filter() {
        // Filters the stream by accepting only those elements
        // that satisfy the given condition.
        Stream.of( 120, 410, 85, 32, 314, 12 )
                .filter( x -> x > 100 )
                .forEach( x -> print( x + " " ) );

        IntStream.range( 0, 10 )
                .filter( x -> x % 3 == 0 )
                .forEach( System.out::println ); // 3,6,9
    }

    public static void map() {
        // Applies a function to each element and then returns a stream
        // in which the elements will be the results of the function.
        // Map can be used to change the type of elements.
        Stream.of( "3", "4", "5" )
                .map( Integer::parseInt )
                .map( x -> x + 10 )
                .forEach( x -> print( x + " " ) );
    }

    public static void mapTO() {
/*
Special operators for converting an object stream to a primitive one, a primitive one to an object one,
or a primitive stream of one type to a primitive stream of another.

scss
Copy code
     Stream.mapToDouble(ToDoubleFunction mapper)
     Stream.mapToInt(ToIntFunction mapper)
     Stream.mapToLong(ToLongFunction mapper)
     IntStream.mapToObj(IntFunction mapper)
     IntStream.mapToLong(IntToLongFunction mapper)
     IntStream.mapToDouble(IntToDoubleFunction mapper)

 */
    }

    public static void limit() {
// Limits the size of the stream.
        Stream.of( 120, 410, 85, 32, 314, 12 )
                .limit( 5 )
                .forEach( System.out::println );
    }

    public static void skip() {
        IntStream.range( 0, 10 ) // 0 to 9
                .skip( 2 ) // skip 0,1
                .limit( 5 ) // only 5 el
                .forEach( System.out::println ); // 2 3 4 5 6
    }

    public static void sort() {
        Stream.of( 120, 410, 85, 32, 314, 12 )
                .sorted()
                .forEach( x -> print( x + " " ) );
    }

    public static void distinct() {
        // Removes duplicate elements and returns a stream with unique elements.
        // Like with sorted, it checks if the stream already consists of unique elements
        // and if not, selects the unique ones and marks the stream as containing unique elements.

        // good to use together with sort()
        Stream.of( 2, 1, 8, 1, 3, 2 )
                .distinct() // 2 1 8 3
                .sorted()   // 1 2 3 8
                .forEach( x -> print( x + " " ) );
    }

    public static void peek() {
        // Performs an action on each element of the stream and returns a stream with the elements of the original stream
        // Used to pass the element somewhere without breaking the chain of operators
        // (remember that forEach - after it, the stream ends?), or for debugging.
        Stream.of( 0, 1, 2, 5 )
                .peek( x -> System.out.format( "num: %d%n", x ) )
                .distinct()
                .peek( x -> System.out.format( "after distinct: %d%n", x ) )
                .map( x -> x + 10 )
                .forEach( x -> System.out.format( "after +10: %d%n", x ) );

    }

    public static void takeWhile() {
    // Returns elements as long as they satisfy the condition
        Stream.of( 1, 2, 3, 4, 2, 5 )
                .takeWhile( x -> x < 3 )
                .forEach( System.out::println );
    }

    public static void forEach() {
        // Performs the specified action for each element of the stream.
        Stream.of( 120, 410, 85, 32, 314, 12 )
                .forEach( x -> System.out.format( "%s, ", x ) );
    }

    public static void count() {
        // Returns the number of elements in the stream.
        long X = Stream.of( 0, 2, 9, 13, 5, 11 )
                .filter( x -> x < 10 )
                .sorted()
                .peek( x -> System.out.format( String.valueOf( x ) ) )
                .count();
        println( "\ncount: " + X );
    }

    public static void Object_toArray() {
        // Возвращает нетипизированный массив с элементами стрима.
        String[] elements = Stream.of( "a", "b", "c", "d" )
                .toArray( x -> new String[x] );
        println( Arrays.toString( elements ) );
    }

    public static void findfirst() {
        // Гарантированно возвращает первый элемент стрима
        int firstSeq = IntStream.range( 4, 65536 )
                .findFirst()
                .getAsInt(); // 4
    }

    public static void sum() {
        // Возвращает сумму элементов примитивного стрима.
        // Для IntStream результат будет типа int
        // для LongStream — long DoubleStream — double.
        long result = LongStream.range( 1, 10 )
                .sum(); // count 1+..+9
        println( result );
    }

    public static void Statistics() {
        // Полезный метод примитивных стримов.
        // Позволяет собрать статистику о числовой последовательности стрима
        // а именно: количество элементов, их сумму, среднее арифметическое, минимальный и максимальный элемент.
        LongSummaryStatistics stats = LongStream.range( 2, 16 )
                .summaryStatistics();
        System.out.format( "  count: %d%n", stats.getCount() );
        System.out.format( "    sum: %d%n", stats.getSum() );
        System.out.format( "average: %.1f%n", stats.getAverage() );
        System.out.format( "    min: %d%n", stats.getMin() );
        System.out.format( "    max: %d%n", stats.getMax() );
    }

    public static void toCollection() {
        // Собирает элементы в заданную коллекцию
        // Если нужно конкретно указать какой List, Set
        // или другую коллекцию мы хотим использовать, то этот метод поможет.
        Deque<Integer> deque = Stream.of( 1, 2, 3, 4, 5 )
                .collect( Collectors.toCollection( ArrayDeque::new ) );

        Set<Integer> set = Stream.of( 1, 2, 3, 4, 5 )
                .collect( Collectors.toCollection( LinkedHashSet::new ) );
    }

    public static void joing() {
        // Собирает элементы, реализующие интерфейс CharSequence, в единую строку.
        // Дополнительно можно указать разделитель, а также префикс и суффикс для всей последовательности.
        String s1, s2, s3;

        s1 = Stream.of( "a", "b", "c", "d" )
                .collect( Collectors.joining() );
        println( s1 );

        s2 = String.join( "-", "a", "b", "c", "d" );
        println( s2 );

        s3 = Stream.of( "a", "b", "c", "d" )
                .collect( Collectors.joining( " -> ", "[ ", " ]" ) );
        println( s3 );
    }

    public static void sum_avg_var() {
        // Коллектор, который преобразовывает объекты
        // в int/long/double и подсчитывает сумму.

        // int sum
        Integer sum = Stream.of( "1", "2", "3", "4" )
                .collect( Collectors.summingInt( Integer::parseInt ) );
        println( sum );

        // double average
        Double average = Stream.of( "1", "2", "3", "4" )
                .collect( Collectors.averagingInt( Integer::parseInt ) );
        println( average );
    }

    public static void counting() {
        // Подсчитывает количество элементов.
        Long count = Stream.of( "1", "2", "3", "4" )
                .collect( Collectors.counting() );
        println( count );
    }

    public static void partitioningBy() {
        // Разбивает последовательность элементов по какому-либо критерию
        // В одну часть попадают все элементы, которые удовлетворяют переданному условию
        // во вторую — все, которые не удовлетворяют.
        Map<Boolean, List<String>> map1 = Stream.of( "ab", "c", "def", "gh", "ijk", "l", "mnop" )
                .collect( Collectors.partitioningBy( s -> s.length() <= 2 ) );
        map1.entrySet().forEach( x -> print( x + " " ) );
    }
}
