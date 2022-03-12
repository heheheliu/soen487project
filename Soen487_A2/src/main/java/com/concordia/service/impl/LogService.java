package com.concordia.service.impl;

import com.concordia.dao.AlbumDao;



import com.concordia.pojo.ServiceStatus;
import com.soen487.log_ws.LogEntry;
import com.soen487.log_ws.LogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    AlbumDao albumDao;

    private LogList logList = new LogList();

    public LogList getChangeLogs(String from, String to, String type){
        logList.getList().clear();
        for(LogEntry entry: albumDao.getLogEntry(from, to, type)){
            logList.getList().add(entry);
        }
        return logList;

    }

    public LogList getAllChangeLogs(){
        logList.getList().clear();
        for(LogEntry entry: albumDao.getAllLogEntry()){
            logList.getList().add(entry);
        }
        return logList;
    }

    public ServiceStatus clearLogs(){
        ServiceStatus status = new ServiceStatus();
        status.setCode("Error");
        status.setDescription("the method is not yet supported");
        return status;
    }


}
