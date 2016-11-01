package cm.inv.com.crawler.common.utils;

import cm.inv.com.crawler.common.config.Global;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
* 此类描述的是：业主app极光推送
* @author:zhangtao107@126.com
* @version: 2016-5-5 上午11:25:02 
*/
	
public class JPushUtils    {
	private static Log log = LogFactory.getLog(JPushUtils.class);
	public static String master = "d746d229958bb5735b7eeade";
	public static String appkey = "049107fec6033d132f23d656";
	public static boolean apnsProduction = true;// 仅对ios有用。生产环境：true；开发环境：false；
	private static PushClient jpushClient = new PushClient(master, appkey);
	private static PushClient getJPushClient() {
		return jpushClient;
	}

	/**
	 * 极光推送
	 * 
	 * @param context
	 *            推送内容，不允许为空，最大长度100。
	 * @param mobile
	 *            推送用户的mobile，不允许为null。
	 */
	public static void pushMsg(final String context, final String... mobile) {
		  Thread t=new Thread(new Runnable(){
		  public void run(){
			  try{
				  String sendContext=new String(context);
				  try {
						if (StringUtils.isEmpty(sendContext) || StringUtils.isEmpty(mobile)) {
							return;
						}
						if (sendContext.length() > 100) {
							sendContext = sendContext.substring(0, 100);
						}
						 
						sendContext+="【"+ Global.getConfig("productName")+"】";
						log.info("context.length()="+context.length()+"|sendContext.length()="+sendContext.length());
						PushResult rlt = sendNotificationWithAlias(sendContext, mobile);
						log.info("极光推送结果成功：" + rlt.toString());
					} catch (Exception e) {
						log.info("极光推送结果失败：" + sendContext);
						e.printStackTrace();
					}
			  }catch (Exception e) {
			   e.printStackTrace();
			  }
		  }
	  });
	  t.start();
	}

	private static PushResult sendNotificationWithAlias(String alert, String... alias) throws Exception {
		Options o = Options.sendno();
		o.setApnsProduction(apnsProduction);
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias)).setNotification(Notification.alert(alert)).setOptions(o).build();
		log.info("极光推送内容：" + payload.toJSON());
		return getJPushClient().sendPush(payload);
	}

	public static void main(String[] args) throws Exception {
		JPushUtils.pushMsg("尊敬的xx小区业主，您好！您发起的故障报修已安排社区专属管家进行处理，请您安心等待。有问题可致电4008115251垂询，我们将竭诚为您服务", "18210225831");
	}
	
	
	public static void pushMsg(PushPayload payload) {
        try {
            PushResult result = jpushClient.sendPush(payload);
            log.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
        	log.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
        	log.error("Should review the error, and fix the request", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }
	}
	
    /**
    * 此方法描述的是：快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
    * @author:zhangtao107@126.com
    * @return PushPayload
    */
    public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll(PlatformNotification.ALERT);
    }
    /**
    * 此方法描述的是：构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT。
    * @author:zhangtao107@126.com
    * @return PushPayload
    */
    public static PushPayload buildPushObject_all_alias_alert(String alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(PlatformNotification.ALERT))
                .build();
    }

    /**
    * 此方法描述的是：构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
    * @author:zhangtao107@126.com
    * @return PushPayload
    */
    public static PushPayload buildPushObject_android_tag_alertWithTitle(String tag,String title) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.android(PlatformNotification.ALERT, title, null))
                .build();
    }

    /**
    * 此方法描述的是：构建推送对象：平台是 iOS，推送目标是 "tag1", "tag_all" 的交集，推送内容同时包括通知与消息 - 通知信息是 ALERT，角标数字为 5，通知声音为 "happy"，并且附加字段 from = "JPush"；消息内容是 MSG_CONTENT。通知是 APNs 推送通道的，消息是 JPush 应用内消息通道的。APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
    * @author:zhangtao107@126.com
    * @return PushPayload
    */
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(String sound,String from,String msgContent,String...tags) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and(tags))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(PlatformNotification.ALERT)
                                .setBadge(5)
                                .setSound(sound)
                                .addExtra("from", from)
                                .build())
                        .build())
                 .setMessage(Message.content(msgContent))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }

    /**
    * 此方法描述的是：构建推送对象：平台是 Andorid 与 iOS，推送目标是 （"tag1" 与 "tag2" 的并集）且（"alias1" 与 "alias2" 的并集），推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
    * @author:zhangtao107@126.com
    * @return PushPayload
    */
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent("MSG_CONTENT")
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }


}
