package kr.co.searchAPI.regionproject.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
		/*
		 * String keyword = ""; boolean isKey = params.containsKey("region"); if(isKey)
		 * { keyword = (String)params.get("region"); }
		 * 
		 * if(!"".equals(keyword)) { list =
		 * regionProjectService.findAllByRegion(keyword); }else { list =
		 * regionProjectService.allFindRegion(); }
		 */
		
		list = regionProjectService.selectRegion(params);
		
		if(list.size() > 0) {
			resultMap.put("regions", list);
		}
		
		return list;
	}
	
	
	@PostMapping("/edit")
	public List<RegionDTO> regionUpdate(
			@RequestBody Map<String, Object> params
			){
		
		Map<String,List<RegionDTO>> resultMap = new HashMap<String, List<RegionDTO>>();
		List<RegionDTO> list = new ArrayList<RegionDTO>();
		String keyword = "";
		boolean isKey = params.containsKey("region");
		if(isKey) {
			keyword = (String)params.get("region");
		}
		
		
		if(list.size() > 0) {
			resultMap.put("regions", list);
		}
		
		return list;
	}
}
