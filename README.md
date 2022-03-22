# maven-plugin-demo

## 设定打包方式

```xml
<packaging>maven-plugin</packaging>
```
## 引入依赖

下面两种方式二选一：

### [1]将来在文档注释中使用注解

```xml
<dependency>
    <groupId>org.apache.maven</groupId>
    <artifactId>maven-plugin-api</artifactId>
    <version>3.8.5</version>
</dependency>
```
### [2]将来直接使用注解

```xml
<dependency>
    <groupId>org.apache.maven.plugin-tools</groupId>
    <artifactId>maven-plugin-annotations</artifactId>
    <version>3.8.5</version>
</dependency>
```


## 创建 Mojo 类

Mojo 类是一个 Maven 插件的核心类。

Mojo 这个单词的意思是：Maven Old Java Object，其实 mojo 这个单词本身包含魔力;符咒(袋);护身符;(人的)魅力的含义，Maven 用 Mojo 是因为它是对 POJO 开的一个小玩笑。

### [1] Mojo 接口

每一个 Mojo 都需要实现 org.apache.maven.plugin.Mojo 接口。
### [2] AbstractMojo 抽象类

我们实现 Mojo 接口比较困难，幸好可以继承 AbstractMojo，此时我们只要实现 execute() 这一个方法即可。
```java
public class MyPlugin extends AbstractMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("This is my first maven plugin");
    }
}
```
## 插件配置
对应的 pom.xml 中的依赖： `maven-plugin-api`


```xml
<dependency>
    <groupId>org.apache.maven</groupId>
    <artifactId>maven-plugin-api</artifactId>
    <version>3.8.5</version>
</dependency>
```
//goal：指定目标名称
```java
/**
 * @goal hi
 */
public class MyPlugin extends AbstractMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("This is my first maven plugin");
    }
}
```
#[2]直接在类上标记注解
对应 pom.xml 中的依赖：`maven-plugin-annotations`

```xml
<dependency>
    <groupId>org.apache.maven</groupId>
    <artifactId>maven-plugin-annotations</artifactId>
    <version>3.8.5</version>
</dependency>
```
// name 属性：指定目标名称
```java
@Mojo(name = "firstBlood")
public class MyPlugin extends AbstractMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("This is my first maven plugin");
    }
}
```
## 安装插件
要在后续使用插件，就必须至少将插件安装到本地仓库。

## 识别插件前缀

Maven 根据插件的 artifactId 来识别插件前缀。例如下面两种情况：

### [1]前置匹配

匹配规则：${prefix}-maven-plugin
artifactId：hello-maven-plugin
前缀：hello

### [2]中间匹配

匹配规则：maven-${prefix}-plugin
artifactId：maven-good-plugin
前缀：good

## 配置到 build 标签里

这里找一个和插件无关的 Maven 工程配置才有说服力。

### [1]配置

```xml
<build>
  <plugins>
    <plugin>
      <!-- 根据weaveown找到前缀 -->
      <groupId>cn.weaveown.maven</groupId>
      <artifactId>weaveown-maven-plugin</artifactId>
      <version>1.0-SNAPSHOT</version>
      <executions>
        <execution>
          <phase>validate</phase>
          <goals>
            <!-- 根据goal找到目标 -->
            <goal>hi</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

### [2]效果

![](https://gitee.com/WeaveOwn/blog-img/raw/master/img/20220322171214.png)

![](https://gitee.com/WeaveOwn/blog-img/raw/master/img/20220322171153.png)

