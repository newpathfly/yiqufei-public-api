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

To enable bean validation in your project, in the code add lines like below:

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
