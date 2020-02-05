package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	/**
	 * 管理者用フォームの作成.
	 *
	 * @return InsertAdministratorFormをインスタンス化.
	 * 
	 */

	@ModelAttribute
	public InsertAdministratorForm insertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	/**
	 * 
	 * 管理者登録画面へ移動.
	 * 
	 * @return 管理者画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * 管理者情報を登録する.
	 * 
	 * @param form  管理者用フォーム
	 * @param model リクエストスコープ
	 * @return administratorServiseのInsert()メソッド
	 */

	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form, Model model) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		model.addAttribute("administrator",administrator);

		return "redirect:/";

	}

}
