package com.kyee.iszx.controller.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyee.iszx.bean.KyAppSetting;
import com.kyee.iszx.bean.KyApplication;
import com.kyee.iszx.bean.KyMethod;
import com.kyee.iszx.bean.SysDictionaryItem;
import com.kyee.iszx.common.AppConstants;
import com.kyee.iszx.common.PageData;
import com.kyee.iszx.service.IAppSettingService;
import com.kyee.iszx.service.IItemSevice;
import com.kyee.iszx.service.IKyApplicationService;
import com.kyee.iszx.service.IMethodService;
import com.kyee.iszx.util.log.LogService;
import com.kyee.iszx.util.string.StringUtil;



@Controller


public class ViewJump implements LogService{
	
	@Autowired
	 private IMethodService methodService;
	@Autowired
	 private IKyApplicationService kyApplicationService;
	@Autowired
	 private IAppSettingService appSettingService;
	@Autowired
	 private IItemSevice itemSevice;
	 
@RequestMapping("/")
public String index(Model model) {
	List<KyApplication> kyApp = kyApplicationService.findAllApp();
	List<KyMethod> kymethod = methodService.findAllMethod();
	model.addAttribute("kyApp",kyApp);
	model.addAttribute("kymethod",kymethod);
    return "/index";
}


@RequestMapping("/method.opt")
public String methodOpt(Model model,@RequestParam("appid") String appid,@RequestParam("metid") String metid) {
	model.addAttribute("appid",appid);
	model.addAttribute("metid",metid);
	List<String> ids = new ArrayList<String>();
	ids.add(metid);
	List<KyMethod> methods = methodService.findMethodByIds(ids);
	if(methods.size() == 1) {
		KyMethod method = methods.get(0);
		String url = appSettingService.getConfig(AppConstants.IIPS_URL,appid) + method.getAccessPath();
		model.addAttribute("accessPath",url);
		model.addAttribute("metAffectContent",method.getAffectContent());
	}else {
		model.addAttribute("errorMsg","方法"+metid +"不存在！");
		return "/error/error";
	}
    return "/method/metOperation";
}

@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping("/app.opt")
public String appOpt(Model model,@RequestParam("appid") String appid) {
	model.addAttribute("appid",appid);
	List<KyAppSetting> appSetting = appSettingService.findAppSettingByCondition(appid);
	model.addAttribute("appSetting",appSetting);
    return "/application/appOperation";
}

@RequestMapping("/qrcode.show")
public String qrcodeShow(Model model,@RequestParam("text") String text,@RequestParam("payType") String payType) {
	String msg = "未知支付方式";
	if("page".equals(payType)) {
		List list = appSettingService.findAppSettingByCondition("1");
		String url = appSettingService.getConfig(AppConstants.SERVICE_URL,list);
		text = url + "/payPage";
		msg = "局域网扫码打开支付页面";
	}else {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dictCode", "1");
		map.put("itemValue", payType);
	List<SysDictionaryItem> sysDictionaryItems = itemSevice.getItemList(map);
	if(sysDictionaryItems != null && sysDictionaryItems.size() >0) {
		for(SysDictionaryItem item : sysDictionaryItems) {
			if(StringUtil.isNotEmpty(payType) && payType.equals(item.getItemValue())) {
				msg = item.getItemName();
			}
		}
	}
	}
	model.addAttribute("msg",msg);
	model.addAttribute("text",text);
    return "/qrcode/qrcode";
}

@RequestMapping("/payPage")
public String payPage() {
    return "/payPage/payPage";
}

}
