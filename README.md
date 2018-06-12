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
