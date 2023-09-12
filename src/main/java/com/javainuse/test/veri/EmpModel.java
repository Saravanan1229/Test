package com.javainuse.test.veri;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


import com.javainuse.test.TestModel;


@Entity
@Table(name="emplyee")
public class EmpModel {
	
	@Id
	@Column(name="empid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empid;
	
	@Column(name="comname")
	private String comname;
	
	@Column(name="phoneno")
	private String phoneno;
	
    @Lob
    private byte[] imageData;
    
    @ManyToOne
    @JoinColumn(name = "id")
    private TestModel test;
    
	public int getId() {
		return empid;
	}

	public void setId(int id) {
		this.empid = empid;
	}

	public String getComname() {
		return comname;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public void setComname(String comname) {
		this.comname = comname;
	}

	public String getphoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	

}
