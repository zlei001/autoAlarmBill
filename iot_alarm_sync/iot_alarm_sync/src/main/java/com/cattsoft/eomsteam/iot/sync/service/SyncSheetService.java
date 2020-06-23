package com.cattsoft.eomsteam.iot.sync.service;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface SyncSheetService {
    @WebMethod
    public String syncSheetState(@WebParam(name="syncXml") String syncXml);
}