package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * @return 
	 * InsertAdministratorFormをインスタンス化.
	 */
	@ModelAttribute
	public InsertAdministratorForm insertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	
	/**
	 * @return
	 * 管理者登録画面へ移動
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	
	
	

}
