package io.sgrydv.multidatasource.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "multidatasources.spring.datasource")
public class MultiDataSourceProperties {

    private List<DataSourceProperties> dataSources;

    public List<DataSourceProperties> getDataSources() {
        return this.dataSources;
    }

    public void setDataSources(List<DataSourceProperties> dataSourcesProps) {
        this.dataSources = dataSourcesProps;
    }

    public static class DataSourceProperties extends org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
    {

        private String dataSourceId;

        public String getDataSourceId() {
            return dataSourceId;
        }

        public void setDataSourceId(String dataSourceId) {
            this.dataSourceId = dataSourceId;
        }
    }
}

