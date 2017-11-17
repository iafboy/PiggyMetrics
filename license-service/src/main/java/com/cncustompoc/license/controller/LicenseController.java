package com.cncustompoc.license.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cncustompoc.license.domain.*;
import com.cncustompoc.license.service.LicenseService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.List;

@RestController
public class LicenseController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private LicenseService licenseService;


	@RequestMapping(value = "/license", method = RequestMethod.GET,headers="Accept=application/json",produces="application/json;charset=UTF-8")
	public JsonResult getLicense() {
		log.info(">>>>>>>>>>>>>start /license GET >>>>>>>>>>>>");
		List<LicenseEntity> res=null;
		try{
			res=licenseService.getLicenses();
		}catch(Exception ex){
			log.error(ex.toString(),ex);
			return new JsonResult(JsonResult.ERROR,ex.getMessage());
		}
		return new JsonResult(res);
	}
	@RequestMapping(value = "/license/{ID}", method = RequestMethod.GET,headers="Accept=application/json",produces="application/json;charset=UTF-8")
	public JsonResult getLicenseByID(@PathVariable String ID) {
		log.info(">>>>>>>>>>>>>start /license{"+ID+"} GET >>>>>>>>>>>>");
		LicenseEntity res=null;
		//ID="1";
		try{
			res=licenseService.getLicenseByID(ID);
		}catch(Exception ex){
			log.error(ex.toString(),ex);
			return new JsonResult(JsonResult.ERROR,ex.getMessage());
		}
		return new JsonResult(res);
	}
	@RequestMapping(value = "/license", method = RequestMethod.PUT,produces="application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public JsonResult saveLicense(@Valid @RequestBody LicenseEntity entity) {
		JSONObject joo = (JSONObject) JSONObject.toJSON(entity);
		log.info(">>>>>>>>>>>>>start /license PUT >>>>>>>>>>>>"+joo.toJSONString());
		try{
			licenseService.saveLicense(entity);
		}catch(Exception ex){
			log.error(ex.toString(),ex);
			return new JsonResult(JsonResult.ERROR,ex.getMessage());
		}
		return new JsonResult(JsonResult.SUCCESS,"保存成功");
	}
	@RequestMapping(value = "/license", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public JsonResult createNewLicense(@Valid @RequestBody LicenseEntity entity) {
		JSONObject joo = (JSONObject) JSONObject.toJSON(entity);
		log.info(">>>>>>>>>>>>>start /license POST >>>>>>>>>>>>"+joo.toJSONString());
		try{
			licenseService.createNewLicense(entity);
		}catch(Exception ex){
			log.error(ex.toString(),ex);
			return new JsonResult(JsonResult.ERROR,ex.getMessage());
		}
		return new JsonResult(JsonResult.SUCCESS,"保存成功");
	}
	@RequestMapping(value = "/license/check", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public JsonResult checkLicense(@Valid @RequestBody DeclarationAndGoodsEntity entity) {
		JSONObject joo = (JSONObject) JSONObject.toJSON(entity);
		log.info(">>>>>>>>>>>>>start /license/check POST >>>>>>>>>>>>"+joo.toJSONString());
		if("license".equals(entity.getDeclarationEntity().getEnterpriseInfo()))
		{
			log.info(">>>>>>>>>>>>>start /license/check POST >>>>>>>>>>>>--人为设定的错误，需要返回false");
			return new JsonResult(JsonResult.ERROR,"校验失败，人为规定需要返回错误");
		}
		boolean f;
		try{
			f= licenseService.checkLicense(entity);
		}catch(Exception ex){
			log.error(ex.toString(),ex);
			return new JsonResult(JsonResult.ERROR,ex.getMessage());
		}
		JSONObject jo = new JSONObject();
		jo.put("flag",f);
		return new JsonResult(JsonResult.SUCCESS,jo);
	}
	//许可证减扣
	@RequestMapping(value = "/license/deduct", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public JsonResult deductLicense(@Valid @RequestBody DeclarationAndGoodsEntity entity) {
		JSONObject joo = (JSONObject) JSONObject.toJSON(entity);
		log.info(">>>>>>>>>>>>>start /license/deduct POST >>>>>>>>>>>>"+joo.toJSONString());
		if("license".equals(entity.getDeclarationEntity().getEnterpriseInfo()))
		{
			log.info(">>>>>>>>>>>>>start /license/deduct POST >>>>>>>>>>>>--人为设定的错误，需要返回false");
			return new JsonResult(JsonResult.ERROR,"扣减失败，人为规定需要返回错误");
		}
		boolean r = false;
		try{
			r = licenseService.deductLicense(entity);
		}catch(Exception ex){
			log.error(ex.toString(),ex);
			return new JsonResult(JsonResult.ERROR,ex.getMessage());
		}
		if(r)
		return new JsonResult(JsonResult.SUCCESS,"成功");
		else
			return new JsonResult(JsonResult.ERROR,"扣减失败，配额不足");

	}
	@RequestMapping(value = "/license/rededuct", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public JsonResult reDeductLicense(@Valid @RequestBody DeclarationAndGoodsEntity entity) {
		JSONObject joo = (JSONObject) JSONObject.toJSON(entity);
		log.info(">>>>>>>>>>>>>start /license/rededuct POST >>>>>>>>>>>>"+joo.toJSONString());
		try{
			licenseService.reDeductLicense(entity);
		}catch(Exception ex){
			log.error(ex.toString(),ex);
			return new JsonResult(JsonResult.ERROR,ex.getMessage());
		}
		return new JsonResult(JsonResult.SUCCESS,"成功");
	}

	@RequestMapping(value = "/osbagent", method = RequestMethod.POST,headers="Accept=application/json",produces="application/json;charset=UTF-8")
	public JsonResult osbAgent(@Valid @RequestBody OSBAgent oa) {
		JSONObject joo = (JSONObject) JSONObject.toJSON(oa);
		log.info(">>>>>>>>>>>>>start /osbagent POST >>>>>>>>>>>>"+joo.toJSONString());
		String url = oa.getRequestURL();
		String mo = oa.getRequestMethod();
		String data = oa.getRequestData();


		String line, resultStr = "";
//		try {
//			URL restURL = new URL(url);
//
//			HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
//			conn.setRequestProperty("Content-Type","application/json");
//			conn.setRequestProperty("Accept","application/json");
//			conn.setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
//
//			conn.setRequestMethod(mo);
//
//			conn.setDoOutput(true);
//
//			conn.setAllowUserInteraction(false);
//
//			PrintStream ps = new PrintStream(conn.getOutputStream());
//			ps.print(data);
//
//			ps.close();
//
//			BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//			while (null != (line = bReader.readLine()))
//
//			{
//
//				resultStr += line;
//
//			}
//
//			bReader.close();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}




		try {

			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(url);
			request.setHeader("Content-Type","application/json");
			//不是表单数据提交，这边使用 StringEntity 即可
			//UrlEncodedFormEntity等都是 HttpEntity 接口的实现类
			StringEntity entity = new StringEntity(data, "UTF-8");//编码
			entity.setContentType("text/xml");
			request.setEntity(entity);
			// 发送请求
			HttpResponse response = client.execute(request);

			org.apache.http.HttpEntity httpEntity = response.getEntity();

			if (httpEntity != null) {
				// EntityUtils.toString 如果不指定编码，EntityUtils默认会使用ISO_8859_1进行编码
				resultStr = EntityUtils.toString(httpEntity, "UTF-8");// 记得设置编码或者如下
				// clientResponse = new String(EntityUtils.toString(httpEntity).getBytes("ISO_8859_1"), "UTF-8");
			}

			if (resultStr == null || "".equals(resultStr)) {
				System.err.println("clientResponse is null or empty.");

			}

			System.out.println(resultStr);

		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(JsonResult.ERROR,e.getMessage());
		}


		JSONObject jo = (JSONObject) JSON.parse(resultStr);
		JsonResult jr = new JsonResult();
		try {
			jr.setState(jo.getInteger("state"));
			jr.setMessage(jo.getString("message"));
			jr.setData(jo.getString("data"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new JsonResult(JsonResult.ERROR,"ESB 调用错误："+resultStr);
		}
//		return resultStr;

		return jr;
	}

//	public static void main(String[] args) {
//		LicenseController l = new LicenseController();
//		OSBAgent oa = new OSBAgent();
//		oa.setRequestData("{\"declarationEntity\":{\"enterpriseInfo\":\"上海进出口\",\"insuranceCost\":3.0,\"otherCost\":2.0,\"id\":1,\"enterpriseID\":\"3\",\"freightCost\":5.0,\"enterpriseName\":\"上海食品进出口公司\",\"attachedInfo\":\"食品进出口\",\"status\":\"0\"},\"goodsInfoEntity\":[{\"goodsCode\":\"007\",\"goodsQuantity\":20}]}");
//		oa.setRequestMethod("POST");
//		oa.setRequestURL("http://140.86.1.88/Customs/AutoCheck/AutoCheck/autocheck");
//
//
//
//		oa.setRequestURL("http://140.86.1.88/Customs/Clearance/Clearance/clearance");
//		oa.setRequestData("{\"declarationEntity\":{\"status\":\"0\",\"enterpriseInfo\":\"\",\"freightCost\":0,\"enterpriseName\":\"\",\"otherCost\":0,\"enterpriseID\":\"\",\"insuranceCost\":0,\"attachedInfo\":\"\",\"id\":8},\"goodsInfoEntity\":[{\"goodsCode\":\"007\",\"goodsQuantity\":20}]}");
//		System.out.println(l.osbAgent(oa).getMessage());
//
//	}
}
