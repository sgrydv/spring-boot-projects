package io.sgrydv.multidatasource.autoconfigure;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiDataSource extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceResolver.getDataSourceId();
    }
}
