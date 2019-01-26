package com.cfw.service.info.impl;

import com.cfw.dao.info.InformationDao;
import com.cfw.model.info.Information;
import com.cfw.service.info.InformationService;
import core.service.BaseService;
import core.util.HtmlUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ray
 * @
 */
@Service
public class InformationServiceImpl extends BaseService<Information> implements InformationService {

	private InformationDao informationDao;

	@Resource
	public void setInformationDao(InformationDao informationDao) {
		this.informationDao = informationDao;
		this.dao = informationDao;
	}

	@Override
	public List<Information> queryInformationHTMLList(List<Information> resultList) {
		List<Information> informationList = new ArrayList<Information>();
		for (Information entity : resultList) {
			Information information = new Information();
			information.setId(entity.getId());
			information.setTitle(entity.getTitle());
			information.setAuthor(entity.getAuthor());
			information.setRefreshTime(entity.getRefreshTime());
			information.setContent(entity.getContent());
			information.setContentNoHTML(HtmlUtils.htmltoText(entity.getContent()));
			informationList.add(information);
		}
		return informationList;
	}

	@Override
	public void indexingInformation() {
		informationDao.indexingInformation();
	}

	@Override
	public List<Information> queryByInformationName(String name) {
		return informationDao.queryByInformationName(name);
	}

}
