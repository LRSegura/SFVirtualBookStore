# Dependencies

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

The `spring-web` dependency contains common web-specific utilities for both Servlet and Portlet environments. 
It provides essential functionality for web applications, such as handling HTTP requests, managing sessions, and working 
with web-related components. When building a Spring-based web application, you'll often include `spring-web` as a transitive
dependency through other Spring modules, like `spring-webmvc`¹². If you're using Spring Boot, the `spring-boot-starter-web` 
dependency includes `spring-web` and sets up a self-contained HTTP server for your application.

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
functionality, it can still be relevant in specific scenarios. Consider your project's setup and configuration needs to determine if you can eliminate web.xml.