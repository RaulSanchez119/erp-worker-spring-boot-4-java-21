package com.raulsanchez.worker.batch.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class BatchDataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("batchdb")
                .addScript("org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("org/springframework/batch/core/schema-h2.sql")
                .build();
    }
}