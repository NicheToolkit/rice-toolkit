package io.github.nichetoolkit.mybatis.consts;


/**
 * <code>ScriptConstants</code>
 * <p>The script constants interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface ScriptConstants {
    /**
     * <code>NOW</code>
     * {@link java.lang.String} <p>The constant <code>NOW</code> field.</p>
     * @see  java.lang.String
     */
    String NOW = "now()";
    /**
     * <code>PARAM</code>
     * {@link java.lang.String} <p>The constant <code>PARAM</code> field.</p>
     * @see  java.lang.String
     */
    String PARAM = "_parameter";
    /**
     * <code>NULL</code>
     * {@link java.lang.String} <p>The constant <code>NULL</code> field.</p>
     * @see  java.lang.String
     */
    String NULL = "null";
    /**
     * <code>AND</code>
     * {@link java.lang.String} <p>The constant <code>AND</code> field.</p>
     * @see  java.lang.String
     */
    String AND = "and";
    /**
     * <code>EMPTY</code>
     * {@link java.lang.String} <p>The constant <code>EMPTY</code> field.</p>
     * @see  java.lang.String
     */
    String EMPTY = "''";
    /**
     * <code>RESULT_MAPS</code>
     * {@link java.lang.String} <p>The constant <code>RESULT_MAPS</code> field.</p>
     * @see  java.lang.String
     */
    String RESULT_MAPS = "resultMaps";
    /**
     * <code>DEFAULT_RESULT_MAP</code>
     * {@link java.lang.String} <p>The constant <code>DEFAULT_RESULT_MAP</code> field.</p>
     * @see  java.lang.String
     */
    String DEFAULT_RESULT_MAP = "defaultResultMap";
    /**
     * <code>CDATA_LT</code>
     * {@link java.lang.String} <p>The constant <code>CDATA_LT</code> field.</p>
     * @see  java.lang.String
     */
    String CDATA_LT = "<![CDATA[";
    /**
     * <code>CDATA_GT</code>
     * {@link java.lang.String} <p>The constant <code>CDATA_GT</code> field.</p>
     * @see  java.lang.String
     */
    String CDATA_GT = "]]>";
    /**
     * <code>NAME_EQUALS_PROPERTY</code>
     * {@link java.lang.String} <p>The constant <code>NAME_EQUALS_PROPERTY</code> field.</p>
     * @see  java.lang.String
     */
    String NAME_EQUALS_PROPERTY = "name = #{name}";
    /**
     * <code>SCRIPT_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>SCRIPT_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    /* "<script>\n%s\n</script>" */
    String SCRIPT_LABEL = "<script>%s\n</script>";
    /**
     * <code>WHERE_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>WHERE_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String WHERE_LABEL = "\n<where>%s\n</where> ";
    /**
     * <code>CHOOSE_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>CHOOSE_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String CHOOSE_LABEL = "\n<choose>%s\n</choose> ";
    /**
     * <code>OTHERWISE_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>OTHERWISE_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String OTHERWISE_LABEL = "\n<otherwise>%s\n</otherwise> ";
    /**
     * <code>SET_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>SET_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String SET_LABEL = "\n<set>%s\n</set> ";
    /**
     * <code>IF_TEST_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>IF_TEST_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String IF_TEST_LABEL = "\n<if test=\"%s\">%s\n</if> ";

    /**
     * <code>IF_TEST_PARAM_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>IF_TEST_PARAM_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String IF_TEST_PARAM_LABEL = "\n<if test=\"_parameter != null and _parameter != ''\">%s\n</if> ";
    /**
     * <code>WHEN_TEST_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>WHEN_TEST_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String WHEN_TEST_LABEL = "\n<when test=\"%s\">%s\n</when> ";

    /**
     * <code>TRIM_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>TRIM_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String TRIM_LABEL = "\n<trim prefix=\"%s\" prefixOverrides=\"%s\" suffixOverrides=\"%s\" suffix=\"%s\">%s\n</trim> ";

    /**
     * <code>TRIM_PREFIX_OVERRIDE_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>TRIM_PREFIX_OVERRIDE_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String TRIM_PREFIX_OVERRIDE_LABEL = "\n<trim prefix=\"%s\" prefixOverrides=\"%s\" suffix=\"%s\">%s\n</trim> ";

    /**
     * <code>TRIM_SUFFIX_OVERRIDE_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>TRIM_SUFFIX_OVERRIDE_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String TRIM_SUFFIX_OVERRIDE_LABEL = "\n<trim prefix=\"%s\" suffixOverrides=\"%s\" suffix=\"%s\">%s\n</trim> ";

    /**
     * <code>FOREACH_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>FOREACH_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String FOREACH_LABEL = "\n<foreach collection=\"%s\" item=\"%s\">%s\n</foreach> ";
    /**
     * <code>FOREACH_SEPARATOR_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>FOREACH_SEPARATOR_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String FOREACH_SEPARATOR_LABEL = "\n<foreach collection=\"%s\" item=\"%s\" separator=\"%s\">%s\n</foreach> ";
    /**
     * <code>FOREACH_BRACE_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>FOREACH_BRACE_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String FOREACH_BRACE_LABEL = "\n<foreach collection=\"%s\" item=\"%s\" open=\"%s\" close=\"%s\" separator=\"%s\">%s\n</foreach> ";
    /**
     * <code>FOREACH_INDEX_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>FOREACH_INDEX_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String FOREACH_INDEX_LABEL = "\n<foreach collection=\"%s\" item=\"%s\" index=\"%s\" open=\"%s\" close=\"%s\" separator=\"%s\">%s\n</foreach> ";
    /**
     * <code>BIND_LABEL</code>
     * {@link java.lang.String} <p>The constant <code>BIND_LABEL</code> field.</p>
     * @see  java.lang.String
     */
    String BIND_LABEL = "\n<bind name=\"%s\" value=\"%s\"/>";
}
