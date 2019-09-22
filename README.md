# official-website
基于 [febs-shiro](https://github.com/wuyouzhuguli/FEBS-Shiro) 魔改的项目。

# 网页端
默认访问网页端
## 错误页面
cc.mrbird.febs.system.controller.SystemViewController 中 handleError 方法中配置的

# 管理端
## 默认访问管理端前缀为 /admin ，如果要修改，需要改动
cc.mrbird.febs.common.entity.FebsConstant
```
public static final String ADMIN_MAPPING_PREFIX = "admin/";
```
febs.properties
```
febs.shiro.anon_url=/admin/images/captcha,/admin/login
febs.shiro.authc_url=/admin/**
```
## 代码生成
cc.mrbird.febs.generator.entity.GeneratorConstant