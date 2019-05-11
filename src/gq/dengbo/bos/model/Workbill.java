package gq.dengbo.bos.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Workbill {
    private String id;
    private String noticebillId;
    private String type;
    private String pickstate;
    private Timestamp buildtime;
    private Integer attachbilltimes;
    private String remark;
    private String staffId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoticebillId() {
        return noticebillId;
    }

    public void setNoticebillId(String noticebillId) {
        this.noticebillId = noticebillId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPickstate() {
        return pickstate;
    }

    public void setPickstate(String pickstate) {
        this.pickstate = pickstate;
    }

    public Timestamp getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(Timestamp buildtime) {
        this.buildtime = buildtime;
    }

    public Integer getAttachbilltimes() {
        return attachbilltimes;
    }

    public void setAttachbilltimes(Integer attachbilltimes) {
        this.attachbilltimes = attachbilltimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workbill workbill = (Workbill) o;
        return Objects.equals(id, workbill.id) &&
                Objects.equals(noticebillId, workbill.noticebillId) &&
                Objects.equals(type, workbill.type) &&
                Objects.equals(pickstate, workbill.pickstate) &&
                Objects.equals(buildtime, workbill.buildtime) &&
                Objects.equals(attachbilltimes, workbill.attachbilltimes) &&
                Objects.equals(remark, workbill.remark) &&
                Objects.equals(staffId, workbill.staffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, noticebillId, type, pickstate, buildtime, attachbilltimes, remark, staffId);
    }
}
