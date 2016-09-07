package com.yc.biz.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.OpRecord;
import com.yc.biz.OpMailSender;
import com.yc.biz.OpRecordReportBiz;
import com.yc.dao.OpRecordDao;
import com.yc.utils.ExcelUtil;

@Service("opRecordReportBizImpl")
public class OpRecordReportBizImpl implements OpRecordReportBiz {

	private OpRecordDao opRecordDao;
	private ExcelUtil excelUtil;
	
	private OpMailSender opMailSender;
	@Override
	public void sendHello() {
		// 1. 查出当天所有的成交记录
		OpRecord opRecord = new OpRecord();
		List<OpRecord> list = this.opRecordDao.findCurrentDayRecord(opRecord);
		List<Map<String,String>> listmap=new ArrayList<Map<String,String>>(); 
		if(  list!=null &&list.size()>0){
			for(  OpRecord op:list){
				Map<String,String> map=new HashMap<String,String>();
				map.put("id", String.valueOf(op.getId())   );
				map.put("accountid", String.valueOf(op.getAccountid()) );
				map.put("opmoney", String.valueOf(op.getOpmoney()) );
				map.put("optime", op.getOptime());
				listmap.add(   map);
			}
		}
		Map<String,String> map=new HashMap<String,String>();
		map.put(  "流水号"  , "id");
		map.put(  "账号"  , "accountid");
		map.put(  "金额"  , "opmoney");
		map.put(  "时间"  , "optime");
		String titles="流水号,账号,金额,时间";
		File directory=new File( System.getProperty("user.dir")+File.separator+"data") ;
		if(  directory.exists()==false){
			directory.mkdirs();
		}
		FileOutputStream fos=null;
		try {
			File file=new File(  directory, "data.xls"    ) ;
			 fos=new FileOutputStream( file );
			// 2. 用于将数据转为 excel文档
			this.excelUtil.createNewExcel(listmap, titles, map, fos);
			this.opMailSender.sendTodayOpRecordEmail(file);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if( fos!=null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		

	}

	@Resource(name = "opRecordDaoMyBatisImpl")
	public void setOpRecordDao(OpRecordDao opRecordDao) {
		this.opRecordDao = opRecordDao;
	}

	@Resource(name="excelUtil")   //体现di
	public void setExcelUtil(ExcelUtil excelUtil) {
		this.excelUtil = excelUtil;
	}

	@Resource(name="opMailSenderImpl")
	public void setOpMailSender(OpMailSender opMailSender) {
		this.opMailSender = opMailSender;
	}

	
	
}
