package item.com.demo.bean;

import java.util.List;

/**
 * Created by wuzongjie on 2018/7/12
 */
public class EvaluationBean {

    private String userName;
    private List<ReplyBean> replyBeans;
    private List<String> imageInfo;

    public List<String> getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(List<String> imageInfo) {
        this.imageInfo = imageInfo;
    }

    public EvaluationBean(String userName) {
        this.userName = userName;
    }

    public EvaluationBean(String userName, List<ReplyBean> replyBeans) {
        this.userName = userName;
        this.replyBeans = replyBeans;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ReplyBean> getReplyBeans() {
        return replyBeans;
    }

    public void setReplyBeans(List<ReplyBean> replyBeans) {
        this.replyBeans = replyBeans;
    }

    public static class ReplyBean {

        private int erid;             //回复id
        private String erContent;    //回复内容
        private String erReplyuser;  //回复人姓名
        private String erReplytime;  //回复时间

        public ReplyBean(int erid, String erContent, String erReplyuser) {
            this.erid = erid;
            this.erContent = erContent;
            this.erReplyuser = erReplyuser;
        }

        public int getErid() {
            return erid;
        }

        public void setErid(int erid) {
            this.erid = erid;
        }

        public String getErContent() {
            return erContent;
        }

        public void setErContent(String erContent) {
            this.erContent = erContent;
        }

        public String getErReplyuser() {
            return erReplyuser;
        }

        public void setErReplyuser(String erReplyuser) {
            this.erReplyuser = erReplyuser;
        }

        public String getErReplytime() {
            return erReplytime;
        }

        public void setErReplytime(String erReplytime) {
            this.erReplytime = erReplytime;
        }
    }
}
