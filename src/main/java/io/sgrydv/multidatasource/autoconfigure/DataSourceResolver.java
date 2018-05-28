package io.sgrydv.multidatasource.autoconfigure;

public class DataSourceResolver {

    public static final ThreadLocal<String> DATA_SOURCE_ID_HOLDER = new ThreadLocal();

    public static String getDatasourceId() {
        return DATA_SOURCE_ID_HOLDER.get();
    }

    public static void setDatasourceId(String dataSourceId){
        DATA_SOURCE_ID_HOLDER.set(dataSourceId);
    }

}
