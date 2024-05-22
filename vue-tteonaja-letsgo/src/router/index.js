import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import Swal from "sweetalert2";

const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;

  let token = sessionStorage.getItem("accessToken");

  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }
  if (userInfo.value === null) {
    Swal.fire({
      position: "top",
      icon: "info",
      title: "로그인 후 이용 가능합니다.",
      showConfirmButton: false,
      timer: 1500,
    });
    next({ name: "login" });
  } else {
    next();
  }
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/user",
      name: "user",
      component: () => import("@/views/UserView.vue"),
      children: [
        {
          path: "login",
          name: "login",
          component: () => import("@/components/user/UserLogin.vue"),
        },
        {
          path: "register",
          name: "register",
          component: () => import("@/components/user/UserRegister.vue"),
        },
        {
          path: "info",
          name: "my-page",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/user/UserInfo.vue"),
        },
      ],
    },
    {
      path: "/board",
      name: "board",
      component: () => import("@/views/BoardView.vue"),
      children: [
        {
          path: "free",
          name: "free-list",
          component: () => import("@/components/board/free/FreeBoardList.vue"),
        },
        {
          path: "free/:id/:commentNum",
          name: "free-detail",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/board/free/FreeBoardDetail.vue"),
        },
        {
          path: "free/write",
          name: "free-write",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/board/free/FreeBoardWrite.vue"),
        },
        {
          path: "free/modify:id",
          name: "free-modify",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/board/free/FreeBoardModify.vue"),
        },
        {
          path: "travel",
          name: "travel-list",
          component: () => import("@/components/board/travel/TravelBoardList.vue"),
        },
        {
          path: "travel/:id/:commentNum",
          name: "travel-detail",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/board/travel/TravelBoardDetail.vue"),
        },
        {
          path: "travel/write",
          name: "travel-write",
          beforeEnter: onlyAuthUser,
          component: () =>
            import("@/components/board/travel/TravelBoardWrite.vue"),
        },
        {
          path: "travel/modify:id",
          name: "travel-modify",
          beforeEnter: onlyAuthUser,
          component: () =>
            import("@/components/board/travel/TravelBoardModify.vue"),
        },
      ],
    },
    {
      path: "/map/:attractionName?",
      name: "map",
      component: () => import("@/views/MapView.vue"),
      children: [
        {
          path: "mapReview/:id",
          name: "map-review",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/attraction/AttractionReview.vue"),
        },
        {
          path: "mapReviewWrite/:id",
          name: "map-review-write",
          component: () => import("@/components/attraction/AttractionReviewWrite.vue"),
        },
      ],
    },
  ],
});

export default router;
