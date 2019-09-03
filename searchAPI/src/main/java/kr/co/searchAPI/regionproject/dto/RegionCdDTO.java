package kr.co.searchAPI.regionproject.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="Regions_cd")
public class RegionCdDTO {
	
	@Id
	@Column(name="region_cd")
	private String regionCd;						//지자체코드
	
	@Column(name="region_nm")
	private String region;					//지자체명
	
}
