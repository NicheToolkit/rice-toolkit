package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import io.github.nichetoolkit.rest.holder.BeanDefinitionRegistryHolder;
import io.github.nichetoolkit.rest.holder.ListableBeanFactoryHolder;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.ServiceIntend;
import io.github.nichetoolkit.rice.configure.RiceServiceProperties;
import io.github.nichetoolkit.rice.enums.AutoMark;
import io.github.nichetoolkit.rice.enums.ConfigMark;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.LogicMode;
import io.github.nichetoolkit.rice.mapper.SuperMapper;
import io.github.nichetoolkit.rice.resolver.RestLogicResolver;
import io.github.nichetoolkit.rice.stereotype.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;

@Slf4j
public class ServiceHolder {

    public static String SERVICE_IMPL_SUFFIX = "ServiceImpl";
    public static String SERVICE_SUFFIX = "Service";
    public static String MAPPER_SUFFIX = "Mapper";
    static boolean IS_HAS_INIT_OF_SERVICE_INTEND = false;

    private static RiceServiceProperties SERVICE_PROPERTIES;

    static void initOfService() {
        if (GeneralUtils.isEmpty(SERVICE_PROPERTIES)) {
            SERVICE_PROPERTIES = BeanUtils.beanOfType(RiceServiceProperties.class);
            log.debug("The service holder has be initiated");
        }
    }

    @SuppressWarnings("rawtypes")
    static void initOfServiceIntend() {
        if (IS_HAS_INIT_OF_SERVICE_INTEND) {
            return;
        }
        IS_HAS_INIT_OF_SERVICE_INTEND = true;
        List<ServiceIntend> serviceIntends = ApplicationContextHolder.beansOfType(ServiceIntend.class);
        if (GeneralUtils.isNotEmpty(serviceIntends)) {
            return;
        }
        serviceIntends = SpringFactoriesLoader.loadFactories(ServiceIntend.class, null);
        if (GeneralUtils.isEmpty(serviceIntends)) {
            return;
        }
        for (ServiceIntend<?> serviceIntend : serviceIntends) {
            serviceIntend = BeanDefinitionRegistryHolder.registerRootBeanDefinition(serviceIntend.beanName(), serviceIntend.beanType(), serviceIntend.beanScope());
            ListableBeanFactoryHolder.autowireBeanProperties(serviceIntend);
            serviceIntend.afterAutowirePropertiesSet();
        }
        log.debug("There are {} service intend beans has be initiated.", serviceIntends.size());
    }

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

    @NonNull
    public static RiceServiceProperties serviceProperties() {
        return SERVICE_PROPERTIES;
    }

    public static boolean identityOfInvade() {
        return SERVICE_PROPERTIES.getIdentityInvade();
    }

    public static boolean identityOfCheck() {
        return SERVICE_PROPERTIES.getIdentityCheck();
    }

    public static boolean nameOfNonnull() {
        return SERVICE_PROPERTIES.getNonnullName();
    }

    public static boolean nameOfUnique() {
        return SERVICE_PROPERTIES.getUniqueName();
    }

    public static boolean modelOfUnique() {
        return SERVICE_PROPERTIES.getUniqueModel();
    }

    public static boolean dynamicOfTable() {
        return SERVICE_PROPERTIES.getDynamicTable();
    }

    public static DeleteMode deleteMode() {
        return SERVICE_PROPERTIES.getDeleteMode();
    }

    public static LogicMode logicMode() {
        return SERVICE_PROPERTIES.getLogicMode();
    }

    public static boolean skipOfBefore() {
        return SERVICE_PROPERTIES.skipOfBefore();
    }

    public static boolean skipOfAfter() {
        return SERVICE_PROPERTIES.skipOfAfter();
    }

    public static Boolean judgeOfAccurate() {
        return SERVICE_PROPERTIES.judgeOfAccurate();
    }

    public static int partitionOfQuery() {
        return SERVICE_PROPERTIES.partitionOfQuery();
    }

    public static int partitionOfSave() {
        return SERVICE_PROPERTIES.partitionOfSave();
    }

    public static int partitionOfDelete() {
        return SERVICE_PROPERTIES.partitionOfDelete();
    }

    public static ConfigMark configMark() {
        return SERVICE_PROPERTIES.getConfigMark();
    }

    public static AutoMark autoMark() {
        return SERVICE_PROPERTIES.getAutoMark();
    }

    public static Object markOfLogic() throws RestException {
        if (logicMode() == LogicMode.CONFIG) {
            return LogicMode.valueOfConfigMark(SERVICE_PROPERTIES.getConfigMark(), SERVICE_PROPERTIES.markOfConfig());
        } else {
            AutoMark autoMark = SERVICE_PROPERTIES.getAutoMark();
            Object resolveLogic = RestLogicResolver.resolveLogic(autoMark);
            if (GeneralUtils.isNotEmpty(resolveLogic)) {
                return resolveLogic;
            }
            Object markValue;
            switch (autoMark) {
                case VERSION:
                    markValue = System.currentTimeMillis();
                    break;
                case DATETIME:
                    markValue = new Date();
                    break;
                case IDENTITY:
                default:
                    markValue = IdentityUtils.valueOfString();
            }
            return markValue;
        }
    }

    public static Object unmarkOfLogic() throws RestException {
        if (logicMode() == LogicMode.CONFIG) {
            return LogicMode.valueOfConfigUnmark(SERVICE_PROPERTIES.getConfigMark(), SERVICE_PROPERTIES.unmarkOfConfig());
        } else {
            return null;
        }
    }

}
