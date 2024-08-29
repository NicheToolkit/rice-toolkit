package io.github.nichetoolkit.rice;

@SuppressWarnings("WeakerAccess")
public interface RestInfo<I> extends RestId<I> {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);
}
