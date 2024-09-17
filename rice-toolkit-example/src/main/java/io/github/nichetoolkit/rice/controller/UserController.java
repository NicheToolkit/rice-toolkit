package io.github.nichetoolkit.rice.controller;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.userlog.LoggingType;
import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rice.RestPage;
import io.github.nichetoolkit.rice.service.UserService;
import io.github.nichetoolkit.rice.simple.UserFilter;
import io.github.nichetoolkit.rice.simple.UserModel;
import io.github.nichetoolkit.rice.stereotype.RestSkip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>UserController</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@RestSkip
@CrossOrigin
@RestController
@RestNotelog("user service")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @RestUserlog(loggingType = LoggingType.CREATE, userlog = "user create")
    public RestResult<UserModel> create(@RequestBody UserModel userModel) throws RestException {
        return RestResult.success(userService.create(userModel));
    }

    @PostMapping("/update")
    @RestUserlog(loggingType = LoggingType.UPDATE, userlog = "user update")
    public RestResult<UserModel> update(@RequestBody UserModel user) throws RestException {
        return RestResult.success(userService.update(user));
    }
    
    @GetMapping("/query/{id}")
    public RestResult<UserModel> queryById(@PathVariable("id") String id) throws RestException {
        UserModel userModel = userService.queryById(id);
        return RestResult.success(userModel);
    }

    @PostMapping("/query/filter")
    public RestResult<RestPage<UserModel>> queryByFilter(@RequestBody UserFilter filter) throws RestException {
        RestPage<UserModel> restPage = userService.queryAllWithFilter(filter);
        return RestResult.success(restPage);
    }

    @DeleteMapping("/delete/{id}")
    @RestUserlog(loggingType = LoggingType.DELETE_ID, userlog = "user delete by id")
    public RestResult<?> deleteById(@PathVariable("id") String id) throws RestException {
        userService.deleteById(id);
        return RestResult.success();
    }

    @PostMapping("/delete/filter")
    @RestUserlog(loggingType = LoggingType.DELETE_FILTER, userlog = "user delete by filter")
    public RestResult<?> deleteByFilter(@RequestBody UserFilter filter) throws RestException {
        userService.deleteAllWithFilter(filter);
        return RestResult.success();
    }
}
