<script setup>
import { ref } from 'vue';

</script>

<template>
    <div class="container text-center mt-3">
        <p class="fs-1 fw-bolder">회원가입</p>
    </div>
    <form id="userInfo">
        <div class="border shadow rounded p-3 col-sm-6 col-md-5 col-lg-3 container centered mt-3 mb-3">
            <div class="justify-content-center">
                <label for="inputUsername" class="form-label">아이디</label> <span class="text-warning">*</span>
                <span><button class="btn btn-sm offset-1 btn-secondary"
                        style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">중복
                        확인</button></span>
                <input type="id" class="form-control mb-3" id="inputUsername" placeholder="아이디 입력" required>
            </div>
            <div class="justify-content-center">
                <label for="inputPassword" class="form-label">비밀번호</label> <span class="text-warning">*</span>
                <input type="password" class="form-control mb-3" id="inputPassword" placeholder="비밀번호 입력" required>
            </div>
            <div class="justify-content-center">
                <label for="inputPassword2" class="form-label">비밀번호 확인</label> <span class="text-warning">*</span>
                <input type="password" class="form-control mb-3" id="inputPassword2" placeholder="비밀번호 재입력" required>
            </div>
            <div class="justify-content-center">
                <label for="name" class="form-label">이름</label> <span class="text-warning">*</span>
                <input type="text" class="form-control mb-3" id="name" placeholder="성 이름" required>
            </div>
            <div class="justify-content-center">
                <label for="inputEmail" class="form-label">이메일</label> <!--<span class="text-warning">*</span>-->
                <input type="text" class="form-control mb-3" id="inputEmail" placeholder="user@domain.com" required>
            </div>
            <div class="justify-content-center">
                <label for="inputCity" class="form-label">휴대폰 번호</label> <span class="text-warning">*</span>
                <input type="text" class="form-control mb-3" id="inputCity" placeholder="-를 제외하고 입력해주세요." required>
            </div>
            <div class="justify-content-center">
                <label for="inputGender" class="form-label" required>성별</label> <span class="text-warning">*</span>
                <select id="inputGender" class="form-select">
                    <option selected>선택해주세요</option>
                    <option>남자</option>
                    <option id="woman">여자</option>
                </select>
            </div>
        </div>
        <div class="container text-center mt-4 ">
            <button type="button" class="btn btn-primary">가입하기</button>
        </div>
    </form>

</template>

<style scoped></style>