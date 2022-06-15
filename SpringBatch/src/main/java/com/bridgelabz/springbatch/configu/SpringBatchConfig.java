package com.bridgelabz.springbatch.configu;

import com.bridgelabz.springbatch.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
//    @Autowired
//    private UserRepository userRepository;

    public FlatFileItemReader<User> reader() {
        FlatFileItemReader<User> readertoDB = new FlatFileItemReader<>();
        readertoDB.setResource(new ClassPathResource("user.csv"));
        readertoDB.setLineMapper(new DefaultLineMapper<User>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames(User.fields());
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<User>(){
                    {
                        setTargetType(User.class);
                    }
                });
            }
        });
//        reader.setLineMapper(getLineMapper());
//        reader.setLinesToSkip(1);
        return readertoDB;
    }

//    private LineMapper<User> getLineMapper() {
//        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//        lineTokenizer.setNames(new String[]{"UserId", "firstName", "lastName"});
//        lineTokenizer.setIncludedFields(new int[]{0, 1, 2});
//
//
//        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(User.class);
//
//        lineMapper.setLineTokenizer(lineTokenizer);   //to separate from ","(cama)
//        lineMapper.setFieldSetMapper(fieldSetMapper);    //to set fields
//        return lineMapper;
//    }

//    @Bean
//    public UserItemProcessor processor() {
//        return new UserItemProcessor();
//    }

//    @Bean
//    public RepositoryItemWriter<User> writer() {
//        RepositoryItemWriter<User> writer = new RepositoryItemWriter<>();
//        writer.setRepository(userRepository);
//        writer.setMethodName("save");
//        return writer;
//    }

    public JdbcBatchItemWriter<User> writer(){
        JdbcBatchItemWriter<User> writertoDB= new JdbcBatchItemWriter<>();
        writertoDB.setDataSource(dataSource);
        writertoDB.setSql("insert into user(userId, firstName, lastName) values (:userId,:firstname,:lastName)");
        writertoDB.setItemPreparedStatementSetter((ItemPreparedStatementSetter<User>) new BeanPropertyItemSqlParameterSourceProvider<User>());
        return writertoDB;
    }

    public Step step() {
        return this.stepBuilderFactory.get("step"). <User, User>chunk(10)
                .reader(reader())
                .writer(writer())
                .build();
    }
    public Job Job(){
        return jobBuilderFactory.get("job")   //.incrementer(new RunIdIncrementer())
                .flow(step()).end().build();
    }
}