<script setup>
import { onMounted, ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { registComment } from '@/api/board';
import { jwtDecode } from 'jwt-decode';
import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';

const props = defineProps({ 
    commentParams: Object,
    type: String
});
 
const router = useRouter();
const route = useRoute();

const comment = ref(
    {
        articleNo: 0,
        userId: "",
        content: "",
    }
);

watch(
    () => { comment.value.content; },
    (newValue, oldValue) => {
        console.log(comment.value.content);
    },
    { deep: true }
);

const refreshPage = () => {
    router.go(0);
};

const controlComment = () => {
    let token = sessionStorage.getItem("accessToken");
    let decodeToken = jwtDecode(token);
    console.log(comment.value.content);
    comment.value.userId = decodeToken.userId;
    comment.value.articleNo = props.commentParams.articleNo;
    if (props.type === "regist") {
        registComment(
            {
                boardType: props.commentParams.boardType,
                comment: comment.value,
            },
            (response) => {
                    console.log(response);
                    refreshPage();
            },
            (error) => console.error(error)
        );
    }

    // if (props.type === "modify") {
    //     modifyArticle(
    //         {
    //             boardType: "free",
    //             article: {
    //                 articleNo: article.value.articleNo,
    //                 subject: article.value.subject,
    //                 content: article.value.content
    //             }
    //         },
    //         (response) => {
    //             console.log(response);
    //             if (response.status == 200) {
    //                 Swal.fire({
    //                     position: "top",
    //                     icon: "success",
    //                     title: "글 수정이 완료되었습니다!",
    //                     showConfirmButton: false,
    //                     timer: 1500
    //                 });
    //                 moveToList();
    //             } else {
    //                 Swal.fire({
    //                     position: "top",
    //                     icon: "error",
    //                     title: "글 수정 처리에 문제가 발생했습니다!",
    //                     showConfirmButton: false,
    //                     timer: 1500
    //                 });
    //             }
    //         },
    //         (error) => {

    //         }
    //     );
    // }
}
</script>

<template>
    <QuillEditor theme="snow" contentType="html" v-model:content="comment.content" toolbar="mini" placeholder="댓글을 입력해주세요." />
    <div class="container mt-3 text-end p-0">
            <button class="btn btn-success btn-sm" @click="controlComment">댓글 작성</button>
    </div>
</template>

<style scoped></style>