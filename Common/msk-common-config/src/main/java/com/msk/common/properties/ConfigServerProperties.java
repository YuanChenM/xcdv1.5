package com.msk.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="config.server")
public class ConfigServerProperties {
    private String environment;
    private String url;
    private String configConstantUrl;
    private String propertiesUrl;
    private String moduleName;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConfigConstantUrl() {
        return configConstantUrl;
    }

    public void setConfigConstantUrl(String configConstantUrl) {
        this.configConstantUrl = configConstantUrl;
    }

    public String getPropertiesUrl() {
        return propertiesUrl;
    }

    public void setPropertiesUrl(String propertiesUrl) {
        this.propertiesUrl = propertiesUrl;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String toString() {
        return "ConfigServerProperties{" +
                "environment='" + environment + '\'' +
                ", url='" + url + '\'' +
                ", configConstantUrl='" + configConstantUrl + '\'' +
                ", propertiesUrl='" + propertiesUrl + '\'' +
                ", moduleName='" + moduleName + '\'' +
                '}';
    }
}
