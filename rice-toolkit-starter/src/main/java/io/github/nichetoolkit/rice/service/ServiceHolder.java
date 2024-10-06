package io.github.nichetoolkit.rice.service;

import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.configure.RiceServiceProperties;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.RemoveMode;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.mapper.SuperMapper;
import io.github.nichetoolkit.rice.stereotype.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

@Slf4j
public class ServiceHolder {

    public static String SERVICE_IMPL_SUFFIX = "ServiceImpl";
    public static String SERVICE_SUFFIX = "Service";
    public static String MAPPER_SUFFIX = "Mapper";

    private static RiceServiceProperties serviceProperties;

    static void initOfService() {
        serviceProperties = BeanUtils.beanOfType(RiceServiceProperties.class);
        log.debug("The service holder has be initiated");
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

    @SuppressWarnings({"unchecked","rawtypes"})
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

    @NonNull
    public static RiceServiceProperties serviceProperties() {
        return serviceProperties;
    }

    public static boolean identityOfInvade() {
        return serviceProperties.getIdentityInvade();
    }

    public static boolean identityOfCheck() {
        return serviceProperties.getIdentityCheck();
    }

    public static boolean nameOfNonnull() {
        return serviceProperties.getNonnullName();
    }

    public static boolean nameOfUnique() {
        return serviceProperties.getUniqueName();
    }

    public static boolean modelOfUnique() {
        return serviceProperties.getUniqueModel();
    }

    public static boolean dynamicOfTable() {
        return serviceProperties.getDynamicTable();
    }

    public static DeleteMode deleteMode() {
        return serviceProperties.getDeleteMode();
    }

    public static RemoveMode removeMode() {
        return serviceProperties.getRemoveMode();
    }

    public static boolean skipOfBefore() {
        return serviceProperties.skipOfBefore();
    }

    public static boolean skipOfAfter() {
        return serviceProperties.skipOfAfter();
    }

    public static Boolean judgeOfAccurate() {
        return serviceProperties.judgeOfAccurate();
    }

    public static boolean signOfBoolean() {
        return serviceProperties.signOfBoolean();
    }

    public static boolean valueOfBoolean() {
        return serviceProperties.valueOfBoolean();
    }

    public static int signOfNumber() {
        return serviceProperties.signOfNumber();
    }

    public static int valueOfNumber() {
        return serviceProperties.valueOfNumber();
    }

    public static String signOfLogic() {
        return RemoveMode.sign(removeMode(),  signOfBoolean(), signOfNumber());
    }

    public static String valueOfLogic() {
        return RemoveMode.value(removeMode(), valueOfBoolean(), valueOfNumber());
    }

    public static int partitionOfQuery() {
        return serviceProperties.partitionOfQuery();
    }

    public static int partitionOfSave() {
        return serviceProperties.partitionOfSave();
    }

    public static int partitionOfDelete() {
        return serviceProperties.partitionOfDelete();
    }
}
