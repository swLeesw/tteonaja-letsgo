import { createApp } from "vue";
import { createPinia } from "pinia";
import { useKakao } from "vue3-kakao-maps/@utils";

import App from "./App.vue";
import router from "./router";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

const app = createApp(App);

useKakao("c3d66eda5b359362432348cc2093c184");
app.use(createPinia());
app.use(router);

app.mount("#app");
