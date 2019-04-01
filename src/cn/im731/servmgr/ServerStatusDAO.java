package cn.im731.servmgr;

import java.util.List;

public interface ServerStatusDAO {
    public List<ServerStatus> getAll();
    public void report(ServerStatus serverStatus);
    public void update(ServerStatus serverStatus);
    public ServerStatus getById(int id);
    public void ackAlert(int id);
}
