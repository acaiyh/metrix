package com.metrix.researcher.service;

import feign.Param;
import feign.RequestLine;

public interface RemoteService {

    @RequestLine("GET /list?json={json}&offset={offset}&limit={limit}&fl={fl}")
    String callUrl(@Param("json") String json, @Param("offset") Integer offset, @Param("limit") Integer limit, @Param("fl") String fl);

    @RequestLine("GET /list")
    String callPaper();

}
