package com.config;


import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
//@ComponentScan("com.aceboot")
@PropertySource(value = "classpath:swagger2.properties", ignoreResourceNotFound = true, encoding = "UTF-8")
//@Profile("dev")
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    private Boolean enabled = false;

    /**
     * @return
     */
    /*
     * 创建API应用
     * apiInfo() 增加API相关信息
     * select() 选择哪些路径和api会生成document
     * apis() 对所有api进行监控
     * paths() 对所有路径进行监控
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ace.boot.controller"))
                //.apis(SwaggerConfig.basePackage("com.controller,com.restController"))
                .paths(PathSelectors.any()).build();
    }


    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                return declaringClass(input).transform(handlerPackage(basePackage)).or(true);
            }
        };
    }

    /**
     * 处理包路径配置规则,支持多路径扫描匹配以逗号隔开
     *
     * @param basePackage 扫描包路径
     * @return Function
     */
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return new Function<Class<?>, Boolean>() {
            @Override
            public Boolean apply(Class<?> input) {
                for (String strPackage : basePackage.split(",")) {
                    boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                    if (isMatch) {
                        return true;
                    }
                }
                return false;
            }
        };
    }


    /*
     * 创建该API的基本信息
     * title:访问界面的标题
     * description：描述
     * version：版本号
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Ace API documents (Swagger)").description("base on SpringBoot").description("前后分离框架").version("1.0").license("version1.0").build();
    }
    //http://localhost:8088/swagger-ui.html


}
