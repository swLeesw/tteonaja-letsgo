import { ref } from "vue";
import { defineStore } from "pinia";

export const useMenuStore = defineStore("menuStore", () => {
    const menuList = ref([
      { name: "로그인", show: true, routeName: "login" },
      { name: "로그아웃", show: false, routeName: "logout" },
      { name: "내정보", show: false, routeName: "my-page" },
    ]);

    const changeMenuState = () => {
      // item의 모든 속성을 포함하고, show 속성을 현재 값의 반대로 설정하는 것
      menuList.value = menuList.value.map((item) => ({
        ...item,
        show: !item.show,
      }));
    };
    return {
      menuList,
      changeMenuState,
    };
  },
  {
  persist: {
      key: 'userMenu',
      storage: sessionStorage,
  }}
);
