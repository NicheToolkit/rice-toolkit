package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import io.github.nichetoolkit.rest.holder.BeanDefinitionRegistryHolder;
import io.github.nichetoolkit.rest.holder.ListableBeanFactoryHolder;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.RestServiceIntend;
import io.github.nichetoolkit.rice.configure.RiceServiceProperties;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.RemoveMode;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.mapper.SuperMapper;
import io.github.nichetoolkit.rice.stereotype.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <code>ServiceHolder</code>
 * <p>The type service holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class ServiceHolder  {

    /**
     * <code>SERVICE_IMPL_SUFFIX</code>
     * {@link java.lang.String} <p>The constant <code>SERVICE_IMPL_SUFFIX</code> field.</p>
     * @see java.lang.String
     */
    public static String SERVICE_IMPL_SUFFIX = "ServiceImpl";
    /**
     * <code>SERVICE_SUFFIX</code>
     * {@link java.lang.String} <p>The constant <code>SERVICE_SUFFIX</code> field.</p>
     * @see java.lang.String
     */
    public static String SERVICE_SUFFIX = "Service";
    /**
     * <code>MAPPER_SUFFIX</code>
     * {@link java.lang.String} <p>The constant <code>MAPPER_SUFFIX</code> field.</p>
     * @see java.lang.String
     */
    public static String MAPPER_SUFFIX = "Mapper";
    /**
     * <code>IS_HAS_INIT_OF_SERVICE_INTEND</code>
     * <p>The <code>IS_HAS_INIT_OF_SERVICE_INTEND</code> field.</p>
     */
    static boolean IS_HAS_INIT_OF_SERVICE_INTEND = false;

    /**
     * <code>SERVICE_PROPERTIES</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties} <p>The constant <code>SERVICE_PROPERTIES</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceServiceProperties
     */
    private static RiceServiceProperties SERVICE_PROPERTIES;

    /**
     * <code>initOfService</code>
     * <p>The of service method.</p>
     */
    static void initOfService() {
        if (GeneralUtils.isEmpty(SERVICE_PROPERTIES)) {
            SERVICE_PROPERTIES = BeanUtils.beanOfType(RiceServiceProperties.class);
            log.debug("The service holder has be initiated");
        }
    }

    /**
     * <code>initOfServiceIntend</code>
     * <p>The of service intend method.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("rawtypes")
    static void initOfServiceIntend() {
        if (IS_HAS_INIT_OF_SERVICE_INTEND) {
            return;
        }
        IS_HAS_INIT_OF_SERVICE_INTEND = true;
        List<RestServiceIntend> serviceIntends = ApplicationContextHolder.beansOfType(RestServiceIntend.class);
        if (GeneralUtils.isNotEmpty(serviceIntends)) {
            return;
        }
        serviceIntends = SpringFactoriesLoader.loadFactories(RestServiceIntend.class, null);
        if (GeneralUtils.isEmpty(serviceIntends)) {
            return;
        }
        for (RestServiceIntend<?> serviceIntend : serviceIntends) {
            Class<? extends RestServiceIntend> beanType = serviceIntend.beanType();
            serviceIntend = BeanDefinitionRegistryHolder.registerRootBeanDefinition(serviceIntend.beanName(), serviceIntend.beanType(), serviceIntend.beanScope());
            ListableBeanFactoryHolder.autowireBeanProperties(serviceIntend);
            serviceIntend.afterAutowirePropertiesSet();
        }
        log.debug("There are {} service intend beans has be initiated.", serviceIntends.size());
    }

    /**
     * <code>nameOfCommon</code>
     * <p>The of common method.</p>
     * @param simpleName {@link java.lang.String} <p>The simple name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The of common return object is <code>String</code> type.</p>
     * @see java.lang.String
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
     * <p>The super mapper method.</p>
     * @param <M>         {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param <E>         {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param <F>         {@link io.github.nichetoolkit.rice.filter.IdFilter} <p>The generic parameter is <code>IdFilter</code> type.</p>
     * @param <I>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param serviceType {@link java.lang.Class} <p>The service type parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.mapper.SuperMapper} <p>The super mapper return object is <code>SuperMapper</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see io.github.nichetoolkit.rice.filter.IdFilter
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rice.mapper.SuperMapper
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    static <M extends RestId<I>, E extends RestId<I>, F extends IdFilter<I, K>, I, K> SuperMapper<E, I>
    findSuperMapper(Class<? extends SuperService> serviceType) {
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
     * <p>The properties method.</p>
     * @return {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties} <p>The properties return object is <code>RiceServiceProperties</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceServiceProperties
     * @see org.springframework.lang.NonNull
     */
    @NonNull
    public static RiceServiceProperties serviceProperties() {
        return SERVICE_PROPERTIES;
    }

    /**
     * <code>identityOfInvade</code>
     * <p>The of invade method.</p>
     * @return boolean <p>The of invade return object is <code>boolean</code> type.</p>
     */
    public static boolean identityOfInvade() {
        return SERVICE_PROPERTIES.getIdentityInvade();
    }

    /**
     * <code>identityOfCheck</code>
     * <p>The of check method.</p>
     * @return boolean <p>The of check return object is <code>boolean</code> type.</p>
     */
    public static boolean identityOfCheck() {
        return SERVICE_PROPERTIES.getIdentityCheck();
    }

    /**
     * <code>nameOfNonnull</code>
     * <p>The of nonnull method.</p>
     * @return boolean <p>The of nonnull return object is <code>boolean</code> type.</p>
     */
    public static boolean nameOfNonnull() {
        return SERVICE_PROPERTIES.getNonnullName();
    }

    /**
     * <code>nameOfUnique</code>
     * <p>The of unique method.</p>
     * @return boolean <p>The of unique return object is <code>boolean</code> type.</p>
     */
    public static boolean nameOfUnique() {
        return SERVICE_PROPERTIES.getUniqueName();
    }

    /**
     * <code>modelOfUnique</code>
     * <p>The of unique method.</p>
     * @return boolean <p>The of unique return object is <code>boolean</code> type.</p>
     */
    public static boolean modelOfUnique() {
        return SERVICE_PROPERTIES.getUniqueModel();
    }

    /**
     * <code>dynamicOfTable</code>
     * <p>The of table method.</p>
     * @return boolean <p>The of table return object is <code>boolean</code> type.</p>
     */
    public static boolean dynamicOfTable() {
        return SERVICE_PROPERTIES.getDynamicTable();
    }

    /**
     * <code>deleteMode</code>
     * <p>The mode method.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>The mode return object is <code>DeleteMode</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.DeleteMode
     */
    public static DeleteMode deleteMode() {
        return SERVICE_PROPERTIES.getDeleteMode();
    }

    /**
     * <code>removeMode</code>
     * <p>The mode method.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.RemoveMode} <p>The mode return object is <code>RemoveMode</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.RemoveMode
     */
    public static RemoveMode removeMode() {
        return SERVICE_PROPERTIES.getRemoveMode();
    }

    /**
     * <code>skipOfBefore</code>
     * <p>The of before method.</p>
     * @return boolean <p>The of before return object is <code>boolean</code> type.</p>
     */
    public static boolean skipOfBefore() {
        return SERVICE_PROPERTIES.skipOfBefore();
    }

    /**
     * <code>skipOfAfter</code>
     * <p>The of after method.</p>
     * @return boolean <p>The of after return object is <code>boolean</code> type.</p>
     */
    public static boolean skipOfAfter() {
        return SERVICE_PROPERTIES.skipOfAfter();
    }

    /**
     * <code>judgeOfAccurate</code>
     * <p>The of accurate method.</p>
     * @return {@link java.lang.Boolean} <p>The of accurate return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public static Boolean judgeOfAccurate() {
        return SERVICE_PROPERTIES.judgeOfAccurate();
    }

    /**
     * <code>signOfBoolean</code>
     * <p>The of boolean method.</p>
     * @return boolean <p>The of boolean return object is <code>boolean</code> type.</p>
     */
    public static boolean signOfBoolean() {
        return SERVICE_PROPERTIES.signOfBoolean();
    }

    /**
     * <code>valueOfBoolean</code>
     * <p>The of boolean method.</p>
     * @return boolean <p>The of boolean return object is <code>boolean</code> type.</p>
     */
    public static boolean valueOfBoolean() {
        return SERVICE_PROPERTIES.valueOfBoolean();
    }

    /**
     * <code>signOfNumber</code>
     * <p>The of number method.</p>
     * @return int <p>The of number return object is <code>int</code> type.</p>
     */
    public static int signOfNumber() {
        return SERVICE_PROPERTIES.signOfNumber();
    }

    /**
     * <code>valueOfNumber</code>
     * <p>The of number method.</p>
     * @return int <p>The of number return object is <code>int</code> type.</p>
     */
    public static int valueOfNumber() {
        return SERVICE_PROPERTIES.valueOfNumber();
    }

    /**
     * <code>signOfLogic</code>
     * <p>The of logic method.</p>
     * @return {@link java.lang.String} <p>The of logic return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String signOfLogic() {
        return RemoveMode.sign(removeMode(), signOfBoolean(), signOfNumber());
    }

    /**
     * <code>valueOfLogic</code>
     * <p>The of logic method.</p>
     * @return {@link java.lang.String} <p>The of logic return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String valueOfLogic() {
        return RemoveMode.value(removeMode(), valueOfBoolean(), valueOfNumber());
    }

    /**
     * <code>partitionOfQuery</code>
     * <p>The of query method.</p>
     * @return int <p>The of query return object is <code>int</code> type.</p>
     */
    public static int partitionOfQuery() {
        return SERVICE_PROPERTIES.partitionOfQuery();
    }

    /**
     * <code>partitionOfSave</code>
     * <p>The of save method.</p>
     * @return int <p>The of save return object is <code>int</code> type.</p>
     */
    public static int partitionOfSave() {
        return SERVICE_PROPERTIES.partitionOfSave();
    }

    /**
     * <code>partitionOfDelete</code>
     * <p>The of delete method.</p>
     * @return int <p>The of delete return object is <code>int</code> type.</p>
     */
    public static int partitionOfDelete() {
        return SERVICE_PROPERTIES.partitionOfDelete();
    }
}
