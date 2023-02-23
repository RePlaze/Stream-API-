package NEW.Collection.Stream;

import java.util.*;
import java.util.Deque;
import java.util.LongSummaryStatistics;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static NEW.Collection.Print.*;

/**
 * Коротко : ✔️Стримы передают элементы из источника (какой-либо структуры данных)
 * через конвейер операций и возвращают некоторое значение, не модифицируя источник ✔️

 * Stream API — это объект для универсальной работы с данными.
 * Мы указываем, какие операции хотим провести, при этом не заботясь о деталях реализации.
 * Например, взять элементы из списка сотрудников, выбрать тех, кто младше 40 лет, отсортировать по фамилии и поместить в новый список.

 * То есть, Stream использует существующие коллекции для получения новых элементов это ни в коем случае не новаяструктура данных.
 * К данным затем применяются операторы. Например, взять лишь некоторые элементы (filter), преобразовать каждый элемент (map), посчитать сумму элементов или объединить всё в один объект (reduce).

 * Операторы можно разделить на две группы:
 * - Промежуточные (intermediate) — обрабатывают поступающие элементы и возвращают стрим. Промежуточных операторов в цепочке обработки элементов может быть много.
 * - Терминальные (terminal) — обрабатывают элементы и завершают работу стрима,
 * так что терминальный оператор в цепочке может быть только один.

 * Основные методы:
 * filter()    -> Фильтрует стрим, принимая только те элементы которые удовлетворяют заданному условию.
 * map()       -> Применяет функцию к каждому элементу стрима и возвращает измененый стрим.
 * limit()     -> Ограничивает размер потока.
 * sort()      -> Подсчитывает количество элементов. ("a","b","c","d") -> 4
 * distinct()  -> Убирает повторяющиеся элементы и возвращаем стрим с уникальными элементами.
 * forEach()   -> Выполняет указанное действие для каждого элемента стрима. (Терминальный).
 * count()     -> Возвращает количество элементов стрима после фильтра
 * counting()  -> Подсчитывает количество элементов.
 * generate()  -> создать (x -> 0) -> 000000...
 * concat()    -> Объединяет два стрима, вначале идут элементы стрима A потом стрима B.
 * iterate()   -> fori for collections
 * skip()      -> Пропустить поток в промежутке  [2-5) -> 2,3,4.
 * peek()      -> Служит для того, чтобы передать элемент куда-нибудь, не разрывая при этом цепочку операторов.
 * takeWhile() -> Возвращает элементы до тех пор, пока они удовлетворяют условию.
 * range()     -> Создаёт стрим из числового промежутка [start..end).
 * findfirst() -> Гарантированно возвращает первый элемент стрима.
 * sum()       -> Возвращает сумму элементов примитивного стрима.
 * Statistic() -> Позволяет собрать статистику а именно: количество элементов,сумму, среднее число,мин и макс элемент.
 * joing()     -> Собирает элементы в единую строку. (можно указать разделитель,префикс и суффикс)
 * sum_avg_var -> Коллектор, который преобразовывает объекты и подсчитывает сумму.
 * partitionBy -> Разбивает поток по какому-либо критерию в одну часть попадают все элементы, которые удовлетворяютусловию
 */


public class FAQ {
    public static void main(String[] args) {
        sum();
    }


    public static void base() {
        IntStream.of( 120, 410, 85, 32, 314, 12 )
                .filter( x -> x < 300 )
                .map( x -> x + 11 )
                .limit( 3 )
                .forEach( System.out::print );
        //   Здесь уже три промежуточных оператора:
        //  - filter — отбирает элементы, значение которых меньше 300,
        //  - map — прибавляет 11 к каждому числу,
        //  - limit — ограничивает количество элементов до 3.
    }


    public static void createStream() {
//        - Стрим из List: list.stream()
//        - Стрим из Map: map.entrySet().stream()
//        - Стрим из массива: Arrays.stream(array)
//        - Стрим из указанных элементов: Stream.of("a", "b", "c")
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

        // Объединяет два стрима так, что вначале идут элементы
        // стрима A, а по его окончанию последуют элементы стрима B.
    }

    public static void builder() {
        // Создаёт мутабельный объект для добавления элементов
        // в стрим без использования какого-либо контейнера для этого.

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
        //Создаёт стрим из числового промежутка [start..end)
        IntStream.range( 0, 10 )
                .forEach( x -> print( x + " " ) );
    }

    public static void filter() {
        // Фильтрует стрим, принимая только те элементы
        // которые удовлетворяют заданному условию.
        Stream.of( 120, 410, 85, 32, 314, 12 )
                .filter( x -> x > 100 )
                .forEach( x -> print( x + " " ) );

        IntStream.range( 0, 10 )
                .filter( x -> x % 3 == 0 )
                .forEach( System.out::println ); // 3,6,9
    }

    public static void map() {
        // Применяет функцию к каждому элементу и затем возвращает стрим
        // в котором элементами будут результаты функции
        // map можно применять для изменения типа элементов.
        Stream.of( "3", "4", "5" )
                .map( Integer::parseInt )
                .map( x -> x + 10 )
                .forEach( x -> print( x + " " ) );
    }

    public static void mapTO() {
     /*
        Специальные операторы для преобразования объектного стрима в примитивный, примитивного в объектный, либо
        примитивного стрима одного типа в примитивный стрим другого.

        Stream.mapToDouble(ToDoubleFunction mapper)
        Stream.mapToInt(ToIntFunction mapper)
        Stream.mapToLong(ToLongFunction mapper)
        IntStream.mapToObj(IntFunction mapper)
        IntStream.mapToLong(IntToLongFunction mapper)
        IntStream.mapToDouble(IntToDoubleFunction mapper)

    */
    }

    public static void limit() {
        //Ограничивает размер потока.
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
        // Убирает повторяющиеся элементы и возвращаем стрим с уникальными элементами
        // Как и в случае с sorted, смотрит, состоит ли уже стрим из уникальных элементов
        // и если это не так, отбирает уникальные и помечает стрим как содержащий уникальные элементы.

        //хорошо использовать вместе с sort()
        Stream.of( 2, 1, 8, 1, 3, 2 )
                .distinct() // 2 1 8 3
                .sorted()  // 1 2 3 8
                .forEach( x -> print( x + " " ) );
    }

    public static void peek() {
        // Выполняет действие над каждым элементом стрима и при этом возвращает стрим с элементами исходного стрима
        // Служит для того, чтобы передать элемент куда-нибудь, не разрывая при этом цепочку операторов
        // (вы же помните, что forEach — после него стрим завершается?), либо для отладки.
        Stream.of( 0, 1, 2, 5 )
                .peek( x -> System.out.format( "num: %d%n", x ) )
                .distinct()
                .peek( x -> System.out.format( "after distinct: %d%n", x ) )
                .map( x -> x + 10 )
                .forEach( x -> System.out.format( "after +10: %d%n", x ) );

    }

    public static void takeWhile() {
        //  Возвращает элементы до тех пор, пока они удовлетворяют условию
        Stream.of( 1, 2, 3, 4, 2, 5 )
                .takeWhile( x -> x < 3 )
                .forEach( System.out::println );
    }

    public static void forEach() {
        // Выполняет указанное действие для каждого элемента стрима.
        Stream.of( 120, 410, 85, 32, 314, 12 )
                .forEach( x -> System.out.format( "%s, ", x ) );
    }

    public static void count() {
        // Возвращает количество элементов стрима
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
