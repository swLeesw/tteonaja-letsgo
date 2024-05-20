<script setup>
import { RouterLink } from 'vue-router';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useMemberStore } from '@/stores/member';
import { useMenuStore } from '@/stores/menu';
import { storeToRefs } from 'pinia';
import Swal from 'sweetalert2';

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin, isLoginError } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
const { changeMenuState } = useMenuStore();

const loginUser = ref({
    userId: "",
    userPassword: "",
});

const login = async () => {
    await userLogin(loginUser.value);
    let token = sessionStorage.getItem("accessToken");
    console.log(token);
    console.log("isLogin: " + isLogin.value);
    if (isLogin.value) {
        getUserInfo(token);
        changeMenuState();
        router.replace("/");
    }
};

// onMounted(() => {
//     changeMenuState();
// })
</script>

<template>

    <div class="container centered">
        <div class="row justify-content-center">
            <div class="col-sm-10 col-md-8 col-lg-6 col-xl-5">
                <RouterLink :to="{ name: 'home' }">
                    <div class="row justify-content-center mt-5">
                        <img src="@/assets/logo.png" class="img-thumnail mb-5" alt="로고"
                            style="width: 150px; height: auto;">
                    </div>
                </RouterLink>
                <div class="card shadow">
                    <div class="card-body">
                        <form>
                            <div class="form-group">
                                <label for="username"></label>
                                <input type="text" class="form-control " id="username" v-model="loginUser.userId"
                                    placeholder="🙎‍♂️ 아이디 입력" required>
                            </div>

                            <div class="form-group mb-3">
                                <label for="password"></label>
                                <input type="password" class="form-control " id="password"
                                    v-model="loginUser.userPassword" @keyup.enter="login" placeholder="🔑 비밀번호 입력"
                                    required>
                            </div>

                            <div class="form-check mb-3">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="checkbox" name="remember"> 아이디 저장
                                </label>
                            </div>

                            <div class="row justify-content-center ms-auto me-auto">
                                <button type="button" class="btn btn-primary" id="login" @click="login">여행 떠나기!</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row justify-content-center mt-4">
                    <ul class="list-group list-group-horizontal justify-content-center">
                        <li class="list-group-item border-0 p-0 me-3">
                            <a href="" class="text-decoration-none text-muted small" onclick="javascript:findpw()">비밀번호
                                찾기</a>
                        </li>
                        <div class="vr no-gutters"></div>
                        <li class="list-group-item border-0 p-0 ms-3 me-3">
                            <a href="" class="text-decoration-none text-muted small">아이디 찾기</a>
                        </li>
                        <div class="vr"></div>
                        <li class="list-group-item border-0 p-0 ms-3">
                            <RouterLink :to="{ name: 'register' }"
                                class="nav-link align-middle text-muted user-controller">회원 가입</RouterLink>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</template>

<style scoped></style>