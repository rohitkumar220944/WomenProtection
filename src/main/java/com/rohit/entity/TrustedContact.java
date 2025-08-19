package com.rohit.entity;

import com.rohit.dto.TrustedContactDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrustedContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String relation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	public TrustedContact(String name, String phone, String relation, User user) {
		super();
		this.name = name;
		this.phone = phone;
		this.relation = relation;
		this.user = user;
	}
	
	public TrustedContact(TrustedContactDTO model)
	{
		this.name=model.getName();
		this.phone=model.getPhone();
		this.relation=model.getRelation();
	}
    
}
