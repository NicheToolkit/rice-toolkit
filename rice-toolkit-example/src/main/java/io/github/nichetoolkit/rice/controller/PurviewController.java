package io.github.nichetoolkit.rice.controller;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.userlog.LoggingType;
import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rice.purview.PurviewType;
import io.github.nichetoolkit.rice.purview.RestPurview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <code>PurviewController</code>
 * <p>The purview controller class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.web.bind.annotation.CrossOrigin
 * @see org.springframework.web.bind.annotation.RestController
 * @see io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @since Jdk1.8
 */
@Slf4j
@CrossOrigin
@RestController
@RestNotelog(loggingKey = "login", notelog = "login controller log")
@RequestMapping("/rest/purview")
public class PurviewController {

    /**
     * <code>test</code>
     * <p>The test method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The test return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.GetMapping
     * @see io.github.nichetoolkit.rice.purview.RestPurview
     * @see io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see io.github.nichetoolkit.rest.RestException
     */
    @GetMapping("/test")
    @RestPurview(purview = PurviewType.PURVIEW_1)
    @RestUserlog(loggingType = LoggingType.TEST, userlog = "purview test")
    public RestResult<?> test() throws RestException {
        return RestResult.success("the purview test successfully");
    }

}
