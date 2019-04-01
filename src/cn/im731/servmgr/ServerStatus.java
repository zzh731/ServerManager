package cn.im731.servmgr;

public class ServerStatus {
    private int id;
    private String descrb;
    private String nowIP;
    private String lastIP;
    private String frpConfigure;
    private String lastReportTime;
    private String others;

    public ServerStatus() {
    }

    public ServerStatus(int id, String descrb, String nowIP, String lastIP, String frpConfigure, String lastReportTime, String others) {
        this.id = id;
        this.descrb = descrb;
        this.nowIP = nowIP;
        this.lastIP = lastIP;
        this.frpConfigure = frpConfigure;
        this.lastReportTime = lastReportTime;
        this.others = others;
    }

    @Override
    public String toString() {
        return "ServerStatus{" +
                "id=" + id +
                ", describe='" + descrb + '\'' +
                ", nowIP='" + nowIP + '\'' +
                ", lastIP='" + lastIP + '\'' +
                ", frpConfigure='" + frpConfigure + '\'' +
                ", lastReportTime='" + lastReportTime + '\'' +
                ", others='" + others + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrb() {
        return descrb;
    }

    public void setDescrb(String descrb) {
        this.descrb = descrb;
    }

    public String getNowIP() {
        return nowIP;
    }

    public void setNowIP(String nowIP) {
        this.nowIP = nowIP;
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
    }

    public String getFrpConfigure() {
        return frpConfigure;
    }

    public void setFrpConfigure(String frpConfigure) {
        this.frpConfigure = frpConfigure;
    }

    public String getLastReportTime() {
        return lastReportTime;
    }

    public void setLastReportTime(String lastReportTime) {
        this.lastReportTime = lastReportTime;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
