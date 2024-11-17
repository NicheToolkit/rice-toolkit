package io.github.nichetoolkit.rice.controller;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rice.service.SimpleService;
import io.github.nichetoolkit.rice.simple.SimpleFilter;
import io.github.nichetoolkit.rice.simple.SimpleModel;
import io.github.nichetoolkit.rice.stereotype.RestSkip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <code>SimpleController</code>
 * <p>The simple controller class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.stereotype.RestSkip
 * @see org.springframework.web.bind.annotation.CrossOrigin
 * @see org.springframework.web.bind.annotation.RestController
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @since Jdk1.8
 */
@RestSkip
@CrossOrigin
@RestController
@RequestMapping("/rice/simple")
public class SimpleController {

    /**
     * <code>simpleService</code>
     * {@link io.github.nichetoolkit.rice.service.SimpleService} <p>The <code>simpleService</code> field.</p>
     * @see io.github.nichetoolkit.rice.service.SimpleService
     */
    private final SimpleService simpleService;

    /**
     * <code>SimpleController</code>
     * <p>Instantiates a new simple controller.</p>
     * @param simpleService {@link io.github.nichetoolkit.rice.service.SimpleService} <p>The simple service parameter is <code>SimpleService</code> type.</p>
     * @see io.github.nichetoolkit.rice.service.SimpleService
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public SimpleController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    /**
     * <code>create</code>
     * <p>The create method.</p>
     * @param simpleModel {@link io.github.nichetoolkit.rice.simple.SimpleModel} <p>The simple model parameter is <code>SimpleModel</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The create return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.SimpleModel
     * @see org.springframework.web.bind.annotation.RequestBody
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.PostMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @PostMapping("/create")
    public RestResult<SimpleModel> create(@RequestBody SimpleModel simpleModel) throws RestException {
        return RestResult.success(simpleService.create(simpleModel));
    }

    /**
     * <code>update</code>
     * <p>The update method.</p>
     * @param simpleModel {@link io.github.nichetoolkit.rice.simple.SimpleModel} <p>The simple model parameter is <code>SimpleModel</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The update return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.SimpleModel
     * @see org.springframework.web.bind.annotation.RequestBody
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.PostMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @PostMapping("/update")
    public RestResult<SimpleModel> update(@RequestBody SimpleModel simpleModel) throws RestException {
        return RestResult.success(simpleService.update(simpleModel));
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
    public RestResult<SimpleModel> queryById(@PathVariable("id") String id) throws RestException {
        SimpleModel simpleModel = simpleService.queryById(id);
        return RestResult.success(simpleModel);
    }

    /**
     * <code>queryByFilter</code>
     * <p>The query by filter method.</p>
     * @param filter {@link io.github.nichetoolkit.rice.simple.SimpleFilter} <p>The filter parameter is <code>SimpleFilter</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The query by filter return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.SimpleFilter
     * @see org.springframework.web.bind.annotation.RequestBody
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.PostMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @PostMapping("/query/filter")
    public RestResult<RestPage<SimpleModel>> queryByFilter(@RequestBody SimpleFilter filter) throws RestException {
        RestPage<SimpleModel> restPage = simpleService.queryAllWithFilter(filter);
        return RestResult.success(restPage);
    }

    /**
     * <code>deleteById</code>
     * <p>The delete by id method.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The delete by id return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.web.bind.annotation.RequestParam
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.DeleteMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @DeleteMapping("/delete")
    public RestResult<?> deleteById(@RequestParam("id") String id) throws RestException {
        simpleService.deleteById(id);
        return RestResult.success();
    }

    /**
     * <code>deleteByFilter</code>
     * <p>The delete by filter method.</p>
     * @param filter {@link io.github.nichetoolkit.rice.simple.SimpleFilter} <p>The filter parameter is <code>SimpleFilter</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The delete by filter return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.SimpleFilter
     * @see org.springframework.web.bind.annotation.RequestBody
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.PostMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @PostMapping("/delete/filter")
    public RestResult<?> deleteByFilter(@RequestBody SimpleFilter filter) throws RestException {
        simpleService.deleteAllWithFilter(filter);
        return RestResult.success();
    }
}
