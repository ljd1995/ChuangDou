package cn.luojiandong.douban.bean;

import cn.bmob.v3.BmobObject;

/**
 * 项目名：Douban
 * 包名：cn.luojiandong.douban.bean
 * 文件名: FeedbackBean
 * 创建者: JackLuo
 * 创建时间: 2016-10-28 10:53
 * 描述：
 */
public class FeedbackBean extends BmobObject {
    //意见反馈类型
    private String feedbackType;
    //意见反馈内容
    private String feedbackInfo;
    //意见反馈电话或邮箱
    private String feedbackCall;

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackInfo() {
        return feedbackInfo;
    }

    public void setFeedbackInfo(String feedbackInfo) {
        this.feedbackInfo = feedbackInfo;
    }

    public String getFeedbackCall() {
        return feedbackCall;
    }

    public void setFeedbackCall(String feedbackCall) {
        this.feedbackCall = feedbackCall;
    }
}
