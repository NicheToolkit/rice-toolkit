package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.RemoveMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <code>RiceBeanProperties</code>
 * <p>The type rice bean properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rice.bean")
public class RiceBeanProperties {
    /**
     * <code>MAX_PARTITION_SIZE</code>
     * {@link java.lang.Integer} <p>the constant <code>MAX_PARTITION_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    public static final Integer MAX_PARTITION_SIZE = 2000;
    /**
     * <code>MIN_PARTITION_SIZE</code>
     * {@link java.lang.Integer} <p>the constant <code>MIN_PARTITION_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    public static final Integer MIN_PARTITION_SIZE = 0;
    /**
     * <code>QUERY_SIZE</code>
     * {@link java.lang.Integer} <p>the constant <code>QUERY_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    public static final Integer QUERY_SIZE = 2000;
    /**
     * <code>SAVE_SIZE</code>
     * {@link java.lang.Integer} <p>the constant <code>SAVE_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    public static final Integer SAVE_SIZE = 500;
    /**
     * <code>DELETE_SIZE</code>
     * {@link java.lang.Integer} <p>the constant <code>DELETE_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    public static final Integer DELETE_SIZE = 1000;

    @NestedConfigurationProperty
    private BeanModel model = new BeanModel();
    @NestedConfigurationProperty
    private BeanKey key = new BeanKey();
    @NestedConfigurationProperty
    private BeanName name = new BeanName();
    @NestedConfigurationProperty
    private BeanPartition partition = new BeanPartition();
    @NestedConfigurationProperty
    private BeanDelete delete = new BeanDelete();

    /**
     * <code>RiceBeanProperties</code>
     * Instantiates a new rice bean properties.
     */
    public RiceBeanProperties() {
    }

    /**
     * <code>BeanPartition</code>
     * <p>The type bean partition class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class BeanPartition {
        private Integer querySize = QUERY_SIZE;
        private Integer saveSize = SAVE_SIZE;
        private Integer deleteSize = DELETE_SIZE;

        /**
         * <code>BeanPartition</code>
         * Instantiates a new bean partition.
         */
        public BeanPartition() {
        }

        /**
         * <code>getQuerySize</code>
         * <p>the query size getter method.</p>
         * @return {@link java.lang.Integer} <p>the query size return object is <code>Integer</code> type.</p>
         * @see java.lang.Integer
         */
        public Integer getQuerySize() {
            return querySize;
        }

        /**
         * <code>setQuerySize</code>
         * <p>the query size setter method.</p>
         * @param querySize {@link java.lang.Integer} <p>the query size parameter is <code>Integer</code> type.</p>
         * @see java.lang.Integer
         */
        public void setQuerySize(Integer querySize) {
            this.querySize = querySize;
        }

        /**
         * <code>getSaveSize</code>
         * <p>the save size getter method.</p>
         * @return {@link java.lang.Integer} <p>the save size return object is <code>Integer</code> type.</p>
         * @see java.lang.Integer
         */
        public Integer getSaveSize() {
            return saveSize;
        }

        /**
         * <code>setSaveSize</code>
         * <p>the save size setter method.</p>
         * @param saveSize {@link java.lang.Integer} <p>the save size parameter is <code>Integer</code> type.</p>
         * @see java.lang.Integer
         */
        public void setSaveSize(Integer saveSize) {
            this.saveSize = saveSize;
        }

        /**
         * <code>getDeleteSize</code>
         * <p>the delete size getter method.</p>
         * @return {@link java.lang.Integer} <p>the delete size return object is <code>Integer</code> type.</p>
         * @see java.lang.Integer
         */
        public Integer getDeleteSize() {
            return deleteSize;
        }

        /**
         * <code>setDeleteSize</code>
         * <p>the delete size setter method.</p>
         * @param deleteSize {@link java.lang.Integer} <p>the delete size parameter is <code>Integer</code> type.</p>
         * @see java.lang.Integer
         */
        public void setDeleteSize(Integer deleteSize) {
            this.deleteSize = deleteSize;
        }
    }

    /**
     * <code>getPartition</code>
     * <p>the partition getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanPartition} <p>the partition return object is <code>BeanPartition</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanPartition
     */
    public BeanPartition getPartition() {
        return partition;
    }

    /**
     * <code>setPartition</code>
     * <p>the partition setter method.</p>
     * @param partition {@link io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanPartition} <p>the partition parameter is <code>BeanPartition</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanPartition
     */
    public void setPartition(BeanPartition partition) {
        this.partition = partition;
    }

    /**
     * <code>getPartitionQuery</code>
     * <p>the partition query getter method.</p>
     * @return {@link java.lang.Integer} <p>the partition query return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer getPartitionQuery() {
        if (this.partition.querySize > MAX_PARTITION_SIZE) {
            return QUERY_SIZE;
        }
        if (this.partition.querySize <= MIN_PARTITION_SIZE) {
            return QUERY_SIZE;
        }
        return this.partition.querySize;
    }


    /**
     * <code>getPartitionSave</code>
     * <p>the partition save getter method.</p>
     * @return {@link java.lang.Integer} <p>the partition save return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer getPartitionSave() {
        if (this.partition.saveSize > MAX_PARTITION_SIZE) {
            return SAVE_SIZE;
        }
        if (this.partition.saveSize <= MIN_PARTITION_SIZE) {
            return SAVE_SIZE;
        }
        return this.partition.saveSize;
    }

    /**
     * <code>getPartitionDelete</code>
     * <p>the partition delete getter method.</p>
     * @return {@link java.lang.Integer} <p>the partition delete return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer getPartitionDelete() {
        if (this.partition.deleteSize > MAX_PARTITION_SIZE) {
            return DELETE_SIZE;
        }
        if (this.partition.deleteSize <= MIN_PARTITION_SIZE) {
            return DELETE_SIZE;
        }
        return this.partition.deleteSize;
    }


    /**
     * <code>getModel</code>
     * <p>the model getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanModel} <p>the model return object is <code>BeanModel</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanModel
     */
    public BeanModel getModel() {
        return model;
    }

    /**
     * <code>setModel</code>
     * <p>the model setter method.</p>
     * @param model {@link io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanModel} <p>the model parameter is <code>BeanModel</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanModel
     */
    public void setModel(BeanModel model) {
        this.model = model;
    }

    /**
     * <code>getName</code>
     * <p>the name getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanName} <p>the name return object is <code>BeanName</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanName
     */
    public BeanName getName() {
        return name;
    }

    /**
     * <code>setName</code>
     * <p>the name setter method.</p>
     * @param name {@link io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanName} <p>the name parameter is <code>BeanName</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanName
     */
    public void setName(BeanName name) {
        this.name = name;
    }

    /**
     * <code>getKey</code>
     * <p>the key getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanKey} <p>the key return object is <code>BeanKey</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanKey
     */
    public BeanKey getKey() {
        return key;
    }

    /**
     * <code>setKey</code>
     * <p>the key setter method.</p>
     * @param key {@link io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanKey} <p>the key parameter is <code>BeanKey</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanKey
     */
    public void setKey(BeanKey key) {
        this.key = key;
    }

    /**
     * <code>getDelete</code>
     * <p>the delete getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanDelete} <p>the delete return object is <code>BeanDelete</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanDelete
     */
    public BeanDelete getDelete() {
        return delete;
    }

    /**
     * <code>setDelete</code>
     * <p>the delete setter method.</p>
     * @param delete {@link io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanDelete} <p>the delete parameter is <code>BeanDelete</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceBeanProperties.BeanDelete
     */
    public void setDelete(BeanDelete delete) {
        this.delete = delete;
    }

    /**
     * <code>BeanModel</code>
     * <p>The type bean model class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class BeanModel {
        private Boolean uniqueEnabled = false;
        private Boolean dynamicTableEnabled = false;

        /**
         * <code>BeanModel</code>
         * Instantiates a new bean model.
         */
        public BeanModel() {
        }

        /**
         * <code>getUniqueEnabled</code>
         * <p>the unique enabled getter method.</p>
         * @return {@link java.lang.Boolean} <p>the unique enabled return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getUniqueEnabled() {
            return uniqueEnabled;
        }

        /**
         * <code>setUniqueEnabled</code>
         * <p>the unique enabled setter method.</p>
         * @param uniqueEnabled {@link java.lang.Boolean} <p>the unique enabled parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setUniqueEnabled(Boolean uniqueEnabled) {
            this.uniqueEnabled = uniqueEnabled;
        }

        /**
         * <code>getDynamicTableEnabled</code>
         * <p>the dynamic table enabled getter method.</p>
         * @return {@link java.lang.Boolean} <p>the dynamic table enabled return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getDynamicTableEnabled() {
            return dynamicTableEnabled;
        }

        /**
         * <code>setDynamicTableEnabled</code>
         * <p>the dynamic table enabled setter method.</p>
         * @param dynamicTableEnabled {@link java.lang.Boolean} <p>the dynamic table enabled parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setDynamicTableEnabled(Boolean dynamicTableEnabled) {
            this.dynamicTableEnabled = dynamicTableEnabled;
        }
    }

    /**
     * <code>BeanKey</code>
     * <p>The type bean key class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class BeanKey {
        private Boolean invadeEnabled = false;
        private Boolean existEnabled = true;

        /**
         * <code>BeanKey</code>
         * Instantiates a new bean key.
         */
        public BeanKey() {
        }

        /**
         * <code>getInvadeEnabled</code>
         * <p>the invade enabled getter method.</p>
         * @return {@link java.lang.Boolean} <p>the invade enabled return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getInvadeEnabled() {
            return invadeEnabled;
        }

        /**
         * <code>setInvadeEnabled</code>
         * <p>the invade enabled setter method.</p>
         * @param invadeEnabled {@link java.lang.Boolean} <p>the invade enabled parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setInvadeEnabled(Boolean invadeEnabled) {
            this.invadeEnabled = invadeEnabled;
        }

        /**
         * <code>getExistEnabled</code>
         * <p>the exist enabled getter method.</p>
         * @return {@link java.lang.Boolean} <p>the exist enabled return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getExistEnabled() {
            return existEnabled;
        }

        /**
         * <code>setExistEnabled</code>
         * <p>the exist enabled setter method.</p>
         * @param existEnabled {@link java.lang.Boolean} <p>the exist enabled parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setExistEnabled(Boolean existEnabled) {
            this.existEnabled = existEnabled;
        }
    }


    /**
     * <code>BeanName</code>
     * <p>The type bean name class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class BeanName {
        private Boolean uniqueEnabled = true;
        private Boolean nonnullEnabled = true;

        /**
         * <code>BeanName</code>
         * Instantiates a new bean name.
         */
        public BeanName() {
        }

        /**
         * <code>getUniqueEnabled</code>
         * <p>the unique enabled getter method.</p>
         * @return {@link java.lang.Boolean} <p>the unique enabled return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getUniqueEnabled() {
            return uniqueEnabled;
        }

        /**
         * <code>setUniqueEnabled</code>
         * <p>the unique enabled setter method.</p>
         * @param uniqueEnabled {@link java.lang.Boolean} <p>the unique enabled parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setUniqueEnabled(Boolean uniqueEnabled) {
            this.uniqueEnabled = uniqueEnabled;
        }

        /**
         * <code>getNonnullEnabled</code>
         * <p>the nonnull enabled getter method.</p>
         * @return {@link java.lang.Boolean} <p>the nonnull enabled return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getNonnullEnabled() {
            return nonnullEnabled;
        }

        /**
         * <code>setNonnullEnabled</code>
         * <p>the nonnull enabled setter method.</p>
         * @param nonnullEnabled {@link java.lang.Boolean} <p>the nonnull enabled parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setNonnullEnabled(Boolean nonnullEnabled) {
            this.nonnullEnabled = nonnullEnabled;
        }
    }

    /**
     * <code>BeanDelete</code>
     * <p>The type bean delete class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class BeanDelete {

        private DeleteMode deleteMode = DeleteMode.NONE;

        private RemoveMode removeMode = RemoveMode.NUMBER;

        private boolean beforeSkip = true;

        private boolean afterSkip = true;

        private boolean pinpointEnabled = true;

        private boolean booleanSign = true;

        private boolean booleanValue = false;

        private Integer numberSign = 2;

        private Integer numberValue = 1;

        /**
         * <code>BeanDelete</code>
         * Instantiates a new bean delete.
         */
        public BeanDelete() {
        }

        /**
         * <code>getDeleteMode</code>
         * <p>the delete mode getter method.</p>
         * @return {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>the delete mode return object is <code>DeleteMode</code> type.</p>
         * @see io.github.nichetoolkit.rice.enums.DeleteMode
         */
        public DeleteMode getDeleteMode() {
            return deleteMode;
        }

        /**
         * <code>setDeleteMode</code>
         * <p>the delete mode setter method.</p>
         * @param deleteMode {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>the delete mode parameter is <code>DeleteMode</code> type.</p>
         * @see io.github.nichetoolkit.rice.enums.DeleteMode
         */
        public void setDeleteMode(DeleteMode deleteMode) {
            this.deleteMode = deleteMode;
        }

        /**
         * <code>isBeforeSkip</code>
         * <p>the before skip method.</p>
         * @return boolean <p>the before skip return object is <code>boolean</code> type.</p>
         */
        public boolean isBeforeSkip() {
            return beforeSkip;
        }

        /**
         * <code>setBeforeSkip</code>
         * <p>the before skip setter method.</p>
         * @param beforeSkip boolean <p>the before skip parameter is <code>boolean</code> type.</p>
         */
        public void setBeforeSkip(boolean beforeSkip) {
            this.beforeSkip = beforeSkip;
        }

        /**
         * <code>isAfterSkip</code>
         * <p>the after skip method.</p>
         * @return boolean <p>the after skip return object is <code>boolean</code> type.</p>
         */
        public boolean isAfterSkip() {
            return afterSkip;
        }

        /**
         * <code>setAfterSkip</code>
         * <p>the after skip setter method.</p>
         * @param afterSkip boolean <p>the after skip parameter is <code>boolean</code> type.</p>
         */
        public void setAfterSkip(boolean afterSkip) {
            this.afterSkip = afterSkip;
        }

        /**
         * <code>getRemoveMode</code>
         * <p>the remove mode getter method.</p>
         * @return {@link io.github.nichetoolkit.rice.enums.RemoveMode} <p>the remove mode return object is <code>RemoveMode</code> type.</p>
         * @see io.github.nichetoolkit.rice.enums.RemoveMode
         */
        public RemoveMode getRemoveMode() {
            return removeMode;
        }

        /**
         * <code>setRemoveMode</code>
         * <p>the remove mode setter method.</p>
         * @param removeMode {@link io.github.nichetoolkit.rice.enums.RemoveMode} <p>the remove mode parameter is <code>RemoveMode</code> type.</p>
         * @see io.github.nichetoolkit.rice.enums.RemoveMode
         */
        public void setRemoveMode(RemoveMode removeMode) {
            this.removeMode = removeMode;
        }

        public boolean isPinpointEnabled() {
            return pinpointEnabled;
        }

        public void setPinpointEnabled(boolean pinpointEnabled) {
            this.pinpointEnabled = pinpointEnabled;
        }

        /**
         * <code>isBooleanValue</code>
         * <p>the boolean value method.</p>
         * @return boolean <p>the boolean value return object is <code>boolean</code> type.</p>
         */
        public boolean isBooleanValue() {
            return booleanValue;
        }

        /**
         * <code>setBooleanValue</code>
         * <p>the boolean value setter method.</p>
         * @param booleanValue boolean <p>the boolean value parameter is <code>boolean</code> type.</p>
         */
        public void setBooleanValue(boolean booleanValue) {
            this.booleanValue = booleanValue;
        }

        /**
         * <code>getNumberValue</code>
         * <p>the number value getter method.</p>
         * @return {@link java.lang.Integer} <p>the number value return object is <code>Integer</code> type.</p>
         * @see java.lang.Integer
         */
        public Integer getNumberValue() {
            return numberValue;
        }

        /**
         * <code>setNumberValue</code>
         * <p>the number value setter method.</p>
         * @param numberValue {@link java.lang.Integer} <p>the number value parameter is <code>Integer</code> type.</p>
         * @see java.lang.Integer
         */
        public void setNumberValue(Integer numberValue) {
            this.numberValue = numberValue;
        }

        /**
         * <code>isBooleanSign</code>
         * <p>the boolean sign method.</p>
         * @return boolean <p>the boolean sign return object is <code>boolean</code> type.</p>
         */
        public boolean isBooleanSign() {
            return booleanSign;
        }

        /**
         * <code>setBooleanSign</code>
         * <p>the boolean sign setter method.</p>
         * @param booleanSign boolean <p>the boolean sign parameter is <code>boolean</code> type.</p>
         */
        public void setBooleanSign(boolean booleanSign) {
            this.booleanSign = booleanSign;
        }

        /**
         * <code>getNumberSign</code>
         * <p>the number sign getter method.</p>
         * @return {@link java.lang.Integer} <p>the number sign return object is <code>Integer</code> type.</p>
         * @see java.lang.Integer
         */
        public Integer getNumberSign() {
            return numberSign;
        }

        /**
         * <code>setNumberSign</code>
         * <p>the number sign setter method.</p>
         * @param numberSign {@link java.lang.Integer} <p>the number sign parameter is <code>Integer</code> type.</p>
         * @see java.lang.Integer
         */
        public void setNumberSign(Integer numberSign) {
            this.numberSign = numberSign;
        }
    }

    /**
     * <code>isIdInvade</code>
     * <p>the id invade method.</p>
     * @return {@link java.lang.Boolean} <p>the id invade return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean isIdInvade() {
        return this.getKey().getInvadeEnabled();
    }

    /**
     * <code>isIdExist</code>
     * <p>the id exist method.</p>
     * @return {@link java.lang.Boolean} <p>the id exist return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean isIdExist() {
        return this.getKey().getExistEnabled();
    }

    /**
     * <code>isNameNonnull</code>
     * <p>the name nonnull method.</p>
     * @return {@link java.lang.Boolean} <p>the name nonnull return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean isNameNonnull() {
        return this.getName().getNonnullEnabled();
    }

    /**
     * <code>isNameUnique</code>
     * <p>the name unique method.</p>
     * @return {@link java.lang.Boolean} <p>the name unique return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean isNameUnique() {
        return this.getName().getUniqueEnabled();
    }

    /**
     * <code>isModelUnique</code>
     * <p>the model unique method.</p>
     * @return {@link java.lang.Boolean} <p>the model unique return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean isModelUnique() {
        return this.getModel().getUniqueEnabled();
    }

    /**
     * <code>isDynamicTable</code>
     * <p>the dynamic table method.</p>
     * @return {@link java.lang.Boolean} <p>the dynamic table return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean isDynamicTable() {
        return this.getModel().getDynamicTableEnabled();
    }

    /**
     * <code>isBeforeSkip</code>
     * <p>the before skip method.</p>
     * @return {@link java.lang.Boolean} <p>the before skip return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean isBeforeSkip() {
        return this.getDelete().isBeforeSkip();
    }

    /**
     * <code>isAfterSkip</code>
     * <p>the after skip method.</p>
     * @return {@link java.lang.Boolean} <p>the after skip return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean isAfterSkip() {
        return this.getDelete().isAfterSkip();
    }

    /**
     * <code>deleteMode</code>
     * <p>the mode method.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>the mode return object is <code>DeleteMode</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.DeleteMode
     */
    public DeleteMode deleteMode() {
        return this.getDelete().getDeleteMode();
    }

    /**
     * <code>removeMode</code>
     * <p>the mode method.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.RemoveMode} <p>the mode return object is <code>RemoveMode</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.RemoveMode
     */
    public RemoveMode removeMode() {
        return this.getDelete().getRemoveMode();
    }

    /**
     * <code>pinpointSign</code>
     * <p>the sign method.</p>
     * @return {@link java.lang.Boolean} <p>the sign return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean pinpointEnabled() {
        return this.getDelete().isPinpointEnabled();
    }

    /**
     * <code>booleanSign</code>
     * <p>the sign method.</p>
     * @return {@link java.lang.Boolean} <p>the sign return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean booleanSign() {
        return this.getDelete().isBooleanSign();
    }

    /**
     * <code>numberSign</code>
     * <p>the sign method.</p>
     * @return {@link java.lang.Integer} <p>the sign return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer numberSign() {
        return this.getDelete().getNumberSign();
    }

    /**
     * <code>booleanValue</code>
     * <p>the value method.</p>
     * @return {@link java.lang.Boolean} <p>the value return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean booleanValue() {
        return this.getDelete().isBooleanValue();
    }

    /**
     * <code>numberValue</code>
     * <p>the value method.</p>
     * @return {@link java.lang.Integer} <p>the value return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer numberValue() {
        return this.getDelete().getNumberValue();
    }

    /**
     * <code>removeSign</code>
     * <p>the sign method.</p>
     * @return {@link java.lang.String} <p>the sign return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String removeSign() {
        return RemoveMode.sign(removeMode(), booleanSign(), numberSign());
    }

    /**
     * <code>removeValue</code>
     * <p>the value method.</p>
     * @return {@link java.lang.String} <p>the value return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String removeValue() {
        return RemoveMode.value(removeMode(), booleanValue(), numberValue());
    }

}
