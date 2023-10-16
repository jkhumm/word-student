package com.mode.technology.config;
 
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

// 参考博客：https://blog.csdn.net/Alian_1223/article/details/131172257?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-131172257-blog-107495008.235%5Ev38%5Epc_relevant_sort_base1&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-131172257-blog-107495008.235%5Ev38%5Epc_relevant_sort_base1&utm_relevant_index=1
@Slf4j
public class MyShardingDatabaseAlgorithm implements PreciseShardingAlgorithm<String> {

    /**
     * 自定义数据库分库类
     * @param collection 配置文件中数据库的数据源
     * @param preciseShardingValue 查询语句中查询值
     * @return 返回符合查询条件的数据库名称
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
            for (String databaseSource : collection) {
                // 获取分片键的值
                String value = preciseShardingValue.getValue();
                log.info("分片键的值:{},逻辑表:{}", value, preciseShardingValue.getLogicTableName());
                // 如果遍历的数据库包含了我们的区域码就直接返回
                if (databaseSource.contains(value)) {
                    log.info("数据库为：" + databaseSource);
                    return databaseSource;
                }
            }
        // 不存在则抛出异常
        throw new UnsupportedOperationException();
    }

}