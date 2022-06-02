package io.github.nichetoolkit.rice.configure;

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

}
