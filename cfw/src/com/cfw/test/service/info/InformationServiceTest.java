package com.cfw.test.service.info;

import com.cfw.dao.info.InformationDao;
import com.cfw.service.info.InformationService;
import com.cfw.test.SSHTest;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Ray
 * Date: 15-9-11
 * Time: 下午5:45
 * To change this template use File | Settings | File Templates.
 */

public class InformationServiceTest  extends  SSHTest{

    @Resource
    private InformationService informationService;

    @Resource
    private InformationDao informationDao;

    @Test
    public void testSaveInfo(){
        Assert.assertNotNull(informationService);
        Assert.assertEquals(1,1);
    }
}
