package kr.co.searchAPI.regionproject.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.searchAPI.regionproject.dto.RegionDTO;
import kr.co.searchAPI.regionproject.serivce.RegionProjectService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/region")
@Slf4j
public class RegionProjectController {

	@Autowired
	RegionProjectService regionProjectService;
	
	@PostMapping("/search")
	public List<RegionDTO> regionSearch(
			@RequestBody Map<String, Object> params
			){
		
		Map<String,List<RegionDTO>> resultMap = new HashMap<String, List<RegionDTO>>();
		List<RegionDTO> list = new ArrayList<RegionDTO>();
		
		list = regionProjectService.selectRegion(params);
		
		if(list.size() > 0) {
			resultMap.put("regions", list);
		}
		
		return list;
	}
	
	
	@PostMapping("/edit/{regionCd}")
	public RegionDTO regionUpdate(
			@RequestBody Map<String, Object> params,
			@PathVariable String regionCd
			){
		
		RegionDTO regionDTO = RegionDTO.builder()
				.regionCd(regionCd)
				.target((String) params.get("target"))
				.usage((String) params.get("usage"))
				.limit((String) params.get("limit"))
				.rate((String) params.get("rate"))
				.institute((String) params.get("institute"))
				.mgmt((String) params.get("mgmt"))
				.reception((String) params.get("reception"))
				.build();

		regionProjectService.regionUpdate(regionDTO);
		
		return regionDTO;
	}
}
