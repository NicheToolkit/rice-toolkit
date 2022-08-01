package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rice.enums.DeleteType;
import io.github.nichetoolkit.rice.enums.RemoveType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <p>RiceBeanNameProperties</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rice.bean")
public class RiceBeanProperties {
    @NestedConfigurationProperty
    private BeanModel model = new BeanModel();
    @NestedConfigurationProperty
    private BeanKey key = new BeanKey();
    @NestedConfigurationProperty
    private BeanName name = new BeanName();
    /** 数据分段 */
    private Integer partition = 500;
    /** 数据删除模式 */
    @NestedConfigurationProperty
    private DeleteModel delete = new DeleteModel();

    public RiceBeanProperties() {
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

    public Integer getPartition() {
        if (this.partition > 1000) {
            return 1000;
        }
        if (this.partition <= 0) {
            return 0;
        }
        return this.partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public static class BeanModel{
        private Boolean uniqueEnabled = false;

        public BeanModel() {
        }

        public Boolean getUniqueEnabled() {
            return uniqueEnabled;
        }

        public void setUniqueEnabled(Boolean uniqueEnabled) {
            this.uniqueEnabled = uniqueEnabled;
        }
    }

    public static class BeanKey {
        private Boolean invadeEnabled = false;

        public BeanKey() {
        }

        public Boolean getInvadeEnabled() {
            return invadeEnabled;
        }

        public void setInvadeEnabled(Boolean invadeEnabled) {
            this.invadeEnabled = invadeEnabled;
        }
    }


    public static class BeanName{
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

        private RemoveType removeModel = RemoveType.BOOLEAN;

        /** 是否开启移除字段标记索引 */
        private boolean removeIndex = false;

        /** removeType BOOLEAN 模式 删除数据标记值 */
        private boolean booleanSign = true;

        /** removeType BOOLEAN 模式 未删除数据标记值 */
        private boolean booleanValue = false;

        /** removeType NUMBER 模式 删除数据标记值 */
        private Integer numberSign = 2;

        /** removeType NUMBER 模式 未删除数据标记值 */
        private Integer numberValue = 1;

        public DeleteModel() {
        }

        public DeleteType getDeleteModel() {
            return deleteModel;
        }

        public void setDeleteModel(DeleteType deleteModel) {
            this.deleteModel = deleteModel;
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

    public Boolean isNameNonnull() {
        return this.getName().getNonnullEnabled();
    }

    public Boolean isNameUnique() {
        return this.getName().getUniqueEnabled();
    }

    public Boolean isModelUnique() {
        return this.getModel().getUniqueEnabled();
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
        return RemoveType.sign(removeModel(),booleanSign(),numberSign());
    }

    public String removeValue() {
        return RemoveType.value(removeModel(),booleanValue(),numberValue());
    }

}
