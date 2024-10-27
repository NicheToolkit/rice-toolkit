* identity for `String` type Resolver examples

```java

@Component
public class DefaultStringIdResolver implements RestIdResolver<String> {

    @Override
    public <M extends RestId<String>> String resolveId(M model) throws RestException {
        return IdentityUtils.generateString();
    }
}
```