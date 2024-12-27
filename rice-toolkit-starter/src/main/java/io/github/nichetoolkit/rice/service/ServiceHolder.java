package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import io.github.nichetoolkit.rest.holder.BeanDefinitionRegistryHolder;
import io.github.nichetoolkit.rest.holder.ListableBeanFactoryHolder;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestLogicMark;
import io.github.nichetoolkit.rice.RestServiceFitter;
import io.github.nichetoolkit.rice.configure.RiceServiceProperties;
import io.github.nichetoolkit.rice.enums.AutoMark;
import io.github.nichetoolkit.rice.enums.ConfigMark;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.LogicMode;
import io.github.nichetoolkit.rice.mapper.SuperMapper;
import io.github.nichetoolkit.rice.stereotype.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <code>ServiceHolder</code>
 * <p>The service holder class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
public class ServiceHolder {

    /**
     * <code>SERVICE_IMPL_SUFFIX</code>
     * {@link java.lang.String} <p>The constant <code>SERVICE_IMPL_SUFFIX</code> field.</p>
     * @see  java.lang.String
     */
    public static String SERVICE_IMPL_SUFFIX = "ServiceImpl";
    /**
     * <code>SERVICE_SUFFIX</code>
     * {@link java.lang.String} <p>The constant <code>SERVICE_SUFFIX</code> field.</p>
     * @see  java.lang.String
     */
    public static String SERVICE_SUFFIX = "Service";
    /**
     * <code>MAPPER_SUFFIX</code>
     * {@link java.lang.String} <p>The constant <code>MAPPER_SUFFIX</code> field.</p>
     * @see  java.lang.String
     */
    public static String MAPPER_SUFFIX = "Mapper";
    /**
     * <code>IS_HAS_INIT_OF_SERVICE_INTEND</code>
     * <p>The <code>IS_HAS_INIT_OF_SERVICE_INTEND</code> field.</p>
     */
    static boolean IS_HAS_INIT_OF_SERVICE_INTEND = false;

    /**
     * <code>serviceProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties} <p>The constant <code>serviceProperties</code> field.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceServiceProperties
     */
    private static RiceServiceProperties serviceProperties;

    /**
     * <code>logicMark</code>
     * {@link io.github.nichetoolkit.rice.RestLogicMark} <p>The constant <code>logicMark</code> field.</p>
     * @see  io.github.nichetoolkit.rice.RestLogicMark
     */
    private static RestLogicMark logicMark;

    /**
     * <code>initOfService</code>
     * <p>The init of service method.</p>
     */
    static void initOfService() {
        if (GeneralUtils.isEmpty(serviceProperties)) {
            serviceProperties = BeanUtils.beanOfType(RiceServiceProperties.class);
            log.debug("The service holder has be initiated");
        }
        if (GeneralUtils.isEmpty(logicMark)) {
            logicMark = BeanUtils.beanOfType(RestLogicMark.class);
        }
    }

    /**
     * <code>initOfServiceIntend</code>
     * <p>The init of service intend method.</p>
     * @see  java.lang.SuppressWarnings
     */
    @SuppressWarnings("rawtypes")
    static void initOfServiceIntend() {
        if (IS_HAS_INIT_OF_SERVICE_INTEND) {
            return;
        }
        IS_HAS_INIT_OF_SERVICE_INTEND = true;
        List<RestServiceFitter> serviceIntends = ApplicationContextHolder.beansOfType(RestServiceFitter.class);
        if (GeneralUtils.isNotEmpty(serviceIntends)) {
            return;
        }
        serviceIntends = SpringFactoriesLoader.loadFactories(RestServiceFitter.class, null);
        if (GeneralUtils.isEmpty(serviceIntends)) {
            return;
        }
        for (RestServiceFitter<?> serviceIntend : serviceIntends) {
            serviceIntend = BeanDefinitionRegistryHolder.registerRootBeanDefinition(serviceIntend.beanName(), serviceIntend.beanType(), serviceIntend.beanScope());
            ListableBeanFactoryHolder.autowireBeanProperties(serviceIntend);
            serviceIntend.afterAutowirePropertiesSet();
        }
        log.debug("There are {} service intend beans has be initiated.", serviceIntends.size());
    }

    /**
     * <code>nameOfCommon</code>
     * <p>The name of common method.</p>
     * @param simpleName {@link java.lang.String} <p>The simple name parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link java.lang.String} <p>The name of common return object is <code>String</code> type.</p>
     */
    static String nameOfCommon(String simpleName) {
        String commonName;
        if (simpleName.contains(SERVICE_IMPL_SUFFIX)) {
            commonName = simpleName.replace(SERVICE_IMPL_SUFFIX, "");
        } else if (simpleName.contains(SERVICE_SUFFIX)) {
            commonName = simpleName.replace(SERVICE_SUFFIX, "");
        } else {
            commonName = simpleName;
        }
        return commonName;
    }

    /**
     * <code>findSuperMapper</code>
     * <p>The find super mapper method.</p>
     * @param <E>  {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param serviceType {@link java.lang.Class} <p>The service type parameter is <code>Class</code> type.</p>
     * @see  io.github.nichetoolkit.rice.RestId
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rice.mapper.SuperMapper
     * @see  java.lang.SuppressWarnings
     * @return  {@link io.github.nichetoolkit.rice.mapper.SuperMapper} <p>The find super mapper return object is <code>SuperMapper</code> type.</p>
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    static <E extends RestId<I>, I> SuperMapper<E, I> findSuperMapper(Class<? extends SuperService> serviceType) {
        String simpleName = serviceType.getSimpleName();
        String commonName = nameOfCommon(simpleName);
        String camelName = GeneralUtils.camelCase(commonName);
        SuperMapper<E, I> superMapper = null;
        RestService service = serviceType.getAnnotation(RestService.class);
        if (GeneralUtils.isNotEmpty(service)) {
            Class<?> mapperType = service.mapperType();
            if (mapperType != null && mapperType != SuperMapper.class) {
                superMapper = (SuperMapper<E, I>) BeanUtils.beanOfType(mapperType);
            }
        }
        if (GeneralUtils.isEmpty(superMapper)) {
            String mapperName = commonName.concat(MAPPER_SUFFIX);
            superMapper = (SuperMapper<E, I>) BeanUtils.beanOfType(mapperName, SuperMapper.class);
        }
        if (GeneralUtils.isEmpty(superMapper)) {
            String camelMapperName = camelName.concat(MAPPER_SUFFIX);
            superMapper = (SuperMapper<E, I>) BeanUtils.beanOfType(camelMapperName, SuperMapper.class);
        }
        return superMapper;
    }

    /**
     * <code>serviceProperties</code>
     * <p>The service properties method.</p>
     * @return  {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties} <p>The service properties return object is <code>RiceServiceProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceServiceProperties
     * @see  org.springframework.lang.NonNull
     */
    @NonNull
    public static RiceServiceProperties serviceProperties() {
        return serviceProperties;
    }

    /**
     * <code>logicMark</code>
     * <p>The logic mark method.</p>
     * @return  {@link io.github.nichetoolkit.rice.RestLogicMark} <p>The logic mark return object is <code>RestLogicMark</code> type.</p>
     * @see  io.github.nichetoolkit.rice.RestLogicMark
     * @see  org.springframework.lang.NonNull
     */
    @NonNull
    public static RestLogicMark logicMark() {
        return logicMark;
    }

    /**
     * <code>identityOfInvade</code>
     * <p>The identity of invade method.</p>
     * @return boolean <p>The identity of invade return object is <code>boolean</code> type.</p>
     */
    public static boolean identityOfInvade() {
        return serviceProperties.getIdentityInvade();
    }

    /**
     * <code>identityOfCheck</code>
     * <p>The identity of check method.</p>
     * @return boolean <p>The identity of check return object is <code>boolean</code> type.</p>
     */
    public static boolean identityOfCheck() {
        return serviceProperties.getIdentityCheck();
    }

    /**
     * <code>nameOfNonnull</code>
     * <p>The name of nonnull method.</p>
     * @return boolean <p>The name of nonnull return object is <code>boolean</code> type.</p>
     */
    public static boolean nameOfNonnull() {
        return serviceProperties.getNonnullName();
    }

    /**
     * <code>nameOfUnique</code>
     * <p>The name of unique method.</p>
     * @return boolean <p>The name of unique return object is <code>boolean</code> type.</p>
     */
    public static boolean nameOfUnique() {
        return serviceProperties.getUniqueName();
    }

    /**
     * <code>modelOfUnique</code>
     * <p>The model of unique method.</p>
     * @return boolean <p>The model of unique return object is <code>boolean</code> type.</p>
     */
    public static boolean modelOfUnique() {
        return serviceProperties.getUniqueModel();
    }

    /**
     * <code>dynamicOfTable</code>
     * <p>The dynamic of table method.</p>
     * @return boolean <p>The dynamic of table return object is <code>boolean</code> type.</p>
     */
    public static boolean dynamicOfTable() {
        return serviceProperties.getDynamicTable();
    }

    /**
     * <code>deleteMode</code>
     * <p>The delete mode method.</p>
     * @return  {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>The delete mode return object is <code>DeleteMode</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.DeleteMode
     */
    public static DeleteMode deleteMode() {
        return serviceProperties.getDeleteMode();
    }

    /**
     * <code>logicMode</code>
     * <p>The logic mode method.</p>
     * @return  {@link io.github.nichetoolkit.rice.enums.LogicMode} <p>The logic mode return object is <code>LogicMode</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.LogicMode
     */
    public static LogicMode logicMode() {
        return serviceProperties.getLogicMode();
    }

    /**
     * <code>skipOfBefore</code>
     * <p>The skip of before method.</p>
     * @return boolean <p>The skip of before return object is <code>boolean</code> type.</p>
     */
    public static boolean skipOfBefore() {
        return serviceProperties.skipOfBefore();
    }

    /**
     * <code>skipOfAfter</code>
     * <p>The skip of after method.</p>
     * @return boolean <p>The skip of after return object is <code>boolean</code> type.</p>
     */
    public static boolean skipOfAfter() {
        return serviceProperties.skipOfAfter();
    }

    /**
     * <code>judgeOfAccurate</code>
     * <p>The judge of accurate method.</p>
     * @return  {@link java.lang.Boolean} <p>The judge of accurate return object is <code>Boolean</code> type.</p>
     * @see  java.lang.Boolean
     */
    public static Boolean judgeOfAccurate() {
        return serviceProperties.judgeOfAccurate();
    }

    /**
     * <code>partitionOfQuery</code>
     * <p>The partition of query method.</p>
     * @return int <p>The partition of query return object is <code>int</code> type.</p>
     */
    public static int partitionOfQuery() {
        return serviceProperties.partitionOfQuery();
    }

    /**
     * <code>partitionOfSave</code>
     * <p>The partition of save method.</p>
     * @return int <p>The partition of save return object is <code>int</code> type.</p>
     */
    public static int partitionOfSave() {
        return serviceProperties.partitionOfSave();
    }

    /**
     * <code>partitionOfDelete</code>
     * <p>The partition of delete method.</p>
     * @return int <p>The partition of delete return object is <code>int</code> type.</p>
     */
    public static int partitionOfDelete() {
        return serviceProperties.partitionOfDelete();
    }

    /**
     * <code>configMark</code>
     * <p>The config mark method.</p>
     * @return  {@link io.github.nichetoolkit.rice.enums.ConfigMark} <p>The config mark return object is <code>ConfigMark</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.ConfigMark
     */
    public static ConfigMark configMark() {
        return serviceProperties.getConfigMark();
    }

    /**
     * <code>autoMark</code>
     * <p>The auto mark method.</p>
     * @return  {@link io.github.nichetoolkit.rice.enums.AutoMark} <p>The auto mark return object is <code>AutoMark</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.AutoMark
     */
    public static AutoMark autoMark() {
        return serviceProperties.getAutoMark();
    }

    /**
     * <code>markOfLogic</code>
     * <p>The mark of logic method.</p>
     * @return  {@link java.lang.Object} <p>The mark of logic return object is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static Object markOfLogic() throws RestException {
        return logicMark.getLogicMark();
    }

    /**
     * <code>unmarkOfLogic</code>
     * <p>The unmark of logic method.</p>
     * @return  {@link java.lang.Object} <p>The unmark of logic return object is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static Object unmarkOfLogic() throws RestException {
        return logicMark.getLogicUnmark();
    }

}
