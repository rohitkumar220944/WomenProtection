package com.rohit.controllers;

import com.rohit.dto.TrustedContactDTO;
import com.rohit.entity.TrustedContact;
import com.rohit.repositories.UserRepositoty;
import com.rohit.util.APIResponse;
import com.rohit.services.TrustedContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class TrustedContactController {

    @Autowired
    private TrustedContactService contactService;

    @Autowired
    private UserRepositoty userRepo;

    @PostMapping("/add")
    public ResponseEntity<APIResponse> save(@RequestBody TrustedContactDTO contact) {
        TrustedContact saved = contactService.save(contact);
        if(saved!=null)
			return ResponseEntity.ok(new APIResponse("Message Saved...",true,saved));
		else
			return ResponseEntity.ok(new APIResponse("Message Not Save...",false,null));
	
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<APIResponse> getUserContacts(@PathVariable Long userId) {
//        List<TrustedContact> contacts = contactService.getByUserId(userId);
//        return ResponseEntity.ok(new APIResponse("Contacts fetched", true, contacts));
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<APIResponse> update(@RequestBody TrustedContact contact) {
//        TrustedContact updated = contactService.update(contact);
//        return ResponseEntity.ok(new APIResponse("Contact updated", true, updated));
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<APIResponse> delete(@PathVariable Long id) {
//        contactService.delete(id);
//        return ResponseEntity.ok(new APIResponse("Contact deleted", true, null));
//    }
}
