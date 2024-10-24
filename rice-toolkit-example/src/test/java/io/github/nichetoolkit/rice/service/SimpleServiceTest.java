package io.github.nichetoolkit.rice.service;


import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RiceExampleApplicationTests;
import io.github.nichetoolkit.rice.simple.SimpleModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * <code>SimpleServiceTest</code>
 * <p>The simple service test class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RiceExampleApplicationTests
 * @since Jdk1.8
 */
class SimpleServiceTest extends RiceExampleApplicationTests {

    /**
     * <code>simpleService</code>
     * {@link io.github.nichetoolkit.rice.service.SimpleService} <p>The <code>simpleService</code> field.</p>
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    private SimpleService simpleService;

    /**
     * <code>create</code>
     * <p>The create method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.junit.jupiter.api.Test
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Test
    void create() throws RestException {
        SimpleModel simpleModel = new SimpleModel();
        simpleModel.setName("test");
        simpleModel.setTime(new Date());
        SimpleModel createModel = simpleService.create(simpleModel);
    }
}