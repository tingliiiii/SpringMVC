package mvc.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.user.dao.BaseDataDao;
import mvc.user.model.dto.UserDto;
import mvc.user.model.po.Education;
import mvc.user.model.po.Gender;
import mvc.user.model.po.Interest;
import mvc.user.model.po.Statistics;
import mvc.user.model.po.User;
import mvc.user.service.UserService;

/**
 * 定義 URI 服務 
 * ------------------------------------------------------------------
 * Method |  URI    | Description
 * ------------------------------------------------------------------
 *  GET   | /user   | 取得所有使用者資料
 *  GET   | /user/1 | 根據 userId 取得單筆使用者資料提供修改 
 *  POST  | /user/  | 新增使用者資料,會自動夾帶 User 物件資料上來（共用表單所以要加/） 
 *  PUT   | /user/1 | 修改指定 userId 的使用者資料,會自動夾帶要修改的 User 物件資料上來 
 * DELETE | /user/1 | 刪除指定 userId 的使用者紀錄
 * ------------------------------------------------------------------ 
 * URL 範例: GET http://localhost:8080/SpringMVC/mvc/user
 */

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BaseDataDao baseDataDao;

	@GetMapping
	// model: 欲將給 jsp 的資料要放在 model 容器中
	public String queryAllUsers(@ModelAttribute User user, Model model) {
		// 基本要傳給 user.jsp 的資訊
		addBasicModel(model);
		// @ModelAttribute User user = model.addAttribute("user", user);
		return "user/user";
		// 完整 jsp(view) 路徑 = "/WEB-INF/view/user/user.jsp";
		// 因為在 springmvc-servlet.xml
		// 已經定義: prefix = "/WEB-INF/view/"
		// suffix = ".jsp"
		// 所以只要寫成 "user/user"
	}

	@GetMapping("/{userId}")
	public String getUser(@PathVariable("userId") Integer userId, Model model) {
		User user = userService.getUser(userId);
		// 將 users 資料傳給 jsp
		model.addAttribute("user", user);
		// 將 _method="PUT" 傳給 jsp
		model.addAttribute("_method", "PUT");
		// 基本要傳給 user.jsp 的資訊
		addBasicModel(model);
		return "user/user";
	}

	@PostMapping("/")
	public String createUser(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			// 基本要傳給 user.jsp 的資訊
			addBasicModel(model);
			// 有錯誤的 user 資料也一併帶入給表單使用(內含錯誤的原因)
			model.addAttribute("user", user);
			return "user/user";
		}

		userService.addUser(user);
		return "redirect:/mvc/user";
	}

	@PutMapping("/{userId}")
	public String updateUser(@PathVariable("userId") Integer userId, @Valid User user, BindingResult result,
			Model model) {
		// 判斷驗證是否通過
		if (result.hasErrors()) {
			// 基本要傳給 user.jsp 的資訊
			addBasicModel(model);
			// 有錯誤的 user 資料一併帶入給表單使用（包括錯誤原因）
			model.addAttribute("user", user);
			// 補 id（因為表單裡的 user 資料沒有包括 id）
			user.setId(userId);
			// 重要！要傳 PUT 回去
			model.addAttribute("_method", "PUT");
			return "user/user";
		}

		userService.updateUser(userId, user);
		return "redirect:/mvc/user";
	}

	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) {
		userService.deleteUser(userId);
		return "redirect:/mvc/user";
	}

	// 基本要傳給首頁 user.jsp 的資料
	private void addBasicModel(Model model) {

		List<UserDto> users = userService.findUserDtos();
		List<Education> educations = baseDataDao.findAllEducations(); // 所有學歷
		List<Gender> genders = baseDataDao.findAllGenders(); // 所有性別
		List<Interest> interests = baseDataDao.findAllInterests(); // 所有興趣
		List<Statistics> genderStatistics = userService.queryStatistics("Gender");
		List<Statistics> educationStatistics = userService.queryStatistics("Education");

		model.addAttribute("users", users);
		model.addAttribute("educations", educations);
		model.addAttribute("genders", genders);
		model.addAttribute("interests", interests);
		model.addAttribute("genderStatistics", genderStatistics);
		model.addAttribute("educationStatistics", educationStatistics);

	}
}
