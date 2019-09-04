package kr.co.searchAPI.regionproject.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URI;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import kr.co.searchAPI.regionproject.serivce.RegionProjectService;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class RegionProjectTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private ApplicationContext app;
	
	@Autowired
	RegionProjectService regionProjectService;
	
	/**
	 * 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API
	 */
	@Test
	@Ignore
	public void readFileSave() {
		
		log.info("데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API");
		
		assertNotNull(app);
		log.info("로딩된 빈 갯수 : {} ", app.getBeanDefinitionCount());
		
		String subUrl = "/files/upload";
		URI url = testUrl(subUrl);
		
		File flie = new File("C:\\Users\\user\\git\\searchAPI\\searchAPI\\src\\test\\java\\beforeProject1.csv");
		
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
		parameters.add("msg", "Region");
		parameters.add("files", flie.getName());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		//headers.add("x-waple-authorization", "API키값");
		
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(parameters, headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		log.info("statusCode : {} ", response.getStatusCodeValue());
		log.info("getBody: {} ", response.getBody());
		
		assertTrue(true); 
		
	}
	
	public String getTestFile() {
		
		return "";
	}
	
	/**
	 * 지원하는 지자체 목록 검색 API
	 */
	@Test
	@Ignore
	public void findAllTest() {
		
		log.info("지원하는 지자체 목록 검색 API");
		
		assertNotNull(app);
		log.info("로딩된 빈 갯수 : {} ", app.getBeanDefinitionCount());
		
		String subUrl = "/region/search";
		URI url = testUrl(subUrl);
		
		// Get 방식 요청시 
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertEquals("Request method 'GET' not supported", 405, response.getStatusCodeValue());	
		
		
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("keyword", "");
		
		HttpHeaders headers = new HttpHeaders();
		//headers.add("x-waple-authorization", "API키값");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);
		
		response = restTemplate.postForEntity(url, request, String.class);
		log.info("statusCode : {} ", response.getStatusCodeValue());
		log.info("getBody: {} ", response.getBody());
		
		assertTrue(true); 
		
	}
	
	
	/**
	 * 지원하는 지자체명을 입력받아 해당 지자체의 지원정보를 출력하는 API
	 */
	@Test
	@Ignore
	public void findAllByRegionTest() {
		
		log.info("지원하는 지자체명을 입력받아 해당 지자체의 지원정보를 출력하는 API");
		
		assertNotNull(app);
		log.info("로딩된 빈 갯수 : {} ", app.getBeanDefinitionCount());
		
		String subUrl = "/region/search";
		URI url = testUrl(subUrl);
		
		// Get 방식 요청시 
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertEquals("Request method 'GET' not supported", 405, response.getStatusCodeValue());	
		
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("keyword", "강릉시");
		
		HttpHeaders headers = new HttpHeaders();
		//headers.add("x-waple-authorization", "API키값");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);
		
		response = restTemplate.postForEntity(url, request, String.class);
		log.info("statusCode : {} ", response.getStatusCodeValue());
		log.info("getBody: {} ", response.getBody());
		
		
		assertTrue(true); 
		
	}
	
	private URI testUrl(String subUrl) {
		URI url = null;
		try {
			url = new URI("http://localhost:"+ port + subUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}
	
	/**
	 * 지원하는 지자체 정보 수정 API
	 */
	@Test
	@Ignore
	public void updateRegion() {
		
		log.info("지원하는 지자체명을 입력받아 해당 지자체의 지원정보를 출력하는 API");
		
		assertNotNull(app);
		log.info("로딩된 빈 갯수 : {} ", app.getBeanDefinitionCount());
		
		String subUrl = "/region/edit/reg0001";
		URI url = testUrl(subUrl);
		
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("keyword", "강릉시");
		
		// 객체 전달?
		// 파라미터 전달..?!
		
		HttpHeaders headers = new HttpHeaders();
		//headers.add("x-waple-authorization", "API키값");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		
		log.info("statusCode : {} ", response.getStatusCodeValue());
		log.info("getBody: {} ", response.getBody());
		
		assertTrue(true); 
		
	}
	
	

}
