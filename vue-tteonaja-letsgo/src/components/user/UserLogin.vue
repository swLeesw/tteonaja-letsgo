<script setup>
import { RouterLink } from 'vue-router';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useMemberStore } from '@/stores/member';
import { useMenuStore } from '@/stores/menu';
import { storeToRefs } from 'pinia';
import Swal from 'sweetalert2';
import { checkCerticationNumber, requestMail } from '@/api/user';

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin, isLoginError } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
const { changeMenuState } = useMenuStore();

const loginUser = ref({
    userId: "",
    userPassword: "",
});

const findPasswordId = ref("")
const certificationNumber = ref("")
const certificationState = ref(false)
const findPassword = ref("")
const isSendEmail = ref(true)
const isCertification = ref(false)

const sendEmail = async () => {
    await requestMail(
        {
            userId: findPasswordId.value
        },
        (response) => {
            if (response.data.check) {
                isSendEmail.value = true
            } else {
                isSendEmail.value = false
            }
        },
        (error) => {
            console.log(error);
            Swal.fire({
                position: "top",
                icon: "error",
                title: "서버에 문제가 발생했습니다!",
                showConfirmButton: false,
                timer: 1500
            });
            isSendEmail.value = false
        }
    )
}

const showPassword = async () => {
    await checkCerticationNumber(
        {
            userNumber: certificationNumber.value,
            userId: findPasswordId.value
        },
        (response) => {
            isCertification.value = true
            if (response.data.check) {
                certificationState.value = true
                findPassword.value = response.data.password
            } else {
                // isCertification.value = true
                certificationState.value = false;
                findPassword.value = "유효하지 않은 인증번호 입니다."
            }
        },
        (error) => {
            console.log(error);
            Swal.fire({
                position: "top",
                icon: "error",
                title: "서버에 문제가 발생했습니다!",
                showConfirmButton: false,
                timer: 1500
            });
        }
    )
}

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
                            <!-- <a href="" class="text-decoration-none text-muted small" onclick="javascript:findpw()">비밀번호
                                찾기</a> -->
                            <a href="#" class="text-decoration-none text-muted" data-bs-toggle="modal"
                                data-bs-target="#findPassword">
                                비밀번호 찾기
                            </a>
                        </li>
                        <!-- <div class="vr no-gutters"></div>
                        <li class="list-group-item border-0 p-0 ms-3 me-3">
                            <a href="" class="text-decoration-none text-muted small">아이디 찾기</a>
                        </li> -->
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

    <div class="modal fade" id="findPassword" tabindex="-1" aria-labelledby="findPasswordLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h1 class="modal-title fs-5 fw-bolder" id="findPassword">비밀번호 찾기</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p class="text-center p-0 mb-0 fs-6">회원가입된 아이디를 입력하시면,</p>
                    <p class="text-center p-0 fs-6 mb-3"> 관련 인증번호 메일을 발송해드립니다.</p>
                    <div class="d-flex align-items-center justify-content-center">
                        <label for="inputEmail" class="form-label mb-0 me-2">아이디</label>
                        <input type="text" v-model="findPasswordId" class="form-control w-50" id="inputEmail">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-target="#certify" data-bs-toggle="modal"
                        @click="sendEmail">인증번호
                        전송</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                </div>
            </div>
        </div>
    </div>
    <div v-if="isSendEmail" class="modal fade" id="certify" aria-hidden="true"
        aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5 fw-bolder" id="exampleModalToggleLabel2">이메일 인증하기</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body row justify-content-center">
                    <p class="text-center p-0 fs-6 mb-3">{{ findPasswordId }}로 가입된 이메일로 전송된 인증번호를 입력해주세요</p>
                    <input type="text" v-model="certificationNumber" class="form-control w-50" id="inputCertify"
                        placeholder="인증번호 6자리 입력">
                    <div v-show="isCertification">
                        <p v-if="certificationState" class="text-center p-0 fs-6 mt-3">{{ findPasswordId}}님의 비밀번호: <span class="text-success">{{findPassword}}</span></p>
                        <p v-else class="text-center p-0 fs-6 mt-3 text-danger">{{ findPassword }}</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" @click="showPassword">인증하기</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                </div>
            </div>
        </div>
    </div>
    <div v-else class="modal fade" id="certify" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2"
        tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5 fw-bolder" id="exampleModalToggleLabel2">이메일 인증하기</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body row justify-content-center">
                    <p class="text-center p-0 fs-6 mb-3">이메일로 전송이 실패했습니다.</p>
                </div>
                <div class="modal-footer">
                    <!-- <button class="btn btn-primary" @click="showPassword">인증하기</button> -->
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">나가기</button>
                </div>
            </div>
        </div>
    </div>

</template>

<style scoped></style>