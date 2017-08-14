package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SlHouseAccount;

/**
 * Created by yang_chunyan on 2016/8/1.
 */
public class BS2102105Result extends SlHouseAccount{
        private String slShowName;
        private String slCodeDis;
        private String slContact;
        private String checkFlag;
        private String sex;

        public String getCheckFlag() {
            return checkFlag;
        }

        public void setCheckFlag(String checkFlag) {
            this.checkFlag = checkFlag;
        }

        public String getSlShowName() {
        return slShowName;
    }

        public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

        public String getSlCodeDis() {
        return slCodeDis;
    }

        public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

        public String getSlContact() {
        return slContact;
    }

        public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
}
