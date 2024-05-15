package mvc.user.model.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mvc.user.model.po.Education;
import mvc.user.model.po.Gender;
import mvc.user.model.po.Interest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Integer id; // 序號
	private String name; // 姓名
	private Integer age; // 年齡
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 接收日期格式
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") // 返回日期格式
	private Date birth; // 生日
	private String resume; // 履歷
	private Education education; // 教育程度
	private Gender gender; // 性別
	private List<Interest> interests; // 興趣
	
	public String getInterestNames() {
		if(interests!=null) {
			return interests.stream().map(Interest::getName).collect(Collectors.joining(" "));
		}
		return "";
	}
	
}
