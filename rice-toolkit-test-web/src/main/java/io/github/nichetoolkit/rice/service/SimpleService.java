package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rice.service.extend.SingleService;
import io.github.nichetoolkit.rice.simple.SimpleFilter;
import io.github.nichetoolkit.rice.simple.SimpleModel;

public interface SimpleService extends FilterService<String, String, SimpleModel, SimpleFilter>, SingleService<String, String, SimpleModel> {
}
