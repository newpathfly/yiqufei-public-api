# yiqufei-public-api

This project contains neccessary classes, enums and interfaces for de-/serialization of Yi-Qu-Fei public API.

## Bean Validation

Each class comes with bean validation constraints which could be used for request and response validation before proceeding with the actual logic.

For example,

```java
/**
 * Adult Number, 1-9, Suppliers please follow the actual request passenger
 * numbers [Attention: request is with the passenger number]
 */
@NotNull
@Min(1)
@Max(9)
Integer adultNumber;
```

To enable bean validation in your project,

1. add following dependencies to `pom.xml`.

    ```xml
    <dependency>
        <groupId>org.hibernate.validator</groupId>
        <artifactId>hibernate-validator</artifactId>
        <version>7.0.1.Final</version>
    </dependency>

    <dependency>
        <groupId>jakarta.validation</groupId>
        <artifactId>jakarta.validation-api</artifactId>
        <version>3.0.0</version>
    </dependency>

    <dependency>
        <groupId>org.glassfish</groupId>
        <artifactId>jakarta.el</artifactId>
        <version>4.0.2</version>
    </dependency>
    ```

1. in the code, add lines like below:

    ```java
    import com.yiqufei.api.model.Search;
    import com.yiqufei.api.utils.ModelValidator;
    
    ...

    Search.Request request = someMethodThatReturnsSearchRequest();
    try {
        new ModelValidator().validate(request);
    } catch (IllegalArgumentException e) {
        // request is invalid
        ...
    }
    ```
