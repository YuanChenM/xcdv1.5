package com.msk.mq.config;

/**
 * Created by mao_yejun on 2016/7/12.
 */
public class EnvironmentConfig {
    private String environment;
    private String configServerUrl;
    private String configLoadProperties;
    private String modelName;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getConfigServerUrl() {
        return configServerUrl;
    }

    public void setConfigServerUrl(String configServerUrl) {
        this.configServerUrl = configServerUrl;
    }

    public String getConfigLoadProperties() {
        return configLoadProperties;
    }

    public void setConfigLoadProperties(String configLoadProperties) {
        this.configLoadProperties = configLoadProperties;
    }
}
