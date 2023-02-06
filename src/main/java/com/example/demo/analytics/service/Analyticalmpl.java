package com.example.demo.analytics.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.analytics.modal.PgiMsColumn;
import com.example.demo.analytics.modal.PgiMsColumnMapping;
import com.example.demo.analytics.modal.PgiMsSummarized;
import com.example.demo.analytics.modal.PgiMsTableJoin;
import com.example.demo.analytics.modal.PgiMsTableList;
import com.example.demo.analytics.modal.analyticsOperator;
import com.example.demo.analytics.repository.PgiMsColumnMappingRepository;
import com.example.demo.analytics.repository.PgiMsColumnRepository;
import com.example.demo.analytics.repository.PgiMsSummarizedRepository;
import com.example.demo.analytics.repository.PgiMsTableJoinRepository;
import com.example.demo.analytics.repository.PgiMsTableListRepository;
import com.example.demo.analytics.repository.analyticsOperatorRepository;
import com.example.demo.bean.QueryResult;
import com.example.demo.bean.filterData;
import com.example.demo.bean.queryLists;
import com.example.demo.bean.staticReportBean;
import com.example.demo.report.repository.NativeRepository;
import com.example.demo.report.repository.ReportNameRepository;
import com.example.demo.report.service.ReportImpl;
import com.example.demo.util.CommonMethod;

@Service
public class Analyticalmpl {

	@Autowired
	PgiMsTableListRepository pgiMsTableListRepository;
	@Autowired
	PgiMsColumnRepository pgiMsColumnRepository;
	@Autowired
	PgiMsTableJoinRepository pgiMsTableJoinRepository;
	@Autowired
	NativeRepository nativeRepository;
	@Autowired
	staticReportBean staticReportBean;
	@Autowired
	analyticsOperatorRepository analyticsOperatorRepository;
	@Autowired
	CommonMethod commonMethod;
	@Autowired
	PgiMsSummarizedRepository pgiMsSummarizedRepository;
	@Autowired
	PgiMsColumnMappingRepository pgiMsColumnMappingRepository;

	public List<PgiMsTableList> tableList() {

		return pgiMsTableListRepository.findAll();

	}

	public List<PgiMsColumn> columnList(int tableId) throws Exception {
		// System.out.println("table list");
		// return analyticalmpl.tableList();
		return pgiMsColumnRepository.findByTableIdOrderByColumnViewAsc(tableId);

	}

	public List<PgiMsTableJoin> dependTable(int tableId) throws Exception {
		// System.out.println("table list");
		// return analyticalmpl.tableList();
		return pgiMsTableJoinRepository.findByTableId(tableId);
	}

	public staticReportBean getSearchQuery(@RequestBody queryLists queryLists) throws Exception {
		String tableName = " ";
		String previousTable = null;
		int previousTableId = 0;
		String currentTable;
		int currentTableId = 0;
		String filterCondition = "";
		String joinCondition = "";
		String aggrigateColumn = "";
		String groupData = "";
		String groupDataColumn="";
		List<String> tableList = new ArrayList<String>();
		List<String> columnList = new ArrayList<String>();
		List<Object> columnView = new ArrayList<Object>();
		List<String> whereConditions = new ArrayList<String>();
		List<String> joinByKey = new ArrayList<String>();
		String finalQuery = "";
		String columnName = "";
		// String singleCoulmnFlag="";
		try {
			for (int i = 0; i < queryLists.getTablesList().size(); i++) {
				// System.out.println("List of table---->" + queryLists.getTablesList().get(i).getTableName());
				if (i > 0) {
					previousTable = queryLists.getTablesList().get(i - 1).getTableName();
					previousTableId = queryLists.getTablesList().get(i - 1).getTableId();
				}
				currentTable = queryLists.getTablesList().get(i).getTableName();
				currentTableId = queryLists.getTablesList().get(i).getTableId();

				if (i > 0) {
					// // System.out.println("previous table--->" + previousTable);
					// // System.out.println("current table--->" + currentTable);
					// tableList.add(" " + previousTable + " join " + currentTable);
					QueryResult qrObj = nativeRepository
							.executeQueries("select * from pgi_ms_table_join where table_id=" + previousTableId
									+ " and joined_table_id=" + currentTableId + "");
					joinCondition += (String) qrObj.getRowValue().get(0).get("table_name") + "."
							+ (String) qrObj.getRowValue().get(0).get("table_mapping_column") + "="
							+ (String) qrObj.getRowValue().get(0).get("join_table_name") + "."
							+ (String) qrObj.getRowValue().get(0).get("join_table_mapping_column") + " and ";
				}
				tableList.add(currentTable);
				// Column List
				// System.out.println("table column");
				// System.out.println("size of first array-->" + queryLists.getColumnsList().get(i).size());
				for (int j = 0; j < queryLists.getColumnsList().get(i).size(); j++) {

					// System.out.println("column Name--->" + queryLists.getColumnsList().get(i).get(j).getId());
					QueryResult qrObj = nativeRepository
							.executeQueries("select * from pgi_ms_column_list where table_id="
									+ queryLists.getTablesList().get(i).getTableId() + " and column_id="
									+ queryLists.getColumnsList().get(i).get(j).getId());
					// System.out.println((String) qrObj.getRowValue().get(0).get("column_name") + "column_type--->"
						//	+ (String) qrObj.getRowValue().get(0).get("column_type"));

					if ((qrObj.getRowValue().get(0).get("column_type").equals("F"))) {
						columnList.add((String) qrObj.getRowValue().get(0).get("column_name") + " as "+'"'
								+ (String) qrObj.getRowValue().get(0).get("column_view") +'"');
						columnView.add((String) qrObj.getRowValue().get(0).get("column_view"));
					} else {
						columnList.add(currentTable + "." + (String) qrObj.getRowValue().get(0).get("column_name"));
						columnView.add((String) qrObj.getRowValue().get(0).get("column_view"));
					}
					// joinByKey.add()
				}

			}

			for (int i = 0; i < queryLists.getAggrigationDatas().size(); i++) {
				if (queryLists.getAggrigationDatas().size() > 0) {
					// System.out.println("For get argument type");
					// System.out.println(
				//			"Column Type--->" + queryLists.getAggrigationDatas().get(i).getAggrigationColumnType());
					// System.out.println("queryLists.getAggrigationDatas().get(i).getAggrigationColumn()--->"+queryLists.getAggrigationDatas().get(i).getAggrigationColumn());

					aggrigateColumn += commonMethod.returnAggrigateColumn(
							queryLists.getAggrigationDatas().get(i).getAggrigationName(),
							queryLists.getAggrigationDatas().get(i).getAggrigationColumn(),
							queryLists.getAggrigationDatas().get(i).getAggrigationTable(),
							queryLists.getAggrigationDatas().get(i).getAggrigationColumnType())
							+ (!queryLists.getAggrigationDatas().get(i).getAggrigationColumnType().equalsIgnoreCase("F")
									? " as " + queryLists.getAggrigationDatas().get(i).getAggrigationColumn()
									: " ")
							+ " , ";
				}
			}

			// System.out.println("all aggrigate function--->" + aggrigateColumn);

			for (String col : columnList) {
				columnName += col + " , ";
			}
			for (String table : tableList) {
				tableName += table + " , ";
			}

			// GroupBy

			for (String group : queryLists.getGroupDatas()) {
				// System.out.println("group---"+group);
				groupData += group + " , ";
			}
			
			for (String group : queryLists.getGroupDatas()) {
				// System.out.println("group-->"+group);
				if(group.contains("mastervalueofcolumn")) {
					groupDataColumn += group +" as "+'"'+group.split("\\,")[0].split("\\.")[1]+"0" +'"'+ " , ";
				}else {
					groupDataColumn += group +" as "+'"'+group.split("\\.")[1]+"0" +'"'+ " , ";	
				}
				
			}
			
//			for(int i=0;i<queryLists.getGroupDatas().size();i++) {
//				if(queryLists.getGroupDatas().get(i))
//			}
			
			// System.out.println("groupDataColumn---------"+groupDataColumn);
			
			

			// System.out.println("queryLists group data---->" + queryLists.getGroupDatas());

			// System.out.println("final join condition---->" + joinCondition);

			// System.out.println("filter data---->" + queryLists.getFilterData());

			Iterator itr = queryLists.getFilterData().iterator();

			while (itr.hasNext()) {
				filterData fd = (filterData) itr.next();
				// System.out.println("fd.getOptValue()---->" + fd.getOptValue());
				if (fd.getOperator().equalsIgnoreCase("in")) {
					if (fd.getColumnType().equalsIgnoreCase("F")) {
						String functionColumn = fd.getConditionColumn().substring(
								fd.getConditionColumn().indexOf("(") + 1, fd.getConditionColumn().indexOf(","));
						// System.out.println("functionColumn------->" + functionColumn);
						filterCondition += functionColumn
								+ " " + fd.getOperator() + commonMethod
										.inReturnValue(fd.getOptValue(), fd.getColumnDataType()).replaceAll(" , $", "")
								+ " and ";
					} else {
						filterCondition += fd.getConditionTable() + "." + fd.getConditionColumn() + " "
								+ fd.getOperator() + commonMethod
										.inReturnValue(fd.getOptValue(), fd.getColumnDataType()).replaceAll(" , $", "")
								+ " and ";
					}
				}else if(fd.getOperator().equalsIgnoreCase("LIKE")) {
					if (fd.getColumnType().equalsIgnoreCase("F")) {
						String functionColumn = fd.getConditionColumn().substring(
								fd.getConditionColumn().indexOf("(") + 1, fd.getConditionColumn().indexOf(","));
						filterCondition +=functionColumn +" " + fd.getOperator()
						+ commonMethod.returnValue(fd.getOptValue(), fd.getColumnDataType()) + " and ";
					}else {
						filterCondition += fd.getConditionTable() + "." + fd.getConditionColumn()+" " + fd.getOperator()
						+ commonMethod.returnValueForLike(fd.getOptValue(), fd.getColumnDataType()) + " and ";
					}
				} else {
					if (fd.getColumnType().equalsIgnoreCase("F")) {
						String functionColumn = fd.getConditionColumn().substring(
								fd.getConditionColumn().indexOf("(") + 1, fd.getConditionColumn().indexOf(","));
						filterCondition +=functionColumn +" " + fd.getOperator()
						+ commonMethod.returnValue(fd.getOptValue(), fd.getColumnDataType()) + " and ";
					}else {
						filterCondition += fd.getConditionTable() + "." + fd.getConditionColumn()+" " + fd.getOperator()
						+ commonMethod.returnValue(fd.getOptValue(), fd.getColumnDataType()) + " and ";
					}
					
					
				}
			}
			// System.out.println("filterCondition--------->" + filterCondition);
			// System.out.println("groupData---->"+groupData);

			if (aggrigateColumn != "") {
				// System.out.println("1");
				if (previousTableId == 0) {
					if (filterCondition != "") {
						// System.out.println("2");
						finalQuery = " Select " + aggrigateColumn.replaceAll(", $", "")
								+ (groupDataColumn != "" ? " , " + groupDataColumn.replaceAll(" , $", "") : " ") + " from "
								+ tableName.replaceAll(", $", "") + " where " + filterCondition.replaceAll("and $", "")
								+ "  " + (groupData != "" ? " group by " + groupData.replaceAll(" , $", "") : " ");
					} else {
						// System.out.println("3");
						finalQuery = " Select " + aggrigateColumn.replaceAll(", $", "")
								+ (groupDataColumn != "" ? " , " + groupDataColumn.replaceAll(" , $", "") : " ") + " from "
								+ tableName.replaceAll(", $", "") + "  "
								+ (groupData != "" ? " group by " + groupData.replaceAll(" , $", "") : " ");
					}
				} else {
					if (filterCondition != "") {
						finalQuery = " Select " + aggrigateColumn.replaceAll(", $", "")
								+ (groupDataColumn != "" ? " , " + groupDataColumn.replaceAll(" , $", "") : " ") + " from "
								+ tableName.replaceAll(", $", "") + " where " + filterCondition.replaceAll("and $", "")
								+ " and " + joinCondition.replaceAll("and $", "") + "  "
								+ (groupData != "" ? " group by " + groupData.replaceAll(" , $", "") : " ");
					} else {
						finalQuery = " Select " + aggrigateColumn.replaceAll(", $", "")
								+ (groupDataColumn != "" ? " , " + groupDataColumn.replaceAll(" , $", "") : " ") + " from "
								+ tableName.replaceAll(", $", "") + " where " + joinCondition.replaceAll("and $", "")
								+ "  " + (groupData != "" ? " group by " + groupData.replaceAll(" , $", "") : " ");
					}
				}

			} else {
				if (previousTableId == 0) {
					if (filterCondition != "") {
						finalQuery = " Select " + columnName.replaceAll(", $", "") + " from "
								+ tableName.replaceAll(", $", "") + " where " + filterCondition.replaceAll("and $", "")
								+ " limit 100 ";
					} else {
						finalQuery = " Select " + columnName.replaceAll(", $", "") + " from "
								+ tableName.replaceAll(", $", "")
						+ " limit 100 ";
					}
				} else {
					if (filterCondition != "") {
						finalQuery = " Select " + columnName.replaceAll(", $", "") + " from "
								+ tableName.replaceAll(", $", "") + " where " + filterCondition.replaceAll("and $", "")
								+ " and " + joinCondition.replaceAll("and $", "")
						+ " limit 100 ";
					} else {
						finalQuery = " Select " + columnName.replaceAll(", $", "") + " from "
								+ tableName.replaceAll(", $", "") + " where " + joinCondition.replaceAll("and $", "")
								+ " limit 100 ";
					}
				}
			}

			
			// // System.out.println("Summarized data ----->" +
			// queryLists.getAggrigationDatas().size());
			// System.out.println("finalQuery--->" + finalQuery);
			QueryResult qrObj = nativeRepository.executeQueries(finalQuery);
			staticReportBean.setColumnName(qrObj.getColumnName());
			staticReportBean.setRowValue(qrObj.getRowValue());
			staticReportBean.setColumnView(columnView);
			staticReportBean.setColumnDataType(qrObj.getColumnDataType());
			staticReportBean.setStatus("1");
			// iterate tabsleList and column List
			// System.out.println("finalQuery--->" + finalQuery);
		} catch (Exception ex) {
			ex.printStackTrace();
			staticReportBean.setStatus("0");
			staticReportBean.setErrorMessage(ex.getMessage());
	
		}

		// // System.out.println("Make Query");

		return staticReportBean;
	}

	// getOperator

	public List<analyticsOperator> getOperator() {
		// System.out.println("table list");
		return analyticsOperatorRepository.findAll();
	}

	public List<PgiMsSummarized> getSummarized() throws Exception {
		return pgiMsSummarizedRepository.findAll();

	}

	public List<PgiMsColumnMapping> getMasterData(int colId) throws Exception {
		return pgiMsColumnMappingRepository.findByColumnIdOrderByMasterDetailsAsc(colId);
	}

}
