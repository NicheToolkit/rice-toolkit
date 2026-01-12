package io.github.nichetoolkit.rice.controller;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rice.stereotype.RestSkip;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <code>HelloController</code>
 * <p>The hello controller class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.bind.annotation.RestController
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @since Jdk1.8
 */
@RestSkip
@RestController
@RequestMapping("/rest")
public class HelloController {

    /**
     * <code>hello</code>
     * <p>The hello method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The hello return object is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.RequestMapping
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public RestResult<?> hello() {
        return RestResult.success(RestErrorStatus.HTTP_CONFIG_ERROR);
    }
}
