package io.sgrydv.multidatasource.autoconfigure;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@AutoConfigureBefore(value= {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({MultiDataSourceProperties.class})
public class MultiDataSourceAutoConfiguration {

    @Autowired
    MultiDataSourceProperties dataSourceProperties;

    @SuppressWarnings("unchecked")
    protected <T> T createDataSource(DataSourceProperties properties,
                                     Class<? extends DataSource> type) {
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }

    @Bean(name = "datasource")
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        MultiRoutingDataSource routingDataSource = new MultiRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap();
        List<MultiDataSourceProperties.DataSourceProperties> tenantDataSources = dataSourceProperties.getDataSources();
        for(MultiDataSourceProperties.DataSourceProperties properties : tenantDataSources) {
            targetDataSources.put(properties.getDataSourceId(), createDataSource(properties, BasicDataSource.class));
            System.out.println(properties.getDataSourceId());
        }
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(targetDataSources.get("tenant1"));
        return routingDataSource;
    }

}
