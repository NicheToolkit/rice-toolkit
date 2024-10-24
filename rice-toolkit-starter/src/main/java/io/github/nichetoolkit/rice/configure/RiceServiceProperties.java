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

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rice.service")
public class RiceServiceProperties {
    public static final Integer MAX_PARTITION_SIZE = 2000;
    public static final Integer MIN_PARTITION_SIZE = 0;
    public static final Integer QUERY_SIZE = 2000;
    public static final Integer SAVE_SIZE = 500;
    public static final Integer DELETE_SIZE = 1000;

    private Boolean uniqueModel = false;
    private Boolean dynamicTable = false;
    private Boolean identityInvade = false;
    private Boolean identityCheck = true;
    private Boolean uniqueName = true;
    private Boolean nonnullName = true;

    private Boolean beforeSkip = true;

    private Boolean afterSkip = true;

    private DeleteMode deleteMode = DeleteMode.DELETE;



    @NestedConfigurationProperty
    private ServicePartition partition = new ServicePartition();
    @NestedConfigurationProperty
    private ServiceDelete delete = new ServiceDelete();

    @Setter
    @Getter
    public static class ServicePartition {
        private Integer querySize = QUERY_SIZE;
        private Integer saveSize = SAVE_SIZE;
        private Integer deleteSize = DELETE_SIZE;
    }

    @Setter
    @Getter
    public static class ServiceDelete {
        private Boolean accurateJudge = true;
        private LogicMode logicMode = LogicMode.CONFIG;
        private ConfigMark configMark = ConfigMark.NUMBER;
        private AutoMark autoMark = AutoMark.IDENTITY;
        private Object unmarkValue = ConfigMark.NUMBER_UNMARK;
        private Object markValue = ConfigMark.NUMBER_MARK;
    }

    public Integer partitionOfQuery() {
        if (this.partition.querySize > MAX_PARTITION_SIZE) {
            return QUERY_SIZE;
        }
        if (this.partition.querySize <= MIN_PARTITION_SIZE) {
            return QUERY_SIZE;
        }
        return this.partition.querySize;
    }

    public Integer partitionOfSave() {
        if (this.partition.saveSize > MAX_PARTITION_SIZE) {
            return SAVE_SIZE;
        }
        if (this.partition.saveSize <= MIN_PARTITION_SIZE) {
            return SAVE_SIZE;
        }
        return this.partition.saveSize;
    }

    public Integer partitionOfDelete() {
        if (this.partition.deleteSize > MAX_PARTITION_SIZE) {
            return DELETE_SIZE;
        }
        if (this.partition.deleteSize <= MIN_PARTITION_SIZE) {
            return DELETE_SIZE;
        }
        return this.partition.deleteSize;
    }

    public Boolean skipOfBefore() {
        return this.getBeforeSkip();
    }

    public Boolean skipOfAfter() {
        return this.getAfterSkip();
    }

    public Boolean judgeOfAccurate() {
        return this.delete.getAccurateJudge();
    }

    public LogicMode getLogicMode() {
        return this.delete.getLogicMode();
    }

    public ConfigMark getConfigMark() {
        return this.delete.getConfigMark();
    }

    public AutoMark getAutoMark() {
        return this.delete.getAutoMark();
    }

    public Object unmarkOfConfig() {
        return this.delete.getUnmarkValue();
    }

    public Object markOfConfig() {
        return this.delete.getMarkValue();
    }

}
