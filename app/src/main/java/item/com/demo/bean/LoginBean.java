package item.com.demo.bean;

/**
 * Created by wuzongjie on 2018/7/10
 */
public class LoginBean {

    /**
     * username : 55555
     * memberLevel : 0
     * token : 3377c624-d53f-4104-bb09-0e2293957b29
     * realName : null
     * avatar : http://bite321.oss-cn-hongkong.aliyuncs.com/2018/05/18/54796ee6-7628-4288-849e-86f1a754321c.jpg
     * promotionCode : U000020i2
     * id : 20
     * promotionPrefix : www.test.com
     * signInAbility : true
     * signInActivity : false
     */

    private String username;
    private int memberLevel;
    private String token;
    private Object realName;
    private String avatar;
    private String promotionCode;
    private int id;
    private String promotionPrefix;
    private boolean signInAbility;
    private boolean signInActivity;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(int memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getRealName() {
        return realName;
    }

    public void setRealName(Object realName) {
        this.realName = realName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromotionPrefix() {
        return promotionPrefix;
    }

    public void setPromotionPrefix(String promotionPrefix) {
        this.promotionPrefix = promotionPrefix;
    }

    public boolean isSignInAbility() {
        return signInAbility;
    }

    public void setSignInAbility(boolean signInAbility) {
        this.signInAbility = signInAbility;
    }

    public boolean isSignInActivity() {
        return signInActivity;
    }

    public void setSignInActivity(boolean signInActivity) {
        this.signInActivity = signInActivity;
    }
}
