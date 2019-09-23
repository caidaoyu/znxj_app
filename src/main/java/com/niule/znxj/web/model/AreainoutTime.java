package com.niule.znxj.web.model;

import java.util.List;

public class AreainoutTime {

    /**
     * deviceTime : [{"deviceId":"366","deviceName":"机泵P1401","deviceTime":[{"endTime":"1531719282140","startTime":"1531719241315"}],"districtId":"182","districtName":"报表测试区域","nfcUse":0,"userId":195},{"deviceId":"367","deviceName":"UPS","deviceTime":[{"endTime":"1531719317299","startTime":"1531719283332"}],"districtId":"182","districtName":"报表测试区域","nfcUse":0,"userId":195},{"deviceId":"368","deviceName":"机房","deviceTime":[{"endTime":"1531719368380","startTime":"1531719318424"}],"districtId":"182","districtName":"报表测试区域","nfcUse":0,"userId":195}]
     * districtId : 182
     * districtName : 报表测试区域
     * districtTime : [{"endTime":"1531719369778","startTime":"1531719240230"}]
     * nfcUse : 0
     * userId : 195
     */

    private String districtId;
    private String districtName;
    private int nfcUse;
    private int userId;
    private List<DeviceTimeBean> deviceTime;
    private List<DistrictTimeBean> districtTime;

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getNfcUse() {
        return nfcUse;
    }

    public void setNfcUse(int nfcUse) {
        this.nfcUse = nfcUse;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<DeviceTimeBean> getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(List<DeviceTimeBean> deviceTime) {
        this.deviceTime = deviceTime;
    }

    public List<DistrictTimeBean> getDistrictTime() {
        return districtTime;
    }

    public void setDistrictTime(List<DistrictTimeBean> districtTime) {
        this.districtTime = districtTime;
    }

    public static class DeviceTimeBean {
        /**
         * deviceId : 366
         * deviceName : 机泵P1401
         * deviceTime : [{"endTime":"1531719282140","startTime":"1531719241315"}]
         * districtId : 182
         * districtName : 报表测试区域
         * nfcUse : 0
         * userId : 195
         */

        private String deviceId;
        private String deviceName;
        private String districtId;
        private String districtName;
        private int nfcUse;
        private int userId;
        private List<DeviceTime> deviceTime;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public int getNfcUse() {
            return nfcUse;
        }

        public void setNfcUse(int nfcUse) {
            this.nfcUse = nfcUse;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<DeviceTime> getDeviceTime() {
            return deviceTime;
        }

        public void setDeviceTime(List<DeviceTime> deviceTime) {
            this.deviceTime = deviceTime;
        }

        public static class DeviceTime {
            /**
             * endTime : 1531719282140
             * startTime : 1531719241315
             */

            private String endTime;
            private String startTime;

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }
        }
    }

    public static class DistrictTimeBean {
        /**
         * endTime : 1531719369778
         * startTime : 1531719240230
         */

        private String endTime;
        private String startTime;

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }
    }
}
