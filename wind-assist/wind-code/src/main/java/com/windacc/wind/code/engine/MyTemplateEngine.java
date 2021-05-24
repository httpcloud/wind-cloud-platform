package com.windacc.wind.code.engine;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.windacc.wind.toolkit.utils.RequestUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/4/7 15:35
 */
@Slf4j
public class MyTemplateEngine extends FreemarkerTemplateEngine {

    private Configuration configuration;
    private ByteArrayOutputStream outputStream;
    //private ZipOutputStream zipOutputStream;

    @Override
    public FreemarkerTemplateEngine init(ConfigBuilder configBuilder) {
        super.init(configBuilder);
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(ConstVal.UTF8);
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, StringPool.SLASH);

        outputStream = new ByteArrayOutputStream();
        //zipOutputStream = new ZipOutputStream(outputStream);

        return this;
    }

    //@Override
    //public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
    //    Template template = configuration.getTemplate(templatePath);
    //    StringWriter stringWriter = new StringWriter();
    //    template.process(objectMap, stringWriter);
    //
    //    zipOutputStream.putNextEntry(new ZipEntry(outputFile));
    //    IOUtils.write(stringWriter.toString(), zipOutputStream, "UTF-8");
    //    IOUtils.closeQuietly(stringWriter);
    //    zipOutputStream.closeEntry();
    //
    //    log.info("模板:" + templatePath + ";  文件:" + outputFile);
    //}

    @Override
    public AbstractTemplateEngine batchOutput() {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            List<TableInfo> tableInfoList = getConfigBuilder().getTableInfoList();
            for (TableInfo tableInfo : tableInfoList) {
                Map<String, Object> objectMap = getObjectMap(tableInfo);
                // 自定义内容
                InjectionConfig injectionConfig = getConfigBuilder().getInjectionConfig();
                if (null != injectionConfig) {
                    injectionConfig.initTableMap(tableInfo);
                    objectMap.put("cfg", injectionConfig.getMap());
                    List<FileOutConfig> focList = injectionConfig.getFileOutConfigList();
                    if (CollectionUtils.isNotEmpty(focList)) {
                        for (FileOutConfig foc : focList) {
                            //writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                            String templatePath = foc.getTemplatePath();
                            String outputFile = foc.outputFile(tableInfo);
                            Template template = configuration.getTemplate(templatePath);

                            try (StringWriter stringWriter = new StringWriter()) {
                                template.process(objectMap, stringWriter);

                                zipOutputStream.putNextEntry(new ZipEntry(outputFile));
                                IOUtils.write(stringWriter.toString(), zipOutputStream, "UTF-8");
                                zipOutputStream.closeEntry();

                                log.info("模板:" + templatePath + ";  文件:" + outputFile);
                            }

                        }
                    }
                }
            }
            //IOUtils.closeQuietly(zipOutputStream);
            zipOutputStream.close();
            byte[] data = outputStream.toByteArray();

            HttpServletResponse response = RequestUtil.getResponse();
            Objects.requireNonNull(response).reset();
            response
                .setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("generator.zip", "utf-8"));
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream; charset=UTF-8");

            IOUtils.write(data, response.getOutputStream());
        } catch (Exception e) {
            logger.error("无法创建文件，请检查配置信息！", e);
        }
        return this;
    }

}
