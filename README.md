# 网络框架Retrofit2.0+数据库框架 - LitePal

Retrofit2.0
网络框架参考https://www.jianshu.com/p/a94e38636fde
LitePal
数据库框架参考
https://www.jianshu.com/p/a94e38636fde

DataBinding
https://www.jianshu.com/p/ba4982be30f8

封装事件处理
https://blog.csdn.net/bskfnvjtlyzmv867/article/details/71480647

在需要接收事件的fragment订阅事件关闭fragment取消订阅

    @Override
    protected void setupViews() {
        EventBusUtil.registerEvent(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregisterEvent(this);
    }
    定义一个事件的对象
    public class MessageEvent {
          private String message;

          public MessageEvent(String message) {
              this.message = message;
          }

          public String getMessage() {
              return message;
         }

         @Override
         public String toString() {
             return message;
         }
     }

    接收事件
     public void onEvent(MessageEvent messageEvent) {
        这里接收这个事件
        }


     发送事件
      根据需要决定是否传递参数
      EventBusUtil.post(new MessageEvent());
      
      
      
      
      
      
 JSON测试数据格式
 
 
       {
	"message": "ok",
	"nu": "500379523313",
	"ischeck": "1",
	"condition": "F00",
	"com": "yuantong",
	"status": "200",
	"state": "3",
	"data": [{
		"time": "2016-08-30 13:35:12",
		"ftime": "2016-08-30 13:35:12",
		"context": "客户 签收人 :邮件收发章 已签收 感谢使用圆通速递，期待再次为您服务",
		"location": ""
	}, {
		"time": "2016-08-30 08:39:30",
		"ftime": "2016-08-30 08:39:30",
		"context": "【广东省广州市越秀区北京路公司】 派件人 :蔡少林 派件中 派件员电话13751896548",
		"location": ""
	}, {
		"time": "2016-08-29 23:48:15",
		"ftime": "2016-08-29 23:48:15",
		"context": "【广州转运中心】 已发出 下一站 【广东省广州市越秀区北京路公司】",
		"location": ""
	}, {
		"time": "2016-08-29 23:00:07",
		"ftime": "2016-08-29 23:00:07",
		"context": "【广州转运中心】 已收入",
		"location": ""
	}, {
		"time": "2016-08-28 01:46:48",
		"ftime": "2016-08-28 01:46:48",
		"context": "【沈阳转运中心】 已发出 下一站 【广州转运中心】",
		"location": ""
	}, {
		"time": "2016-08-27 22:38:08",
		"ftime": "2016-08-27 22:38:08",
		"context": "【沈阳转运中心】 已收入",
		"location": ""
	}, {
		"time": "2016-08-27 17:03:28",
		"ftime": "2016-08-27 17:03:28",
		"context": "【辽宁省丹东市公司】 已发出 下一站 【沈阳转运中心】",
		"location": ""
	}, {
		"time": "2016-08-27 16:45:17",
		"ftime": "2016-08-27 16:45:17",
		"context": "【辽宁省丹东市第一分部公司】 已收件",
		"location": ""
	}, {
		"time": "2016-08-27 16:31:50",
		"ftime": "2016-08-27 16:31:50",
		"context": "【辽宁省丹东市第一分部公司】 已收件",
		"location": ""
	}]
}
