package com.yzl.pojo;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-03-29
 */
public class YzlQualitypromotion {
    private Long id;

    /**
     * 地区编号
     */
    private String dcode;

    /**
     * 工程编号
     */
    private String ecode;

    /**
     * 合计/设计面积
     */
    private String designarea;

    /**
     * 合计/自检合格面积
     */
    private String iqr;

    /**
     * 合计/核实面积记
     */
    private String verifyareat;

    /**
     * 合计/核实面积核实率
     */
    private String verifyarea;

    /**
     * 合计/核实合格面积记
     */
    private String vqat;

    /**
     * 合计/核实合格面积合格率
     */
    private String vqaqr;

    /**
     * 合计/抚育面积记
     */
    private String nurtureareat;

    /**
     * 合计/抚育面积抚育率
     */
    private String nanr;

    /**
     * 合计/管护面积记
     */
    private String manageareat;

    /**
     * 合计/管护面积管护率
     */
    private String manageareamr;

    /**
     * 桉树设计面积
     */
    private String eudesignarea;

    /**
     * 桉树自检合格面积
     */
    private String euiqr;

    /**
     * 桉树核实面积
     */
    private String euverifyarea;

    /**
     * 桉树核实合格面积
     */
    private String euvqa;

    /**
     * 桉树抚育面积
     */
    private String eunurturearea;

    /**
     * 桉树管护面积
     */
    private String eumanagearea;

    /**
     * 桉树核实面积合格率
     */
    private String euvrqr;

    /**
     * 桉树抚育率
     */
    private String eunurture;

    /**
     * 桉树管护率
     */
    private String eumanage;

    /**
     * 其他设计面积
     */
    private String otdesignarea;

    /**
     * 其他自检合格面积
     */
    private String otiqr;

    /**
     * 其他核实面积
     */
    private String otverifyarea;

    /**
     * 其他核实合格面积
     */
    private String otvqa;

    /**
     * 其他抚育面积
     */
    private String otnurturearea;

    /**
     * 其他管护面积
     */
    private String otmanagearea;

    /**
     * 其他核实面积合格率
     */
    private String otvrqr;

    /**
     * 其他抚育率
     */
    private String othnurture;

    /**
     * 其他管护率
     */
    private String otmanage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode == null ? null : dcode.trim();
    }

    public String getEcode() {
        return ecode;
    }

    public void setEcode(String ecode) {
        this.ecode = ecode == null ? null : ecode.trim();
    }

    public String getDesignarea() {
        return designarea;
    }

    public void setDesignarea(String designarea) {
        this.designarea = designarea == null ? null : designarea.trim();
    }

    public String getIqr() {
        return iqr;
    }

    public void setIqr(String iqr) {
        this.iqr = iqr == null ? null : iqr.trim();
    }

    public String getVerifyareat() {
        return verifyareat;
    }

    public void setVerifyareat(String verifyareat) {
        this.verifyareat = verifyareat == null ? null : verifyareat.trim();
    }

    public String getVerifyarea() {
        return verifyarea;
    }

    public void setVerifyarea(String verifyarea) {
        this.verifyarea = verifyarea == null ? null : verifyarea.trim();
    }

    public String getVqat() {
        return vqat;
    }

    public void setVqat(String vqat) {
        this.vqat = vqat == null ? null : vqat.trim();
    }

    public String getVqaqr() {
        return vqaqr;
    }

    public void setVqaqr(String vqaqr) {
        this.vqaqr = vqaqr == null ? null : vqaqr.trim();
    }

    public String getNurtureareat() {
        return nurtureareat;
    }

    public void setNurtureareat(String nurtureareat) {
        this.nurtureareat = nurtureareat == null ? null : nurtureareat.trim();
    }

    public String getNanr() {
        return nanr;
    }

    public void setNanr(String nanr) {
        this.nanr = nanr == null ? null : nanr.trim();
    }

    public String getManageareat() {
        return manageareat;
    }

    public void setManageareat(String manageareat) {
        this.manageareat = manageareat == null ? null : manageareat.trim();
    }

    public String getManageareamr() {
        return manageareamr;
    }

    public void setManageareamr(String manageareamr) {
        this.manageareamr = manageareamr == null ? null : manageareamr.trim();
    }

    public String getEudesignarea() {
        return eudesignarea;
    }

    public void setEudesignarea(String eudesignarea) {
        this.eudesignarea = eudesignarea == null ? null : eudesignarea.trim();
    }

    public String getEuiqr() {
        return euiqr;
    }

    public void setEuiqr(String euiqr) {
        this.euiqr = euiqr == null ? null : euiqr.trim();
    }

    public String getEuverifyarea() {
        return euverifyarea;
    }

    public void setEuverifyarea(String euverifyarea) {
        this.euverifyarea = euverifyarea == null ? null : euverifyarea.trim();
    }

    public String getEuvqa() {
        return euvqa;
    }

    public void setEuvqa(String euvqa) {
        this.euvqa = euvqa == null ? null : euvqa.trim();
    }

    public String getEunurturearea() {
        return eunurturearea;
    }

    public void setEunurturearea(String eunurturearea) {
        this.eunurturearea = eunurturearea == null ? null : eunurturearea.trim();
    }

    public String getEumanagearea() {
        return eumanagearea;
    }

    public void setEumanagearea(String eumanagearea) {
        this.eumanagearea = eumanagearea == null ? null : eumanagearea.trim();
    }

    public String getEuvrqr() {
        return euvrqr;
    }

    public void setEuvrqr(String euvrqr) {
        this.euvrqr = euvrqr == null ? null : euvrqr.trim();
    }

    public String getEunurture() {
        return eunurture;
    }

    public void setEunurture(String eunurture) {
        this.eunurture = eunurture == null ? null : eunurture.trim();
    }

    public String getEumanage() {
        return eumanage;
    }

    public void setEumanage(String eumanage) {
        this.eumanage = eumanage == null ? null : eumanage.trim();
    }

    public String getOtdesignarea() {
        return otdesignarea;
    }

    public void setOtdesignarea(String otdesignarea) {
        this.otdesignarea = otdesignarea == null ? null : otdesignarea.trim();
    }

    public String getOtiqr() {
        return otiqr;
    }

    public void setOtiqr(String otiqr) {
        this.otiqr = otiqr == null ? null : otiqr.trim();
    }

    public String getOtverifyarea() {
        return otverifyarea;
    }

    public void setOtverifyarea(String otverifyarea) {
        this.otverifyarea = otverifyarea == null ? null : otverifyarea.trim();
    }

    public String getOtvqa() {
        return otvqa;
    }

    public void setOtvqa(String otvqa) {
        this.otvqa = otvqa == null ? null : otvqa.trim();
    }

    public String getOtnurturearea() {
        return otnurturearea;
    }

    public void setOtnurturearea(String otnurturearea) {
        this.otnurturearea = otnurturearea == null ? null : otnurturearea.trim();
    }

    public String getOtmanagearea() {
        return otmanagearea;
    }

    public void setOtmanagearea(String otmanagearea) {
        this.otmanagearea = otmanagearea == null ? null : otmanagearea.trim();
    }

    public String getOtvrqr() {
        return otvrqr;
    }

    public void setOtvrqr(String otvrqr) {
        this.otvrqr = otvrqr == null ? null : otvrqr.trim();
    }

    public String getOthnurture() {
        return othnurture;
    }

    public void setOthnurture(String othnurture) {
        this.othnurture = othnurture == null ? null : othnurture.trim();
    }

    public String getOtmanage() {
        return otmanage;
    }

    public void setOtmanage(String otmanage) {
        this.otmanage = otmanage == null ? null : otmanage.trim();
    }
}