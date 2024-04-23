package io.github.nichetoolkit.rice.controller;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import io.github.nichetoolkit.rice.RestPage;
import io.github.nichetoolkit.rice.service.SimpleService;
import io.github.nichetoolkit.rice.simple.SimpleFilter;
import io.github.nichetoolkit.rice.simple.SimpleModel;
import io.github.nichetoolkit.rice.stereotype.RestSkip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>SimpleController</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@RestSkip
@CrossOrigin
@RestNotelog
@RestController
@RequestMapping("/rice/simple")
public class SimpleController {

    @Autowired
    private SimpleService simpleService;

    @GetMapping("/error")
    public ResponseEntity<RestResult> error() throws RestException {
        Object test = null;
        test.toString();
        return RestResult.ok();
    }

    @PostMapping("/create")
    public ResponseEntity<RestResult<SimpleModel>> create(@RequestBody SimpleModel simpleModel) throws RestException {
        return RestResult.ok(simpleService.create(simpleModel));
    }

    @PostMapping("/update")
    public ResponseEntity<RestResult<SimpleModel>> update(@RequestBody SimpleModel simpleModel) throws RestException {
        return RestResult.ok(simpleService.update(simpleModel));
    }

    @GetMapping("/query/{id}")
    public ResponseEntity<RestResult<SimpleModel>> queryById(@PathVariable("id") String id) throws RestException {
        SimpleModel simpleModel = simpleService.queryById(id);
        return RestResult.ok(simpleModel);
    }

    @PostMapping("/query/filter")
    public ResponseEntity<RestResult<RestPage<SimpleModel>>> queryByFilter(@RequestBody SimpleFilter filter) throws RestException {
        RestPage<SimpleModel> restPage = simpleService.queryAllWithFilter(filter);
        return RestResult.ok(restPage);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteById(@RequestParam("id") String id) throws RestException {
        simpleService.deleteById(id);
        return RestResult.ok();
    }

    @PostMapping("/delete/filter")
    public ResponseEntity deleteByFilter(@RequestBody SimpleFilter filter) throws RestException {
        simpleService.deleteAllWithFilter(filter);
        return RestResult.ok();
    }
}
