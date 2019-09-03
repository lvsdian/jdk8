### 函数式接口(java.lang.FunctionalInterface)：
1. 如果一个接口只有一个抽象方法(其他的方法比如默认方法或与java.lang.Object类相同的方法可以有多个)，那么该接口就是一个函数式接口
2. 如果我们在某个接口上声明了FunctionInterface注解，那么编译器会按照函数式接口的定义来要求该接口，如果不符合就会报错
3. 如果一个接口只有一个抽象方法，但我们并没有给该接口声明@FunctionInterface注解,编译器依旧将该接口看作函数式接口，类似于Override注解，如果不加注解表示不会报错，但加上后增加可读性，如果出错也可以检测出来
4. 函数式接口实例，可通过**lambda表达式**，**方法引用**，**构造方法引用**来实现。
5. jdk8之前出现的函数式接口也支持lambda，比如java.lang.Runnable，在jdk8之前通过匿名内部类来实现  
*参考代码见cn.andios.jdk8包下Test1,Test2,Test3* 
### lambda expression形式与statement形式  
*参考代码见cn.andios.jdk8包下StringComparator* 
### Consumer接口(一个输入参数，无返回值)
*参考代码见cn.andios.jdk8包下Test1*
### Function接口与BiFunction接口
##### Function接口(1个输入参数，输出一个结果值)
- apply：接收一个输入，返回一个结果值，如果是面向对象，输入参数只能是值，这里传递的是行为，即具体怎么操作由用户调用时确定  
*参考代码见cn.andios.jdk8包下FunctionTest*
- compose：`function1.compose(function2).apply(a)`会先对a执行function2,再将结果执行function1，function1和function2具体 
做什么，由用户调用时确定。
- andThen：`function1.andThen(function2).apply(a)`会先对a执行function1,再将结果执行function2，function1和function2具体
做什么，由用户调用时确定。  
##### BiFunction接口(两个输入参数，一个输出结果)
- apply：`biFunction.apply(a,b)`将a,b两个输入参数执行某个操作，怎么操作由用户调用函数时确定。
- andThen：`biFunction.andThen(function).apply(a,b)`将a，b两个参数执行biFunction的apply，再将结果执行function的apply操作。
因为执行biFunction的apply后两个参数变为一个参数，所以biFunction没有compose方法。
*参考代码见cn.andios.jdk8包下FunctionTest2*
- BiFunction实例  
*参考代码见cn.andios.jdk8包下Person,PersonTest*
### Predicate接口(1个参数，返回true或false)
- test：`predicate.test(a)`接收一个参数a，判断这个a是否满足某个条件，返回true或false
- and：`predicate1.and(predicate2).test(a)`,判断两个predicate的test之与，
- or：`predicate1.or(predicate2).test(a)`,判断两个predicate的test之或，
- negate：`predicate.negate().test(integer)`,取test结果的非
- isEqual：isEqual为Predicate接口的静态方法，返回结果为Predicate,例如`Predicate.isEqual("test")`返回Predicate，`Predicate.isEqual("test").test("test")`返回true  
*参考代码见cn.andios.jdk8包下PredicateTest，PredicateTest2*
### Supplier接口(无输入参数，得到一个返回值)
*参考代码见cn.andios.jdk8包下Student,StudentTest*
### BinaryOperator接口(两个输入参数，一个输出结果)
- BinaryOperator接口的两个输入参数和一个输出结果值的类型一致，是BiFunction的一种特例，BinaryOperator接口继承了BiFunction接口
- minBy 传一个比较器，返回较小的那个对象 
- maxBy 传一个比较器，返回较大的那个对象  
*参考代码见cn.andios.jdk8包下BinaryOperatorTest*
### Optional(用来规避NullPointerException)
1. 简介  
    - Optional是一个容器对象，里面可能包含一个非空值，也可能不包含一个非空值，如果包含，`isPresent()`返回true,`get()`返回这个值  
    - Optional由final修饰，是一个[value-based](https://docs.oracle.com/javase/8/docs/api/java/lang/doc-files/ValueBased.html)的类，不建议对它进行`identity-sensitive`的操
  作，比如`reference equality`，`identity hash code`或者`synchronization`    
    - 构造方法私有化，不能访问，只能通过工厂方法来获得实例  
2. 容器实例的创建  
    - empty：返回容器里面为空的Optional实例，  
    - ofNullable：返回容器里面可以为空也可以不为空的Optional实例
    - of：返回容器里面必须不为空的Optional实例
3. 包含一个非空值  
    - ifPresent：如果值存在则使用该值调用Consumer(即把这个值作为Consumer的输入参数，进行操作，不返回结果), 否则不做任何事情。
    - filter：如果值存在则使用该值调用Predicate(即判断这个值是否满足Predicate的test)，返回一个Optional用以描述这个值，否则返回一个空的Optional。  
    - map：如果值存在则使用该值调用Function(即把这个值作为Function的输入参数,得到一个返回结果，如果返回结果不为null，就返回包含这个值的Optional,否则返回空的Optional)
    - flatMap：如果值存在则使用该值调用Function(即把这个值作为Function的输入参数,得到一个返回结果)这个返回结果必须是Optional类型，且不能为null，否则报NullPointerException
    - get：如果值存在则返回该值，否则报`NoSuchElementException`异常
    - isPresent：如果值存在则返回true，否则返回false
4. 不包含一个非空值  
    - orElse(T other)：如果值存在，返回该值，否则返回other 
    - orElseGet：如果值存在，返回该值，否则调用Supplier接口(即没有输入参数但获得一个返回值)返回Supplier的返回结果
    - orElseThrow：如果值存在，返回该值，否则抛出由 Supplier 继承的异常  
*参考代码见cn.andios.jdk8包下OptionalTest,OptionTest2,Company,Employee*
### 方法引用
- 直接访问类或者实例的已经存在的方法或者构造方法，必须是已经存在的方法，用法分以下四类：   
1. 类名::静态方法
2. 类名::实例方法
3. 引用名(对象名)::实例方法
4. 类名::new  (调用构造方法，如果有多个构造方法，编译器会根据参数自动选择调用哪个构造方法)  
*参考代码见cn.andios.jdk8.methodreference包*
### 默认方法
- java8引入默认方法原因：避免对java8之前的代码有影响
- 两个接口拥有相同的默认方法时(比如myMethod())，如果某个类同时实现了这两个接口，那么这个类必须重写这个方法，
如果重写时想用其中的某个接口(比如Interface2)的默认方法作为实现，则用`Interface2.super.myMethod()`即可  
- 一个接口与一个类拥有相同的方法时(比如myMethod())，如果某个类实现了这个接口并且继承了这个类，那么这个类使用的是父类中的实现，
不是接口中的实现。因为类更为具体，接口只类似与一种契约。  
*参考代码见cn.andios.jdk8.defaultmethod包*
### Stream 
- 什么是Stream
    - Stream使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
它将要处理的元素集合看作一种流，流在管道中传输， 并且可以在管道的节点上进行处理，比如筛选，排序，聚合等。  
*创建流参考代码见cn.andios.jdk8.stream包下StreamTest1，StreamTest2，StreamTest6*
    - 一个流管道由一个源 (可以是数组，集合，IO通道等), 0或多个中间操作(将一个流转为另一个流), 一个终止操作(产生最终的结果)组成.  
*参考代码见cn.andios.jdk8.stream包下StreamTest3*
    - Streams为懒加载;对源的计算操作只有在进行终止操作时才会进行, 只有在需要时才会执行.    
*参考代码见cn.andios.jdk8.stream包下StreamTest7*
    - 一个流只能被操作一次.不然会报`java.lang.IllegalStateException`.  
    - 和以前的Collection操作不同， Stream操作还有两个基础的特征：
        - Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 
比如延迟执行(laziness)和短路( short-circuiting)。
        - 内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。
- 常用API  
    - stream转字符串，数组，list，set等  
*参考代码见cn.andios.jdk8.stream包下StreamTest4*
    - map：参数为Function接口，进行映射操作  
    - flatMap：流的扁平化操作，比如`Stream <List<Integer>>`这种数据类型，flatMap会把多个list打通，变成`Stream <Integer>`类型  
*参考代码见cn.andios.jdk8.stream包下StreamTest5，StreamTest11，StreamTest12*
    - 方法链的调用顺序问题  
*参考代码见cn.andios.jdk8.stream包下StreamTest8*
    - 流的短路与并发  
*参考代码见cn.andios.jdk8.stream包下StreamTest9，StreamTest10*
    - 分组与分区  
        - 分组：Collectors.groupingBy
        - 分区：Collectors.partitioningBy，分区是分组的一种特殊情况，分区分为true，false两种情况  
*参考代码见cn.andios.jdk8.stream包下StreamTest13，Student*
### Collector
- Collector<T, A, R>
    - T：进行reduction operation(汇聚操作)的输入元素的类型，即流中每个元素的类型，比如List<Student>，T就是Student.
    - A：汇聚操作的mutable accumulation type()可变累积类型)，可以理解为中间结果的类型,通常作为实现细节而隐藏。
    - R：汇聚操作最终的结果类型。
- Collector是一个mutable reduction operation(可变的汇聚操作)，它将输入参数累积到一个可变的结果容器中(a mutable result container),它会在所有
    元素都处理完毕后，将累积的结果转换为一个最终的表示(这是一个可选操作)。它支持串行并行两种方式。
- mutable reduction operations(可变的汇聚操作)有：累积元素到Collection(集合)里面；用StringBuilder拼接字符串；计算汇总信息比如sum, min, max, 
    or average；根据卖方最大交易额计算pivot table(数据透视图)等。java.util.stream.Collectors类中提供了many common mutable reductions(很多常用的
    可变汇聚操作)的实现。Collectors本身实际上是一个工厂。
- 一个Collector里面有4个函数，它们协同工作将entries(条目)累积到一个可变的结果容器中，并且可选的将这个结果进行一个最终的转换。
    - Supplier<A> supplier()：创建并返回一个新的mutable result container(可变结果容器)。A是结果容器的类型。
    - BiConsumer<A, T> accumulator()：合并一个新的数据元素到结果容器中。A是结果容器的类型，T是待处理元素的类型。
    - BinaryOperator<A> combiner()：与并发相关。将两个结果容器合并成一个。combiner可以将一个参数的状态合并到另一个中并返回，也可以将返回一个新的
        结果容器。比如list1,list2,可以`list1.addAll(list2);return list1;`,也可以`list3.addAll(list1);list3.addAll(list2),return list3;`。
        A是两个中间结果的类型。
    - Function<A, R> finisher()：可选的对这个容器执行一个最终的转换。将中间的累积类型转换为最终的结果类型。A是中间结果类型，R是最终返回的类型。
- Collector有一个characteristics set(即特征的集合，实际是一个叫Characteristics的枚举)，Characteristics枚举表示一个Collector的属性，用于
    optimize reduction implementations(优化汇聚操作的实现)。
- 使用collector来进行汇聚操作的串行实现会用supplier函数创建一个唯一的结果容器,每一个输入元素会调用accumulator函数一次，但一个并行实现会对输入
    进行分区，针对每个区域会创建一个结果容器，累积每个分区的内容到一个subresult(子结果)中，然后用combiner函数将子结果合并为一个最终合并的结果。
- 为了确保串行执行与并行执行产生相同的结果，collector必须满足两个条件identity(同一性) associativity(结合性)。
    - 同一性约束是针对任何部分累积的结果来说(任何一个subresult),将它与an empty result container(一个空的结果容器)进行组合，必须生成一个等价
        的结果。也就是说，对于一个部分累积结果a来说，a是某条线上(比如多线程环境，一个线程就是一条执行线)通过调用accumulator and combiner等 
        操作产生的结果，必须满足 `a==combiner.apply(a, supplier.get())`，supplier.get()产生一个空的结果容器。
    - 结合性指splitting the computation(分割计算)必须产生相同的结果。也就是说，对于任何的输入元素t1,t2，结果r1,r2，在以下的计算中r1应该等于r2。
        ```java
        //result without splitting
        //通过supplier.get()得到a1这个累积结果容器
        A a1 = supplier.get();
        //t1累积到a1中
        accumulator.accept(a1, t1);
        //t2累积到a1中
        accumulator.accept(a1, t2);
        //将a1转换成最终的结果容器r1并返回
        R r1 = finisher.apply(a1);      
  
        // result with splitting
        //第一个线程通过supplier.get()得到a2这个累积结果容器
        A a2 = supplier.get();
        //t1累积到a2中
        accumulator.accept(a2, t1);
        //第二个线程通过supplier.get()得到a3这个累积结果容器
        A a3 = supplier.get();
        //t2累积到a3中
        accumulator.accept(a3, t2);
        //将a2与a3两个同类型的不同对象(两个subresult)合并，再转换成最终的结果容器r2并返回
        R r2 = finisher.apply(combiner.apply(a2, a3));
        ```
- 对于没有 UNORDERED characteristic(无序特性)的collector来说(UNORDERED是Characteristics枚举的一个元素)，两个累积的结果a1与a2如果满足`finisher.apply(a1).equals(finisher.apply(a2))`,
    那么a1与a2等价。对于无序的collector来说，equivalence is relaxed(等价性要求就放松了)，会考虑到顺序上的区别对应的不相等性。比如一个无序的collector累积到一个list中，
    在考虑两个list是否相等时，只考虑包含的元素是否相同，忽略了顺序。
- 基于Collector来implement reduction(实现汇聚)的库,比如`java.util.stream.Stream.collect(java.util.stream.Collector<? super T,A,R>)`，
    必须遵守如下的约定：
    - 传递给accumulator函数的第一个参数，传递给combiner函数的两个参数，传递给finisher函数的参数都必须是上一次调用supplier，accumulator，combiner函数返回的结果类型。
    - 对于实现来说，不应该对supplier,accumulator,combiner函数返回的中间结果进行任何操作，除了将这些中间结果再次传递给accumulator,combiner,finisher
        函数，或者直接将他们返回给汇聚操作的调用者。
    - 如果一个结果被传给combiner 或者 finisher函数，但是the same object(相同的对象)没有从这个函数中返回，那么它就不会再被使用了。即说明在combiner或者finisher
        里面生成了新的对象，之前传递的结果已经用完了，不会再被使用。
    - 一个结果一旦传递给combiner or finisher函数后，就不会再传递给accumulator函数了。
    - 对于非并发的收集器来说，任何从supplier, accumulator,combiner函数中返回的结果一定是serially thread-confined(线程限定，即只在当前线程执行，不会被其他的线程使用)。
        这使得collector可以在并行环境下执行而无需实现任何额外的同步操作。reduction implementation(汇聚操作的实现)必须确保输入被合适的进行分区，各个分区processed in isolation
        (单独的隔离的进行处理)。只有当所有的accumulation完成后才会进行combining。
    - 对于并发的收集器来说，实现可以自由选择implement reduction concurrently(并发实现汇聚操作)，A concurrent reduction(一个并发的汇聚操作)是指,accumulator函数
        在multiple threads(多个线程)里面调用，会使用the same concurrently-modifiable(同一个可以并发修改的)结果容器，而不是保持各个结果独立。一个concurrent reduction
        (并发的汇聚操作)只有在无序时才会(be applied)被应用,要么有Characteristics枚举的UNORDERED，要么原始数据是无序的。
- 除了在java.util.stream.Collectors中预定义的实现之外，也可以用`java.util.stream.Collector.of(...)`静态工厂方法来construct collectors(构建收集器),比如说
    你可以创建一个收集器，将widgets累积到一个TreeSet中：
        ```
Collector<Widget, ?, TreeSet<Widget>> intoSet =
                  Collector.of(TreeSet::new, TreeSet::add,
                               (left, right) -> { left.addAll(right); return left; });
        ```
    - TreeSet::new：对应Supplier<R> supplier，即new一个TreeSet作为结果容器
    - TreeSet::add：BiConsumer<R, T> accumulator，每次调用TreeSet的add方法，将Widget累积到TreeSet容器中。
    - (left, right) -> { left.addAll(right); return left; }：用于多线程处理，将一个TreeSet类型的结果容器全部添加到另一个TreeSet结果容器中，再返回这个结果容器。  
    这个操作还可以通过the predefined collector(预定义的收集器)`java.util.stream.Collectors.toCollection`来实现。
- 使用收集器进行汇聚操作生成的结果与以下等同：
    ```java
    R container = collector.supplier().get();
    for (T t : data)
        collector.accumulator().accept(container, t);
    return collector.finisher().apply(container);
    ```
- 然而库可以自由的对输入进行分区，在每个分区上执行汇聚操作，然后使用combiner函数将各个部分结果进行合并来实现一个并行的汇聚操作，取决于具体的汇聚操作，这种操作
    可能更好可能更差，取决于accumulator 和 combiner函数的the relative cost(相对成本)。说明并行流效率不一定比串行流效率高。
- Collectors(收集器)被设计成composed(可以组合的)，`java.util.stream.Collectors`里面的很多方法都是一个函数，它们接收一个collector并生成一个新的collector,
    比如给定如下collector，它会计算工资的总和(流里面是员工，计算员工的工资总和)。
    ```java
     Collector<Employee, ?, Integer> summingSalaries = Collectors.summingInt(Employee::getSalary))
    ```
    如果我们想要创建一个收集器，他会根据部门对工资进行tabulate(计算，汇总，表格化),我们可以重用上面的逻辑，
    使用`java.util.stream.Collectors.groupingBy(java.util.function.Function<? super T,? extends K>, java.util.stream.Collector<? super T,A,D>)`
    这个方法：
    ```java
    Collector<Employee, ?, Map<Department, Integer>> summingSalariesByDept = Collectors.groupingBy(Employee::getDepartment, summingSalaries);
    ```
- 自定义收集器实现  
*参考代码见cn.andios.jdk8.stream.source包下MyCollectorSetImpl*
- java.util.stream.Collector.Characteristics.**IDENTITY_FINISH**  
    在java.util.stream.ReferencePipeline.collect(java.util.stream.Collector<? super P_OUT,A,R>)中可以看到，如果collector的Characteristics中
    有"IDENTITY_FINISH"，那么finisher函数不会被调用，中间容器A直接被强转为最终容器R，如果没有"IDENTITY_FINISH"，那么会经过finisher方法转化。所以，
    如果我们不能确保中间容器类型到最终容器类型可以强转的话，不能加IDENTITY_FINISH,不然会出错。  
- java.util.stream.Collector.Characteristics.**CONCURRENT**  
    表示收集器是并行的，意味着结果容器可以支持累加器函数并行的被调用，即多个线程同时操作同一个结果容器。如果不加CONCURRENT，那么几个线程就会操作几个结果容器，
    再使用combiner将它们合并。  
*参考代码见cn.andios.jdk8.stream.source包下MyCollectorSetImpl2*
### Collectors
- `java.util.stream.Collectors`是`java.util.stream.Collector`的实现，实现了很多有用的reduction operations(汇聚操作)，比如说，将元素累积到集合中，
    根据various criteria(各种各样的标准)，获取元素的summarizing(摘要)。
- 如下的例子是使用预定义的收集器去实现常用的可变汇聚任务：
    - 将name累积到List中  
    `List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());`
    - 将name累积到TreeSet中  
    `Set<String> set = people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));`
    - 将元素转为字符串，将字符串拼接起来，各个字符串间用逗号分割  
    `String joined = things.stream().map(Object::toString).collect(Collectors.joining(", "));`
    - 计算员工工资总和  
    `int total = employees.stream().collect(Collectors.summingInt(Employee::getSalary)));`
    - 根据部门将员工分组  
    `Map<Department, List<Employee>> byDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));`
    - 根据部门计算工资总数  
    `Map<Department, Integer> totalByDept = employees.stream()..collect(Collectors.groupingBy(Employee::getDepartment,Collectors.summingInt(Employee::getSalary)));`
    - 将学生分为通过和未通过两个部分  
    `Map<Boolean, List<Student>> passingFailing = students.stream().collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));`
- 收集器多级分组分区  
*参考代码见cn.andios.jdk8.stream.source包下StreamTest1,Student*
- java.util.stream.Collectors.**groupingBy(java.util.function.Function<? super T,? extends K>)**
    ```java
        public static <T, K> Collector<T, ?, Map<K, List<T>>>
        groupingBy(Function<? super T, ? extends K> classifier) {
            return groupingBy(classifier, toList());
        }
    ```
    返回值`Collector<T, ?, Map<K, List<T>>>`中，T为元素类型，?代表中间结果类型，由于调用者只关心输入，输出，不关心如何实现，所以这里用?表示，
    Map<K, List<T>>为最终的结果类型。
- java.util.stream.Collectors.**groupingBy(java.util.function.Function<? super T,? extends K>, java.util.stream.Collector<? super T,A,D>)**
    ```java
        public static <T, K, A, D>
        Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier,
                                              Collector<? super T, A, D> downstream) {
            return groupingBy(classifier, HashMap::new, downstream);
        }
    ```
    **接收一个Function和收集器，返回一个收集器，实际是将Function应用到接收的收集器中，将其收集过程进行一系列的转换，再将转换后的收集器返回。**
    - Collector<T, ?, Map<K, D>>：
        - T：元素类型
        - K：classifier(分类器)返回的结果类型，即map得key，根据它进行分组
        - D：返回的map的value的类型
- java.util.stream.Collectors.**groupingBy(java.util.function.Function<? super T,? extends K>, java.util.function.Supplier<M>, java.util.stream.Collector<? super T,A,D>)**
    ```java
        public static <T, K, D, A, M extends Map<K, D>>
        Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier,
                                      Supplier<M> mapFactory,
                                      Collector<? super T, A, D> downstream) {
            //得到downstream的supplier对象                      
            Supplier<A> downstreamSupplier = downstream.supplier();
            //得到downstream的accumulator对象
            BiConsumer<A, ? super T> downstreamAccumulator = downstream.accumulator();
            //构造最终返回的收集器的accumulator对象
            BiConsumer<Map<K, A>, T> accumulator = (m, t) -> {
                //classifier返回结果作为map的key
                K key = Objects.requireNonNull(classifier.apply(t), "element cannot be mapped to a null key");
                //java.util.Map.computeIfAbsent：给定一个键，判断map里面是否有对应的值，如果有直接返回这个值，
                //如果没有，应用一个Function，把键映射成出一个结果，如果这个结果不为空，将键-结果放入map，返回这个结果，如果结果为空，返回空
                //这里作用是根据key判断map里面是否有对应的value,这里的vlaue是容器对象，有的话，取出来，没有，就用downstreamSupplier的容器对象
                A container = m.computeIfAbsent(key, k -> downstreamSupplier.get());
                //将元素t累积到中间结果container中
                downstreamAccumulator.accept(container, t);
            };
            //mapMerger：将右边参数合并到左边参数中，完成两个map的合并
            BinaryOperator<Map<K, A>> merger = Collectors.<K, A, Map<K, A>>mapMerger(downstream.combiner());
            @SuppressWarnings("unchecked")
            //supplier返回的肯定是当前收集器的中间结果类型，根据上面，中间结果类型为Map<K,A>类型，所以这里强转一定成功
            Supplier<Map<K, A>> mangledFactory = (Supplier<Map<K, A>>) mapFactory;
            //包含`IDENTITY_FINISH`，则表示combiner执行后的结果为最终结果，不再应用finisher
            if (downstream.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)) {
                return new CollectorImpl<>(mangledFactory, accumulator, merger, CH_ID);
            }
            else {//自己提供finisher对象
                @SuppressWarnings("unchecked")
                Function<A, A> downstreamFinisher = (Function<A, A>) downstream.finisher();
                Function<Map<K, A>, M> finisher = intermediate -> {
                    //java.util.Map.replaceAll：根据传入的函数将所有的value替换，直到所有的value被替换或者这个函数抛出异常
                    intermediate.replaceAll((k, v) -> downstreamFinisher.apply(v));
                    @SuppressWarnings("unchecked")
                    //M才是最终的结果类型
                    M castResult = (M) intermediate;
                    return castResult;
                };
                return new CollectorImpl<>(mangledFactory, accumulator, merger, finisher, CH_NOID);
            }
        }
    ```
    - 返回一个收集器对T类型的输入元素实现了层叠的group by操作，根据一个分类函数对元素进行分组，然后使用给定的downstream收集器根据给定的key所关联的值进行汇聚操作。由
        收集器生成的map是根据supplier工厂函数创建的。即输入参数中的`Supplier<M> mapFactory`
    - 分类器函数会将元素映射成某个键的类型K(即输入参数中`Function<? super T, ? extends K> classifier`,将T映射成K)，downstream收集器会操作T类型元素生成一个
        D类型结果(即输入参数`Collector<? super T, A, D> downstream`，将T收集到D类型结果容器中)，最终的类型为`Map<K, D>`
    - 比如说，计算每个城市中的人的性姓的集合，而且城市的名称是排序好的：  
        `Map<City, Set<String>> namesByCity = people.stream().collect(groupingBy(Person::getCity, TreeMap::new,mapping(Person::getLastName, toSet())));`
    - 最终返回的collector并不是并发的，对于并行流管道来说，combiner函数会将一个map的key合并到另一个map中，这是成本比较高的一个操作，如果元素顺序的保存对downstream收集器来说
        元素顺序不是必须的话，推荐使用groupingByConcurrent。
- java.util.stream.Collectors.**groupingByConcurrent(java.util.function.Function<? super T,? extends K>, java.util.function.Supplier<M>, java.util.stream.Collector<? super T,A,D>)**
    ```java
        public static <T, K, A, D, M extends ConcurrentMap<K, D>>
        Collector<T, ?, M> groupingByConcurrent(Function<? super T, ? extends K> classifier,
                                                Supplier<M> mapFactory,
                                                Collector<? super T, A, D> downstream) {
            Supplier<A> downstreamSupplier = downstream.supplier();
            BiConsumer<A, ? super T> downstreamAccumulator = downstream.accumulator();
            BinaryOperator<ConcurrentMap<K, A>> merger = Collectors.<K, A, ConcurrentMap<K, A>>mapMerger(downstream.combiner());
            @SuppressWarnings("unchecked")
            Supplier<ConcurrentMap<K, A>> mangledFactory = (Supplier<ConcurrentMap<K, A>>) mapFactory;
            BiConsumer<ConcurrentMap<K, A>, T> accumulator;
            //如果包含CONCURRENT
            if (downstream.characteristics().contains(Collector.Characteristics.CONCURRENT)) {
                accumulator = (m, t) -> {
                    K key = Objects.requireNonNull(classifier.apply(t), "element cannot be mapped to a null key");
                    A resultContainer = m.computeIfAbsent(key, k -> downstreamSupplier.get());
                    downstreamAccumulator.accept(resultContainer, t);
                };
            }
            else {//不包含CONCURRENT，会进行同步操作
                accumulator = (m, t) -> {
                    K key = Objects.requireNonNull(classifier.apply(t), "element cannot be mapped to a null key");
                    A resultContainer = m.computeIfAbsent(key, k -> downstreamSupplier.get());
                    synchronized (resultContainer) {
                        downstreamAccumulator.accept(resultContainer, t);
                    }
                };
            }
    
            if (downstream.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)) {
                return new CollectorImpl<>(mangledFactory, accumulator, merger, CH_CONCURRENT_ID);
            }
            else {
                @SuppressWarnings("unchecked")
                Function<A, A> downstreamFinisher = (Function<A, A>) downstream.finisher();
                Function<ConcurrentMap<K, A>, M> finisher = intermediate -> {
                    intermediate.replaceAll((k, v) -> downstreamFinisher.apply(v));
                    @SuppressWarnings("unchecked")
                    M castResult = (M) intermediate;
                    return castResult;
                };
                return new CollectorImpl<>(mangledFactory, accumulator, merger, finisher, CH_CONCURRENT_NOID);
            }
        }
    ```
    - 使用groupingByConcurrent需要满足两个条件：**CONCURRENT**，**UNORDERED**
### Comparator  
- java.util.Comparator.thenComparing(java.util.Comparator<? super T>)  
    - 当第一个比较器返回两个元素相等时，thenComparing里面的比较器就会用于进一步比较这两个元素的顺序，只有它前面的那个比较器认为两个元素相等，
    thenComparing才会发生作用。
    - 如果第一个比较器是Serializable，那么thenComparing里面的比较器也是Serializable
    - 比如将字符串现根据长度比较，再忽略大小写以自然顺序比较：  
    `Comparator<String> cmp = Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER);`  
*参考代码见cn.andios.jdk8.stream.source包下MyComparatorTest*
### Stream Source
- java.util.stream.**Stream**
    - 流是一个支持串行与并行聚合操作的元素序列，如下实例演示了使用Stream与IntStream进行聚合操作：
        ```java
              int sum = widgets.stream()
                               .filter(w -> w.getColor() == RED)
                               .mapToInt(w -> w.getWeight())
                               .sum();
        ```
        在这个例子中，widgets是一个集合，我们通过`Collection.stream()`创建了一个widget对象流，对他进行过滤生成了一个只包含color为红色的新的Stream，然后将它转为一个
        int值的Stream,这个int值Steam代表了每个红色widget的重量，然后这个流会汇总起来生成一个总的重量。
    - 除了java.util.stream.Stream之外，本身是a stream of object references(一个对象引用流)，还有一些原生特化的比如IntStream，LongStream,DoubleStream，它们也被称为流，
        并且符合流的特征与约束。
    - 为了执行流的计算，流的操作会被组合到stream pipeline(流管道)中，一个流管道由源(可能是an array, a collection, a generator function, an I/O channel等)，一个或多个
        中间操作(将一个流转为另外一个流)，一个终止操作(生成一个结果或副作用)组成。流是延迟的，只有当终止操作被发起时，对源的计算才会开始，源里面的元素只有在需要时才会被消费
    - Collections与流，虽然由相似性，但目标是不同的，集合主要是实现对元素的高效管理与访问。与之相反，流并没有提供直接访问或者操作它们的元素的方式，而关注以声明的方式描述
        它们的源和计算性的操作，这些计算行的操作会被聚合起来应用到源上。然而，如果所提供的流操作并没有提供我们所期望的功能，那么`java.util.stream.BaseStream.iterator` ，
        `java.util.stream.BaseStream.spliterator`就可以用于执行controlled traversal(可控制的遍历).
    - 一个流管道，就像上面实例中的widgets，可以看作是对流的源的查询，除非这个流被显式的设计成可以并发修改的，比如java.util.concurrent.ConcurrentHashMap，否则可能会发生错误。
    - 大多数的流操作都会接收描述用户指定行为的参数，比如上面例子中的lambda表达式`w -> w.getWeight()`传给mapToInt函数。为了保证得到一个正确的结果，传递的lambda
        表达式必须满足以下条件：
        - 必须是non-interfering(互不干扰的)，不修改流的源。
        - 在大多数情况下必须是stateless(无状态的)，结果不应该依赖于流管道执行过程中可能会改变的任何状态。
    - 这些参数都是函数式接口的实例，通常都是lambda表达式或者方法引用，除非特别指名，否则这些参数不能为空。
    - 一个流只能被操作(调用一个中间操作或一个终止操作)一次，这个规则，比如说，"forked" streams(派生的流)相同的源会提供两个或多个pipelines,或者对相同的流执行多次遍历，一个
        流实现如果被检测到重用了，可能会抛出`IllegalStateException`异常。但有些流操作可能会返回它们的接收者而不是新的流对象，这种情况就不会检测到重用了。
    - 流有一个close()方法，并且实现了`java.lang.AutoCloseable`这个接口。但是几乎所有的流实例不需要在使用完后关闭。一般来说，只有流的源是
        IO通道(比如从`java.nio.file.Files.lines(java.nio.file.Path, java.nio.charset.Charset`里返回的)，才是需要关闭的。大多数流的底层都是collections, arrays, 
        or generating functions，并不需要特殊的资源管理。如果一个流需要关闭，那么它可以在try(...)里声明。
    - 流管道可以以串行或并行的方式执行，这种执行模式只是流的一个属性，流创建时会有一个串行或并行执行的初始选择。比如，`java.util.Collection.stream`会创建一个串行流，
        `java.util.Collection.stream`会创建一个并行流。这种执行模式还可以通过`java.util.stream.BaseStream.sequential`或`java.util.stream.BaseStream.parallel`
        方法修改。并且可以通过`java.util.stream.BaseStream.isParallel`方法查询。
- java.util.stream.BaseStream.**onClose**
    - 返回一个等价的流并且附带一个额外的close handler(关闭处理器)，当流的close()方法被调用时关闭处理器会按照被添加的顺序运行，如果最先调用的关闭处理器发生异常，其他关闭
        处理器会正常运行。任何一个关闭处理器抛出异常时，第一个抛出的异常会传递给close()方法的调用者，其他的异常会作为被压制的异常添加到那个异常中，除非剩下的异常中有与第
        一个异常相同的异常，毕竟一个异常不会压制它自己。  
    *参考代码见cn.andios.jdk8.stream.source包下StreamTest2*
    - 这是一个中间操作。
### java.util.Spliterator
- 分割迭代器(Spliterator)是用于对源中的元素进行遍历和分区(分成若干个区域)的一个对象，被分割迭代器所覆盖的源可以是数组，集合，IO通道或者a generator function(生成器函数)。
- 一个分割迭代器可以单独的一个一个的遍历元素，对应方法为`java.util.Spliterator.tryAdvance`，也可以顺序的一块一块的遍历，对应方法为`java.util.Spliterator.forEachRemaining`。
- 一个分割迭代器还可以对它的元素进行分区，对应方法为`java.util.Spliterator.trySplit`,分成另外一个分割迭代器,以并行的操作来使用。使用分割迭代器的操作如果不能分割，或者可以分割
    但分割后很不平衡或者效率非常低时，就无法利用并行的优势了。遍历或者分割都会消耗元素，每个分割迭代器只对一小块有用。
- 分割迭代器会报告一个它的结构、源、元素等特性值的集合，有ORDERED，DISTINCT，SORTED，SIZED，NONNULL，IMMUTABLE，CONCURRENT，SUBSIZED。这些可以被一个分割迭代器的客户端使用来
    控制、具体化或简化计算。比如说，一个Collection的分割迭代器会报告一个SIZED,一个Set的分割迭代器会报告DISTINCT，一个SortedSet的分割迭代器会报告SORTED。Characteristics会作
    为一个简单的位操作(Collector中通过枚举来表示，这里是通过16进制位操作来计算)来报告。  
    有一些限定值会额外的限制方法的行为。比如，如果是ORDERED，那么遍历方法必须遵循它们在文档中定义好的顺序。未来可能会定义一些新的特性，所以不要给没有列出来的值赋予新的含
    义(即这里特性值定义了8个，我们的实现代码中不应该再定义其他的，因为以后的jdk版本可能会定义新的特性值，万一jdk新定义的与我们自己定义的名称相同意义不同就出问题了)
- 一个分割迭代器如果不报告IMMUTABLE或者CONCURRENT就被期望有一个文档化的策略的考量。当分割迭代器绑定到元素的源上时，在绑定之后，要对元素源的结构的修改做出检测。一个延迟绑定的分割
    迭代器会在第一次遍历，第一次分割，或在第一次查询元素大小时绑定到元素的源上，不是在分割迭代器创建的时候就绑定了。一个不是延迟绑定的分割迭代器会在刚创建时或者分割迭代器中任何
    一个方法得到调用时绑定到元素的源上。如果对源的修改发生在绑定之前，那么当分割迭代器遍历时这些修改就会反应出来。当分割迭代器已经被绑定到某个源上，这时再去修改源，就会抛出
    `java.util.ConcurrentModificationException`。如果一个分割迭代器按照这种方式来行事的，就被称为fail-fast(快速失败)。分割迭代器中`forEachRemaining`这个按块遍历的方法
    会优化遍历，在所有元素遍历完后检测结构的修改而不是一个元素一个元素的检查并立即失败。
- 分割迭代器可以通过`java.util.Spliterator.estimateSize`方法对还没有处理的元素进行数量的估算。在理想情况下，如果特性值包含SIZED，那么估算的值一定等于接下来要去遍历的
    元素数量。然而，即便不能精确的知道待遍历元素的数量，一个估算的值对源的操作也是很有帮助的，比如说帮助你确定是否进一步分割或者以串行的方式进行遍历剩下的元素。
- 尽管在并行算法中存在这些显著的功能，分割迭代器并不被要求是线程安全的。相反的，使用分割迭代器实现了并行算法的实现，应该确保分割迭代器一次只能被唯一一个线程使用。这通常可以
    很简单的通过serial thread-confinement(串行线程围栏)来实现，串行线程围栏这样一种模式通常是通过递归解耦的典型并行算法的自然结果。如果初始的线程将分割迭代器交由另外一个线程
    处理时，这种情况下一定要在tryAdvance方法遍历元素开始之前完成，一旦遍历开始再将分割迭代器交由其他线程处理，就不能保证结果的有效性。
- 分割迭代器原生子类型的特化版本由`java.util.Spliterator.OfInt`,`java.util.Spliterator.OfLong`,`java.util.Spliterator.OfDouble`提供.子类型
    中`java.util.Spliterator.tryAdvance`、`java.util.Spliterator.forEachRemaining`的默认实现将原生的值包装成对应类的实例，这种包装相对于原生类型的特化
    可能影响了性能上的优势.为了避免装箱这种操作，相应的基于原生值的方法应该被使用。比如说，`java.util.Spliterator.OfInt.tryAdvance(java.util.function.IntConsumer)`,
    `java.util.Spliterator.OfInt.forEachRemaining(java.util.function.IntConsumer)`应该比`java.util.Spliterator.tryAdvance`,
    `java.util.Spliterator.forEachRemaining`优先使用。使用基于装箱方法的原生值遍历`java.util.Spliterator.tryAdvance`,`java.util.Spliterator.forEachRemaining`
    并不会影响值的顺序，会按照装箱之后的值的顺序进行遍历。
- 分割迭代器就像(java.util.Iterator)迭代器一样，用于遍历源当中的元素，分割迭代器的API除了串行遍历之外，也被设计成并行的高效的遍历操作，通过支持解耦以及single-element iteration
    (单元素的遍历)。此外，通过分割迭代器访问元素的协议被设计成相比于迭代器施加更小的每个元素的负担(即相比于迭代器，分割迭代器的成本更低。)，避免了hasNext()方法与next()方法调
    用的竞争(即原来使用迭代器时，hasNext()与next()配套使用，现在改用分割迭代器的方式，一个方法(tryAdvance)完成两个方法的功能)。
- 对于可变的源来说，在分割迭代器绑定到数据源上到遍历结束之间，如果源的结构被修改(指元素的添加，替换，删除)了，那么任意的不确定的行为可能会发生。比如中，在使用流框架时这种修改
    可能会生成任意的不确定的结果。
- 一个源结构上的修改可以通过如下的几种方式进行管理。
    - 源的结构性不能被改变。
        - 比如说，一个`java.util.concurrent.CopyOnWriteArrayList`的实例是一个不可变的源，根据这个源创建的分割迭代器会报告一个IMMUTABLE的特性值。
    - 源自己管理并发修改。
        - 比如说，`java.util.concurrent.ConcurrentHashMap`的键的集合是一个并发的源,通过这个源创建的分割迭代器会报告一个CONCURRENT的特性值。
    - 可变的源提供一个延迟绑定和快速失败的分割迭代器。
        - 延迟绑定会缩短修改影响计算的时间窗口，快速失败尽最大的努力，在遍历开始后检测到结构的修改并抛出`ConcurrentModificationException`.比如说，ArrayList以及JDK中很
            多其他的非并发的集合的类，都提供延迟绑定的，快速失败的分割迭代器。
    - 可变的源提供非延迟绑定的但快速失败的分割迭代器。
        - 源增加了抛出`ConcurrentModificationException`异常的可能性，因为潜在的修改的窗口被放大了(因为上面一条中说到，延迟绑定会缩短这个时间窗口，这里提供非延迟绑定
            的分割迭代器就不会缩短这个窗口)。
    - 可变的源提供非快速失败的但延迟绑定的分割迭代器。
        - 在遍历开始之后源会有任意的，不确定的行为的风险。
    - 可变的源提供非延迟绑定的并且非快速失败的分割迭代器。
        - 源增加了任意的，不确定的行为的风险，因为创建后可能发生没检测到的修改。
- 这里有个类(并不是特别有用，用来阐述说明)，这个类维护了一个数组，实际的数据是在偶数的位置上存放，不相关的标签数据存放在奇数位置上，这个数组的分割迭代器只关注偶数位置上的数据：
    ```java
    class TaggedArray<T> {
      private final Object[] elements; // immutable after construction
      TaggedArray(T[] data, Object[] tags) {
        int size = data.length;  
        //奇数位置个数应该与偶数位置个数相等
        if (tags.length != size) throw new IllegalArgumentException();
        this.elements = new Object[2 * size];
        for (int i = 0, j = 0; i < size; ++i) {
          elements[j++] = data[i];
          elements[j++] = tags[i];
        }
      }
      public Spliterator<T> spliterator() {
        return new TaggedArraySpliterator<>(elements, 0, elements.length);
      }
      static class TaggedArraySpliterator<T> implements Spliterator<T> {
        private final Object[] array;
        // current index, advanced on split or traversal，即当前索引，在分割或遍历时加1
        private int origin; 
        // one past the greatest index，表示分割迭代器能前进到哪个位置上，
        private final int fence; 
        TaggedArraySpliterator(Object[] array, int origin, int fence) {
          this.array = array; this.origin = origin; this.fence = fence;
        }
        public void forEachRemaining(Consumer<? super T> action) {
          for (; origin < fence; origin += 2)
            action.accept((T) array[origin]);
        }
        public boolean tryAdvance(Consumer<? super T> action) {
          if (origin < fence) {
            action.accept((T) array[origin]);
            //因为不考虑标签数据，所以直接+2
            origin += 2;
            return true;
          }
          else // cannot advance
            return false;
        }
        public Spliterator<T> trySplit() {
          int lo = origin; // divide range in half
          int mid = ((lo + fence) >>> 1) & ~1; // force midpoint to be even
          if (lo < mid) { // split out left half
            origin = mid; // reset this Spliterator's origin
            return new TaggedArraySpliterator<>(array, lo, mid);
          }
          else       // too small to split
            return null;
        }
        public long estimateSize() {
          return (long)((fence - origin) / 2);
        }
        public int characteristics() {
          return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
        }
      }
    }
    ```
- 作为一个并行计算框架的案例，比如`java.util.stream`这个包，会在一个并行计算中使用分割迭代器，这里有一种实现与之相关的并行的forEach的实现方式，它描述了分割子任务的方法
    直到分割后的the estimated amount(估计的数量)足够小以执行串行操作。这里我们假定处理子任务的顺序无关紧要，不同的任务可能会进一步分割，并发处理元素时也以一个不确定的顺序，
    这个例子使用`java.util.concurrent.CountedCompleter`,与其他的并行任务的类用法类似：
    ```java
    static <T> void parEach(TaggedArray<T> a, Consumer<T> action) {
      Spliterator<T> s = a.spliterator();
      long targetBatchSize = s.estimateSize() / (ForkJoinPool.getCommonPoolParallelism() * 8);
      new ParEach(null, s, action, targetBatchSize).invoke();
    }
    static class ParEach<T> extends CountedCompleter<Void> {
      final Spliterator<T> spliterator;
      final Consumer<T> action;
      final long targetBatchSize;
      ParEach(ParEach<T> parent, Spliterator<T> spliterator,
              Consumer<T> action, long targetBatchSize) {
        super(parent);
        this.spliterator = spliterator; this.action = action;
        this.targetBatchSize = targetBatchSize;
      }
      public void compute() {
        Spliterator<T> sub;
        while (spliterator.estimateSize() > targetBatchSize &&
               (sub = spliterator.trySplit()) != null) {
          addToPendingCount(1);
          new ParEach<>(this, sub, action, targetBatchSize).fork();
        }
        spliterator.forEachRemaining(action);
        propagateCompletion();
      }
    }
    ```
- 如果系统布尔值`org.openjdk.java.util.stream.tripwire`被设成true，那么操作原生子类型的特化时进行装箱操作，就会报告一个警告信息。
- java.util.Spliterator.**tryAdvance**
    - 如果有剩余的元素，那么对剩余元素就会执行给定的动作，同时返回true，否则返回false。如果Spliterator是ORDERED,那么动作就会按照顺序执行。由动作抛出来的异常会返回给调用者。
- java.util.Spliterator.**forEachRemaining**
    - 针对剩余的每个元素都执行给定的动作，当前线程中以串行方式执行，直到所有的元素都被处理或这个动作抛出了异常。如果Spliterator是ORDERED,那么动作就会按照顺序执行。由动作抛出来的
        异常会返回给调用者。默认的实现会重复调用`java.util.Spliterator.tryAdvance`直到返回false，在必要时会被重写。
                   
    

    


        




    
 