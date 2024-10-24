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
 * <code>UserController</code>
 * <p>The user controller class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see io.github.nichetoolkit.rice.stereotype.RestSkip
 * @see org.springframework.web.bind.annotation.CrossOrigin
 * @see org.springframework.web.bind.annotation.RestController
 * @see io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @since Jdk1.8
 */
@Slf4j
@RestSkip
@CrossOrigin
@RestController
@RestNotelog("user service")
@RequestMapping("/rest/user")
public class UserController {

    /**
     * <code>userService</code>
     * {@link io.github.nichetoolkit.rice.service.UserService} <p>The <code>userService</code> field.</p>
     * @see io.github.nichetoolkit.rice.service.UserService
     */
    private final UserService userService;

    /**
     * <code>UserController</code>
     * <p>Instantiates a new user controller.</p>
     * @param userService {@link io.github.nichetoolkit.rice.service.UserService} <p>The user service parameter is <code>UserService</code> type.</p>
     * @see io.github.nichetoolkit.rice.service.UserService
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * <code>create</code>
     * <p>The create method.</p>
     * @param userModel {@link io.github.nichetoolkit.rice.simple.UserModel} <p>The user model parameter is <code>UserModel</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The create return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.UserModel
     * @see org.springframework.web.bind.annotation.RequestBody
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.PostMapping
     * @see io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see io.github.nichetoolkit.rest.RestException
     */
    @PostMapping("/create")
    @RestUserlog(loggingType = LoggingType.CREATE, userlog = "user create")
    public RestResult<UserModel> create(@RequestBody UserModel userModel) throws RestException {
        return RestResult.success(userService.create(userModel));
    }

    /**
     * <code>update</code>
     * <p>The update method.</p>
     * @param user {@link io.github.nichetoolkit.rice.simple.UserModel} <p>The user parameter is <code>UserModel</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The update return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.UserModel
     * @see org.springframework.web.bind.annotation.RequestBody
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.PostMapping
     * @see io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see io.github.nichetoolkit.rest.RestException
     */
    @PostMapping("/update")
    @RestUserlog(loggingType = LoggingType.UPDATE, userlog = "user update")
    public RestResult<UserModel> update(@RequestBody UserModel user) throws RestException {
        return RestResult.success(userService.update(user));
    }

    /**
     * <code>queryById</code>
     * <p>The query by id method.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The query by id return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.web.bind.annotation.PathVariable
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.GetMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @GetMapping("/query/{id}")
    public RestResult<UserModel> queryById(@PathVariable("id") String id) throws RestException {
        UserModel userModel = userService.queryById(id);
        return RestResult.success(userModel);
    }

    /**
     * <code>queryByFilter</code>
     * <p>The query by filter method.</p>
     * @param filter {@link io.github.nichetoolkit.rice.simple.UserFilter} <p>The filter parameter is <code>UserFilter</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The query by filter return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.UserFilter
     * @see org.springframework.web.bind.annotation.RequestBody
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.PostMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @PostMapping("/query/filter")
    public RestResult<RestPage<UserModel>> queryByFilter(@RequestBody UserFilter filter) throws RestException {
        RestPage<UserModel> restPage = userService.queryAllWithFilter(filter);
        return RestResult.success(restPage);
    }

    /**
     * <code>deleteById</code>
     * <p>The delete by id method.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The delete by id return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.web.bind.annotation.PathVariable
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.DeleteMapping
     * @see io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see io.github.nichetoolkit.rest.RestException
     */
    @DeleteMapping("/delete/{id}")
    @RestUserlog(loggingType = LoggingType.DELETE_ID, userlog = "user delete by id")
    public RestResult<?> deleteById(@PathVariable("id") String id) throws RestException {
        userService.deleteById(id);
        return RestResult.success();
    }

    /**
     * <code>deleteByFilter</code>
     * <p>The delete by filter method.</p>
     * @param filter {@link io.github.nichetoolkit.rice.simple.UserFilter} <p>The filter parameter is <code>UserFilter</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The delete by filter return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.UserFilter
     * @see org.springframework.web.bind.annotation.RequestBody
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.PostMapping
     * @see io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see io.github.nichetoolkit.rest.RestException
     */
    @PostMapping("/delete/filter")
    @RestUserlog(loggingType = LoggingType.DELETE_FILTER, userlog = "user delete by filter")
    public RestResult<?> deleteByFilter(@RequestBody UserFilter filter) throws RestException {
        userService.deleteAllWithFilter(filter);
        return RestResult.success();
    }
}
