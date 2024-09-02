package io.github.nichetoolkit.rice.controller;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import io.github.nichetoolkit.rice.RestPage;
import io.github.nichetoolkit.rice.service.SimpleService;
import io.github.nichetoolkit.rice.simple.SimpleFilter;
import io.github.nichetoolkit.rice.simple.SimpleModel;
import io.github.nichetoolkit.rice.stereotype.RestSkip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestSkip
@CrossOrigin
@RestNotelog
@RestController
@RequestMapping("/rice/simple")
public class SimpleController {

    private final SimpleService simpleService;

    @Autowired
    public SimpleController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @GetMapping("/error")
    public RestResult<?> error() throws RestException {
        return RestResult.mistake(RestErrorStatus.MISTAKE,new NullPointerException());
    }

    @PostMapping("/create")
    public RestResult<SimpleModel> create(@RequestBody SimpleModel simpleModel) throws RestException {
        return RestResult.success(simpleService.create(simpleModel));
    }

    @PostMapping("/update")
    public RestResult<SimpleModel> update(@RequestBody SimpleModel simpleModel) throws RestException {
        return RestResult.success(simpleService.update(simpleModel));
    }

    @GetMapping("/query/{id}")
    public RestResult<SimpleModel> queryById(@PathVariable("id") String id) throws RestException {
        SimpleModel simpleModel = simpleService.queryById(id);
        return RestResult.success(simpleModel);
    }

    @PostMapping("/query/filter")
    public RestResult<RestPage<SimpleModel>> queryByFilter(@RequestBody SimpleFilter filter) throws RestException {
        RestPage<SimpleModel> restPage = simpleService.queryAllWithFilter(filter);
        return RestResult.success(restPage);
    }

    @DeleteMapping("/delete")
    public RestResult<?> deleteById(@RequestParam("id") String id) throws RestException {
        simpleService.deleteById(id);
        return RestResult.success();
    }

    @PostMapping("/delete/filter")
    public RestResult<?> deleteByFilter(@RequestBody SimpleFilter filter) throws RestException {
        simpleService.deleteAllWithFilter(filter);
        return RestResult.success();
    }
}
