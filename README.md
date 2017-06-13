
## 简介
这是一个使用 SSM 实现的多用户博客系统，预览地址[广场](http://35.185.178.144:8080/square)，测试帐号 hgrx，密码 helloworld
* 实现了标签 / 归档 / 发布 / 修改文章 等基础功能
* 实现了多用户系统，关注他人，收藏文章，阅读量统计，站内信等功能
* 使用前缀树实现了敏感词过滤功能
* 在不影响原有系统设计的情况下实现了一个简单的缓存系统，定义了若干缓存更新策略，将高频Api速度提升了两个数量级以上并极大的减轻了数据库压力
* 使用 Redis 实现了一个简单的异步消息队列
* 开发中涉及到的技术： SSM、MySQL、Git、Maven、JQuery、Pure、Linux 和其他众多开源库
