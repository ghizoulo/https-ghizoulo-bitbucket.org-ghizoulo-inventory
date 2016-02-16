package inventory.dao;

public interface DashboardDao {

    public Long getProductsNbr() throws Exception;
    public Long getAlertsNbr() throws Exception;
    public Long getStocksNbr() throws Exception;
    public Long getFamillesNbr() throws Exception;
}
