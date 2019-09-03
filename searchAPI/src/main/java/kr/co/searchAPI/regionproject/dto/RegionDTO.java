package kr.co.searchAPI.regionproject.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name="Regions_info")
public class RegionDTO {
	
	@Id
	@Column(name="c_region_seq")
	private Long regionSeq;					//구분
	
	@Column(name="c_region_cd")
	@JoinColumns({
		@JoinColumn(name="c_region_cd",
				referencedColumnName = "region_cd")
	})
	private String regionCd;				//지자체코드
	
	@Column(name="c_target")
	private String target;					//지원대상
	
	@Column(name="c_usage")
	private String usage;					//용도
	
	@Column(name="c_limit")
	private String limit;					//지원한도
	
	@Column(name="c_rate")
	private String rate;					//이차보전
	
	@Column(name="c_institute")
	private String institute;				//추천기관
	
	@Column(name="c_mgmt")
	private String mgmt;					//관리점
	
	@Column(name="c_reception")
	private String reception;				//취급점
	
	@Column(name="c_insert_date")
	private String insertDate;				//생성일자
	
	@Column(name="c_update_date")
	private String updateDate;				//수정일자
}