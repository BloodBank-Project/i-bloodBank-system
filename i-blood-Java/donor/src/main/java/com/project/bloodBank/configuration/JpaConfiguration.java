package com.project.bloodBank.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.project.bloodBank")
@EnableTransactionManagement
public class JpaConfiguration {

}
