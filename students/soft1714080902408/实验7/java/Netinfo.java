package com.hzuandroid.netconncheck;

public class Netinfo {
    enum NetMessage{
        MESSAGE("网络断开连接",1);
        NetMessage(String msg, int index){
            this.msg = msg;
            this.index = index;
        }
        private String msg;
        private int index;
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
    enum NetType{
        TYPE_NAME("手机网络",1);
        NetType(String type_name, int index){
            this.type_name = type_name;
            this.index = index;
        }
        private String type_name;
        private int index;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    };
    enum Net_state{
        ISCONNECT("is_connect",true),
        ISCANUSER("is_canuser", true);
        private String state_bame;
        private boolean isCanUse;
        Net_state(String state_bame, boolean isCanUse){
            this.state_bame = state_bame;
            this.isCanUse = isCanUse;
        }
        public String getState_bame() {
            return state_bame;
        }
        public void setState_bame(String state_bame) {
            this.state_bame = state_bame;
        }
        public boolean isCanUse() {
            return isCanUse;
        }
        public void setCanUse(boolean canUse) {
            isCanUse = canUse;
        }
    }
}
