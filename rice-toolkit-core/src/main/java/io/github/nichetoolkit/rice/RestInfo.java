package io.github.nichetoolkit.rice;

/**
 * <p>RestInfo</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 * @group cyan.tool.kit
 * @date 17:06 2021/6/28
 */
public interface RestInfo<I> extends RestId<I> {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);
}
