# 历史上的今天

## 网站简介

[Demo:历史上的今天](http://huoche123.top/today/)


展示历史上在当天发生的大事件，以及该日出生及逝世的名人信息。默认展示当天的信息，也可从右侧选择其他日期。

点击类别，可显示或隐藏该类下的条目。

![网站截图](http://odszv0fof.bkt.clouddn.com/%E5%8E%86%E5%8F%B2%E4%B8%8A%E7%9A%84%E4%BB%8A%E5%A4%A9.png)


## 网站架构
tomcat +  servlet + jsp


## 数据来源
数据来源于维基百科历史上的今天页面：例：[1月1日](https://zh.wikipedia.org/zh-cn/1月1日)


爬虫项目：[TodayInHistory-Crawler](https://github.com/libowei1213/TodayInHistory-Crawler)

## API
对外提供api接口，接口格式：`http://网址/today/api?date=xx月xx日`

返回json格式的数据：

```json
{
  "list": [
    {
      "date": "2月29日",
      "message": "哥伦布声称当日的月全食是神愤怒造成，迫使牙买加土著居民继续为他和船员供应补给。",
      "type": "0",
      "year": "1504年"
    },
    {
      "date": "2月29日",
      "message": "保禄三世，教宗（逝于1549年）",
      "type": "1",
      "year": "1468年"
    }
  ],
  "message": "调用成功",
  "status": true
}
```

接口调用成`status`为`true`, `list`为历史上该日的大事件、出生和逝世的数据

```
{
    "date": "2月29日",      //日期
    "message": "保禄三世，教宗（逝于1549年）",      //内容
    "type": "1",        //类型，0为大事件, 1为出生, 2为逝世
    "year": "1468年"    //年份
}
```
