package ma.sir.event.service.impl.admin;

import ma.sir.event.bean.core.Evenement;
import ma.sir.event.bean.history.EvenementHistory;
import ma.sir.event.dao.criteria.core.EvenementCriteria;
import ma.sir.event.dao.criteria.history.EvenementHistoryCriteria;
import ma.sir.event.dao.facade.core.EvenementDao;
import ma.sir.event.dao.facade.history.EvenementHistoryDao;
import ma.sir.event.dao.specification.core.EvenementSpecification;
import ma.sir.event.service.facade.admin.EvenementAdminService;
import ma.sir.event.zynerator.service.AbstractServiceImpl;
import ma.sir.event.zynerator.util.ListUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;


import ma.sir.event.service.facade.admin.EvenementStateAdminService ;
import ma.sir.event.service.facade.admin.SalleAdminService ;


import java.util.List;
@Service
public class EvenementAdminServiceImpl extends AbstractServiceImpl<Evenement,EvenementHistory, EvenementCriteria, EvenementHistoryCriteria, EvenementDao,
EvenementHistoryDao> implements EvenementAdminService {


    public Evenement findByReferenceEntity(Evenement t){
        return  dao.findByReference(t.getReference());
    }


   // @Cacheable(cacheNames = "itemCache", key = "#id", cacheManager = "redisCacheManager")
    public List<Evenement> findBySalleId(Long id){
        return dao.findBySalleId(id);
    }
    public int deleteBySalleId(Long id){
        return dao.deleteBySalleId(id);
    }
    public List<Evenement> findByEvenementStateId(Long id){
        return dao.findByEvenementStateId(id);
    }
    public int deleteByEvenementStateId(Long id){
        return dao.deleteByEvenementStateId(id);
    }

    @Override
    public Evenement findByReference(String reference) {
        return dao.findByReference(reference);
    }

    public void configure() {
        super.configure(Evenement.class,EvenementHistory.class, EvenementHistoryCriteria.class, EvenementSpecification.class);
    }

    @Autowired
    private EvenementStateAdminService evenementStateService ;
    @Autowired
    private SalleAdminService salleService ;
    public EvenementAdminServiceImpl(EvenementDao dao, EvenementHistoryDao historyDao) {
        super(dao, historyDao);
    }

}