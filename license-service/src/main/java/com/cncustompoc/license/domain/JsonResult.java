package com.cncustompoc.license.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class JsonResult<T>  implements Serializable {
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    private int state=0;
    private String message = "";
    private T data=null;
    private final Logger log = LoggerFactory.getLogger(getClass());
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public JsonResult() {
        state = SUCCESS;
    }

    public JsonResult(int state,String message) {
        this.state = state;
        this.message = message;
        JSONObject joo = null;
        JSONArray jaa = null;
        try {
            joo = (JSONObject) JSONObject.toJSON(data);
            log.info("JsonResult:"+"state:"+state+",message:"+message+",data:"+joo.toJSONString());
        }
        catch(Exception e)
        {
            try {
                jaa = (JSONArray) JSONArray.toJSON(data);
                log.info("JsonResult:" + "state:" + state + ",message:" + message + ",data:" + jaa.toJSONString());
            }
            catch(Exception ee){}
        }
    }

    //为了方便,重载n个构造器
    public JsonResult(int state, T data) {
        this.state = state;
        this.data = data;
        JSONObject joo = null;
        JSONArray jaa = null;
        try {
            joo = (JSONObject) JSONObject.toJSON(data);
            log.info("JsonResult:"+"state:"+state+",message:"+message+",data:"+joo.toJSONString());
        }
        catch(Exception e)
        {
            try {
                jaa = (JSONArray) JSONArray.toJSON(data);
                log.info("JsonResult:" + "state:" + state + ",message:" + message + ",data:" + jaa.toJSONString());
            }
            catch(Exception ee){}
        }
    }

    public JsonResult(T data) {

        this.data = data;
        JSONObject joo = null;
        JSONArray jaa = null;
        try {
            joo = (JSONObject) JSONObject.toJSON(data);
            log.info("JsonResult:"+"state:"+state+",message:"+message+",data:"+joo.toJSONString());
        }
        catch(Exception e)
        {
            try {
                jaa = (JSONArray) JSONArray.toJSON(data);
                log.info("JsonResult:" + "state:" + state + ",message:" + message + ",data:" + jaa.toJSONString());
            }
            catch(Exception ee){}
        }
    }

    //为了方便,重载n个构造器
    public JsonResult(int state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
        JSONObject joo = null;
        JSONArray jaa = null;
        try {
            joo = (JSONObject) JSONObject.toJSON(data);
            log.info("JsonResult:"+"state:"+state+",message:"+message+",data:"+joo.toJSONString());
        }
        catch(Exception e)
        {
            try {
                jaa = (JSONArray) JSONArray.toJSON(data);
                log.info("JsonResult:" + "state:" + state + ",message:" + message + ",data:" + jaa.toJSONString());
            }
            catch(Exception ee){}
        }
    }
}