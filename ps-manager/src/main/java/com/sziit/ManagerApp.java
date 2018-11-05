package com.sziit;

/*
 *  @项目名：  ps-parent 
 *  @包名：    com.sziit
 *  @文件名:   ManagerApp
 *  @创建者:   dzy
 *  @创建时间:  2018/10/15 9:28
 *  @描述：    TODO
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//设置自动检测工作，不包含数据源的检测
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class ManagerApp {
    public static void main(String [] args){
        SpringApplication.run(ManagerApp.class , args);
    }
}
