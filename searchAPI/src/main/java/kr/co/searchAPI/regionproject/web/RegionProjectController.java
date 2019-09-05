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

import kr.co.searchAPI.regionproject.dto.RegionCdDTO;
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
			@RequestBody RegionCdDTO cdDto
			){
		
		List<RegionDTO> list = new ArrayList<RegionDTO>();
		
		list = regionProjectService.selectRegion(cdDto);
		
		return list;
	}
	
	
	@PostMapping("/edit/{regionCd}")
	public Map<String, String> regionUpdate(
			@RequestBody RegionDTO regionDTO,
			@PathVariable String regionCd
			){
		
		regionDTO.setRegionCd(regionCd);
		
		Map<String, String> resultMap = regionProjectService.regionUpdate(regionDTO);
		
		return resultMap;
	}
	
	
}
