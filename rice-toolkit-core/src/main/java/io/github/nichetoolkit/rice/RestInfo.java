package io.github.nichetoolkit.rice;

/**
 * <code>RestInfo</code>
 * <p>The rest info interface.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestId
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("WeakerAccess")
public interface RestInfo<I> extends RestId<I> {

    /**
     * <code>getName</code>
     * <p>The get name getter method.</p>
     * @return {@link java.lang.String} <p>The get name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getName();

    /**
     * <code>setName</code>
     * <p>The set name setter method.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    void setName(String name);

    /**
     * <code>getDescription</code>
     * <p>The get description getter method.</p>
     * @return {@link java.lang.String} <p>The get description return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getDescription();

    /**
     * <code>setDescription</code>
     * <p>The set description setter method.</p>
     * @param description {@link java.lang.String} <p>The description parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    void setDescription(String description);
}
