package io.github.nichetoolkit.rice.simple;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.nichetoolkit.rest.parsing.JsonParsingMultiField;
import io.github.nichetoolkit.rice.RestFilter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

/**
 * <code>TestFilter</code>
 * <p>The test filter class.</p>
 * @see  io.github.nichetoolkit.rice.RestFilter
 * @see  lombok.Getter
 * @see  lombok.Setter
 * @see  lombok.experimental.SuperBuilder
 * @see  lombok.NoArgsConstructor
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class TestFilter extends RestFilter {
    /**
     * <code>test</code>
     * {@link java.lang.String} <p>The <code>test</code> field.</p>
     * @see  java.lang.String
     */
    private String test;
    /**
     * <code>index</code>
     * {@link java.lang.Integer} <p>The <code>index</code> field.</p>
     * @see  java.lang.Integer
     */
    private Integer index;
    /**
     * <code>tests</code>
     * {@link java.util.Set} <p>The <code>tests</code> field.</p>
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.parsing.JsonParsingMultiField
     */
    @JsonParsingMultiField
    private Set<String> tests;
    /**
     * <code>time</code>
     * {@link java.util.Date} <p>The <code>time</code> field.</p>
     * @see  java.util.Date
     * @see  org.springframework.format.annotation.DateTimeFormat
     * @see  com.fasterxml.jackson.annotation.JsonFormat
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    /**
     * <code>stringType</code>
     * {@link io.github.nichetoolkit.rice.simple.StringTestType} <p>The <code>stringType</code> field.</p>
     * @see  io.github.nichetoolkit.rice.simple.StringTestType
     */
    private StringTestType stringType;
    /**
     * <code>longType</code>
     * {@link io.github.nichetoolkit.rice.simple.LongTestType} <p>The <code>longType</code> field.</p>
     * @see  io.github.nichetoolkit.rice.simple.LongTestType
     */
    private LongTestType longType;
}
