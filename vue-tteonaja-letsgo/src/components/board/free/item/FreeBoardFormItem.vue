<script setup>
import { ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { getArticle, modifyArticle, registArticle } from '@/api/board';

import { jwtDecode } from 'jwt-decode';

import Swal from 'sweetalert2';

import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';

const props = defineProps({ type: String });
const router = useRouter();
const route = useRoute();

const article = ref(
    {
        articleNo: 0,
        userId: "",
        subject: "",
        content: "",
    }
);

// 글 작성 내용 감시
watch(
    () => { article.value.content; },
    (newValue, oldValue) => {
        console.log(article.value.content);
    },
    { deep: true }
);


// 글 수정시 원래 있던 제목, 내용 긁어옴
if (props.type === "modify") {
    article.value.articleNo = route.params.id;
    getArticle(
        {
            boardType: 'free',
            articleNo: article.value.articleNo
        },
        (response) => {
            article.value.subject = response.data.subject;
            article.value.content = response.data.content;
        },
        (error) => {
            console.error(error);
        }
    );
}

const moveToList = () => {
    router.replace({ name: 'free-list' });
};

const controlArticle = () => {
    let token = sessionStorage.getItem("accessToken");
    let decodeToken = jwtDecode(token);
    article.value.userId = decodeToken.userId;
    if (props.type === "regist") {
        registArticle(
            {
                userId: article.value.userId,
                subject: article.value.subject,
                content: article.value.content
            },
            (response) => {
                if (response.status == 201) {
                    Swal.fire({
                        position: "top",
                        icon: "success",
                        title: "글 등록이 완료되었습니다!",
                        showConfirmButton: false,
                        timer: 1500
                    });
                    moveToList();
                } else {
                    Swal.fire({
                        position: "top",
                        icon: "error",
                        title: "글 등록 처리에 문제가 발생했습니다!",
                        showConfirmButton: false,
                        timer: 1500
                    });
                }
            },
            (error) => console.error(error)
        );
    }

    if (props.type === "modify") {
        modifyArticle(
            {
                boardType: "free",
                article: {
                    articleNo: article.value.articleNo,
                    subject: article.value.subject,
                    content: article.value.content
                }
            },
            (response) => {
                console.log(response);
                if (response.status == 200) {
                    Swal.fire({
                        position: "top",
                        icon: "success",
                        title: "글 수정이 완료되었습니다!",
                        showConfirmButton: false,
                        timer: 1500
                    });
                    moveToList();
                } else {
                    Swal.fire({
                        position: "top",
                        icon: "error",
                        title: "글 수정 처리에 문제가 발생했습니다!",
                        showConfirmButton: false,
                        timer: 1500
                    });
                }
            },
            (error) => {

            }
        );
    }
};

</script>

<template>
    <p class="text-secondary">글쓰기</p>
    <hr class="mb-5">
    <label for="subjectFormControlInput1" class="form-label fw-bolder">제목</label>
    <input type="email" class="subject-input mb-5" id="subjectFormControlInput1" v-model="article.subject"
        placeholder="글 제목을 입력하세요.">
    <label for="contentFormControlInput1" class="form-label fw-bolder">내용</label>
    <QuillEditor theme="snow" v-model:content="article.content" contentType="html" toolbar="full" />
    <!-- <div class="container" v-html="article.content">
    </div> -->
    <div class="mt-3 d-flex justify-content-between align-items-center">
        <button class="btn btn-secondary btn-sm" @click="moveToList">글 목록</button>
        <button class="btn btn-primary btn-sm" v-if="type === 'regist'" @click="controlArticle">등록하기</button>
        <button class="btn btn-primary btn-sm" v-if="type === 'modify'" @click="controlArticle">글 수정</button>
    </div>
</template>

<style scoped>
.subject-input {
    width: 100%;
    /* Make the title input width 100% */
    border-radius: 0;
    /* Remove border radius to make corners sharp */
    padding: 0.375rem 0.75rem;
    /* Add some padding to the input for better appearance */
    border: 1px solid #ced4da;
    /* Match the border style of typical form inputs */
    outline: none;
    /* Remove the default outline */
    box-sizing: border-box;
    /* Ensure the width includes padding and border */
}
</style>