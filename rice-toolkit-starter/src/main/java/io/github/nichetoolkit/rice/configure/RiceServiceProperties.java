package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.RemoveMode;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Data
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
    private DeleteMode deleteMode = DeleteMode.NONE;
    private RemoveMode removeMode = RemoveMode.NUMBER;

    @NestedConfigurationProperty
    private ServicePartition partition = new ServicePartition();
    @NestedConfigurationProperty
    private ServiceDelete delete = new ServiceDelete();

    @Data
    public static class ServicePartition {
        private Integer querySize = QUERY_SIZE;
        private Integer saveSize = SAVE_SIZE;
        private Integer deleteSize = DELETE_SIZE;
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

    @Data
    public static class ServiceDelete {
        private Boolean beforeSkip = true;
        private Boolean afterSkip = true;
        private Boolean accurateJudge = true;
        private Boolean booleanSign = true;
        private Boolean booleanValue = false;
        private Integer numberSign = 2;
        private Integer numberValue = 1;
    }

    public Boolean skipOfBefore() {
        return this.delete.getBeforeSkip();
    }

    public Boolean skipOfAfter() {
        return this.delete.getAfterSkip();
    }

    public Boolean judgeOfAccurate() {
        return this.delete.getBooleanSign();
    }

    public Boolean signOfBoolean() {
        return this.delete.getBooleanSign();
    }

    public Integer signOfNumber() {
        return this.delete.getNumberSign();
    }

    public Boolean valueOfBoolean() {
        return this.delete.getBooleanValue();
    }

    public Integer valueOfNumber() {
        return this.delete.getNumberValue();
    }

}
