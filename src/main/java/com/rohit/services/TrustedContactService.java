package com.rohit.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rohit.dto.TrustedContactDTO;
import com.rohit.entity.TrustedContact;
import com.rohit.entity.User;
import com.rohit.repositories.TrustedContactRepository;

@Service
public class TrustedContactService {

    @Autowired
    private TrustedContactRepository contactRepo;

    public TrustedContact save(TrustedContactDTO contact) {
    	try {
			TrustedContact tr=new TrustedContact(contact);
			//tr.setCreated(LocalDate.now());
			User userob = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			tr.setUser(userob);
			return contactRepo.save(tr);
		}catch(Exception e) {
			return null;
		}
    }

//    public List<TrustedContact> list(User userob) {
//    	try {
//			return contactRepo.findByUserId(userob);
//		}catch(Exception e){
//			return null;
//		}
//    }
//
//    public TrustedContact get(Long id) {
//        return contactRepo.findById(id).orElse(null);
//    }
//
//    public void delete(Long id) {
//        contactRepo.deleteById(id);
//    }
//
//    public TrustedContact update(TrustedContact contact) {
//        return contactRepo.save(contact);
//    }
}
