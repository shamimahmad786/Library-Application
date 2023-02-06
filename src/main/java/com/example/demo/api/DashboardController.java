package com.example.demo.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.DashoardCtrl.CTRL)
@CrossOrigin(origins = {"http://10.25.26.251:4200","http://localhost:1000","http://demo.sdmis.gov.in","http://localhost:4200","http://pgi.seshagun.gov.in","https://pgi.udiseplus.gov.in","http://pgi.udiseplus.gov.in","https://demopgi.udiseplus.gov.in","https://dashboard.seshagun.gov.in/"}, allowedHeaders = "*",allowCredentials = "true")
public class DashboardController {

}
