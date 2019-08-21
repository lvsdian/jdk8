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
- andThen：`function1.compose(function2).apply(a)`会先对a执行function1,再将结果执行function2，function1和function2具体
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
    
 