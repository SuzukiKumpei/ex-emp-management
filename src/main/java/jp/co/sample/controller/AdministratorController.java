package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 
 * 管理者情報を扱うコントローラ.
 * @author suzukikunpei
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private HttpSession session;

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
	 * @return ログイン画面
	 */

	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		//model.addAttribute("administrator",administrator);

		return "redirect:/";

	}
	
	/**
	 * ログイン画面を表示する
	 * @return ログインパラメータ
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	/**
	 * ログインをする処理
	 * @param form
	 * @return 従業員一覧
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator  administratorName = administratorService.login(form.getMailAddress(), form.getPassword());
		if(administratorName == null) {
			model.addAttribute("errorMessage","メールアドレスまたはパスワードが不正です。");
			return "redirect:/";
		}else {
			session.setAttribute("administratorName", administratorName);
			return "forward:/employee/showList";
		}
		
		
	}
	
	
	
	
	
	
	

}
