# 移动应用开发实验库（2019）   
实验要求：https://github.com/hzuapps/android-labs-2019/issues?q=is%3Aissue+is%3Aopen+label%3ALab

## Android中文教材
http://hukai.me/android-training-course-in-chinese/index.html  
https://www.gitbook.com/book/mobile100/android/details  

## Android英文教程
http://www.tutorialspoint.com/index.htm

## Android开发工具下载网址
https://developer.android.google.cn  
http://www.androiddevtools.cn/ 

## Android Studio汉化（某同学亲测成功）
http://jingyan.baidu.com/article/ff42efa93177c0c19e2202c3.html

## 配置代理的新方法

https://blog.vvzero.com/2018/04/08/set-proxy-for-newest-android-studio/  
https://blog.csdn.net/weixin_40849588/article/details/86559842  
http://www.voidcn.com/article/p-alndgxtc-bpx.html   

## Gradle 报错解决方法

Error：Could not Get 'https://dl.google.com/dl/android/maven2/com/android/tools/build/gradle/3.1.3/gradle-3.1.3.pom'. Received status code 400 from server: Bad Request
Enable Gradle 'offline mode' and sync project  

方法1：在 gradle.properties 里面添加代理
```  
systemProp.https.proxyPort=80
systemProp.http.proxyHost=mirrors.opencas.org
```  
https://blog.csdn.net/u010134683/article/details/82740268

方法2：file  ->> setting  ->> build，excution，->>gradle   ->>  android studio ->>  勾选Enable。。。  

https://blog.csdn.net/qq_41550842/article/details/81939479

## Android版本6.0安装提示
在Android SDK Manager中，参考下面的截图配置和下载6.0版本的库。
![在Android SDK Manager中配置教育网下载代理](https://raw.githubusercontent.com/hzuapps/android-labs/master/screens/sdk-manager-proxy-edu.cn.png "配置教育网下载代理")
![在Android SDK Manager中选择6.0库](https://raw.githubusercontent.com/hzuapps/android-labs/master/screens/android-6-sdk.png "配置教育网下载代理")
![在Android SDK Manager中下载6.0库](https://raw.githubusercontent.com/hzuapps/android-labs/master/screens/android-6-sdk-download.png "配置教育网下载代理")

## Android应用架构
https://github.com/googlesamples/android-architecture 

--

![image](https://cloud.githubusercontent.com/assets/627946/23102172/3613a9c6-f6df-11e6-8d0b-8942995d3d66.png)

Since Feb. 19, 2017.

