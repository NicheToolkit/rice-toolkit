package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.RemoveMode;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <code>RiceServiceProperties</code>
 * <p>The type rice service properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Data
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rice.service")
public class RiceServiceProperties {
    /**
     * <code>MAX_PARTITION_SIZE</code>
     * {@link java.lang.Integer} <p>The constant <code>MAX_PARTITION_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    public static final Integer MAX_PARTITION_SIZE = 2000;
    /**
     * <code>MIN_PARTITION_SIZE</code>
     * {@link java.lang.Integer} <p>The constant <code>MIN_PARTITION_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    public static final Integer MIN_PARTITION_SIZE = 0;
    /**
     * <code>QUERY_SIZE</code>
     * {@link java.lang.Integer} <p>The constant <code>QUERY_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    public static final Integer QUERY_SIZE = 2000;
    /**
     * <code>SAVE_SIZE</code>
     * {@link java.lang.Integer} <p>The constant <code>SAVE_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    public static final Integer SAVE_SIZE = 500;
    /**
     * <code>DELETE_SIZE</code>
     * {@link java.lang.Integer} <p>The constant <code>DELETE_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    public static final Integer DELETE_SIZE = 1000;

    /**
     * <code>uniqueModel</code>
     * {@link java.lang.Boolean} <p>The <code>uniqueModel</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean uniqueModel = false;
    /**
     * <code>dynamicTable</code>
     * {@link java.lang.Boolean} <p>The <code>dynamicTable</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean dynamicTable = false;
    /**
     * <code>identityInvade</code>
     * {@link java.lang.Boolean} <p>The <code>identityInvade</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean identityInvade = false;
    /**
     * <code>identityCheck</code>
     * {@link java.lang.Boolean} <p>The <code>identityCheck</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean identityCheck = true;
    /**
     * <code>uniqueName</code>
     * {@link java.lang.Boolean} <p>The <code>uniqueName</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean uniqueName = true;
    /**
     * <code>nonnullName</code>
     * {@link java.lang.Boolean} <p>The <code>nonnullName</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean nonnullName = true;
    /**
     * <code>deleteMode</code>
     * {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>The <code>deleteMode</code> field.</p>
     * @see io.github.nichetoolkit.rice.enums.DeleteMode
     */
    private DeleteMode deleteMode = DeleteMode.NONE;
    /**
     * <code>removeMode</code>
     * {@link io.github.nichetoolkit.rice.enums.RemoveMode} <p>The <code>removeMode</code> field.</p>
     * @see io.github.nichetoolkit.rice.enums.RemoveMode
     */
    private RemoveMode removeMode = RemoveMode.NUMBER;

    /**
     * <code>partition</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties.ServicePartition} <p>The <code>partition</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceServiceProperties.ServicePartition
     * @see org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private ServicePartition partition = new ServicePartition();
    /**
     * <code>delete</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties.ServiceDelete} <p>The <code>delete</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceServiceProperties.ServiceDelete
     * @see org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private ServiceDelete delete = new ServiceDelete();

    /**
     * <code>ServicePartition</code>
     * <p>The type service partition class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Data
     * @since Jdk1.8
     */
    @Data
    public static class ServicePartition {
        /**
         * <code>querySize</code>
         * {@link java.lang.Integer} <p>The <code>querySize</code> field.</p>
         * @see java.lang.Integer
         */
        private Integer querySize = QUERY_SIZE;
        /**
         * <code>saveSize</code>
         * {@link java.lang.Integer} <p>The <code>saveSize</code> field.</p>
         * @see java.lang.Integer
         */
        private Integer saveSize = SAVE_SIZE;
        /**
         * <code>deleteSize</code>
         * {@link java.lang.Integer} <p>The <code>deleteSize</code> field.</p>
         * @see java.lang.Integer
         */
        private Integer deleteSize = DELETE_SIZE;
    }


    /**
     * <code>partitionOfQuery</code>
     * <p>The of query method.</p>
     * @return {@link java.lang.Integer} <p>The of query return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer partitionOfQuery() {
        if (this.partition.querySize > MAX_PARTITION_SIZE) {
            return QUERY_SIZE;
        }
        if (this.partition.querySize <= MIN_PARTITION_SIZE) {
            return QUERY_SIZE;
        }
        return this.partition.querySize;
    }


    /**
     * <code>partitionOfSave</code>
     * <p>The of save method.</p>
     * @return {@link java.lang.Integer} <p>The of save return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer partitionOfSave() {
        if (this.partition.saveSize > MAX_PARTITION_SIZE) {
            return SAVE_SIZE;
        }
        if (this.partition.saveSize <= MIN_PARTITION_SIZE) {
            return SAVE_SIZE;
        }
        return this.partition.saveSize;
    }

    /**
     * <code>partitionOfDelete</code>
     * <p>The of delete method.</p>
     * @return {@link java.lang.Integer} <p>The of delete return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer partitionOfDelete() {
        if (this.partition.deleteSize > MAX_PARTITION_SIZE) {
            return DELETE_SIZE;
        }
        if (this.partition.deleteSize <= MIN_PARTITION_SIZE) {
            return DELETE_SIZE;
        }
        return this.partition.deleteSize;
    }

    /**
     * <code>ServiceDelete</code>
     * <p>The type service delete class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Data
     * @since Jdk1.8
     */
    @Data
    public static class ServiceDelete {
        /**
         * <code>beforeSkip</code>
         * {@link java.lang.Boolean} <p>The <code>beforeSkip</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean beforeSkip = true;
        /**
         * <code>afterSkip</code>
         * {@link java.lang.Boolean} <p>The <code>afterSkip</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean afterSkip = true;
        /**
         * <code>accurateJudge</code>
         * {@link java.lang.Boolean} <p>The <code>accurateJudge</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean accurateJudge = true;
        /**
         * <code>booleanSign</code>
         * {@link java.lang.Boolean} <p>The <code>booleanSign</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean booleanSign = true;
        /**
         * <code>booleanValue</code>
         * {@link java.lang.Boolean} <p>The <code>booleanValue</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean booleanValue = false;
        /**
         * <code>numberSign</code>
         * {@link java.lang.Integer} <p>The <code>numberSign</code> field.</p>
         * @see java.lang.Integer
         */
        private Integer numberSign = 2;
        /**
         * <code>numberValue</code>
         * {@link java.lang.Integer} <p>The <code>numberValue</code> field.</p>
         * @see java.lang.Integer
         */
        private Integer numberValue = 1;
    }

    /**
     * <code>skipOfBefore</code>
     * <p>The of before method.</p>
     * @return {@link java.lang.Boolean} <p>The of before return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean skipOfBefore() {
        return this.delete.getBeforeSkip();
    }

    /**
     * <code>skipOfAfter</code>
     * <p>The of after method.</p>
     * @return {@link java.lang.Boolean} <p>The of after return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean skipOfAfter() {
        return this.delete.getAfterSkip();
    }

    /**
     * <code>judgeOfAccurate</code>
     * <p>The of accurate method.</p>
     * @return {@link java.lang.Boolean} <p>The of accurate return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean judgeOfAccurate() {
        return this.delete.getBooleanSign();
    }

    /**
     * <code>signOfBoolean</code>
     * <p>The of boolean method.</p>
     * @return {@link java.lang.Boolean} <p>The of boolean return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean signOfBoolean() {
        return this.delete.getBooleanSign();
    }

    /**
     * <code>signOfNumber</code>
     * <p>The of number method.</p>
     * @return {@link java.lang.Integer} <p>The of number return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer signOfNumber() {
        return this.delete.getNumberSign();
    }

    /**
     * <code>valueOfBoolean</code>
     * <p>The of boolean method.</p>
     * @return {@link java.lang.Boolean} <p>The of boolean return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean valueOfBoolean() {
        return this.delete.getBooleanValue();
    }

    /**
     * <code>valueOfNumber</code>
     * <p>The of number method.</p>
     * @return {@link java.lang.Integer} <p>The of number return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer valueOfNumber() {
        return this.delete.getNumberValue();
    }

}
