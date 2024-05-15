
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

// 資料渲染（資料所在地，目標位置，渲染方法）
const fetchAndRenderData = async(url, containerId, renderFn) => {

}

// 待 DOM 加載完成之後再執行
document.addEventListener("DOMContentLoaded", async () => {

  // 加上 await 關鍵字等待 loadHTML 函數完成才會進行下一個程序
  await loadHTML('/user/user-form.html', 'user-form-container');
  await loadHTML('/user/user-list.html', 'user-list-container');

  // 資料渲染（fetch取資料＋渲染）
  fetchAndRenderData('/mvc/rest/user', 'user-list-body', renderUser);
});