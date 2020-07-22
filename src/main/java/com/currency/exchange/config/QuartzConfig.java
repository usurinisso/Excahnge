package com.currency.exchange.config;

import com.currency.exchange.component.ExchangeRateTask;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail scheduleJob() {
        return JobBuilder.newJob(ExchangeRateTask.class).storeDurably()
                .withIdentity("get_exchange_rate").withDescription("Exchange rate task").build();
    }
    @Bean
    public Trigger scheduleTrigger() {
        return newTrigger().withIdentity("updateRates").forJob(scheduleJob()).
                withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 ? * MON-FRI *")).build(); //Everyday except weekends
    }
}
