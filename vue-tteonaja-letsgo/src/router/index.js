import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import UserView from "@/views/UserView.vue";
import UserLogin from "@/components/user/UserLogin.vue"
import UserRegister from "@/components/user/UserRegister.vue"

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
      component: UserView,
      children: [
        {
          path: "login",
          name: "login",
          component: UserLogin
        },
        {
          path: "register",
          name: "register",
          component: UserRegister
        }
      ]
    }
  ],
});

export default router;
