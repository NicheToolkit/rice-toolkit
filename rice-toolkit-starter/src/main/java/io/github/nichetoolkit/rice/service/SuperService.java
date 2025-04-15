package io.github.nichetoolkit.rice.service;

import com.github.pagehelper.Page;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;
import io.github.nichetoolkit.rest.helper.PartitionHelper;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.*;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.error.service.ServiceUnknownException;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.filter.StatusFilter;
import io.github.nichetoolkit.rice.mapper.*;
import io.github.nichetoolkit.rice.mapper.natives.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@SuppressWarnings("RedundantThrows")
public abstract class SuperService<M extends RestId<I>, E extends RestId<I>, F extends IdFilter<I, K>, I, K>
        extends SuperAdvice<M, E, F, I, K> implements InitializingBean {

    private String simpleName;

    @Override
    public void afterPropertiesSet() throws Exception {
        ServiceHolder.initOfService();
        ServiceHolder.initOfServiceIntend();
        this.simpleName = this.getClass().getSimpleName();
        this.superMapper = ServiceHolder.findSuperMapper(this.getClass());
        String superMessage = "The service and mapper name must be like 'xxxService'/'xxxServiceImpl' and 'xxxMapper'.";
        OptionalUtils.ofNullException(this.superMapper, superMessage, this.simpleName, log, ServiceUnknownException::new);
        this.tableMapper = BeanUtils.beanOfType(TableMapper.class);
        if (isFickleField()) {
            String tableMessage = "The bean of table mapper is no found, it's possible that you don't need auto fickle.";
            OptionalUtils.ofNullException(this.tableMapper, tableMessage, this.simpleName, log, ServiceUnknownException::new);
        }
        this.afterSuperHandle();
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M create(M model, Object... idArray) throws RestException {
        return create(null, model, idArray);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M create(K tablekey, M model, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalCreate(tablekey, model);
        this.beforeCreate(model);
        Integer result = single(tablekey, model, idArray);
        if (useSaveResult()) {
            String message = "The creating method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
            OptionalUtils.ofCreate(result, message, simpleName, log);
        }
        this.afterCreate(model);
        this.refresh();
        return model;
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M update(M model, Object... idArray) throws RestException {
        return update(null, model, idArray);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M update(K tablekey, M model, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalUpdate(tablekey, model);
        this.beforeUpdate(model);
        Integer result = single(tablekey, model, idArray);
        if (useSaveResult()) {
            String message = "The updating method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
            OptionalUtils.ofUpdate(result, message, simpleName, log);
        }
        this.afterUpdate(model);
        this.refresh();
        return model;
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M save(M model, Object... idArray) throws RestException {
        return save(null, model, idArray);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public M save(K tablekey, M model, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(model)) {
            return null;
        }
        optionalSave(tablekey, model);
        this.beforeSave(model);
        Integer result = single(tablekey, model, idArray);
        if (useSaveResult()) {
            String message = "The saving method has error with " + simpleName + ": " + JsonUtils.parseJson(model);
            OptionalUtils.ofSave(result, message, simpleName, log);
        }
        this.afterSave(model);
        this.refresh();
        return model;
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public List<M> saveAll(Collection<M> modelList) throws RestException {
        return saveAll(modelList, (Object[]) null);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public List<M> saveAll(Collection<M> modelList, Object... idArray) throws RestException {
        return saveAll(null, modelList, idArray);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public List<M> saveAll(K tablekey, Collection<M> modelList, Object... idArray) throws RestException {
        if (GeneralUtils.isEmpty(modelList)) {
            return Collections.emptyList();
        }
        Method beforeSaveAllMethod = null;
        try {
            beforeSaveAllMethod = this.getClass().getMethod("beforeSaveAll", Collection.class);
        } catch (NoSuchMethodException ignored) {
        }
        List<E> entityList;
        if (beforeSaveAllMethod != null && !beforeSaveAllMethod.isDefault()) {
            for (M model : modelList) {
                optionalSave(tablekey, model);
            }
            this.beforeSaveAll(modelList);
            entityList = entityActuator(modelList, model -> {
            }, idArray);
        } else {
            entityList = entityActuator(modelList, model -> {
                this.optionalSave(tablekey, model);
                this.beforeSave(model);
            }, idArray);
        }
        String tablename = resolveTablename(tablekey, modelList);
        Integer result;
        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
            result = PartitionHelper.save(entityList, this.partitionOfSave(), entities -> superMapper.saveDynamicAll(tablename, entities));
        } else {
            result = PartitionHelper.save(entityList, this.partitionOfSave(), superMapper::saveAll);
        }
        if (useSaveResult()) {
            Boolean present = modelList.size() == result;
            String message = "The saveAll method has error with " + simpleName + ": " + JsonUtils.parseJson(modelList);
            OptionalUtils.ofSaveAll(present, message, simpleName, log);
        }
        this.afterSaveAll(modelList);
        this.refresh();
        return new ArrayList<>(modelList);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateById(I id, OperateType operate) throws RestException {
        operateById(null, id, operate);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateById(K tablekey, I id, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(operate)) {
            return;
        }
        if (superMapper instanceof OperateMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                operateId(tablename, id, operate);
            } else {
                E entity = findById(id, tablename);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (!isBeforeSkip()) {
                        this.beforeOperate(entity);
                    }
                    operateId(tablename, id, operate);
                    if (!isAfterSkip()) {
                        this.afterOperate(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAll(Collection<I> idList, OperateType operate) throws RestException {
        operateAll(null, idList, operate);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAll(K tablekey, Collection<I> idList, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        if (superMapper instanceof OperateMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                operatePartition(tablename, idList, operate);
            } else {
                List<E> entityList = findAll(idList, tablename);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    operateAdvice(entityList, operate, type -> operatePartition(tablename, idList, type));
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateByLinkId(L linkId, OperateType operate) throws RestException {
        operateByLinkId(null, linkId, operate);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateByLinkId(K tablekey, L linkId, OperateType operate) throws RestException {
        operateByLinkId(null, linkId, null, operate);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateByLinkId(L linkId, RestKey<String> linkName, OperateType operate) throws RestException {
        operateByLinkId(null, linkId, linkName, operate);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateByLinkId(K tablekey, L linkId, RestKey<String> linkName, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        if (superMapper instanceof OperateLinkMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                operateLinkId(tablename, linkId, linkName, operate);
            } else {
                List<E> entityList = findByLinkId(tablename, linkId, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    if (!isBeforeSkip()) {
                        this.beforeOperateAll(entityList);
                    }
                    operateLinkId(tablename, linkId, linkName, operate);
                    if (!isAfterSkip()) {
                        this.afterOperateAll(entityList);
                    }
                    this.refresh();
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateAllByLinkIds(Collection<L> linkIdList, OperateType operate) throws RestException {
        operateAllByLinkIds(null, linkIdList, operate);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateAllByLinkIds(K tablekey, Collection<L> linkIdList, OperateType operate) throws RestException {
        operateAllByLinkIds(null, linkIdList, null, operate);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName, OperateType operate) throws RestException {
        operateAllByLinkIds(null, linkIdList, linkName, operate);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void operateAllByLinkIds(K tablekey, Collection<L> linkIdList, RestKey<String> linkName, OperateType operate) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        if (superMapper instanceof OperateLinkMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                operateLinkIdPartition(tablename, linkIdList, linkName, operate);
            } else {
                List<E> entityList = findAllByLinkIds(tablename, linkIdList, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    operateAdvice(entityList, operate, type -> operateLinkIdPartition(tablename, linkIdList, linkName, type));
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertById(I id, S status) throws RestException {
        alertById(null, id, status);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertById(K tablekey, I id, S status) throws RestException {
        if (GeneralUtils.isEmpty(id) || GeneralUtils.isEmpty(status)) {
            return;
        }
        if (superMapper instanceof AlertMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                alertId(tablename, id, status);
            } else {
                E entity = findById(id, tablename);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (!isBeforeSkip()) {
                        this.beforeAlert(entity);
                    }
                    alertId(tablename, id, status);
                    if (!isAfterSkip()) {
                        this.afterAlert(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertAll(Collection<I> idList, S status) throws RestException {
        alertAll(null, idList, status);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertAll(K tablekey, Collection<I> idList, S status) throws RestException {
        if (GeneralUtils.isEmpty(idList) || GeneralUtils.isEmpty(status)) {
            return;
        }
        if (superMapper instanceof AlertMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                alertPartition(tablename, idList, status);
            } else {
                List<E> entityList = findAll(idList, tablename);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    alertAdvice(entityList, status, alertStatus -> alertPartition(tablename, idList, alertStatus));
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertByLinkId(L linkId, S status) throws RestException {
        alertByLinkId(null, linkId, status);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertByLinkId(K tablekey, L linkId, S status) throws RestException {
        alertByLinkId(null, linkId, null, status);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertByLinkId(L linkId, RestKey<String> linkName, S status) throws RestException {
        alertByLinkId(null, linkId, linkName, status);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertByLinkId(K tablekey, L linkId, RestKey<String> linkName, S status) throws RestException {
        if (GeneralUtils.isEmpty(linkId) || GeneralUtils.isEmpty(status)) {
            return;
        }
        if (superMapper instanceof AlertLinkMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                alertLinkId(tablename, linkId, linkName, status);
            } else {
                List<E> entityList = findByLinkId(tablename, linkId, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    if (!isBeforeSkip()) {
                        this.beforeAlertAll(entityList);
                    }
                    alertLinkId(tablename, linkId, linkName, status);
                    if (!isAfterSkip()) {
                        this.afterAlertAll(entityList);
                    }
                    this.refresh();
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertAllByLinkIds(Collection<L> linkIdList, S status) throws RestException {
        alertAllByLinkIds(null, linkIdList, status);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertAllByLinkIds(K tablekey, Collection<L> linkIdList, S status) throws RestException {
        alertAllByLinkIds(null, linkIdList, null, status);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName, S status) throws RestException {
        alertAllByLinkIds(null, linkIdList, linkName, status);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L, S> void alertAllByLinkIds(K tablekey, Collection<L> linkIdList, RestKey<String> linkName, S status) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList) || GeneralUtils.isEmpty(status)) {
            return;
        }
        if (superMapper instanceof AlertLinkMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                alertLinkIdPartition(tablename, linkIdList, linkName, status);
            } else {
                List<E> entityList = findAllByLinkIds(tablename, linkIdList, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    alertAdvice(entityList, status, type -> alertLinkIdPartition(tablename, linkIdList, linkName, type));
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeById(I id) throws RestException {
        removeById(null, id);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeById(K tablekey, I id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return;
        }
        Object logic = markOfLogic();
        if (superMapper instanceof RemoveMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                removeId(tablename, id, logic);
            } else {
                E entity = findById(id, tablename);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (!isBeforeSkip()) {
                        this.beforeRemove(entity);
                    }
                    removeId(tablename, id, logic);
                    if (!isAfterSkip()) {
                        this.afterRemove(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAll(Collection<I> idList) throws RestException {
        removeAll(null, idList);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAll(K tablekey, Collection<I> idList) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        Object logic = markOfLogic();
        if (superMapper instanceof RemoveMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                removePartition(tablename, idList, logic);
            } else {
                List<E> entityList = findAll(idList, tablename);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    removeAdvice(entityList, logic, sign -> removePartition(tablename, idList, sign));
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeByLinkId(L linkId) throws RestException {
        removeByLinkId(null, linkId);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeByLinkId(K tablekey, L linkId) throws RestException {
        removeByLinkId(null, linkId, null);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeByLinkId(L linkId, RestKey<String> linkName) throws RestException {
        removeByLinkId(null, linkId, linkName);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeByLinkId(K tablekey, L linkId, RestKey<String> linkName) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        Object logic = markOfLogic();
        if (superMapper instanceof RemoveLinkMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                removeLinkId(tablename, linkId, linkName, logic);
            } else {
                List<E> entityList = findByLinkId(tablename, linkId, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    if (!isBeforeSkip()) {
                        this.beforeRemoveAll(entityList);
                    }
                    removeLinkId(tablename, linkId, linkName, logic);
                    if (!isAfterSkip()) {
                        this.afterRemoveAll(entityList);
                    }
                    this.refresh();
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeAllByLinkIds(Collection<L> linkIdList) throws RestException {
        removeAllByLinkIds(null, linkIdList);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeAllByLinkIds(K tablekey, Collection<L> linkIdList) throws RestException {
        removeAllByLinkIds(null, linkIdList, null);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName) throws RestException {
        removeAllByLinkIds(null, linkIdList, linkName);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void removeAllByLinkIds(K tablekey, Collection<L> linkIdList, RestKey<String> linkName) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        Object logic = markOfLogic();
        if (superMapper instanceof RemoveLinkMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                removeLinkIdPartition(tablename, linkIdList, linkName, logic);
            } else {
                List<E> entityList = findAllByLinkIds(tablename, linkIdList, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    removeAdvice(entityList, logic, sign -> removeLinkIdPartition(tablename, linkIdList, linkName, sign));
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteById(I id) throws RestException {
        deleteById(null, id);
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteById(K tablekey, I id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return;
        }
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeById(tablekey, id);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateById(tablekey, id, OperateType.REMOVE);
        } else {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                deleteId(tablename, id);
            } else {
                E entity = findById(id, tablename);
                if (GeneralUtils.isNotEmpty(entity)) {
                    if (!isBeforeSkip()) {
                        this.beforeDelete(entity);
                    }
                    deleteId(tablename, id);
                    if (!isAfterSkip()) {
                        this.afterDelete(entity);
                    }
                    this.refresh();
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAll(Collection<I> idList) throws RestException {
        deleteAll(null, idList);
    }

    @Override
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAll(K tablekey, Collection<I> idList) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeAll(tablekey, idList);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateAll(tablekey, idList, OperateType.REMOVE);
        } else {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                deletePartition(tablename, idList);
            } else {
                List<E> entityList = superMapper.findAll(idList);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    deleteAdvice(entityList, () -> deletePartition(tablename, idList));
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteByLinkId(L linkId) throws RestException {
        deleteByLinkId(null, linkId);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteByLinkId(K tablekey, L linkId) throws RestException {
        deleteByLinkId(null, linkId, null);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteByLinkId(L linkId, RestKey<String> linkName) throws RestException {
        deleteByLinkId(null, linkId, linkName);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteByLinkId(K tablekey, L linkId, RestKey<String> linkName) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return;
        }
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeByLinkId(tablekey, linkId, linkName);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateByLinkId(tablekey, linkId, linkName, OperateType.REMOVE);
        } else if (superMapper instanceof DeleteLinkMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                deleteLinkId(tablename, linkId, linkName);
            } else {
                List<E> entityList = findByLinkId(tablename, linkId, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    if (!isBeforeSkip()) {
                        this.beforeDeleteAll(entityList);
                    }
                    deleteLinkId(tablename, linkId, linkName);
                    if (!isAfterSkip()) {
                        this.afterDeleteAll(entityList);
                    }
                    this.refresh();
                }
            }
        }
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteAllByLinkIds(Collection<L> linkIdList) throws RestException {
        deleteAllByLinkIds(null, linkIdList);
    }

    @SuppressWarnings("Duplicates")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteAllByLinkIds(K tablekey, Collection<L> linkIdList) throws RestException {
        deleteAllByLinkIds(null, linkIdList, null);
    }

    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName) throws RestException {
        deleteAllByLinkIds(null, linkIdList, linkName);
    }

    @SuppressWarnings("Duplicates")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <L> void deleteAllByLinkIds(K tablekey, Collection<L> linkIdList, RestKey<String> linkName) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return;
        }
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeAllByLinkIds(tablekey, linkIdList, linkName);
        } else if (deleteModel == DeleteMode.OPERATE) {
            operateAllByLinkIds(tablekey, linkIdList, linkName, OperateType.REMOVE);
        } else if (superMapper instanceof DeleteLinkMapper) {
            String tablename = resolveTablename(tablekey);
            if (isBeforeSkip() && isAfterSkip()) {
                deleteLinkIdPartition(tablename, linkIdList, linkName);
            } else {
                List<E> entityList = findAllByLinkIds(tablename, linkIdList, linkName);
                if (GeneralUtils.isNotEmpty(entityList)) {
                    deleteAdvice(entityList, () -> deleteLinkIdPartition(tablename, linkIdList, linkName));
                }
            }
        }
    }

    @Override
    public M queryById(I id, Boolean... isLoadArray) throws RestException {
        return queryById(null, id, isLoadArray);
    }

    @Override
    public M queryById(I id, Collection<RestKey<String>> fickleList, Boolean... isLoadArray) throws RestException {
        return queryById(null, id, fickleList, isLoadArray);
    }

    @Override
    public M queryById(K tablekey, I id, Boolean... isLoadArray) throws RestException {
        return queryById(tablekey, id, null, isLoadArray);
    }

    @Override
    public M queryById(K tablekey, I id, Collection<RestKey<String>> fickleList, Boolean... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            return null;
        }
        E entity;
        String tablename = resolveTablename(tablekey);
        String[] tableFickle = resolveTableFickle(tablename, fickleList);
        if (GeneralUtils.isNotEmpty(isLoadArray) && GeneralUtils.isNotEmpty(tableFickle)
                && isFickleField() && FickleLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            entity = findByIdFickleLoad(id, tablename, tableFickle, isLoadArray);
        } else if (GeneralUtils.isNotEmpty(tableFickle) && isFickleField() && FindFickleMapper.class.isAssignableFrom(superMapper.getClass())) {
            entity = findByIdFickle(id, tablename, tableFickle);
        } else if (GeneralUtils.isNotEmpty(isLoadArray) && FindLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            entity = findByIdLoad(id, tablename, isLoadArray);
        } else {
            entity = findById(id, tablename);
        }
        if (GeneralUtils.isEmpty(entity)) {
            return null;
        }
        return modelActuator(entity, isLoadArray);
    }

    @Override
    public List<M> queryAll(Collection<I> idList, Boolean... isLoadArray) throws RestException {
        return queryAll(null, idList, isLoadArray);
    }

    @Override
    public List<M> queryAll(Collection<I> idList, Collection<RestKey<String>> fickleList, Boolean... isLoadArray) throws RestException {
        return queryAll(null, idList, fickleList, isLoadArray);
    }

    public List<M> queryAll(K tablekey, Collection<I> idList, Boolean... isLoadArray) throws RestException {
        return queryAll(tablekey, idList, null, isLoadArray);
    }

    @Override
    public List<M> queryAll(K tablekey, Collection<I> idList, Collection<RestKey<String>> fickleList, Boolean... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return Collections.emptyList();
        }
        List<E> entityList;
        String tablename = resolveTablename(tablekey);
        String[] tableFickle = resolveTableFickle(tablename, fickleList);
        if (GeneralUtils.isNotEmpty(isLoadArray) && GeneralUtils.isNotEmpty(tableFickle)
                && isFickleField() && FickleLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findAllFickleLoad(idList, tablename, tableFickle, isLoadArray);
        } else if (GeneralUtils.isNotEmpty(tableFickle) && isFickleField() && FindFickleMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findAllFickle(idList, tablename, tableFickle);
        } else if (GeneralUtils.isNotEmpty(isLoadArray) && FindLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findAllLoad(idList, tablename, isLoadArray);
        } else {
            entityList = findAll(idList, tablename);
        }
        return modelActuator(entityList, null, isLoadArray);
    }

    public RestPage<M> queryAllWithFilter(F filter) throws RestException {
        optionalQueryFilter(filter);
        queryFilterCache.set(filter);
        String whereSql = queryWhereSql(filter);
        Boolean[] loadArray = findLoadArray(filter);
        Boolean[] isLoadArray = queryLoadArray(filter);
        String[] fieldArray = fieldArray(filter);
        String[] fickleArray = fickleArray(filter);
        K tablekey = tablekey(filter);
        String tablename = resolveTablename(tablekey);
        String[] tableFickle = resolveTableFickle(tablename, fickleArray);
        PageResult<E, I> pageResult;
        if (GeneralUtils.isNotEmpty(loadArray) && GeneralUtils.isNotEmpty(tableFickle)
                && isFickleField() && FickleFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
            pageResult = findAllByFickleLoadWhere(whereSql, tablename, filter, tableFickle, loadArray);
        } else if (GeneralUtils.isNotEmpty(tableFickle) && isFickleField()
                && FilterFickleMapper.class.isAssignableFrom(superMapper.getClass())) {
            pageResult = findAllByFickleWhere(whereSql, tablename, filter, tableFickle);
        } else if (GeneralUtils.isNotEmpty(loadArray) && FilterLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            pageResult = findAllByLoadWhere(whereSql, tablename, filter, loadArray);
        } else if (FindFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
            pageResult = findAllByFilterWhere(whereSql, tablename, filter);
        } else {
            Page<E> page = filter.toPage();
            List<E> entityList = findAllByWhere(whereSql, tablename);
            pageResult = PageResult.builder(page, entityList);
        }
        List<M> modelList = modelActuator(pageResult.getEntities(), null, isLoadArray);
        queryFilterCache.remove();
        return RestPage.result(modelList, pageResult.getPage());
    }

    public <L> List<M> queryByLinkId(L linkId, Boolean... isLoadArray) throws RestException {
        return queryByLinkId(null, linkId, isLoadArray);
    }

    public <L> List<M> queryByLinkId(L linkId, String[] fickleArray, Boolean... isLoadArray) throws RestException {
        return queryByLinkId(null, linkId, null, fickleArray, isLoadArray);
    }

    public <L> List<M> queryByLinkId(K tablekey, L linkId, Boolean... isLoadArray) throws RestException {
        return queryByLinkId(tablekey, linkId, null, null, isLoadArray);
    }

    public <L> List<M> queryByLinkId(K tablekey, L linkId, String[] fickleArray, Boolean... isLoadArray) throws RestException {
        return queryByLinkId(tablekey, linkId, null, null, isLoadArray);
    }

    public <L> List<M> queryByLinkId(L linkId, RestKey<String> linkName, Boolean... isLoadArray) throws RestException {
        return queryByLinkId(null, linkId, linkName, isLoadArray);
    }

    public <L> List<M> queryByLinkId(L linkId, RestKey<String> linkName, String[] fickleArray, Boolean... isLoadArray) throws RestException {
        return queryByLinkId(null, linkId, linkName, fickleArray, isLoadArray);
    }

    public <L> List<M> queryByLinkId(K tablekey, L linkId, RestKey<String> linkName, Boolean... isLoadArray) throws RestException {
        return queryByLinkId(tablekey, linkId, linkName, null, isLoadArray);
    }

    public <L> List<M> queryByLinkId(K tablekey, L linkId, RestKey<String> linkName, String[] fickleArray, Boolean... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(linkId)) {
            return Collections.emptyList();
        }
        List<E> entityList;
        String tablename = resolveTablename(tablekey);
        String[] tableFickle = resolveTableFickle(tablename, fickleArray);
        if (GeneralUtils.isNotEmpty(isLoadArray) && GeneralUtils.isNotEmpty(tableFickle)
                && isFickleField() && FickleLinkMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findByLinkIdFickleLoad(tablename, linkId, linkName, tableFickle, isLoadArray);
        } else if (GeneralUtils.isNotEmpty(tableFickle) && isFickleField() && LinkFickleMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findByLinkIdFickle(tablename, linkId, linkName, tableFickle);
        } else if (GeneralUtils.isNotEmpty(isLoadArray) && LinkLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findByLinkIdLoad(tablename, linkId, linkName, isLoadArray);
        } else {
            entityList = findByLinkId(tablename, linkId, linkName);
        }
        if (GeneralUtils.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        return modelActuator(entityList, null, isLoadArray);
    }

    public <L> List<M> queryAllByLinkIds(Collection<L> linkIdList, Boolean... isLoadArray) throws RestException {
        return queryAllByLinkIds(null, linkIdList, isLoadArray);
    }

    public <L> List<M> queryAllByLinkIds(Collection<L> linkIdList, String[] fickleArray, Boolean... isLoadArray) throws RestException {
        return queryAllByLinkIds(null, linkIdList, fickleArray, isLoadArray);
    }

    public <L> List<M> queryAllByLinkIds(K tablekey, Collection<L> linkIdList, Boolean... isLoadArray) throws RestException {
        return queryAllByLinkIds(tablekey, linkIdList, null, null, isLoadArray);
    }

    public <L> List<M> queryAllByLinkIds(K tablekey, Collection<L> linkIdList, String[] fickleArray, Boolean... isLoadArray) throws RestException {
        return queryAllByLinkIds(tablekey, linkIdList, null, null, isLoadArray);
    }

    public <L> List<M> queryAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName, Boolean... isLoadArray) throws RestException {
        return queryAllByLinkIds(null, linkIdList, linkName, isLoadArray);
    }

    public <L> List<M> queryAllByLinkIds(Collection<L> linkIdList, RestKey<String> linkName, String[] fickleArray, Boolean... isLoadArray) throws RestException {
        return queryAllByLinkIds(null, linkIdList, linkName, fickleArray, isLoadArray);
    }

    public <L> List<M> queryAllByLinkIds(K tablekey, Collection<L> linkIdList, RestKey<String> linkName, Boolean... isLoadArray) throws RestException {
        return queryAllByLinkIds(tablekey, linkIdList, linkName, null, isLoadArray);
    }

    public <L> List<M> queryAllByLinkIds(K tablekey, Collection<L> linkIdList, RestKey<String> linkName, String[] fickleArray, Boolean... isLoadArray) throws RestException {
        if (GeneralUtils.isEmpty(linkIdList)) {
            return Collections.emptyList();
        }
        List<E> entityList;
        String tablename = resolveTablename(tablekey);
        String[] tableFickle = resolveTableFickle(tablename, fickleArray);
        if (GeneralUtils.isNotEmpty(isLoadArray) && GeneralUtils.isNotEmpty(tableFickle)
                && isFickleField() && FickleLinkMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findAllByLinkIdsFickleLoad(tablename, linkIdList, linkName, tableFickle, isLoadArray);
        } else if (GeneralUtils.isNotEmpty(tableFickle) && isFickleField() && LinkFickleMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findAllByLinkIdsFickle(tablename, linkIdList, linkName, tableFickle);
        } else if (GeneralUtils.isNotEmpty(isLoadArray) && LinkLoadMapper.class.isAssignableFrom(superMapper.getClass())) {
            entityList = findAllByLinkIdsLoad(tablename, linkIdList, linkName, isLoadArray);
        } else {
            entityList = findAllByLinkIds(tablename, linkIdList, linkName);
        }
        return modelActuator(entityList, null, isLoadArray);
    }


    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void deleteAllWithFilter(F filter) throws RestException {
        DeleteMode deleteModel = deleteMode();
        if (deleteModel == DeleteMode.REMOVE) {
            removeAllWithFilter(filter);
        } else if (deleteModel == DeleteMode.OPERATE) {
            filter.setOperate(OperateType.DELETE);
            operateAllWithFilter(filter);
        } else {
            optionalDeleteFilter(filter);
            String whereSql = deleteWhereSql(filter);
            K tablekey = tablekey(filter);
            String tablename = resolveTablename(tablekey);
            if (GeneralUtils.isNotEmpty(whereSql)) {
                if (DeleteFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
                    DeleteFilterMapper<E, F, I, K> filterMapper = (DeleteFilterMapper<E, F, I, K>) superMapper;
                    Method findMethod = null;
                    Method deleteMethod = null;
                    try {
                        findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, IdFilter.class);
                        deleteMethod = filterMapper.getClass().getMethod("deleteAllByFilterWhere", String.class, IdFilter.class);
                    } catch (NoSuchMethodException ignored) {
                    }
                    Method findAllByWhereMethod = findMethod;
                    Method deleteAllByWhereMethod = deleteMethod;
                    if (deleteAllByWhereMethod != null && !deleteAllByWhereMethod.isDefault()) {
                        if (isBeforeSkip() && isAfterSkip()) {
                            if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                                filterMapper.deleteDynamicAllByFilterWhere(tablename, whereSql, filter);
                            } else {
                                filterMapper.deleteAllByFilterWhere(whereSql, filter);
                            }
                        } else {
                            String queryWhereSql = queryWhereSql(filter);
                            List<E> entityList;
                            if (!findAllByWhereMethod.isDefault()) {
                                entityList = filterMapper.findAllByFilterWhere(queryWhereSql, filter);
                            } else {
                                entityList = superMapper.findAllByWhere(queryWhereSql);
                            }
                            if (GeneralUtils.isNotEmpty(entityList)) {
                                deleteAdvice(entityList, () -> {
                                    if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                                        filterMapper.deleteDynamicAllByFilterWhere(tablename, whereSql, filter);
                                    } else {
                                        filterMapper.deleteAllByFilterWhere(whereSql, filter);
                                    }
                                });
                            }
                        }
                    } else {
                        deleteAllByWhere(whereSql, tablename, filter);
                    }
                } else {
                    deleteAllByWhere(whereSql, tablename, filter);
                }
            }
        }
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void removeAllWithFilter(F filter) throws RestException {
        optionalRemoveFilter(filter);
        String removeWhereSql = removeWhereSql(filter);
        K tablekey = tablekey(filter);
        String tablename = resolveTablename(tablekey);
        if (GeneralUtils.isNotEmpty(removeWhereSql)) {
            Object logic = markOfLogic();
            if (RemoveFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
                RemoveFilterMapper<E, F, I, K> filterMapper = (RemoveFilterMapper<E, F, I, K>) superMapper;
                Method findMethod = null;
                Method removeMethod = null;
                try {
                    findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, IdFilter.class);
                    removeMethod = filterMapper.getClass().getMethod("removeAllByFilterWhere", String.class, IdFilter.class, String.class);
                } catch (NoSuchMethodException ignored) {
                }
                Method findAllByWhereMethod = findMethod;
                Method removeAllByWhereMethod = removeMethod;
                if (removeAllByWhereMethod != null && !removeAllByWhereMethod.isDefault()) {
                    if (isBeforeSkip() && isAfterSkip()) {
                        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                            filterMapper.removeDynamicAllByFilterWhere(tablename, removeWhereSql, filter, logic);
                        } else {
                            filterMapper.removeAllByFilterWhere(removeWhereSql, filter, logic);
                        }
                    } else {
                        String queryWhereSql = queryWhereSql(filter);
                        List<E> entityList;
                        if (!findAllByWhereMethod.isDefault()) {
                            entityList = filterMapper.findAllByFilterWhere(queryWhereSql, filter);
                        } else {
                            entityList = superMapper.findAllByWhere(queryWhereSql);
                        }
                        if (GeneralUtils.isNotEmpty(entityList)) {
                            removeAdvice(entityList, logic, sign -> {
                                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                                    filterMapper.removeDynamicAllByFilterWhere(tablename, removeWhereSql, filter, sign);
                                } else {
                                    filterMapper.removeAllByFilterWhere(removeWhereSql, filter, sign);
                                }
                            });
                        }
                    }
                } else {
                    removeAllByWhere(removeWhereSql, tablename, filter);
                }
            } else {
                removeAllByWhere(removeWhereSql, tablename, filter);
            }

        }
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public void operateAllWithFilter(F filter) throws RestException {
        optionalOperateFilter(filter);
        String operateWhereSql = operateWhereSql(filter);
        K tablekey = tablekey(filter);
        String tablename = resolveTablename(tablekey);
        if (GeneralUtils.isNotEmpty(operateWhereSql)) {
            if (OperateFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
                OperateFilterMapper<E, F, I, K> filterMapper = (OperateFilterMapper<E, F, I, K>) superMapper;
                Method findMethod = null;
                Method operateMethod = null;
                try {
                    findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, IdFilter.class);
                    operateMethod = filterMapper.getClass().getMethod("operateAllByFilterWhere", String.class, IdFilter.class, Integer.class);
                } catch (NoSuchMethodException ignored) {
                }
                Method findAllByWhereMethod = findMethod;
                Method operateAllByWhereMethod = operateMethod;
                if (operateAllByWhereMethod != null && !operateAllByWhereMethod.isDefault()) {
                    OperateType operateType = GeneralUtils.isNotEmpty(filter.getOperate()) ? filter.getOperate() : OperateType.REMOVE;
                    if (isBeforeSkip() && isAfterSkip()) {
                        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                            filterMapper.operateDynamicAllByFilterWhere(tablename, operateWhereSql, filter, operateType.getKey());
                        } else {
                            filterMapper.operateAllByFilterWhere(operateWhereSql, filter, operateType.getKey());
                        }
                    } else {
                        String queryWhereSql = queryWhereSql(filter);
                        List<E> entityList;
                        if (!findAllByWhereMethod.isDefault()) {
                            entityList = filterMapper.findAllByFilterWhere(queryWhereSql, filter);
                        } else {
                            entityList = superMapper.findAllByWhere(queryWhereSql);
                        }
                        if (GeneralUtils.isNotEmpty(entityList)) {
                            operateAdvice(entityList, operateType, operate -> {
                                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                                    filterMapper.operateDynamicAllByFilterWhere(tablename, operateWhereSql, filter, operate.getKey());
                                } else {
                                    filterMapper.operateAllByFilterWhere(operateWhereSql, filter, operate.getKey());
                                }
                            });
                        }
                    }
                } else {
                    operateAllByWhere(operateWhereSql, tablename, filter);
                }
            } else {
                operateAllByWhere(operateWhereSql, tablename, filter);
            }
        }
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    public <S> void alertAllWithFilter(F filter) throws RestException {
        optionalAlertFilter(filter);
        boolean isPresentFilter = StatusFilter.class.isAssignableFrom(filter.getClass());
        String messageOfFilter = "The 'alertAllWithFilter' method is invoked error, the filter must extends 'StatusFilter'.";
        OptionalUtils.ofFalse(isPresentFilter, messageOfFilter, "alertAllWithFilter", log, UnsupportedErrorException::new);
        assert filter instanceof StatusFilter;
        StatusFilter<S> statusFilter = (StatusFilter<S>) filter;
        String messageOfStatus = "The 'alertAllWithFilter' method is invoked error, the value of 'getStatus' method must not null.";
        OptionalUtils.ofEmpty(statusFilter.getStatus(), messageOfStatus, "alertAllWithFilter", log, UnsupportedErrorException::new);
        String alertWhereSql = alertWhereSql(filter);
        K tablekey = tablekey(filter);
        String tablename = resolveTablename(tablekey);
        if (GeneralUtils.isNotEmpty(alertWhereSql)) {
            if (AlertFilterMapper.class.isAssignableFrom(superMapper.getClass())) {
                AlertFilterMapper<E, F, S, I, K> filterMapper = (AlertFilterMapper<E, F, S, I, K>) superMapper;
                Method findMethod = null;
                Method alertMethod = null;
                try {
                    findMethod = filterMapper.getClass().getMethod("findAllByFilterWhere", String.class, IdFilter.class);
                    alertMethod = filterMapper.getClass().getMethod("alertAllByFilterWhere", String.class, IdFilter.class, Integer.class);
                } catch (NoSuchMethodException ignored) {
                }
                Method findAllByWhereMethod = findMethod;
                Method alertAllByWhereMethod = alertMethod;
                if (alertAllByWhereMethod != null && !alertAllByWhereMethod.isDefault()) {
                    S status = statusFilter.getStatus();
                    if (isBeforeSkip() && isAfterSkip()) {
                        if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                            filterMapper.alertDynamicAllByFilterWhere(tablename, alertWhereSql, filter, status);
                        } else {
                            filterMapper.alertAllByFilterWhere(alertWhereSql, filter, status);
                        }
                    } else {
                        String queryWhereSql = queryWhereSql(filter);
                        List<E> entityList;
                        if (!findAllByWhereMethod.isDefault()) {
                            entityList = filterMapper.findAllByFilterWhere(queryWhereSql, filter);
                        } else {
                            entityList = superMapper.findAllByWhere(queryWhereSql);
                        }
                        if (GeneralUtils.isNotEmpty(entityList)) {
                            alertAdvice(entityList, status, alertStatus -> {
                                if (isDynamicOfTable() && GeneralUtils.isNotEmpty(tablename)) {
                                    filterMapper.alertDynamicAllByFilterWhere(tablename, alertWhereSql, filter, alertStatus);
                                } else {
                                    filterMapper.alertAllByFilterWhere(alertWhereSql, filter, alertStatus);
                                }
                            });
                        }
                    }
                } else {
                    alertAllByWhere(alertWhereSql, tablename, filter);
                }
            } else {
                alertAllByWhere(alertWhereSql, tablename, filter);
            }
        }
    }

    @Override
    public M mutateEntity(E model, Boolean... isLoadArray) throws RestException {
        return modelActuator(model, isLoadArray);
    }

    @Override
    public List<M> mutateEntityList(List<E> entityList, ConsumerActuator<E> actuator, Boolean... isLoadArray) throws RestException {
        return modelActuator(entityList, actuator, isLoadArray);
    }

    @Override
    public E mutateModel(M model, Object... idArray) throws RestException {
        return entityActuator(model, idArray);
    }

    @Override
    public List<E> mutateModelList(List<M> modelList, ConsumerActuator<M> actuator, Object... idArray) throws RestException {
        return entityActuator(modelList, actuator, idArray);
    }

}
