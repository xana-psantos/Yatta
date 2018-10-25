package fr.afcepf.al31.yatta.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al31.yatta.business.api.IBusinessBidon;
import fr.afcepf.al31.yatta.dao.api.IDaoMembre;
import fr.afcepf.al31.yatta.entities.Membre;

@Service
@Transactional
public class BusinessBidon implements IBusinessBidon {
    
    @Autowired
	private IDaoMembre daoMembre;

    @Override
    public Membre getMembreById(int paramId) {
        return daoMembre.findOne(paramId);
    }

    public IDaoMembre getDaoMembre() {
        return daoMembre;
    }

    public void setDaoMembre(IDaoMembre paramDaoMembre) {
        daoMembre = paramDaoMembre;
    }

	
	

}
