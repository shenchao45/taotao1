package com.shenchao.taotao1.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenchao on 2017/1/2.
 */
public class FreeMarkerTest {
    @Test
    public void testFreeMarker() throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\shenchao\\IdeaProjects\\taotao1\\taotao1-portal\\src\\main\\webapp\\WEB-INF\\ftl"));
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("first.ftl");
        Map root = new HashMap<>();
        Student student = new Student("1", "沈超", "江苏");
        root.put("student", student);
        root.put("title", "沈超");
        ArrayList list = new ArrayList();
        for(int i =0 ;i<3;i++) {
            Student student1 = new Student(i+"", "沈超"+i, "江苏");
            list.add(student1);
        }
        root.put("students", list);
        Writer out = new FileWriter("D:\\html\\hello1.html");
        template.process(root,out);
        out.close();
    }
}
