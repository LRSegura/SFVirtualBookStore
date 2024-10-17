# Pom.xml Dependencies

**Spring Core (spring-core):**
1. `Purpose`: Spring Core is the foundational module of the Spring Framework. It provides essential features such as 
Inversion of Control (IoC) and dependency injection.
2. `Functionality`:
- `IoC Container`: Spring Core manages the lifecycle of objects (beans) and their dependencies. It injects dependencies 
   into various bean classes (e.g., controllers, services).
- `Core Utilities`: It includes core utilities and common functionality (e.g., enums) critical for Spring.
- `Transitive Dependency`: Other Spring modules often depend on spring-core directly or indirectly.
- `Dependency`: If you include spring-context in your project, it automatically brings spring-core as a transitive 
  dependency.

**Spring Context (spring-context):**

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.1.11</version>
</dependency>
```

1. `Purpose`: Spring Context builds upon Spring Core and provides an Application Context, which serves as Spring’s 
Dependency Injection Container.
2. `Functionality`:
- `Dependency Injection`: Spring Context manages beans, resolves dependencies, and provides a runtime environment for 
  your application.
- `Resource Loading`: It supports internationalization using resource bundles and transparently creates contexts (e.g., 
  for Servlet containers).
- `Event Propagation`: Spring Context handles event propagation within the application.

- `Dependency`: Spring Context itself depends on spring-core, so when you declare spring-context, you also get spring-core in your classpath12.
In summary, while spring-core provides the core features like IoC and utilities, spring-context extends it by offering an application context and additional functionalities. When working with Spring, you’ll typically include both in your project

**Spring-web**
```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-web -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>6.1.11</version>
</dependency>
```
The `spring-web` dependency contains common web-specific utilities for both Servlet and Portlet environments. 
It provides essential functionality for web applications, such as handling HTTP requests, managing sessions, and working 
with web-related components. When building a Spring-based web application, you'll often include `spring-web` as a transitive
dependency through other Spring modules, like `spring-webmvc`¹². If you're using Spring Boot, the `spring-boot-starter-web` 
dependency includes `spring-web` and sets up a self-contained HTTP server for your application.

**DataSource**

`Spring-orm`: Spring Object/Relational Mapping which provides integration layers for popular object-relational mapping 
APIs, including JPA, JDO, and Hibernate.
```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-orm -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-orm</artifactId>
    <version>6.1.11</version>
</dependency>
```

`Hibernate's core ORM functionality`
```xml
<!-- https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core -->
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.5.2.Final</version>
</dependency>
```

`Jakarta Persistence API`

```xml
<!-- https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api -->
<dependency>
    <groupId>jakarta.persistence</groupId>
    <artifactId>jakarta.persistence-api</artifactId>
    <version>3.2.0</version>
</dependency>
```

`Spring transaction`: Support for programmatic and declarative transaction management for classes that implement 
special interfaces or any POJO.

```xml
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-tx</artifactId>
    <version>6.1.11</version>
</dependency>
```

`Apache Commons DBCP` software implements Database Connection Pooling
```xml
    <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-dbcp2 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-dbcp2</artifactId>
    <version>2.12.0</version>
</dependency>        
        
```
`Spring Data module for JPA repositories.`
```xml
<!-- https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa -->
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-jpa</artifactId>
    <version>3.3.2</version>
</dependency>

```

`Spring AOP.`

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-aspects -->
<dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-aspects</artifactId>
     <version>6.1.13</version>
 </dependency>
```

# Configuration

**dispatcher-servlet.xml (is it necessary?):**


No, you typically don't need a dispatcher-servlet.xml file in Spring Framework if you're using Java annotation configuration.

Here's why:

`Purpose of dispatcher-servlet.xml:` This file was traditionally used for XML-based configuration of Spring MVC 
components like DispatcherServlet itself, view resolvers, handler mappings, and more.

`Java Annotation Configuration:` With annotations like @EnableWebMvc and @ComponentScan, you can define these configurations 
directly in your Java classes. This approach is cleaner and more concise.

`Auto-configuration` in Spring Boot: If you're using Spring Boot, it often auto-configures the DispatcherServlet based on 
conventions, further reducing the need for manual configuration files.
However, there are some scenarios where you might still consider using dispatcher-servlet.xml:

`Legacy Applications:` If you're working with an existing Spring MVC application that relies on XML configuration, you might 
need to keep the dispatcher-servlet.xml file for now.
Fine-grained control: While annotations offer a convenient way to configure most aspects, for very specific customizations,
you might resort to XML configuration in dispatcher-servlet.xml.

In summary, for new Spring MVC applications using Java annotations, focus on leveraging annotations and potentially Spring Boot
auto-configuration. You can revisit the need for dispatcher-servlet.xml only if you have specific requirements for XML-based configuration.

`Example web.xml`
```xml
<web-app>
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>

```

**applicationContext.xml (is it necessary?):**

No, you generally don't need an applicationContext.xml file if you're using Java annotation configuration in Spring. Here's why:

`Purpose of applicationContext.xml:` This file traditionally served as the central configuration point for Spring 
applications. It defined beans (objects managed by Spring) using XML syntax.

`Java Annotation Configuration:` Annotations like @Configuration, @Bean, and @ComponentScan allow you to define beans directly 
within your Java classes. This approach is more type-safe and easier to maintain.

`Auto-configuration in Spring Boot:` If you're using Spring Boot, it often auto-configures many common beans based on conventions,
further reducing the need for manual configuration in XML.

However, there are some scenarios where you might still consider using applicationContext.xml:

`Legacy Applications:` If you're working with an existing Spring application that relies on XML configuration, you might need to keep applicationContext.xml for now.
Complex Configuration: For highly complex configurations that involve dependencies between beans or integrating with non-annotated libraries, you might find XML easier to manage.

Here's a breakdown of when to consider using applicationContext.xml:

`New Projects:` In new projects, prioritize Java annotation configuration for its simplicity and type-safety.
`Legacy Projects:` If working with legacy code, gradually migrate away from XML configuration as feasible.
`Complex Scenarios:` For very specific or intricate configurations, evaluate if XML offers a clearer approach.
Remember, Spring Boot's auto-configuration often handles many common scenarios, so the need for applicationContext.xml 
becomes less frequent with modern Spring development.

**web.xml (is it necessary?):**

You might still need a web.xml file in a Spring project with Java annotation configuration, but it depends on your setup. 
Here's a breakdown:

`DispatcherServlet Registration:` Traditionally, web.xml played a role in registering the DispatcherServlet for Spring MVC applications.
Java Annotation Configuration: You can configure DispatcherServlet programmatically using a class implementing WebApplicationInitializer. This offers a cleaner approach.
Spring Boot Auto-configuration: In Spring Boot applications, the DispatcherServlet is often auto-configured, eliminating the need for manual registration in web.xml.
So, when do you need web.xml?

`Manual DispatcherServlet Registration:` If you're not using Spring Boot and want more control over DispatcherServlet configuration 
(beyond annotations), you'd need web.xml for manual registration.Other Servlet or Filter Configuration: Even with Spring, 
you might have other servlets, filters, or listeners that require configuration in web.xml.
Here's a quick guide:

`Spring Boot MVC Project:` No web.xml needed in most cases (DispatcherServlet auto-configured).
Traditional Spring MVC Project: You might need web.xml for manual DispatcherServlet registration unless using a WebApplicationInitializer.
Project with Non-Spring Servlets/Filters: You might need web.xml to configure these alongside Spring components.
Overall, while Java annotations and Spring Boot auto-configuration can often replace the need for web.xml for core Spring 
functionality

# DispatcherServlet
In a Spring MVC application, the DispatcherServlet acts as the front controller. This means it is responsible for handling all incoming web requests, dispatching them to the appropriate handlers (such as controllers), and returning the appropriate responses to the client. The DispatcherServlet is a crucial component of the Spring MVC framework, as it integrates the various components of the framework to process web requests.

## Key Functions of the DispatcherServlet:

1. `Request Handling:` The DispatcherServlet intercepts all incoming requests and determines the appropriate handler (controller) based on the URL pattern.

2. `Handler Mapping:` It uses handler mappings to map requests to handler objects. The handler mappings are configured either through annotations (@RequestMapping, @GetMapping, etc.) or XML configurations.

3. `View Resolution:` After processing a request, the DispatcherServlet uses a view resolver to resolve logical view names returned by controllers into actual views (e.g., JSP files).

4. `Interceptors:` It supports the registration of interceptors, which can be used to preprocess and postprocess requests (e.g., logging, authentication).

5. `Exception Handling:` It provides mechanisms to handle exceptions thrown by controllers, either through annotated methods (@ExceptionHandler) or by configuring global exception resolvers.

## How DispatcherServlet is Configured

In the context of the Java-based configuration, the DispatcherServlet is configured and initialized by extending **AbstractAnnotationConfigDispatcherServletInitializer**. Here’s how it’s set up in the ApplicationInitializer class:

```java
package com.example;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.example.config.WebConfig;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
```

### Breakdown of ApplicationInitializer:

`getRootConfigClasses():` Returns the configuration classes for the root application context. In this example, it returns an empty array, meaning no root context configuration is specified.

`getServletConfigClasses():` Returns the configuration classes for the dispatcher servlet application context. In this case, it returns WebConfig.class, which contains Spring MVC configuration.

`getServletMappings():` Specifies the servlet mapping(s) for the DispatcherServlet. Here, it is mapped to the root URL pattern ("/"), meaning it will handle all incoming requests.

## How DispatcherServlet Works:

1. `Initialization:` When the web application starts, the DispatcherServlet is initialized as specified in the web.xml file or by the ApplicationInitializer.

2. `Request Interception:` When a client sends a request, the DispatcherServlet intercepts it.

3. `Handler Mapping:` It consults handler mappings to find the appropriate controller to handle the request.

4. `Controller Execution:` The controller processes the request and returns a ModelAndView object, which contains the model data and view name.

5. `View Resolution:` The DispatcherServlet uses a view resolver (e.g., InternalResourceViewResolver) to resolve the view name to an actual view (e.g., a JSP file).

6. `Response Generation:` The resolved view is rendered with the model data and the response is sent back to the client.

This setup allows you to define REST endpoints and other controllers in a Spring MVC application, with the DispatcherServlet managing the lifecycle of each web request.



# How to create the dispatcherservlet and the applicationContext?

To create the DispatcherServlet and the ApplicationContext using Java-based configuration in a Spring Framework application, you will need to:

- Configure the DispatcherServlet in web.xml.
- Create an initializer class to set up the DispatcherServlet.
- Create the application context configuration class.

### Here's a step-by-step guide:

1. Configure the DispatcherServlet in web.xml. Create src/main/webapp/WEB-INF/web.xml:

```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                             http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextClass</param-name>
            <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
        </init-param>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>com.example.config.WebConfig</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```
2. Create an Initializer Class

This class will replace the need for web.xml if you are using Java configuration. If you already use web.xml, you don't necessarily need this class. However, it is useful for purely Java-based configurations.

Create src/main/java/com/example/ApplicationInitializer.java:

```java
package com.example;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.example.config.WebConfig;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
```

3. Create the Application Context Configuration Class. Create src/main/java/com/example/config/WebConfig.java:

```java 

package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example")
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
```

`Summary:`
- web.xml: Configures the DispatcherServlet and its context parameters, including the configuration class (WebConfig).

- ApplicationInitializer: Initializes the DispatcherServlet and links it to the configuration class (WebConfig), defining the servlet mapping and configuration classes.

- WebConfig: Configures Spring MVC using Java annotations, including component scanning and view resolution.

# How to create the dispatcherServlet with java based configuration?

To create the DispatcherServlet using Java-based configuration in a Spring Framework application, you can eliminate the need for web.xml and configure everything using Java classes. This approach leverages `WebApplicationInitializer` or its convenience subclass `AbstractAnnotationConfigDispatcherServletInitializer` to configure the DispatcherServlet programmatically.

# In this example, how the application context is started?

In the Java-based configuration example provided, the application context is started by the **DispatcherServlet**, which is configured programmatically using the **AbstractAnnotationConfigDispatcherServletInitializer**. Here's a detailed explanation of how it works:

### How the Application Context is Started

1. AbstractAnnotationConfigDispatcherServletInitializer Initialization

> The ApplicationInitializer class extends AbstractAnnotationConfigDispatcherServletInitializer, which automatically registers the DispatcherServlet and initializes the Spring application context when the web application starts.

2. getServletConfigClasses() Method

> The getServletConfigClasses() method returns the configuration class (WebConfig.class) that contains the Spring MVC configuration. This class is used by the DispatcherServlet to initialize the Spring application context.

3. getRootConfigClasses() Method

> The getRootConfigClasses() method returns the configuration classes for the root application context (if any). In this example, it returns an empty array, meaning no root context configuration is specified. This is typically used for non-web-related configuration (e.g., data access configuration).

4. getServletMappings() Method

> The getServletMappings() method returns the URL pattern mappings for the DispatcherServlet. In this example, it is mapped to the root URL pattern ("/"), meaning it will handle all incoming requests.

### Detailed Steps

1. Application Starts: When the web application starts, the servlet container (e.g., Tomcat) looks for classes that implement **WebApplicationInitializer**. The **AbstractAnnotationConfigDispatcherServletInitializer** is a convenience class that implements this interface.

2. Servlet Registration: The **AbstractAnnotationConfigDispatcherServletInitializer** registers the DispatcherServlet with the servlet container. This is equivalent to declaring the DispatcherServlet in the web.xml.

3. Context Initialization: The DispatcherServlet is initialized, and it creates a WebApplicationContext based on the configuration class returned by the getServletConfigClasses() method (WebConfig.class in this case). The WebApplicationContext is responsible for loading and managing the Spring beans defined in the configuration class (WebConfig).
   Handler Mapping: The DispatcherServlet uses the WebApplicationContext to map incoming requests to the appropriate handlers (e.g., controllers).

