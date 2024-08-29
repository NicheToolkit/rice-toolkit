package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rice.enums.DeleteType;
import io.github.nichetoolkit.rice.enums.RemoveType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "nichetoolkit.rice.bean")
public class RiceBeanProperties {
    public static final Integer MAX_PARTITION_SIZE = 2000;
    public static final Integer MIN_PARTITION_SIZE = 0;
    public static final Integer QUERY_SIZE = 2000;
    public static final Integer SAVE_SIZE = 500;
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
    private DeleteModel delete = new DeleteModel();

    public RiceBeanProperties() {
    }

    public static class BeanPartition {
        private Integer querySize = QUERY_SIZE;
        private Integer saveSize = SAVE_SIZE;
        private Integer deleteSize = DELETE_SIZE;

        public BeanPartition() {
        }

        public Integer getQuerySize() {
            return querySize;
        }

        public void setQuerySize(Integer querySize) {
            this.querySize = querySize;
        }

        public Integer getSaveSize() {
            return saveSize;
        }

        public void setSaveSize(Integer saveSize) {
            this.saveSize = saveSize;
        }

        public Integer getDeleteSize() {
            return deleteSize;
        }

        public void setDeleteSize(Integer deleteSize) {
            this.deleteSize = deleteSize;
        }
    }

    public BeanPartition getPartition() {
        return partition;
    }

    public void setPartition(BeanPartition partition) {
        this.partition = partition;
    }

    public Integer getPartitionQuery() {
        if (this.partition.querySize > MAX_PARTITION_SIZE) {
            return QUERY_SIZE;
        }
        if (this.partition.querySize <= MIN_PARTITION_SIZE) {
            return QUERY_SIZE;
        }
        return this.partition.querySize;
    }


    public Integer getPartitionSave() {
        if (this.partition.saveSize > MAX_PARTITION_SIZE) {
            return SAVE_SIZE;
        }
        if (this.partition.saveSize <= MIN_PARTITION_SIZE) {
            return SAVE_SIZE;
        }
        return this.partition.saveSize;
    }

    public Integer getPartitionDelete() {
        if (this.partition.deleteSize > MAX_PARTITION_SIZE) {
            return DELETE_SIZE;
        }
        if (this.partition.deleteSize <= MIN_PARTITION_SIZE) {
            return DELETE_SIZE;
        }
        return this.partition.deleteSize;
    }


    public BeanModel getModel() {
        return model;
    }

    public void setModel(BeanModel model) {
        this.model = model;
    }

    public BeanName getName() {
        return name;
    }

    public void setName(BeanName name) {
        this.name = name;
    }

    public BeanKey getKey() {
        return key;
    }

    public void setKey(BeanKey key) {
        this.key = key;
    }

    public DeleteModel getDelete() {
        return delete;
    }

    public void setDelete(DeleteModel delete) {
        this.delete = delete;
    }

    public static class BeanModel {
        private Boolean uniqueEnabled = false;
        private Boolean dynamicTableEnabled = false;

        public BeanModel() {
        }

        public Boolean getUniqueEnabled() {
            return uniqueEnabled;
        }

        public void setUniqueEnabled(Boolean uniqueEnabled) {
            this.uniqueEnabled = uniqueEnabled;
        }

        public Boolean getDynamicTableEnabled() {
            return dynamicTableEnabled;
        }

        public void setDynamicTableEnabled(Boolean dynamicTableEnabled) {
            this.dynamicTableEnabled = dynamicTableEnabled;
        }
    }

    public static class BeanKey {
        private Boolean invadeEnabled = false;
        private Boolean existEnabled = true;

        public BeanKey() {
        }

        public Boolean getInvadeEnabled() {
            return invadeEnabled;
        }

        public void setInvadeEnabled(Boolean invadeEnabled) {
            this.invadeEnabled = invadeEnabled;
        }

        public Boolean getExistEnabled() {
            return existEnabled;
        }

        public void setExistEnabled(Boolean existEnabled) {
            this.existEnabled = existEnabled;
        }
    }


    public static class BeanName {
        private Boolean uniqueEnabled = true;
        private Boolean nonnullEnabled = true;

        public BeanName() {
        }

        public Boolean getUniqueEnabled() {
            return uniqueEnabled;
        }

        public void setUniqueEnabled(Boolean uniqueEnabled) {
            this.uniqueEnabled = uniqueEnabled;
        }

        public Boolean getNonnullEnabled() {
            return nonnullEnabled;
        }

        public void setNonnullEnabled(Boolean nonnullEnabled) {
            this.nonnullEnabled = nonnullEnabled;
        }
    }

    public static class DeleteModel {

        private DeleteType deleteModel = DeleteType.NONE;

        private RemoveType removeModel = RemoveType.NUMBER;

        private boolean beforeSkip = true;

        private boolean afterSkip = true;

        private boolean removeIndex = false;

        private boolean booleanSign = true;

        private boolean booleanValue = false;

        private Integer numberSign = 2;

        private Integer numberValue = 1;

        public DeleteModel() {
        }

        public DeleteType getDeleteModel() {
            return deleteModel;
        }

        public void setDeleteModel(DeleteType deleteModel) {
            this.deleteModel = deleteModel;
        }

        public boolean isBeforeSkip() {
            return beforeSkip;
        }

        public void setBeforeSkip(boolean beforeSkip) {
            this.beforeSkip = beforeSkip;
        }

        public boolean isAfterSkip() {
            return afterSkip;
        }

        public void setAfterSkip(boolean afterSkip) {
            this.afterSkip = afterSkip;
        }

        public RemoveType getRemoveModel() {
            return removeModel;
        }

        public void setRemoveModel(RemoveType removeModel) {
            this.removeModel = removeModel;
        }

        public boolean isRemoveIndex() {
            return removeIndex;
        }

        public void setRemoveIndex(boolean removeIndex) {
            this.removeIndex = removeIndex;
        }

        public boolean isBooleanValue() {
            return booleanValue;
        }

        public void setBooleanValue(boolean booleanValue) {
            this.booleanValue = booleanValue;
        }

        public Integer getNumberValue() {
            return numberValue;
        }

        public void setNumberValue(Integer numberValue) {
            this.numberValue = numberValue;
        }

        public boolean isBooleanSign() {
            return booleanSign;
        }

        public void setBooleanSign(boolean booleanSign) {
            this.booleanSign = booleanSign;
        }

        public Integer getNumberSign() {
            return numberSign;
        }

        public void setNumberSign(Integer numberSign) {
            this.numberSign = numberSign;
        }
    }

    public Boolean isIdInvade() {
        return this.getKey().getInvadeEnabled();
    }

    public Boolean isIdExist() {
        return this.getKey().getExistEnabled();
    }

    public Boolean isNameNonnull() {
        return this.getName().getNonnullEnabled();
    }

    public Boolean isNameUnique() {
        return this.getName().getUniqueEnabled();
    }

    public Boolean isModelUnique() {
        return this.getModel().getUniqueEnabled();
    }

    public Boolean isDynamicTable() {
        return this.getModel().getDynamicTableEnabled();
    }

    public Boolean isBeforeSkip() {
        return this.getDelete().isBeforeSkip();
    }

    public Boolean isAfterSkip() {
        return this.getDelete().isAfterSkip();
    }

    public DeleteType deleteModel() {
        return this.getDelete().getDeleteModel();
    }

    public RemoveType removeModel() {
        return this.getDelete().getRemoveModel();
    }

    public Boolean removeIndex() {
        return this.getDelete().isRemoveIndex();
    }

    public Boolean booleanSign() {
        return this.getDelete().isBooleanSign();
    }

    public Integer numberSign() {
        return this.getDelete().getNumberSign();
    }

    public Boolean booleanValue() {
        return this.getDelete().isBooleanValue();
    }

    public Integer numberValue() {
        return this.getDelete().getNumberValue();
    }

    public String removeSign() {
        return RemoveType.sign(removeModel(), booleanSign(), numberSign());
    }

    public String removeValue() {
        return RemoveType.value(removeModel(), booleanValue(), numberValue());
    }

}
