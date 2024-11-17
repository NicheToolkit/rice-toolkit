package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rice.enums.AutoMark;
import io.github.nichetoolkit.rice.enums.ConfigMark;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.LogicMode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <code>RiceServiceProperties</code>
 * <p>The rice service properties class.</p>
 * @see  lombok.Setter
 * @see  lombok.Getter
 * @see  org.springframework.stereotype.Component
 * @see  org.springframework.boot.context.properties.ConfigurationProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rice.service")
public class RiceServiceProperties {
    /**
     * <code>MAX_PARTITION_SIZE</code>
     * {@link java.lang.Integer} <p>The constant <code>MAX_PARTITION_SIZE</code> field.</p>
     * @see  java.lang.Integer
     */
    public static final Integer MAX_PARTITION_SIZE = 2000;
    /**
     * <code>MIN_PARTITION_SIZE</code>
     * {@link java.lang.Integer} <p>The constant <code>MIN_PARTITION_SIZE</code> field.</p>
     * @see  java.lang.Integer
     */
    public static final Integer MIN_PARTITION_SIZE = 0;
    /**
     * <code>QUERY_SIZE</code>
     * {@link java.lang.Integer} <p>The constant <code>QUERY_SIZE</code> field.</p>
     * @see  java.lang.Integer
     */
    public static final Integer QUERY_SIZE = 2000;
    /**
     * <code>SAVE_SIZE</code>
     * {@link java.lang.Integer} <p>The constant <code>SAVE_SIZE</code> field.</p>
     * @see  java.lang.Integer
     */
    public static final Integer SAVE_SIZE = 500;
    /**
     * <code>DELETE_SIZE</code>
     * {@link java.lang.Integer} <p>The constant <code>DELETE_SIZE</code> field.</p>
     * @see  java.lang.Integer
     */
    public static final Integer DELETE_SIZE = 1000;

    /**
     * <code>uniqueModel</code>
     * {@link java.lang.Boolean} <p>The <code>uniqueModel</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean uniqueModel = false;
    /**
     * <code>dynamicTable</code>
     * {@link java.lang.Boolean} <p>The <code>dynamicTable</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean dynamicTable = false;
    /**
     * <code>identityInvade</code>
     * {@link java.lang.Boolean} <p>The <code>identityInvade</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean identityInvade = false;
    /**
     * <code>identityCheck</code>
     * {@link java.lang.Boolean} <p>The <code>identityCheck</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean identityCheck = true;
    /**
     * <code>uniqueName</code>
     * {@link java.lang.Boolean} <p>The <code>uniqueName</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean uniqueName = true;
    /**
     * <code>nonnullName</code>
     * {@link java.lang.Boolean} <p>The <code>nonnullName</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean nonnullName = true;

    /**
     * <code>beforeSkip</code>
     * {@link java.lang.Boolean} <p>The <code>beforeSkip</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean beforeSkip = true;

    /**
     * <code>afterSkip</code>
     * {@link java.lang.Boolean} <p>The <code>afterSkip</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean afterSkip = true;

    /**
     * <code>deleteMode</code>
     * {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>The <code>deleteMode</code> field.</p>
     * @see  io.github.nichetoolkit.rice.enums.DeleteMode
     */
    private DeleteMode deleteMode = DeleteMode.DELETE;


    /**
     * <code>partition</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties.ServicePartition} <p>The <code>partition</code> field.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceServiceProperties.ServicePartition
     * @see  org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private ServicePartition partition = new ServicePartition();
    /**
     * <code>delete</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties.ServiceDelete} <p>The <code>delete</code> field.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceServiceProperties.ServiceDelete
     * @see  org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private ServiceDelete delete = new ServiceDelete();

    /**
     * <code>ServicePartition</code>
     * <p>The service partition class.</p>
     * @see  lombok.Setter
     * @see  lombok.Getter
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    @Setter
    @Getter
    public static class ServicePartition {
        /**
         * <code>querySize</code>
         * {@link java.lang.Integer} <p>The <code>querySize</code> field.</p>
         * @see  java.lang.Integer
         */
        private Integer querySize = QUERY_SIZE;
        /**
         * <code>saveSize</code>
         * {@link java.lang.Integer} <p>The <code>saveSize</code> field.</p>
         * @see  java.lang.Integer
         */
        private Integer saveSize = SAVE_SIZE;
        /**
         * <code>deleteSize</code>
         * {@link java.lang.Integer} <p>The <code>deleteSize</code> field.</p>
         * @see  java.lang.Integer
         */
        private Integer deleteSize = DELETE_SIZE;
    }

    /**
     * <code>ServiceDelete</code>
     * <p>The service delete class.</p>
     * @see  lombok.Setter
     * @see  lombok.Getter
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    @Setter
    @Getter
    public static class ServiceDelete {
        /**
         * <code>accurateJudge</code>
         * {@link java.lang.Boolean} <p>The <code>accurateJudge</code> field.</p>
         * @see  java.lang.Boolean
         */
        private Boolean accurateJudge = true;
        /**
         * <code>logicMode</code>
         * {@link io.github.nichetoolkit.rice.enums.LogicMode} <p>The <code>logicMode</code> field.</p>
         * @see  io.github.nichetoolkit.rice.enums.LogicMode
         */
        private LogicMode logicMode = LogicMode.CONFIG;
        /**
         * <code>configMark</code>
         * {@link io.github.nichetoolkit.rice.enums.ConfigMark} <p>The <code>configMark</code> field.</p>
         * @see  io.github.nichetoolkit.rice.enums.ConfigMark
         */
        private ConfigMark configMark = ConfigMark.NUMBER;
        /**
         * <code>autoMark</code>
         * {@link io.github.nichetoolkit.rice.enums.AutoMark} <p>The <code>autoMark</code> field.</p>
         * @see  io.github.nichetoolkit.rice.enums.AutoMark
         */
        private AutoMark autoMark = AutoMark.IDENTITY;
        /**
         * <code>unmarkValue</code>
         * {@link java.lang.Object} <p>The <code>unmarkValue</code> field.</p>
         * @see  java.lang.Object
         */
        private Object unmarkValue;
        /**
         * <code>markValue</code>
         * {@link java.lang.Object} <p>The <code>markValue</code> field.</p>
         * @see  java.lang.Object
         */
        private Object markValue;
    }

    /**
     * <code>partitionOfQuery</code>
     * <p>The partition of query method.</p>
     * @return  {@link java.lang.Integer} <p>The partition of query return object is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
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
     * <p>The partition of save method.</p>
     * @return  {@link java.lang.Integer} <p>The partition of save return object is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
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
     * <p>The partition of delete method.</p>
     * @return  {@link java.lang.Integer} <p>The partition of delete return object is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
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
     * <code>skipOfBefore</code>
     * <p>The skip of before method.</p>
     * @return  {@link java.lang.Boolean} <p>The skip of before return object is <code>Boolean</code> type.</p>
     * @see  java.lang.Boolean
     */
    public Boolean skipOfBefore() {
        return this.getBeforeSkip();
    }

    /**
     * <code>skipOfAfter</code>
     * <p>The skip of after method.</p>
     * @return  {@link java.lang.Boolean} <p>The skip of after return object is <code>Boolean</code> type.</p>
     * @see  java.lang.Boolean
     */
    public Boolean skipOfAfter() {
        return this.getAfterSkip();
    }

    /**
     * <code>judgeOfAccurate</code>
     * <p>The judge of accurate method.</p>
     * @return  {@link java.lang.Boolean} <p>The judge of accurate return object is <code>Boolean</code> type.</p>
     * @see  java.lang.Boolean
     */
    public Boolean judgeOfAccurate() {
        return this.delete.getAccurateJudge();
    }

    /**
     * <code>getLogicMode</code>
     * <p>The get logic mode getter method.</p>
     * @return  {@link io.github.nichetoolkit.rice.enums.LogicMode} <p>The get logic mode return object is <code>LogicMode</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.LogicMode
     */
    public LogicMode getLogicMode() {
        return this.delete.getLogicMode();
    }

    /**
     * <code>getConfigMark</code>
     * <p>The get config mark getter method.</p>
     * @return  {@link io.github.nichetoolkit.rice.enums.ConfigMark} <p>The get config mark return object is <code>ConfigMark</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.ConfigMark
     */
    public ConfigMark getConfigMark() {
        return this.delete.getConfigMark();
    }

    /**
     * <code>getAutoMark</code>
     * <p>The get auto mark getter method.</p>
     * @return  {@link io.github.nichetoolkit.rice.enums.AutoMark} <p>The get auto mark return object is <code>AutoMark</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.AutoMark
     */
    public AutoMark getAutoMark() {
        return this.delete.getAutoMark();
    }

    /**
     * <code>unmarkOfConfig</code>
     * <p>The unmark of config method.</p>
     * @return  {@link java.lang.Object} <p>The unmark of config return object is <code>Object</code> type.</p>
     * @see  java.lang.Object
     */
    public Object unmarkOfConfig() {
        return this.delete.getUnmarkValue();
    }

    /**
     * <code>markOfConfig</code>
     * <p>The mark of config method.</p>
     * @return  {@link java.lang.Object} <p>The mark of config return object is <code>Object</code> type.</p>
     * @see  java.lang.Object
     */
    public Object markOfConfig() {
        return this.delete.getMarkValue();
    }

}
