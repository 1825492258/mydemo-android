package item.com.demo.bean;

import java.math.BigDecimal;

/**
 * Created by wuzongjie on 2018/7/10
 */
public class CoinBean {
    /**
     * id : 91
     * memberId : 20
     * coin : {"name":"Bitcoin","nameCn":"比特币","unit":"BTC","status":0,"minTxFee":2.0E-6,"cnyRate":0,"maxTxFee":2.0E-4,"usdRate":0,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":1,"withdrawThreshold":0.01,"minWithdrawAmount":1.0E-4,"maxWithdrawAmount":5,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":null,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0}
     * balance : 1000043.0899906
     * frozenBalance : 0
     * address : 3Jc5aJW8jEWtvXcSbzKQ9NQAWtyADtkZ7G
     * isLock : 0
     */

    private int id;
    private int memberId;
    private CoinBeans coin;
    private double balance;
    private int frozenBalance;
    private String address;
    private int isLock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public CoinBeans getCoin() {
        return coin;
    }

    public void setCoin(CoinBeans coin) {
        this.coin = coin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(int frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    public static class CoinBeans {
        /**
         * name : Bitcoin
         * nameCn : 比特币
         * unit : BTC
         * status : 0
         * minTxFee : 2.0E-6
         * cnyRate : 0
         * maxTxFee : 2.0E-4
         * usdRate : 0
         * enableRpc : 1
         * sort : 1
         * canWithdraw : 1
         * canRecharge : 1
         * canTransfer : 1
         * canAutoWithdraw : 1
         * withdrawThreshold : 0.01
         * minWithdrawAmount : 1.0E-4
         * maxWithdrawAmount : 5
         * isPlatformCoin : 0
         * hasLegal : false
         * allBalance : null
         * coldWalletAddress : null
         * hotAllBalance : null
         * minerFee : 0
         * withdrawScale : 0
         * minRechargeAmount : 0
         */

        private String name;
        private String nameCn;
        private String unit;
        private int status;
        private double minTxFee;
        private int cnyRate;
        private double maxTxFee;
        private int usdRate;
        private int enableRpc;
        private int sort;
        private int canWithdraw;
        private int canRecharge;
        private int canTransfer;
        private int canAutoWithdraw;
        private double withdrawThreshold;
        private double minWithdrawAmount;
        private int maxWithdrawAmount;
        private int isPlatformCoin;
        private boolean hasLegal;
        private Object allBalance;
        private Object coldWalletAddress;
        private Object hotAllBalance;
        private double minerFee;
        private double withdrawScale;
        private int minRechargeAmount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameCn() {
            return nameCn;
        }

        public void setNameCn(String nameCn) {
            this.nameCn = nameCn;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getMinTxFee() {
            return minTxFee;
        }

        public void setMinTxFee(double minTxFee) {
            this.minTxFee = minTxFee;
        }

        public int getCnyRate() {
            return cnyRate;
        }

        public void setCnyRate(int cnyRate) {
            this.cnyRate = cnyRate;
        }

        public double getMaxTxFee() {
            return maxTxFee;
        }

        public void setMaxTxFee(double maxTxFee) {
            this.maxTxFee = maxTxFee;
        }

        public int getUsdRate() {
            return usdRate;
        }

        public void setUsdRate(int usdRate) {
            this.usdRate = usdRate;
        }

        public int getEnableRpc() {
            return enableRpc;
        }

        public void setEnableRpc(int enableRpc) {
            this.enableRpc = enableRpc;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getCanWithdraw() {
            return canWithdraw;
        }

        public void setCanWithdraw(int canWithdraw) {
            this.canWithdraw = canWithdraw;
        }

        public int getCanRecharge() {
            return canRecharge;
        }

        public void setCanRecharge(int canRecharge) {
            this.canRecharge = canRecharge;
        }

        public int getCanTransfer() {
            return canTransfer;
        }

        public void setCanTransfer(int canTransfer) {
            this.canTransfer = canTransfer;
        }

        public int getCanAutoWithdraw() {
            return canAutoWithdraw;
        }

        public void setCanAutoWithdraw(int canAutoWithdraw) {
            this.canAutoWithdraw = canAutoWithdraw;
        }

        public double getWithdrawThreshold() {
            return withdrawThreshold;
        }

        public void setWithdrawThreshold(double withdrawThreshold) {
            this.withdrawThreshold = withdrawThreshold;
        }

        public double getMinWithdrawAmount() {
            return minWithdrawAmount;
        }

        public void setMinWithdrawAmount(double minWithdrawAmount) {
            this.minWithdrawAmount = minWithdrawAmount;
        }

        public int getMaxWithdrawAmount() {
            return maxWithdrawAmount;
        }

        public void setMaxWithdrawAmount(int maxWithdrawAmount) {
            this.maxWithdrawAmount = maxWithdrawAmount;
        }

        public int getIsPlatformCoin() {
            return isPlatformCoin;
        }

        public void setIsPlatformCoin(int isPlatformCoin) {
            this.isPlatformCoin = isPlatformCoin;
        }

        public boolean isHasLegal() {
            return hasLegal;
        }

        public void setHasLegal(boolean hasLegal) {
            this.hasLegal = hasLegal;
        }

        public Object getAllBalance() {
            return allBalance;
        }

        public void setAllBalance(Object allBalance) {
            this.allBalance = allBalance;
        }

        public Object getColdWalletAddress() {
            return coldWalletAddress;
        }

        public void setColdWalletAddress(Object coldWalletAddress) {
            this.coldWalletAddress = coldWalletAddress;
        }

        public Object getHotAllBalance() {
            return hotAllBalance;
        }

        public void setHotAllBalance(Object hotAllBalance) {
            this.hotAllBalance = hotAllBalance;
        }

        public double getMinerFee() {
            return minerFee;
        }

        public void setMinerFee(double minerFee) {
            this.minerFee = minerFee;
        }

        public double getWithdrawScale() {
            return withdrawScale;
        }

        public void setWithdrawScale(double withdrawScale) {
            this.withdrawScale = withdrawScale;
        }

        public int getMinRechargeAmount() {
            return minRechargeAmount;
        }

        public void setMinRechargeAmount(int minRechargeAmount) {
            this.minRechargeAmount = minRechargeAmount;
        }
    }

}
