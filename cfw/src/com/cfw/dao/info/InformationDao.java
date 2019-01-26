package com.cfw.dao.info;

import com.cfw.model.info.Information;
import core.dao.Dao;

import java.util.List;

/**
 * @author Ray
 * @
 */
public interface InformationDao extends Dao<Information> {

	void indexingInformation();

	List<Information> queryByInformationName(String name);

}
