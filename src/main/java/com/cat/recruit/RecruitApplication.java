package com.cat.recruit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author cat
 */
@SpringBootApplication
@EnableAspectJAutoProxy
// 扫描mapper接口，无需在mapper接口上添加@Mapper注解
@MapperScan("com.cat.recruit.mapper")
public class RecruitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class, args);
    }
}
