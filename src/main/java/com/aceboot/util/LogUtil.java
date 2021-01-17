package com.aceboot.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.List;

public class LogUtil {
    static private Log log = LogFactory.getLog(LogUtil.class);

    public static void printLog(List<Object> parm) {
        for (int i = 0; i < parm.size(); i++) {
            log.info(i + ". ***" + parm.get(i).toString() + "***");
        }
    }

    public static void main(String[] args) {
        log.info("info");
        log.error("error");
        log.debug("debug");
        log.fatal("fatal");
    }

    /**
     * 删除多余的log
     *
     * @param fullPath
     * @param lineNum
     * @throws IOException
     */
    public void delLogByLineNum(String fullPath, int lineNum) throws IOException {
        FileUtil fileUtil = new FileUtil();
        fileUtil.removeLinesByLineNum(fullPath, lineNum);
    }

}
