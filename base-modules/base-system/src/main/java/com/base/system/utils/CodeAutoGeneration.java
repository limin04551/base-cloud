package com.base.system.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.stereotype.Component;

/**
 * @author min
 * @description
 * @date 2020/12/24
 */

@Component
public class CodeAutoGeneration {

    /*public static void main(String[] args) {
        CodeAutoGeneration.generation(false,
                "/base-modules/base-storage",
                "storage",
                "t_storage"
                );
    }

    public  static void generation(Boolean override,String secondModuleName,String moduleName,String... tableName){
        // 需要构建一个 代码自动生成器 对象
        AutoGenerator mpg = new AutoGenerator();
        // 配置策略
        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+secondModuleName+"/src/main/java");
        gc.setAuthor("jiuyue");
        gc.setOpen(false);
        gc.setFileOverride(override); // 是否覆盖
        gc.setServiceName("%sService"); // 去Service的I前缀
        gc.setIdType(IdType.AUTO);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(false);

        mpg.setGlobalConfig(gc);
        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.01:3306/base?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent("com.base");
        pc.setEntity("domain");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);
        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix("t_");
        strategy.setInclude(tableName);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        mpg.setStrategy(strategy);
        mpg.execute(); //执行
        System.out.println("生成成功");

    }*/


    /*public static void main(String[] args) {
        // 需要构建一个 代码自动生成器 对象
        AutoGenerator mpg = new AutoGenerator();
        // 配置策略
        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("limin");
        gc.setOpen(false);
        gc.setFileOverride(false); // 是否覆盖
        gc.setServiceName("%sService"); // 去Service的I前缀
        gc.setIdType(IdType.AUTO);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);
        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/ht_base?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("base");
        pc.setParent("com.huateng");
        pc.setEntity("domain");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);
        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
//        strategy.setInclude("blog_tags","course","links","sys_settings","user_record"," user_say"); // 设置要映射的表名 strategy.setNaming(NamingStrategy.underline_to_camel); strategy.setColumnNaming(NamingStrategy.underline_to_camel); strategy.setEntityLombokModel(true); // 自动lombok； strategy.setLogicDeleteFieldName("deleted"); // 自动填充配置 TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT); TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE); ArrayList<TableFill> tableFills = new ArrayList<>(); tableFills.add(gmtCreate); tableFills.add(gmtModified); strategy.setTableFillList(tableFills); // 乐观锁 strategy.setVersionFieldName("version");
        strategy.setInclude("t_upload_content"); // 设置要映射的表名 strategy.setNaming(NamingStrategy.underline_to_camel); strategy.setColumnNaming(NamingStrategy.underline_to_camel); strategy.setEntityLombokModel(true); // 自动lombok； strategy.setLogicDeleteFieldName("deleted"); // 自动填充配置 TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT); TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE); ArrayList<TableFill> tableFills = new ArrayList<>(); tableFills.add(gmtCreate); tableFills.add(gmtModified); strategy.setTableFillList(tableFills); // 乐观锁 strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix("t_");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setLogicDeleteFieldName("deleted");
//        strategy.setVersionFieldName("version");

//        strategy.setFieldPrefix("t_");
        // localhost:8080/hello_id_2
        mpg.setStrategy(strategy);
        mpg.execute(); //执行
    }*/
}
