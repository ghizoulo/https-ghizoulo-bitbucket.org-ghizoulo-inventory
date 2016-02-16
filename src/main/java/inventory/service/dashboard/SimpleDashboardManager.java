package inventory.service.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.dao.DashboardDao;

@Service("dashboardServiceImpl")
public class SimpleDashboardManager implements DashboardManager {

	@Autowired
    private DashboardDao dashboardDao;
  
	@Override
	public Long getProductsNbr() throws Exception {
		return dashboardDao.getProductsNbr();
	}

	@Override
	public Long getAlertsNbr() throws Exception {
		return dashboardDao.getAlertsNbr();
	}

	@Override
	public Long getStocksNbr() throws Exception {
		return dashboardDao.getStocksNbr();
	}

	@Override
	public Long getFamillesNbr() throws Exception {
		return dashboardDao.getFamillesNbr();
	}

}
