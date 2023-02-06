package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mst_state",schema = "public")
public class StateMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "state_id")
	private Long id;
	@Column(name = "state_name")
	private String stateName;
	@Column(name = "udise_state_code")
	private String udiseStateCode;
	@Column(name = "lgd_state_id")
	private Long lgdStateId;
	@Column(name = "status")
	private String status;
	@Column(name = "is_ut")
	private Long isUt;
	@Column(name = "path")
	private String path;
	@Column(name = "banner")
	private String banner;
	@OneToMany(targetEntity = DistrictMaster.class, mappedBy = "stateMaster", fetch = FetchType.LAZY)
	private List<DistrictMaster> districts;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getUdiseStateCode() {
		return udiseStateCode;
	}
	public void setUdiseStateCode(String udiseStateCode) {
		this.udiseStateCode = udiseStateCode;
	}
	public Long getLgdStateId() {
		return lgdStateId;
	}
	public void setLgdStateId(Long lgdStateId) {
		this.lgdStateId = lgdStateId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getIsUt() {
		return isUt;
	}
	public void setIsUt(Long isUt) {
		this.isUt = isUt;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public List<DistrictMaster> getDistricts() {
		return districts;
	}
	public void setDistricts(List<DistrictMaster> districts) {
		this.districts = districts;
	}
	
	
	
	
//	@Override
//	public String toString() {
//		return "StateMaster [id=" + id + ", stateName=" + stateName + ", udiseStateCode=" + udiseStateCode
//				+ ", stateId=" + lgdStateId + ", status=" + status + ", isUt=" + isUt + ", path=" + path + ", banner="
//				+ banner + ", districts=" + districts + "]";
//	}

}
