<script setup>
import { onMounted, ref } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useMemberStore } from '@/stores/member';
import { storeToRefs } from 'pinia';
import { jwtDecode } from 'jwt-decode';
import { httpStatusCode } from '@/util/http-status';
import { deleteUser, findById, logout, modifyUser } from '@/api/user';
import Swal from 'sweetalert2';
import { useMenuStore } from '@/stores/menu';

const memberStore = useMemberStore();
const menuStore = useMenuStore();
const { tokenRegenerate } = memberStore;
const { changeMenuState } = menuStore;
const router = useRouter();

const newInfo = ref(
    {
        userId: "",
        userPassword: "",
        userNickname: "",
        email: "",
        phone: ""
    }
);

const passwordConfirm = ref("");

const userName = ref("");

const modifyUserInfo = () => {
    if (newInfo.value.userPassword === passwordConfirm.value) {
        modifyUser(
            JSON.stringify(newInfo.value),
            (response) => {
                if (response.status === httpStatusCode.OK) {
                    Swal.fire({
                        position: "top",
                        icon: "success",
                        title: "회원 정보 수정이 성공적으로 완료되었습니다.",
                        showConfirmButton: false,
                        timer: 1500
                    });
                }
            },
            (error) => {
                Swal.fire({
                    position: "top",
                    icon: "error",
                    title: "회원 정보 수정에 실패하였습니다.",
                    showConfirmButton: false,
                    timer: 1500
                });
                console.log(error);
            }
        );
    }
    else {
        Swal.fire({
            position: "top",
            icon: "error",
            title: "비밀번호를 확인해주세요.",
            showConfirmButton: false,
            timer: 1500
        });
    }
};

const removeUserInfo = () => {
    Swal.fire({
        title: "정말로 탈퇴하시겠습니까?",
        text: "삭제 후 복구할 수 없음에 주의하세요!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "확인",
        cancelButtonText: "취소"
    }).then((result) => {
        if (result.isConfirmed) {
            deleteUser(
                newInfo.value.userId,
                (response) => {
                    Swal.fire({
                        title: "성공적으로 탈퇴하였습니다.",
                        text: "이용해주셔서 감사합니다.",
                        icon: "success",
                        confirmButtonText: "확인",
                    }).then((result) => {
                        sessionStorage.removeItem("accessToken");
                        sessionStorage.removeItem("refreshToken");
                        sessionStorage.removeItem("recentUseInfo");
                        sessionStorage.removeItem("userMenu");
                        changeMenuState();
                        router.replace("/");
                    });
                },
                (error) => {
                    Swal.fire({
                        title: "탈퇴에 실패하였습니다.",
                        text: "잠시 후 다시 시도해주십시오.",
                        icon: "error",
                        confirmButtonText: "확인",
                    });
                    console.log(error);
                }
            );
        }
    });
};

onMounted(async () => {
    let token = sessionStorage.getItem("accessToken");
    let decodeToken = jwtDecode(token);
    await findById(
        decodeToken.userId,
        (response) => {
            console.log(response);
            if (response.status === httpStatusCode.OK) {
                newInfo.value.userId = response.data.userInfo.userId;
                userName.value = response.data.userInfo.userName;
                newInfo.value.userNickname = response.data.userInfo.userNickname;
                newInfo.value.email = response.data.userInfo.email;
                newInfo.value.phone = response.data.userInfo.phone;
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
})

</script>

<template>
    <div class="container col-md-10 col-sm-8 col-lg-6 text-center mt-5 shadow p-4 rounded">
        <h4 class="fw-bolder">내 정보</h4>
        <div class="container mt-5">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col" class="bg-dark text-white align-middle">사용자 ID</th>
                        <td>
                            <input type="text" class="form-control text-center" v-model="newInfo.userId" readonly>
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="col" class="bg-dark text-white align-middle">새로운 비밀번호</th>
                        <td>
                            <input type="password" class="form-control text-center" v-model="newInfo.userPassword">
                        </td>
                    </tr>
                    <tr>
                        <th scope="col" class="bg-dark text-white align-middle">비밀번호 확인</th>
                        <td>
                            <input type="password" class="form-control text-center" v-model="passwordConfirm">
                            <label v-if="passwordConfirm.length < 8 || newInfo.userPassword.length < 8"
                                class="form-text text-danger">비밀번호는 8자 이상이어야 합니다.
                            </label>
                            <label v-else-if="passwordConfirm === newInfo.userPassword" class="form-text text-success">비밀번호가
                                일치합니다.</label>
                            <label v-else class="form-text text-danger">비밀번호가
                                일치하지 않습니다.</label>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row" class="bg-dark text-white align-middle">이름</th>
                        <td>
                            <input type="text" class="form-control text-center" v-model="userName" readonly>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row" class="bg-dark text-white align-middle">닉네임</th>
                        <td>
                            <input type="text" class="form-control text-center" v-model="newInfo.userNickname">
                        </td>
                    </tr>
                    <tr>
                        <th scope="row" class="bg-dark text-white align-middle">이메일</th>
                        <td>
                            <input type="text" class="form-control text-center" v-model="newInfo.email">
                        </td>
                    </tr>
                    <tr>
                        <th scope="row" class="bg-dark text-white align-middle">휴대폰 번호</th>
                        <td>
                            <input type="text" class="form-control text-center" v-model="newInfo.phone">
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="cotainer text-center">
            <button class="btn btn-success btn-sm me-5" @click="modifyUserInfo">정보 수정</button>
            <button class="btn btn-danger btn-sm" @click="removeUserInfo">회원 탈퇴</button>
        </div>
    </div>
</template>

<style scoped></style>