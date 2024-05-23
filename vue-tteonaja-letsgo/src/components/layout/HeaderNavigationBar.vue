<script setup>
import { RouterLink, RouterView } from 'vue-router';
import { onMounted, ref } from 'vue';
import { useMenuStore } from '@/stores/menu';
import { useMemberStore } from '@/stores/member';
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';
import { checkPass, findById } from '@/api/user';
import { jwtDecode } from 'jwt-decode';
import { httpStatusCode } from "@/util/http-status";
import Swal from 'sweetalert2';
const router = useRouter();

const menuStore = useMenuStore();
const memberStore = useMemberStore();

const { menuList } = storeToRefs(menuStore);
const { changeMenuState } = menuStore;

const { userLogout, getUserInfo, tokenRegenerate } = memberStore;
const { isLogin, isValidToken, userInfo } = storeToRefs(memberStore);

// TODO: 새로고침 시 로그인 해제 방지
const checkLogin = async () => {
    console.log(isValidToken.value);
    console.log(isLogin.value);
    let token = sessionStorage.getItem("accessToken");
    if (token !== null) {
        let decodeToken = jwtDecode(token);
        console.log(decodeToken.userId);
        await findById(
            decodeToken.userId,
            (response) => {
                console.log(response);
                if (response.status === httpStatusCode.OK) {
                    userInfo.value = response.data.userInfo;
                    console.log("이제 자자");
                    console.log(userInfo.value);
                    // changeMenuState();
                } else {
                    console.log("유저 정보 없음");
                    userLogout();
                }
            },
            async (error) => {
                console.log(error);
                console.log("g[토큰 만료되어 사용 불가능.] :");
                await tokenRegenerate();
            }
        );
    }
};


const logout = () => {
    userLogout();
    changeMenuState();
};

const checkUser = () => {
    Swal.fire({
        title: "비밀번호를 입력해주세요.",
        input: "password",
        inputAttributes: {
            autocapitalize: "off"
        },
        showCancelButton: true,
        confirmButtonText: "확인",
        cancelButtonText: "취소",
        showLoaderOnConfirm: true,
        preConfirm: async (password) => {
            try {
                await checkPass(
                    {
                        userId: userInfo.value.userId,
                        checkPassword: password
                    },
                    (response) => {
                        console.log(response.data.check);
                        if (!response.data.check) {
                            return Swal.showValidationMessage(`올바른 비밀번호가 아닙니다!`);
                        }
                        return response.data.check;
                    }, (error) => {
                        console.log(error);
                    }
                );
            } catch (error) {
                Swal.showValidationMessage(`Request failed: ${error}`);
            }
        },
        allowOutsideClick: () => !Swal.isLoading()
        }).then((result) => {
            if (result.isConfirmed) {
                router.push({ name: 'my-page' });
            }
        });
};

onMounted(() => {
    checkLogin();
})

</script>

<template>

    <nav class="navbar navbar-expand-md bg-white sticky-top">
        <div class="container-fluid">
            <RouterLink :to="{ name: 'home' }" class="navbar-brand ms-3">
                <img src="@/assets/logo.png" class="ratio" alt="..." style="width: 25%" />
            </RouterLink>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse me-3" id="navbarScroll">
                <ul class="navbar-nav ms-auto navbar-nav-scroll p-1" style="--bs-scroll-height: 100px">
                    <li class="nav-item me-3">
                        <RouterLink :to="{ name: 'home' }" class="nav-link btn-hover-effect rounded-pill">홈</RouterLink>
                    </li>
                    <li class="nav-item me-3">
                        <RouterLink :to="{ name: 'map' }" class="nav-link align-middle btn-hover-effect rounded-pill">
                            관광지 검색</RouterLink>
                    </li>
                    <li class="nav-item me-3">
                        <RouterLink :to="{ name: 'travel-list' }"
                            class="nav-link align-middle btn-hover-effect rounded-pill">
                            여행 코스</RouterLink>
                    </li>
                    <li class="nav-item me-3">
                        <RouterLink :to="{ name: 'free-list' }" class="nav-link btn-hover-effect rounded-pill">자유 게시판
                        </RouterLink>
                    </li>
                    <!-- <li class="nav-item me-3">
                        <RouterLink :to="{ name: 'login' }" class="nav-link align-middle btn-hover-effect rounded-pill">
                            로그인</RouterLink>
                    </li> -->
                    <!-- <li class="nav-item dropdown me-1">
                        <a class="nav-link dropdown-toggle btn-hover-effect rounded-pill" href="#" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            HELP DESK</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item " href="#">공지사항</a></li>
                            <li><a class="dropdown-item" href="#">FAQ</a></li>
                        </ul>
                    </li> -->
                </ul>
                <ul class="navbar-nav navbar-nav-scroll" style="--bs-scroll-height: 100px">
                    <template v-for="menu in menuList" :key="menu.routeName">
                        <template v-if="menu.show">
                            <template v-if="menu.routeName === 'logout'">
                                <li class="nav-item me-2">
                                    <router-link to="/" @click.prevent="logout"
                                        class="nav-link btn-hover-effect rounded-pill">{{
                                        menu.name
                                        }}</router-link>
                                </li>
                            </template>
                            <template v-else-if="menu.routeName === 'my-page'">
                                <li class="nav-item">
                                    <!-- <router-link :to="{ name: menu.routeName }"
                                        class="nav-link btn-hover-effect rounded-pill">{{
                                        menu.name
                                        }}</router-link> -->
                                    <button class="nav-link btn-hover-effect rounded-pill" @click="checkUser">내
                                        정보</button>
                                </li>
                            </template>
                            <template v-else>
                                <li class="nav-item">
                                    <router-link :to="{ name: menu.routeName }"
                                        class="nav-link btn-hover-effect rounded-pill">{{
                                        menu.name
                                        }}</router-link>
                                </li>
                            </template>
                        </template>
                    </template>
                </ul>
            </div>
        </div>
    </nav>
    <!-- <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Carousel</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled">Disabled</a>
                    </li>
                </ul>
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav> -->
</template>

<style scoped>
.btn-hover-effect:hover {
    background-color: lightskyblue;
    color: white;
    border-color: #004085;
    transition: background-color 0.3s ease, color 0.3s ease, border-color 0.3s ease;
}

.del-deco {
    text-decoration: none;
    color: black;
}
</style>