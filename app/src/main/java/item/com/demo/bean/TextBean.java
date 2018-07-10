package item.com.demo.bean;

/**
 * Created by wuzongjie on 2018/7/6
 */
public class TextBean {
    /**
     * serialNumber : 9FC4D311A324478F867C222102852C8B
     * name : app1
     * sysAdvertiseLocation : 0
     * startTime : 2018-06-01 00:00:00
     * endTime : 2019-06-30 00:00:00
     * url : https://xinhuo-xindai.oss-cn-hangzhou.aliyuncs.com/2018/06/29/12623f2a-38d9-4118-b7ef-2eddc5a64c9f.jpg
     * linkUrl : http://focctest.io/#/login
     * remark :
     * status : 0
     * createTime : 2018-06-29 05:51:44
     * content : null
     * author : null
     * sort : 0
     */

    private String serialNumber;
    private String name;
    private String startTime;
    private String endTime;
    private String url;
    private String linkUrl;
    private String createTime;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
