
// 透過 $ 來替代 document.getElementById() 方法
const $ = (id) => document.getElementById(id);

// 定義一個非同步函數來加載 HTML 內容
const loadHTML = async (url, containerId) => {
  const fullUrl = 'http://localhost:8080/SpringMVC' + url;
  try {
    const response = await fetch(fullUrl); // 等待 fetch 請求完成
    if (!response.ok) {
      throw new Error('HTTP error, status = ' + response.status);
    }
    const data = await response.text(); // 等待回應本文內容
    // document.getElementById(containerId).innerHTML
    $(containerId).innerHTML = data; // 將所得到的本文內容加入到指定容器中
  } catch (e) {
    console.error(e);
    $(containerId).innerHTML = '無法加載資料';
  }
};

// 渲染 User 資料配置
const renderUser = ({id,name,gender,age,birth,education,interestNames,interests,resume}) => `
			<tr>
				<td>${id}</td><td>${name}</td><td>${gender.name}</td><td>${age}</td>
				<td>${birth}</td>
				<td>${education.name}</td><td>${interestNames}</td><td>${resume}</td>
				<td>修改</td><td>刪除</td>
			</tr>`;

// 資料渲染（資料所在地，目標位置，渲染方法）
const fetchAndRenderData = async (url, containerId, renderFn) => {
 const fullUrl = 'http://localhost:8080/SpringMVC' + url;
	try {
		const response = await fetch(fullUrl); // 等待 fetch 請求完成
		const {state, message, data} = await response.json(); // 等待回應本文內容
		console.log(state, message, data);
		// console.log(renderFn(data[0]));
		// console.log(renderFn(data[1]));
		// console.log(renderFn(data[2]));
		// $(containerId).innerHTML = renderFn(data[0])+renderFn(data[1])+renderFn(data[2]);
		// 三元運算元：如果data是陣列，就使用 map 多筆渲染，否則單筆
		$(containerId).innerHTML = Array.isArray(data) ? data.map(renderFn).join('') : renderFn(data);
		
		
	} catch(e) {
		console.error(e);
		$(containerId).innerHTML = '無法加載資料';
	} 
}

// 待 DOM 加載完成之後再執行
document.addEventListener("DOMContentLoaded", async () => {

  // 加上 await 關鍵字等待 loadHTML 函數完成才會進行下一個程序
  await loadHTML('/user/user-form.html', 'user-form-container');
  await loadHTML('/user/user-list.html', 'user-list-container');

  // 資料渲染（fetch取資料＋渲染）
  fetchAndRenderData('/mvc/rest/user', 'user-list-body', renderUser);
});