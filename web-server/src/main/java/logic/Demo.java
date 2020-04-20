package logic;

import com.nebula.mooc.webserver.WebServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@MapperScan("logic.mappers.Devicemappers")//对应你的mapper路径，应为我们要用这个扫描它
@MapperScan("logic.pojo.Device")//当然以访万一User找不到

public class Demo {
    public static void main(String[] args) {
        SpringApplication.run(Demo.class, args);
    }
}
