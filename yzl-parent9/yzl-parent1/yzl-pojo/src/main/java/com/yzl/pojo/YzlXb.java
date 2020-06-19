package com.yzl.pojo;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-12-19
 */
/**
 * @author administrator_
 *
 */
public class YzlXb {
    /**
     * id
     */
    private Integer id;

    /**
     * 标识码
     */
    private String bsm;

    /**
     * 市
     */
    private String city;

    /**
     * 县
     */
    private String county;

    /**
     * 乡
     */
    private String town;

    /**
     * 村
     */
    private String village;

    /**
     * 林班
     */
    private String linBan;

    /**
     * 经营班
     */
    private String jyingBan;

    /**
     * 小班
     */
    private String xiaoBan;

    /**
     * 作业小班
     */
    private String zyXb;

    /**
     * 样地号
     */
    private String ydh;

    /**
     * 图幅号
     */
    private String tfh;

    /**
     * 工程类别
     */
    private String gclb;

    /**
     * 造林类别
     */
    private String zllb;

    /**
     * 计划年度
     */
    private String jhnd;

    /**
     * 作业年度
     */
    private String zynd;

    /**
     * 造林（封育）前地类
     */
    private String zlqDilei;

    /**
     * 地类
     */
    private String dilei;

    /**
     * 封育类型
     */
    private String fylx;

    /**
     * 封育方式
     */
    private String fyfs;

    /**
     * 林地权属
     */
    private String ldqs;

    /**
     * 林木权属
     */
    private String lmqs;

    /**
     * 林种
     */
    private String lz;

    /**
     * 树种1
     */
    private String sz1;

    /**
     * 树种2
     */
    private String sz2;

    /**
     * 比例
     */
    private String bili;

    /**
     * 植被类型
     */
    private String zblx;

    /**
     * 坡度
     */
    private String poDu;

    /**
     * 县统计上报面积
     */
    private String xtjsbmj;

    /**
     * 成活率
     */
    private String chl;

    /**
     * 成活率等级
     */
    private String chlDj;

    /**
     * 郁闭度（灌木林覆盖度）
     */
    private String ybd;

    /**
     * 核实面积
     */
    private String hsmj;

    /**
     * 不核实面积
     */
    private String bhsmj;

    /**
     * 合格面积
     */
    private String hgmj;

    /**
     * 成林面积
     */
    private String clmj;

    /**
     * 待补植面积
     */
    private String dbzmj;

    /**
     * 失败（不合格）面积
     */
    private String bhgmj;

    /**
     * 损失面积
     */
    private String ssmj;

    /**
     * 不核实原因
     */
    private String bhsyy;

    /**
     * 未成林原因
     */
    private String wclyy;

    /**
     * 待补植原因
     */
    private String dbzyy;

    /**
     * 不合格（失败）原因
     */
    private String bhgyy;

    /**
     * 损失原因
     */
    private String ssyy;

    /**
     * 是否有作业设计
     */
    private String sfsj;

    /**
     * 作业设计是否审批
     */
    private String sfsp;

    /**
     * 设计单位资质
     */
    private String sjdwzz;

    /**
     * 是否按设计施工
     */
    private String sfasjss;

    /**
     * 是否有档案
     */
    private String sfyda;

    /**
     * 是否抚育
     */
    private String sffy;

    /**
     * 是否管护
     */
    private String sfgh;

    /**
     * 是否开展县级自检
     */
    private String sfzj;

    /**
     * 是否签订合同
     */
    private String sfyht;

    /**
     * 是否发放林权证
     */
    private String sfylqz;

    /**
     * 育林方式
     */
    private String ylfs;

    /**
     * 设计育林面积
     */
    private String sjylmj;

    /**
     * 核实育林面积
     */
    private String hsylmj;

    /**
     * 造林主体类型
     */
    private String zlztlx;

    /**
     * 备注1
     */
    private String bz1;

    /**
     * 备注2
     */
    private String bz2;

    /**
     * 备注3
     */
    private String bz3;

    /**
     * 核查人员
     */
    private String hcry;

    /**
     * 核查日期
     */
    private String hcrq;

    /**
     * 核查照片
     */
    private String photo;

    /**
     * 状态
     */
    private String stat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBsm() {
        return bsm;
    }

    public void setBsm(String bsm) {
        this.bsm = bsm == null ? null : bsm.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town == null ? null : town.trim();
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village == null ? null : village.trim();
    }

    public String getLinBan() {
        return linBan;
    }

    public void setLinBan(String linBan) {
        this.linBan = linBan == null ? null : linBan.trim();
    }

    public String getJyingBan() {
        return jyingBan;
    }

    public void setJyingBan(String jyingBan) {
        this.jyingBan = jyingBan == null ? null : jyingBan.trim();
    }

    public String getXiaoBan() {
        return xiaoBan;
    }

    public void setXiaoBan(String xiaoBan) {
        this.xiaoBan = xiaoBan == null ? null : xiaoBan.trim();
    }

    public String getZyXb() {
        return zyXb;
    }

    public void setZyXb(String zyXb) {
        this.zyXb = zyXb == null ? null : zyXb.trim();
    }

    public String getYdh() {
        return ydh;
    }

    public void setYdh(String ydh) {
        this.ydh = ydh == null ? null : ydh.trim();
    }

    public String getTfh() {
        return tfh;
    }

    public void setTfh(String tfh) {
        this.tfh = tfh == null ? null : tfh.trim();
    }

    public String getGclb() {
        return gclb;
    }

    public void setGclb(String gclb) {
        this.gclb = gclb == null ? null : gclb.trim();
    }

    public String getZllb() {
        return zllb;
    }

    public void setZllb(String zllb) {
        this.zllb = zllb == null ? null : zllb.trim();
    }

    public String getJhnd() {
        return jhnd;
    }

    public void setJhnd(String jhnd) {
        this.jhnd = jhnd == null ? null : jhnd.trim();
    }

    public String getZynd() {
        return zynd;
    }

    public void setZynd(String zynd) {
        this.zynd = zynd == null ? null : zynd.trim();
    }

    public String getZlqDilei() {
        return zlqDilei;
    }

    public void setZlqDilei(String zlqDilei) {
        this.zlqDilei = zlqDilei == null ? null : zlqDilei.trim();
    }

    public String getDilei() {
        return dilei;
    }

    public void setDilei(String dilei) {
        this.dilei = dilei == null ? null : dilei.trim();
    }

    public String getFylx() {
        return fylx;
    }

    public void setFylx(String fylx) {
        this.fylx = fylx == null ? null : fylx.trim();
    }

    public String getFyfs() {
        return fyfs;
    }

    public void setFyfs(String fyfs) {
        this.fyfs = fyfs == null ? null : fyfs.trim();
    }

    public String getLdqs() {
        return ldqs;
    }

    public void setLdqs(String ldqs) {
        this.ldqs = ldqs == null ? null : ldqs.trim();
    }

    public String getLmqs() {
        return lmqs;
    }

    public void setLmqs(String lmqs) {
        this.lmqs = lmqs == null ? null : lmqs.trim();
    }

    public String getLz() {
        return lz;
    }

    public void setLz(String lz) {
        this.lz = lz == null ? null : lz.trim();
    }

    public String getSz1() {
        return sz1;
    }

    public void setSz1(String sz1) {
        this.sz1 = sz1 == null ? null : sz1.trim();
    }

    public String getSz2() {
        return sz2;
    }

    public void setSz2(String sz2) {
        this.sz2 = sz2 == null ? null : sz2.trim();
    }

    public String getBili() {
        return bili;
    }

    public void setBili(String bili) {
        this.bili = bili == null ? null : bili.trim();
    }

    public String getZblx() {
        return zblx;
    }

    public void setZblx(String zblx) {
        this.zblx = zblx == null ? null : zblx.trim();
    }

    public String getPoDu() {
        return poDu;
    }

    public void setPoDu(String poDu) {
        this.poDu = poDu == null ? null : poDu.trim();
    }

    public String getXtjsbmj() {
        return xtjsbmj;
    }

    public void setXtjsbmj(String xtjsbmj) {
        this.xtjsbmj = xtjsbmj == null ? null : xtjsbmj.trim();
    }

    public String getChl() {
        return chl;
    }

    public void setChl(String chl) {
        this.chl = chl == null ? null : chl.trim();
    }

    public String getChlDj() {
        return chlDj;
    }

    public void setChlDj(String chlDj) {
        this.chlDj = chlDj == null ? null : chlDj.trim();
    }

    public String getYbd() {
        return ybd;
    }

    public void setYbd(String ybd) {
        this.ybd = ybd == null ? null : ybd.trim();
    }

    public String getHsmj() {
        return hsmj;
    }

    public void setHsmj(String hsmj) {
        this.hsmj = hsmj == null ? null : hsmj.trim();
    }

    public String getBhsmj() {
        return bhsmj;
    }

    public void setBhsmj(String bhsmj) {
        this.bhsmj = bhsmj == null ? null : bhsmj.trim();
    }

    public String getHgmj() {
        return hgmj;
    }

    public void setHgmj(String hgmj) {
        this.hgmj = hgmj == null ? null : hgmj.trim();
    }

    public String getClmj() {
        return clmj;
    }

    public void setClmj(String clmj) {
        this.clmj = clmj == null ? null : clmj.trim();
    }

    public String getDbzmj() {
        return dbzmj;
    }

    public void setDbzmj(String dbzmj) {
        this.dbzmj = dbzmj == null ? null : dbzmj.trim();
    }

    public String getBhgmj() {
        return bhgmj;
    }

    public void setBhgmj(String bhgmj) {
        this.bhgmj = bhgmj == null ? null : bhgmj.trim();
    }

    public String getSsmj() {
        return ssmj;
    }

    public void setSsmj(String ssmj) {
        this.ssmj = ssmj == null ? null : ssmj.trim();
    }

    public String getBhsyy() {
        return bhsyy;
    }

    public void setBhsyy(String bhsyy) {
        this.bhsyy = bhsyy == null ? null : bhsyy.trim();
    }

    public String getWclyy() {
        return wclyy;
    }

    public void setWclyy(String wclyy) {
        this.wclyy = wclyy == null ? null : wclyy.trim();
    }

    public String getDbzyy() {
        return dbzyy;
    }

    public void setDbzyy(String dbzyy) {
        this.dbzyy = dbzyy == null ? null : dbzyy.trim();
    }

    public String getBhgyy() {
        return bhgyy;
    }

    public void setBhgyy(String bhgyy) {
        this.bhgyy = bhgyy == null ? null : bhgyy.trim();
    }

    public String getSsyy() {
        return ssyy;
    }

    public void setSsyy(String ssyy) {
        this.ssyy = ssyy == null ? null : ssyy.trim();
    }

    public String getSfsj() {
        return sfsj;
    }

    public void setSfsj(String sfsj) {
        this.sfsj = sfsj == null ? null : sfsj.trim();
    }

    public String getSfsp() {
        return sfsp;
    }

    public void setSfsp(String sfsp) {
        this.sfsp = sfsp == null ? null : sfsp.trim();
    }

    public String getSjdwzz() {
        return sjdwzz;
    }

    public void setSjdwzz(String sjdwzz) {
        this.sjdwzz = sjdwzz == null ? null : sjdwzz.trim();
    }

    public String getSfasjss() {
        return sfasjss;
    }

    public void setSfasjss(String sfasjss) {
        this.sfasjss = sfasjss == null ? null : sfasjss.trim();
    }

    public String getSfyda() {
        return sfyda;
    }

    public void setSfyda(String sfyda) {
        this.sfyda = sfyda == null ? null : sfyda.trim();
    }

    public String getSffy() {
        return sffy;
    }

    public void setSffy(String sffy) {
        this.sffy = sffy == null ? null : sffy.trim();
    }

    public String getSfgh() {
        return sfgh;
    }

    public void setSfgh(String sfgh) {
        this.sfgh = sfgh == null ? null : sfgh.trim();
    }

    public String getSfzj() {
        return sfzj;
    }

    public void setSfzj(String sfzj) {
        this.sfzj = sfzj == null ? null : sfzj.trim();
    }

    public String getSfyht() {
        return sfyht;
    }

    public void setSfyht(String sfyht) {
        this.sfyht = sfyht == null ? null : sfyht.trim();
    }

    public String getSfylqz() {
        return sfylqz;
    }

    public void setSfylqz(String sfylqz) {
        this.sfylqz = sfylqz == null ? null : sfylqz.trim();
    }

    public String getYlfs() {
        return ylfs;
    }

    public void setYlfs(String ylfs) {
        this.ylfs = ylfs == null ? null : ylfs.trim();
    }

    public String getSjylmj() {
        return sjylmj;
    }

    public void setSjylmj(String sjylmj) {
        this.sjylmj = sjylmj == null ? null : sjylmj.trim();
    }

    public String getHsylmj() {
        return hsylmj;
    }

    public void setHsylmj(String hsylmj) {
        this.hsylmj = hsylmj == null ? null : hsylmj.trim();
    }

    public String getZlztlx() {
        return zlztlx;
    }

    public void setZlztlx(String zlztlx) {
        this.zlztlx = zlztlx == null ? null : zlztlx.trim();
    }

    public String getBz1() {
        return bz1;
    }

    public void setBz1(String bz1) {
        this.bz1 = bz1 == null ? null : bz1.trim();
    }

    public String getBz2() {
        return bz2;
    }

    public void setBz2(String bz2) {
        this.bz2 = bz2 == null ? null : bz2.trim();
    }

    public String getBz3() {
        return bz3;
    }

    public void setBz3(String bz3) {
        this.bz3 = bz3 == null ? null : bz3.trim();
    }

    public String getHcry() {
        return hcry;
    }

    public void setHcry(String hcry) {
        this.hcry = hcry == null ? null : hcry.trim();
    }

    public String getHcrq() {
        return hcrq;
    }

    public void setHcrq(String hcrq) {
        this.hcrq = hcrq == null ? null : hcrq.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat == null ? null : stat.trim();
    }


    
}