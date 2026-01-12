package io.github.nichetoolkit.rice.simple;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.nichetoolkit.rest.shadow.ShadowMutiField;
import io.github.nichetoolkit.rice.RestFilter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class TestFilter extends RestFilter {
    private String test;
    private Integer index;
    @ShadowMutiField
    private Set<String> tests;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    private StringTestType stringType;
    private LongTestType longType;
}
