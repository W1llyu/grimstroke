package com.ouresports.grimstroke.base.config;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;

/**
 *
 * @author will
 * @date 2018/12/21
 */
public class GeneralFastjsonConfig {
    private volatile static FastJsonConfig instance;

    public static FastJsonConfig getFastJsonConfig() {
        if (instance == null) {
            synchronized (FastJsonConfig.class) {
                if (instance == null) {
                    initInstance();
                }
            }
        }
        return instance;
    }

    private static void initInstance() {
        instance = new FastJsonConfig();
        instance.setSerializerFeatures(
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteEnumUsingToString
        );
        instance.setDateFormat("yyyy-MM-dd HH:mm:ss");
        SerializeConfig serializeConfig = new SerializeConfig();
        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        instance.setSerializeConfig(serializeConfig);
    }
}
