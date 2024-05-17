<script setup>
import { ref } from 'vue';
import { idCheck, register } from '@/api/user';
import Swal from 'sweetalert2';
import { useRoute, useRouter } from 'vue-router';

const isIdCheck = ref(false);

const router = useRouter();

const newUser = ref(
    {
        userId: "",
        userName: "",
        userPassword: "",
        email: "",
        phone: "",
        userNickname: ""
    }
);

const userIdCheck = () => {
    console.log(newUser.value.userId);
    idCheck(
        newUser.value.userId,
        (response) => {
            console.log(response);
            if (!response.data.check) {
                Swal.fire({
                    position: "top",
                    icon: "success",
                    title: "아이디 사용이 가능합니다.",
                    showConfirmButton: false,
                    timer: 1500
                });
                isIdCheck.value = true;
            } else {
                Swal.fire({
                    position: "top",
                    icon: "error",
                    title: "존재하는 아이디입니다.",
                    showConfirmButton: false,
                    timer: 1500
                });
                isIdCheck.value = false;
            }
        },
        (error) => {
            Swal.fire({
                position: "top",
                icon: "error",
                title: "연결 오류.",
                showConfirmButton: false,
                timer: 1500
            });
            console.log(error);
        }
    );
};

const userRegister = () => {
    console.log(newUser.value);
    if (isIdCheck.value) {
        register(
            newUser.value,
            (response) => {
                Swal.fire({
                    position: "top",
                    icon: "success",
                    title: "회원가입이 완료되었습니다!",
                    showConfirmButton: false,
                    timer: 1500
                });
                router.replace({ name: 'login' });
            },
            (error) => {
                Swal.fire({
                    position: "top",
                    icon: "error",
                    title: "회원가입이 실패하였습니다!",
                    showConfirmButton: false,
                    timer: 1500
                });
                console.log(error);
            }
        );
    } else {
        Swal.fire("아이디 중복확인을 해주세요!");
    }
};
</script>

<template>
    <div class="container text-center mt-3">
        <p class="fs-1 fw-bolder">회원가입</p>
    </div>
    <form id="userInfo">
        <div class="border shadow rounded p-3 col-sm-6 col-md-5 col-lg-3 container centered mt-3 mb-3">
            <div class="justify-content-center">
                <label for="inputUsername" class="form-label">아이디</label> <span class="text-warning">*</span>
                <span><button type="button" class="btn btn-sm offset-1 btn-secondary" @click="userIdCheck"
                        style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">중복
                        확인</button></span>
                <input type="id" class="form-control mb-3" id="inputUsername" v-model="newUser.userId"
                    placeholder="아이디 입력" required>
            </div>
            <div class="justify-content-center">
                <label for="inputPassword" class="form-label">비밀번호</label> <span class="text-warning">*</span>
                <input type="password" class="form-control mb-3" id="inputPassword" v-model="newUser.userPassword"
                    placeholder="비밀번호 입력" required>
            </div>
            <div class="justify-content-center">
                <label for="inputPassword2" class="form-label">비밀번호 확인</label> <span class="text-warning">*</span>
                <input type="password" class="form-control mb-3" id="inputPassword2" placeholder="비밀번호 재입력" required>
            </div>
            <div class="justify-content-center">
                <label for="name" class="form-label">이름</label> <span class="text-warning">*</span>
                <input type="text" class="form-control mb-3" id="name" v-model="newUser.userName" placeholder="성 이름"
                    required>
            </div>
            <div class="justify-content-center">
                <label for="nickname" class="form-label">닉네임</label> <span class="text-warning">*</span>
                <input type="text" class="form-control mb-3" id="name" v-model="newUser.userNickname" placeholder="닉네임"
                    required>
            </div>
            <div class="justify-content-center">
                <label for="inputEmail" class="form-label">이메일</label> <!--<span class="text-warning">*</span>-->
                <input type="text" class="form-control mb-3" id="inputEmail" v-model="newUser.email"
                    placeholder="user@domain.com">
            </div>
            <div class="justify-content-center">
                <label for="inputPhone" class="form-label">휴대폰 번호</label> <span class="text-warning">*</span>
                <input type="text" class="form-control mb-3" id="inputPhone" v-model="newUser.phone"
                    placeholder="-를 제외하고 입력해주세요." required>
            </div>
            <!-- <div class="justify-content-center">
                <label for="inputGender" class="form-label" required>성별</label> <span class="text-warning">*</span>
                <select id="inputGender" class="form-select" v-model="newUser.">
                    <option selected>선택해주세요</option>
                    <option>남자</option>
                    <option id="woman">여자</option>
                </select>
            </div> -->
        </div>
        <div class="container text-center mt-4 ">
            <button type="button" class="btn btn-primary" @click="userRegister">가입하기</button>
        </div>
    </form>

</template>

<style scoped></style>