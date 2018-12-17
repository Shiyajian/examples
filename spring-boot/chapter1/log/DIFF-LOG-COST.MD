# Java日志格式应该是占位符还是字符串拼接？

## 背景

​	上次在群中，有个群友说自己把所有项目中，所有使用占位符打印日志的方式都修改成为了字符串拼接的方式，因为他曾经看了一篇文章，说字符串拼接的形式比占位符形式的性能更好，这个话题引起了大家的广泛讨论。

​	在我印象中，我记得曾经看过的文章说，占位符的方式性能好，因为如果不打日志的话，就不会进行字符串拼接，节省性能。最后抱着一探究竟的心态，我进行了一次日志输出方式的实验。

## 工具

IDEA + Gradle + JDK 8 + SpringBoot + Junit + Slf4j + logback

## 测试方式

本次测试我定义了4个方法，每个方法同时打印 DEBUG ，INFO 日志，默认日志级别是INFO级别的，所有DEBUG级别日志是不输出的，这样可以检测输出的日志会不会被不输出级别日志影响，同时我还给部分方法增加了if判断，这个是为了测试，我们是否有必要显性的进行日志级别的操作，具体代码如下：

```java
 public void record(LogEntity logEntity) {
        for (int i = 0; i < 1000; i++) {
            if (log.isDebugEnabled()) {
                log.debug("id:{},name:{},ip:{},url:{},params:{}",
                        logEntity.getId(),
                        logEntity.getName(),
                        logEntity.getId(),
                        logEntity.getUrl(),
                        logEntity.getParams());
            }
            log.info("id:{},name:{},ip:{},url:{},params:{}",
                    logEntity.getId(),
                    logEntity.getName(),
                    logEntity.getId(),
                    logEntity.getUrl(),
                    logEntity.getParams());
        }
    }

    public void record1(LogEntity logEntity) {
        for (int i = 0; i < 1000; i++) {
            log.debug("id:{},name:{},ip:{},url:{},params:{}",
                    logEntity.getId(),
                    logEntity.getName(),
                    logEntity.getId(),
                    logEntity.getUrl(),
                    logEntity.getParams());
            log.info("id:{},name:{},ip:{},url:{},params:{}",
                    logEntity.getId(),
                    logEntity.getName(),
                    logEntity.getId(),
                    logEntity.getUrl(),
                    logEntity.getParams());
        }
    }

    public void record2(LogEntity logEntity) {
        for (int i = 0; i < 1000; i++) {
            if (log.isDebugEnabled()) {
                log.debug("id:" + logEntity.getId() +
                        ",name:" + logEntity.getName() +
                        ",ip:" + logEntity.getId() +
                        ",url:" + logEntity.getUrl() +
                        ",params:" + logEntity.getParams());
            }
            log.info("id:" + logEntity.getId() +
                    ",name:" + logEntity.getName() +
                    ",ip:" + logEntity.getId() +
                    ",url:" + logEntity.getUrl() +
                    ",params:" + logEntity.getParams());

        }
    }

    public void record3(LogEntity logEntity) {
        for (int i = 0; i < 1000; i++) {
            log.debug("id:" + logEntity.getId() +
                      ",name:" + logEntity.getName() +
                      ",ip:" + logEntity.getId() +
                      ",url:" + logEntity.getUrl() +
                      ",params:" + logEntity.getParams());
            log.info("id:" + logEntity.getId() +
                    ",name:" + logEntity.getName() +
                    ",ip:" + logEntity.getId() +
                    ",url:" + logEntity.getUrl() +
                    ",params:" + logEntity.getParams());

        }
    }
```

## 测试结果

我运行了6次Junit测试用例，结果如下：

![log](https://github.com/Shiyajian/examples/blob/master/public/images/log.png)

## 结论

通过测试结果可以有以下发现：

- 字符串拼接的方法，大部分下情况性能高于占位符

- 如果采用占位符的方式，一定不要增加 **log.isDebugEnabled()**这种方式再显性的进行判断，否则性能会大大降低

  > 昨天看到nacos项目，在翻看nacos源码的时候，发现针对日志输出这块，nacos也使用的字符串拼接

本文没有太高深的道理和原理，只是因为一个小的讨论，进行了一次实验，实验的过程和结果是否准确可靠还需要大家各自斟酌，同时希望借此抛砖引玉，能有大神给更加详细的解答。

github地址：  https://github.com/Shiyajian/examples ,查看 spring-boot/chapter1/log

## 其他

- 自己写了个插件，可以打开CSDN的博客之后，自动展开全部内容，不用每次点【查看更多】，然后还需要登录那么麻烦了，github: https://github.com/Shiyajian/CSDN-clear.git

- 个人QQ好友群：757696438，吹牛扯淡为主，技术为辅，拒绝装逼，最欢迎妹子。
- 个人微信：q408859832 技术交流为主
- 备注：博客园

