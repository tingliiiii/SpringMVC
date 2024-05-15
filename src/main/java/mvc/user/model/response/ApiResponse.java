package mvc.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

	// 前後端分離的前端，回傳資料結構有統一樣式
	private Boolean state; // 狀態
	private String message; // 訊息
	private T data; // 實際資料（T是任意泛型）
}
