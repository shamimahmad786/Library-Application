package com.example.demo.api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.bean.autonomousBean;
import com.example.demo.dataentry.modal.AcademicResults;
import com.example.demo.dataentry.modal.AnualFinance;
import com.example.demo.dataentry.modal.NcertAchiventSurvey;
import com.example.demo.dataentry.modal.NcertTextBookDetails;
import com.example.demo.dataentry.modal.SchoolCMMembercount;
import com.example.demo.dataentry.modal.SchooleDataEntry;
import com.example.demo.dataentry.service.DataEntryImpl;
import com.example.demo.util.ApiPaths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@RestController
@RequestMapping(ApiPaths.SchoolEntryCtrl.CTRL)
@CrossOrigin(origins = {"http://10.25.26.251:4200","http://demo.sdmis.gov.in","http://localhost:4200","http://pgi.seshagun.gov.in","https://pgi.udiseplus.gov.in","http://pgi.udiseplus.gov.in","https://demopgi.udiseplus.gov.in","https://dashboard.seshagun.gov.in/","https://dashboard.udiseplus.gov.in"}, allowedHeaders = "*",allowCredentials = "true")
public class AutonomousController {
	@Autowired
	DataEntryImpl dataEntryImpl;
	
	@Value("${uploadDocumentPath}")
	private String uploadDocumentPath;
	
//	private final Path fileStorageLocation;
	@RequestMapping(value = "/schoolDataEntry", method = RequestMethod.POST)
	public ResponseEntity<SchooleDataEntry> schoolDataEntry(@RequestBody SchooleDataEntry sObj) {
		// System.out.println("sObj--->" + sObj.getSchool_country());
		return dataEntryImpl.schoolDataEntry(sObj);

	}

	@RequestMapping(value = "/getSchoolDetails", method = RequestMethod.POST)
	public ResponseEntity<List<SchooleDataEntry>> getSchoolDetails(@RequestBody String schoolId) {
		return dataEntryImpl.getSchoolDetails(schoolId);

	}

	@RequestMapping(value = "/schoolStudentCount", method = RequestMethod.POST)
	public ResponseEntity<SchoolCMMembercount> schoolStudentCount(@RequestBody SchoolCMMembercount sObj) {
		// System.out.println(sObj.getGeneralBoys());
		return dataEntryImpl.schoolStudentCount(sObj);
	}

	@RequestMapping(value = "/getSchoolStudentCount", method = RequestMethod.POST)
	public ResponseEntity<List<SchoolCMMembercount>> getSchoolStudentCount(@RequestBody autonomousBean autoObj) {
		// System.out.println("schoolId--->"+autoObj.getSchoolId());
		// System.out.println("year------->"+autoObj.getYear());
		// System.out.println(dataEntryImpl.getSchoolStudentCount(autoObj.getSchoolId(),autoObj.getYear()));
		return dataEntryImpl.getSchoolStudentCount(autoObj.getSchoolId(),autoObj.getYear());
	}

	@RequestMapping(value = "/academicResults", method = RequestMethod.POST)
	public ResponseEntity<AcademicResults> academicResults(@RequestBody AcademicResults sObj) {
		// System.out.println(sObj.getSchoolId());
		return dataEntryImpl.academicResults(sObj);

	}

	@RequestMapping(value = "/getAcademicResults", method = RequestMethod.POST)
	public ResponseEntity<List<AcademicResults>> getAcademicResults(@RequestBody autonomousBean autoObj) {
		// // System.out.println(sObj.getSchoolId());
		return dataEntryImpl.getAcademicResults(autoObj.getSchoolId(),autoObj.getYear());

	}

	@RequestMapping(value = "/uploadDocument1", method = RequestMethod.POST)
	public ResponseEntity<?> uploadDocument(@RequestParam("file") MultipartFile file) {
		// System.out.println("In file upload controller");
		// System.out.println("uploadfile---->" + file);
		// if (uploadfile.isEmpty()) {
		// return new ResponseEntity("please select a file!", HttpStatus.OK);
		// }
		//
		// try {
		//
		// byte[] bytes = uploadfile.getBytes();
		// Path path = Paths.get(uploadfile.getOriginalFilename());
		// Files.write(path, bytes);
		//
		// } catch (IOException e) {
		// return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		// }
		return null;
		// return new ResponseEntity("Successfully uploaded - " +
		// uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

	}

	@RequestMapping(value = "/uploadDocument", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("docname") String docname,
			@RequestParam("schoolName") String schoolName) throws IOException {
		// System.out.println("uploadDocumentPath ---->"+uploadDocumentPath);
		// System.out.println("called for upload--->" + docname);
		try {
			File f = new File(uploadDocumentPath + File.separator + schoolName);
			if (!f.exists()) {
				f.mkdir();
			}
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
					file.getOriginalFilename().length());
			// Path filePath = Paths.get("C:\\Users\\NIC\\SRC" + "\\" +
			// file.getOriginalFilename());
			Path filePath = Paths.get(uploadDocumentPath + File.separator + schoolName + File.separator + docname + ext);
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			String userDirectory = System.getProperty("user.dir");
			// System.out.println("userDirectory--->" + userDirectory);
			// System.out.println("id--->" + docname);	
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	@RequestMapping(value = "/getDocumentName", method = RequestMethod.POST)
	public List<Map<String, String>> getDocumentName(@RequestBody String folderName) {
		// System.out.println("folder name--->" + folderName);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		File file = new File(uploadDocumentPath + File.separator + folderName);
		String[] fileList = file.list();
		for (String name : fileList) {
			// System.out.println("name--->" + name);
			Map<String, String> mp = new HashMap<String, String>();
			// System.out.println(name.split("\\.")[0]);
			mp.put("docName", name.split("\\.")[0]);
			mp.put("extension", name.split("\\.")[1]);
			result.add(mp);
		}

		// System.out.println(result);
		return result;
	}

	// @RequestMapping(value = "/downloadDocument", method = RequestMethod.GET)
	// public void downloadDocument(HttpServletRequest request, HttpServletResponse
	// response,@RequestParam ("fileName") String
	// fileName,@RequestParam("fileExtension") String
	// fileExtension,@RequestParam("schoolType") String schoolType) throws
	// IOException {
	// // System.out.println("download document");
	// File file = new File("C:\\Users\\NIC\\SRC"+File.separator +
	// schoolType+File.separator+fileName+"."+fileExtension);
	// // System.out.println("file--->"+file);
	// String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	// // System.out.println("mimeType------->"+mimeType);
	// if (mimeType == null) {
	// mimeType = "application/octet-stream";
	// }
	// // System.out.println("mimeType--->"+mimeType);
	// response.setContentType("text/pdf");
	// response.setHeader("Content-Disposition", String.format("attachment;
	// filename=\"" + file.getName() + "\""));
	// response.setContentLength((int) file.length());
	// InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	// // System.out.println(inputStream);
	// FileCopyUtils.copy(inputStream, response.getOutputStream());
	// }

	@RequestMapping(value = "/downloadDocument", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadDocument(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("fileName") String fileName, @RequestParam("fileExtension") String fileExtension,
			@RequestParam("schoolType") String schoolType) throws IOException {
		
		// System.out.println("called for download");
		 Resource resource=null;
		 Path filePath=null;
		try {
			
			// System.out.println(uploadDocumentPath+File.separator +schoolType+File.separator+fileName+"."+fileExtension);
//			Path filePath = fileStorageLocation.resolve("C:\\Users\\NIC\\SRC"+File.separator +schoolType+File.separator+fileName+"."+fileExtension).normalize();
			 String dataDirectory = request.getServletContext().getRealPath(uploadDocumentPath+File.separator +schoolType);
		        filePath  = Paths.get(uploadDocumentPath+File.separator +schoolType, fileName+"."+fileExtension);
	         resource = new UrlResource(filePath.toUri());
	        // System.out.println(filePath.toUri());
		}catch(Exception ex) {
			ex.printStackTrace();
		}

        
        
        
		// // System.out.println("download document");
		// File file = new File("C:\\Users\\NIC\\SRC"+File.separator +
		// schoolType+File.separator+fileName+"."+fileExtension);
		// // System.out.println("file--->"+file);
		// String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		// // System.out.println("mimeType------->"+mimeType);
		// if (mimeType == null) {
		// mimeType = "application/octet-stream";
		// }
		// // System.out.println("mimeType--->"+mimeType);
		// response.setContentType("text/pdf");
		// response.setHeader("Content-Disposition", String.format("attachment;
		// filename=\"" + file.getName() + "\""));
		// response.setContentLength((int) file.length());
		// InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		// // System.out.println(inputStream);
		// FileCopyUtils.copy(inputStream, response.getOutputStream());
//		// System.out.println("file path--->"+filePath);
//		// System.out.println("file mimetype--->"+ Files.probeContentType(filePath));
		
		String contentType = Files.probeContentType(filePath);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	
	@RequestMapping(value = "/achivmentSurvey", method = RequestMethod.POST)
	public ResponseEntity<NcertAchiventSurvey> achivmentSurvey(@RequestBody NcertAchiventSurvey sObj) {
//		// System.out.println(sObj.getSchoolId());
		return dataEntryImpl.achivmentSurvey(sObj);

	}
	
	@RequestMapping(value = "/getAchivmentSurvey", method = RequestMethod.POST)
	public ResponseEntity<List<NcertAchiventSurvey>> getAchivmentSurvey() {
		return dataEntryImpl.getAchivmentSurvey();

	}
	
	@RequestMapping(value = "/ncertBookDetails", method = RequestMethod.POST)
	public ResponseEntity<NcertTextBookDetails> ncertBookDetails(@RequestBody NcertTextBookDetails sObj) {
		return dataEntryImpl.ncertBookDetails(sObj);
	}
	
	@RequestMapping(value = "/getNcertBookDetails", method = RequestMethod.POST)
	public ResponseEntity<List<NcertTextBookDetails>> getNcertBookDetails(@RequestBody NcertTextBookDetails sObj) {
		return dataEntryImpl.getNcertBookDetails();
	}
	
	@RequestMapping(value = "/anualFinance", method = RequestMethod.POST)
	public ResponseEntity<AnualFinance> anualFinance(@RequestBody AnualFinance sObj) {
		return dataEntryImpl.anualFinance(sObj);
	}
	
	@RequestMapping(value = "/getAnualFinance", method = RequestMethod.POST)
	public ResponseEntity<List<AnualFinance>> getAnualFinance(@RequestBody autonomousBean autoObj) {
		return dataEntryImpl.getAnualFinance(autoObj.getSchoolId(),autoObj.getYear());
	}
	
	

}
