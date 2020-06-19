package com.dg.main.vo;




public class TransToVo {
    public static class PayeeInfo{
        private String identity;
        private String identity_type="ALIPAY_LOGON_ID";
        private String name;

        public PayeeInfo(String identity, String name) {
            this.identity = identity;

            this.name = name;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getIdentity_type() {
            return identity_type;
        }

        public void setIdentity_type(String identity_type) {
            this.identity_type = identity_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    private String out_biz_no="";
    private String product_code="TRANS_ACCOUNT_NO_PWD";
    private String biz_scene="DIRECT_TRANSFER";
    private String trans_amount="";
    private PayeeInfo payee_info;
    private String order_title="转账";

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    private String remark="转账备注";

    public String getOut_biz_no() {
        return out_biz_no;
    }

    public void setOut_biz_no(String out_biz_no) {
        this.out_biz_no = out_biz_no;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getBiz_scene() {
        return biz_scene;
    }

    public void setBiz_scene(String biz_scene) {
        this.biz_scene = biz_scene;
    }

    public String getTrans_amount() {
        return trans_amount;
    }

    public void setTrans_amount(String trans_amount) {
        this.trans_amount = trans_amount;
    }

    public PayeeInfo getPayee_info() {
        return payee_info;
    }

    public void setPayee_info(PayeeInfo payee_info) {
        this.payee_info = payee_info;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
