package com.example.demo.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.StateIndicatorScores;
import com.example.demo.bean.StaticReportBeans;
import com.example.demo.bean.pgiPerfomanceList;
import com.example.demo.bean.staticReportBean;
import com.example.demo.bean.AddGroup;
import com.example.demo.bean.CustumResponse;
import com.example.demo.bean.QueryResult;
import com.example.demo.dto.IndicatorRequestDto;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.PgiResponse;
import com.example.demo.dto.RegistirationRequest;
import com.example.demo.dto.TokenResponse;
import com.example.demo.model.BlockMaster;
import com.example.demo.model.CategoryMaster;
import com.example.demo.model.CycleMaster;
import com.example.demo.model.DistrictMaster;
import com.example.demo.model.DistrictQuestion;
import com.example.demo.model.DomainMaster;
import com.example.demo.model.GroupMapping;
import com.example.demo.model.GroupMaster;
import com.example.demo.model.IndicatorMaster;
import com.example.demo.model.StateIndicatorScore;
import com.example.demo.model.StateMaster;
import com.example.demo.model.TableFields;
import com.example.demo.model.TableJoinDetails;
import com.example.demo.model.TableMaster;
import com.example.demo.model.User;
import com.example.demo.model.UserRoleMst;
import com.example.demo.pgi.modal.Access;
import com.example.demo.pgi.modal.DigitalLearning;
import com.example.demo.pgi.modal.DistrictPgi;
import com.example.demo.pgi.modal.Equity;
import com.example.demo.pgi.modal.GovernanceProcess;
import com.example.demo.pgi.modal.InfraFacility;
import com.example.demo.pgi.modal.LearningOutcome;
import com.example.demo.pgi.service.PgiImpl;
import com.example.demo.report.repository.NativeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.GroupServiceImpl;
import com.example.demo.service.IndicatorServiceImpl;
import com.example.demo.service.LocationServiceImpl;
import com.example.demo.service.MasterDataServiceImpl;
import com.example.demo.service.UserRoleMstServiceImpl;
import com.example.demo.service.UserServiceImp;
import com.example.demo.util.ApiPaths;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(ApiPaths.MainCtrl.CTRL)
@CrossOrigin(origins = {"http://10.25.26.251:4200","http://localhost:8086","http://10.25.26.251:1000","http://localhost:1000","http://localhost:4200","http://10.25.26.251:8086","http://demo.sdmis.gov.in","http://pgi.seshagun.gov.in","https://pgi.udiseplus.gov.in","http://pgi.udiseplus.gov.in","https://demopgi.udiseplus.gov.in","https://dashboard.seshagun.gov.in/","https://dashboard.udiseplus.gov.in"}, allowedHeaders = "*",allowCredentials = "true")
public class MainController {
	private static final Logger log = LoggerFactory.getLogger(MainController.class);
	private final AuthenticationManager authenticationManager;

	private final UserRepository userRepository;

	private final UserServiceImp userServiceImp;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final ModelMapper modelMapper;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	LocationServiceImpl locationServiceImpl;
	@Autowired
	private UserRoleMstServiceImpl userRoleMstServiceImpl;
	@Autowired
	private IndicatorServiceImpl indServiceImpl;
	@Autowired
	private MasterDataServiceImpl masterDataServiceImpl;
	@Autowired
	private PgiImpl pgiImpl;
	@Autowired
	private GroupServiceImpl groupServiceImpl; 
@Autowired
CustumResponse custumResponse;
@Autowired
GroupMaster groupMaster;
@Autowired
GroupMapping groupMapping;
@Autowired
AddGroup addGroup;
@Autowired
NativeRepository nativeRepository;
@Autowired
staticReportBean staticReportBean;

@Autowired
StaticReportBeans staticReportBeans;


	public MainController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			AuthenticationManager authenticationManager, ModelMapper modelMapper, UserServiceImp userServiceImp) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.modelMapper = modelMapper;
		this.authenticationManager = authenticationManager;
		this.userServiceImp = userServiceImp;
	}

	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) throws AuthenticationException {
// System.out.println("call  for sigin");
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
 
		final User user = userRepository.findByUsername(request.getUsername());
		final String token = jwtTokenUtil.generateToken(user);
		 // System.out.println("token--->"+token);
		return ResponseEntity.ok(new TokenResponse(user.getUsername(), token, user.getParamName(), user.getParamValue(),
				user.getRoleId(),user.getSchema_name(),user.getStateId(),user.getStateName(),user.getDistrictName(),user.getGroupId()));
	}

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public ResponseEntity<CustumResponse> signUp(@RequestBody RegistirationRequest registirationRequest) throws Exception {
		
		// System.out.println("getPassword--->"+registirationRequest.getGroupId());
		// System.out.println(registirationRequest.getUsername());
		CustumResponse result = userServiceImp.register(registirationRequest);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/delete-user", method = RequestMethod.POST)
	public ResponseEntity<Boolean> deleteUser(@RequestBody RegistirationRequest registirationRequest) throws Exception {
		Boolean result = userServiceImp.deleteUser(registirationRequest);
		return ResponseEntity.ok(result);
	}
	

	@GetMapping("/getDistictDistrict")
	public staticReportBean getDistictDistrict(@RequestParam("stateId") String stateId) throws JsonProcessingException {
		
		QueryResult qrObj = nativeRepository.executeQueries("select distinct \n"
				+ "district_id ,\n"
				+ "state_id,\n"
				+ "district_name,\n"
				+ "udise_district_code,\n"
				+ "udise_state_code\n"
				+ "from mst_district_report md \n"
				+ "where state_id ="+stateId);
		
		staticReportBean.setColumnName(qrObj.getColumnName());
		staticReportBean.setRowValue(qrObj.getRowValue());
		staticReportBean.setColumnDataType(qrObj.getColumnDataType());
		staticReportBean.setStatus("1");
		return staticReportBean;
	}
	

	@GetMapping("/getState")
	public List<StateMaster> getState() throws JsonProcessingException {
		log.info("State requested data ");
//// System.out.println("in state");
		return locationServiceImpl.getState();
	}
	
//	@GetMapping("/getStateListByYear")
	@RequestMapping(value = "/getStateListByYear", method = RequestMethod.POST,consumes = MediaType.TEXT_PLAIN_VALUE)
	public StaticReportBeans getStateListByYear(@RequestBody String Year) throws JsonProcessingException {
		log.info("State requested data ");
//// System.out.println("in state");
		return locationServiceImpl.getStateListByYear(Year);
	}

	@GetMapping("/getStateById")
	public StateMaster getStateById(@RequestParam("stateId") String stateId) throws JsonProcessingException {
		log.info("State requested data ");
		// System.out.println("getStateById---->" + locationServiceImpl.findById(stateId));
		return locationServiceImpl.findById(stateId);
	}
	@GetMapping("/getStateByStateId")
	public List<StateMaster> getStateByStateId(@RequestParam("stateId") String stateId) throws JsonProcessingException {
		log.info("State requested data ");
//		// System.out.println("getStateById---->" + locationServiceImpl.getStateByStateId(stateId));
		return locationServiceImpl.getStateByStateId(stateId);
	}

	@GetMapping("/getDistrict")
	public List<DistrictMaster> getDistrict(@RequestParam("stateId") String stateId) throws JsonProcessingException {
		// log.info("State requested data ");

		return locationServiceImpl.findByUdiseStaCode(stateId,"2019-20");
	}

	@GetMapping("/getBlock")
	public List<BlockMaster> getBlock(@RequestParam("districtId") String districtId) throws JsonProcessingException {
		// log.info("State requested data ");
		
		// System.out.println("districtId--->"+districtId);

		return locationServiceImpl.getBlock(districtId);
	}

	@GetMapping("/getRoleList")
	public List<UserRoleMst> getRoleList(@RequestParam("roleId") Long roleId) {
		// log.info("Inside getRoleList -> roleId : " + roleId);
		return userRoleMstServiceImpl.getRoleList(roleId);

	}

	@GetMapping("/getRole")
	public List<UserRoleMst> getRole() throws JsonProcessingException {
		// log.info("User role requested data ");

		return userRoleMstServiceImpl.getRoleList(null);
	}

	@GetMapping("/getUserList")
	public List<User> getUserList(@RequestParam("search") String search , @RequestParam("stateId") String stateId) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));
		return userServiceImp.getUserList(search,stateId);
	}

	@GetMapping("/getUserById")
	public Optional<User> getUserById(@RequestParam("id") String search) throws JsonProcessingException {
		// System.out.println("userId--->"+search);
		return userServiceImp.getUserById(search);
	}

	
	@RequestMapping(value = "/getStateIndScore", method = RequestMethod.POST)
	public ResponseEntity<PgiResponse> getStateIndMaster(@RequestBody IndicatorRequestDto indReqDto)
			throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));

		return ResponseEntity.ok(indServiceImpl.getStateScores(indReqDto));
	}

	@GetMapping("/getCategory")
	public List<CategoryMaster> getCategory() throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));

		return masterDataServiceImpl.getCategory();
	}

	@GetMapping("/getDomain")
	public List<DomainMaster> getDomain(@RequestParam("categoryId") int categoryId) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));

		return masterDataServiceImpl.getDomain(categoryId);
	}

	@GetMapping("/getCycle")
	public List<CycleMaster> getCycle(@RequestParam("year") int year) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));

		return masterDataServiceImpl.getCycle(year);
	}

	@GetMapping("/getStateIndicator")
	public List<IndicatorMaster> getStateIndicator(@RequestParam("domainId") int domainId)
			throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));

		return indServiceImpl.findByDomainId(domainId);
	}

	@RequestMapping(value = "/getIndValueById", method = RequestMethod.POST)
	public ResponseEntity<StateIndicatorScore> getIndValueById(@RequestBody IndicatorRequestDto indReqDto)
			throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));

		return ResponseEntity.ok(indServiceImpl.getIndValueById(indReqDto));
	}

	@RequestMapping(value = "/getAllIndicator", method = RequestMethod.POST)
	public ResponseEntity<Map<String,List<StateIndicatorScores>>> getAllIndicator(@RequestBody String Year) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));
//		return null;
		 return ResponseEntity.ok(indServiceImpl.getAllIndicator(Year));
	}

	@RequestMapping(value = "/saveStateIndScore", method = RequestMethod.POST)
	public ResponseEntity<Boolean> saveStateIndScore(@RequestBody IndicatorRequestDto indReqDto) throws Exception {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));

		return ResponseEntity.ok(indServiceImpl.saveStateIndScore(indReqDto.getStateIndicatorScore()));
	}

	@RequestMapping(value = "/getDistrictQuestion", method = RequestMethod.POST)
	public List<DistrictQuestion> getDistrictQuestion(@RequestBody String DomainId) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));
// System.out.println("get district question--->"+DomainId);
		return masterDataServiceImpl.getDistrictQuestions(DomainId);
	}
	@GetMapping("/getAllDistrictQuestion")
	public List<DistrictQuestion> getAllDistrictQuestion() throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));

		return masterDataServiceImpl.getAllDistrictQuestion();
	}
	

	@GetMapping("/getTables")
	public List<TableMaster> getTables(@RequestParam("tableId") long tableId) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));

		if (tableId > 0) {
			return masterDataServiceImpl.getTablebyId(tableId);
		}
		return masterDataServiceImpl.getAllTables();
	}

	@GetMapping("/getTableField")
	public List<TableFields> getTableFields(@RequestParam("tableId") long tableId) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));

		return masterDataServiceImpl.getTableFields(tableId);
	}

	@RequestMapping(value = "/getQueryResult", method = RequestMethod.POST)
	public <T> List<T> getQueryResult(@RequestBody IndicatorRequestDto indReqDto) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));
		// System.out.println(indReqDto.getQueryModel());
		return (List<T>) indServiceImpl.getData(indReqDto.getQueryModel());
	}

	@RequestMapping(value = "/getJoinResult", method = RequestMethod.POST)
	public <T> List<T> getJoinResult(@RequestBody IndicatorRequestDto indReqDto) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));
		// System.out.println(indReqDto.getQueryModel());
		return indServiceImpl.getJoinData(indReqDto.getQueryModel());
	}

	@GetMapping(value = "/getJoinDetails")
	public List<TableJoinDetails> getJoinDetails(@RequestParam("tableId") int tableId) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));
		return masterDataServiceImpl.getJoinDetails(tableId);
	}
	
	@RequestMapping(value = "/getIndicators", method = RequestMethod.POST)
	public Map<String,Object> getIndicators(@RequestBody String DomainId) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));
		Map<String,Object> mp=new HashMap<String,Object>();
		mp.put("indicators", masterDataServiceImpl.getIndicators(Integer.parseInt(DomainId)));
		mp.put("statusCode", "APP001");
		mp.put("statusDesc", "Success");
		
//		// System.out.println("get indicator--->"+masterDataServiceImpl.getIndicators(Integer.parseInt(DomainId)));
		return mp;
//		return null;
	}
	
	@RequestMapping(value = "/saveDistLoQuestion", method = RequestMethod.POST)
	public  LearningOutcome saveDistLoQuestion(@RequestBody LearningOutcome LoData) throws JsonProcessingException {
		// System.out.println("save data--->"+LoData.getAvgLangScore5());
		return pgiImpl.saveDistLoQuestion(LoData);
	}
	
	@RequestMapping(value = "/saveDistDLQuestion", method = RequestMethod.POST)
	public  DigitalLearning saveDistDLQuestion(@RequestBody DigitalLearning DLData) throws JsonProcessingException {
		return pgiImpl.saveDistDLQuestion(DLData);
	}
	
	@RequestMapping(value = "/saveDistAQuestion", method = RequestMethod.POST)
	public  Access saveDistAQuestion(@RequestBody Access DLData) throws JsonProcessingException {
		return pgiImpl.saveDistAQuestion(DLData);
	}
	
	@RequestMapping(value = "/saveDistIFQuestion", method = RequestMethod.POST)
	public  InfraFacility saveDistIFQuestion(@RequestBody InfraFacility DLData) throws JsonProcessingException {
		return pgiImpl.saveDistIFQuestion(DLData);
	}
	
	@RequestMapping(value = "/saveDistEQuestion", method = RequestMethod.POST)
	public  Equity saveDistEQuestion(@RequestBody Equity DLData) throws JsonProcessingException {
		return pgiImpl.saveDistEQuestion(DLData);
	}
	
	@RequestMapping(value = "/saveDistGPQuestion", method = RequestMethod.POST)
	public  GovernanceProcess saveDistGPQuestion(@RequestBody GovernanceProcess DLData) throws JsonProcessingException {
		return pgiImpl.saveDistGPQuestion(DLData);
	}
	
	@RequestMapping(value = "/getLOPGI", method = RequestMethod.POST)
	public  List<LearningOutcome> getLOPGI(@RequestBody String distId) throws JsonProcessingException {
		return pgiImpl.getLOPGI(distId);
	}
	
	@RequestMapping(value = "/getDLPGI", method = RequestMethod.POST)
	public  List<DigitalLearning> getDLPGI(@RequestBody String distId) throws JsonProcessingException {
		return pgiImpl.getDLPGI(distId);
	}
	
	@RequestMapping(value = "/getAPGI", method = RequestMethod.POST)
	public  List<Access> getAPGI(@RequestBody String distId) throws JsonProcessingException {
		return pgiImpl.getAPGI(distId);
	}
	@RequestMapping(value = "/getIFPGI", method = RequestMethod.POST)
	public  List<InfraFacility> getIFPGI(@RequestBody String distId) throws JsonProcessingException {
		return pgiImpl.getIFPGI(distId);
	}
	@RequestMapping(value = "/getEPGI", method = RequestMethod.POST)
	public  List<Equity> getEPGI(@RequestBody String distId) throws JsonProcessingException {
		return pgiImpl.getEPGI(distId);
	}
	@RequestMapping(value = "/getGPPGI", method = RequestMethod.POST)
	public  List<GovernanceProcess> getGPPGI(@RequestBody String distId) throws JsonProcessingException {
		return pgiImpl.getGPPGI(distId);
	}
	
	@RequestMapping(value = "/saveDistPGI", method = RequestMethod.POST)
	public  DistrictPgi saveDistPGI(@RequestBody DistrictPgi dpData) throws JsonProcessingException {
		return pgiImpl.saveDistPGI(dpData);
	}
	
	@RequestMapping(value = "/getDistrictPGI", method = RequestMethod.POST)
	public  List<DistrictPgi> getDistrictPGI(@RequestBody String stateId) throws JsonProcessingException {
		return pgiImpl.getDistrictPGI(stateId);
	}
	
	
	@RequestMapping(value = "/getFinalPGIStatus", method = RequestMethod.POST)
	public  List<DistrictPgi> getFinalPGIStatus(@RequestBody String distCode) throws JsonProcessingException {
		return pgiImpl.getFinalPGIStatus(distCode);
	}
	
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST,consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<CustumResponse> addGroup(@RequestBody String data) throws JsonProcessingException {
		
		ObjectMapper mapperObj = new ObjectMapper();
		AddGroup addgroup=new AddGroup();
		try {
			addgroup = mapperObj.readValue(data, new TypeReference<AddGroup>() {
			});
		}catch(Exception ex) {
			
		}
		
		CustumResponse resObj=new CustumResponse();
		try {
		if(addgroup.getGroupId()>0) {
			groupServiceImpl.deleteGroup(addgroup.getGroupId());
		}
		GroupMaster gpm=new GroupMaster();
		// System.out.println("called add group");
		List<GroupMapping> finalGmObj=new ArrayList<GroupMapping>();
		// System.out.println(addgroup.getGroupId());
		List<GroupMapping> addList=addgroup.getGroupMapping();
		// System.out.println(addList.get(0).getReportName());
		gpm.setGroupName(addgroup.getGroupName());

		if(addgroup.getGroupId()>0) {
			// System.out.println("In set group");			
			gpm.setId(addgroup.getGroupId());
		}
//		
		
		GroupMaster gm=groupServiceImpl.addGroup(gpm);
		// System.out.println("final group--->"+gm.getId());
		ListIterator<GroupMapping> listIterator = addList.listIterator();
		while(listIterator.hasNext()) {
			GroupMapping gmObj= listIterator.next();
			gmObj.setGroupId(gm.getId());
			finalGmObj.add(gmObj);
		}
		
		List<GroupMapping> groupMapping=groupServiceImpl.addGroupMapping(finalGmObj);
		
		addGroup.setGroupId(gm.getId());
		addGroup.setGroupName(gm.getGroupName());
		addGroup.setGroupMapping(groupMapping);
		resObj.setStatus(1);
		resObj.setMessage("successful");
		resObj.setData(addGroup);
		return ResponseEntity.ok(resObj);
		}catch(Exception ex) {
			resObj.setStatus(0);
			resObj.setMessage("fail");
			resObj.setData(ex.getMessage());
			return ResponseEntity.ok(resObj);	
		}
//		return pgiImpl.getFinalPGIStatus(distCode);
	}
	
	@RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
	public long deleteGroup(@RequestBody AddGroup addgroup) throws JsonProcessingException {
		
		return groupServiceImpl.deleteGroup(addgroup.getGroupId());
		
	}
	
	@RequestMapping(value = "/getAllGroup", method = RequestMethod.POST)
	public List<GroupMaster> getAllGroup() throws JsonProcessingException {
		return groupServiceImpl.getAllGroup();
		
	}

	@RequestMapping(value = "/getGroupDetailsById", method = RequestMethod.POST)
	public AddGroup getGroupDetailsById(@RequestBody AddGroup addgroup) throws JsonProcessingException {
		 // System.out.println();
		
		Optional<GroupMaster> gmObj=groupServiceImpl.getGroupById(addgroup.getGroupId());
		addGroup.setGroupId(gmObj.get().getId());
		addGroup.setGroupName(gmObj.get().getGroupName());
		List<GroupMapping> gmappingObj=groupServiceImpl.getGroupMappingByGroupId(gmObj.get().getId());
		addGroup.setGroupMapping(gmappingObj);
		 return addGroup;
		
	}
	
	@RequestMapping(value = "/getStateQuestion", method = RequestMethod.POST)
	public staticReportBean getStateQuestion(@RequestBody String stateId) throws JsonProcessingException {
// System.out.println("get district question--->"+stateId);
QueryResult qrObj = nativeRepository.executeQueries("select  prf.*, psq.weight ,psq.*, psq.questiondesc as quesDesc, psq.fieldcount \n"
		+ "from pgi_re_performance prf, pgi_state_question psq\n"
		+ "where state_id = 110"
		+ "and prf.question_id = psq.questionid\n"
		+ "order by  psq.domainid , psq.questionid ");
staticReportBean.setColumnName(qrObj.getColumnName());
staticReportBean.setRowValue(qrObj.getRowValue());
staticReportBean.setColumnDataType(qrObj.getColumnDataType());
staticReportBean.setStatus("1");
return staticReportBean;
	}
	
	
	@RequestMapping(value = "/updatePerformance", method = RequestMethod.POST)
	public staticReportBean updatePerformance(@RequestBody pgiPerfomanceList listPgiPerformance) throws JsonProcessingException {
		// System.out.println("called in performance");
		// System.out.println(listPgiPerformance);
	//	// System.out.println(listPgiPerformance.getListPgiPerformance().get(5).getDomId());
		try {
			pgiImpl.updatePerformance(listPgiPerformance.getListPgiPerformance());
			staticReportBean.setStatus("1");
		}catch(Exception ex) {
			staticReportBean.setStatus("1");
			ex.printStackTrace();
		}

return staticReportBean;
	}
	
}
