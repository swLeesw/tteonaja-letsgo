import { ref } from "vue";
import { useRouter } from "vue-router";
import { defineStore } from "pinia";
import { jwtDecode } from "jwt-decode";

import { userConfirm, findById, tokenRegeneration, logout } from "@/api/user";
import { httpStatusCode } from "@/util/http-status";
import { useMenuStore } from "./menu";
import Swal from "sweetalert2";


// defineStore에 의해 store가 정의됨
// 할당 변수 이름은  use{store이름}Store로 설정, 규칙임
// memberStore는 pinia에서 store와 devtools를 연결하는데 사용
export const useMemberStore = defineStore("memberStore", () => {
    // 아래는 store를 정의하는 문법 중 setup store 문법
    // ref()는 state 속성
    // computed()는 getters
    // function()는 actions
    // 위의 요소들은 반드시 반환해야 정상동작함
    // 감시자를 생성하고 컴포저블을 자유롭게 사용할 수 있어 옵션 스토어보다 유연하다
    // 하지만 SSR에서 사용할 떄 컴포저블 사용이 더 복잡해질 수 있다.

    const router = useRouter();

    const isLogin = ref(false);
    const isLoginError = ref(false);
    const userInfo = ref(null);
    const isValidToken = ref(false);

    const userLogin = async (loginUser) => {
      console.log(loginUser);
      await userConfirm(
        loginUser,
        (response) => {
          if (response.status === httpStatusCode.CREATE) {
            console.log("로그인 성공");
            console.log(response);
            let { data } = response;
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            isLogin.value = true;
            isLoginError.value = false;
            isValidToken.value = true;
            sessionStorage.setItem("accessToken", accessToken);
            sessionStorage.setItem("refreshToken", refreshToken);
          }
        },
        async (error) => {
          const errorMessage = error.response.data.message
          await Swal.fire({
            position: "top",
            icon: "error",
            titleText: errorMessage,
            showConfirmButton: false,
            timer: 1500
          });
          console.log("로그인 실패");
          console.log(error)
          console.log(error.response.data.message)
          isLogin.value = false;
          isLoginError.value = true;
          isValidToken.value = false;
          console.error();
        }
      );
    };

    const getUserInfo = async (token) => {
      let decodeToken = jwtDecode(token);
      console.log(decodeToken);
      console.log(decodeToken.userId);
      await findById(
        decodeToken.userId,
        (response) => {
          if (response.status === httpStatusCode.OK) {
            userInfo.value = response.data.userInfo;
          } else {
            console.log("유저 정보 없음");
          }
        },
        async (error) => {
          console.error(
            "g[토큰 만료되어 사용 불가능.] :",
            error.response.status,
            error.response.statusText
          );
          isValidToken.value = false;
          await tokenRegenerate();
        }
      );
    };

  const tokenRegenerate = async () => {
    console.log("토큰 재생성 위한 유저 정보");
    console.log(userInfo.value);
      await tokenRegeneration(
        JSON.stringify(userInfo.value),
        (response) => {
          if (response.status === httpStatusCode.CREATE) {
            console.log("토큰 재생성");
            console.log(response)
            let accessToken = response.data["access-token"];
            sessionStorage.setItem("accessToken", accessToken);
            isValidToken.value = true;
          }
        },
        async (error) => {
          // 401: RefreshToken 기간 만료
          if (error.response.status === httpStatusCode.UNAUTHORIZED) {
            // logout을 통해서 DB에 저장된 RefreshToken 제거.
            console.log("여기가 안돼?")
            console.log(userInfo.value.userId)
            await logout(
              userInfo.value.userId,
              (response) => {
                if (response.status === httpStatusCode.OK) {
                  sessionStorage.removeItem("accessToken")
                  sessionStorage.removeItem("refreshToken")
                  sessionStorage.removeItem("recentUseInfo")
                  sessionStorage.removeItem("userMenu")
                  console.log("RefreshToken 제거 성공");
                } else {
                  console.log("RefreshToken 제거 실패");
                }
                alert("세션이 만료되었습니다. 다시 로그인해주세요");
                isLogin.value = false;
                userInfo.value = null;
                isValidToken.value = false;
                const menuStore = useMenuStore();
                console.log(menuStore)
                menuStore.changeMenuState();
                router.replace({ name: "login" });
              },
              (error) => {
                console.error(error);
                isLogin.value = false;
                userInfo.value = null;
              }
            );
          }
        }
      );
    };

    const userLogout = async () => {
      // await getUserInfo(sessionStorage.getItem(['accessToken']))
      console.log("로그아웃 아이디: " + userInfo.value.userId);
      await logout(
        userInfo.value.userId,
        (response) => {
          if (response.status === httpStatusCode.OK) {
            isLogin.value = false;
            userInfo.value = null;
            isValidToken.value = false;
            sessionStorage.removeItem("recentUseInfo")
            sessionStorage.removeItem("userMenu")
            sessionStorage.removeItem("accessToken");
            sessionStorage.removeItem("refreshToken");
          } else {
            console.error("유저 정보 없음");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    };
    return {
      isLogin,
      isLoginError,
      userInfo,
      isValidToken,
      userLogin,
      userLogout,
      getUserInfo,
      tokenRegenerate,
    };
  },
  {
    persist: {
      key: 'recentUseInfo',
      storage: sessionStorage,
      paths:['userInfo']
  }}
);

