package com.itgmm.config;

import com.itgmm.pojo.SystemConstants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.plaf.PanelUI;

@Configuration
@MapperScan("com.itgmm.mapper")
public class CommonConfiguration {


    @Bean
    public ChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }

    @Bean
    public ChatClient chatClient(OpenAiChatModel model, ChatMemory chatMemory) {

        return ChatClient.builder(model)
                .defaultSystem(SystemConstants.PROMPT)
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        new MessageChatMemoryAdvisor(chatMemory))
                .build();
    }
}
