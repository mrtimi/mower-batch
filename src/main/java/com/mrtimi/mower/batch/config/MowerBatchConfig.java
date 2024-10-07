package com.mrtimi.mower.batch.config;

import com.mrtimi.mower.batch.model.Lawn;
import com.mrtimi.mower.batch.model.Mower;
import com.mrtimi.mower.batch.model.Position;
import com.mrtimi.mower.batch.processor.MowerProcessor;
import com.mrtimi.mower.batch.reader.MowerReader;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class MowerBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final Lawn lawn;

    @Value("${mower.input.file}")
    private String inputFile;
    @Value("${mower.output.file}")
    private String outputFile;


    @Bean
    public Job processJob() throws IOException {
        return new JobBuilder("mowItNowJob", jobRepository)
                .start(processStep())
                .build();
    }

    @Bean
    public Step processStep() throws IOException {
        return new StepBuilder("mowItNowStep", jobRepository)
                .<Mower, Position>chunk(0, transactionManager)  // Set chunk size to match the reading pattern
                .reader(reader())
                .processor(processor(lawn))
                .writer(writer())
                .build();
    }

    @Bean
    public MowerReader reader() throws IOException {
        Resource resource = new FileSystemResource(inputFile);
        return new MowerReader(resource, lawn);
    }

    @Bean
    public MowerProcessor processor(Lawn lawn) {
        return new MowerProcessor(lawn);
    }


    @Bean
    public FlatFileItemWriter<Position> writer() {
        return new FlatFileItemWriterBuilder<Position>()
                .name("itemWriter")
                .resource(new FileSystemResource(outputFile))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }


}
