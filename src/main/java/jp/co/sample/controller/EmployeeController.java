package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を検索する処理をするコントローラ.
 * @author suzukikunpei
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 従業員一覧を出力.
	 * @param model
	 * @return 従業員一覧
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		
		model.addAttribute("employeeList",employeeService.showList());
		return "employee/list";
		
	}
	
	/**
	 * 従業員情報を更新する.
	 * @return 従業員情報
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
		
	}

	/**
	 * @param id 従業員ID
	 * @param model リクエストスコープ
	 * @return　従業員詳細情報
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id,Model model) {
		model.addAttribute("employee",employeeService.showDetail(Integer.parseInt(id)));
		
		return "employee/detail";
		
	}
	
}
