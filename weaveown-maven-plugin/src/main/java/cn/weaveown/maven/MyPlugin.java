package cn.weaveown.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author wangwei
 * @date 2022/3/22
 * @goal hi
 */
public class MyPlugin extends AbstractMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("This is my first maven plugin");
    }
}
