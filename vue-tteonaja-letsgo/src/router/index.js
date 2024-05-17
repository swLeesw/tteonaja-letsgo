import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";

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
          path: "free/:id",
          name: "free-detail",
          component: () => import("@/components/board/free/FreeBoardDetail.vue"),
        },
        {
          path: "trip",
          name: "trip-list",
          component: () => import("@/components/board/trip/TripBoardList.vue"),
        },
      ],
    },
    {
      path: "/map",
      name: "map",
      component: () => import("@/views/MapView.vue"),
    },
  ],
});

export default router;
