package io.github.nichetoolkit.rice.controller;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.parsing.RequestParsing;
import io.github.nichetoolkit.rice.simple.TestFilter;
import io.github.nichetoolkit.rice.stereotype.RestSkip;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <code>ShadowController</code>
 * <p>The shadow controller class.</p>
 * @see  io.github.nichetoolkit.rice.stereotype.RestSkip
 * @see  org.springframework.web.bind.annotation.CrossOrigin
 * @see  org.springframework.web.bind.annotation.RestController
 * @see  org.springframework.web.bind.annotation.RequestMapping
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@RestSkip
@CrossOrigin
@RestController
@RequestMapping("/rice/shadow")
public class ShadowController {
    /**
     * <code>test</code>
     * <p>The test method.</p>
     * @param filter {@link io.github.nichetoolkit.rice.simple.TestFilter} <p>The filter parameter is <code>TestFilter</code> type.</p>
     * @param file {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @see  io.github.nichetoolkit.rice.simple.TestFilter
     * @see  io.github.nichetoolkit.rest.parsing.RequestParsing
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  org.springframework.web.bind.annotation.RequestPart
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  org.springframework.web.bind.annotation.PostMapping
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The test return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    @PostMapping("/test")
    public RestResult<?> test(@RequestParsing TestFilter filter, @RequestPart("file") MultipartFile file) throws RestException {
        return RestResult.success();
    }
}
