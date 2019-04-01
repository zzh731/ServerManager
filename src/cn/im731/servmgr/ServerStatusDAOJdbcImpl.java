package cn.im731.servmgr;

import java.util.List;

public class ServerStatusDAOJdbcImpl extends DAO<ServerStatus> implements ServerStatusDAO {
    @Override
    public List<ServerStatus> getAll() {
        String sql = "SELECT * FROM zzh_servers";
        return getForList(sql);
    }

    //处理server发来的report心跳包
    @Override
    public void report(ServerStatus serverStatus) {
        //先查id是否存在
        long count = getForValue("SELECT count(id) FROM zzh_servers WHERE id = ?", serverStatus.getId());
        if (count == 0) {//id不存在
            String sql = "INSERT INTO zzh_servers(id, nowIP, lastIP, lastReportTime, frpConfigure, others, descrb) VALUES(?,?,?,?,?,?,?)";
            update(sql, serverStatus.getId(), serverStatus.getNowIP(), serverStatus.getLastIP(), serverStatus.getLastReportTime(), serverStatus.getFrpConfigure(), serverStatus.getOthers(), serverStatus.getDescrb());
        } else {//id存在
            String sql = "UPDATE zzh_servers SET nowIP=?, lastReportTime=?, frpConfigure=?, others=?, descrb=? WHERE id=?";
            update(sql, serverStatus.getNowIP(), serverStatus.getLastReportTime(), serverStatus.getFrpConfigure(), serverStatus.getOthers(), serverStatus.getDescrb(), serverStatus.getId());
        }
    }

    //处理用户对服务器属性的修改
    @Override
    public void update(ServerStatus serverStatus) {

    }

    @Override
    public ServerStatus getById(int id) {
        String sql = "SELECT * FROM zzh_servers WHERE id = ?";
        return get(sql, id);
    }

    @Override
    public void ackAlert(int id) {
        String sql = "SELECT nowIP FROM zzh_servers WHERE id = ?";
        String nowIP = getForValue(sql, id);
        sql = "UPDATE zzh_servers SET lastIP = ? WHERE id = ?";
        update(sql, nowIP, id);
    }
}
