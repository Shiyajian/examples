## Spring Cloud下使用Feign Form实现微服务之间的文件上传

#### 背景

​	Spring Cloud现在已经被越来越多的公司采用了，微服务架构比传统意义上的单服务架构从复杂度上多了很多，出现了很多复杂的场景。比如，我们的产品是个app，支持第三方登录功能，在手机端调用第三方授权接口之后，返回了用户的相关信息，比如open_id，性别，头像等。这些信息我们需要保存在我们服务器上，当时针对头像是应该保存图片的url还是图片本身发生了歧义，在一番讨论之后，得出的结果是，我们需要通过url将图片下载到我们本地，然后调用我们自己的文件微服务中上传功能保存起来。

​	跨服务之间调用，我们采用的是Feign组件，原生的Feign组件并不支持文件上传，但是如果添加了Feign-Form模块，那么就能上传文件，下面我通过一篇文章来讲述如何通过Feign上传文件，代码已经上传github地址。

#### 说明

个人博客首发： https://Shiyajian.github.io

github项目地址：https://github.com/Shiyajian/examples ，请找spring-cloud/chapter1

本博客所有文章除特别声明外，均采用 [BY-NC-SA](https://creativecommons.org/licenses/by-nc-sa/4.0/) 许可协议。转载请注明出处！

#### 工具

- IDE ：IntelliJ IDEA
- JDK :  jdk 8
- 构建工具：Gradle 4.10.2
- Spring Cloud 版本：Finchley.SR2 （截止2018-11-25最新的GA版本，基于boot 2.0.6）
- Spring Boot 版本：2.0.6.RELEASE （截止2018-11-25最新为2.1.0.RELEASE）

此处采用Gradle而没有使用Maven作为依赖构建和管理的工具，主要原因是我们公司目前使用的是Gradle，而且从编译速度，代码可读性和清晰度上都远远优于Maven。

#### 项目结构

​	本项目分为三个角色，分别如下：

- eurka-server ： 注册中心
- provider-server： 服务提供者，此处模拟一个文件服务器，提供文件上传功能
- consumer-server： 服务消费者，此处模拟一个业务服务，需要调用文件上传服务

大致的依赖图如下：

![](https://github.com/Shiyajian/examples/blob/master/public/images/chapter1-1.png)

#### 配置并运行

​	我们首先通过运行感受一下通过Feign上传文件的流程，在整个项目可以完整运行后，我们再参考文章和代码一起分析其中设置，并将其应用到自己的应用中

- 首先clone项目到本地

  ```shell
  git clone https://github.com/Shiyajian/examples.git
  ```

- 安装并配置Gradle
- 将项目导入到IDEA中
- 确认IDEA支持Lombok插件，默认IDEA都支持的，此步骤可忽略
- 更改IDEA设置，Project Settings(Mac中为Preferences)-> Compiler  -> Annoatation Processors  ->  [√] Enable annotation processing 
- 刷新Gradle，下载依赖并编译
- 启动注册中心
  - 找到 examples/spring-cloud/eureka-server中的EurekaApplication，运行main方法
  - 打开浏览器，运行：http://localhost:8761/，能打开证明成功
- 启动Provider项目
  - 找到 examples/spring-cloud/chapter1/provider/provider-service中的ProviderApplication，运行main方法
  - 刷新注册中心页面，找到服务证明成功
- 运行Consumer项目中的测试
  - 打开examples/spring-cloud/chapter1/consumer/consumer-server/src/test目录
  - 修改com.shiyajian.examples.consumer.service.impl.ConsumerServiceImplTest类中文件的路径为本机电脑上存在的文件
  - 运行测试方法
  - 方法绿灯结束，在控制台能找到输出为成功

#### Provider 服务配置说明

Provider服务为上传服务的提供者，这里模拟的是一个文件服务器，通过上面图，我们可以看到项目分为2部分，下面就进行详细解读：

- provider-api

  这个项目最终打成一个可以被引用的jar包，consumer-server通过引用这个jar包可以通过注入方式引用其中的方法，provider-server也需要引用这个jar包，然后实现其中的逻辑，供consumer-server远程调用。配置api的方法如下：

  - 添加org.springframework.cloud:spring-cloud-starter-openfeign依赖，只需要这一个依赖就够了，里面保存Fegin-Form等依赖。

  - 编写配置类FeignMultipartSupportConfig.java

    ```java
    public class FeignMultipartSupportConfig {
    
        @Bean
        @Primary
        @Scope("prototype")
        public Encoder multipartFormEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
            return new FeignSpringFormEncoder(new SpringEncoder(messageConverters));
        }
    
    }
    ```

  - 编写自定义的Encoder，因为这个有个设计得BUG，本身可以解析文件数组，但是代码缺少对应的判断，此处参考文章：https://blog.csdn.net/tony_lu229/article/details/73823757，代码不贴了，详细见工程

  - 定义自己的接口，这里我定义的是ProviderClient，代码简单如下：

    ```java
    @FeignClient(value = "provider-server", configuration = FeignMultipartSupportConfig.class)
    public interface ProviderClient { 
    	
    	@PostMapping(value = "client/upload/{id}", consumes = MULTIPART_FORM_DATA_VALUE)
        String uploadFile(@RequestPart("file") MultipartFile file,
                          @PathVariable("id") String id,
                          @RequestParam("name") String name);
    
        @PostMapping(value = "client/uploads", consumes = MULTIPART_FORM_DATA_VALUE)
        List<ProviderResponse> uploadFiles(@RequestPart("files") MultipartFile[] files, @RequestParam("author") String author);
    
    }
    ```

    这个接口定义时候需要有以下注意的几点：

    - @FeignClient中的value，对应的是服务实现类在eureka中注册的名字，也就是spring.application.name的值
    - configuration必须配置，就是咱们上面添加的两个类，用来编解码使用
    - 方法可以使用类似Controller中的一些注解，比如方法上可以加@RequestMapping，@PostMapping等，类上面不可以加，我试的时候，在class上加了@RequestMapping之后报错，项目启动时候显示Url报错，其实，也完全不需要加
    - 接受文件的时候，必须是@RequestPart注解，我曾经看有文章说，@RequestPart和@RequestParam通用，但是我自己测试并不是这样
    - consumes对应请求的contentType，必须为：multipart/form-data，此处使用了静态导包。
    - 在传统Controller中，我本身会经常简写@RequestParam，忽略他的value字段。但是Feign接口中不行，如果这些注解没有括号中的value那么就会报错
    - 不支持@RequestBody注解

  - provider-server

    这个项目是最后实际提供服务的项目，所以必须实现provider-api接口中的方法，并且注册到eureka服务中。

    - 添加对feign的依赖，添加api项目的依赖，其他依赖略

      ```gradle
      compile project(":provider-api")
      "org.springframework.cloud:spring-cloud-starter-feign:$feignVersion"
      ```

    - 实现provider-api中ProviderClient接口，生成实现类，并编写业务代码，需要注意两点

      - 因为父级已经在方法上增加了@PostMapping，此处可以省略
      - 如果是通过IDEA快生成的实现类，那么参数前面的@RequestPart、@RequestParam的注解需要加上，不然报错

  - consumer-server

    这个项目是消费对方提供服务的项目，需要做的也比较简单。

    - 添加provider-api的项目依赖，正式环境下，两个项目可能是不同组开发的，所以需要引入jar包，而不是直接编译此工程，这里仅做展示使用

      ```
      compile project(":provider-api")
      ```

    - 在启动类上增加注解，扫描添加Feign功能对应的包

      ```java
      @SpringCloudApplication
      // 这个注解非常重要，不然引用不到client中的方法
      @EnableFeignClients("com.shiyajian.examples.provider")
      public class ConsumerApplication {
          public static void main(String[] args) {
              SpringApplication.run(ConsumerApplication.class);
          }
      }
      ```

    - 在需要的地方通过@Autowird方式注入，然后就可以进行调用了

      ```java
      @Autowired
      ProviderClient providerClient;
      ……
      providerClient.dosomething();
      ……
      ```

  #### 总结

  ​	整个通过Feign-Form上传文件的案例就写完了，第一次写博客，写的不好还望见谅，如果文章解释的不够清楚，可以参考我的项目中的代码，代码上可能会更清晰点，代码我已经测试通过的，可以放心使用。文章中如果有写错误的地方还望各位指正，当然，如果有什么好的建议也可以给我评论和留言，如果你还其他关于java方面的教程和示例代码你也可以告诉我，我如果不忙的时候，我就会写出来。

  #### 意外

  ​	在发文章之前又做了一次测试，这次测试没有通过，通过调查发现，Eureka中项目的注册地址变成了：MacBook-Pro.local:provider-server:8100，然后调用时候就发生url错误，请求fe80:0:0:0:***:8100这个地址，等重新联网之后再次启动，注册地址就变成 192.168.1.101这种地址。

  #### 其他

  ​	QQ群：757696438是我的个人好友群，目前也就30来个人，主要就是吹牛侃大山，顺便学习技术共同进步。欢迎各种浪的飞起、闷骚到爆的同志来玩，但是不欢迎装逼的。


