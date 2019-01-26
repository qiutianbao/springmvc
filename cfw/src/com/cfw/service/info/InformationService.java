package com.cfw.service.info;

import com.cfw.model.info.Information;
import com.cfw.model.info.Information;
import core.service.Service;

import java.util.List;

/**
 * @author Ray
 * @
 */
public interface InformationService extends Service<Information> {

	List<Information> queryInformationHTMLList(List<Information> resultList);

	void indexingInformation();

	List<Information> queryByInformationName(String name);

}
