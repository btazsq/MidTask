# 期中考核总结

我觉得我多半是失败了，啥都没做出来

连apk都打包不出来，报错是Cause: unable to find valid certification path to requested target

去网上查解决办法，不管用；不知道怎么的就有这个问题，明明以前就能打包，偏偏今天不行了，以前能打包的工程今天也不行了。

![](https://github.com/btazsq/MidTask/blob/master/picture/build_failed.png)

## 关于实现的功能

少得可怜。。

### 登录页面

![](https://github.com/btazsq/MidTask/tree/master/picture/start.gif)

整个App只有两个活动，其它全是Fragment

这个首页我不知道怎么回事，图片占的位置很长，我解决不了

网络请求工具类也暴露出来了许多问题，实在是改不动了

### 歌单以及播放

花了我太长太长时间，还没有搞好的一个部分

我实在不敢去动它了，越改越有问题

![](https://github.com/btazsq/MidTask/tree/master/picture/into.gif)

这个歌单，有些时候一首歌都拿不到

![](https://github.com/btazsq/MidTask/tree/master/picture/null_tracks.png)

就算拿到了歌，也不一定能拿到资源，问题太多了

![](https://github.com/btazsq/MidTask/tree/master/picture/music.gif)

用进度条控制歌曲播放总算是做到了

这个UI主要是这个界面一共就没有进来过几次，就没有继续写，因为无法测试（

### 个人页面

好了，这个页面没用网络请求，那个东西问题太多了，不敢用了

![](https://github.com/btazsq/MidTask/tree/master/picture/layout_failed.png)

![](https://github.com/btazsq/MidTask/tree/master/picture/layout_bad.png)

### 然后就没有了

那个歌单实在是太不稳定了，让我根本无法放心去做个人歌单界面

再加上时间不多了，放弃了，先打包apk

然后如上所说，apk打包不了了？？？真是在戏弄我阿
