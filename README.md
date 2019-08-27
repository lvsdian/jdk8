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
    
    
    


        




    
 