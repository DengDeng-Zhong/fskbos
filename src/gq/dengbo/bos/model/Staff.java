package gq.dengbo.bos.model;

import java.util.Objects;

public class Staff {
    private String id;
    private String name;
    private String telephone;
    //是否有收单设备 0:无 1:有
    private String haspda;
    //删除标志 0:在职 1:离职
    private String deltag = "0";
    //所属单位
    private String station;
    //收费标准
    private String standard;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getHaspda() {
        return haspda;
    }

    public void setHaspda(String haspda) {
        this.haspda = haspda;
    }

    public String getDeltag() {
        return deltag;
    }

    public void setDeltag(String deltag) {
        this.deltag = deltag;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equals(id, staff.id) &&
                Objects.equals(name, staff.name) &&
                Objects.equals(telephone, staff.telephone) &&
                Objects.equals(haspda, staff.haspda) &&
                Objects.equals(deltag, staff.deltag) &&
                Objects.equals(station, staff.station) &&
                Objects.equals(standard, staff.standard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, telephone, haspda, deltag, station, standard);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", haspda='" + haspda + '\'' +
                ", deltag='" + deltag + '\'' +
                ", station='" + station + '\'' +
                ", standard='" + standard + '\'' +
                '}';
    }
}
