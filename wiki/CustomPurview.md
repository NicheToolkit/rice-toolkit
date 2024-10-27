* custom `RestPurview` annotation

```java

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestPurview {

    String key() default "";

    String[] keys() default {};

    long value() default 0L;

    long[] values() default {};

    PurviewType purview() default PurviewType.PURVIEW_ALL;

    PurviewType[] purviews() default {};

    final class Purview {

        public static List<String> keys(@NonNull RestPurview purview) throws RestException {
            Set<String> keySet = new HashSet<>();
            OptionalUtils.ofEmptyable(purview.key()).ifEmptyPresent(keySet::add);
            OptionalUtils.ofEmptyable(purview.keys()).ifEmptyPresent(keys -> keySet.addAll(Arrays.asList(keys)));
            OptionalUtils.ofNullable(purview.purview()).nullFlatMap(value -> OptionalUtils.ofEmptyable(value.getKey())).emptyMap(keySet::add);
            OptionalUtils.ofNullable(purview.purviews()).ifNullPresent(values -> RestStream.stream(values).forEach(value -> OptionalUtils.ofNullable(value).nullFlatMap(module -> OptionalUtils.ofEmptyable(module.getKey())).emptyMap(keySet::add)));
            return new ArrayList<>(keySet);
        }

        public static List<Long> values(@NonNull RestPurview purview) throws RestException {
            Set<Long> valueSet = new HashSet<>();
            OptionalUtils.ofEmptyable(purview.value()).ifEmptyPresent(valueSet::add);
            OptionalUtils.ofEmptyable(purview.values()).ifEmptyPresent(values -> Arrays.stream(values).forEach(valueSet::add));
            OptionalUtils.ofNullable(purview.purview()).nullFlatMap(value -> OptionalUtils.ofEmptyable(value.getValue())).emptyMap(valueSet::add);
            OptionalUtils.ofNullable(purview.purviews()).ifNullPresent(values -> RestStream.stream(values).forEach(value -> OptionalUtils.ofNullable(value).nullFlatMap(module -> OptionalUtils.ofEmptyable(module.getValue())).emptyMap(valueSet::add)));
            return new ArrayList<>(valueSet);
        }

    }
}
```

* custom `PurviewAdvice`  advice

```java

@Slf4j
@Component
public class PurviewAdvice implements DefaultAdvice<RestPurview> {

    private final TokenService tokenService;

    @Autowired
    public PurviewAdvice(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public int order() {
        return 98;
    }

    @Override
    public void doAnnotationHandle(RestHttpRequest request, HttpServletResponse response, HandlerMethod handlerMethod, RestPurview purview) throws RestException {
        UserModel userModel = tokenService.resolveUserInfo(request);
        /* custom user purview */
        userModel.setPurviewType(PurviewType.PURVIEW_1);
        /* custom key mode check*/
        purviewKeysCheck(userModel, RestPurview.Purview.keys(purview));
        /* custom value mode check*/
        purviewValuesCheck(userModel, RestPurview.Purview.values(purview));
    }

    private void purviewKeysCheck(UserModel userModel, List<String> purviewKeys) throws RestException {
        PurviewType purviewType = userModel.getPurviewType();
        if (GeneralUtils.isNotEmpty(purviewKeys)) {
            OptionalUtils.ofEmptyable(purviewType).emptyElseThrow(TokenPermissionException::new);
            String purviewTypeKey = purviewType.getKey();
            OptionalUtils.falseable(purviewKeys.contains(purviewTypeKey), TokenPermissionException::new);
        }
    }

    private void purviewValuesCheck(UserModel userModel, List<Long> purviewValues) throws RestException {
        PurviewType purviewType = userModel.getPurviewType();
        if (GeneralUtils.isNotEmpty(purviewValues)) {
            OptionalUtils.ofEmptyable(purviewType).emptyElseThrow(TokenPermissionException::new);
            Long annexValue = RestReckon.annexValue(purviewValues);
            Long value = purviewType.getValue();
            OptionalUtils.falseable(RestReckon.reachValue(annexValue, value), TokenPermissionException::new);
        }
    }
}
```

* controller `RestPurview` usages

```java

@Slf4j
@CrossOrigin
@RestController
@RestNotelog(loggingKey = "login", notelog = "login controller log")
@RequestMapping("/rest/purview")
public class PurviewController {

    @GetMapping("/test")
    @RestPurview(purview = PurviewType.PURVIEW_1)
    @RestUserlog(loggingType = LoggingType.TEST, userlog = "purview test")
    public RestResult<?> test() throws RestException {
        return RestResult.success("the purview test successfully");
    }

}
```