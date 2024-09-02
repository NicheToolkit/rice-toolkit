package io.github.nichetoolkit.rice;

/**
 * <code>RestInfo</code>
 * <p>The type rest info interface.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
public interface RestInfo<I> extends RestId<I> {

    /**
     * <code>getName</code>
     * <p>the name getter method.</p>
     * @return {@link java.lang.String} <p>the name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getName();

    /**
     * <code>setName</code>
     * <p>the name setter method.</p>
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    void setName(String name);

    /**
     * <code>getDescription</code>
     * <p>the description getter method.</p>
     * @return {@link java.lang.String} <p>the description return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getDescription();

    /**
     * <code>setDescription</code>
     * <p>the description setter method.</p>
     * @param description {@link java.lang.String} <p>the description parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    void setDescription(String description);
}
