package kr.co.searchAPI.regionproject.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="tb_region_cd")
public class RegionCdDTO {
	
	@Id
	@Column(name="c_region_cd")
	private String regionCd;				//지자체코드
	
	@Column(name="c_region_nm")
	private String region;					//지자체명
}
