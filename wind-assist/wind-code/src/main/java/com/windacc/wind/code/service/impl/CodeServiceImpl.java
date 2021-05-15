package com.windacc.wind.code.service.impl;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.windacc.wind.code.constants.CodeGenConstant;
import com.windacc.wind.code.engine.MyTemplateEngine;
import com.windacc.wind.code.service.ICodeService;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 14:40
 */
@Service
public class CodeServiceImpl implements ICodeService {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public void codeGen(String tableNames) {

        AutoGenerator mpg = new AutoGenerator();
        Configuration config = getConfig();

        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(null);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setAuthor(config.getString("author", "codeGen"));
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setOpen(false);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        mpg.setDataSource(dsc);

        PackageConfig pc = new PackageConfig();
        pc.setModuleName(config.getString("moduleName", ""));
        pc.setParent(config.getString("package", "com.open.capacity"));
        mpg.setPackageInfo(pc);

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/template/my.entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "entity/" + tableInfo.getEntityName() + ".java";
            }
        });
        focList.add(new FileOutConfig("/template/my.mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "xml/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        focList.add(new FileOutConfig("/template/my.mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "mapper/" + tableInfo.getEntityName() + "Mapper.java";
            }
        });
        focList.add(new FileOutConfig("/template/my.service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "service/I" + tableInfo.getEntityName() + "Service.java";
            }
        });
        focList.add(new FileOutConfig("/template/my.serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "impl/" + tableInfo.getEntityName() + "ServiceImpl.java";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        tc.setMapper(null);
        tc.setEntity(null);
        tc.setController(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        mpg.setTemplate(tc);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(config.getString("tablePrefix", ""));
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 字段名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude(tableNames.split(","));
        strategy.setEntityLombokModel(true);
        strategy.setEntityBuilderModel(false);
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        // 是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 自定义 mapper 父类
        strategy.setSuperMapperClass(CodeGenConstant.SUPER_MAPPER_CLASS);
        // 自定义 service 父类
        strategy.setSuperServiceClass(CodeGenConstant.SUPER_SERVICE_CLASS);
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass(CodeGenConstant.SUPER_SERVICE_IMPL_CLASS);
        // 设置填充字段
        List<TableFill> list = new ArrayList<>();
        list.add(new TableFill("create_time", FieldFill.INSERT));
        list.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
        strategy.setTableFillList(list);

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new MyTemplateEngine());
        mpg.execute();

    }

    private Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties" );
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败，", e);
        }
    }

}
