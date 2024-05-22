<script setup>
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { deleteArticle, getArticle } from '@/api/board';
import BoardCommentWrite from '@/components/board/comment/BoardCommentWrite.vue';
import Swal from 'sweetalert2';
import BoardCommentList from '../comment/BoardCommentList.vue';
import { jwtDecode } from 'jwt-decode';
import { getGPTResponse } from '@/util/translator';
// import { Typed } from "@duskmoon/vue3-typed-js";
// import { TypedOptions } from "@duskmoon/vue3-typed-js";

const route = useRoute();
const router = useRouter();

const params = ref(
    {
        boardType: 'free',
        articleNo: route.params.id
    }
);

const currentUserId = ref("")

const article = ref({});

const getArticleDetail = () => {
    getArticle(params.value,
        (response) => {
            article.value = response.data;
            console.log(response);
        },
        (error) => {
            console.log("게시물 상세 정보 오류");
            console.log(error);
        }
    );
};

const deleteFreeArticle = () => {
    deleteArticle(
        params.value,
        (response) => {
            if (response.status == 200) {
                Swal.fire({
                    position: "top",
                    icon: "success",
                    title: "글 삭제가 완료되었습니다!",
                    showConfirmButton: false,
                    timer: 1500
                });
                moveToList();
            } else {
                Swal.fire({
                    position: "top",
                    icon: "error",
                    title: "글 삭제 처리에 문제가 발생했습니다!",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        },
        (error) => {
            Swal.fire({
                position: "top",
                icon: "error",
                title: "서버에 문제가 발생했습니다!",
                showConfirmButton: false,
                timer: 1500
            });
            console.log(error);
        }
    );
};

const translated = ref("")
const translateMode = ref(false)

const translate = async () => {
    if (!translateMode.value) {
        translated.value = await getGPTResponse(article.value.content)
    }
    translateMode.value = !translateMode.value
}

// const options: TypedOptions = {
//     strings: ["Hello", "World", "This is vue3-typed-js"],
//     loop: true,
//     typeSpeed: 30,
// };

onMounted(() => {
    getArticleDetail();
    currentUserId.value = jwtDecode(sessionStorage.getItem(['accessToken'])).userId;
});

const moveToList = () => {
    router.replace({ name: 'free-list' });
};

const moveToModify = () => {
    router.push({ name: 'free-modify', params: { articleNo: article.value.articleNo } });
}

</script>

<template>
    <br><br>
    <div class="container mt-5 shadow p-4 rounded">
        <h1 class="fw-light mb-5 text-center">자유게시판</h1>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <table class="table">
                    <thead>
                        <tr>
                            <th colspan="10" class="table-dark text-center">{{ article.subject }}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="align-middle">
                            <th colspan="1" class="table-secondary text-center">작성자 </th>
                            <th colspan="2" class="text-center">{{ article.userId }}</th>
                            <th colspan="1" class="table-secondary text-center">작성일자 </th>
                            <th colspan="2" class="text-center">{{ article.registerTime }}</th>
                            <th colspan="1" class="table-secondary text-center">조회수 </th>
                            <th colspan="2" class="text-center">{{ article.hit }}</th>
                            <th colspan="1" class="table-secondary text-center"><a href="#" class="btn fw-bolder p-0"
                                    @click="translate">Translate<i class="bi bi-globe"></i></a></th>
                        </tr>
                        <tr v-if="!translateMode">
                            <td colspan="10" v-html="article.content" class="p-3"></td>
                        </tr>
                        <tr v-else>
                            <!-- <td colspan="10" class="p-3">
                                <Typed :options="options">
                                    <h1 class="typing"></h1>
                                </Typed>
                                <vue-typed-js :typeSpeed="60" :strings="['test']">
                                    <p></p>
                                </vue-typed-js>
                            </td> -->
                            <td colspan="10" class="p-3" v-html="translated"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="container border p-3" id="comment_block">
            <p>댓글 목록</p>
            <hr>
            <BoardCommentList board-type="free" :comment-params="params" :write-checker="writeChecker"
                @deleted-comment="deletedComment" @complete-write="completeWrite" />
            <BoardCommentWrite :comment-params="params" @write-comment="writeComment" />
        </div>

        <div v-if="currentUserId === article.userId"
            class="container mt-3 d-flex justify-content-between align-items-center p-1">
            <div>
                <button class="btn btn-secondary btn-sm" @click="moveToList">글 목록</button>
            </div>
            <div>
                <button class="btn btn-primary btn-sm" @click="moveToModify">글 수정</button>
                <button class="btn btn-danger ms-3 btn-sm" @click="deleteFreeArticle">글 삭제</button>
            </div>
        </div>
    </div>


</template>

<style scoped></style>