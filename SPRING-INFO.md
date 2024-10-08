# Spring IoC Container and Beans

The `org.springframework.beans` and `org.springframework.context` packages are the basis 
for Spring Framework’s IoC container. 
The `BeanFactory` interface provides an advanced configuration mechanism capable of managing any type of object.
`ApplicationContext` is a sub-interface of BeanFactory. It adds:

Easier integration with Spring’s AOP features

Message resource handling (for use in internationalization)

Event publication

Application-layer specific contexts such as the WebApplicationContext for use in web applications.

In short, the BeanFactory provides the configuration framework and basic functionality, and the ApplicationContext adds 
more enterprise-specific functionality. 

# Container overview

The `org.springframework.context.ApplicationContext1` interface represents the Spring IoC container and is 
responsible for instantiating, configuring, and assembling the beans. The container gets its instructions on the components 
to instantiate, configure, and assemble by reading configuration metadata. The configuration metadata can be represented 
as annotated component classes, configuration classes with factory methods, or external XML files or Groovy scripts. 
With either format, you may compose your application and the rich interdependencies between those components.

Several implementations of the ApplicationContext interface are part of core Spring. In stand-alone applications, it is 
common to create an instance of `AnnotationConfigApplicationContext` or `ClassPathXmlApplicationContext`.

In most application scenarios, explicit user code is not required to instantiate one or more instances of a Spring IoC container. 
For example, in a plain web application scenario, a simple boilerplate web descriptor XML in the web.xml file of the application 
suffices. In a Spring Boot scenario, the application context is implicitly bootstrapped for you based on common setup conventions.

# Configuration Metadata

The Spring IoC container consumes a form of configuration metadata. This configuration metadata represents how you,
as an application developer, tell the Spring container to instantiate, configure, and assemble the components in your application.

The Spring IoC container itself is totally decoupled from the format in which this configuration metadata is actually written.
These days, many developers choose Java-based configuration for their Spring applications:

`Annotation-based configuration:` define beans using annotation-based configuration metadata on your application’s component classes.

`Java-based configuration:` define beans external to your application classes by using Java-based configuration classes.
To use these features, see the `@Configuration`, `@Bean`, `@Import`, and `@DependsOn` annotations.


# Bean Overview

A Spring IoC container manages one or more beans. These beans are created with the configuration metadata that you supply 
to the container (for example, in the form of XML <bean/> definitions).

Within the container itself, these bean definitions are represented as BeanDefinition objects, which contain (among other information) 
the following metadata:

- A package-qualified class name: typically, the actual implementation class of the bean being defined.


- Bean behavioral configuration elements, which state how the bean should behave in the container (scope, lifecycle 
callbacks, and so forth).


- References to other beans that are needed for the bean to do its work. These references are also called 
collaborators or dependencies.


- Other configuration settings to set in the newly created object — for example, the size limit of the pool or the 
number of connections to use in a bean that manages a connection pool.

In addition to bean definitions that contain information on how to create a specific bean, the ApplicationContext implementations
also permit the registration of existing objects that are created outside the container (by users). This is done by accessing
the ApplicationContext’s BeanFactory through the `getBeanFactory() method`, which returns the `DefaultListableBeanFactory` implementation.
`DefaultListableBeanFactory` supports this registration through the `registerSingleton(..)` and `registerBeanDefinition(..)` methods.
However, typical applications work solely with beans defined through regular bean definition metadata.

# Overriding Beans
Bean overriding is happening when a bean is registered using an identifier that is already allocated. While bean overriding is possible,
it makes the configuration harder to read and this feature will be deprecated in a future release.

To disable bean overriding altogether, you can set the `allowBeanDefinitionOverriding` flag to `false` on the `ApplicationContext` before
it is refreshed. In such setup, an exception is thrown if bean overriding is used.

By default, the container logs every bean overriding at `INFO` level so that you can adapt your configuration accordingly.
While not recommended, you can silence those logs by setting the `allowBeanDefinitionOverriding` flag to `true`.

If you use Java Configuration, a corresponding `@Bean` method always silently overrides a scanned bean class with the same
component name as long as the return type of the `@Bean` method matches that bean class. This simply means that the container
will call the `@Bean` factory method in favor of any pre-declared constructor on the bean class.

# Naming Beans
Every bean has one or more identifiers. These identifiers must be unique within the container that hosts the bean.
A bean usually has only one identifier. However, if it requires more than one, the extra ones can be considered aliases.

You are not required to supply a name or an id for a bean. If you do not supply a name or id explicitly, the container 
generates a unique name for that bean.

# Naming Beans

Every bean has one or more identifiers. These identifiers must be unique within the container that hosts the bean. A bean usually has only one identifier. However, if it requires more than one, the extra ones can be considered aliases.

# Instantiating Beans

A bean definition is essentially a recipe for creating one or more objects. The container looks at the recipe for a named bean when asked and uses the configuration metadata encapsulated by that bean definition to create (or acquire) an actual object.

# Dependency Injection

Dependency injection (DI) is a process whereby objects define their dependencies (that is, the other objects with which they work) only through constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is constructed or returned from a factory method. The container then injects those dependencies when it creates the bean. This process is fundamentally the inverse (hence the name, Inversion of Control) of the bean itself controlling the instantiation or location of its dependencies on its own by using direct construction of classes or the Service Locator pattern.

# Constructor-based Dependency Injection

Constructor-based DI is accomplished by the container invoking a constructor with a number of arguments, each representing a dependency.
```java
public class SimpleMovieLister {

	// the SimpleMovieLister has a dependency on a MovieFinder
	private final MovieFinder movieFinder;

	// a constructor so that the Spring container can inject a MovieFinder
	public SimpleMovieLister(MovieFinder movieFinder) {
		this.movieFinder = movieFinder;
	}

	// business logic that actually uses the injected MovieFinder is omitted...
}
```

# Constructor Argument Resolution

Constructor argument resolution matching occurs by using the argument’s type. If no potential ambiguity exists in the constructor arguments of a bean definition, the order in which the constructor arguments are defined in a bean definition is the order in which those arguments are supplied to the appropriate constructor when the bean is being instantiated. Consider the following class:

```java
package x.y;

public class ThingOne {

	public ThingOne(ThingTwo thingTwo, ThingThree thingThree) {
		// ...
	}
}
```
Assuming that the ThingTwo and ThingThree classes are not related by inheritance, no potential ambiguity exists.

# Setter-based Dependency Injection

Setter-based DI is accomplished by the container calling setter methods on your beans after invoking a no-argument constructor or a no-argument static factory method to instantiate your bean.

The following example shows a class that can only be dependency-injected by using pure setter injection. This class is conventional Java. It is a POJO that has no dependencies on container specific interfaces, base classes, or annotations.

```java
public class SimpleMovieLister {

	// the SimpleMovieLister has a dependency on the MovieFinder
	private MovieFinder movieFinder;

	// a setter method so that the Spring container can inject a MovieFinder
	public void setMovieFinder(MovieFinder movieFinder) {
		this.movieFinder = movieFinder;
	}

	// business logic that actually uses the injected MovieFinder is omitted...
}
```

The ApplicationContext supports constructor-based and setter-based DI for the beans it manages. It also supports setter-based DI after some dependencies have already been injected through the constructor approach. You configure the dependencies in the form of a BeanDefinition, which you use in conjunction with PropertyEditor instances to convert properties from one format to another. However, most Spring users do not work with these classes directly (that is, programmatically) but rather with XML bean definitions, annotated components (that is, classes annotated with @Component, @Controller, and so forth), or @Bean methods in Java-based @Configuration classes. These sources are then converted internally into instances of BeanDefinition and used to load an entire Spring IoC container instance.

# Constructor-based or setter-based DI?

Since you can mix constructor-based and setter-based DI, it is a good rule of thumb to use constructors for mandatory dependencies and setter methods or configuration methods for optional dependencies. Note that use of the @Autowired annotation on a setter method can be used to make the property be a required dependency; however, constructor injection with programmatic validation of arguments is preferable.

The Spring team generally advocates constructor injection, as it lets you implement application components as immutable objects and ensures that required dependencies are not null. Furthermore, constructor-injected components are always returned to the client (calling) code in a fully initialized state. As a side note, a large number of constructor arguments is a bad code smell, implying that the class likely has too many responsibilities and should be refactored to better address proper separation of concerns.

Setter injection should primarily only be used for optional dependencies that can be assigned reasonable default values within the class. Otherwise, not-null checks must be performed everywhere the code uses the dependency. One benefit of setter injection is that setter methods make objects of that class amenable to reconfiguration or re-injection later. Management through JMX MBeans is therefore a compelling use case for setter injection.

Use the DI style that makes the most sense for a particular class. Sometimes, when dealing with third-party classes for which you do not have the source, the choice is made for you. For example, if a third-party class does not expose any setter methods, then constructor injection may be the only available form of DI.

# Dependency Resolution Process

The container performs bean dependency resolution as follows:

- The ApplicationContext is created and initialized with configuration metadata that describes all the beans. Configuration metadata can be specified by XML, Java code, or annotations.

- For each bean, its dependencies are expressed in the form of properties, constructor arguments, or arguments to the static-factory method (if you use that instead of a normal constructor). These dependencies are provided to the bean, when the bean is actually created.

- Each property or constructor argument is an actual definition of the value to set, or a reference to another bean in the container.

- Each property or constructor argument that is a value is converted from its specified format to the actual type of that property or constructor argument. By default, Spring can convert a value supplied in string format to all built-in types, such as int, long, String, boolean, and so forth.

The Spring container validates the configuration of each bean as the container is created. However, the bean properties themselves are not set until the bean is actually created. Beans that are singleton-scoped and set to be pre-instantiated (the default) are created when the container is created. Scopes are defined in Bean Scopes. Otherwise, the bean is created only when it is requested. Creation of a bean potentially causes a graph of beans to be created, as the bean’s dependencies and its dependencies' dependencies (and so on) are created and assigned. Note that resolution mismatches among those dependencies may show up late — that is, on first creation of the affected bean.

>### Circular dependencies
>
>If you use predominantly constructor injection, it is possible to create an unresolvable circular dependency scenario.
>
>For example: Class A requires an instance of class B through constructor injection, and class B requires an instance of class A through constructor injection. If you configure beans for classes A and B to be injected into each other, the Spring IoC container detects this circular reference at runtime, and throws a BeanCurrentlyInCreationException.
>
>One possible solution is to edit the source code of some classes to be configured by setters rather than constructors. Alternatively, avoid constructor injection and use setter injection only. In other words, although it is not recommended, you can configure circular dependencies with setter injection.
>
>Unlike the typical case (with no circular dependencies), a circular dependency between bean A and bean B forces one of the beans to be injected into the other prior to being fully initialized itself (a classic chicken-and-egg scenario).

You can generally trust Spring to do the right thing. It detects configuration problems, such as references to non-existent beans and circular dependencies, at container load-time. Spring sets properties and resolves dependencies as late as possible, when the bean is actually created. This means that a Spring container that has loaded correctly can later generate an exception when you request an object if there is a problem creating that object or one of its dependencies — for example, the bean throws an exception as a result of a missing or invalid property. This potentially delayed visibility of some configuration issues is why ApplicationContext implementations by default pre-instantiate singleton beans. At the cost of some upfront time and memory to create these beans before they are actually needed, you discover configuration issues when the ApplicationContext is created, not later. You can still override this default behavior so that singleton beans initialize lazily, rather than being eagerly pre-instantiated.

If no circular dependencies exist, when one or more collaborating beans are being injected into a dependent bean, each collaborating bean is totally configured prior to being injected into the dependent bean. This means that, if bean A has a dependency on bean B, the Spring IoC container completely configures bean B prior to invoking the setter method on bean A. In other words, the bean is instantiated (if it is not a pre-instantiated singleton), its dependencies are set, and the relevant lifecycle methods (such as a configured init method or the InitializingBean callback method) are invoked.


# Bean Scopes

When you create a bean definition, you create a recipe for creating actual instances of the class defined by that bean definition. The idea that a bean definition is a recipe is important, because it means that, as with a class, you can create many object instances from a single recipe.

You can control not only the various dependencies and configuration values that are to be plugged into an object that is created from a particular bean definition but also control the scope of the objects created from a particular bean definition. This approach is powerful and flexible, because you can choose the scope of the objects you create through configuration instead of having to bake in the scope of an object at the Java class level. Beans can be defined to be deployed in one of a number of scopes. The Spring Framework supports six scopes, four of which are available only if you use a web-aware ApplicationContext.

>**Singleton:** (Default) Scopes a single bean definition to a single object instance for each Spring IoC container.
>
>Only one shared instance of a singleton bean is managed, and all requests for beans with an ID or IDs that match that bean definition result in that one specific bean instance being returned by the Spring container.
>
>To put it another way, when you define a bean definition and it is scoped as a singleton, the Spring IoC container creates exactly one instance of the object defined by that bean definition. This single instance is stored in a cache of such singleton beans, and all subsequent requests and references for that named bean return the cached object.

>**Prototype:** Scopes a single bean definition to any number of object instances. The non-singleton prototype scope of bean deployment results in the creation of a new bean instance every time a request for that specific bean is made. That is, the bean is injected into another bean or you request it through a getBean() method call on the container. As a rule, you should use the prototype scope for all stateful beans and the singleton scope for stateless beans.
>
>In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean. The container instantiates, configures, and otherwise assembles a prototype object and hands it to the client, with no further record of that prototype instance. Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype beans hold. To get the Spring container to release resources held by prototype-scoped beans, try using a custom bean post-processor which holds a reference to beans that need to be cleaned up.
>
>In some respects, the Spring container’s role in regard to a prototype-scoped bean is a replacement for the Java new operator. All lifecycle management past that point must be handled by the client.

>**Request:** Scopes a single bean definition to the lifecycle of a single HTTP request. That is, each HTTP request has its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware Spring ApplicationContext.

>**Session:** Scopes a single bean definition to the lifecycle of an HTTP Session. Only valid in the context of a web-aware Spring ApplicationContext.

>**Application:** Scopes a single bean definition to the lifecycle of a ServletContext. Only valid in the context of a web-aware Spring ApplicationContext.

>**Websocket:** Scopes a single bean definition to the lifecycle of a WebSocket. Only valid in the context of a web-aware Spring ApplicationContext.


## Singleton:
Only one shared instance of a singleton bean is managed, and all requests for beans with an ID or IDs that match that bean definition result in that one specific bean instance being returned by the Spring container.

To put it another way, when you define a bean definition and it is scoped as a singleton, the Spring IoC container creates exactly one instance of the object defined by that bean definition. This single instance is stored in a cache of such singleton beans, and all subsequent requests and references for that named bean return the cached object.

## Prototype:

Scopes a single bean definition to any number of object instances. The non-singleton prototype scope of bean deployment results in the creation of a new bean instance every time a request for that specific bean is made. That is, the bean is injected into another bean or you request it through a getBean() method call on the container. As a rule, you should use the prototype scope for all stateful beans and the singleton scope for stateless beans.

In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean. The container instantiates, configures, and otherwise assembles a prototype object and hands it to the client, with no further record of that prototype instance. Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype beans hold. To get the Spring container to release resources held by prototype-scoped beans, try using a custom bean post-processor which holds a reference to beans that need to be cleaned up.

In some respects, the Spring container’s role in regard to a prototype-scoped bean is a replacement for the Java new operator. All lifecycle management past that point must be handled by the client.

## Singleton Beans with Prototype-bean Dependencies

When you use singleton-scoped beans with dependencies on prototype beans, be aware that dependencies are resolved at instantiation time. Thus, if you dependency-inject a prototype-scoped bean into a singleton-scoped bean, a new prototype bean is instantiated and then dependency-injected into the singleton bean. The prototype instance is the sole instance that is ever supplied to the singleton-scoped bean.

However, suppose you want the singleton-scoped bean to acquire a new instance of the prototype-scoped bean repeatedly at runtime. You cannot dependency-inject a prototype-scoped bean into your singleton bean, because that injection occurs only once, when the Spring container instantiates the singleton bean and resolves and injects its dependencies.

## Request, Session, Application, and WebSocket Scopes

The request, session, application, and websocket scopes are available only if you use a web-aware Spring ApplicationContext implementation (such as XmlWebApplicationContext). If you use these scopes with regular Spring IoC containers, such as the ClassPathXmlApplicationContext, an IllegalStateException that complains about an unknown bean scope is thrown.

## Initial Web Configuration

To support the scoping of beans at the request, session, application, and websocket levels (web-scoped beans), some minor initial configuration is required before you define your beans. (This initial setup is not required for the standard scopes: singleton and prototype.)

How you accomplish this initial setup depends on your particular Servlet environment.

If you access scoped beans within Spring Web MVC, in effect, within a request that is processed by the Spring DispatcherServlet, no special setup is necessary. DispatcherServlet already exposes all relevant state.

If you use a Servlet web container, with requests processed outside of Spring’s DispatcherServlet (for example, when using JSF), you need to register the org.springframework.web.context.request.RequestContextListener ServletRequestListener. This can be done programmatically by using the WebApplicationInitializer interface. Alternatively, add the following declaration to your web application’s web.xml file:
```xml
<web-app>
	...
	<listener>
		<listener-class>
			org.springframework.web.context.request.RequestContextListener
		</listener-class>
	</listener>
	...
</web-app>
```
Alternatively, if there are issues with your listener setup, consider using Spring’s RequestContextFilter. The filter mapping depends on the surrounding web application configuration, so you have to change it as appropriate. The following listing shows the filter part of a web application:

```xml
<web-app>
	...
	<filter>
		<filter-name>requestContextFilter</filter-name>
		<filter-class>org.springframework.web.filter.RequestContextFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>requestContextFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	...
</web-app>
```
DispatcherServlet, RequestContextListener, and RequestContextFilter all do exactly the same thing, namely bind the HTTP request object to the Thread that is servicing that request. This makes beans that are request- and session-scoped available further down the call chain.


## Request scope

The Spring container creates a new instance of the LoginAction bean by using the loginAction bean definition for each and every HTTP request. That is, the loginAction bean is scoped at the HTTP request level. You can change the internal state of the instance that is created as much as you want, because other instances created from the same loginAction bean definition do not see these changes in state. They are particular to an individual request. When the request completes processing, the bean that is scoped to the request is discarded.

When using annotation-driven components or Java configuration, the @RequestScope annotation can be used to assign a component to the request scope. The following example shows how to do so:

```java
@RequestScope
@Component
public class LoginAction {
	// ...
}
```

## Session Scope

Consider the following XML configuration for a bean definition:

The Spring container creates a new instance of the UserPreferences bean by using the userPreferences bean definition for the lifetime of a single HTTP Session. In other words, the userPreferences bean is effectively scoped at the HTTP Session level. As with request-scoped beans, you can change the internal state of the instance that is created as much as you want, knowing that other HTTP Session instances that are also using instances created from the same userPreferences bean definition do not see these changes in state, because they are particular to an individual HTTP Session. When the HTTP Session is eventually discarded, the bean that is scoped to that particular HTTP Session is also discarded.

When using annotation-driven components or Java configuration, you can use the @SessionScope annotation to assign a component to the session scope.

```java
@SessionScope
@Component
public class UserPreferences {
	// ...
}
```

## Application Scope

Consider the following XML configuration for a bean definition:

The Spring container creates a new instance of the AppPreferences bean by using the appPreferences bean definition once for the entire web application. That is, the appPreferences bean is scoped at the ServletContext level and stored as a regular ServletContext attribute. This is somewhat similar to a Spring singleton bean but differs in two important ways: It is a singleton per ServletContext, not per Spring ApplicationContext (for which there may be several in any given web application), and it is actually exposed and therefore visible as a ServletContext attribute.

When using annotation-driven components or Java configuration, you can use the @ApplicationScope annotation to assign a component to the application scope. The following example shows how to do so:

```java
@ApplicationScope
@Component
public class AppPreferences {
	// ...
}
```

## WebSocket Scope

WebSocket scope is associated with the lifecycle of a WebSocket session and applies to STOMP over WebSocket applications, see WebSocket scope for more details.

# Lifecycle Callbacks

```@PostConstruct``` and ```@PreDestroy``` annotations are generally considered best practice for receiving lifecycle callbacks in a modern Spring application.

Be aware that ```@PostConstruct``` and initialization methods in general are executed within the container’s singleton creation lock. The bean instance is only considered as fully initialized and ready to be published to others after returning from the @PostConstruct method. Such individual initialization methods are only meant for validating the configuration state and possibly preparing some data structures based on the given configuration but no further activity with external bean access. Otherwise there is a risk for an initialization deadlock.

# Customizing Beans by Using a BeanPostProcessor

The ```BeanPostProcessor``` interface defines callback methods that you can implement to provide your own (or override the container’s default) instantiation logic, dependency resolution logic, and so forth. If you want to implement some custom logic after the Spring container finishes instantiating, configuring, and initializing a bean, you can plug in one or more custom BeanPostProcessor implementations.

You can configure multiple BeanPostProcessor instances, and you can control the order in which these BeanPostProcessor instances run by setting the order property. You can set this property only if the BeanPostProcessor implements the Ordered interface. If you write your own BeanPostProcessor, you should consider implementing the Ordered interface, too. For further details, see the javadoc of the BeanPostProcessor and Ordered interfaces.



The ```org.springframework.beans.factory.config.BeanPostProcessor``` interface consists of exactly two callback methods. When such a class is registered as a post-processor with the container, for each bean instance that is created by the container, the post-processor gets a callback from the container both before container initialization methods (such as InitializingBean.afterPropertiesSet() or any declared init method) are called, and after any bean initialization callbacks. The post-processor can take any action with the bean instance, including ignoring the callback completely. A bean post-processor typically checks for callback interfaces, or it may wrap a bean with a proxy. Some Spring AOP infrastructure classes are implemented as bean post-processors in order to provide proxy-wrapping logic.

An ApplicationContext automatically detects any beans that are defined in the configuration metadata that implement the BeanPostProcessor interface. The ApplicationContext registers these beans as post-processors so that they can be called later, upon bean creation. Bean post-processors can be deployed in the container in the same fashion as any other beans.

Note that, when declaring a BeanPostProcessor by using an @Bean factory method on a configuration class, the return type of the factory method should be the implementation class itself or at least the org.springframework.beans.factory.config.BeanPostProcessor interface, clearly indicating the post-processor nature of that bean. Otherwise, the ApplicationContext cannot autodetect it by type before fully creating it. Since a BeanPostProcessor needs to be instantiated early in order to apply to the initialization of other beans in the context, this early type detection is critical.

# Injection with @Resource
Spring also supports injection by using the JSR-250 @Resource annotation (jakarta.annotation.Resource) on fields or bean property setter methods. This is a common pattern in Jakarta EE: for example, in JSF-managed beans and JAX-WS endpoints. Spring supports this pattern for Spring-managed objects as well.

@Resource takes a name attribute. By default, Spring interprets that value as the bean name to be injected. In other words, it follows by-name semantics, as demonstrated in the following example:

```java
public class SimpleMovieLister {

	private MovieFinder movieFinder;

	@Resource(name="myMovieFinder") 
	public void setMovieFinder(MovieFinder movieFinder) {
		this.movieFinder = movieFinder;
	}
}
```

# Basic Concepts: @Bean and @Configuration

The central artifacts in Spring’s Java configuration support are **@Configuration**-annotated classes and @Bean-annotated methods.

The **@Bean** annotation is used to indicate that a method instantiates, configures, and initializes a new object to be managed by the Spring IoC container.

Annotating a class with @Configuration indicates that its primary purpose is as a source of bean definitions. Furthermore, @Configuration classes let inter-bean dependencies be defined by calling other @Bean methods in the same class. The simplest possible @Configuration class reads as follows:

```java
@Configuration
public class AppConfig {

	@Bean
	public MyServiceImpl myService() {
		return new MyServiceImpl();
	}
}
```

# Instantiating the Spring Container by Using AnnotationConfigApplicationContext

The following sections document Spring’s AnnotationConfigApplicationContext, introduced in Spring 3.0. This versatile ApplicationContext implementation is capable of accepting not only **@Configuration** classes as input but also plain **@Component** classes and classes annotated with JSR-330 metadata.

When **@Configuration** classes are provided as input, the @Configuration class itself is registered as a bean definition and all declared @Bean methods within the class are also registered as bean definitions.

When **@Component** and JSR-330 classes are provided, they are registered as bean definitions, and it is assumed that DI metadata such as @Autowired or @Inject are used within those classes where necessary.

## Simple Construction

```java
public static void main(String[] args) {
	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	MyService myService = ctx.getBean(MyService.class);
	myService.doStuff();
}
```

As mentioned earlier, AnnotationConfigApplicationContext is not limited to working only with @Configuration classes. Any @Component or JSR-330 annotated class may be supplied as input to the constructor, as the following example shows:

```java
public static void main(String[] args) {
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MyServiceImpl.class, Dependency1.class, Dependency2.class);
	MyService myService = ctx.getBean(MyService.class);
	myService.doStuff();
}
```

## Enabling Component Scanning with scan(String…​)

To enable component scanning, you can annotate your @Configuration class as follows:

```java
@Configuration
@ComponentScan(basePackages = "com.acme") 
public class AppConfig  {
	
}
```

# Declaring a Bean
To declare a bean, you can annotate a method with the @Bean annotation. You use this method to register a bean definition within an ApplicationContext of the type specified as the method’s return value. By default, the bean name is the same as the method name. The following example shows a @Bean method declaration:

```java
@Configuration
public class AppConfig {

	@Bean
	public TransferServiceImpl transferService() {
		return new TransferServiceImpl();
	}
}
```

# Bean Dependencies

A @Bean-annotated method can have an arbitrary number of parameters that describe the dependencies required to build that bean. For instance, if our TransferService requires an AccountRepository, we can materialize that dependency with a method parameter, as the following example shows:

```java
@Configuration
public class AppConfig {

	@Bean
	public TransferService transferService(AccountRepository accountRepository) {
		return new TransferServiceImpl(accountRepository);
	}
}
```

# Specifying Bean Scope

Spring includes the @Scope annotation so that you can specify the scope of a bean.

## Using the @Scope Annotation

You can specify that your beans defined with the @Bean annotation should have a specific scope. You can use any of the standard scopes specified in the Bean Scopes section.

The default scope is singleton, but you can override this with the @Scope annotation, as the following example shows:

```java
@Configuration
public class MyConfiguration {

	@Bean
	@Scope("prototype")
	public Encryptor encryptor() {
		// ...
	}
}
```

# Customizing Bean Naming
By default, configuration classes use a @Bean method’s name as the name of the resulting bean. This functionality can be overridden, however, with the name attribute, as the following example shows:

```java
@Configuration
public class AppConfig {

	@Bean("myThing")
	public Thing thing() {
		return new Thing();
	}
}
```

# Bean Aliasing
As discussed in Naming Beans, it is sometimes desirable to give a single bean multiple names, otherwise known as bean aliasing. The name attribute of the @Bean annotation accepts a String array for this purpose. The following example shows how to set a number of aliases for a bean:

```java
@Configuration
public class AppConfig {

	@Bean({"dataSource", "subsystemA-dataSource", "subsystemB-dataSource"})
	public DataSource dataSource() {
		// instantiate, configure and return DataSource bean...
	}
}
```

# Composing Java-based Configurations
Spring’s Java-based configuration feature lets you compose annotations, which can reduce the complexity of your configuration.

## Using the @Import Annotation
Much as the <import/> element is used within Spring XML files to aid in modularizing configurations, the @Import annotation allows for loading @Bean definitions from another configuration class, as the following example shows:

```java

@Configuration
public class ConfigA {

	@Bean
	public A a() {
		return new A();
	}
}


@Configuration
@Import(ConfigA.class)
public class ConfigB {

	@Bean
	public B b() {
		return new B();
	}
}

```
Now, rather than needing to specify both ConfigA.class and ConfigB.class when instantiating the context, only ConfigB needs to be supplied explicitly, as the following example shows:

```java

public static void main(String[] args) {
	ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigB.class);

	// now both beans A and B will be available...
	A a = ctx.getBean(A.class);
	B b = ctx.getBean(B.class);
}
```
This approach simplifies container instantiation, as only one class needs to be dealt with, rather than requiring you to remember a potentially large number of @Configuration classes during construction.

# Profiles

In Spring, profiles allow you to separate different parts of your configuration for different environments (like development, testing, production, etc.). You can activate a profile in several ways depending on your use case. Here’s how you can activate profiles in Spring:

### 1. Using Application Properties
You can specify the active profile in the application.properties file by setting the spring.profiles.active property.

Example:

In application.properties:
```properties
spring.profiles.active=dev
```

### 2. Using JVM System Properties
You can also set the active profile when starting the application by passing a system property.
```text
java -jar myapp.jar --spring.profiles.active=prod
```
Alternatively, you can use:
```properties
-Dspring.profiles.active=prod
```

### 3. Using Environment Variables
You can set an environment variable to activate a profile. On Linux or macOS, you would set an environment variable like this:

```text
export SPRING_PROFILES_ACTIVE=prod
```

On Windows:
```text
set SPRING_PROFILES_ACTIVE=prod
```

### 4. Using @ActiveProfiles Annotation (For Tests)
When writing tests, you can use the **ActiveProfiles** annotation to activate a specific profile.

```java
@ActiveProfiles("test")
@SpringBootTest
public class MyServiceTest {
    // Test methods here
}
```

### 5. Using Programmatic Activation
You can activate profiles programmatically in your code using **SpringApplication.setAdditionalProfiles()**.

```java
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyApplication.class);
        app.setAdditionalProfiles("dev");
        app.run(args);
    }
}
```

# @Value

The `@Value` annotation in Spring is a powerful tool used to inject values into Spring-managed beans from various sources, such as property files, system properties, environment variables, or even expressions evaluated at runtime. It provides a simple way to externalize configuration and make your application more flexible and maintainable.

## **Key Features of `@Value`**

1. **Injecting Property Values**: Easily inject values from `application.properties` or `application.yml` files.
2. **SpEL Support**: Utilize Spring Expression Language (SpEL) to perform dynamic value assignments.
3. **Default Values**: Provide default values if the desired property is not found.
4. **Environment Variables and System Properties**: Access environment variables and system properties directly.

## **Basic Usage**

### 1. **Injecting Values from Property Files**

Assume you have an `application.properties` file with the following content:

```properties
app.name=MySpringApp
app.version=1.0.0
```
You can inject these properties into your Spring beans using the @Value annotation.

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppInfo {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    // Getters and setters

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }
}

```

### 2. Providing Default Values
   If a property might not be defined, you can specify a default value to avoid errors.

```java
@Value("${app.description:Default Description}")
private String appDescription;
```
In this example, if app.description is not defined in the property files, appDescription will be set to "Default Description".

### 3. Injecting Environment Variables and System Properties
   You can access environment variables and system properties using @Value.

Environment Variable:

```java
@Value("${HOME}")
private String homeDirectory;
```
System Property:
```java
@Value("#{systemProperties['user.name']}")
private String userName;
```
### 4. Using Spring Expression Language (SpEL)
SpEL allows you to perform more complex operations, such as string concatenation, mathematical operations, or invoking methods.

Concatenation Example:

```java
@Value("#{systemProperties['user.name'] + ' - ' + T(java.lang.Math).random()}")
private String dynamicValue;
```

Conditional Expression:

```java
@Value("#{2 > 1 ? 'True' : 'False'}")
private String conditionalValue;
```

### 5. Injecting Values into Constructor Parameters
Starting with Spring 4.3, you can use @Value in constructor parameters for immutable fields.

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {

    private final String url;
    private final String username;
    private final String password;

    public DatabaseConfig(
            @Value("${db.url}") String url,
            @Value("${db.username}") String username,
            @Value("${db.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // Getters
}
```

### Common Use Cases
- **Configuration Properties:** Injecting database URLs, credentials, API keys, etc.
- **Feature Toggles:** Enabling or disabling features based on properties.
- **Dynamic Values:** Calculating or modifying values at runtime using SpEL.
- **Externalized Configuration:** Making the application adaptable to different environments without changing the code.

### Best Practices
Prefer @ConfigurationProperties for Complex Configurations: While @Value is suitable for simple injections, @ConfigurationProperties is better for binding groups of related properties.

```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
private String name;
private String version;
// Getters and setters
}
```

- **Use Immutable Fields When Possible:** Prefer constructor injection with @Value to create immutable beans, enhancing 
thread safety and testability.

- **Avoid Overusing SpEL:** While powerful, excessive use of SpEL can make configurations hard to read and maintain. 
  Use it judiciously.

- **Secure Sensitive Information:** Avoid hardcoding sensitive data. Use environment variables or secure vaults to 
  manage secrets.

### Example: Comprehensive Usage
Let's create a more comprehensive example to illustrate various aspects of @Value.

**application.properties**
```properties
app.name=MySpringApp
app.version=1.0.0
app.description=This is a sample Spring application.
app.feature.enabled=true
db.url=jdbc:mysql://localhost:3306/mydb
db.username=root
db.password=secret
```

**ConfigurableService.java**

Copy code
```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigurableService {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.description:No Description Provided}")
    private String appDescription;

    @Value("${app.feature.enabled:false}")
    private boolean isFeatureEnabled;

    @Value("#{systemProperties['user.home']}")
    private String userHome;

    @Value("#{T(java.lang.Math).PI}")
    private double piValue;

    // Getters

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public boolean isFeatureEnabled() {
        return isFeatureEnabled;
    }

    public String getUserHome() {
        return userHome;
    }

    public double getPiValue() {
        return piValue;
    }

    // Business logic methods can use these injected values
}
```

**Using the Service**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner {

    private final ConfigurableService configurableService;

    @Autowired
    public ApplicationRunner(ConfigurableService configurableService) {
        this.configurableService = configurableService;
    }

    public void run() {
        System.out.println("App Name: " + configurableService.getAppName());
        System.out.println("App Version: " + configurableService.getAppVersion());
        System.out.println("App Description: " + configurableService.getAppDescription());
        System.out.println("Is Feature Enabled: " + configurableService.isFeatureEnabled());
        System.out.println("User Home Directory: " + configurableService.getUserHome());
        System.out.println("Pi Value: " + configurableService.getPiValue());
    }
}
```
When you run the application, ApplicationRunner will print the injected values from the properties file and system properties.

### Common Pitfalls and Solutions

#### 1. Missing Property Placeholders

- Issue: If a property key used in @Value is missing from the property sources, the application may fail to start.
- Solution: Provide default values or ensure all necessary properties are defined.

```java
@Value("${non.existent.property:defaultValue}")
private String someValue;
```
#### 2. Incorrect Syntax

- Issue: Incorrect usage of ${} or #{} can lead to errors.
- Solution: Use ${} for property placeholders and #{} for SpEL expressions.

#### 3. Typographical Errors in Property Keys

- Issue: Misspelling property keys leads to unresolved placeholders.
- Solution: Double-check property keys and use tools or IDE features to manage properties.

#### 4. Circular Dependencies

- Issue: Overusing @Value in beans with complex dependencies might introduce circular dependencies.
- Solution: Refactor the configuration or use @ConfigurationProperties to manage related properties collectively.


### Advanced Usage
**Injecting Lists and Maps**

You can inject lists and maps using @Value combined with SpEL.

- List Injection:
```properties
app.servers=server1,server2,server3
```
```java
@Value("#{'${app.servers}'.split(',')}")
private List<String> servers;
```

- Map Injection:
```properties
app.settings.key1=value1
app.settings.key2=value2
```

```java
@Value("#{${app.settings}}")
private Map<String, String> settings;
```

### Using @Value with Optional Dependencies
You can conditionally inject values based on the presence of certain properties.

```java
@Value("${optional.property:#{null}}")
private String optionalProperty;
```
In this case, if optional.property is not defined, optionalProperty will be null.
