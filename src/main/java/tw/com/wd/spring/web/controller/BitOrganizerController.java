package tw.com.wd.spring.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.wd.spring.domain.BitOrganizer;
import tw.com.wd.spring.service.IBitOrganizerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class BitOrganizerController implements org.springframework.web.servlet.mvc.Controller {
	private String viewPage;
	
	@Autowired
	private IBitOrganizerService bitOrganizerService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse rsp) throws Exception {
		BitOrganizer entity = bitOrganizerService.find(BitOrganizer.class, Long.valueOf(0));
		return new ModelAndView(viewPage, getMapFromEntity(entity));
	}
	
	private Map<String, String> getMapFromEntity(BitOrganizer entity) {
		Map<String, String> entityMap = new HashMap<String, String>();
		entityMap.put("date", entity.getRecordDate().toString());
		entityMap.put("itemName", entity.getItemName());
		entityMap.put("price", String.valueOf(entity.getPrice()));
		return entityMap;
	}

    public void setViewPage(String viewPage) {
        this.viewPage = viewPage;
    }
    
    public void setBitOrganizerService(IBitOrganizerService bitOrganizerService) {
    	this.bitOrganizerService = bitOrganizerService;
    }
}
