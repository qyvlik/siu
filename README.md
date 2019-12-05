# short url

## api

### create

```bash
curl http://localhost:8222/api/v1/short-url/create?originUrl=https://github.com/qyvlik/siu
```

```json
{
  "result": "abcd"
}
```

### location

```bash
curl -I http://localhost:8222/abcd
```

```text
HTTP/1.1 302 
Location: https://github.com/qyvlik/siu
Content-Length: 0
Date: Thu, 05 Dec 2019 08:26:38 GMT
```

## ref

[chenlongqiang/easy-short-url/esu.sql](https://github.com/chenlongqiang/easy-short-url/blob/master/esu.sql)

[yourls.org](http://yourls.org/)

[短网址(short URL)系统的原理及其实现](https://hufangyun.com/2017/short-url/)

[短 URL 系统是怎么设计的](https://www.zhihu.com/question/29270034)

[Grafana的Worldmap插件使用方法](https://blog.csdn.net/Py_Wang/article/details/79186634)

[浏览器Http请求头部比较](https://blog.csdn.net/kjb000/article/details/7774588)

[用浏览器访问网址时，请求头(request header)是根据什么生成的?](https://www.zhihu.com/question/34603729)

[How can I use environment variables in Nginx.conf](https://serverfault.com/questions/577370/how-can-i-use-environment-variables-in-nginx-conf)
