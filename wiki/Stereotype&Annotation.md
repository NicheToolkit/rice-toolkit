* default annotation

|  annotation   |                 target                  |                                               description                                                |
|:-------------:|:---------------------------------------:|:--------------------------------------------------------------------------------------------------------:|
| `RestService` |           `ElementType.TYPE`            |                      the annotation is used to annotate subclass of super service.                       |
|  `RestSkip`   | `ElementType.METHOD`、`ElementType.TYPE` |   the annotation is used to annotate `controller ` or `method` that need to skip login related handle.   |
|  `RestLogin`  |          `ElementType.METHOD`           | the annotation is used to annotate `method` that need to generate authentication information for login.  |
|  `RestCheck`  |          `ElementType.METHOD`           |         the annotation is used to annotate `method` that need to check token prefixes for login.         |
|  `RestAuth`   |          `ElementType.METHOD`           |           the annotation is used to annotate `method` that need to check  auth code for login.           |
| `RestPended`  |          `ElementType.METHOD`           | the annotation is used to annotate `method` that need to precheck  authentication information for login. |
| `RestLogout`  |          `ElementType.METHOD`           |               the annotation is used to annotate `method` that need to discard for login.                |
|  `RestUser`   |         `ElementType.PARAMETER`         |      the annotation is used to annotate `parameter` that need to inject user information of login.       |

* examples

```java

@Slf4j
@CrossOrigin
@RestController
@RestNotelog(loggingKey = "login", notelog = "login controller log")
@RequestMapping("/rest")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RestLogin
    @PostMapping("/login/password")
    @RestUserlog(loggingType = LoggingType.USER_LOGIN, userlog = "password login")
    public RestResult<UserModel> loginWithPassword(RestMap restMap, @RequestBody LoginRequest loginRequest) throws RestException {
        UserModel user = loginService.loginWithPassword(loginRequest);
        return buildLoginResult(restMap, user);
    }

    @GetMapping("/logout")
    @RestUserlog(loggingType = LoggingType.USER_LOGOUT, userlog = "user logout")
    public RestResult<?> logout() throws Exception {
        return RestResult.success();
    }

    private RestResult<UserModel> buildLoginResult(RestMap restMap, UserModel user) {
        restMap.put(UserModel.LOGIN_USER_INFO, JsonUtils.parseJson(user));
        restMap.put(UserModel.LOGIN_USER_ID, user.getId());
        return RestResult.success(user);
    }

}

```

* http request

```http request

POST http://localhost:8080/rest/login/password
Content-Type: application/json

{
  "account": "testUser",
  "password": "123456"
}

> {%
client.global.set("auth_token", response.body.data.token);
%}

###

GET http://localhost:8080/rest/info
Authorization: Bearer {{auth_token}}

###

GET http://localhost:8080/rest/logout
Authorization: Bearer {{auth_token}}

###
```