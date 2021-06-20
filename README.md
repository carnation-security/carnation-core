# Carnation-Core

> 康乃馨（轻量级SpringMVC权限框架）核心框架， 提供Carnation的整个认证逻辑

## 认证流程

> 框架主要由**认证器**和**授权器**组成。认证器的主要工作是对用户进行登录认证，由于不同系统的认证逻辑各不相同，所以具体的认证逻辑由开发人员自己完成，在开发人员完成验证用户合法性之后，向认证器提供认证成功后的用户信息（`UserInfo`对象），认证器会自动完成后续认证，最后返回一个`Token`对象（包含`token`和`refreshToken`）；之后访问需要认证的接口时， 授权器会自动校验请求是否携带token以及token的合法性，再通过AOP来验证用户是否具有权限

## 配置组件

> 自动配置: [Carnation-Spring-Boot-Autoconfigure](https://github.com/VioletFreesia/carnation-spring-boot-autoconfigure)
>
> SpringBoot Starter: [Carnation-Spring-Boot-Starter](https://github.com/VioletFreesia/carnation-spring-boot-starter)
>
> 使用案例：[Carnation-Spring-Boot-Starter-Test](https://github.com/VioletFreesia/carnation-spring-boot-starter-test)

