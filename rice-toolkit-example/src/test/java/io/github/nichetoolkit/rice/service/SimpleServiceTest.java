package io.github.nichetoolkit.rice.service;


import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RiceExampleApplicationTests;
import io.github.nichetoolkit.rice.simple.SimpleModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

class SimpleServiceTest extends RiceExampleApplicationTests {

    @Autowired
    private SimpleService simpleService;
    @Test
    void create() throws RestException {
        SimpleModel simpleModel = new SimpleModel();
        simpleModel.setName("test");
        simpleModel.setTime(new Date());
        SimpleModel createModel = simpleService.create(simpleModel);
    }
}