package com.bridgelabz.springbatch.configu;

import com.bridgelabz.springbatch.model.User;
import com.bridgelabz.springbatch.repository.UserRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FlatFileFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfigu {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
//    @Autowired
//    private UserRepository userRepository;

    @Bean
    public FlatFileItemReader<User> reader() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("user.csv"));
        reader.setLineMapper(getLineMapper());
        reader.setLinesToSkip(1);
        return reader;
    }
    private LineMapper<User> getLineMapper() {
        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"UserId", "firstName", "lastName"});
        lineTokenizer.setIncludedFields(new int[]{0, 1, 2});


        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        lineMapper.setLineTokenizer(lineTokenizer);   //to separate from ","(cama)
        lineMapper.setFieldSetMapper(fieldSetMapper);    //to set fields
        return lineMapper;
    }

    @Bean
    public UserItemProcessor processor() {
        return new UserItemProcessor();
    }

//    @Bean
//    public RepositoryItemWriter<User> writer() {
//        RepositoryItemWriter<User> writer = new RepositoryItemWriter<>();
//        writer.setRepository(userRepository);
//        writer.setMethodName("save");
//        return writer;
//    }

    public JdbcBatchItemWriter<User> writer(){
        JdbcBatchItemWriter<User> writer= new JdbcBatchItemWriter<>();
        writer.setItemPreparedStatementSetter((ItemPreparedStatementSetter<User>) new BeanPropertyItemSqlParameterSourceProvider<User>());
        writer.setSql("insert into user(userId, firstName, lastName) values (:userId,:firstname,:lastName)");
        writer.setDataSource(this.dataSource);
        return writer;
    }

    public Step step1() {
        return this.stepBuilderFactory.get("Step1"). <User, User>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    public Job runJob(){
        return jobBuilderFactory.get("importingUser").incrementer(new RunIdIncrementer())
                .flow(step1()).end().build();
    }
}